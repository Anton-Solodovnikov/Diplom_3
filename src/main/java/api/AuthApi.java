package api;

import api.json.UserRequest;
import io.restassured.response.Response;

public class AuthApi extends BaseHttpClient {
    private final String apiPath = "/api/auth/";

    public Response register (UserRequest userRequest) {
        String secondPartOfApiPath = apiPath + "register";
        return doPostRequest(secondPartOfApiPath, userRequest);
    }
    public Response login (UserRequest userRequest) {
        String secondPartOfApiPath = apiPath + "login";
        return doPostRequest(secondPartOfApiPath, userRequest);
    }
}
