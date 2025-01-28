package AlmosaferTest;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Parameters {

	WebDriver driver = new ChromeDriver();
	Random rand = new Random();

	String[] Websites = { "https://www.almosafer.com/ar", "https://www.almosafer.com/en" };
	int RandomIndexForTheWebsite = rand.nextInt(Websites.length);

	String DefaultWebsite = "https://www.almosafer.com/en";

	String ExpectedEnglishLanguage = "en";
	String ExpectedArabicLanguage = "ar";

	String ExpectedCurrency = "SAR";

	String ExpectedContactNumber = "+966554400000";

	boolean ExpectedQitafLogoIsDisplayed = true;

	String ExpectedValueForHotelTab = "false";

	LocalDate date = LocalDate.now();

	int Today = date.getDayOfMonth();

	String Tomorrow = Integer.toString(date.plusDays(1).getDayOfMonth());

	String DayAfterTomorrow = Integer.toString(date.plusDays(2).getDayOfMonth());

	String[] EnglishCities = { "Dubai", "Jeddah", "Riyadh" };
	int randomIndexEN = rand.nextInt(EnglishCities.length);

	String[] ArabicCities = { "دبي", "جدة", "الرياض" };
	int randomIndexAR = rand.nextInt(ArabicCities.length);

	boolean ExpectedResult = true;

	public void configurationToAccess() {

		driver.get(DefaultWebsite);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		WebElement selectMsgSar = driver.findElement(By.className("cta__saudi"));

		selectMsgSar.click();
	}

}
