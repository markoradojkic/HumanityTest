package humanity.selenium.webdriver;

import static org.junit.Assert.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginTest {
	
	//Main script
	public static void main(String[] args) {
	
		//Instantiate objects. Set properties, define location
		System.setProperty("webdriver.chrome.driver", "D:\\Work\\Automation\\Drivers\\Selenium chrome driver v2.35\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, 100);
		LoginTest myObj = new LoginTest();
		
		//Call the method that will set up the browser
		driver = myObj.invokeBrowser(driver);

		//Go to the login page
		driver.navigate().to("https://testaccount45.humanity.com/app/");
		
		//Type in username - email
		driver.findElement(By.xpath("//*[@id='email']")).sendKeys("markoradojkic@telus.net");
		
		//Type in the correct password
		driver.findElement(By.xpath("//*[@id='password']")).sendKeys("AutomationTest");
		
		//Click the Log in button
		driver.findElement(By.xpath("//*[@id='LoginForm']/div[3]/div/button")).click();
		
		//Wait for an element to load and become clickable - first popup
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='walkme-balloon-462027']/div/div[1]/div[2]/div/div[2]/div/a/span")));
		
		//Click on the button to close pop-up
		driver.findElement(By.xpath("//*[@id='walkme-balloon-462027']/div/div[1]/div[2]/div/div[2]/div/a/span")).click();
		
		//Wait for an element to load and become clickable - second popup
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='walkme-balloon-360256']/div/div[1]/div[2]/div/div[1]")));
		
		//Click on the button to close pop-up
		driver.findElement(By.xpath("//*[@id='walkme-balloon-360256']/div/div[1]/div[2]/div/div[1]")).click();
		
		//Assert that login was successful by checking that an element from the welcome page (dashboard) is displayed
		assertTrue("Element not found!", elementExists("//*[@id='right']/div/table/tbody/tr/td[1]/div[2]/div[1]", driver));
		
		//Click the avatar link to open the options dropdown
		driver.findElement(By.xpath("//*[@id='tr_avatar']")).click();
			
		//Click the Sign Out button
		driver.findElement(By.xpath("//*[@id='userm']/div/div[4]/a")).click();
			
		//Wait for the Login page to load - specifically the login button to become clickable
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='LoginForm']/div[3]/div/button")));
		
		//Type in username - email
		driver.findElement(By.xpath("//*[@id='email']")).sendKeys("markoradojkic@telus.net");
		
		//Type in an incorrect password
		driver.findElement(By.xpath("//*[@id='password']")).sendKeys("TestTest");
		
		//Click the Log in button
		driver.findElement(By.xpath("//*[@id='LoginForm']/div[3]/div/button")).click();
		
		//Assert that a proper error message was displayed when attempting to log in with the incorrect password
		assertTrue("Element not found!", elementExists("//*[@id='response-message']", driver));
				
	}
	
	//Setup browser
	public WebDriver invokeBrowser(WebDriver driver1) {
		driver1.manage().deleteAllCookies();
		driver1.manage().window().maximize();
		return driver1;
		
	}
	
	//method for checking if an element exists on the page. boolean
	static boolean elementExists(String xpath1, WebDriver driver1) {
		try {
	        driver1.findElement(By.xpath(xpath1));
	    } catch (NoSuchElementException e) {
	        return false;
	    }
	    return true;
	}

}
