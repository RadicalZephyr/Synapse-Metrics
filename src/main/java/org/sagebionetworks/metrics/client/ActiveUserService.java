package org.sagebionetworks.metrics.client;

import org.sagebionetworks.metrics.client.widget.RowTable.Row;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("activeUsers")
public interface ActiveUserService extends RemoteService {

    Row[] getActiveUsers(int window);
    
}
