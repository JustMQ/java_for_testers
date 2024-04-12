package tests.Contacts;

import model.ContactData;
import org.junit.jupiter.api.Test;
import tests.TestBase;

public class ContactRemovalTests extends TestBase {

    @Test
    public void canRemoveContact() {
        if (!app.contacts().isContactPresent()) {
            app.contacts().createContact(new ContactData(
                    "Ivan",
                    "Ivanovich",
                    "Ivanov",
                    "BEAR",
                    "Example title",
                    "Some company",
                    "Moscow, Koroleva 11",
                    "+79998887766",
                    "+79998887765",
                    "+79998887764",
                    "+79998887763",
                    "Bear@iv.an",
                    "Ivan@be.ar",
                    "some@com.pany",
                    "somecompany.com"));
        }
        app.contacts().removeContact();
    }
}
