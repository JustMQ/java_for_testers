package tests.Contacts;

import common.CommonFunctions;
import manager.hbm.ContactInGroupRecord;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class ContactModificationTests extends TestBase {

    @Test
    void canModifyContact() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData(
                    "",
                    properties.getProperty("web.firstname"),
                    properties.getProperty("web.middlename"),
                    properties.getProperty("web.lastname"),
                    "",
                    ""));
        }
        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        var testData = new ContactData().withFirstName("modified FirstName");
        app.contacts().modifyContact(oldContacts.get(index), testData);
        var newContacts = app.hbm().getContactList();
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.set(index, testData.withId(oldContacts.get(index).id()));
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContacts.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newContacts, expectedList);
    }

    @Test
    void canInviteContactInGroup() {
        if (app.hbm().getGroupCount() == 0) {
            app.groups().createGroup(new GroupData(
                    "",
                    properties.getProperty("web.groupname"),
                    properties.getProperty("web.groupheader"),
                    properties.getProperty("web.groupfooter")));
        }
        if (app.hbm().getContactCount() == 0) {
            app.contacts().createContact(new ContactData(
                    "",
                    properties.getProperty("web.firstname"),
                    properties.getProperty("web.middlename"),
                    properties.getProperty("web.lastname"),
                    "",
                    ""));
        }
        var oldContacts = app.contacts().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());
        var contact = oldContacts.get(index);
        var grouplist = app.hbm().getGroupList();
        var group = grouplist.get(rnd.nextInt(grouplist.size()));
        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contacts().InviteContactInGroup(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());


    }

    @Test
    void canRemoveContactFromGroup() {
        var rnd = new Random(); //randomizer
        ContactInGroupRecord contactWithGroup = new ContactInGroupRecord(); //empty table object in address_in_groups
        GroupData group; //empty Group Object
        ContactData contact; //empty Contact Object
        List<ContactInGroupRecord> contactsInGroup = app.hbm().getAddressInGroupsList(); // get list from db address_in_groups
        if (contactsInGroup.isEmpty()) {  // check list is empty or not
            if (app.hbm().getGroupCount() == 0) { // check group count
                app.groups().createGroup(new GroupData(
                        "",
                        properties.getProperty("web.groupname"),
                        properties.getProperty("web.groupheader"),
                        properties.getProperty("web.groupfooter")));
            }
            var grouplist = app.hbm().getGroupList(); //get all groups list
            group = grouplist.get(rnd.nextInt(grouplist.size())); //get random group data
            if (app.hbm().getContactCount() == 0) { // check contacts count
                app.contacts().createContactWithGroup(new ContactData()
                        .withFirstName(CommonFunctions.randomString(10))
                        .withLastName(CommonFunctions.randomString(10))
                        .withPhoto(randomFile("src/test/resources/images")), group);
                contact = app.hbm().getContactList().get(0); //get created contact data
            } else {
                var contactlist = app.hbm().getContactList(); //get contact list from db
                contact = contactlist.get(rnd.nextInt(contactlist.size())); //get random contact from db
                app.contacts().InviteContactInGroup(contact, group); // Invite Contact In Group
            }
        } else { //find exist object in address_in_groups and setup empty object from line 80
            contactWithGroup = contactsInGroup.get(rnd.nextInt(contactsInGroup.size()));
            group = new GroupData().withId(String.valueOf(contactWithGroup.group_id));
            contact = new ContactData().withId(String.valueOf(contactWithGroup.id));
        }
        app.contacts().RemoveContactFromGroup(contact, group); //remove contact from group
        Assertions.assertFalse(app.hbm().getAddressInGroupsList().contains(contactWithGroup)); //check - contact id was removed from group id
    }
}
