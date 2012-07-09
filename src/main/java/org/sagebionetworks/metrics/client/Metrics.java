package org.sagebionetworks.metrics.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.sagebionetworks.metrics.client.cell.CollectionCell;
import org.sagebionetworks.metrics.client.model.SynapseProject;
import org.sagebionetworks.metrics.client.model.SynapseProjectProperties;
import org.sagebionetworks.metrics.client.model.SynapseUser;
import org.sagebionetworks.metrics.client.model.SynapseUserProperties;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.cell.core.client.NumberCell;
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

    private ActiveDataServiceAsync activeDataSvc;

    public Widget asWidget() {
        Grid<SynapseUser> userGrid = createUserGrid();
        Grid<SynapseProject> projectGrid = createProjectGrid();

        ContentPanel userPanel = new ContentPanel();
        userPanel.setHeadingHtml("Active Users");
        userPanel.setSize("700", "450");
        userPanel.add(userGrid);
        userPanel.addStyleName("margin-10");
        userPanel.setCollapsible(false);

        ContentPanel projectPanel = new ContentPanel();
        projectPanel.setHeadingHtml("Active Projects");
        projectPanel.setSize("600", "400");
        projectPanel.add(projectGrid);
        projectPanel.addStyleName("margin-10");
        projectPanel.setCollapsible(false);

        HorizontalPanel panel = new HorizontalPanel();
        panel.add(userPanel);
        panel.add(projectPanel);
        return panel;
    }

    public Grid<SynapseUser> createUserGrid() {
        SynapseUserProperties properties = GWT.create(SynapseUserProperties.class);
        ListStore<SynapseUser> store = new ListStore<SynapseUser>(properties.id());

        refreshActiveUserData(store);

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
        return grid;
    }

    public Grid<SynapseProject> createProjectGrid() {
        SynapseProjectProperties properties = GWT.create(SynapseProjectProperties.class);
        ListStore<SynapseProject> store = new ListStore<SynapseProject>(properties.id());

        refreshActiveProjectData(store);

        ArrayList<ColumnConfig<SynapseProject, ?>> cfgs = new ArrayList<ColumnConfig<SynapseProject, ?>>();

        ColumnConfig<SynapseProject, String> name = new ColumnConfig<SynapseProject, String>(properties.name(), 60);
        name.setHeader("Project");
        name.setCell(new TextCell());
        cfgs.add(name);

        ColumnConfig<SynapseProject, Collection<String>> contributors = new ColumnConfig<SynapseProject, Collection<String>>(properties.contributors(), 60);
        contributors.setHeader("Contributors");
        contributors.setCell(new CollectionCell<String>());
        cfgs.add(contributors);

        ColumnConfig<SynapseProject, Integer> score = new ColumnConfig<SynapseProject, Integer>(properties.score(), 60);
        score.setHeader("Score");
        score.setCell(new NumberCell<Integer>());
        cfgs.add(score);

        ColumnModel<SynapseProject> colModel = new ColumnModel<SynapseProject>(cfgs);

        Grid<SynapseProject> grid = new Grid<SynapseProject>(store, colModel);

        grid.getView().setStripeRows(true);
        grid.getView().setColumnLines(true);
        grid.getView().setAutoFill(true);
        return grid;
    }
    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        RootPanel.get().add(this);
    }

    private void refreshActiveUserData(final ListStore<SynapseUser> store) {
        activateDataService();

        AsyncCallback<Collection<SynapseUser>> callback = new AsyncCallback<Collection<SynapseUser>>() {

            public void onFailure(Throwable caught) {
                // TODO Auto-generated method stub
            }

            public void onSuccess(Collection<SynapseUser> result) {
                store.addAll(result);
            }
        };

        activeDataSvc.getActiveUsers(callback);
    }

    private void refreshActiveProjectData(final ListStore<SynapseProject> store) {
        activateDataService();

        AsyncCallback<Collection<SynapseProject>> callback = new AsyncCallback<Collection<SynapseProject>>() {

            public void onFailure(Throwable caught) {
            }

            public void onSuccess(Collection<SynapseProject> result) {
                store.addAll(result);
            }
        };

        activeDataSvc.getActiveProjects(callback);
    }

    public void activateDataService() {
        if (activeDataSvc == null) {
             activeDataSvc = GWT.create(ActiveDataService.class);
        }
    }
}
