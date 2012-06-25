package org.sagebionetworks.metrics.server;

import org.sagebionetworks.metrics.client.widget.RowTable.Row;

public class SynapseProjectData {
    String id;
    String name;
    String[] contributors;
    int score;
    
    public Row asRow() {
        return new Row(id, name, contributorsAsString(), score);
    }
    
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
    public String[] getContributors() {
        return contributors;
    }
    public void setContributors(String[] contributors) {
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
