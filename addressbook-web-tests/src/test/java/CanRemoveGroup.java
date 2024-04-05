import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CanRemoveGroup {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new FirefoxDriver();
    }

    @AfterEach
    public void tearDown() {

        driver.quit();
    }

    @Test
    public void CanRemoveGroup() {
        driver.get("http://localhost/addressbook/");
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.findElement(By.name("user")).sendKeys("admin");
        driver.findElement(By.name("pass")).sendKeys("secret");
        driver.findElement(By.cssSelector("input:nth-child(7)")).click();
        driver.findElement(By.linkText("groups")).click();
        driver.findElement(By.name("selected[]")).click();
        driver.findElement(By.cssSelector("input:nth-child(8)")).click();
        driver.findElement(By.linkText("group page")).click();
        driver.findElement(By.linkText("Logout")).click();
    }
}
