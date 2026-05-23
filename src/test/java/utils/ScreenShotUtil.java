package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenShotUtil {

    public void TakeScreenShot(
            WebDriver Driver,
            String TestName
    ) throws IOException {

        String Time =
                new SimpleDateFormat("yyMMdd_HHmmss")
                        .format(new Date());

        String screenShotPath =
                "screenshot/"
                        + TestName.replaceAll(" ", "_")
                        + "_"
                        + Time
                        + ".png";

        File screenShotFile =
                ((TakesScreenshot) Driver)
                        .getScreenshotAs(OutputType.FILE);

        File destfile =
                new File(screenShotPath);

        destfile.getParentFile().mkdirs();

        Files.copy(
                screenShotFile.toPath(),
                destfile.toPath(),
                StandardCopyOption.REPLACE_EXISTING
        );
    }

    public String TakeScreenShotForExtent(
            WebDriver Driver,
            String TestName
    ) {

        try {

            String Time =
                    new SimpleDateFormat("yyMMdd_HHmmss")
                            .format(new Date());

            String fileName =
                    TestName.replaceAll(" ", "_")
                            + "_"
                            + Time
                            + ".png";

            String screenShotPath =
                    "target/screenshots/" + fileName;

            byte[] screenshotBytes =
                    ((TakesScreenshot) Driver)
                            .getScreenshotAs(OutputType.BYTES);

            File destfile =
                    new File(screenShotPath);

            destfile.getParentFile().mkdirs();

            Files.write(
                    destfile.toPath(),
                    screenshotBytes
            );

            return "../screenshots/" + fileName;

        } catch (Exception e) {

            e.printStackTrace();

            return null;
        }
    }
}