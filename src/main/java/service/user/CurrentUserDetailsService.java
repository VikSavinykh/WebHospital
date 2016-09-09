package service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import domain.user.CurrentUser;
import domain.user.User;

@Service
public class CurrentUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public CurrentUser loadUserByUsername(String logonName)
        throws UsernameNotFoundException {
        User user = userService.getUserByLogonName(logonName);
        if (user == null) {
            throw new UsernameNotFoundException(
                "Пользователь с логином='" + logonName + "' не был найден");
        }
        return new CurrentUser(user);
    }
}