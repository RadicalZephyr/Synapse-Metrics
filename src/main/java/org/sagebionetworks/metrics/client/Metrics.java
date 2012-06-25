package org.sagebionetworks.metrics.client;

import org.sagebionetworks.metrics.client.widget.RowTable;
import org.sagebionetworks.metrics.client.widget.RowTable.Row;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Metrics implements EntryPoint {

    private TableDataServiceAsync activeUserSvc = GWT.create(TableDataService.class);
    
    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {

        initActiveUserSvc();
        
        RowTable ucscTable = new RowTable(2, "soe.ucsc.org -- 4 active users");
        RowTable sageTable = new RowTable(2, "sagebase.org -- 7 active users");

        Row header = new Row("User", "Last Sign-In");
        ucscTable.setHeadings(header);
        sageTable.setHeadings(header);

        RowTable projectTable = new RowTable(2, "Projects");

        // Make RPC call to get data
        getActiveUsers(ucscTable, "soe.ucsc.edu");
        getActiveUsers(sageTable, "sagebase.org");
        getActiveProjects(projectTable);

        projectTable.setHeadings(new Row("Name", "Score"));

        final Panel panel = new HorizontalPanel();
        Panel userStatPanel = new VerticalPanel();

        userStatPanel.add(ucscTable);
        userStatPanel.add(sageTable);

        panel.add(userStatPanel);
        panel.add(projectTable);

        RootPanel.get("statTableContainer").add(panel);
    }

    private void initActiveUserSvc() {
        if (activeUserSvc == null) {
            activeUserSvc = GWT.create(TableDataService.class);
        }
    }

    private void getActiveUsers(final RowTable table, String domain) {
        AsyncCallback<Row[]> callback = new AsyncCallback<Row[]>() {
            public void onFailure(Throwable caught) {
            }

            public void onSuccess(Row[] result) {
                table.setRows(result);
            }
        };
        activeUserSvc.getActiveUsers(domain, callback);
    }
 
    private void getActiveProjects(final RowTable table) {
        AsyncCallback<Row[]> callback = new AsyncCallback<Row[]>() {
            public void onFailure(Throwable caught) {
            }

            public void onSuccess(Row[] result) {
                table.setRows(result);
            }
            
        };
        activeUserSvc.getActiveProjects(callback);
    }
}
