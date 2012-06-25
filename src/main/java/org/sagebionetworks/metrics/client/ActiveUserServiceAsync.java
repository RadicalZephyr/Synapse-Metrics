package org.sagebionetworks.metrics.client;

import org.sagebionetworks.metrics.client.widget.RowTable.Row;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ActiveUserServiceAsync {

    void getActiveUsers(String domain, AsyncCallback<Row[]> callback);

}
