package service.user;

import domain.user.User;

public interface UserService {

    User getUserByLogonName(String logonName);

    User createUser(User user);
}
