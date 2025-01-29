package uz.pdp.hotelsystem.payload;

public class ApiResult <T> {
    T result;
    boolean success;
    String message;

    public ApiResult(T result, boolean success, String message) {
        this.result = result;
        this.success = success;
        this.message = message;
    }

    public ApiResult(T result, Boolean success, String message) {
        this.result = result;
        this.success = success;
        this.message = message;
    }
}
