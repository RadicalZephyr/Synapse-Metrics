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

import org.sagebionetworks.metrics.client.ActiveUserService;
import org.sagebionetworks.metrics.client.widget.RowTable.Row;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ActiveUserServiceImpl extends RemoteServiceServlet 
    implements ActiveUserService {
    
    static final private String CROWD_LOG_DIRECTORY_PATH = "../../../data/crowdlogs/";

    private Map<String, Collection<Row>> domainRows = new HashMap<String, Collection<Row>>();
    
    public ActiveUserServiceImpl() {
        try {
            runLogParser(100);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public Row[] getActiveUsers(String domain) {
        Row[] data = new Row[] {};
        if (domain.equalsIgnoreCase("projects")) {
            data = new Row[] {new Row("Name","Score"),
                              new Row("Metagenomics", "11"),
                              new Row("Synapse Umbrella", "4")};
        } else {
            data = domainRows.get(domain).toArray(data);
        }

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
