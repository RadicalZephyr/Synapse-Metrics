package org.sagebionetworks.metrics.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProjectLogParser {

    public static Collection<SynapseProjectData> parseSynapseProjectFile(File file) throws IOException {
        FileInputStream inStream = new FileInputStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));

        List<SynapseProjectData> dataList = new ArrayList<SynapseProjectData>();
        try {
            String line = reader.readLine();
            while (line!=null) {
                SynapseProjectData data = parseSynapseProjectData(line);
                if (data != null) {
                    dataList.add(data);
                }
                line = reader.readLine();
            }
        } finally {
            reader.close();
            inStream.close();

        }

        return dataList;
    }
    
    public static SynapseProjectData parseSynapseProjectData(String logLine){
        SynapseProjectData projectData = null;
        String[] logArray = logLine.split(",");

        if (logArray.length == 4) {
            projectData = new SynapseProjectData();
            projectData.setId(logArray[0]);
            projectData.setName(logArray[1]);
            ArrayList<String> contributors = new ArrayList<String>();
            
            for (String contributor : logArray[2].split(" ")) {
                contributors.add(contributor);
            }
            
            projectData.setContributors(contributors);
            projectData.setScore(Integer.parseInt(logArray[3]));
        }

        return projectData;
    }
}
