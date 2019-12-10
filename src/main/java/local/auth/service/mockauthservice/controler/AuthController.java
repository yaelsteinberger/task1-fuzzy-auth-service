package local.auth.service.mockauthservice.controler;


import com.fasterxml.jackson.core.JsonProcessingException;
import local.auth.service.mockauthservice.entity.SignupForm;
import local.auth.service.mockauthservice.entity.User;
import local.auth.service.mockauthservice.entity.response.*;
import local.auth.service.mockauthservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @GetMapping(path = MappingPaths.GET_USERS)
    public ResponseEntity getUsers() throws JsonProcessingException {

        ResponseMessage response;
        String path = MappingPaths.GET_USERS;
        Iterable<User> users = userRepository.findAll();

        response = new ResponseSuccess(
                    path,
                    users);

        return addBodyToResponse(response.getResponseMessage());
    }

    @GetMapping(path = MappingPaths.GET_USER)
    public ResponseEntity<Object> getUser(@PathVariable(name = "userName") String userName) throws JsonProcessingException {

        ResponseMessage response;
        String path = MappingPaths.GET_USER.replace("{userName}",userName);
        User user = userRepository.findUserByUserName(userName);

        if(user != null){
            response = new ResponseSuccess(path,user);
        }else{
            response = new ResponseError(
                    HttpStatus.NOT_FOUND,
                    "User doesn't exist",
                    path);
        }
        return addBodyToResponse(response.getResponseMessage());
    }


    @GetMapping(path = MappingPaths.IS_USER_ACTIVE)
    public ResponseEntity<Object> isUserAuth(@PathVariable(name = "userName") String userName) throws JsonProcessingException {

        ResponseMessage response;
        String path = MappingPaths.IS_USER_ACTIVE.replace("{userName}",userName);
        User user = userRepository.findUserByUserName(userName);

        boolean isActive = (user != null) && user.isActive();

        if(isActive){
            response = new ResponseSuccess(
                    path,
                    new HashMap<String,Boolean>() {{put("active",user.isActive());}});
        }else{

            String message = (user != null) ?
                    "User is disabled" :
                    "User doesn't exist";

            HttpStatus status = (user != null) ?
                    HttpStatus.FORBIDDEN :
                    HttpStatus.NOT_FOUND;

            response = new ResponseError(
                    status,
                    message,
                    path);
        }

        return addBodyToResponse(response.getResponseMessage());
    }

    @PostMapping(path = MappingPaths.SAVE_USER)
    public ResponseEntity<Object> saveUser(@RequestBody SignupForm signupForm) throws JsonProcessingException {

        ResponseMessage response;
        String path = MappingPaths.SAVE_USER;

        /* check if user already exists */
        if(userRepository.findUserByUserName(signupForm.getUserName()) == null){
            User data = userRepository.save(signupFormToUser(signupForm));
            response = new ResponseSuccess(path, data);
        }else{
            response = new ResponseError(
                    HttpStatus.FORBIDDEN,
                    "UserName already exists",
                    path);
        }

        return addBodyToResponse(response.getResponseMessage());
    }


    private User signupFormToUser(SignupForm signupForm){
        return new User(
                signupForm.getFirstName(),
                signupForm.getLastName(),
                signupForm.getUserName(),
                true
        );
    }

    private ResponseEntity<Object> addBodyToResponse(Map response) throws JsonProcessingException {
        return  ResponseEntity
                .status(HttpStatus.valueOf((Integer) response.get("status")))
                .body(response);
    }
}
