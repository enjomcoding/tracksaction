package myproject.Connect_DB;

import javax.swing.table.DefaultTableModel;

public class Table_edit extends DefaultTableModel {

    public Table_edit(Object[][] data, String[] columnNames) {
        super(data, columnNames);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return true; // Allow all cells to be editable
    }
}
