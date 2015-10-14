package crunch;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SalaryController {
	@Autowired SalaryRepository repository;
	@Autowired HttpServletRequest request;
	//http://localhost:8080/calculate?email=test&taxyear=12&gross=12
	
    @RequestMapping("/calculate")
    @ExceptionHandler(Exception.class)
    public Salary salary(@RequestParam String email, 
							 @RequestParam String taxyear,
							 @RequestParam String gross   ) {
    	
    Salary s = new Salary(email, Long.parseLong(taxyear), Long.parseLong(gross), 0, request.getRemoteAddr());
    repository.save(s);

	return s;
	
    }
	
}
