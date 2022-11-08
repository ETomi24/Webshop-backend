package org.ewebshop.product.exception;

public class MinusQuantityException extends Exception{
    public MinusQuantityException() {
        super("The quantity would be minus");
    }
}
