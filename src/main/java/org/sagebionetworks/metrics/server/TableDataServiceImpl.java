package org.sagebionetworks.metrics.server;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.sagebionetworks.metrics.client.TableDataService;
import org.sagebionetworks.metrics.client.widget.RowTable.Row;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class TableDataServiceImpl extends RemoteServiceServlet 
    implements TableDataService {
    
    private static final String CROWD_LOG_DIRECTORY_PATH = "../../../data/crowdlogs/";
    private static final String PROJECT_LOG_FILE_PATH = "../../../data/activityDump.txt";

    private Map<String, Collection<Row>> domainRows = new HashMap<String, Collection<Row>>();
    
    public TableDataServiceImpl() {
        try {
            runLogParser(100);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    
    public Row[] getActiveUsers(String domain) {
        Row[] data = new Row[] {};
        data = domainRows.get(domain).toArray(data);
        return data;
    }

    public Row[] getActiveProjects() {
        Row[] data = new Row[] {new Row("Name","Score"),
                                new Row("Metagenomics", "11"),
                                new Row("Synapse Umbrella", "4")};

        return data;
    }

    private void runLogParser(int window) throws IOException, ParseException {
        Date endDate = new Date();
        Date startDate = new Date(endDate.getTime()-window*24*60*60*1000L);

        Map<String, Collection<Long>> userDays = CrowdLogParser.parseAuthenticationEvents(new File(CROWD_LOG_DIRECTORY_PATH), startDate.getTime(), endDate.getTime());
        Map<String, Collection<String>> frequentOutsideUsers = CrowdLogParser.getFrequentOutsideUsers(userDays);

        for (String domain : frequentOutsideUsers.keySet()) {
            Collection<Row> rows = createRows(frequentOutsideUsers.get(domain),
                                              userDays);
            domainRows.put(domain, rows);
        }
    }
    
    private Collection<Row> createRows(Collection<String> names, Map<String, Collection<Long>> userDays) {
        DateFormat df = new SimpleDateFormat(CrowdLogParser.INPUT_DATE_TIME_FORMAT);

        Collection<Row> rows = new LinkedList<Row>();
        for (String name : names) {
            Collection<Long> days = userDays.get(name);

            Row row = new Row(name.split("@")[0], df.format(days.iterator().next()));
            rows.add(row);
        }
        return rows;
    }

}
