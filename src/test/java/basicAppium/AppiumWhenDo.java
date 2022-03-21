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

public class AppiumWhenDo {

    private AppiumDriver appiumDriver;

    @BeforeEach
    public void openApplication() throws MalformedURLException {
        DesiredCapabilities capabilities= new DesiredCapabilities();
        capabilities.setCapability("deviceName","Erick");
        capabilities.setCapability("platformVersion","10");
        capabilities.setCapability("appPackage","com.vrproductiveapps.whendo");
        capabilities.setCapability("appActivity","com.vrproductiveapps.whendo.ui.HomeActivity");
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
    public void verifyTask() throws InterruptedException {
        Thread.sleep(5000);
        //click boton +
        appiumDriver.findElement(By.xpath("//android.widget.ImageButton[@resource-id='com.vrproductiveapps.whendo:id/fab']")).click();
        //click titulo y llenar titulo
        appiumDriver.findElement(By.xpath("//android.widget.EditText[@text='Title']")).click();
        appiumDriver.findElement(By.xpath("//android.widget.EditText[@text='Title']")).sendKeys("Tarea 1");
        //click nota y llenar nota
        appiumDriver.findElement(By.xpath("//android.widget.EditText[@text='Notes']")).click();
        appiumDriver.findElement(By.xpath("//android.widget.EditText[@text='Notes']")).sendKeys("Descripcion 1");
        //click save
        appiumDriver.findElement(By.xpath("//android.widget.TextView[@content-desc='Save']")).click();
        Thread.sleep(2000);
        //verificar titulo
        String expectedTitle = "Tarea 1";
        String actualTitle = appiumDriver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.vrproductiveapps.whendo:id/home_list_item_text']")).getText();
        Assertions.assertEquals(expectedTitle, actualTitle, "ERROR el titulo esta incorrecto");
        //verificar descripcion
        String expectedDesc = "Descripcion 1";
        String actualDesc = appiumDriver.findElement(By.xpath("//android.widget.TextView[@resource-id='com.vrproductiveapps.whendo:id/home_list_item_text2']")).getText();
        Assertions.assertEquals(expectedDesc, actualDesc, "ERROR la descripcion esta incorrecta");
    }
}
