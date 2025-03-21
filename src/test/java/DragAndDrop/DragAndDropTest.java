package DragAndDrop;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class DragAndDropTest {
    WebDriver driver;
   private static final String BASE_URL = "https://bonigarcia.dev/selenium-webdriver-java/drag-and-drop.html";

   @DisplayName("проверка Drag & drop")
   @Tags({@Tag("Smoke"), @Tag("UI")})
   @Test
   void dragAndDropTest(){
       driver = new ChromeDriver();
       driver.get(BASE_URL);
       driver.manage().window().maximize();

       WebElement draggablePanel = driver.findElement(By.id("draggable"));
       Point locationOfDraggablePanel = draggablePanel.getLocation();
       String firstlocationOfDraggablePanelAsString = locationOfDraggablePanel.toString();
       WebElement target = driver.findElement(By.id("target"));
       Point locationOfTarget = target.getLocation();
       String locationOfTargetAsString = locationOfTarget.toString();

       Actions actions = new Actions(driver);
       actions.dragAndDrop(draggablePanel,target).perform();

       Point lastLocationOfDraggablePanel = draggablePanel.getLocation();
       String actualtLocationOfDraggablePanelAsString = lastLocationOfDraggablePanel.toString();

       Assertions.assertNotEquals(firstlocationOfDraggablePanelAsString, actualtLocationOfDraggablePanelAsString, "Значения должны не совпадать");
       Assertions.assertEquals(locationOfTargetAsString, actualtLocationOfDraggablePanelAsString, "Значения должны совпадать");
       driver.quit();
   }
}
