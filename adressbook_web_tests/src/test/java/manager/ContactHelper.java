package manager;

import model.ContactData;
import org.openqa.selenium.By;

public class ContactHelper extends HelperBase{

    public ContactHelper(ApplicationManager manager) {

        super(manager);
    }

    public void createContact(ContactData contact) {
        
        InitContactCreation();
        FillContactsForm(contact);
        SubmitContactCreation();
        ReturnToHomePage();
    }
    public void removeContact() {
        ReturnToHomePage();
        SelectContact();
        RemoveSelectedContact();
        ReturnToContactsPage();
    }

    private void ReturnToContactsPage() {
        manager.driver.switchTo().alert().accept();
    }

    private void RemoveSelectedContact() {
        click(By.xpath("//input[@value=\"Delete\"]"));
    }

    private void SelectContact() {
        click(By.name("selected[]"));
    }

    private void ReturnToHomePage() {
        click(By.linkText("home"));
    }

    public boolean isContactPresent() {
        ReturnToHomePage();
        return manager.isElementPresent(By.name("selected[]"));
    }
    private void SubmitContactCreation() {
        click(By.name("submit"));
    }

    private void FillContactsForm(ContactData contact) {
        type(By.name("firstname"), contact.firstName());
        type(By.name("middlename"), contact.middleName());
        type(By.name("lastname"), contact.lastName());
        type(By.name("nickname"), contact.nickname());
        type(By.name("title"), contact.title());
        type(By.name("company"), contact.company());
        type(By.name("address"), contact.address());
        type(By.name("home"), contact.telephoneHome());
        type(By.name("mobile"), contact.telephoneMobile());
        type(By.name("work"), contact.telephoneWork());
        type(By.name("fax"), contact.telephoneFax());
        type(By.name("email"), contact.email());
        type(By.name("email2"), contact.email2());
        type(By.name("email3"), contact.email3());
        type(By.name("homepage"), contact.homepage());

    }

    private void InitContactCreation() {
        click(By.linkText("add new"));
    }

    public int getCount() {
        ReturnToHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }
}
