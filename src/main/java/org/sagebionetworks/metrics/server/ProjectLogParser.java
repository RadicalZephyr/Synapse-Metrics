package org.sagebionetworks.metrics.server;

public class ProjectLogParser {

    
    public SynapseProjectData parseSynapseProjectData(String logLine){
        String[] logArray = logLine.split(",");

        SynapseProjectData projectData = new SynapseProjectData();
        projectData.setId(logArray[0]);
        projectData.setName(logArray[1]);
        projectData.setContributors(logArray[2].split(" "));
        projectData.setScore(Integer.parseInt(logArray[3]));

        return projectData;
    }
}
