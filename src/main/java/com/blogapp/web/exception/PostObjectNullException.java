package com.blogapp.web.exception;

public class PostObjectNullException extends Throwable {

    public PostObjectNullException() {
        super();
    }

    public PostObjectNullException(String message) {
        super(message);
    }

    public PostObjectNullException(String message, Throwable cause) {
        super(message, cause);
    }
}
