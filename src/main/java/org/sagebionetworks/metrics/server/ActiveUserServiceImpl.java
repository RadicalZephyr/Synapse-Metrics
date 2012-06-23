package org.sagebionetworks.metrics.server;

import org.sagebionetworks.metrics.client.ActiveUserService;
import org.sagebionetworks.metrics.client.widget.RowTable.Row;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ActiveUserServiceImpl extends RemoteServiceServlet 
    implements ActiveUserService {

    public Row[] getActiveUsers(int window) {
        Row[] data;
        if (window == 1) {
            data = new Row[] {new Row("jlong", "14-May-2012"),
                              new Row("kellrott", "10-May-2012"),
                              new Row("elinor", "10-May-2012"),
                              new Row("ted", "01-May-2012")};
        } else if (window == 2) {
            data = new Row[] {new Row("chris.gaiteri", "18-May-2012"),
                              new Row("xudong.dai", "16-Apr-2012"),
                              new Row("charles.ferte", "27-Mar-2012"),
                              new Row("ben.sauerwine", "23-Apr-2012"),
                              new Row("in.sock.jang", "15-Mar-2012"),
                              new Row("erich.huang", "14-Mar-2012"),
                              new Row("lara.mangravite", "15-Mar-2012")};
        } else {
            data = new Row[] {new Row("Name","Score"),
                              new Row("Metagenomics", "11"),
                              new Row("Synapse Umbrella", "4")};
        }
        
        return data;
    }

}
