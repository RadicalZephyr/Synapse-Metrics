package org.sagebionetworks.metrics.server;

import java.io.Serializable;
import java.util.Collection;

public class SynapseProjectData implements Serializable {
    String id;
    String name;
    Collection<String> contributors;
    int score;
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Collection<String> getContributors() {
        return contributors;
    }
    public void setContributors(Collection<String> contributors) {
        this.contributors = contributors;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    
    public String contributorsAsString() {
        StringBuilder sb = new StringBuilder();
        String newline = "";

        for (String name : contributors) {
            sb.append(newline).append(name);
            newline = "\n";
        }

        return sb.toString();
    }
}
