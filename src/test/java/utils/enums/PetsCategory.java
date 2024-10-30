package utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PetsCategory {
    DOGS(1, "dogs"),
    CATS(2, "cats"),
    OTHER(3, "other");

    private final int id;
    private final String categoryName;
}
