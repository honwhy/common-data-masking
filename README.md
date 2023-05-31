# common-data-masking

Data masking util based on Fastjson ValueFilter and Jackson SimpleBeanPropertyFilter

# usage

## for POJOs within project
- when use Fastjson, annotated with @Mask annotations on fields
```java
@AllArgsConstructor
@Data
public class AnyDTO {
    @MaskCarNo
    private String val1;
    @MaskMobile
    private String val2;

}
```
- use util
```md
String json = MaskUtil.toJSONString(dto);
```
- when use Jackson, annotated with @Mask annotations on fields, and @JsonFilter("MaskAny") on class level
```java
@AllArgsConstructor
@Data
@JsonFilter("MaskAny")
public class AnyDTO {
    @MaskCarNo
    private String val1;
    @MaskMobile
    private String val2;

}
```
- use util
```md
String json = MaskJackUtil.writeValueAsString(dto);
```

## for POJOs outside project
- example 
```java
package com.thh3;

@AllArgsConstructor
@Data
public class SimpleDTO {
    private String val1;
    private String val2;
}
```
- properties configuration
```properties
com.thh3.SimpleDTO#val1=MaskCarNo
com.thh3.SimpleDTO#val2=MaskMobile
```
- use util
```md
String json = MaskSettingUtil.toJSONString(dto);
```

## for Map type
- composite your own util
```md
public class MaskMapUtil {

    public static String toJSONString(Object object) {
        return JSON.toJSONString(object, new ValueFilter[]{
                new KeyMapValueFilter("carNo", new CarNoValueFilter()),
                new KeyMapValueFilter("mobile", new MobileValueFilter())
        });
    }
}

```
- use util
```md
Map<String, Object> dto = new HashMap<>();
dto.put("carNo", "粤B-D23456");
dto.put("mobile", "13700137000");
String json = MaskMapUtil.toJSONString(dto);
```
# documents

- [fastjson-wiki](https://github.com/alibaba/fastjson/wiki)
- [jackson-docs](https://github.com/FasterXML/jackson-docs)