package ru.stqa.mantis.manager;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Properties;

public class ApplicationManager {

    private WebDriver driver;
    private String string;
    private Properties properties;
    private SessionHelper sessionHelper;
    private HttpSessionHelper httpSessionHelper;
    private JamesCliHelper jamesCliHelper;
    private MailHelper mailHelper;
    private BrowserHelper browserHelper;
    private JamesApiHelper jamesApiHelper;
    private UserHelper userHelper;
    private DeveloperMailHelper developerMailHelper;
    private RestApiHelper restApiHelper;

    public void init(String browser, Properties properties) {
        this.string = browser;
        this.properties = properties;
    }

    public WebDriver driver() {
       if (driver == null) {
           if ("firefox".equals(string)) {
               driver = new FirefoxDriver();
           } else
           if ("chrome".equals(string)) {
               driver = new ChromeDriver();
           } else {
               throw new IllegalArgumentException(String.format("Unknown browser %s", string));
           }
           Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
           driver.get(properties.getProperty("web.baseUrl"));
           driver.manage().window().setSize(new Dimension(1920, 1080));
       }
       return driver;
    }

    public SessionHelper session() {
        if(sessionHelper == null) {
            sessionHelper = new SessionHelper(this);
        }
        return sessionHelper;
    }

    public HttpSessionHelper http() {
        if(httpSessionHelper == null) {
            httpSessionHelper = new HttpSessionHelper(this);
        }
        return httpSessionHelper;
    }

    public JamesCliHelper jamesCli() {
        if(jamesCliHelper == null) {
            jamesCliHelper = new JamesCliHelper(this);
        }
        return jamesCliHelper;
    }

    public JamesApiHelper jamesApi() {
        if(jamesApiHelper == null) {
            jamesApiHelper = new JamesApiHelper(this);
        }
        return jamesApiHelper;
    }

    public MailHelper mail() {
        if(mailHelper == null) {
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }

    public BrowserHelper browserHelper() {
        if(browserHelper == null) {
            browserHelper = new BrowserHelper(this);
        }
        return browserHelper;
    }

    public UserHelper user() {
        if (userHelper == null) {
            userHelper = new UserHelper(this);
        }
        return userHelper;
    }

    public DeveloperMailHelper developerMail() {
        if (developerMailHelper == null) {
            developerMailHelper = new DeveloperMailHelper(this);
        }
        return developerMailHelper;
    }

    public RestApiHelper rest() {
        if (restApiHelper == null) {
            restApiHelper = new RestApiHelper(this);
        }
        return restApiHelper;
    }

    public String property(String name) {
        return properties.getProperty(name);
    }
}
