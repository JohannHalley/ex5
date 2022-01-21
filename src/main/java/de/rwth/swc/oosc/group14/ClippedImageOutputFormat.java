package de.rwth.swc.oosc.group14;

import org.jhotdraw.annotation.Nullable;
import org.jhotdraw.draw.Drawing;
import org.jhotdraw.draw.Figure;
import org.jhotdraw.draw.io.ImageOutputFormat;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

public class ClippedImageOutputFormat extends ImageOutputFormat {
    ClippedImageOutputFormat(){
        super();
    }

    public void write(OutputStream out, Drawing drawing, java.util.List<Figure> figures,
                      @Nullable AffineTransform drawingTransform, @Nullable Dimension imageSize) throws IOException {
        BufferedImage img;
        if (drawingTransform == null || imageSize == null) {
            // set clip to figures, I think this is a bug, but not 100% on that
            img = toImage(drawing, figures, 1d, true);
        } else {
            img = toImage(drawing, figures, drawingTransform, imageSize);
        }
        ImageIO.write(img, "PNG", out);
        img.flush();
    }
}
