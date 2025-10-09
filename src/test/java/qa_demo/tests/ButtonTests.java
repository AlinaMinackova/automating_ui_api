package qa_demo.tests;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import qa_demo.pages.button.ButtonPage;

@Tag("ui")
public class ButtonTests extends TestBase {
    ButtonPage buttonPage = new ButtonPage();

    @BeforeEach
    public void openPage() {
        buttonPage.openPage();
    }

    @Test
    @DisplayName("Проверяем двойное нажатие на кнопку")
    public void testDoubleButton() {
        buttonPage.doubleClickBtn().verifyDoubleClickBtn();
    }

    @Test
    @DisplayName("Проверяем нажатие на кнопку правой кнопкой мыши")
    public void testRightButton() {
        buttonPage.rightClickBtn().verifyRightClickBtn();
    }

    @Test
    @DisplayName("Проверяем нажатие на динамическую кнопку")
    public void testDynamicButton() {
        buttonPage.clickBtn().verifyClickBtn();
    }
}
