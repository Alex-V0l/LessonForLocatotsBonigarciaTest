package DropdownMenu;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


public class DropdownMenuTest {

    WebDriver driver;
    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/dropdown-menu.html";

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @DisplayName("Проверка Left click dropdown menu")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void useLeftClickTest()  {
        WebElement leftClickDropDown = driver.findElement(By.id("my-dropdown-1"));
        leftClickDropDown.click();

        WebElement ActionOption = driver.findElement(By.xpath("//ul[@data-popper-placement='bottom-start']//a[@class='dropdown-item' and text()='Action']"));
        Actions actions = new Actions(driver);
        String valueOfActionOption = ActionOption.getDomProperty("text");
        Assertions.assertEquals("Action", valueOfActionOption, "Значения должны совпадать");
        actions.click(ActionOption).perform();

        leftClickDropDown.click();
        WebElement firstDropDownMenu = driver.findElement(By.xpath("//ul[@class='dropdown-menu']//a[@class='dropdown-item' and text()='Action']"));
        boolean isHidden = ! firstDropDownMenu.isDisplayed();

        Assertions.assertTrue(isHidden, "Меню должно быть скрыто");
    }

    @DisplayName("Проверка Right click dropdown Menu")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void useRightClickTest() {
        WebElement rightClickDropDown = driver.findElement(By.id("my-dropdown-2"));
        Actions actions = new Actions(driver);
        actions.contextClick(rightClickDropDown).perform();

        WebElement AnotherActionOption = driver.findElement(By.xpath("//ul[@id='context-menu-2']//a[@class='dropdown-item' and text()='Another action']"));

        String valueOfAnotherActionOption = AnotherActionOption.getDomProperty("text");
        Assertions.assertEquals("Another action", valueOfAnotherActionOption, "Значения должны совпадать");

        actions.click(AnotherActionOption).perform();
        WebElement rightClickDropDownMenu = driver.findElement(By.xpath("//ul[@id='context-menu-2']"));
        boolean isHidden = ! rightClickDropDownMenu.isDisplayed();
        Assertions.assertTrue(isHidden, "Меню должно быть скрыто");
    }

    @DisplayName("Проверка double click dropdown Menu")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void useDoubleClickTest() {
        WebElement DoubletClickDropDown = driver.findElement(By.id("my-dropdown-3"));
        Actions actions = new Actions(driver);
        actions.doubleClick(DoubletClickDropDown).perform();

        WebElement SomethingElseHereOption = driver.findElement(By.xpath("//ul[@id='context-menu-3']//a[@class='dropdown-item' and text()='Something else here']"));

        String valueOfSomethingElseHereOption = SomethingElseHereOption.getDomProperty("text");
        Assertions.assertEquals("Something else here", valueOfSomethingElseHereOption, "Значения должны совпадать");

        actions.click(SomethingElseHereOption).perform();
        WebElement doubleClickDropDownMenu = driver.findElement(By.xpath("//ul[@id='context-menu-3']"));
        boolean isHidden = ! doubleClickDropDownMenu.isDisplayed();
        Assertions.assertTrue(isHidden, "Меню должно быть скрыто");
    }
}



