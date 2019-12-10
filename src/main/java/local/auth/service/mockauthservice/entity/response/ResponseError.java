package local.auth.service.mockauthservice.entity.response;

import org.springframework.http.HttpStatus;

public class ResponseError extends ResponseMessage {

    public ResponseError(HttpStatus status, String message, String path) {

        super(status, message, path, null);
    }
}
