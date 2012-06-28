package org.sagebionetworks.metrics.client;

import java.util.Collection;

import org.sagebionetworks.metrics.client.model.SynapseProject;
import org.sagebionetworks.metrics.client.model.SynapseUser;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("activeData")
public interface ActiveDataService extends RemoteService {
    public Collection<SynapseUser> getActiveUsers();
    
    public Collection<SynapseProject> getActiveProjects();
}
