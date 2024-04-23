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
        InitContactModification(contact);
        FillContactsForm(modifiedContact);
        UpdateContactCreation();
        ReturnToContactsPage();
    }

    private void UpdateContactCreation() {
        click(By.name("update"));
    }

    private void InitContactModification(ContactData contact) { click(By.xpath(String.format("//a[contains(@href,'edit.php?id=%s')]", contact.id()))); }

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
        var trs = manager.driver.findElements(By.cssSelector("tr[name=\"entry\"]"));
        for (var tr : trs) {
            var cells = tr.findElements(By.tagName("td"));
            var lastName = cells.get(1).getText();
            var firstName = cells.get(2).getText();
            var checkbox = tr.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName));
        }
        return contacts;
    }
}
