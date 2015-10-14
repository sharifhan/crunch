package crunch;


import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
public class CustomErrorController implements ErrorController {
   
    @RequestMapping("/error")
    ErrorJson error(HttpServletRequest request, HttpServletResponse response) {        
        return new ErrorJson(response.getStatus());
    }

	@Override
	public String getErrorPath() {
		return null;
	}

}
