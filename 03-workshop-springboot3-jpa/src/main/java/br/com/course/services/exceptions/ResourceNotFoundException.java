package br.com.course.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(Object id) {
        super("Could not find resource. Id: " + id);
    }

}
