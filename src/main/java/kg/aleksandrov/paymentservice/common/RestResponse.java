package kg.aleksandrov.paymentservice.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author dialeksandrov
 * @created 12/05/2022
 **/
@Data
public class RestResponse implements Serializable {
    private Status status;
    private ErrorCode errorCode;
    private Object data;
    private String message;

    public RestResponse() {
    }

    public RestResponse(Object data) {
        this.status = Status.SUCCESS;
        this.data = data;
    }

    public RestResponse(Status status, Object data) {
        this.status = status;
        this.data = data;
    }

    public RestResponse(Status status, Object data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public enum Status {
        SUCCESS, FAIL, ERROR
    }

    public enum ErrorCode {
        NOT_FOUND, UNKNOWN
    }

    public static RestResponse success(Object data) {
        return new RestResponse(data);
    }

    public static RestResponse success(Object data, String message){
        return new RestResponse(Status.SUCCESS, data, message);
    }

    public static RestResponse error(String message) {
        return new RestResponse(Status.ERROR, null, message);
    }

    public static RestResponse fail(String message){
        return new RestResponse(Status.FAIL, null, message);
    }
}
