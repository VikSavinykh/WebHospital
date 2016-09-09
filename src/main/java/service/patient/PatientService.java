package service.patient;

import domain.patient.Patient;

public interface PatientService {

    Patient get(Long id);

    Patient create(Patient patient);
}
