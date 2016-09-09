package service.record;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.doctor.Doctor;
import domain.patient.Patient;
import domain.record.Record;
import domain.record.RecordForm;
import domain.record.RecordRepository;
import service.DateUtil;

@Service("recordService")
public class RecordServiceImpl implements RecordService {


    public final static int START_WORKDAY = 9;
    public final static int RECEPTION_PER_DAY = 8;


    @Autowired
    private RecordRepository recordRepository;

    @Override
    public Record get(Long id) {
        return recordRepository.getOne(id);
    }

    @Override
    public List<Record> findAllByDoctorAndDay(Doctor doctor, Date day) {

        Date nextDay = new DateTime(day).plusDays(1).toDate();

        return recordRepository.findAllByDoctorAndDay(doctor, day, nextDay);
    }

    @Override
    public List<Record> findAllByPatient(Patient patient) {
        return recordRepository.findAllByPatient(patient);
    }

    @Override
    public Record create(RecordForm recordForm) {
        return recordRepository.save(formToObject(recordForm));
    }

    private Record formToObject(RecordForm form){
        Record object = new Record();
        object.setDoctor(form.getDoctor());
        object.setPatient(form.getPatient());
        object.setStartVisit(form.getVisitDate());
        return object;
    }

    @Override
    public List<RecordForm> getAllRecordForms(Date day, List<Record> busyRecordForDoctorToday, Doctor doctor, Patient patient){

        List<RecordForm> allRecordForms = new ArrayList<>();
        boolean isToday = new DateTime().withTime(0,0,0,0).equals(new DateTime(day));
        DateTime dateTime = new DateTime(day).plusHours(START_WORKDAY);
        for(int i = 0; i <= RECEPTION_PER_DAY; i++){
            RecordForm recordForm = new RecordForm();
            recordForm.setDayDate(dateTime.toDate());
            recordForm.setPatient(patient);
            recordForm.setDoctor(doctor);
            if(isToday){
                if(dateTime.isAfter(new DateTime())){
                    allRecordForms.add(recordForm);
                }
            }else {
                allRecordForms.add(recordForm);
            }
            dateTime = dateTime.plusHours(1);
        }
        List<String> busyTimes = new ArrayList<>();
        for(Record record : busyRecordForDoctorToday){
            busyTimes.add(DateUtil.getTimeSimpleFormat(record.getStartVisit()));
        }
        for(RecordForm recordForm : allRecordForms){
            if(busyTimes.contains(DateUtil.getTimeSimpleFormat(recordForm.getDayDate()))){
                recordForm.setBusy(true);
            }
        }
        return allRecordForms;
    }
}
