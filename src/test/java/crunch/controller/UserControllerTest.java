package crunch.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import crunch.domain.User;
import crunch.service.UserService;
import crunch.util.UserUtil;



import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    private BusinessLogic businessLogic;

    @Before
    public void setUp() throws Exception {
        businessLogic = new BusinessLogic();
    }
        
    
    @Test
    public void IsValidEmailAddress() throws Exception {    	    	
    	assertEquals("Validating sharif@gmail.com", true, businessLogic.validateEamil("sharif@gmail.com"));
    	assertEquals("Validating sharif.hanif@gmail.com", true, businessLogic.validateEamil("sharif.hanif@gmail.com"));
    	assertEquals("Validating sharif@gmail.com", false, businessLogic.validateEamil("sharif@.gmail.com"));
    	assertEquals("Validating sharif@gmail.com", false, businessLogic.validateEamil("@crunch.com"));
    	assertEquals("Validating sharif@gmail.com", false, businessLogic.validateEamil("crunch@.com"));
    	assertEquals("Validating Empty email", false, businessLogic.validateEamil(""));
    }
    
    @Test
    public void IsValidTaxYear() throws Exception {
    	assertEquals("Validating 2014/15", true, businessLogic.validateTaxYear("2014/15"));
        assertEquals("Validating 2015/16", true, businessLogic.validateTaxYear("2015/16"));
        assertEquals("Validating 2016/17", false, businessLogic.validateTaxYear("2016/17"));
        assertEquals("Validating 2014-15", false, businessLogic.validateTaxYear("2014-15"));
        assertEquals("Validating 2014", false, businessLogic.validateTaxYear("2015"));
        assertEquals("Validating Empty taxyear", false, businessLogic.validateTaxYear(""));
    }
    
    @Test
    public void IsValidGrossAmount() throws Exception {
    	assertEquals("Validating gross range above limit", false, businessLogic.validateGrossRange("210000"));
    	assertEquals("Validating gross range lower limit", false, businessLogic.validateGrossRange("0"));    	
        assertEquals("Validating gross range withing limit", true, businessLogic.validateGrossRange("12"));
        assertEquals("Validating gross range empty field", false, businessLogic.validateGrossRange(""));
        assertEquals("Validating gross range above limit", false, businessLogic.validateGrossRange("210000"));
    }
    
    @Test
    public void shouldCalculateSalary() throws Exception {        
        assertEquals("Calculating the net amount", "5000", businessLogic.calculateNetAmount("2014/15", "5000"));
        assertEquals("Calculating the net amount", "18000", businessLogic.calculateNetAmount("2014/15", "20000"));
        assertEquals("Calculating the net amount", "35492", businessLogic.calculateNetAmount("2014/15", "41865"));
        assertEquals("Calculating the net amount", "36028", businessLogic.calculateNetAmount("2015/16", "42385"));
        assertEquals("Calculating the net amount", "58373", businessLogic.calculateNetAmount("2014/15", "80000"));
        assertEquals("Calculating the net amount", "82373", businessLogic.calculateNetAmount("2014/15", "120000"));
    }

    @Test
    public void shouldReturnStoredUser() {
        final User user = UserUtil.createUser();
        when(userService.save(any(User.class))).thenReturn(user);        
    }
    

}
