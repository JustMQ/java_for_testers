import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CanCreateGroup {
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
    public void test() {
        driver.get("http://localhost/addressbook/");
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.findElement(By.name("user")).sendKeys("admin");
        driver.findElement(By.name("pass")).sendKeys("secret");
        driver.findElement(By.xpath("//input[@value=\'Login\']")).click();
        driver.findElement(By.linkText("groups")).click();
        driver.findElement(By.name("new")).click();
        driver.findElement(By.name("group_name")).click();
        driver.findElement(By.name("group_name")).sendKeys("Group Name");
        driver.findElement(By.name("group_header")).sendKeys("Group Header");
        driver.findElement(By.name("group_footer")).sendKeys("Group Footer");
        driver.findElement(By.name("submit")).click();
        driver.findElement(By.linkText("group page")).click();
        driver.findElement(By.linkText("Logout")).click();
    }
}