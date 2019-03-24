import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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