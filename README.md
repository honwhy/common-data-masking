# common-data-masking

Data masking util based on Fastjson ValueFilter and Jackson SimpleBeanPropertyFilter

# usage

## for Fastjson
- annotated with @Mask annotations on fields
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
## for Jackson
- annotated with @Mask annotations on fields, and @JsonFilter("MaskAny") on class level
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
# documents

- [fastjson-wiki](https://github.com/alibaba/fastjson/wiki)
- [jackson-docs](https://github.com/FasterXML/jackson-docs)