
import com.testinium.base.BaseFunction;
import com.testinium.base.BaseTest;
import com.thoughtworks.gauge.Step;
import io.appium.java_client.MobileElement;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;
import java.util.Random;



public class BaseStep extends BaseTest implements BaseFunction {
    private static final Logger log = LogManager.getLogger(BaseStep.class);

    @Step("Uygulamanin acildigi kontrol edilir.")
    public void checkApp() throws InterruptedException {
        BaseFunction.checkStart();
    }
    @Step("Asagi dogru kaydirilir")
    public void scrollToBottom(){
        BaseFunction.scrollBottom();
    }

    @Step("<elementID> listesini bul(ID).")
    public List<MobileElement> ListByID(String elementID) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(elementID)));
        return appiumDriver.findElements(By.id(elementID));
    }

    @Step("<elementXpath> listesini bul(Xpath).")
    public List<MobileElement> ListByXpath(String elemntXpath) {
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(elemntXpath)));
        return appiumDriver.findElements(By.xpath(elemntXpath));
    }
    @Step("<elementID> rastgele sec(ID).")
    public void randomSelectID(String elemntID) {
        Random rand = new Random();
        ListByID(elemntID).get(rand.nextInt(ListByID(elemntID).size())).click();
    }

    @Step("<elementXpath> rastgele sec(Xpath).")
    public void randomSelectXpath(String elemntXpath) {
        Random rand = new Random();
        ListByXpath(elemntXpath).get(rand.nextInt(ListByXpath(elemntXpath).size() + 1)).click();
    }

    @Step("<elementID> elemani tıklanabilir olunca tiklanir(ID)")
    public void clickByID(String elmntID){
        BaseFunction.clickByID(elmntID);
    }
    @Step("<elementXpath> elamani tıklanabilir oldugunda tiklanir(xpath)")
        public void clickbyXpath(String elemntXpath){
            BaseFunction.clickByXpath(elemntXpath);
        }

    @Step("<seconds> saniye beklenir")
    public void waitSeconds(int seconds) throws InterruptedException {
        Thread.sleep(seconds*1000);
    }

    @Step("<scrollTimes> kere aşağı doğru kaydirilr")
    public static void scrollToBottom(int scrollTimes) throws InterruptedException {
        for (int i=1;i<=scrollTimes;i++){
            BaseFunction.scrollBottom();
            Thread.sleep(1000);
        }
    }


    @Step("<elementID> tiklanabilir urun bedenini tikla.(ID)")
    public void findProductSizeByID(String elementID) {
        List<MobileElement> sizeList = ListByID(elementID);
        for (MobileElement element : sizeList) {
            if (element.isEnabled())
                element.click();
            break;
        }
    }
    @Step("<elementXpath> tiklanabilir urun bedenini tikla.(Xpath)")
    public void findProductSizeByXpath(String elementXpath) {
        List<MobileElement> sizeList = ListByXpath(elementXpath);
        for (MobileElement element : sizeList) {
            if (element.isEnabled())
                element.click();
            break;
        }
    }


    @Step("<elementID> elementine <value> yaz.(ID)")
    public void sendKeysByID(String elementID, String value) {
        BaseFunction.sendKeysID(elementID,value);
    }

    @Step("<elementXpath> elementine <value> yaz.(Xpath)")
    public void sendKeysByXpath(String elementXpath, String value) {
        BaseFunction.sendKeysXpath(elementXpath,value);
    }

    @Step("<elementID> elementini bul ve <value> metni ile sayfa kontrolunu yap(ID)")
    public void ControlByID(String elemntID, String value) { Assert.assertEquals(value, appiumDriver.findElementById(elemntID).getText()); }

    @Step("<elementXpath> elementini bul ve <value> metni ile sayfa kontrolunu yap(XPATH)")
    public void ControlByXpath(String elementXpath, String value) { Assert.assertEquals(value, appiumDriver.findElementByXPath(elementXpath).getText());    }

    @Step("<element_id> elementinin seçilebilir oldugunu kontrol edilir(ID)")
    public static void checkSelectedByID(String element_id){
        BaseFunction.isSelectedCheckById(element_id);
    }
    @Step("<elementxpath> elementinin seçilebilir durumunu kontrol et(Xpath)")
    public static void checkSelectedByXpath(String elementxpath){
        BaseFunction.isSelectedCheckByXpath(elementxpath);
    }


}
