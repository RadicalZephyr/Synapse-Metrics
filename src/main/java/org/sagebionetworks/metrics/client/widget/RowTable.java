package org.sagebionetworks.metrics.client.widget;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class RowTable extends Composite {
	public class Row {
		private final Object[] rowData;
		
		Row(Object... rowData) {
			this.rowData = rowData;
		}

		String getData(int column) {
			return rowData[column].toString(); 
		}
	}

	private final int numColumns;
	private InlineLabel heading = new InlineLabel();
	private FlexTable table = new FlexTable();
	private Row tableHeadings;
	
	RowTable(int numColumns) {
		this.numColumns = numColumns;
		setup();
	}
	
	RowTable (String heading, int numColumns) {
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

		setHeadings(this.new Row(emptyArray.toArray()));
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
}
