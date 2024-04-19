package tests.Contacts;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tests.TestBase;

import java.util.ArrayList;
import java.util.List;

public class ContactCreationTests extends TestBase {

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();
        for (var firstName : List.of("", "contact firstName")) {
            for (var middleName : List.of("", "contact middleName")) {
                for (var lastName : List.of("", "contact lastName")) {
                    for (var nickname : List.of("", "contact nickname")) {
                        for (var title : List.of("", "contact title")) {
                            for (var company : List.of("", "contact company")) {
                                for (var address : List.of("", "contact address")) {
                                    for (var telephoneHome : List.of("", "contact telephoneHome")) {
                                        for (var telephoneMobile : List.of("", "contact telephoneMobile")) {
                                            for (var telephoneWork : List.of("", "contact telephoneWork")) {
                                                for (var telephoneFax : List.of("", "contact telephoneFax")) {
                                                    for (var email : List.of("", "contact email")) {
                                                        for (var email2 : List.of("", "contact email2")) {
                                                            for (var email3 : List.of("", "contact email3")) {
                                                                for (var homepage : List.of("", "contact homepage")) {
                        result.add(new ContactData(
                                firstName,
                                middleName,
                                lastName,
                                nickname,
                                title,
                                company,
                                address,
                                telephoneHome,
                                telephoneMobile,
                                telephoneWork,
                                telephoneFax,
                                email,
                                email2,
                                email3,
                                homepage));
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            }
        for(int i = 0; i < 5; i++) {
            result.add(new ContactData(
                    randomString(i * 10),
                    randomString(i * 10),
                    randomString(i * 10),
                    randomString(i * 10),
                    randomString(i * 10),
                    randomString(i * 10),
                    randomString(i * 10),
                    randomString(i * 10),
                    randomString(i * 10),
                    randomString(i * 10),
                    randomString(i * 10),
                    randomString(i * 10),
                    randomString(i * 10),
                    randomString(i * 10),
                    randomString(i * 10)));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateMultipleContacts(ContactData contact) {
        int contactCount = app.contacts().getCount();
        app.contacts().createContact(contact);
        int newContactCount = app.contacts().getCount();
        Assertions.assertEquals(contactCount + 1, newContactCount);
    }
}
