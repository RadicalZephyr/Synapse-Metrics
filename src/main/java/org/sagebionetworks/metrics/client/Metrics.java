package org.sagebionetworks.metrics.client;

import org.sagebionetworks.metrics.client.widget.RowTable;
import static org.sagebionetworks.metrics.client.widget.RowTable.Row;

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

    private ActiveUserServiceAsync activeUserSvc = GWT.create(ActiveUserService.class);
    private Row[] ucscData = null;
    private Row[] sageData = null;;
    private Row[] projectData = null;
    
    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        initActiveUserSvc();
        
        getActiveUsers(1);
        getActiveUsers(2);
        getActiveUsers(3);
        
        RowTable ucscTable = new RowTable(2, "soe.ucsc.org -- 4 active users");
        RowTable sageTable = new RowTable(2, "sagebase.org -- 7 active users");

        Row header = new Row("User", "Last Sign-In");
        ucscTable.setHeadings(header);
        sageTable.setHeadings(header);

        ucscTable.setRows(ucscData);
        sageTable.setRows(sageData);

        RowTable projectTable = new RowTable(2, "Projects");

        projectTable.setHeadings(new Row("Name", "Score"));
        projectTable.setRows(projectData);

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
            activeUserSvc = GWT.create(ActiveUserService.class);
        }
    }

    private void getActiveUsers(final int window) {
        AsyncCallback<Row[]> callback = new AsyncCallback<Row[]>() {
            public void onFailure(Throwable caught) {
            }

            public void onSuccess(Row[] result) {
                setData(window, result);
            }
        };
        activeUserSvc.getActiveUsers(window, callback);
    }
    
    private void setData(int window, Row[] rows) {
        if (window == 1) {
            ucscData = rows;
        } else if (window == 2) {
            sageData = rows;
        } else {
            projectData = rows;
        }
    }
}
