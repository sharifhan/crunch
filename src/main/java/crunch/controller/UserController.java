package crunch.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import crunch.domain.User;
import crunch.exception.UserAlreadyExistsException;
import crunch.service.UserService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private BusinessLogic inputValidator;
    @Autowired HttpServletRequest request;
    
    @Inject
    public UserController(final UserService userService) {
        this.userService = userService;
    }
    
    
    @RequestMapping("/calculate")
    public User calculateSalary(@RequestParam String email, 
							 @RequestParam String taxyear,
							 @RequestParam String gross   ) {
    	
    	LOGGER.debug("Calculate net salary for " + request.getRemoteAddr());
    	
    	inputValidator = new BusinessLogic();
    	inputValidator.validateEamil(email);
    	
    	User user = new User(email, taxyear, gross, "0", request.getRemoteAddr());
    	return userService.save(user);
	
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleUserAlreadyExistsException(UserAlreadyExistsException e) {
        return e.getMessage();
    }

}
