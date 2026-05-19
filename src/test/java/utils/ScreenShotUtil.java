package utils;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenShotUtil {

    public void TakeScreenShot(WebDriver Driver, String TestName) {
        String Time = new SimpleDateFormat("yyMMdd_HHmmss").format(new Date());
        String screenShotPath = "screenshot/ " + TestName + "_" + Time + ".png";
        File screenShotFile = ((TakesScreenshot) Driver).getScreenshotAs(OutputType.FILE);
        File destfile = new File(screenShotPath);
        destfile.getParentFile().mkdir();
        screenShotFile.renameTo(destfile);
    }

}
