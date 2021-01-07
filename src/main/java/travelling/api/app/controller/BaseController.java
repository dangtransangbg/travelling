package travelling.api.app.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import travelling.api.app.functional.Gate;
import travelling.api.app.functional.GateWay;
import travelling.api.app.functional.Transform;
import travelling.api.app.model.response.ObjectResponse;

import java.util.function.Consumer;

public class BaseController {
    public <R> ResponseEntity<ObjectResponse> success(R r, Consumer<R> gateWay) {
        gateWay.accept(r);
        ObjectResponse objectResponse = setStatus(null, "SUCCESS", "Thành Công", 200);
        return new ResponseEntity<>(objectResponse, HttpStatus.OK);
    }

    public ResponseEntity<ObjectResponse> success(GateWay gateWay) {
        gateWay.consumer();
        ObjectResponse objectResponse = setStatus("", "SUCCESS", "Thành Công", 200);
        return new ResponseEntity<>(objectResponse, HttpStatus.OK);
    }

    public <R> ResponseEntity<ObjectResponse> success(Transform<R> transform) {
        transform.consume();
        ObjectResponse objectResponse = setStatus("", "SUCCESS", "Thành Công", 200);
        return new ResponseEntity<>(objectResponse, HttpStatus.OK);
    }

    public <T, R> ResponseEntity<ObjectResponse> success(R r, Gate<T, R> gateway) {
        T t = gateway.consumer(r);
        ObjectResponse objectResponse = setStatus(t, "SUCCESS", "Thành Công", 200);
        return new ResponseEntity<>(objectResponse, HttpStatus.OK);
    }

    public ResponseEntity<ObjectResponse> error(String code, String message, int httpStatus) {
        ObjectResponse objectResponse = setStatus(null, code, message, httpStatus);

        return new ResponseEntity<>(objectResponse, HttpStatus.valueOf(httpStatus));
    }

    private ObjectResponse setStatus(Object data, String code, String message, int httpStatus) {
        ObjectResponse objectResponse = new ObjectResponse();
        ObjectResponse.ResponseStatus status = new ObjectResponse.ResponseStatus();
        status.setCode(code);
        status.setMessage(message);
        status.setStatusCode(httpStatus);
        objectResponse.setData(data);

        return objectResponse;
    }
}

