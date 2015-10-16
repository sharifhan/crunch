package crunch.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import crunch.domain.User;
import crunch.exception.UserAlreadyExistsException;
import crunch.repository.UserRepository;
import crunch.service.UserService;
import crunch.service.UserServiceImpl;
import crunch.util.UserUtil;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @Before
    public void setUp() throws Exception {
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    public void shouldSaveNewUser_GivenThereDoesNotExistOneWithTheSameId_ThenTheSavedUserShouldBeReturned() throws Exception {
        final User savedUser = stubRepositoryToReturnUserOnSave();
        final User user = UserUtil.createUser();
        final User returnedUser = userService.save(user);
        // verify repository was called with user
        verify(userRepository, times(1)).save(user);
        assertEquals("Returned user should come from the repository", savedUser, returnedUser);
    }

    public User stubRepositoryToReturnUserOnSave() {
        User user = UserUtil.createUser();
        when(userRepository.save(any(User.class))).thenReturn(user);
        return user;
    }

    @Test
    public void shouldSaveNewUser_GivenThereExistsOneWithTheSameId_ThenTheExceptionShouldBeThrown() throws Exception {
        stubRepositoryToReturnExistingUser();
        try {
            userService.save(UserUtil.createUser());
            fail("Expected exception");
        } catch (UserAlreadyExistsException ignored) {
        }
        verify(userRepository, never()).save(any(User.class));
    }


    public void stubRepositoryToReturnExistingUser() {
        final User user = UserUtil.createUser();
        when(userRepository.findOne(user.getId())).thenReturn(user);
    }

}
