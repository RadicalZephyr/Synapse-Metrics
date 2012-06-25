package org.sagebionetworks.metrics.client;

import org.sagebionetworks.metrics.client.widget.RowTable.Row;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("activeUsers")
public interface TableDataService extends RemoteService {

    Row[] getActiveUsers(String domain);
    
    Row[] getActiveProjects();
}
