package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  private final Properties properties;
  WebDriver wd;

  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private GroupsHelper groupsHelper;
  private ContactsHelper contactsHelper;
  private String browser;
  private DbHelper dbHelper;

  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }


  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    dbHelper = new DbHelper();
if ("".equals(properties.getProperty("selenium.server"))){
  if (Objects.equals(browser, BrowserType.FIREFOX)) {
    wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true));
  } else if (Objects.equals(browser, BrowserType.CHROME)) {
    wd = new ChromeDriver();
  } else if (Objects.equals(browser, BrowserType.IE)) {
    wd = new InternetExplorerDriver();
  }
} else {
  DesiredCapabilities capabilities = new DesiredCapabilities();
  capabilities.setBrowserName(browser);
  capabilities.setPlatform(Platform.fromString(System.getProperty("platform", "win7")));
  wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilities);
}

    wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    wd.get(properties.getProperty("web.baseURL"));
    contactsHelper = new ContactsHelper(wd);
    groupsHelper = new GroupsHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));
  }


  public void stop() {
    wd.quit();
  }

  public GroupsHelper group() {
    return groupsHelper;
  }

  public NavigationHelper goTo() {
    return navigationHelper;
  }

  public ContactsHelper contact() {
    return contactsHelper;
  }

  public DbHelper db(){return dbHelper;}
}
