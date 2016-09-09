package service.patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.patient.Patient;
import domain.patient.PatientRepository;

@Service("patientService")
public class PatientServiceImpl implements PatientService {

    @Autowired
    PatientRepository patientRepository;

    @Override
    public Patient get(Long id) {
        return patientRepository.getOne(id);
    }

    @Override
    public Patient create(Patient patient) {
        return patientRepository.save(patient);
    }
}
