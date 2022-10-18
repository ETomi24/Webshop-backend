package org.ewebshop.product.exception;

public class IdNotMatchingException extends Exception{
    public IdNotMatchingException() {
        super("Id is not matching with the given one");
    }
}
