package tests.Contacts;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {

    @Test
    void TestPhones() {
        var contacts = app.hbm().getContactList();
        var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
            Stream.of(contact.home(), contact.mobile(), contact.work(), contact.secondary())
                    .filter(s -> s != null && !"".equals(s))
                    .collect(Collectors.joining("\n"))
        ));
        var phones = app.contacts().getPhones();
        Assertions.assertEquals(expected, phones);
    }

    @Test
    void CanCheckMatchingInfo() {
        var satisfied = app.hbm().getContactList().stream().filter(contactData -> !contactData.address().isEmpty() &&
                !(contactData.home().isEmpty() || contactData.mobile().isEmpty() || contactData.work().isEmpty() || contactData.secondary().isEmpty())  &&
                !(contactData.email().isEmpty() || contactData.email2().isEmpty() || contactData.email3().isEmpty())).toList(); //check Contact data from db
        if (app.hbm().getContactCount() == 0  || satisfied.size() == 0) {
            app.hbm().createContact(new ContactData(
                    "",
                    properties.getProperty("web.firstname"),
                    properties.getProperty("web.middlename"),
                    properties.getProperty("web.lastname"),
                    properties.getProperty("web.address"),
                    "",
                    properties.getProperty("web.home"),
                    properties.getProperty("web.mobile"),
                    properties.getProperty("web.work"),
                    "",
                    properties.getProperty("web.email"),
                    properties.getProperty("web.email2"),
                    properties.getProperty("web.email3")));
            satisfied = app.hbm().getContactList(); //update contactList after creating
        }
        var contact = satisfied.get(0); //get 1st contact
        app.contacts().getContactEditPageInfo(contact); //update contact with Data from Edit Page

        var adresses = app.contacts().getAdress(contact); //get adress as string from contact page
        var expectedAdress = contact.address(); //get adress from Edit Page
        Assertions.assertEquals(expectedAdress, adresses); //compare

        var phones = app.contacts().getPhones(contact);
        var expectedPhones = Stream.of(contact.home(), contact.mobile(), contact.work())
                .filter(s -> s !=null && ! "".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expectedPhones, phones);

        var emails = app.contacts().getEmails(contact);
        var expectedEmails = Stream.of(contact.email(), contact.email2(), contact.email3())
                .filter(s -> s !=null && ! "".equals(s))
                .collect(Collectors.joining("\n"));
        Assertions.assertEquals(expectedEmails, emails);
    }
}

