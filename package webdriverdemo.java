package webdriverdemo;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverDemo {

    private WebDriver driver;

    @Before
    public void setUp() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.navigate().to("http://www.calculator.net/");
        driver.manage().window().maximize();
    }

    @Test
    public void testPositivePercentageCalculation() {
        navigateToPercentCalculator();
        enterValuesAndCalculate("10", "50");
        assertResult("5");
    }

    @Test
    public void testZeroPercentageCalculation() {
        navigateToPercentCalculator();
        enterValuesAndCalculate("0", "100");
        assertResult("0");
    }

    @Test
    public void testLargeValuesPercentageCalculation() {
        navigateToPercentCalculator();
        enterValuesAndCalculate("1000", "2000");
        assertResult("50");
    }

    @After
    public void tearDown() {
      
        driver.close();
    }



    private void navigateToPercentCalculator() {
        driver.findElement(By.xpath("//*[@id=\"homelistwrap\"]/div[3]/div[2]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"content\"]/table[2]/tbody/tr/td/div[3]/a")).click();
    }

    private void enterValuesAndCalculate(String firstValue, String secondValue) {
        driver.findElement(By.id("cpar1")).sendKeys(firstValue);
        driver.findElement(By.id("cpar2")).sendKeys(secondValue);
        driver.findElement(By.xpath("//*[@id=\"content\"]/form[1]/table/tbody/tr[2]/td/input[2]")).click();
    }

    private void assertResult(String expectedResult) {
        String result = driver.findElement(By.xpath("/html/body/div[3]/div[1]/p[2]/font/b")).getText();
        assertEquals(expectedResult, result);
    }
}