package crunch.service;

import java.util.List;

import crunch.domain.User;

public interface UserService {

    User save(User user);

    List<User> getList();

}
