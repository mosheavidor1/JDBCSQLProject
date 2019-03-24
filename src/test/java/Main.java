import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.*;
import java.util.concurrent.TimeUnit;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Main {

    private static WebDriver driver;

    @BeforeClass
    public static void bc() throws ClassNotFoundException, SQLException {

        UploadBrowsers.Browsers(driver);

        System.setProperty(StringConstant.chromeDriver1, StringConstant.chromeDriver2);
        driver = new ChromeDriver();
    }


    @Test
    public void Test01_RunTest() throws ClassNotFoundException, SQLException {

        Class.forName(StringConstant.classForName);

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

    }


    @Test
    public void Test02_API() throws
            SQLException, ParserConfigurationException, ClassNotFoundException, SAXException, IOException, InterruptedException {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        API.API_Locations(driver);
        System.out.println( driver.getTitle());
    }

    @Test
    public void Test03_Compare () {


compareJson.Browsers(driver);

    }
    }












