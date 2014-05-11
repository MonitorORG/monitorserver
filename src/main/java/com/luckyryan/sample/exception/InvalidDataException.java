package com.luckyryan.sample.exception;

/**
 * User: ryan
 * Date: 2/20/13
 */
public class InvalidDataException extends RuntimeException {

    public InvalidDataException(String s) {
        super(s);
    }
}
