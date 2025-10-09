package qa_demo.pages.button;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class ButtonPage {

    private final String TITLE_TEXT = "Buttons";
    private SelenideElement
            doubleClickBtn = $("#doubleClickBtn"),
            doubleClickMessage = $("#doubleClickMessage"),
            rightClickBtn = $("#rightClickBtn"),
            rightClickMessage = $("#rightClickMessage"),
            clickMessage = $("#dynamicClickMessage");

    public ButtonPage openPage() {
        open("/buttons");
        $("h1.text-center").shouldHave(text(TITLE_TEXT));
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        return this;
    }

    public ButtonPage doubleClickBtn() {
        doubleClickBtn.doubleClick();
        return this;
    }

    public ButtonPage verifyDoubleClickBtn() {
        doubleClickMessage.should(appear);
        doubleClickMessage.shouldHave(text("You have done a double click"));

        return this;
    }

    public ButtonPage rightClickBtn() {
        rightClickBtn.contextClick();
        return this;
    }

    public ButtonPage verifyRightClickBtn() {
        rightClickMessage.should(appear);
        rightClickMessage.shouldHave(text("You have done a right click"));

        return this;
    }

    public ButtonPage clickBtn() {
        rightClickBtn.parent().sibling(0).find("button").click();
        return this;
    }

    public ButtonPage verifyClickBtn() {
        clickMessage.should(appear);
        clickMessage.shouldHave(text("You have done a dynamic click"));

        return this;
    }
}
