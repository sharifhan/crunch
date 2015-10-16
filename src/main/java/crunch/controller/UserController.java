package crunch.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import crunch.domain.User;
import crunch.exception.CrunchException;
import crunch.service.UserService;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    
    private BusinessLogic inputValidator = new BusinessLogic();
    @Autowired HttpServletRequest request;
    
    @Inject
    public UserController(final UserService userService) {
        this.userService = userService;
    }
    
    
    @RequestMapping("/calculate")
    public User calculateSalary(@RequestParam String email, 
							 @RequestParam String taxyear,
							 @RequestParam String gross   ) {
    	
    	//http://localhost:8080/calculate?email=test@email.com&taxyear=2014/15&gross=120000
    	
    	LOGGER.debug("Calculate net salary for " + request.getRemoteAddr());
    	
    	
    	if(!validate(email, taxyear, gross)){
    		throw new CrunchException(String.format("Invalid input: check email, taxyear and gross amount are valid"));
    	}
    	
    	User user = new User(email, taxyear, gross, inputValidator.calculateNetAmount(taxyear, gross), request.getRemoteAddr());
    	return userService.save(user);
    	    
    }

    private boolean validate(String email, String taxyear, String gross) {
    	
    	return (inputValidator.validateEamil(email)&&inputValidator.validateGrossRange(gross)&&inputValidator.validateTaxYear(taxyear)) ? true : false;
		
	}


	@ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleUserAlreadyExistsException(CrunchException e) {
        return e.getMessage();
    }

}
