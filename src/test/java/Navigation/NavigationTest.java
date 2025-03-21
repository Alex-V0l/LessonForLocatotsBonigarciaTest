package Navigation;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class NavigationTest {
    WebDriver driver;
    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/navigation1.html";

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

    @DisplayName("Проверка поиска заголовка всей страницы")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void findTitleTest() {
        WebElement title = driver.findElement(By.className("display-6"));

        Assertions.assertEquals("Navigation example", title.getText(), "Значения должны совпадать");
    }

    @DisplayName("проверка значений направляющих кнопок в исходном состоянии")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void basicStateValuesTest() {
        WebElement previousButton = driver.findElement(By.xpath("//li[@class='page-item disabled']/a"));
        WebElement parentLi = previousButton.findElement(By.xpath("./.."));
        boolean isDisabled = parentLi.getDomAttribute("class").contains("disabled");
        Assertions.assertTrue(isDisabled, "На кнопку нельзя нажать");

        WebElement firstText = driver.findElement(By.className("lead"));
        String firstPageText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
        Assertions.assertEquals(firstPageText, firstText.getText(), "Значения должны совпадать");

        WebElement nextButton = driver.findElement(By.xpath("//a[@class='page-link' and text()='Next']"));
        String actualValue = nextButton.getDomAttribute("href");
        Assertions.assertEquals("navigation2.html",actualValue, "Значения должны совпадать");
    }

    @DisplayName("проверка значений направляющих кнопок и переход на вторую страницу")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
     void   transitionToSecondPageTest() {
        WebElement TwoButton = driver.findElement(By.xpath("//a[@href='navigation2.html']"));
        TwoButton.click();
        String secondlURL = driver.getCurrentUrl();
        Assertions.assertEquals("https://bonigarcia.dev/selenium-webdriver-java/navigation2.html", secondlURL, "Значения должны совпадать");

        WebElement secondText = driver.findElement(By.className("lead"));
        String secondPageText = "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.";
        Assertions.assertEquals(secondPageText, secondText.getText(), "Значения должны совпадать");

        WebElement nextButton = driver.findElement(By.xpath("//a[@class='page-link' and text()='Next']"));
        String actualNextValue = nextButton.getDomAttribute("href");
        Assertions.assertEquals("navigation3.html", actualNextValue, "Значения должны совпадать");

        WebElement previousButton = driver.findElement(By.xpath("//a[@class='page-link' and text()='Previous']"));
        String actualPreviousValue = previousButton.getDomAttribute("href");
        Assertions.assertEquals("navigation1.html", actualPreviousValue, "Значения должны совпадать");
    }

    @DisplayName("проверка значений направляющих кнопок и переход на третью страницу")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
        void transitionToThirdTestPage(){
        WebElement ThreeButton = driver.findElement(By.xpath("//a[@href='navigation3.html']"));
        ThreeButton.click();
        String thirdURL = driver.getCurrentUrl();
        Assertions.assertEquals("https://bonigarcia.dev/selenium-webdriver-java/navigation3.html", thirdURL, "Значения должны совпадать");

        WebElement thirdText = driver.findElement(By.className("lead"));
        String thirdPageText = "Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
        Assertions.assertEquals(thirdPageText, thirdText.getText(), "Значения должны совпадать");

        WebElement NextButton = driver.findElement(By.xpath("//li[@class='page-item disabled']/a"));
        WebElement nextParentLi = NextButton.findElement(By.xpath("./.."));
        boolean isDisabled = nextParentLi.getDomAttribute("class").contains("disabled");
        Assertions.assertTrue(isDisabled, "На кнопку нельзя нажать");

        WebElement previousButton = driver.findElement(By.xpath("//a[@class='page-link' and text()='Previous']"));
        String actualPreviousValue = previousButton.getDomAttribute("href");
        Assertions.assertEquals("navigation2.html", actualPreviousValue, "Значения должны совпадать");
    }

    @DisplayName("проверка перехода с помощью направляющих кнопок")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void   transitionWithNextButtonTest() {
        WebElement nextButton = driver.findElement(By.xpath("//a[@class='page-link' and text()='Next']"));
        String actualNextValue = nextButton.getDomAttribute("href");
        Assertions.assertEquals("navigation2.html", actualNextValue, "Значения должны совпадать");

        nextButton.click();

        String secondlURL = driver.getCurrentUrl();
        Assertions.assertEquals("https://bonigarcia.dev/selenium-webdriver-java/navigation2.html", secondlURL, "Значения должны совпадать");

        WebElement previousButton = driver.findElement(By.xpath("//a[@class='page-link' and text()='Previous']"));
        String actualPreviousValue = previousButton.getDomAttribute("href");
        Assertions.assertEquals("navigation1.html", actualPreviousValue, "Значения должны совпадать");

        previousButton.click();
        String firstURL = driver.getCurrentUrl();
        Assertions.assertEquals("https://bonigarcia.dev/selenium-webdriver-java/navigation1.html", firstURL, "Значения должны совпадать");
    }
}


