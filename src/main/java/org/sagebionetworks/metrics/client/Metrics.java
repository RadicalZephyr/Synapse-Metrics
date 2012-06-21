package org.sagebionetworks.metrics.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Metrics implements EntryPoint {

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
	  String[] testUsersUCSC = new String[] {"Users", "jlong", "kellrott", "elinor", "ted"};
	  String[] testUserDateUCSC = new String[] {"Days", "14-May-2012", "10-May-2012", "10-May-2012", "01-May-2012"};
	  
	  String[] testUsersSage = new String[] {"Users", "chris.gaiteri", "xudong.dai", "charles.ferte", "ben.sauerwine", "in.sock.jang", "erich.huang", "lara.mangravite"};
	  String[] testUserDateSage = new String[] {"Days", "18-May-2012", "16-Apr-2012", "27-Mar-2012", "23-Apr-2012", "15-Mar-2012", "14-Mar-2012", "15-Mar-2012", "25-Mar-2012"};
	  
	  String[] projects = new String[] {"Name", "Metagenomics", "Synapse Umbrella"};
	  String[] projectScore = new String[] {"Score", "11", "4"};

	  final Panel panel = new HorizontalPanel();
	  Panel userStatPanel = new VerticalPanel();
	  
	  Widget activeUCSCUsers = makeTableWithHeader("soe.ucsc.org - 4 active users", testUsersUCSC, testUserDateUCSC);
	  Widget activeSageUsers = makeTableWithHeader("sagebase.org - 7 active users", testUsersSage, testUserDateSage);
	  
	  Widget activeProjects = makeTableWithHeader("Projects", projects, projectScore); 
	  
	  userStatPanel.add(activeUCSCUsers);
	  userStatPanel.add(activeSageUsers);
	  
	  panel.add(userStatPanel);
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
