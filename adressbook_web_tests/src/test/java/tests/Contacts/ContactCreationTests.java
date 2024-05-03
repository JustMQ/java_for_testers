package tests.Contacts;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunctions;
import model.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tests.TestBase;
import java.io.*;
import java.util.*;

public class ContactCreationTests extends TestBase {

    public static ArrayList<ContactData> contactProvider() throws IOException {
        var result = new ArrayList<ContactData>();
        for (var firstName : List.of("", "contact firstName")) {
                for (var lastName : List.of("", "contact lastName")) {
                        result.add(new ContactData()
                                .withFirstName(firstName)
                                .withLastName(lastName));
                                                                }
                                                            }

        ObjectMapper mapper = new ObjectMapper();
        var value = mapper.readValue(new File("contacts.json"), new TypeReference<List<ContactData>>() {});
        result.addAll(value);
        return result;
    }


    public static List<ContactData> singleRandomContact() {
        return List.of(new ContactData()
                .withFirstName(CommonFunctions.randomString(10))
                .withLastName(CommonFunctions.randomString(20))
                .withAddress(CommonFunctions.randomString(30)));
    }

    @ParameterizedTest
    @MethodSource("singleRandomContact")
    public void canCreateContact(ContactData contact) {
        var oldContacts = app.jdbc().getContactList();
        app.contacts().createContact(contact);
        var newContacts = app.jdbc().getContactList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        var maxId = newContacts.get(newContacts.size() -1).id();

        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(maxId));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    void canCreateContactWithPhoto() {
        var contact = new ContactData()
                .withFirstName(CommonFunctions.randomString(10))
                .withLastName(CommonFunctions.randomString(10))
                .withPhoto(randomFile("src/test/resources/images"));
        app.contacts().createContactWithPhoto(contact);
    }

    @Test
    void canCreateContactInGroup() {
        var contact = new ContactData()
                .withFirstName(CommonFunctions.randomString(10))
                .withLastName(CommonFunctions.randomString(10))
                .withPhoto(randomFile("src/test/resources/images"));
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData(
                    "",
                    properties.getProperty("web.groupname"),
                    properties.getProperty("web.groupheader"),
                    properties.getProperty("web.groupfooter")));
        }
        var group = app.hbm().getGroupList().get(0);

        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().createContactWithGroup(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());
    }

}
