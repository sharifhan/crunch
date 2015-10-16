package crunch.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import crunch.controller.UserController;
import crunch.domain.User;
import crunch.service.UserService;
import crunch.util.UserUtil;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    private UserController userController;
    private InputValidator inputValidator;

    @Before
    public void setUp() throws Exception {
        userController = new UserController(userService);
        inputValidator = new InputValidator();
    }
        
    
    @Test
    public void IsValidEmailAddress() throws Exception {    	    	
    	assertEquals("Validating sharif@gmail.com", true, inputValidator.validateEamil("sharif@gmail.com"));
    	assertEquals("Validating sharif.hanif@gmail.com", true, inputValidator.validateEamil("sharif.hanif@gmail.com"));
    	assertEquals("Validating sharif@gmail.com", false, inputValidator.validateEamil("sharif@.gmail.com"));
    	assertEquals("Validating sharif@gmail.com", false, inputValidator.validateEamil("@crunch.com"));
    	assertEquals("Validating sharif@gmail.com", false, inputValidator.validateEamil("crunch@.com"));
    	assertEquals("Validating Empty email", false, inputValidator.validateEamil(""));
    }
    
    @Test
    public void IsValidTaxYear() throws Exception {
    	assertEquals("Validating 2014/15", true, inputValidator.validateTaxYear("2014/15"));
        assertEquals("Validating 2015/16", true, inputValidator.validateTaxYear("2015/16"));
        assertEquals("Validating 2016/17", false, inputValidator.validateTaxYear("2016/17"));
        assertEquals("Validating 2014-15", false, inputValidator.validateTaxYear("2014-15"));
        assertEquals("Validating 2014", false, inputValidator.validateTaxYear("2015"));
        assertEquals("Validating Empty taxyear", false, inputValidator.validateTaxYear(""));
    }
    
    @Test
    public void IsValidGrossAmount() throws Exception {
        
        assertEquals("Validating gross range", true, inputValidator.validateGrossRange("12"));
    }
    
    @Test
    public void shouldCalculateSalary() throws Exception {
        
        assertEquals("Returned user should come from the service", "", "");
    }

    @Test
    public void shouldReturnStoredUser() {
        final User user = UserUtil.createUser();
        when(userService.save(any(User.class))).thenReturn(user);        
    }
    

}
