package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.CredentialsConfig;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static java.lang.String.format;

public class TestBase {
    @BeforeAll
    static void setup(){
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        Configuration.startMaximized = true;

        CredentialsConfig credentials = ConfigFactory.create(CredentialsConfig.class);
        String login = credentials.login();
        String password = credentials.password();
        String url = System.getProperty("url", " ");

        Configuration.remote = format("https://%s:%s@%s", login, password, url);
        //gradle clean properties_tests -Durl='selenoid.autotests.cloud/wd/hub/'
        String message = format("I login to %s as %s with password %s", url, login, password);
        System.out.println(message);



        Map<String, ?> configMap = new HashMap<String, Object>(){{
            put("enableVNC", true);
            put("enableVideo", true);
            put("resolutionHeight", 800);
            put("resolutionWidth", 1200);
        }};

        DesiredCapabilities capabilities = new DesiredCapabilities(configMap);
        //capabilities.setCapability("enableVNC", true);
        //capabilities.setCapability("enableVideo", true);

        Configuration.browserCapabilities = capabilities;
        
        //Это тест вывода разрешения, убрать потом
        String rWidthName = "resolutionWidth";
        String rHeightName = "resolutionHeight";
        
        //Configuration.browserCapabilities.setCapability(rWidthName,1200);
        //Configuration.browserCapabilities.setCapability(rHeightName,800);
        System.out.println(rWidthName + ": " + Configuration.browserCapabilities.getCapability(rWidthName));
        System.out.println(rHeightName + ": " + Configuration.browserCapabilities.getCapability(rHeightName));
    }
    @AfterEach
    public void tearDown() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}
