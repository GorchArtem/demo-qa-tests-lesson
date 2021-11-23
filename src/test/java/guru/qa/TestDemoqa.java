package guru.qa;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
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
        String linkURL = "https://demoqa.com/automation-practice-form";
        String firstName = "1Name";
        String lastName = "2LastName";


        open(linkURL);

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
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

        $(".table-responsive").shouldHave(text(firstName + " " + lastName),
                text("qwe@qas.ru"),
                text("Female"),
                text("1234567891"),
                text("12 January,2020"),
                text("Physics"),
                text("Music, Sports"),
                text("facemy.jpg"),
                text("SomeAddress"),
                text("Uttar Pradesh Agra"));

    }
}
