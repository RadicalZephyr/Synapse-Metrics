package org.sagebionetworks.metrics.client;

import org.sagebionetworks.metrics.client.widget.RowTable.Row;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface TableDataServiceAsync {

    void getActiveUsers(String domain, AsyncCallback<Row[]> callback);

    void getActiveProjects(AsyncCallback<Row[]> callback);

}
