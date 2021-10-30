package guru.qa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.conditions.Text;
import com.codeborne.selenide.selector.ByText;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestReporter;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TestDemoqa {
    @BeforeAll
    static void beforeAll() {
        Configuration.startMaximized = true;
    }

    @Test
    void fillFormTest() {
        open("https://demoqa.com/automation-practice-form");

        $("#firstName").setValue("1Name");
        $("#lastName").setValue("2LastName");
        $("#userEmail").setValue("qwe@qas.ru");
        $$(".custom-radio").get(1).click();
        $("#userNumber").setValue("1234567891");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("January");
        $(".react-datepicker__year-select").selectOption("2020");
        $$(".react-datepicker__day").find(text("12")).click();
        $("#subjectsInput").setValue("Physics").pressEnter();
        $(byText("Music")).click();
        $(byText("Sports")).click();
        $("#uploadPicture").uploadFromClasspath("facemy.jpg");
        $("#currentAddress").setValue("SomeAddress");
        $("#react-select-3-input").setValue("Uttar Pradesh").pressEnter();
        $("#react-select-4-input").setValue("Agra").pressEnter();
        $("#submit").click();

        $(".table-responsive").shouldHave(text("1Name 2LastName"), text("qwe@qas.ru"),
                text("Female"), text("1234567891"), text("12 January,2020"),
                text("Physics"), text("Music, Sports"), text("facemy.jpg"), text("SomeAddress"),
                text("Uttar Pradesh Agra"));

    }
}
