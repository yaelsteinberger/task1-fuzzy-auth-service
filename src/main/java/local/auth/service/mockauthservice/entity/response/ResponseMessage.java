package local.auth.service.mockauthservice.entity.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class ResponseMessage<T>  {
    private final HttpStatus status;
    private final String path;

    @Nullable
    private final String message;

    @Nullable
    private final T data;

    public ResponseMessage(
            HttpStatus status,
            @Nullable String message,
            String path,
            @Nullable T data) {
        this.status = status;
        this.message = message;
        this.path = path;
        this.data = data;
    }

    public Map getResponseMessage(){
        String date = new Date().toString();

        return Collections.unmodifiableMap(new LinkedHashMap<>() {{
            put("timestamp", date);
            put("status", status.value());
            if((status.value() >= 400) && (status.value() < 600)){put("error", status.name());}
            if (message != null) {put("message", message);}
            put("path", path);
            if (data != null) {put("data", data);}
        }});
    }

    public String writeToJsonString() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(getResponseMessage());
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getPath() {
        return path;
    }

    @Nullable
    public String getMessage() {
        return message;
    }

    @Nullable
    public T getData() {
        return data;
    }
}
