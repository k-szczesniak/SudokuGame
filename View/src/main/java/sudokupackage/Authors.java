package sudokupackage;

import java.util.ListResourceBundle;

public class Authors extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"1", "Konrad Chojnacki"},
                {"2", "Krzysztof Szczesniak"}
        };
    }
}
