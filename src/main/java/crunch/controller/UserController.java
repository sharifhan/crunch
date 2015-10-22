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
    
    /*
     * 
     * A REST endpoint is created that accepts an email, a tax year and a gross salary.
     * 
     * http://localhost:8080/calculate?email=test@email.com&taxyear=2014/15&gross=120000
     * 
     * **/
    
    @RequestMapping("/calculate")
    public String calculateSalary(@RequestParam String email, 
							 @RequestParam String taxyear,
							 @RequestParam String gross   ) {
    	
    
    	
    	LOGGER.debug("Calculate net salary for " + request.getRemoteAddr());
    	
    	// Checking if all the given inputs are valid. An appropriate error response is returned if any validation fails.
    	if(!validate(email, taxyear, gross)){
    		throw new CrunchException(String.format("Invalid input: check email, taxyear and gross amount are valid and not missing."));
    	}
    	
    	// The service then calculates the users tax and take home pay for the given tax year.
    	User user = new User(email, taxyear, gross, inputValidator.calculateNetAmount(taxyear, gross), request.getRemoteAddr());
    	
    	// All requests are saved in a database along with the users IP.
    	userService.save(user);
    	
    	// The service then returns the users tax and take home pay for the given tax year.
    	return "For earning £" + user.getGross() + " during the tax year " + user.getTaxyear() + " you'll pay £" + (Long.parseLong(user.getGross()) - Long.parseLong(user.getNet())) + " tax. And you'r take home amount is £" + user.getNet();
    	    
    }

    private boolean validate(String email, String taxyear, String gross) {
    	
    	/*
    	 * 	Checking if all the given inputs are valid.
    	 **/
    	
    	return (inputValidator.validateEamil(email)&&inputValidator.validateGrossRange(gross)&&inputValidator.validateTaxYear(taxyear)) ? true : false;
		
	}


	@ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleUserAlreadyExistsException(CrunchException e) {
        return e.getMessage();
    }

}
