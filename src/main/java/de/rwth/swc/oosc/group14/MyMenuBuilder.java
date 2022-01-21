package de.rwth.swc.oosc.group14;

import org.jhotdraw.annotation.Nullable;
import org.jhotdraw.app.Application;
import org.jhotdraw.app.DefaultMenuBuilder;
import org.jhotdraw.app.MenuBuilder;
import org.jhotdraw.app.View;
import org.jhotdraw.util.ResourceBundleUtil;

import javax.swing.*;

public class MyMenuBuilder extends DefaultMenuBuilder {
    @Override
    public void addOtherFileItems(JMenu m, Application app, @Nullable View v) {
        ActionMap am = app.getActionMap(v);
        Action a;


        if (null != (a = am.get("file.publish"))) {
            this.add(m, a);
        }
    }
}
