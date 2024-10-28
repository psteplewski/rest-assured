package utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PetStatus {
    AVALIABLE("available"),
    PENDING("pending"),
    SOLD("sold");
    
    private String status;
}
