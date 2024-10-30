package utils;

import pojo.user.User;
import utils.enums.UserStatus;

import java.util.Random;

public class UsetTestDataGenerator extends TestDataGenerator {

    public User generateUserData() {
        UserStatus userStatus = generateRandomUserCategory();
        User user = new User();
        user.setId(faker().idNumber().hashCode());
        user.setUsername(faker().funnyName().name());
        user.setFirstName(faker().name().firstName());
        user.setLastName(faker().name().lastName());
        user.setEmail("test@test.com");
        user.setPassword("password");
        user.setPhone(faker().phoneNumber().phoneNumber());
        user.setUserStatus(userStatus.getStatus());
        return user;
    }

    private UserStatus generateRandomUserCategory() {
        int pick = new Random().nextInt(UserStatus.values().length);
        return UserStatus.values()[pick];
    }

}
