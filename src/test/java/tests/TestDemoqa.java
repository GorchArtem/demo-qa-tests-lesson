package tests;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TestDemoqa extends TestBase {
    Faker faker = new Faker();

    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            email = faker.internet().emailAddress(),
            mobile = faker.number().digits(10),
            dayOfBirth = "10",
            monthOfBirth = "May",
            yearOfBirth = "1988",
            subject1 = "Chemistry",


            picture = "facemy.jpg",
            currentAddress = faker.address().fullAddress(),
            state = "Uttar Pradesh",
            city = "Merrut";

    @Test
    void fillFormTest() {
        String linkURL = "https://demoqa.com/automation-practice-form";


        open(linkURL);

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $$(".custom-radio").get(1).click();
        $("#userNumber").setValue(mobile);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(monthOfBirth);
        $(".react-datepicker__year-select").selectOption(yearOfBirth);
        $$(".react-datepicker__day").find(text(dayOfBirth)).click();
        $("#subjectsInput").setValue(subject1).pressEnter();
        $(byText("Music")).click();
        $(byText("Sports")).click();
        $("#uploadPicture").uploadFromClasspath(picture);
        $("#currentAddress").setValue(currentAddress);
        $("#react-select-3-input").setValue(state).pressEnter();
        $("#react-select-4-input").setValue(city).pressEnter();
        $("#submit").click();

        $(".table-responsive").shouldHave(text(firstName + " " + lastName),
                text(email),
                text("Female"),
                text(mobile),
                text(dayOfBirth + " " + monthOfBirth + "," + yearOfBirth),
                text(subject1),
                text("Music, Sports"),
                text(picture),
                text(currentAddress),
                text(state + " " + city));

    }
}
