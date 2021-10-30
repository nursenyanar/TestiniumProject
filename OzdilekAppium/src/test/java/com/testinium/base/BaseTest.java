package com.testinium.base;


import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.FluentWait;
import static com.testinium.device.DeviceInfo.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BaseTest {
    private static final Logger log = LogManager.getLogger(BaseTest.class);
    protected static AppiumDriver<MobileElement> appiumDriver;
    protected static FluentWait<AppiumDriver<MobileElement>> appiumFluentWait;
    protected boolean localAndroid =true;

    @BeforeScenario
    public void start() throws MalformedURLException {
        BasicConfigurator.configure();
        if(StringUtils.isEmpty(System.getenv("key"))){
            if(localAndroid){
                log.info("Start test!");
                DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, PLTNAME);
                desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME,DVCNAME);
                desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,DVCVER);
                desiredCapabilities.setCapability(MobileCapabilityType.UDID, UDID);
                desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
                desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,APPLICATIONPACKAGE);
                desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,MAINACTIVITY);
                desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,3000);
                URL url = new URL("http://0.0.0.0:4723/wd/hub");
                appiumDriver = new AndroidDriver(url,desiredCapabilities);

            }
        }
        appiumFluentWait = new FluentWait<AppiumDriver<MobileElement>>(appiumDriver);
        appiumFluentWait.withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofMillis(450)).ignoring(NoSuchElementException.class);

    }

    @AfterScenario
    public void finish(){
        if(appiumDriver !=null)
            log.info("Finish test!");
            appiumDriver.quit();
    }
}
