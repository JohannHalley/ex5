package de.rwth.swc.oosc.group14.figures;

import de.rwth.swc.oosc.group14.AppLabels;
import org.jhotdraw.draw.TextAreaFigure;

import static org.jhotdraw.draw.AttributeKeys.TEXT;

public class OwnFigure extends FurnitureFigure {
    public OwnFigure() {
        this(AppLabels.getLabels().
                getString("createOwn.defaultText"));
    }

    public OwnFigure(String text) {
        setText(text);
    }

    public void setText(String newText) {
        set(TEXT, newText);
    }
}
