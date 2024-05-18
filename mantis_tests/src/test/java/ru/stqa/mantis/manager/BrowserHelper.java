package ru.stqa.mantis.manager;

import org.openqa.selenium.By;
import ru.stqa.mantis.model.RegistrationFormData;

public class BrowserHelper extends HelperBase {

    public BrowserHelper(ApplicationManager manager) {
        super(manager);
    }

    public void createAccount(RegistrationFormData form) {

        OpenRegistrationForm();
        FillRegistrationForm(form);
        SubmitAccountCreation();
        ReturnToLoginForm();
    }

    public void editAccount() {
       FillEditForm();
       UpdateUser();
    }

    private void FillEditForm() {
        type(By.name("realname"), "RealName");
        type(By.name("password"), "password");
        type(By.name("password_confirm"), "password");
    }

    private void UpdateUser() {
        click(By.cssSelector("button[type=\"submit\"]"));
    }

    private void ReturnToLoginForm() {
        click(By.linkText("Proceed"));
    }

    private void SubmitAccountCreation() {
        click(By.cssSelector("input[type=\"submit\"]"));
    }

    private void FillRegistrationForm(RegistrationFormData form) {
        type(By.name("username"), form.username());
        type(By.name("email"), form.email());
    }

    private void OpenRegistrationForm() {
        click(By.linkText("Signup for a new account"));
    }

}
