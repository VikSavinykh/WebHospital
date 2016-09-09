package service.user;

import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.BasePasswordEncoder;

public interface PasswordEncoderService {

    BasePasswordEncoder getPasswordEncoder();

    SaltSource getSaltSource();
}
