package app.controller.components;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class CommonTables {
    //kete parametrin table e kam lene veq me dallu me cilen tabele po punojme
    public static <T> void configureTable(
            TableView<T> table,
            List<TableColumn<T, ?>> columns,
            String[] propertyNames) {

        for (int i = 0; i < columns.size(); i++) {
            columns.get(i).setCellValueFactory(new PropertyValueFactory<>(propertyNames[i]));
        }
    }
}
