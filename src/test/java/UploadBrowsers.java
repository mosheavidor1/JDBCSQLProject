


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.sql.*;
//This test make sure that all browsers can run on this code .

public class UploadBrowsers {

  public static void Browsers(WebDriver driver) throws ClassNotFoundException, SQLException {


      Class.forName(StringConstant.classForName);

      Connection con = DriverManager.getConnection(StringConstant.SQL1, StringConstant.SQL2, StringConstant.SQL3);
      String statementToExecute = "";
      Statement stmt = con.createStatement();

      statementToExecute = StringConstant.SQL4;


      ResultSet rs = stmt.executeQuery(statementToExecute);
      while (rs.next()) {

          String Maps = rs.getString("Maps");
          String x = Maps;

        String browserType = (x);
        if (browserType.equals("Chrome")) {
            driver = new ChromeDriver();
        } else if (browserType.equals("FireFox")) {
            driver = new FirefoxDriver();
        } else if (browserType.equals("IE")) {
            driver = new InternetExplorerDriver();

    }}}}

