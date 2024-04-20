package manager;

import model.ContactData;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

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
    public void removeContact(ContactData contact) {
        ReturnToHomePage();
        SelectContact(contact);
        RemoveSelectedContact();
        ReturnToContactsPage();
    }

    public void modifyContact(ContactData contact, ContactData modifiedContact) {
        ReturnToContactsPage();
        InitContactModification();
        FillContactsForm(modifiedContact);
        UpdateContactCreation();
        ReturnToContactsPage();
    }

    private void UpdateContactCreation() {
        click(By.name("update"));
    }

    private void InitContactModification() { click(By.xpath("//*[contains(@title, 'Edit')] ")); }

    private void ReturnToContactsPage() {
        ReturnToHomePage();
    }

    private void RemoveSelectedContact() {
        click(By.xpath("//input[@value=\"Delete\"]"));
    }

    private void SelectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[value='%s']", contact.id())));
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

    }

    private void InitContactCreation() {
        click(By.linkText("add new"));
    }

    public int getCount() {
        ReturnToHomePage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getList() {
        ReturnToHomePage();
        var contacts = new ArrayList<ContactData>();
        var tds = manager.driver.findElements(By.cssSelector("tr[name=\"entry\"]"));
        for (var td : tds) {
            var name = td.getText();
            var checkbox = td.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            contacts.add(new ContactData().withId(id).withFirstName(name));
        }
        return contacts;
    }
}
