package org.ewebshop.exception;

public class IdNotMatchingException extends Exception{
    public IdNotMatchingException() {
        super("Id is not matching with the given one");
    }
}
