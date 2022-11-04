package org.ewebshop.category.exception;

public class DeleteException extends Exception{
    public DeleteException() {
        super("Delete is not possible because product(s) depends on it");
    }
}
