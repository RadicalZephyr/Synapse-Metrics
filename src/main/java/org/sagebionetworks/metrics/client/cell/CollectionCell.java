package org.sagebionetworks.metrics.client.cell;

import java.util.Collection;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

public class CollectionCell<T> extends AbstractCell<Collection<T>> {

    @Override
    public void render(com.google.gwt.cell.client.Cell.Context context,
            Collection<T> values, SafeHtmlBuilder sb) {

        if (values == null) {
            return;
        }
        String newline = "";
        for (T value : values) {
            sb.appendHtmlConstant(newline);
            sb.appendEscaped(value.toString());
            newline = "<br />";
        }
    }

}
