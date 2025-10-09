package qa_demo.pages.webtables.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class ModalAddForm {

    private final String TITLE_TEXT = "Registration Form";
    private SelenideElement
            firstName = $("#firstName"),
            lastName = $("#lastName"),
            userEmail = $("#userEmail"),
            age = $("#age"),
            salary = $("#salary"),
            department = $("#department"),
            submit = $("#submit");

    public ModalAddForm availableForm(){
        $(".modal-content").should(appear);
        $(".modal-content").$("#registration-form-modal").shouldHave(text(TITLE_TEXT));

        return this;
    }

    public ModalAddForm setFirstName(String value){
        firstName.setValue(value);

        return this;
    }

    public ModalAddForm setLastName(String value){
        lastName.setValue(value);

        return this;
    }

    public ModalAddForm setUserEmail(String value){
        userEmail.setValue(value);

        return this;
    }

    public ModalAddForm setAge(String value){
        age.setValue(value);

        return this;
    }

    public ModalAddForm setSalary(String value){
        salary.setValue(value);

        return this;
    }

    public ModalAddForm setDepartment(String value){
        department.setValue(value);

        return this;
    }

    public void submit(){
        submit.click();
    }

}
