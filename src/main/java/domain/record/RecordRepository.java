package domain.record;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.doctor.Doctor;
import domain.patient.Patient;

public interface RecordRepository extends JpaRepository<Record, Long> {

    @Query("SELECT r FROM #{#entityName} r WHERE r.doctor = ?1 AND r.startVisit > ?2 AND r.startVisit < ?3")
    List<Record> findAllByDoctorAndDay(Doctor doctor, Date day, Date nextDay);

    List<Record> findAllByPatient(Patient patient);

}
