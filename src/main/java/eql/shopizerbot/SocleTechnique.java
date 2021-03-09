package eql.shopizerbot;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.slf4j.Logger;

public class SocleTechnique {
	
	public static WebDriver choisirNavigateur(String navigateur) {
		switch (navigateur) {
		case "Firefox":
			System.setProperty("webdriver.gecko.driver", "src/main/resources/driver/geckodriver.exe");
			return new FirefoxDriver();
		case "Chrome":
			System.setProperty("webdriver.chrome.driver", "src/main/resources/driver/chromedriver.exe");
			return new ChromeDriver();
		case "Edge":
			System.setProperty("webdriver.edge.driver", "src/main/resources/driver/msedgedriver.exe");
			return new EdgeDriver();
		case "Explorer":
			System.setProperty("webdriver.ie.driver", "src/main/resources/driver/IEDriverServer.exe");
			return new InternetExplorerDriver();
		default:
			System.out.println("le navigateur choisi n'existe pas");
			return null;
		}
	}
	public static boolean containsWords(String inputString, String[] items) {
	    boolean found = true;
	    for (String item : items) {
	        if (!inputString.contains(item)) {
	            found = false;
	            break;
	        }
	    }
	    return found;
	}
	public static boolean isElementPresent(WebElement we, Logger log) {
		boolean resultat = we.isDisplayed();
		if (resultat == false) {
			log.error(we + " indisponible");
		} else {
			log.info(we + " présent");
		}
		return resultat;
	}
	public void waitForBrowserReadystateComplete(WebDriver webDriver) {
	    for (int a=0; a<20; a++) {
	        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
	        if (javascriptExecutor.executeScript("return document.readyState")
	                .toString().equals("complete")) {
	            break;
	        }
	        sleepResponsibly(500);
	    }
	}

	public void sleepResponsibly(int timeMillisecond){
	    try{
	        Thread.sleep(timeMillisecond);
	    } catch (InterruptedException ex) {
	        Thread.currentThread().interrupt(); 
	        throw new RuntimeException(ex);
	    }
	}

	public void explicitScrollIntoView(WebDriver webDriver, WebElement elementToScrollIntoView, boolean blockOption) {
	    final String scriptStr = "arguments[0].scrollIntoView(" + blockOption + ");";
	    ((JavascriptExecutor) webDriver).executeScript(scriptStr, elementToScrollIntoView);

	    sleepResponsibly(500);

	}

}
