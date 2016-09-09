package config.db;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.doctor.Doctor;
import domain.doctor.DoctorSpecialization;
import service.doctor.DoctorService;

@Component
public class DoctorInit {

    @Autowired
    private DoctorService doctorService;

    @Transactional
    @PostConstruct
    public void executeDoctorInit() {
        createDoctor("Беззубов Иван Иванович", DoctorSpecialization.DENTIST);
        createDoctor("Белоснежный Владимир Владимирович", DoctorSpecialization.DENTIST);
        createDoctor("Глазов Петр Петрович", DoctorSpecialization.OCULIST);
        createDoctor("Смирнов Ярослав Владимирович", DoctorSpecialization.OCULIST);
        createDoctor("Зоркая Екатерина Игоревна", DoctorSpecialization.OCULIST);
        createDoctor("Топоров Игорь Геннадьевич", DoctorSpecialization.SURGEON);
        createDoctor("Резаков Илья Сергеевич", DoctorSpecialization.SURGEON);
        createDoctor("Скальпелев Сергей Дмитриевич", DoctorSpecialization.SURGEON);
    }

    private void createDoctor(String fio, DoctorSpecialization specialization){
        Doctor doctor = new Doctor();
        doctor.setFio(fio);
        doctor.setSpecialization(specialization);
        doctorService.create(doctor);
    }
}
