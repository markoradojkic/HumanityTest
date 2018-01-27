package humanity.selenium.webdriver;

import static org.junit.Assert.assertTrue;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AddEmployee {

	//Main script
	public static void main(String[] args) {
	
		//Instantiate objects. Set properties, define location
		System.setProperty("webdriver.chrome.driver", "D:\\Work\\Automation\\Drivers\\Selenium chrome driver v2.35\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, 100);
		AddEmployee myObj = new AddEmployee();
		
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
		
		//Click on the Staff button
		driver.findElement(By.xpath("//*[@id='sn_staff']/span")).click();
		
		//Wait for the popup to load and become clickable
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='walkme-balloon-420799']/div/div[1]/div[2]/div/div[1]")));
		
		//Close the third popup
		driver.findElement(By.xpath("//*[@id='walkme-balloon-420799']/div/div[1]/div[2]/div/div[1]")).click();
		
		//Wait for the fourth popup to become clickable
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='walkme-balloon-undefined']/div/div[1]/div[4]/div[2]/div/button/span")));
		
		//Close the fourth popup
		driver.findElement(By.xpath("//*[@id='walkme-balloon-undefined']/div/div[1]/div[4]/div[2]/div/button/span")).click();
		
		//Click the Add Employees button
		driver.findElement(By.xpath("//*[@id='act_primary']")).click();
		
		//Wait for the Save Employees button to load and become clickable
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='_as_save_multiple']")));
		
		//Type in the First Name
		driver.findElement(By.xpath("//*[@id='_asf1']")).sendKeys("Firsttest");
		
		//Type in the Last Name
		driver.findElement(By.xpath("//*[@id='_asl1']")).sendKeys("Lasttest");
		
		//Type in the email address
		driver.findElement(By.xpath("//*[@id='_ase1']")).sendKeys("testmail@test.com");
		
		//Click the Save Employees button
		driver.findElement(By.xpath("//*[@id='_as_save_multiple']")).click();
		
		//Wait for the Staff button to load and become clickable
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='sn_staff']/span")));
		
		//Wait 3 seconds - stability reasons
		wait3Seconds();
				
		//Click the Staff button
		driver.findElement(By.xpath("//*[@id='sn_staff']/span")).click();
		
		//Wait 3 seconds - stability reasons
		wait3Seconds();
				
		//Wait for the newly added employee's link to load and become clickable
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='_cd_staff']/div[3]/div[3]/table/tbody/tr[2]/td[2]/a")));
		
		//Click the newly added employee's link
		driver.findElement(By.xpath("//*[@id='_cd_staff']/div[3]/div[3]/table/tbody/tr[2]/td[2]/a")).click();
		
		//wait for the emplyee's page to load - specifically the "Click here to delete this employee" link to be clickable
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='_cd_staff']/div[3]/div[2]/table/tbody/tr/td[1]/div[2]/ul/li[3]/span[2]/a")));
		
		//Click the "Click here to delete this employee" link
		driver.findElement(By.xpath("//*[@id='_cd_staff']/div[3]/div[2]/table/tbody/tr/td[1]/div[2]/ul/li[3]/span[2]/a")).click();
		
		//Press Enter on the keyboard on the message popup
		Robot r = null;
		try {
			r = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		r.keyPress(KeyEvent.VK_ENTER);
		r.keyRelease(KeyEvent.VK_ENTER);
		
		//Wait for 3 seconds - stability reasons
		wait3Seconds();
				
		//Wait until the avatar link is loaded and clickable
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='tr_avatar']")));
		
		//Click the avatar link to open the options dropdown
		driver.findElement(By.xpath("//*[@id='tr_avatar']")).click();
		
		//Wait 3 seconds - stability reasons
		wait3Seconds();
				
		//Click the Sign Out button
		driver.findElement(By.xpath("//*[@id='userm']/div/div[4]/a")).click();
								
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

	//method that will create a 3 second delay in script eecution - for stability reasons
	public static void wait3Seconds() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
