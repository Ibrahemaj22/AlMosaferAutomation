package AlmosaferTest;

import java.sql.Driver;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import net.bytebuddy.asm.Advice.WithCustomMapping;

import org.testng.Assert;

public class MyTestCases extends Parameters {

	@BeforeTest
	public void mySetUp() {
		configurationToAccess();

	}

	@Test(priority = 1, description = "Default Language is English", enabled = true)
	public void DefaultLanguageIsEnglish() {

		String ActualLanguage = driver.findElement(By.tagName("html")).getDomAttribute("lang");
		Assert.assertEquals(ActualLanguage, ExpectedEnglishLanguage);

	}

	@Test(priority = 2, description = "Default Currency is SAR ", enabled = true)
	public void DefaultCurrencyIsSar() {

		String ActualCurrency = driver.findElement(By.className("sc-dRFtgE")).getText();
		Assert.assertEquals(ActualCurrency, ExpectedCurrency);

	}

	@Test(priority = 3, description = "Contact Number is Correct ", enabled = true)
	public void ContactNumberIsCorrect() {

		String ActualContactNumber = driver.findElement(By.className("sc-hUfwpO")).getText();
		Assert.assertEquals(ActualContactNumber, ExpectedContactNumber);

	}

	@Test(priority = 4, description = "Qitaf Logo Is Displayed in Footer", enabled = true)

	public void QitafLogoIsDisplayed() {

		WebElement TheFooter = driver.findElement(By.tagName("footer"));

		WebElement TheContainerDiv = TheFooter.findElement(By.cssSelector(".sc-ghsgMZ.hIElfs"));

		WebElement QitafLogo = TheContainerDiv.findElement(By.tagName("svg"));

		boolean ActualQitafLogoIsDisplayed = QitafLogo.isDisplayed();

		Assert.assertEquals(ActualQitafLogoIsDisplayed, ExpectedQitafLogoIsDisplayed);
	}

	@Test(priority = 5, description = "Hotel Tab Is Not Selected", enabled = true)

	public void HotelTabIsNotSelected() {

		WebElement HotelTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));

		String ActualValueForHotelTab = HotelTab.getDomAttribute("aria-selected");

		Assert.assertEquals(ActualValueForHotelTab, ExpectedValueForHotelTab);
	}

	@Test(priority = 6, description = "Departure Date", enabled = true)

	public void CheckDepartureDate() {

		List<WebElement> dates = driver.findElements(By.className("sc-fvLVrH"));

		String ActualDepartureDate = dates.get(0).getText();

		Assert.assertEquals(ActualDepartureDate, Tomorrow);
	}

	@Test(priority = 7, description = "Return Date", enabled = true)

	public void CheckReturnDate() {

		List<WebElement> dates = driver.findElements(By.className("sc-fvLVrH"));

		String ActualReturnDate = dates.get(1).getText();

		Assert.assertEquals(ActualReturnDate, DayAfterTomorrow);
	}

	@Test(priority = 8, description = "Change Website Language Randomly", enabled = true)

	public void RandomlyChangeWebsiteLanguage() throws InterruptedException {

		driver.get(Websites[RandomIndexForTheWebsite]);

		WebElement HotelSearchTab = driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		HotelSearchTab.click();

		WebElement HotelSearchInput = driver.findElement(By.cssSelector(".sc-phbroq-2.uQFRS.AutoComplete__Input"));

		WebElement headerForTheLanguage = driver.findElement(By.xpath("//a[@data-testid= 'Header__LanguageSwitch']"));

		if (headerForTheLanguage.getText().equals("العربية")) {

			String ActualLanguage = driver.findElement(By.tagName("html")).getDomAttribute("lang");
			Assert.assertEquals(ActualLanguage, ExpectedEnglishLanguage);

			HotelSearchInput.sendKeys(EnglishCities[randomIndexEN]);

			WebElement ListOfCities = driver.findElement(By.cssSelector(".sc-phbroq-4.gGwzVo.AutoComplete__List"));
			ListOfCities.findElements(By.tagName("li")).get(1).click();

		}

		else {
			String ActualLanguage = driver.findElement(By.tagName("html")).getDomAttribute("lang");
			Assert.assertEquals(ActualLanguage, ExpectedArabicLanguage);

			HotelSearchInput.sendKeys(ArabicCities[randomIndexAR]);

			WebElement ListOfCities = driver.findElement(By.cssSelector(".sc-phbroq-4.gGwzVo.AutoComplete__List"));
			ListOfCities.findElements(By.tagName("li")).get(1).click();
		}

		WebElement selectRoomRandomlyField = driver.findElement(By.cssSelector(".sc-tln3e3-1.gvrkTi"));

		Select select = new Select(selectRoomRandomlyField);

		int randomIndex = rand.nextInt(2);
		select.selectByIndex(randomIndex);

		WebElement SearchButton = driver.findElement(By.className("btn-primary"));
		SearchButton.click();

		Thread.sleep(10000);

		WebElement Results = driver.findElement(By.xpath("//span[@data-testid= 'srp_properties_found']"));

		boolean ActualResult = Results.getText().contains("stays") || Results.getText().contains("مكان");
		Assert.assertEquals(ActualResult, ExpectedResult);
	}

}
