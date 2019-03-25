import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.xml.sax.SAXException;
import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import sun.security.pkcs11.wrapper.Constants;



import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.*;
import java.util.concurrent.TimeUnit;



@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Main {

    private static WebDriver driver;
    private static ExtentReports extent;
    private static ExtentTest test;


    @BeforeClass
    public static void bc() throws ClassNotFoundException, SQLException, IOException {

        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(StringConstant.ExtentHtml);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        test = extent.createTest(StringConstant.CreateTestName, StringConstant.CreateTestDescription); // name your test and add description
        Constants constants = new Constants();
        String currentTime = String.valueOf(System.currentTimeMillis());

        boolean succsed = false;
        try {
            UploadBrowsers.Browsers(driver);

            System.setProperty(StringConstant.chromeDriver1, StringConstant.chromeDriver2);
            driver = new ChromeDriver();
            succsed = true;
        } catch (Exception e) {
            e.printStackTrace();
            succsed = false;
        } finally {
            if (succsed == true) {
                test.log(Status.PASS, StringConstant.SuccseedDetails1, MediaEntityBuilder.createScreenCaptureFromPath(ScreenShotClass.takeScreenShot(StringConstant.ImagewPath + currentTime, driver)).build());
            } else {
                test.log(Status.FAIL, StringConstant.SuccseedDetails2, MediaEntityBuilder.createScreenCaptureFromPath(ScreenShotClass.takeScreenShot(StringConstant.ImagewPath + currentTime, driver)).build());
            }
        }
    }
//This test approach to the Remote SQL DataBase and Upload the Google maps website.

    @Test
    public void Test01_RunTest() throws IOException, ClassNotFoundException, SQLException {
        String currentTime = String.valueOf(System.currentTimeMillis());

        boolean Run = false;
        try {
            test.log(Status.INFO, StringConstant.UploadWEB);
            Class.forName(StringConstant.classForName);
            Run = true;


            Connection con = DriverManager.getConnection(StringConstant.SQL1, StringConstant.SQL2, StringConstant.SQL3);
            String statementToExecute = "";
            Statement stmt = con.createStatement();

            statementToExecute = StringConstant.SQL4;


            ResultSet rs = stmt.executeQuery(statementToExecute);
            while (rs.next()) {

                String Maps = rs.getString("Maps");


                String BrowseToWebSite = Maps;
                driver.get(BrowseToWebSite);

                driver.manage().window().maximize();


            }


            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            Run = false;
        } finally {
            if (Run == true) {
                test.log(Status.PASS, StringConstant.Upload1, MediaEntityBuilder.createScreenCaptureFromPath(ScreenShotClass.takeScreenShot(StringConstant.ImagewPath + currentTime, driver)).build());
            } else {
                test.log(Status.FAIL, StringConstant.Upload2, MediaEntityBuilder.createScreenCaptureFromPath(ScreenShotClass.takeScreenShot(StringConstant.ImagewPath + currentTime, driver)).build());
            }
        }
    }
//This test Convert the Address to coordinates and search for the result in google maps search box.

    @Test
    public void Test02_API() throws
           SQLException, ParserConfigurationException, ClassNotFoundException, SAXException, IOException, InterruptedException {
        String currentTime = String.valueOf(System.currentTimeMillis());
             boolean Convert=false;
        try {
            test.log(Status.INFO, StringConstant.GetAPI);
      driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        API.API_Locations(driver);
        System.out.println(driver.getTitle());
            Convert = true;
    }catch (Exception e) {
            e.printStackTrace();
            Convert = false;
        }
        finally {
            if (Convert==true) {
                test.log(Status.PASS, StringConstant.GetAPI2, MediaEntityBuilder.createScreenCaptureFromPath(ScreenShotClass.takeScreenShot(StringConstant.ImagewPath + currentTime,driver)).build());
            }
            else
            {
                test.log(Status.FAIL, StringConstant.GetAPI3,MediaEntityBuilder.createScreenCaptureFromPath(ScreenShotClass.takeScreenShot(StringConstant.ImagewPath + currentTime,driver)).build());
            }
        }
    }
//This test compare the Api address to the actual address that we get on the map
    @Test
    public void Test03_Compare() throws IOException {
        String currentTime = String.valueOf(System.currentTimeMillis());
        boolean TestCompare=false;
        try {

            test.log(Status.INFO, StringConstant.CompareTests);

        compareJson.Browsers(driver);
            TestCompare=true;

    }catch (Exception e){
            e.printStackTrace();
            TestCompare=false;
        }
        finally {
            if (TestCompare==true) {
                test.log(Status.PASS, StringConstant.CompareTests2, MediaEntityBuilder.createScreenCaptureFromPath(ScreenShotClass.takeScreenShot(StringConstant.ImagewPath + currentTime,driver)).build());
            }
            else
            {
                test.log(Status.FAIL, StringConstant.CompareTests3,MediaEntityBuilder.createScreenCaptureFromPath(ScreenShotClass.takeScreenShot(StringConstant.ImagewPath + currentTime,driver)).build());
            }
        }
    }


    @AfterClass

    public static void tearDown() {
        driver.manage().timeouts().implicitlyWait(100,TimeUnit.SECONDS);
        driver.quit();
        extent.flush();


    }


}






