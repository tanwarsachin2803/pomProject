package utilities;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.IOException;
import java.util.Properties;
@Log4j2
public class platFormSelection extends testBase{
    public testBase tb;
    public DesiredCapabilities dc;
    Properties prop;

    public platFormSelection(testBase tb)
    {this.tb=tb;}

    public void websiteSelect() throws IOException {
        String driverPath=System.getProperty("user.dir")+"/drivers";
        WebDriverManager.chromedriver().setup(); //used to download and install newest version of driver
        driver = new ChromeDriver();
    }

    public void androidos() throws IOException {
        log.info("Android app is opening");
        prop=tb.readProp("android_config");
        String app=System.getProperty("user.dir")+"/src/main/app/"+prop.getProperty("appName")+".apk";
        dc=new DesiredCapabilities();
        dc.setCapability("automationName","UiAutomator2");
        dc.setCapability("platformName","Android");
        dc.setCapability("platformVersion","11.0");
        dc.setCapability("deviceName","Pixel 2 API 30");
        dc.setCapability("app",app);
        log.info("App "+prop.getProperty("appName") +" is opened");
        dc.setCapability("udid","");


    }
}
