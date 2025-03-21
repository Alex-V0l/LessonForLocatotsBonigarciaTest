package WebForm;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.net.URL;




public class WebFormTest {

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
        WebElement TextformText = driver.findElement(By.xpath("//label[@class='form-label w-100' and normalize-space(text())='Text input']"));
        WebElement TextForm = driver.findElement(By.id("my-text-id"));
        TextForm.sendKeys("Test it");
        String expectedValue = TextForm.getDomProperty("value");

        Assertions.assertEquals("Text input", TextformText.getText(), "Значения должны совпадать");
        Assertions.assertEquals("Test it", expectedValue, "Значения должны совпадать");
    }

    @DisplayName("Проверка формы password")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void findPasswordFormTest() {
        WebElement formPasswordText = driver.findElement(By.xpath("//label[@class='form-label w-100' and normalize-space(text())='Password']"));
        WebElement TextForm = driver.findElement(By.name("my-password"));
        TextForm.sendKeys("1234f");
        String expectedValue = TextForm.getDomProperty("value");

        Assertions.assertEquals("Password", formPasswordText.getText(), "Значения должны совпадать");
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
        WebElement DisabledInput = driver.findElement(By.name("my-disabled"));

        Assertions.assertFalse(DisabledInput.isEnabled());
        Assertions.assertEquals("Disabled input", formDisabledInputUnder.getText(), "Значения должны совпадать");
        Assertions.assertThrows(org.openqa.selenium.ElementNotInteractableException.class, () -> DisabledInput.sendKeys("Test"));
    }

    @DisplayName("Проверка формы ReadOnlyInput")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void findReadOnlyInputFormTest() {
        WebElement formReadOnlyInputUnder = driver.findElement(By.xpath("//label[@class='form-label w-100' and normalize-space(text())='Readonly input']"));
        WebElement ReadOnlyInput = driver.findElement(By.name("my-readonly"));
        boolean isReadOnly = ReadOnlyInput.getDomProperty("readOnly") != null;

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

    @DisplayName("Проверка ссылки на страницу разработчика и Copyright")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void findBoniGarciaTest() {
        WebElement CopyrightText = driver.findElement(By.xpath("//span[@class='text-muted' and normalize-space(text())='Copyright © 2021-2025']"));
        Assertions.assertEquals("Copyright © 2021-2025 Boni García", CopyrightText.getText(), "Значения должны совпадать");

        WebElement DevelopersPageLink = driver.findElement(By.xpath("//a[@href='https://bonigarcia.dev/']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", DevelopersPageLink);

        String actualLink = driver.getCurrentUrl();

        Assertions.assertEquals("https://bonigarcia.dev/", actualLink, "Значения должны совпадать");
    }

    @DisplayName("Проверка Dropdown (select)")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void findDropDownSelectTest() {
        WebElement DropDownText = driver.findElement(By.xpath("//label[@class='form-label w-100' and normalize-space(text())= 'Dropdown (select)']"));
        String labelText = DropDownText.getText().split("\n")[0].trim();

        WebElement DropDownMenu = driver.findElement(By.className("form-select"));
        Select select = new Select(DropDownMenu);
        select.selectByValue("2");


        Assertions.assertEquals("Dropdown (select)", labelText, "Значения должны совпадать");
        Assertions.assertEquals("Two", select.getFirstSelectedOption().getText(), "Значения должны совпадать");
        Assertions.assertTrue(select.getFirstSelectedOption().isSelected(), "Должно быть выбрано значение \"Two\"");
    }

    @DisplayName("Проверка Dropdown (datalist)")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void findDropDownDataListTest() {
        WebElement DataListText = driver.findElement(By.xpath("//label[@class='form-label w-100' and normalize-space(text())= 'Dropdown (datalist)']"));
        WebElement DataList = driver.findElement(By.name("my-datalist"));

        DataList.sendKeys("New York");
        String actualPick = DataList.getDomProperty("value");

        Assertions.assertEquals("Dropdown (datalist)", DataListText.getText(), "Значения должны совпадать");
        Assertions.assertEquals("New York", actualPick, "Значения должны совпадать");
    }

    @Disabled//сложная попытка добавить еще проверку с выбором из всплывающего после клика поля
    @Test
    void findDropDownDataListTry() {
        WebElement DataListText = driver.findElement(By.xpath("//label[@class='form-label w-100' and normalize-space(text())= 'Dropdown (datalist)']"));
        Assertions.assertEquals("Dropdown (datalist)", DataListText.getText(), "Значения должны совпадать");

        WebElement DataList = driver.findElement(By.name("my-datalist"));
        DataList.click();
        WebElement SeattleOption = driver.findElement(By.xpath("//option[@value='Seattle']"));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();", SeattleOption);
        String selectedOption = DataList.getDomProperty("value");
        Assertions.assertEquals("Seattle", selectedOption, "Значения должны совпадать");

        DataList.sendKeys("New York");
        String actualPick = DataList.getDomProperty("value");
        Assertions.assertEquals("New York", actualPick, "Значения должны совпадать");
    }

    @DisplayName("Проверка Checked checkbox")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void findCheckBoxTest() {
        WebElement CheckedCheckBoxText = driver.findElement(By.xpath("//label[@class='form-check-label w-100'][normalize-space(.)='Checked checkbox']"));
        Assertions.assertEquals("Checked checkbox", CheckedCheckBoxText.getText(), "Значения должны совпадать");

        WebElement CheckedCheckBox = driver.findElement(By.id("my-check-1"));
        boolean isSelected = CheckedCheckBox.isSelected();
        Assertions.assertTrue(isSelected, "Checkbox must be checked");

        CheckedCheckBox.click();
        boolean isNotSelected = CheckedCheckBox.isSelected();
        Assertions.assertFalse(isNotSelected, "Checkbox must not be checked");
    }

    @DisplayName("Проверка Default checkbox")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void findDefaultBoxTest() {
        WebElement CheckedCheckBoxText = driver.findElement(By.xpath("//label[@class='form-check-label w-100'][normalize-space(.)='Default checkbox']"));
        Assertions.assertEquals("Default checkbox", CheckedCheckBoxText.getText(), "Значения должны совпадать");

        WebElement DefaultCheckBox = driver.findElement(By.id("my-check-2"));
        boolean isNotSelected = DefaultCheckBox.isSelected();
        Assertions.assertFalse(isNotSelected, "Checkbox must not be checked");

        DefaultCheckBox.click();
        boolean isSelected = DefaultCheckBox.isSelected();
        Assertions.assertTrue(isSelected, "Checkbox must be checked");
    }

    @DisplayName("Проверка Checked Radio")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void findCheckedRadioTest() {
        WebElement CheckedRadioText = driver.findElement(By.xpath("//label[@class='form-check-label w-100'][normalize-space(.)='Checked radio']"));
        WebElement CheckedRadio = driver.findElement(By.id("my-radio-1"));

        boolean isSelected = CheckedRadio.isSelected();

        Assertions.assertEquals("Checked radio", CheckedRadioText.getText(), "Значения должны совпадать");
        Assertions.assertTrue(isSelected, "Radio must not be checked");
    }

    @DisplayName("Проверка Default Radio")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void findDefaultRadioTest() {
        WebElement DefaultRadioText = driver.findElement(By.xpath("//label[@class='form-check-label w-100'][normalize-space(.)='Default radio']"));
        WebElement DefaultRadio = driver.findElement(By.id("my-radio-2"));
        boolean isSelected = DefaultRadio.isSelected();

        Assertions.assertFalse(isSelected, "Radio must not be checked");
        Assertions.assertEquals("Default radio", DefaultRadioText.getText(), "Значения должны совпадать");
    }

    @DisplayName("Проверка Color Picker")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void findColorPickerTest() {
        WebElement ColorPickerText = driver.findElement(By.xpath("//label[@class='form-label w-100' and normalize-space(text())='Color picker']"));
        Assertions.assertEquals("Color picker", ColorPickerText.getText(), "Значения должны совпадать");

        WebElement ColorPickerMenu = driver.findElement(By.name("my-colors"));
        String starterColor = ColorPickerMenu.getDomAttribute("value");
        String blueColor = "#0000FF";
        String script = "arguments[0].setAttribute('value', arguments[1]);" + "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));";

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript(script, ColorPickerMenu, blueColor);

        String actulaColor = ColorPickerMenu.getDomAttribute("value");

        Assertions.assertNotEquals(starterColor, actulaColor, "Значения не должны совпадать");
    }

    @DisplayName("Проверка Date Picker")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void findDatePickerTest() {
        WebElement DatePickerText = driver.findElement(By.xpath("//label[@class='form-label w-100' and normalize-space(text())='Date picker']"));
        WebElement DatePickerMenu = driver.findElement(By.name("my-date"));
        DatePickerMenu.click();

        WebElement certainDate = driver.findElement(By.xpath("//td[@class='old day' and contains(text(), '23')]"));
        certainDate.click();

        String DatePickerValue = DatePickerMenu.getDomProperty("value");

        Assertions.assertEquals("Date picker", DatePickerText.getText(), "Значения должны совпадать");
        Assertions.assertEquals("02/23/2025", DatePickerValue, "Значения должны совпадать");
    }

    @DisplayName("Проверка Example range")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void findExampleRangeTest() {
        WebElement ExampleRangeText = driver.findElement(By.xpath("//label[@class='form-label w-100' and normalize-space(text())='Example range']"));
        WebElement ExampleRangeMenu = driver.findElement(By.name("my-range"));
        ExampleRangeMenu.click();
        String initialValue = ExampleRangeMenu.getDomProperty("value");
        Actions actions = new Actions(driver);
        actions.clickAndHold(ExampleRangeMenu)
                .moveByOffset(50, 0).release().perform();
        String newValue = ExampleRangeMenu.getDomProperty("value");

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

        WebElement FormSubmittedText = driver.findElement(By.xpath("//h1[@class='display-6']"));
        String actualFormSubmittedText = FormSubmittedText.getText();

        Assertions.assertEquals("Form submitted", actualFormSubmittedText, "Значения должны совпадать");
    }

    @DisplayName("Проверка FileInput")
    @Tags({@Tag("Smoke"), @Tag("UI")})
    @Test
    void findFileInputTest() throws InterruptedException {
        WebElement FileInputText = driver.findElement(By.xpath("//label[@class='form-label w-100' and normalize-space(text())='File input']"));
        Assertions.assertEquals("File input", FileInputText.getText(), "Значения должны совпадать");

        URL url = WebFormTest.class.getClassLoader().getResource("try.txt");
        String absolutePath = null;
        if (url != null) {
            absolutePath = new File(url.getPath()).getAbsolutePath();

        } else {
            throw new InterruptedException("Ресурс не найден");
        }
        WebElement fileUpload = driver.findElement(By.name("my-file"));
        fileUpload.sendKeys(absolutePath);
        driver.findElement(By.xpath("//button[text()='Submit']")).click();
        Thread.sleep(3000);
        Assertions.assertTrue(driver.getCurrentUrl().contains("try.txt"), "Файла нет в URL!");
    }
}


