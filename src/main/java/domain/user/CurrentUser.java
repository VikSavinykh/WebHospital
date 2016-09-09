package domain.user;

import java.util.Collections;

public class CurrentUser extends org.springframework.security.core.userdetails.User {

    private User user;

    public CurrentUser(User user) {

        super(user.getLogonName(), user.getPasswordHash(), true, true, true, true,
            Collections.emptyList());
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Long getId() {
        return user.getId();
    }

    /**
     * @return
     * @see User#getLogonName()
     */
    public String getLogonName() {
        return user.getLogonName();
    }

}
