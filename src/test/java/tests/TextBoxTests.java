package tests;

import org.junit.jupiter.api.Test;
import pages.TextBoxPage;

import static com.codeborne.selenide.logevents.SelenideLogger.step;

public class TextBoxTests extends TestBase {

    String userFullName = "Kathryn Dean";
    String userEmail = "katrin.dean@gmail.com";
    String userCurrentAddress = "Test current address";
    String userPermanentAddress = "Test permanent address";
    TextBoxPage textBoxPage = new TextBoxPage();

    @Test
    void FillFormTextBoxTest(){
        step("Заполнить форму", () -> {
        textBoxPage.openPage().
                removeBanners().
                setUserFullName(userFullName).
                setUserEmail(userEmail).
                setCurrentAddress(userCurrentAddress).
                setPermanentAddress(userPermanentAddress).
                clickSubmitButton();
        });
        step("Проверить правильность заполнения полей", () -> {
        textBoxPage.checkOutputName(userFullName).
                checkOutputEmail(userEmail).
                checkOutputCurrentAddress(userCurrentAddress).
                setPermanentAddress(userPermanentAddress);
        });
    }
}
