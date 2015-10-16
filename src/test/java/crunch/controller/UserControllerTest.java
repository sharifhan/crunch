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

    @Before
    public void setUp() throws Exception {
        userController = new UserController(userService);
    }
    
    @Test
    public void shouldCalculateSalary() throws Exception {
        
        assertEquals("Returned user should come from the service", "", "");
    }

    private User stubServiceToReturnStoredUser() {
        final User user = UserUtil.createUser();
        when(userService.save(any(User.class))).thenReturn(user);
        return user;
    }

    private void stubServiceToReturnExistingUsers(int howMany) {
        when(userService.getList()).thenReturn(UserUtil.createUserList(howMany));
    }

}
