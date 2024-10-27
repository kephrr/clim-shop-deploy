package soft_afric.clim.shop.clim_shop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;
import soft_afric.clim.shop.clim_shop.web.dto.request.RechercheDto;

@ControllerAdvice
public class GlobalExceptionHandler {
        @ExceptionHandler(NoHandlerFoundException.class)
        @ResponseStatus(HttpStatus.NOT_FOUND)
        public String handleNotFoundError(NoHandlerFoundException ex) {
            return "error/404";
        }
}
