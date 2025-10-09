package qa_demo.pages.webtables;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import qa_demo.pages.webtables.components.ModalAddForm;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class WebTablesPage {

    private final String TITLE_TEXT = "Web Tables";
    private ElementsCollection table = $$("div.rt-tbody .rt-tr-group");
    private SelenideElement
            addNewRecordButton = $("#addNewRecordButton");

    public WebTablesPage openPage() {
        open("/webtables");
        $("h1.text-center").shouldHave(text(TITLE_TEXT));
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        return this;
    }

    public ModalAddForm openModalAddForm() {
        addNewRecordButton.click();
        return new ModalAddForm();
    }

    public Long sizeTable() {
        return table.stream().filter(m -> m.getText().length() > 7).count();
    }

//    public void getLastChild(String value) {
//        table.last().shouldHave(text(value));
//    }


}
