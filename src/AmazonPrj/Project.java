package AmazonPrj;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Project {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		
		// Step1 -> Storing the ChromeDriver in the driver
		WebDriver driver = new ChromeDriver();
		
		// opening the Amazon.in page 
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		
		// Step2 ->Searching the Samsung product in the search bar
		WebElement SearchBox = driver.findElement(By.id("twotabsearchtextbox"));
		SearchBox.sendKeys("Samsung");
		
		// Step3 ->clicking on the search button icon on the main page
		WebElement SearchBtn = driver.findElement(By.id("nav-search-submit-button"));
		SearchBtn.click();
		
		// Step4 -> Finding the locator for the Products(Samsung) via xpath
		List<WebElement> ProductList = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']//h2/a"));
		
		// Step5 ->Finding the locator for the Price of respective products via xpath
		List<WebElement> PriceList = driver.findElements(By.xpath("//div[@data-component-type='s-search-result']//span[@class='a-price']"));
		
		//Printing the size of the product list
		System.out.println("Total number of Product on the Amazon page are :" + PriceList.size());

		// Step6 ->using for loop to print the name of the products and respective prices
		for(int cnt = 0; cnt<ProductList.size();cnt++) {
			System.out.println(ProductList.get(cnt).getText() + " "+PriceList.get(cnt).getText());
		}
		
		//Step7 -> As the first product will open in a new window once clicked,handling the window
		String ParentWin = driver.getWindowHandle();
		String ExpectedValue = ProductList.get(0).getText();
		ProductList.get(0).click();

		Set<String> Allwin = driver.getWindowHandles();
		for(String Win : Allwin) {
			System.out.println(Win);
			
			if(!Win.equals(ParentWin)) {
				driver.switchTo().window(Win);
			}
		}
		
		// Step8 -> Validating the first product name with the output of the product list generated earlier
		WebElement ProductName = driver.findElement(By.id("productTitle"));
		String Name = ProductName.getText();

		if(Name.equals(ExpectedValue)) {
			System.out.println("The Product Name is matching");
		}
		else {
			System.out.println("The Product Name is not matching");
		}

		driver.quit();		

	}

}
