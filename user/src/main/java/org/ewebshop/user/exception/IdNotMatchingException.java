package org.ewebshop.user.exception;

public class IdNotMatchingException extends Exception{
    public IdNotMatchingException() {
        super("Username is not matching with the given one");
    }
}
