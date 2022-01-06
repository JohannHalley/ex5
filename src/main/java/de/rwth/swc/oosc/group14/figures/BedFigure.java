package de.rwth.swc.oosc.group14.figures;

import de.rwth.swc.oosc.group14.AppLabels;
import org.jhotdraw.draw.GraphicalCompositeFigure;
import org.jhotdraw.draw.ListFigure;
import org.jhotdraw.draw.RectangleFigure;
import org.jhotdraw.draw.TextFigure;
import org.jhotdraw.draw.layouter.VerticalLayouter;
import org.jhotdraw.geom.Insets2D;
import org.jhotdraw.util.ResourceBundleUtil;

import static org.jhotdraw.draw.AttributeKeys.*;

public class BedFigure extends GraphicalCompositeFigure {
    public BedFigure() {
        super(new RectangleFigure());

        setLayouter(new VerticalLayouter());

        RectangleFigure nameCompartmentPF = new RectangleFigure();
        nameCompartmentPF.set(STROKE_COLOR, null);
        nameCompartmentPF.setAttributeEnabled(STROKE_COLOR, false);
        nameCompartmentPF.set(FILL_COLOR, null);
        nameCompartmentPF.setAttributeEnabled(FILL_COLOR, false);
        ListFigure nameCompartment = new ListFigure(nameCompartmentPF);
        ListFigure nameCompartment2 = new ListFigure(nameCompartmentPF);
        ListFigure nameCompartment3 = new ListFigure(nameCompartmentPF);
        ListFigure nameCompartment4 = new ListFigure(nameCompartmentPF);
        ListFigure nameCompartment5 = new ListFigure(nameCompartmentPF);
        ListFigure nameCompartment6 = new ListFigure(nameCompartmentPF);
        add(nameCompartment);
        add(nameCompartment2);
        add(nameCompartment3);
        add(nameCompartment4);
        add(nameCompartment5);
        add(nameCompartment6);

        Insets2D.Double insets = new Insets2D.Double(4, 8, 4, 8);
        nameCompartment.set(LAYOUT_INSETS, insets);

        TextFigure nameFigure;
        nameCompartment.add(nameFigure = new TextFigure());
        nameFigure.set(FONT_BOLD, true);
        nameFigure.setAttributeEnabled(FONT_BOLD, false);

        setAttributeEnabled(STROKE_DASHES, false);

        ResourceBundleUtil labels =
                AppLabels.getLabels();

        setName("Bed");

    }

    public void setName(String newValue) {
        getNameFigure().setText(newValue);
    }

    private TextFigure getNameFigure() {
        return (TextFigure) ((ListFigure) getChild(0)).getChild(0);
    }
}
