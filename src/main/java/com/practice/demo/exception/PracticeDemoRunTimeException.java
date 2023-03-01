package com.practice.demo.exception;

public class PracticeDemoRunTimeException extends RuntimeException {
	
private static final long serialVersionUID = 1L;
    
    public static final String PAGE_SIZE_INVALID = "Page size is not valid";

    public PracticeDemoRunTimeException() {
    }

    public PracticeDemoRunTimeException(String msg) {
        super(msg);
    }

    public PracticeDemoRunTimeException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public PracticeDemoRunTimeException(Throwable cause) {
        super(cause);
    }


}
