package de.rwth.swc.oosc.group14.figures;

import org.jhotdraw.draw.AttributeKeys;
import org.jhotdraw.draw.LineFigure;
import org.jhotdraw.geom.GrowStroke;

import java.awt.*;

public class WallFigure extends LineFigure {
    protected void drawStroke(Graphics2D g) {
        g.setStroke(new BasicStroke(5.0f));
        if (this.isClosed()) {
            double grow = AttributeKeys.getPerpendicularDrawGrowth(this);
            if (grow == 0.0D) {
                g.draw(this.path);
            } else {
                GrowStroke gs = new GrowStroke(grow, AttributeKeys.getStrokeTotalWidth(this) * (java.lang.Double)this.get(AttributeKeys.STROKE_MITER_LIMIT));
                g.draw(gs.createStrokedShape(this.path));
            }
        } else {
            g.draw(this.getCappedPath());
        }

        this.drawCaps(g);
    }
}
