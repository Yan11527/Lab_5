package com.yan.common.exception;

/**
 * Class-exception for notifying about wrong argument in command
 */
public class BadArgumentException extends RuntimeException {

    public BadArgumentException(String message) {
        super("!!! " + message + " !!!");
    }

}
