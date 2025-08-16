package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Tag;
import tests.data.TestData;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import static com.codeborne.selenide.logevents.SelenideLogger.step;
public class FormTests extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    @Tag("web")
    void registrationFormAllFieldsTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        TestData testData = new TestData();
        step("Заполнить форму регистрации полностью", () -> {
        registrationPage.openPage()
                .removeBanners()
                .setFirstName(testData.getUserFirstName())
                .setLastName(testData.getUserLastName())
                .setUserEmail(testData.getUserEmail())
                .setUserGender(testData.getUserGender())
                .setUserNumber(testData.getUserPhoneNumber())
                .setDateOfBirth(testData.getUserBirthDay(), testData.getUserMonthOfBirth(), testData.getUserYearOfBirth())
                .setSubjects(testData.getUserSubject())
                .setUserHobbies(testData.getUserHobbies())
                .uploadUserPhoto(testData.getUserPicture())
                .setUserAddress(testData.getUserAddress())
                .setUserState(testData.getUserState())
                .setUserCity(testData.getUserCity())
                .clickSubmitButton();
        });
        step("Проверить правильность заполнения таблицы результатов", () -> {
        registrationPage.checkTableResultsAppear()
                .checkTableResultsHeader()
                .checkResuls("Student Name", testData.getUserFirstName() + " " + testData.getUserLastName())
                .checkResuls("Student Email", testData.getUserEmail())
                .checkResuls("Gender", testData.getUserGender())
                .checkResuls("Mobile", testData.getUserPhoneNumber())
                .checkResuls("Date of Birth", testData.getUserBirthDay() + " " + testData.getUserMonthOfBirth() + "," + testData.getUserYearOfBirth())
                .checkResuls("Subjects", testData.getUserSubject())
                .checkResuls("Hobbies", testData.getUserHobbies())
                .checkResuls("Picture", testData.getUserPicture())
                .checkResuls("Address", testData.getUserAddress())
                .checkResuls("State and City", testData.getUserState() + " " + testData.getUserCity());
        });
        }


    @Test
    @Tag("web")
    void registrationFormMinFieldsTest(){
        TestData testData = new TestData();
        step("Заполнить обязательные поля формы регистрации", () -> {
        registrationPage.openPage()
                .removeBanners()
                .setFirstName(testData.getUserFirstName())
                .setLastName(testData.getUserLastName())
                .setUserEmail(testData.getUserEmail())
                .setUserGender(testData.getUserGender())
                .setUserNumber(testData.getUserPhoneNumber())
                .setDateOfBirth(testData.getUserBirthDay(), testData.getUserMonthOfBirth(), testData.getUserYearOfBirth())
                .clickSubmitButton();
        });
        step("Проверить правильность заполнения таблицы результатов", () -> {
        registrationPage.checkTableResultsAppear()
                .checkTableResultsHeader().checkResuls("Student Name", testData.getUserFirstName() + " " + testData.getUserLastName())
                .checkResuls("Student Email", testData.getUserEmail())
                .checkResuls("Gender", testData.getUserGender())
                .checkResuls("Mobile", testData.getUserPhoneNumber())
                .checkResuls("Date of Birth", testData.getUserBirthDay() + " " + testData.getUserMonthOfBirth() + "," + testData.getUserYearOfBirth());
        });
        }

    @Test
    @Tag("web")
    void registrationFormNegativeTest(){
        TestData testData = new TestData();
        step("Заполнить несколько полей формы регистрации", () -> {
        registrationPage.openPage().
                removeBanners()
                .setFirstName(testData.getUserFirstName())
                .setUserEmail(testData.getUserEmail())
                .setUserGender(testData.getUserGender())
                .setUserNumber(testData.getUserPhoneNumber())
                .setDateOfBirth(testData.getUserBirthDay(), testData.getUserMonthOfBirth(), testData.getUserYearOfBirth())
                .clickSubmitButton();
        });
        step("Таблица результатов не отображается", () -> {
        registrationPage.checkTableResultsNotAppear();
        });
    }
}
