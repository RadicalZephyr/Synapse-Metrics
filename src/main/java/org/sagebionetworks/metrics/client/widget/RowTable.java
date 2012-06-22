package org.sagebionetworks.metrics.client.widget;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class RowTable extends Composite {
    public static class Row {
        private final Object[] rowData;
        
        public Row(Object... rowData) {
            this.rowData = rowData;
        }

        public String getData(int column) {
            return rowData[column].toString(); 
        }
    }

    private final int numColumns;
    private InlineLabel heading = new InlineLabel();
    private FlexTable table = new FlexTable();
    private Row tableHeadings;
    
    public RowTable(int numColumns) {
        this.numColumns = numColumns;
        setup();
    }
    
    public RowTable (int numColumns, String heading) {
        this.numColumns = numColumns;
        this.heading.setText(heading);
        setup();
    }
    
    private void setup() {
        VerticalPanel panel = new VerticalPanel();

        // Initialize the header row to be a list of empty strings
        // May not actually be necessary, but unlikely to have 
        // performance implications
        ArrayList<String> emptyArray = new ArrayList<String>();
        for (int i = 0; i < numColumns; i++) {
            emptyArray.add("");
        }

        setHeadings(new Row(emptyArray.toArray()));
        table.getRowFormatter().setStylePrimaryName(0, "header-row");

        panel.addStyleName("margin-table");
        panel.add(heading);
        panel.add(table);

        initWidget(panel);
    }
    
    public void setHeadings(Row headings) {
        tableHeadings = headings;
        setRow(0, tableHeadings);
    }
    
    public void setRow(int row, Row data) {
        for (int i = 0; i < numColumns; i++) {
            table.setText(row, i, data.getData(i));
        }
    }

    public void setRows(Row[] rows) {
        setRows(1, rows);
    }

    public void setRows(int startRow, Row[] rows) {
        int i = startRow;
        for (Row row : rows) {
            setRow(i, row);
            i++;
        }
    }
}
