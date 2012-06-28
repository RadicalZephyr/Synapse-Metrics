package org.sagebionetworks.metrics.client;

import java.util.Collection;

import org.sagebionetworks.metrics.client.model.SynapseProject;
import org.sagebionetworks.metrics.client.model.SynapseUser;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ActiveDataServiceAsync {

    public void getActiveProjects(AsyncCallback<Collection<SynapseProject>> callback);

    public void getActiveUsers(AsyncCallback<Collection<SynapseUser>> callback);

}
