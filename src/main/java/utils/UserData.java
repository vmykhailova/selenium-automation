package utils;

import com.github.javafaker.Faker;
import model.User;

public class UserData {

    public static User defaultUser() {
        return new User("realapp", "realapp@mail.com", "qwerty123");
    }

    public static User randomUser() {
        Faker faker = new Faker();
        User user = new User();
        user.setUserName(faker.name().username());
        user.setEmail(faker.name().lastName() + "@mail.com");
        user.setPassword("qwerty123");
        return user;
    }
}
