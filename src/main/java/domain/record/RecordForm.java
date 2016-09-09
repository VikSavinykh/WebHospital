package domain.record;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import domain.doctor.Doctor;
import domain.patient.Patient;

/**
 * @author Vilgodskiy Sergey on 06.09.2016.
 * @since 0.3.0
 */
public class RecordForm {

    private Long id;

    private Doctor doctor;

    private Patient patient;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dayDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date visitDate;

    private boolean isBusy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Date getDayDate() {
        return dayDate;
    }

    public void setDayDate(Date dayDate) {
        this.dayDate = dayDate;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date startTime) {
        this.visitDate = startTime;
    }
}
