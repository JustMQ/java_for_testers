package tests.Contacts;

import model.ContactData;
import org.junit.jupiter.api.Test;
import tests.TestBase;

public class ContactCreationTests extends TestBase {
    @Test
    public void canCreateContact() {
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
}
