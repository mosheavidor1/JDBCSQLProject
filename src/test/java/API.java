import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.*;
import java.util.concurrent.TimeUnit;

//This test Upload the API string from DataBase on a RemoteSQL And also convert it from address to coordinates and also search for those coordinates in the google maps search box.

public class  API {

    private static WebDriver driver;


    public static void API_Locations(WebDriver driver) throws IOException, ParserConfigurationException, SAXException, SQLException, ClassNotFoundException {
//Connects to the SQL DB.

        Class.forName(StringConstant.classForName);

        Connection con = DriverManager.getConnection(StringConstant.SQL1, StringConstant.SQL2, StringConstant.SQL3);
        String statementToExecute = "";
        Statement stmt = con.createStatement();


        statementToExecute = StringConstant.SQL5;//Import the Json API string from MySQL.


        ResultSet rs = stmt.executeQuery(statementToExecute);

        while (rs.next()) {


            String API = rs.getString("API");


            String y = API;

//Convert the Searched address to Coordinates.
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(y).build();//Convert Location to coordinates


            Response response = client.newCall(request).execute();
            String jsonData = response.body().string();

            JSONObject mainJsonObject = new JSONObject(jsonData);

            JSONArray resultsJson = mainJsonObject.getJSONArray("results");
            JSONObject mainJson = resultsJson.getJSONObject(0);

            JSONObject geometry = mainJson.getJSONObject("geometry");
            JSONObject location = geometry.getJSONObject("location");

            System.out.println(location.get("lat").toString() + " " + location.get("lng").toString());

//Placed the Coordinates into the Google Maps search box.
            driver.findElement(By.id("searchboxinput")).sendKeys(location.get("lat").toString() + " " + location.get("lng").toString());


            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            driver.findElement(By.id("searchbox-searchbutton")).click();





        }
    }
}
