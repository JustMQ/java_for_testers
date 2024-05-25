package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.model.DeveloperMailUser;
import ru.stqa.mantis.model.RegistrationFormData;

import java.time.Duration;

public class UserCreationTests extends TestBase {
    DeveloperMailUser user;
    @Test
    void canCreateUser() {
        var password = "password";
        user = app.developerMail().addUser();
        var email = String.format("%s@developermail.com", user.name());
        app.user().startCreation(user.name(), email);
        var message = app.developerMail().receive(user, Duration.ofSeconds(20));
        var url = CommonFunctions.extractUrl(message);
        app.user().finishCreation(url, password);
        app.http().login(user.name(), password);
        Assertions.assertTrue(app.http().isLoggedIn());
    }

    @Test
    void canCreateUserWithApi() {
        var userNameRandomizer = CommonFunctions.randomString(8);
        app.jamesApi().addUser(
                String.format("%s@localhost", userNameRandomizer),
                "password");
        app.rest().createNewUser(
                new RegistrationFormData()
                        .withUsername(userNameRandomizer)
                        .withEmail(String.format("%s@localhost", userNameRandomizer))
        );
        var message = app.mail().receive(String.format("%s@localhost", userNameRandomizer), "password", Duration.ofSeconds(60));
        var url = CommonFunctions.extractUrl(message.get(0).content());
        app.user().finishCreation(url, "password");
        app.http().login(userNameRandomizer, "password");
        Assertions.assertTrue(app.http().isLoggedIn());

    }

//    @AfterEach
//    void deleteMailUser() {
//        app.developerMail().deleteUser(user);
//    }
}
