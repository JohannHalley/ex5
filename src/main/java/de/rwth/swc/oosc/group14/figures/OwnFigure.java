package de.rwth.swc.oosc.group14.figures;

import de.rwth.swc.oosc.group14.AppLabels;

import static org.jhotdraw.draw.AttributeKeys.TEXT;

//TODO not implement
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