package service.record;

import java.util.Date;
import java.util.List;

import domain.doctor.Doctor;
import domain.patient.Patient;
import domain.record.Record;
import domain.record.RecordForm;

public interface RecordService {

    Record get(Long id);

    List<Record> findAllByDoctorAndDay(Doctor doctor, Date day);

    List<Record> findAllByPatient(Patient patient);

    Record create(RecordForm recordForm);

    List<RecordForm> getAllRecordForms(Date day, List<Record> busyRecordForDoctorToday, Doctor doctor, Patient patient);
}
