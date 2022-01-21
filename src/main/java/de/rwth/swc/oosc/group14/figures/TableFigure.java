package de.rwth.swc.oosc.group14.figures;

import de.rwth.swc.oosc.group14.AppLabels;

import static org.jhotdraw.draw.AttributeKeys.*;

public class TableFigure extends FurnitureFigure {
    public TableFigure() {
        this(AppLabels.getLabels().
                getString("TableFigure.defaultText"));
    }

    public TableFigure(String text) {
        setText(text);
    }

    public void setText(String newText) {
        set(TEXT, newText);
    }
}
