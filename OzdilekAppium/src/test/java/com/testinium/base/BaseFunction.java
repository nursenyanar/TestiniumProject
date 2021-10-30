package com.testinium.base;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static com.testinium.base.BaseTest.appiumDriver;

public interface BaseFunction {
    static TouchAction action = new TouchAction(appiumDriver);
    int  x = appiumDriver.manage().window().getSize().width / 2;
    int y_bottom = (int) (appiumDriver.manage().window().getSize().height * 0.8);
    int y_top = (int) (appiumDriver.manage().window().getSize().height * 0.2);
    WebDriverWait wait = new WebDriverWait(appiumDriver,30);
    Logger logger = Logger.getLogger(BaseFunction.class);

    static void checkStart() throws InterruptedException {
        String context = appiumDriver.getContext();
        if (context.equals("NATIVE_APP")) {
            logger.info("Uygulama acildi.");
        } else {
            logger.error("Uygulama acilmadi !");
        }
    }

    static void scrollAction(){
        action.press(PointOption.point(x,y_bottom)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
                .moveTo(PointOption.point(x, y_top))
                .release().perform();

    }

    static void scrollBottom(){
            scrollAction();
            logger.info("Asagi kaydirma islemi gerçeklestirildi.");
    }
    static void clickByID(String elementID){
        wait.until(ExpectedConditions.presenceOfElementLocated(new By.ById(elementID))).click();
        logger.info("ID'le "+elementID + " elemanina tiklanildi");
    }
    static void clickByXpath(String xpath){
        wait.until(ExpectedConditions.presenceOfElementLocated(new By.ByXPath(xpath))).click();
        logger.info("Xpath'le "+ xpath + " elemanina tiklanildi");
    }
    static void sendKeysID(String elementID, String value){
        WebElement element = appiumDriver.findElementById(elementID);
        wait.until(ExpectedConditions.elementToBeClickable(new By.ById(elementID)));
        element.sendKeys(value);
    }
    static void sendKeysXpath(String elementXpath, String value){
        WebElement element = appiumDriver.findElementByXPath(elementXpath);
        wait.until(ExpectedConditions.elementToBeClickable(new By.ByXPath(elementXpath)));
        element.sendKeys(value);
    }

    static void isSelectedCheckByXpath(String xpath) {
        By element = new By.ByXPath(xpath);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        Assert.assertTrue(Boolean.parseBoolean(appiumDriver.findElement(element).getAttribute("selected")));
        if (Boolean.parseBoolean(appiumDriver.findElement(element).getAttribute("selected"))) {
            logger.info("Elementin selected ozeligi true");
        } else {
            logger.error("Elementin selected ozeligi false");
        }
    }
    static void isSelectedCheckById(String id) {
        By element = new By.ById(id);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        Assert.assertTrue(Boolean.parseBoolean(appiumDriver.findElement(element).getAttribute("selected")));
        if (Boolean.parseBoolean(appiumDriver.findElement(element).getAttribute("selected"))) {
            logger.info("Elementin selected ozeligi true");
        } else {
            logger.error("Elementin selected ozeligi false");
        }
    }



}
