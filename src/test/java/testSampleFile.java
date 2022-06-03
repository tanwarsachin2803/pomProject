import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.AppiumServer;
import utilities.platFormSelection;
import utilities.testBase;

import java.io.IOException;
import java.util.Properties;

public class testSampleFile {
    testBase tb;
    Properties prop;
    platFormSelection pf;
    AppiumServer as;



    @BeforeClass
    public void classSetup()
    {
        tb=new testBase();
        prop=new Properties();
        pf=new platFormSelection(tb);
        as=new AppiumServer(tb);
    }

    @Test
    public void test() throws Exception {
        prop=tb.readProp("config");
        System.out.println(prop.getProperty("platform"));
       // as.Start();
        pf.androidos();
    }
}
