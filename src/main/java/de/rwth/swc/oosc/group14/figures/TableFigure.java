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

public class TableFigure extends GraphicalCompositeFigure {
    public TableFigure() {
        super(new RectangleFigure());

        setLayouter(new VerticalLayouter());

        RectangleFigure nameCompartmentPF = new RectangleFigure();
        nameCompartmentPF.set(STROKE_COLOR, null);
        nameCompartmentPF.setAttributeEnabled(STROKE_COLOR, false);
        nameCompartmentPF.set(FILL_COLOR, null);
        nameCompartmentPF.setAttributeEnabled(FILL_COLOR, false);
        ListFigure nameCompartment = new ListFigure(nameCompartmentPF);

        add(nameCompartment);

//        Insets2D.Double insets = new Insets2D.Double(4, 8, 4, 8);
//        nameCompartment.set(LAYOUT_INSETS, insets);
        nameCompartment.setAttributeEnabled(LAYOUT_INSETS, false);
        TextFigure nameFigure;
        nameCompartment.add(nameFigure = new TextFigure());
        nameFigure.set(FONT_BOLD, true);
        nameFigure.setAttributeEnabled(FONT_BOLD, false);

        setAttributeEnabled(STROKE_DASHES, false);

        ResourceBundleUtil labels =
                AppLabels.getLabels();

        setName("Table");

    }

    public void setName(String newValue) {
        getNameFigure().setText(newValue);
    }

    private TextFigure getNameFigure() {
        return (TextFigure) ((ListFigure) getChild(0)).getChild(0);
    }
    public TableFigure(String text) {
        setText(text);
    }

    public void setText(String newText) {
        set(TEXT, newText);
    }
}
