package local.auth.service.mockauthservice.controler;

public interface MappingPaths {
    String GET_USERS = "/users";
    String GET_USER = "user/{userName}";
    String IS_USER_ACTIVE = "user/{userName}/isactive";
    String SAVE_USER = "user/register/";
}
