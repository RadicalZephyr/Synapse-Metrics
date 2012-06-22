package org.sagebionetworks.metrics.client;

import org.sagebionetworks.metrics.client.widget.RowTable;
import static org.sagebionetworks.metrics.client.widget.RowTable.Row;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Metrics implements EntryPoint {

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        Row[] ucscData = new Row[] {new Row("jlong", "14-May-2012"),
                                    new Row("kellrott", "10-May-2012"),
                                    new Row("elinor", "10-May-2012"),
                                    new Row("ted", "01-May-2012")};

        Row[] sageData = new Row[] {new Row("chris.gaiteri", "18-May-2012"),
                                    new Row("xudong.dai", "16-Apr-2012"),
                                    new Row("charles.ferte", "27-Mar-2012"),
                                    new Row("ben.sauerwine", "23-Apr-2012"),
                                    new Row("in.sock.jang", "15-Mar-2012"),
                                    new Row("erich.huang", "14-Mar-2012"),
                                    new Row("lara.mangravite", "15-Mar-2012")};

        RowTable ucscTable = new RowTable(2, "soe.ucsc.org -- 4 active users");
        RowTable sageTable = new RowTable(2, "sagebase.org -- 7 active users");

        Row header = new Row("User", "Last Sign-In");
        ucscTable.setHeadings(header);
        sageTable.setHeadings(header);

        ucscTable.setRows(ucscData);
        sageTable.setRows(sageData);

        Row[] projectData = new Row[] {new Row("Name","Score"),
                                       new Row("Metagenomics", "11"),
                                       new Row("Synapse Umbrella", "4")};
        RowTable projectTable = new RowTable(2, "Projects");

        projectTable.setHeadings(new Row("Name", "Score"));
        projectTable.setRows(projectData);

        final Panel panel = new HorizontalPanel();
        Panel userStatPanel = new VerticalPanel();

        userStatPanel.add(ucscTable);
        userStatPanel.add(sageTable);

        panel.add(userStatPanel);
        panel.add(projectTable);

        RootPanel.get("statTableContainer").add(panel);
    }

}
