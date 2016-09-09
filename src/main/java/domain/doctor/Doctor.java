package domain.doctor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // Класс представляет объект, который нужно долговременно хранить.
@Table(name = "Doctors") // Поля класса будем хранить в таблице Doctors.
public class Doctor {

    @Id // Уникальное поле
    @GeneratedValue // Генерится автоматом
    private Long id;

    @Column(name = "fio") // Поле будем хранить в столбце fio.
    private String fio;

    @Enumerated(value = EnumType.STRING)
    @Column
    private DoctorSpecialization specialization;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public DoctorSpecialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(DoctorSpecialization specialization) {
        this.specialization = specialization;
    }
}
