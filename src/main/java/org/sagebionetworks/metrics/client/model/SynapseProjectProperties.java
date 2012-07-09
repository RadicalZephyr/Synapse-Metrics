package org.sagebionetworks.metrics.client.model;

import java.util.Collection;

import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface SynapseProjectProperties extends PropertyAccess<SynapseProject> {
/*    String id;
    String name;
    Collection<String> contributors;
    int score;*/
    
    ModelKeyProvider<SynapseProject> id();
    
    @Path("name")
    LabelProvider<SynapseProject> nameLabel();
    
    ValueProvider<SynapseProject, String> name();
    
    ValueProvider<SynapseProject, Collection<String>> contributors();
    
    ValueProvider<SynapseProject, Integer> score();
    
}
