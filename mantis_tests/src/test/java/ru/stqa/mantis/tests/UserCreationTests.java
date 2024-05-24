package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.stqa.mantis.common.CommonFunctions;

import java.time.Duration;
import java.util.stream.Stream;

public class UserCreationTests extends TestBase{

    public static Stream<String> randomUser() {
        return Stream.of(CommonFunctions.randomString(8));
    }

    @ParameterizedTest
    @MethodSource("randomUser")
    void canCreateUser(String user) {
        var email = String.format("%s@localhost" , user);
        var password = "password";
        app.jamesApi().addUser(email, password);

        app.user().startCreation(user);

        var messages = app.mail().receive(email, password, Duration.ofSeconds(10));
        var url = CommonFunctions.extractUrl(messages.get(0).content());

        app.user().finishCreation(url, password);

        app.http().login(user, password);
        Assertions.assertTrue(app.http().isLoggedIn());
    }
}
