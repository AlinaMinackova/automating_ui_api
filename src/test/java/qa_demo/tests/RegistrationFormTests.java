package qa_demo.tests;

import com.github.javafaker.Faker;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import qa_demo.pages.registration.RegistrationPage;

import java.util.Locale;

import static io.qameta.allure.Allure.step;

@Tag("ui")
public class RegistrationFormTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    @DisplayName("Заполняем форму и проверяемм успешное заполнение")
    void successfulRegistrationTest() {
        Faker faker = new Faker(new Locale("it"));

        String userName = faker.name().firstName(),
                lastName = faker.name().lastName(),
                userEmail = faker.internet().emailAddress(),
                currentAddress = faker.lebowski().quote();

        step("Заполняем данные формы", () -> {

            registrationPage.openPage()
                    .setFirstName(userName)
                    .setLastName(lastName)
                    .setEmail(userEmail)
                    .setGender("Other")
                    .setPhone("1234567890")
                    .setBirthDate("30", "July", "2008")
                    .setSubject("Math")
                    .setHobbies("Sports")
                    .setPhoto("selenoid/img/1.png")
                    .setAddress(currentAddress)
                    .setState("NCR")
                    .setCity("Delhi")
                    .submit();

        });

        step("Проверяем данные формы", () -> {

            registrationPage.verifyResultsModalAppears()
                    .verifyResult("Student Name", userName + " " + lastName)
                    .verifyResult("Student Email", userEmail)
                    .verifyResult("Gender", "Other")
                    .verifyResult("Mobile", "1234567890")
                    .verifyResult("Date of Birth", "30 July,2008")
                    .verifyResult("Subjects", "Math")
                    .verifyResult("Hobbies", "Sports")
                    .verifyResult("Picture", "1.png")
                    .verifyResult("Address", currentAddress)
                    .verifyResult("State and City", "NCR Delhi");

        });
    }

}
