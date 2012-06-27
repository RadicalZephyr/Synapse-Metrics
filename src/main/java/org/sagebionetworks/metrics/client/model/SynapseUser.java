package org.sagebionetworks.metrics.client.model;

import java.io.Serializable;
import java.util.Date;

public class SynapseUser implements Serializable {
    private Integer id;
    private String name;
    private String domain;
    private Date lastLogin;

    private static int COUNTER = 0;
    
    public SynapseUser() {
        this.id = Integer.valueOf(COUNTER++);
    }
    
    public SynapseUser(String name, String domain, Date lastLogin) {
        this();
        this.name = name;
        this.domain = domain;
        this.lastLogin = lastLogin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDomain() {
        return domain;
    }
    public void setDomain(String domain) {
        this.domain = domain;
    }
    public Date getLastLogin() {
        return lastLogin;
    }
    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }
}

