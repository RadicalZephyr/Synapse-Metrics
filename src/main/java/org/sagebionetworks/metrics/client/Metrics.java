package org.sagebionetworks.metrics.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Metrics implements EntryPoint {
  /**
   * The message displayed to the user when the server cannot be reached or
   * returns an error.
   */
  private static final String SERVER_ERROR = "An error occurred while "
      + "attempting to contact the server. Please check your network "
      + "connection and try again.";

  /**
   * Create a remote service proxy to talk to the server-side Greeting service.
   */
  private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

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
	  
	  FlexTable activeSageUsers = makeTableWithHeader("Sage", sageUsers, sageUserDays);
	  FlexTable activeGoogleUsers = makeTableWithHeader("Google", googleUsers, googleUserDays);
	  FlexTable activeProjects = makeTableWithHeader("Projects", projects, projectScore); 
	  
	  panel.add(activeSageUsers);
	  panel.add(activeGoogleUsers);
	  panel.add(activeProjects);
	  RootPanel.get("statTableContainer").add(panel);
  }
  
  public FlexTable makeTableWithHeader(String header, String[] col1, String[] col2) {
	  FlexTable table = new FlexTable();
	  
	  table.addStyleName("margin-table");
	  table.getRowFormatter().setStylePrimaryName(0, "header-row");
	  for (int i = 0; i < col1.length; i++) {
		  table.setText(i, 0, col1[i]);
		  table.setText(i, 1, col2[i]);
	  }

	  return table;
  }
}
