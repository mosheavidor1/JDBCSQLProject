import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

//This test make a compare between the actual website to the search from the API coordinates.
import static org.junit.Assert.assertEquals;

public class compareJson {

    public static void Browsers(WebDriver driver) {

        driver.get(StringConstant.JsonAddress );

 String eTitle = StringConstant.JsonAddress2;

        String aTitle =   StringConstant.JsonAddress3;


        if (aTitle.contentEquals(eTitle))
        {
            System.out.println( "Test Passed") ;
        }
        else {
            System.out.println( "Test Failed" );
        }


 assertEquals(StringConstant.JsonAddress2,eTitle);


    }
}