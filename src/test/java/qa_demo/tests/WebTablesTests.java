package qa_demo.tests;

import org.junit.jupiter.api.*;
import qa_demo.pages.webtables.WebTablesPage;

@Tag("ui")
public class WebTablesTests extends TestBase {
    WebTablesPage webTablesPage = new WebTablesPage();

    @BeforeEach
    public void openPage() {
        webTablesPage.openPage();
    }

    @Test
    @DisplayName("Проверяем добавление записи в таблицу через модальное окно")
    public void tablesTest() {

        Long sizeBefore = webTablesPage.sizeTable();

        webTablesPage.openModalAddForm()
                .availableForm()
                .setFirstName("First Name")
                .setLastName("Last Name")
                .setUserEmail("a@gmail.com")
                .setAge("10")
                .setSalary("1000000")
                .setDepartment("departmant")
                .submit();

        webTablesPage.sizeTable();

        Long sizeAfter = webTablesPage.sizeTable();
        Assertions.assertEquals(sizeAfter, sizeBefore + 1);

    }
}
