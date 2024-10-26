package pojo.pet;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
public class Category {

    private Integer id;
    private String name;
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

}