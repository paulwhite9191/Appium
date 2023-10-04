package ru.netology.qa;

import io.appium.java_client.AppiumDriver;

import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;

import ru.netology.qa.screens.MainScreen;

import java.net.MalformedURLException;
import java.net.URL;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UIAutomatorTest {
    private AppiumDriver driver;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:deviceName", "Some name");
        desiredCapabilities.setCapability("appium:appPackage", "ru.netology.testing.uiautomator");
        desiredCapabilities.setCapability("appium:appActivity", "ru.netology.testing.uiautomator.MainActivity");
        desiredCapabilities.setCapability("appium:automationName", "uiautomator2");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

        URL remoteUrl = new URL("http://127.0.0.1:4723/");

        driver = new AppiumDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void emptyStringTest() {
        MainScreen button = new MainScreen(driver);

        String textBefore = button.textToBeChanged.getText();
        button.userInput.sendKeys(" ");
        button.buttonChange.click();
        String textAfter = button.textToBeChanged.getText();
        Assertions.assertEquals(textBefore, textAfter);
    }

    @Test
    public void NewActivityTest() {
        MainScreen button = new MainScreen(driver);

        String newText = "Hi, Necrology";
        button.userInput.sendKeys(newText);
        button.buttonActivity.click();
        button.activityText.isDisplayed();
        Assertions.assertEquals(newText, button.activityText.getText());
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}