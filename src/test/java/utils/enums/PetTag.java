package utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PetTag {
    YOUNG_PET(1, "young-pet"),
    ADULT_PET(2, "adult-pet");

    private int id;
    private String tagyName;
}
