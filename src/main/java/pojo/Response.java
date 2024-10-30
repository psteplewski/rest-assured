package pojo;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Response {
    private Integer code;
    private String type;
    private String message;
    private Map<String, Object> additionalProperties = new HashMap<>();
}
