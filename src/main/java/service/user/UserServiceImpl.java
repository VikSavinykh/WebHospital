package service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.user.User;
import domain.user.UserRepository;
import service.patient.PatientService;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PatientService patientService;

    @Override
    public User getUserByLogonName(String logonName) {
        return userRepository.findOneByLogonName(logonName);
    }

    @Override
    public User createUser(User user) {
        user.setPatient(patientService.create(user.getPatient()));
        return userRepository.save(user);
    }
}
