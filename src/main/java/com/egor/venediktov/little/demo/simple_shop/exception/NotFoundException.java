package com.egor.venediktov.little.demo.simple_shop.exception;


public class NotFoundException extends Exception {

    private final Class<?> entityClass;

    private final Long entityId;

    public NotFoundException(Class<?> entityClass, Long entityId) {
        super("Object with type: " + entityClass + ", not found for entityId: " + entityId);
        this.entityClass = entityClass;
        this.entityId = entityId;
    }
}
