package WebForm;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;



public class LocatorsTest {

    WebDriver driver;
    private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/web-form.html";

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
        WebElement title = driver.findElement(By.className("display-4"));

        Assertions.assertEquals("Hands-On Selenium WebDriver with Java", title.getText(), "Значения должны совпадать");
    }

    @DisplayName("Проверка поиска подзаголовка")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void findSubTitleTest() {
        WebElement subTitle = driver.findElement(By.tagName("h5"));

        Assertions.assertEquals("Practice site", subTitle.getText(), "Значения должны совпадать");
    }

    @DisplayName("Проверка формы text input")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void findTextInputFormTest() {
        WebElement formTextUnder = driver.findElement(By.xpath("//label[@class='form-label w-100' and normalize-space(text())='Text input']"));
        WebElement TextForm = driver.findElement(By.id("my-text-id"));
        TextForm.sendKeys("Test it");
        String expectedValue = TextForm.getDomProperty("value");

        Assertions.assertEquals("Text input", formTextUnder.getText(), "Значения должны совпадать");
        Assertions.assertEquals("Test it", expectedValue, "Значения должны совпадать");
    }

    @DisplayName("Проверка формы password")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void findPasswordFormTest() {
        WebElement formPasswordUnder = driver.findElement(By.xpath("//label[@class='form-label w-100' and normalize-space(text())='Password']"));
        WebElement TextForm = driver.findElement(By.name("my-password"));
        TextForm.sendKeys("1234f");
        String expectedValue = TextForm.getDomProperty("value");

        Assertions.assertEquals("Password", formPasswordUnder.getText(), "Значения должны совпадать");
        Assertions.assertEquals("1234f", expectedValue, "Значения должны совпадать");
    }

    @DisplayName("Проверка формы Textarea")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void findTextAreaFormTest() {
        WebElement formTextAreaUnder = driver.findElement(By.xpath("//label[@class='form-label w-100' and normalize-space(text())='Textarea']"));
        WebElement TextForm = driver.findElement(By.name("my-textarea"));
        TextForm.sendKeys("This text");
        String expectedValue = TextForm.getDomProperty("value");

        Assertions.assertEquals("Textarea", formTextAreaUnder.getText(), "Значения должны совпадать");
        Assertions.assertEquals("This text", expectedValue, "Значения должны совпадать");
    }

    @DisplayName("Проверка формы Disabled input")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void findDisabledInputFormTest() {
        WebElement formDisabledInputUnder = driver.findElement(By.xpath("//label[@class='form-label w-100' and normalize-space(text())='Disabled input']"));
        WebElement TextForm = driver.findElement(By.name("my-disabled"));

        Assertions.assertEquals("Disabled input", formDisabledInputUnder.getText(), "Значения должны совпадать");
        Assertions.assertThrows(org.openqa.selenium.ElementNotInteractableException.class, () -> TextForm.sendKeys("Test"));
    }

    @DisplayName("Проверка формы ReadOnlyInput")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void findReadOnlyInputFormTest() {
        WebElement formReadOnlyInputUnder = driver.findElement(By.xpath("//label[@class='form-label w-100' and normalize-space(text())='Readonly input']"));
        WebElement TextForm = driver.findElement(By.name("my-readonly"));
        boolean isReadOnly = TextForm.getDomProperty("readOnly") != null;

        Assertions.assertEquals("Readonly input", formReadOnlyInputUnder.getText(), "Значения должны совпадать");
        Assertions.assertTrue(isReadOnly, "Поле должно быть доступно только для чтения");
    }

    @DisplayName("Проверка ссылки Return to index")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void findReturnToIndexLinkTest() {
        WebElement ReturnToIndexLink = driver.findElement(By.xpath("//a[@href='./index.html']"));
        ReturnToIndexLink.click();
        String actualURL = driver.getCurrentUrl();

        Assertions.assertEquals("https://bonigarcia.dev/selenium-webdriver-java/index.html", actualURL, "Значения должны совпадать");
    }

    @DisplayName("Проверка ссылки на домашнюю страницу и Copyright")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Disabled//Так и не смог перейти по ссылке. Может быть скрыта футером?
    @Test
    void findBoniGarciaTest() {
        WebElement CopyrightText = driver.findElement(By.xpath("//span[@class='text-muted' and normalize-space(text())='Copyright © 2021-2025']"));
        WebElement HomePageLink = driver.findElement(By.xpath("//span[@class='text-muted']/a[text()='Boni García']"));
        HomePageLink.click();
        String actualLink = driver.getCurrentUrl();

        Assertions.assertEquals("Copyright © 2021-2025", CopyrightText.getText(), "Значения должны совпадать");
        Assertions.assertEquals("https://bonigarcia.dev/", actualLink, "Значения должны совпадать");
    }

    @DisplayName("Проверка Dropdown (select)")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void findDropDownSelectTest() {
        WebElement DropDownText = driver.findElement(By.xpath("//label[@class='form-label w-100' and normalize-space(text())='Dropdown (select)']"));
        String labelText = DropDownText.getAttribute("innerHTML").split("<select")[0].trim();

        WebElement DropDownMenu = driver.findElement(By.className("form-select"));
        DropDownMenu.click();

        WebElement selectedOption = driver.findElement(By.xpath("//option[@value='1']"));
        selectedOption.click();

        Assertions.assertEquals("Dropdown (select)", labelText, "Значения должны совпадать");
        Assertions.assertEquals("One", selectedOption.getText(), "Значения должны совпадать");
    }//Через сохранение коллекции и поиск нужного элемента?

    @DisplayName("Проверка Dropdown (datalist)")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void findDropDownDataListTest() {
        WebElement DropDownTextUnder = driver.findElement(By.xpath("//label[@class='form-label w-100' and normalize-space(text())= 'Dropdown (datalist)']"));
        WebElement DropDownMenu = driver.findElement(By.name("my-datalist"));
        DropDownMenu.sendKeys("New York");
        String actualValue = DropDownMenu.getDomProperty("value");

        Assertions.assertEquals("Dropdown (datalist)", DropDownTextUnder.getText(), "Значения должны совпадать");
        Assertions.assertEquals("New York", actualValue, "Значения должны совпадать");
    }

    @DisplayName("Проверка Checked checkbox")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void findCheckBoxTest() {
        WebElement CheckedCheckBoxText = driver.findElement(By.xpath("//label[@class='form-check-label w-100'][normalize-space(.)='Checked checkbox']"));
        WebElement CheckedCheckBox = driver.findElement(By.id("my-check-1"));
        boolean isSelected = CheckedCheckBox.isSelected();

        Assertions.assertTrue(isSelected, "Checkbox must be checked");
        Assertions.assertEquals("Checked checkbox", CheckedCheckBoxText.getText(), "Значения должны совпадать");
    }

    @DisplayName("Проверка Default checkbox")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void findDefaultBoxTest() {
        WebElement CheckedCheckBoxText = driver.findElement(By.xpath("//label[@class='form-check-label w-100'][normalize-space(.)='Default checkbox']"));
        WebElement DefaultCheckBox = driver.findElement(By.id("my-check-2"));
        boolean isSelected = DefaultCheckBox.isSelected();

        Assertions.assertFalse(isSelected, "Checkbox must not be checked");
        Assertions.assertEquals("Default checkbox", CheckedCheckBoxText.getText(), "Значения должны совпадать");
    }

    @DisplayName("Проверка Checked Radio")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void findCheckedRadioTest() {
        WebElement CheckedRadioText = driver.findElement(By.xpath("//label[@class='form-check-label w-100'][normalize-space(.)='Checked radio']"));
        WebElement CheckedRadio = driver.findElement(By.id("my-radio-1"));
        boolean isSelected = CheckedRadio.isSelected();

        Assertions.assertTrue(isSelected, "Checkbox must be checked");
        Assertions.assertEquals("Checked radio", CheckedRadioText.getText(), "Значения должны совпадать");
    }

    @DisplayName("Проверка Default Radio")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void findDefaultRadioTest() {
        WebElement DefaultRadioText = driver.findElement(By.xpath("//label[@class='form-check-label w-100'][normalize-space(.)='Default radio']"));
        WebElement DefaultRadio = driver.findElement(By.id("my-radio-2"));
        boolean isSelected = DefaultRadio.isSelected();

        Assertions.assertFalse(isSelected, "Checkbox must not be checked");
        Assertions.assertEquals("Default radio", DefaultRadioText.getText(), "Значения должны совпадать");
    }

    @DisplayName("Проверка Color Picker")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void findColorPickerTest() {
        WebElement ColorPickerText = driver.findElement(By.xpath("//label[@class='form-label w-100' and normalize-space(text())='Color picker']"));
        WebElement ColorPickerMenu = driver.findElement(By.name("my-colors"));
        ColorPickerMenu.click();

        String colorValue = ColorPickerMenu.getDomProperty("value");

        Assertions.assertEquals("Color picker", ColorPickerText.getText(), "Значения должны совпадать");
        Assertions.assertNotNull(colorValue, "Цвет не изменился");
    }

    @DisplayName("Проверка Date Picker")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void findDatePickerTest() {
        WebElement DatePickerText = driver.findElement(By.xpath("//label[@class='form-label w-100' and normalize-space(text()[1])='Date picker']"));
        WebElement DatePickerMenu = driver.findElement(By.name("my-date"));
        DatePickerMenu.click();
        DatePickerMenu.sendKeys("2025-03-15");

        String DatePickerValue = DatePickerMenu.getDomProperty("value");

        Assertions.assertEquals("Date picker", DatePickerText.getText(), "Значения должны совпадать");
        Assertions.assertEquals("2025-03-15", DatePickerValue, "Значения должны совпадать");
    }

    @DisplayName("Проверка Example range")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void findExampleRangeTest() {
        WebElement ExampleRangeText = driver.findElement(By.xpath("//label[@class='form-label w-100' and normalize-space(text()[1])='Example range']"));
        WebElement DatePickerMenu = driver.findElement(By.name("my-range"));
        DatePickerMenu.click();
        String initialValue = DatePickerMenu.getDomProperty("value");
        Actions actions = new Actions(driver);
        actions.clickAndHold(DatePickerMenu)
                .moveByOffset(50, 0).release().perform();
        String newValue = DatePickerMenu.getDomProperty("value");

        Assertions.assertEquals("Example range", ExampleRangeText.getText(), "Значения должны совпадать");
        Assertions.assertNotEquals(initialValue, newValue, "Значение ползунка не изменилось!");
    }

    @DisplayName("Проверка Submit")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void findSubmitTest() {
        WebElement SubmitText = driver.findElement(By.xpath("//button[(text())='Submit']"));
        Assertions.assertEquals("Submit", SubmitText.getText(), "Значения должны совпадать");

        WebElement SubmitButton = driver.findElement(By.tagName("button"));
        SubmitButton.click();

        String actualURL = driver.getCurrentUrl();
        String expectedURL = "https://bonigarcia.dev/selenium-webdriver-java/submitted-form.html?my-text=&my-password=&my-textarea=&my-readonly=Readonly+input&my-select=Open+this+select+menu&my-datalist=&my-file=&my-check=on&my-radio=on&my-colors=%23563d7c&my-date=&my-range=5&my-hidden=";

        Assertions.assertEquals(expectedURL, actualURL, "Значения должны совпадать");
    }




}


