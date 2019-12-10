package local.auth.service.mockauthservice.entity.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;

import java.util.LinkedHashMap;
import java.util.Map;

public class ResponseSuccess extends ResponseMessage {

    public ResponseSuccess(String path, Object data) {
        super(HttpStatus.OK, null, path, data);
    }
}
