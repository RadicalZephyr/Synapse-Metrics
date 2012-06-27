package org.sagebionetworks.metrics.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.sagebionetworks.metrics.client.model.SynapseUser;
import org.sagebionetworks.metrics.client.model.SynapseUserProperties;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.GroupingView;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Metrics implements EntryPoint, IsWidget {

    public Widget asWidget() {
        SynapseUserProperties properties = GWT.create(SynapseUserProperties.class);
        ListStore<SynapseUser> store = new ListStore<SynapseUser>(properties.id());
        
        // Load fake data into the store
        store.add(new SynapseUser("geoff.shannon", "sagebase.org", new Date()));
        store.add(new SynapseUser("bennett.ng", "sagebase.org", new Date()));
        store.add(new SynapseUser("ted", "soe.ucsc.edu", new Date()));
        store.add(new SynapseUser("kellrott", "soe.ucsc.edu", new Date()));
        
        List<ColumnConfig<SynapseUser, ?>> cfgs = new ArrayList<ColumnConfig<SynapseUser, ?>>();
        ColumnConfig<SynapseUser, String> name = new ColumnConfig<SynapseUser, String>(properties.name(), 60);
        name.setHeader("Name");
        name.setCell(new TextCell());
        cfgs.add(name);

        final ColumnConfig<SynapseUser, String> domain = new ColumnConfig<SynapseUser, String>(properties.domain(), 60);
        domain.setHeader("domain");
        domain.setCell(new TextCell());
        cfgs.add(domain);
                
        final ColumnConfig<SynapseUser, Date> lastLogin= new ColumnConfig<SynapseUser, Date>(properties.lastLogin(), 60);
        lastLogin.setHeader("Last Login");
        lastLogin.setCell(new DateCell(DateTimeFormat.getFormat("MM/dd/yyyy")));
        cfgs.add(lastLogin);
        
        ColumnModel<SynapseUser> colModel = new ColumnModel<SynapseUser>(cfgs);
        
        final GroupingView<SynapseUser> view = new GroupingView<SynapseUser>();
        view.setShowGroupedColumn(false);
        view.setForceFit(true);
        
        Grid<SynapseUser> grid = new Grid<SynapseUser>(store, colModel);
        grid.setView(view);
        view.groupBy(domain);
        
        ContentPanel panel = new ContentPanel();
        panel.setHeadingHtml("Active Users");
        panel.setSize("700", "450");
        panel.add(grid);
        panel.addStyleName("margin-10");
        panel.setCollapsible(false);
        return panel;
    }
    
    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        RootPanel.get().add(this);
    }

}
