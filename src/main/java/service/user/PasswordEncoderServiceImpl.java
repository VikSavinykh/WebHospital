package service.user;

import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.BasePasswordEncoder;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("passwordEncoderService")
public class PasswordEncoderServiceImpl implements PasswordEncoderService {

    private static final BasePasswordEncoder passwordEncoder = new Md5PasswordEncoder();

    @Override
    public BasePasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    @Override
    public SaltSource getSaltSource(){
        return (userDetails -> "Test-web-doctor");
    }
}
