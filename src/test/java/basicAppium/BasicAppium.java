package basicAppium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BasicAppium {

    private AppiumDriver appiumDriver;

    @BeforeEach
    public void openApplication() throws MalformedURLException {
        DesiredCapabilities capabilities= new DesiredCapabilities();
        capabilities.setCapability("deviceName","Erick");
        capabilities.setCapability("platformVersion","10");
        capabilities.setCapability("appPackage","com.huawei.calculator");
        capabilities.setCapability("appActivity",".Calculator");
        capabilities.setCapability("platformName","Android");

        appiumDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);

        // implicit
        appiumDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }
    @AfterEach
    public void closeApplication(){
        appiumDriver.quit();
    }

    @Test
    public void verifyCalculator() throws InterruptedException {
        Thread.sleep(5000);
        //click 2 com.huawei.calculator:id/digit_2
        appiumDriver.findElement(By.xpath("//android.widget.Button[@text='2']")).click();
        //click + com.huawei.calculator:id/op_add
        appiumDriver.findElement(By.xpath("//android.widget.ImageView[@content-desc='plus']")).click();
        //click 5 com.huawei.calculator:id/digit_5
        appiumDriver.findElement(By.xpath("//android.widget.Button[@text='5']")).click();
        //click = com.huawei.calculator:id/eq
        appiumDriver.findElement(By.xpath("//android.widget.ImageView[@content-desc='equals']")).click();
        Thread.sleep(2000);
        //verificar que la suma es 7 com.huawei.calculator:id/formula
        String expectedResult = "7";
        String actualResult = appiumDriver.findElement(By.id("//android.widget.EditText")).getText();

        Assertions.assertEquals(expectedResult, actualResult, "ERROR");
    }
}

