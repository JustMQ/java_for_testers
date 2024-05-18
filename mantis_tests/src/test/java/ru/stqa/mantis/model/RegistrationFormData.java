package ru.stqa.mantis.model;

public record RegistrationFormData(String username, String email) {

    public RegistrationFormData() {
        this("", "");
    }

    public RegistrationFormData withUsername(String username) {
        return new RegistrationFormData(username, this.email);
    }
    public RegistrationFormData withEmail(String email) {
        return new RegistrationFormData(this.username, email);
    }
}
