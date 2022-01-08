/* @(#)DrawApplicationModel.java
 * Copyright © The authors and contributors of JHotDraw. MIT License.
 */
package de.rwth.swc.oosc.group14;

import de.rwth.swc.oosc.group14.figures.*;
import org.jhotdraw.app.Application;
import org.jhotdraw.app.ApplicationModel;
import org.jhotdraw.app.DefaultApplicationModel;
import org.jhotdraw.app.View;
import org.jhotdraw.app.action.edit.ClearSelectionAction;
import org.jhotdraw.app.action.file.ExportFileAction;
import org.jhotdraw.draw.*;
import org.jhotdraw.draw.action.ButtonFactory;
import org.jhotdraw.draw.decoration.ArrowTip;
import org.jhotdraw.draw.liner.CurvedLiner;
import org.jhotdraw.draw.liner.ElbowLiner;
import org.jhotdraw.draw.tool.BezierTool;
import org.jhotdraw.draw.tool.ConnectionTool;
import org.jhotdraw.draw.tool.CreationTool;
import org.jhotdraw.draw.tool.ImageTool;
import org.jhotdraw.draw.tool.TextAreaCreationTool;
import org.jhotdraw.draw.tool.TextCreationTool;
import org.jhotdraw.gui.JFileURIChooser;
import org.jhotdraw.gui.URIChooser;
import org.jhotdraw.gui.filechooser.ExtensionFileFilter;
import org.jhotdraw.util.ResourceBundleUtil;

import org.jhotdraw.annotation.Nullable;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import static org.jhotdraw.draw.AttributeKeys.END_DECORATION;

/**
 * Provides factory methods for creating views, menu bars and toolbars.
 * <p>
 * See {@link ApplicationModel} on how this class interacts with an application.
 * 
 * @author Werner Randelshofer.
 * @version $Id$
 */
public class DrawApplicationModel extends DefaultApplicationModel {
    private static final long serialVersionUID = 1L;

    /**
     * This editor is shared by all views.
     */
    private DefaultDrawingEditor sharedEditor;

    //todo for reasonable resize check
    private int backgroundWidth = 0;
    private int backgroundHeight = 0;

    /** Creates a new instance. */
    public DrawApplicationModel() {
    }

    public DefaultDrawingEditor getSharedEditor() {
        if (sharedEditor == null) {
            sharedEditor = new DefaultDrawingEditor();
        }
        return sharedEditor;
    }

    @Override
    public void initView(Application a,View p) {
        if (a.isSharingToolsAmongViews()) {
            ((DrawView) p).setEditor(getSharedEditor());
        }
    }

    /**
     * Creates toolbars for the application.
     * This class always returns an empty list. Subclasses may return other
     * values.
     */
    @Override
    public List<JToolBar> createToolBars(Application a, @Nullable View pr) {
        ResourceBundleUtil labels = DrawLabels.getLabels();
        DrawView p = (DrawView) pr;

        DrawingEditor editor;
        if (p == null) {
            editor = getSharedEditor();
        } else {
            editor = p.getEditor();
        }

        LinkedList<JToolBar> list = new LinkedList<JToolBar>();
        JToolBar tb;
        tb = new JToolBar();
        addCreationButtonsTo(tb, editor);
        tb.setName(labels.getString("window.drawToolBar.title"));
        list.add(tb);
//        tb = new JToolBar();
//        addMyButtonsTo(tb, editor);
//        tb.setName("myButton");
//        list.add(tb);

        //TODO toolbar for furniture
        tb = new JToolBar();

        list.add(tb);

        tb = new JToolBar();
        ButtonFactory.addAlignmentButtonsTo(tb, editor);
        tb.setName(labels.getString("window.alignmentToolBar.title"));
        list.add(tb);
        return list;
    }

    private void addMyButtonsTo(JToolBar tb, final DrawingEditor editor,
                                Collection<Action> drawingActions, Collection<Action> selectionActions) {
        ResourceBundleUtil myLabels = AppLabels.getLabels();
        ResourceBundleUtil labels = DrawLabels.getLabels();
        HashMap<AttributeKey<?>, Object> attributes;
        ButtonFactory.addSelectionToolTo(tb, editor, drawingActions, selectionActions);
        tb.addSeparator();

        AbstractAttributedFigure af;
        CreationTool ct;
        ConnectionTool cnt;
        ConnectionFigure lc;

        ButtonFactory.addToolTo(tb, editor, new CreationTool(new RectangleFigure()), "edit.createRectangle", labels);
        ButtonFactory.addToolTo(tb, editor, new CreationTool(new RoundRectangleFigure()), "edit.createRoundRectangle", labels);
        ButtonFactory.addToolTo(tb, editor, new CreationTool(new EllipseFigure()), "edit.createEllipse", labels);
        ButtonFactory.addToolTo(tb, editor, new CreationTool(new DiamondFigure()), "edit.createDiamond", labels);
        ButtonFactory.addToolTo(tb, editor, new CreationTool(new TriangleFigure()), "edit.createTriangle", labels);
        ButtonFactory.addToolTo(tb, editor, new CreationTool(new LineFigure()), "edit.createLine", labels);
        ButtonFactory.addToolTo(tb, editor, new BezierTool(new BezierFigure()), "edit.createScribble", labels);
        ButtonFactory.addToolTo(tb, editor, new BezierTool(new BezierFigure(true)), "edit.createPolygon", labels);
        tb.addSeparator();

        ButtonFactory.addToolTo(tb, editor, new ImageTool(new ImportSketchFigure()), "edit.importSketch", myLabels);
        ButtonFactory.addToolTo(tb, editor, new CreationTool(new RectangleFigure()), "edit.createFloor", myLabels);
        ButtonFactory.addToolTo(tb, editor, new CreationTool(new WallFigure()), "edit.createWall", myLabels);

        attributes = new HashMap<AttributeKey<?>, Object>();
        attributes.put(AttributeKeys.FILL_COLOR, Color.white);
        ButtonFactory.addToolTo(tb, editor, new CreationTool(new WindowFigure(), attributes), "edit.createWindow", myLabels);

        attributes = new HashMap<AttributeKey<?>, Object>();
        attributes.put(AttributeKeys.FILL_COLOR, Color.black);
        ButtonFactory.addToolTo(tb, editor, new CreationTool(new DoorFigure(), attributes), "edit.createDoor", myLabels);
        tb.addSeparator();

        attributes = new HashMap<AttributeKey<?>, Object>();
        ButtonFactory.addToolTo(tb, editor, new CreationTool(new TableFigure(), attributes), "edit.createTable", myLabels);

        attributes = new HashMap<AttributeKey<?>, Object>();
        ButtonFactory.addToolTo(tb, editor, new CreationTool(new ChairFigure(), attributes), "edit.createChair", myLabels);

        ButtonFactory.addToolTo(tb, editor, new CreationTool(new BedFigure(), attributes), "edit.createBed", myLabels);

        ButtonFactory.addToolTo(tb, editor, new CreationTool(new PlantFigure(), attributes), "edit.createPlant", myLabels);

        ButtonFactory.addToolTo(tb, editor, new CreationTool(new BathtubFigure(), attributes), "edit.createBathtub", myLabels);
        tb.addSeparator();

        ButtonFactory.addToolTo(tb, editor, new TextAreaCreationTool(new OwnFigure()), "edit.createOwn", myLabels);

        ButtonFactory.addToolTo(tb, editor, new ImageTool(new ImageFigure()), "edit.importOwn", myLabels);
    }

    private void addCreationButtonsTo(JToolBar tb, DrawingEditor editor) {
        addMyButtonsTo(tb, editor,
                ButtonFactory.createDrawingActions(editor),
                ButtonFactory.createSelectionActions(editor));
    }

    @Override
    public URIChooser createOpenChooser(Application a, @Nullable View v) {
        JFileURIChooser c = new JFileURIChooser();
        c.addChoosableFileFilter(new ExtensionFileFilter("Drawing .xml","xml"));
        return c;
    }

    @Override
    public URIChooser createSaveChooser(Application a, @Nullable View v) {
        JFileURIChooser c = new JFileURIChooser();
        c.addChoosableFileFilter(new ExtensionFileFilter("Drawing .xml","xml"));
        return c;
    }

    @Override
    public ActionMap createActionMap(Application a, @Nullable View v) {
        ActionMap m = super.createActionMap(a, v);
        m.put("file.export", new ExportFileAction(a, v));
        return m;
    }
}
