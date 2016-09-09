package service.doctor;

import java.util.List;

import domain.doctor.Doctor;
import domain.doctor.DoctorSpecialization;

public interface DoctorService {

    Doctor get(Long id);

    List<Doctor> findAllBySpecialization(DoctorSpecialization specialization);

    Doctor create(Doctor doctor);
}
