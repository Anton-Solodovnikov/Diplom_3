package api.steps;

import api.AuthApi;
import api.UserApi;
import api.json.UserRequest;
import api.json.UserResponse;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.Matchers.equalTo;

public class UserSteps {
    private AuthApi authApi = new AuthApi();
    private UserApi userApi = new UserApi();
    private ValidatableResponse response;
    @Step("Регистрация пользователя")
    public void registerUser(UserRequest userRequest) {
        response = authApi.register(userRequest).then();
    }
    @Step("Проверка кода ответа")
    public void checkStatusCode(int expectedCode) {
        response.statusCode(equalTo(expectedCode));
    }
    @Step("Удаление пользователя")
    public void deleteUser(String token) {
        userApi.deleteUser(token);
    }
    @Step("Взять токен из ответа")
    public String getToken() {
        return response.extract().as(UserResponse.class).getAccessToken().split(" ")[1];
    }
    @Step("Логин пользователя")
    public void loginUser(String email, String password) {
        response = authApi.login(new UserRequest(email, password)).then();
    }
}
