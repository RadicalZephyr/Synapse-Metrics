package org.sagebionetworks.metrics.client;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import org.sagebionetworks.metrics.client.model.SynapseProject;
import org.sagebionetworks.metrics.client.model.SynapseUser;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ActiveDataServiceImpl extends RemoteServiceServlet implements
        ActiveDataService {

    public Collection<SynapseUser> getActiveUsers() {
        Collection<SynapseUser> userList = new LinkedList<SynapseUser>();
        // Load fake data
        userList.add(new SynapseUser("geoff.shannon", "sagebase.org", new Date()));
        userList.add(new SynapseUser("bennett.ng", "sagebase.org", new Date()));
        userList.add(new SynapseUser("ted", "soe.ucsc.edu", new Date()));
        userList.add(new SynapseUser("kellrott", "soe.ucsc.edu", new Date()));

        return userList;
    }

    public Collection<SynapseProject> getActiveProjects() {
        return null;
    }

}
