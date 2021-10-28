package guru.qa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestReporter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestDemoqa {
    @BeforeAll
    static void beforeAll(){
        Configuration.startMaximized = true;
    }

    @Test
    void fillFormTest(){
        open("https://demoqa.com/automation-practice-form");

        $("#firstName").setValue("1Name");
        $("#lastName").setValue("2LastName");
        $("#userEmail").setValue("qwe@qas.ru");
        $("#gender-radio-3").setValue("Other");
        $("userNumber").setValue("889987632");
        $("dateOfBirthInput").click();
        $("#uploadPicture").uploadFromClasspath("лицо моё");
        $("").setValue("");
        $("").setValue("");
    }
}
