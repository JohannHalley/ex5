package de.rwth.swc.oosc.group14.figures;

import de.rwth.swc.oosc.group14.AppLabels;
import org.jhotdraw.draw.*;
import org.jhotdraw.draw.connector.LocatorConnector;
import org.jhotdraw.draw.event.FigureAdapter;
import org.jhotdraw.draw.event.FigureEvent;
import org.jhotdraw.draw.handle.BoundsOutlineHandle;
import org.jhotdraw.draw.handle.ConnectorHandle;
import org.jhotdraw.draw.handle.Handle;
import org.jhotdraw.draw.handle.MoveHandle;
import org.jhotdraw.draw.layouter.VerticalLayouter;
import org.jhotdraw.draw.locator.RelativeLocator;
import org.jhotdraw.geom.Insets2D;
import org.jhotdraw.util.ResourceBundleUtil;

import java.awt.*;
import java.util.*;

import static org.jhotdraw.draw.AttributeKeys.*;

public class TableFigure extends RectangleFigure{
    public TableFigure() {
        this("Table");
    }

    public TableFigure(String text) {
        setText(text);
    }

    @Override
    protected void drawText(Graphics2D g) {
        super.drawText(g);
        g.drawString("Table", 10, 20);
    }

    public void setText(String newText) {
        set(TEXT, newText);
    }
}