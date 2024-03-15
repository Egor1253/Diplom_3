package api;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static constants.Urls.*;
import static io.restassured.RestAssured.given;

public class UserSteps extends Client {

    @Step("User creation")
    public ValidatableResponse createUser(User user) {
        return given()
                .spec(getSpec())
                .body(user)
                .when()
                .post(AUTH_REGISTER_URL)
                .then();

    }

    @Step("User deletion")
    public void deleteUser(String accessToken) {
        given()
                .header("authorization", accessToken)
                .spec(getSpec())
                .when()
                .delete(AUTH_USER_URL);
    }

    public ValidatableResponse login(Credentials credentials) {
        return
                given()
                        .spec(getSpec())
                        .body(credentials)
                        .when()
                        .post(AUTH_LOGIN_URL)
                        .then();

    }

    @Step("Getting access token")
    public String getAccessToken(ValidatableResponse validatableResponse) {
        return validatableResponse.extract().path("accessToken");
    }

    @Step("Removing possible users after tests")
    public void deletingUsersAfterTests(String accessToken) {
        if (accessToken != null) {
            deleteUser(accessToken);
        } else {
            given().spec(getSpec())
                    .when()
                    .delete(AUTH_USER_URL);
        }
    }
}