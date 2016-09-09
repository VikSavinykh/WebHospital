package service.doctor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.doctor.Doctor;
import domain.doctor.DoctorRepository;
import domain.doctor.DoctorSpecialization;

@Service("doctorService")
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public Doctor get(Long id) {
        return doctorRepository.getOne(id);
    }

    @Override
    public List<Doctor> findAllBySpecialization(DoctorSpecialization specialization) {
        return doctorRepository.findAllBySpecialization(specialization);
    }

    @Override
    public Doctor create(Doctor doctor) {
        return doctorRepository.save(doctor);
    }
}
