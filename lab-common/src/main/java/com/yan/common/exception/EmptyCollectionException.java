package com.yan.common.exception;

/**
 * Class-exception for notifying about empty collection
 */
public class EmptyCollectionException extends RuntimeException {

    public EmptyCollectionException() {
        super("!!! Collection is empty !!!");
    }
}
