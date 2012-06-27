package org.sagebionetworks.metrics.client.model;

import java.util.Date;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface SynapseUserProperties extends PropertyAccess<SynapseUser> {

    ModelKeyProvider<SynapseUser> id();
    
    @Path("name")
    LabelProvider<SynapseUser> nameLabel();
    
    ValueProvider<SynapseUser, String> name();
    
    ValueProvider<SynapseUser, String> domain();
    
    ValueProvider<SynapseUser, Date> lastLogin();
}
