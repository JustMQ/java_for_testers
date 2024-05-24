package ru.stqa.mantis.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.model.DeveloperMailUser;

public class UserCreationTests extends TestBase{

//    public static Stream<String> randomUser() {
//        return Stream.of(CommonFunctions.randomString(8));
//    }

    DeveloperMailUser user;

    @Test
    void canCreateUser() {
        var password = "password";
        user = app.developerMail().addUser();
        var email = String.format("%s@developermail.com", user.name());

//        app.user().startCreation(user);
//
//        var messages = app.mail().receive(email, password, Duration.ofSeconds(10));
//        var url = CommonFunctions.extractUrl(messages.get(0).content());
//
//        app.user().finishCreation(url, password);
//
//        app.http().login(user, password);
//        Assertions.assertTrue(app.http().isLoggedIn());
    }

    @AfterEach
    void deleteMailUser() {
        app.developerMail().deleteUser(user);
    }
}
