package driver;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import util.Helper;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.AfterSpec;
import com.thoughtworks.gauge.AfterStep;
import com.thoughtworks.gauge.BeforeScenario;
import com.thoughtworks.gauge.BeforeSuite;
import com.thoughtworks.gauge.ExecutionContext;

import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

	private static WebDriver driver;
	public static WebDriver getDriver() {
        return driver;
    }
	@BeforeScenario
	public void Setup() throws MalformedURLException {

        String browser = System.getenv("BROWSER");
        String headlessMode = System.getenv("HEADLESS_MODE");
        browser = (browser == null) ? "CHROME": browser;
        System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+File.separator+System.getenv("webdriver.gecko.driver"));
        System.setProperty("webdriver.chrome.driver","C:\\Users\\rramasahayam\\Downloads\\Rahul\\Smartbiz\\Drivers\\chromedriver.exe");
        switch (browser) {
            case "IE":                      
            	System.setProperty("webdriver.ie.driver", "C:\\Users\\rramasahayam\\Documents\\projects\\app-test\\copilot\\Drivers\\IEDriverServer.exe");
                DesiredCapabilities cap = new DesiredCapabilities();
                cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                cap.setCapability("requireWindowFocus", true);
                cap.setCapability("ignoreZoomSetting", true);
                cap.setCapability("browserAttachTimeout", 3000);
                cap.setCapability("elementScrollBehavior", 1);
                cap.setCapability("enablePersistentHover", false);
                cap.setCapability("applicationCacheEnabled", false);
                InternetExplorerDriverManager.getInstance().setup();
                driver = new InternetExplorerDriver(cap);
                driver.manage().window().maximize();
            case "FIREFOX":                
            	 FirefoxOptions options = new FirefoxOptions();
                 //Removes captive portal service errors
                 options.addPreference("network.captive-portal-service.enabled",false);
                 options.addPreference("javascript.enabled", true);

                 //Disable all Firefox update services
                 options.addPreference("app.update.enabled", false);
                 options.addPreference("app.update.service.enabled", false);
                 options.addPreference("app.update.auto", false);
                 options.addPreference("app.update.staging.enabled", false);
                 options.addPreference("app.update.silent", false);
                 
               //Disable all media players and their updates
                 options.addPreference("media.gmp-provider.enabled", false);

                 //Disable the auto update of extensions
                 options.addPreference("extensions.update.autoUpdate", false);
                 options.addPreference("extensions.update.autoUpdateEnabled", false);
                 options.addPreference("extensions.update.enabled", false);
                 options.addPreference("extensions.update.url", "");

                 //Disable microsummary updates. I have no clue what microsummary is and it's probably not important
                 options.addPreference("Browser.microsummary.enabled", false);
                 options.addPreference("Browser.microsummary.generatorUpdateInterval", false);

                 //Disable an auto update of some media player. Probably not useful
                 options.addPreference("media.gmp-gmpopenh264.autoupdate", false);

                 //Disable firefox lightweight themes updates.
                 options.addPreference("lightweightThemes.update.enabled", false);

                 //Not sure what these are for, they are probably not useful
                 options.addPreference("browser.tabs.remote.autostart", false);
                 options.addPreference("browser.tabs.remote.autostart.2", false);

                 //Here's where the magic happens, this sets the network proxy for firefox
                 //4 Means autodetect proxy
                 options.addPreference("network.proxy.type", 4);

                 //Disable even more addons updates
                 options.addPreference("xpinstall.enabled", false);
                 options.addPreference("extensions.enabledScopes", 0);
                 if(headlessMode.equalsIgnoreCase("true"))
                     options.addArguments("-headless");
                 //FirefoxDriverManager.getInstance().setup();
                 driver = new FirefoxDriver(options);
            case "CHROME":
            default:
            	DesiredCapabilities caps = new DesiredCapabilities();
	            ChromeOptions chromeOptions = new ChromeOptions();
	            chromeOptions.addArguments("--window-size=1920,1080");
//	            if(headlessMode.equalsIgnoreCase("true"))
//	            {
//	            	chromeOptions.addArguments("--headless");
//	            	chromeOptions.addArguments("--disable-gpu");
//	            }
	            Map prefs = new HashMap();  
	            prefs.put("profile.default_content_settings.cookies", 2);  
	            chromeOptions.setExperimentalOption("prefs", prefs);
	            driver = new ChromeDriver(chromeOptions);
	            caps.setBrowserName("chrome");
	            driver.manage().window().maximize();
	            
	            //Lines 137-142 are used to execute the scripts on remote driver
	            /*
	             caps.setPlatform(Platform.LINUX);
	            chromeOptions.merge(caps);
	            chromeOptions.setProxy(null);
	            String nodeUrl = "http://vfp-selenium-1.ftscc.net:4444/wd/hub";
	            String nodeUrl = "http://jenkinssw.ftscc.net:4444/wd/hub";
	            driver = new RemoteWebDriver(new URL(nodeUrl), chromeOptions); */
        }
    }
	
	@BeforeSuite 
    public void prerequisite(){
        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmss");
		Helper.setPropertyValue("Execution_Timestamp", sdf.format(new Date()));
    }

    @AfterScenario
    public void TearDown(ExecutionContext context)
    {
        if(driver instanceof ChromeDriver){
        	driver.close(); //Prevents an error with Chrome driver as well as other drivers
        }
        driver.quit();
    }
}