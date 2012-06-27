package org.sagebionetworks.metrics.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.sagebionetworks.metrics.client.model.SynapseProject;

public class ProjectLogParser {

    public static Collection<SynapseProject> parseSynapseProjectFile(File file) throws IOException {
        FileInputStream inStream = new FileInputStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));

        List<SynapseProject> dataList = new ArrayList<SynapseProject>();
        try {
            String line = reader.readLine();
            while (line!=null) {
                SynapseProject data = parseSynapseProjectData(line);
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
    
    public static SynapseProject parseSynapseProjectData(String logLine){
        SynapseProject projectData = null;
        String[] logArray = logLine.split(",");

        if (logArray.length == 4) {
            projectData = new SynapseProject();
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
