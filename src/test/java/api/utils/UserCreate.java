package api.utils;

import api.models.users.UserRequest;

import static io.qameta.allure.Allure.step;

public class UserCreate {

    public static UserRequest createUser() {
        return UserRequest.builder()
                        .username("John")
                        .email("john@gmail.com")
                        .password("1234").build();
    }
}
