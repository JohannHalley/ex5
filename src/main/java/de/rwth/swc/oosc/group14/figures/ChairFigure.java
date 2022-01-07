package de.rwth.swc.oosc.group14.figures;

import de.rwth.swc.oosc.group14.AppLabels;
import org.jhotdraw.draw.*;
import org.jhotdraw.draw.layouter.VerticalLayouter;
import org.jhotdraw.geom.Insets2D;
import org.jhotdraw.util.ResourceBundleUtil;

import static org.jhotdraw.draw.AttributeKeys.*;

public class ChairFigure extends FurnitureFigure {

    public ChairFigure() {
        this(AppLabels.getLabels().
                getString("ChairFigure.defaultText"));
    }

    public ChairFigure(String text) {
        setText(text);
    }

    public void setText(String newText) {
        set(TEXT, newText);
    }
}
