package com.thh3;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson2.JSON;
import com.thh3.annotation.MaskTag;
import com.thh3.annotation.MaskType;
import com.thh3.filter.fast.CarNoValueFilter;
import com.thh3.filter.fast.MobileValueFilter;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class MaskReflectUtil {
    static Map<Class<?>, JSONUtil> map = new ConcurrentHashMap<>(64);
    public static String toJSONString(Object object) {
        if (object == null || ObjectUtil.isBasicType(object)) {
            return String.valueOf(object);
        }
        if (map.containsKey(object.getClass())) {
            JSONUtil util = map.get(object.getClass());
            return util.toJSONString(object);
        }
        boolean flag = object.getClass().isAnnotationPresent(MaskTag.class);
        if (!flag) {
            return JSON.toJSONString(object);
        }
        Field[] fields = object.getClass().getDeclaredFields();
        Set<ValueFilter> set = new LinkedHashSet<>();
        for (Field field : fields) {
            if (field.isAnnotationPresent(MaskTag.class)) {
                MaskTag annotation = field.getAnnotation(MaskTag.class);
                MaskType type = annotation.type();
                switch (type) {
                    case ANY:
                        break;
                    case CAR_NO:
                        set.add(new CarNoValueFilter());
                        break;
                    case MOBILE:
                        set.add(new MobileValueFilter());
                        break;
                }
            }
        }
        if (set.size() > 0) {
            // proxy instance
            Object proxy = Proxy.newProxyInstance(JSONUtil.class.getClassLoader(), new Class[]{JSONUtil.class}, new JSONUtilInvocationHandler(set));
            JSONUtil util = (JSONUtil) proxy;
            // add to Map
            map.put(object.getClass(), util);

            String json = util.toJSONString(object);
            return json;

        }
        return JSON.toJSONString(object);
    }

    public interface JSONUtil {
        String toJSONString(Object object);
    }
    static class JSONUtilInvocationHandler implements InvocationHandler {
        Set<ValueFilter> set;

        JSONUtilInvocationHandler(Set<ValueFilter> set) {
            this.set = set;
        }
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (!method.getName().equals("toJSONString")) {
                method.invoke(proxy, args);
            }
            ValueFilter[] filters = set.toArray(new ValueFilter[0]);
            return JSON.toJSONString(args[0], filters);
        }
    }
}
