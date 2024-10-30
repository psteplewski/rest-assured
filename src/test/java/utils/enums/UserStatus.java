package utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserStatus {
    AVAILABLE(1),
    PENDING(2),
    NOT_EXISTS(3);

    private final Integer status;
}
