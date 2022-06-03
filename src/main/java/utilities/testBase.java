package utilities;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

@Log4j2
public class testBase {
    public WebDriver driver;
    public WebDriverWait wait;
    String platForm;
    public Properties prop;

    public testBase() // constructor is used to initialize the variables of the respective class
    {
        super(); // it is used to call all the variable and methods
    }


    public Properties readProp(String fileName) throws IOException {
        log.info("Property file read");
        prop=new Properties();
        String filePath=System.getProperty("user.dir")+"/src/properties/"+fileName+".properties";
        System.out.println(filePath);
        FileInputStream fis= new FileInputStream(filePath); // used to get the input of the file
        prop.load(fis);
        return prop;
    }

}
