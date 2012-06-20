package org.sagebionetworks.metrics.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Metrics implements EntryPoint {

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
	  String[] sageUsers = new String[] {"Users", "Bruce Hoff", "Dave Burdick", "Bennet Ng", "Geoff Shannon"};
	  String[] sageUserDays = new String[] {"Days", "10", "2", "14", "1", "7"};
	  String[] googleUsers = new String[] {"Users", "Jay Bean", "My Name", "Goo Gle", "En Gineer"};
	  String[] googleUserDays = new String[] {"Days", "13", "80", "4", "2", "3"};
	  String[] projects = new String[] {"Name", "Metagenomics", "Synapse Umbrella"};
	  String[] projectScore = new String[] {"Score", "11", "4"};

	  final Panel panel = new HorizontalPanel();
	  
	  Widget activeSageUsers = makeTableWithHeader("Sage", sageUsers, sageUserDays);
	  Widget activeGoogleUsers = makeTableWithHeader("Google", googleUsers, googleUserDays);
	  Widget activeProjects = makeTableWithHeader("Projects", projects, projectScore); 
	  
	  panel.add(activeSageUsers);
	  panel.add(activeGoogleUsers);
	  panel.add(activeProjects);
	  RootPanel.get("statTableContainer").add(panel);
  }
  
  public Widget makeTableWithHeader(String header, String[] col1, String[] col2) {
	  FlowPanel panel = new FlowPanel();
	  FlexTable table = new FlexTable();
	  InlineLabel label = new InlineLabel();
	  
	  label.setText(header);
	  panel.add(label);
	  
	  panel.addStyleName("margin-table");
	  table.getRowFormatter().setStylePrimaryName(0, "header-row");
	  for (int i = 0; i < col1.length; i++) {
		  table.setText(i, 0, col1[i]);
		  table.setText(i, 1, col2[i]);
	  }
	  
	  panel.add(table);
	  return panel;
  }
}
