package cz.itnetwork.controller.advice;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.webjars.NotFoundException;

/**
 * This class defines what happens when NotFoundException and EntityNotFoundException are thrown
 *
 * @author ITnetwork
 * @author The author of Javadoc for this class is Kat
 */
@ControllerAdvice
public class EntityNotFoundExceptionAdvice {

    /**
     * <p>Handles NotFoundException and EntityNotFoundException</p>
     * <p>Returns http response status 404 - not found</p>
     */
    @ExceptionHandler({NotFoundException.class, EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleEntityNotFoundException() {
    }

}
