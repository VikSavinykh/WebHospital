package domain.doctor;

public enum DoctorSpecialization {

    DENTIST("Стоматолог"),
    OCULIST("Окулист"),
    SURGEON("Хирург");

    DoctorSpecialization(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }
}
