package utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
public enum PetsCategory {
    DOGS(1, "dogs"),
    CATS(2, "cats"),
    OTHER(3, "other");

    private int id;
    private String categoryName;
}
