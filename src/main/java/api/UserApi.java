package api;

import io.restassured.response.Response;

public class UserApi extends BaseHttpClient {
    private final String apiPath = "/api/auth/user";
    public Response deleteUser (String token) {
        return doDeleteRequest(apiPath, token);
    }
}
