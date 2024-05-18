package ru.stqa.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.stqa.mantis.common.CommonFunctions;
import ru.stqa.mantis.model.RegistrationFormData;

import java.time.Duration;
import java.util.regex.Pattern;

public class UserRegistrationTests extends TestBase {

    @Test
    void canRegisterUser() {
        var username = CommonFunctions.randomString(5);
        var email = String.format("%s@localhost", username);
        app.jamesCli().addUser(
                email,
                "password"); //создать пользователя (адрес) на почтовом сервере (JamesHelper)
        app.browserHelper().createAccount(new RegistrationFormData()
                .withUsername(username)
                .withEmail(email)); //заполняем форму создания и отправляем (браузер - создать класс помощник в котором будут совершаться действия для браузера)
        var messages = app.mail().receive(email, "password", Duration.ofSeconds(60)); //получаем (ждем) почту (MailHelper)
        var text = messages.get(0).content();
        var pattern = Pattern.compile("http://\\S*"); //извлекаем ссылку из письма
        var matcher = pattern.matcher(text);
        String url = "";
        if (matcher.find()) {
            url = text.substring(matcher.start(), matcher.end());
            System.out.println(url);
        }
        app.driver().get(url);
        app.browserHelper().editAccount(); //проходим по ссылке и завершаем регистрацию (браузер)
        app.http().login("administrator", "root"); //проверяем, что пользователь может залогиниться (HttpSessionHelper)
        Assertions.assertTrue(app.http().isLoggedIn());
    }
}

