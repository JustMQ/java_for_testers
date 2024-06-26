package manager;

import model.ContactData;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public void createContactWithGroup(ContactData contact, GroupData group) {

        InitContactCreation();
        FillContactsForm(contact);
        CreationContactSelectGroup(group);
        SubmitContactCreation();
        ReturnToHomePage();
    }

    public void InviteContactInGroup(ContactData contact, GroupData group) {
        ReturnToHomePage();
        SelectContact(contact);
        SelectGroupToInvite(group);
        AddToGroup();
        ReturnToContactsPage();
    }

    public void RemoveContactFromGroup(ContactData contact, GroupData group) {
        ReturnToHomePage();
        SelectGroupForCheckContacts(group);
        SelectContact(contact);
        RemoveFromGroup();
        ReturnToContactsPage();
    }



    public void createContactWithPhoto(ContactData contact) {

        InitContactCreation();
        FillContactsFormFull(contact);
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

    public void getContactEditPageInfo(ContactData contact) {
        ReturnToContactsPage();
        InitContactModification(contact);
        contact.withAddress(manager.driver.findElement(By.name("address")).getText())
                .withHome(manager.driver.findElement(By.name("home")).getText())
                .withMobile(manager.driver.findElement(By.name("mobile")).getText())
                .withWork(manager.driver.findElement(By.name("work")).getText())
                .withEmail(manager.driver.findElement(By.name("email")).getText())
                .withEmail2(manager.driver.findElement(By.name("email2")).getText())
                .withEmail3(manager.driver.findElement(By.name("email3")).getText());
        ReturnToHomePage();
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
        click(By.id("logo"));
    }

    private void AddToGroup() { click(By.name("add")); }

    private void RemoveFromGroup() { click(By.name("remove")); }

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
        type(By.name("address"), contact.address());
    }

    private void CreationContactSelectGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }

    private void SelectGroupToInvite(GroupData group) {
        new Select(manager.driver.findElement(By.name("to_group"))).selectByValue(group.id());
    }

    private void AddGroupToInvite(GroupData group) {
        new Select(manager.driver.findElement(By.name("to_group"))).selectByValue(group.id());
    }

    private void SelectGroupForCheckContacts(GroupData group) {
        new Select(manager.driver.findElement(By.name("group"))).selectByValue(group.id());
    }

    private void FillContactsFormFull(ContactData contact) {
        FillContactsForm(contact);
        attach(By.name("photo"), contact.photo());
    }

    private void attach(By locator, String file) {
        manager.driver.findElement(locator).sendKeys(Paths.get(file).toAbsolutePath().toString());
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
            var adress = cells.get(3).getText();
            var emails = cells.get(4).getText();
            var phones = cells.get(5).getText();
            var checkbox = tr.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName));
        }
        return contacts;
    }

    public String getAdress(ContactData contact) {
        return  manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[4]", contact.id()))).getText();
    }

    public String getEmails(ContactData contact) {
        return  manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[5]", contact.id()))).getText();
    }

    public String getPhones(ContactData contact) {
        return  manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[6]", contact.id()))).getText();
    }

    public Map<String, String> getPhones() {
       var result = new HashMap<String, String>();
       List<WebElement> rows = manager.driver.findElements(By.name("entry"));
       for (WebElement row : rows) {
           var id = row.findElement(By.tagName("input")).getAttribute("id");
           var phones = row.findElements(By.tagName("td")).get(5).getText();
           result.put(id, phones);
       }
       return result;
    }
}
