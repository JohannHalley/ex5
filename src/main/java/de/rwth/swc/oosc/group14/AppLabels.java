package de.rwth.swc.oosc.group14;

import org.jhotdraw.util.ResourceBundleUtil;

import java.util.ResourceBundle;

public class AppLabels {
    private AppLabels() {
    }
    public static ResourceBundleUtil getLabels() {
        ResourceBundleUtil labels = new ResourceBundleUtil(ResourceBundle.getBundle("de.rwth_aachen.swc.oosc.drawing_app.bundle"));
        labels.setBaseClass(AppLabels.class);
        return labels;
    }
}
