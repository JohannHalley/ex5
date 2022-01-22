package de.rwth.swc.oosc.group14;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import de.rwth.swc.oosc.group14.AppLabels;
import org.jhotdraw.annotation.Nullable;
import org.jhotdraw.app.Application;
import org.jhotdraw.app.ApplicationLabels;
import org.jhotdraw.app.View;
import org.jhotdraw.app.action.AbstractViewAction;
import org.jhotdraw.util.ResourceBundleUtil;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class PublishAction extends AbstractViewAction {
    private static final long serialVersionUID = 1L;
    public static final String ID = "file.publish";
    private Component oldFocusOwner;


    public PublishAction(Application app, @Nullable View view) {
        super(app, view);
        ResourceBundleUtil labels = AppLabels.getLabels();
        labels.configureAction(this, "file.publish");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //todo click action
        InputStream is = null;
        OutputStream os = null;
//        BufferedReader br = nu
        HttpURLConnection connection = null;
        try {

            //创建连接对象
            URL url = new URL("http://localhost:8080/api/v1/floorplans");
            //创建连接
            connection = (HttpURLConnection) url.openConnection();
            //设置请求方法
            connection.setRequestMethod("POST");
            //设置连接超时时间
            connection.setConnectTimeout(15000);
            //设置读取超时时间
            connection.setReadTimeout(15000);
            //DoOutput设置是否向httpUrlConnection输出，DoInput设置是否从httpUrlConnection读入，此外发送post请求必须设置这两个
            //设置是否可读取
            connection.setDoOutput(true);
            connection.setDoInput(true);
            //设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            connection.setRequestProperty("Content-Type", "application/json");

            Image image = new Image(1, new URL("https:www.baidu.com"));

            os = connection.getOutputStream();

            ObjectMapper mapper = new ObjectMapper();
            JavaTimeModule javaTimeModule=new JavaTimeModule();
            // Hack time module to allow 'Z' at the end of string (i.e. javascript json's)
            javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME));
            mapper.registerModule(javaTimeModule);
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
//            System.out.println(mapper.writeValueAsString(image));
            os.write(mapper.writeValueAsString(image).getBytes());
            if (connection.getResponseCode() == 200) {
                System.out.println("success");
            }


        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (ProtocolException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } finally {
            //关闭连接

            if(os!=null){
                try {
                    os.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if(is!=null){
                try {
                    is.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            //关闭连接
            connection.disconnect();
        }

    }


//
//    public void actionPerformed(ActionEvent evt) {
//        final View view = this.getActiveView();
//        if (view.isEnabled()) {
//            ResourceBundleUtil labels = ApplicationLabels.getLabels();
//            this.oldFocusOwner = SwingUtilities.getWindowAncestor(view.getComponent()).getFocusOwner();
//            view.setEnabled(false);
//
//            try {
//                URIChooser fileChooser = this.getApplication().getExportChooser(view);
//                if (this.proposeFileName) {
//                    URI proposedURI = view.getURI();
//                    if (proposedURI != null) {
//                        try {
//                            URI selectedURI = fileChooser.getSelectedURI();
//                            File selectedFolder;
//                            if (selectedURI == null) {
//                                Preferences prefs = Preferences.userNodeForPackage(this.getApplication().getModel().getClass());
//
//                                try {
//                                    selectedURI = new URI(prefs.get("recentExportFile", (new File(proposedURI)).getParentFile().toURI().toString()));
//                                    selectedFolder = (new File(selectedURI)).getParentFile();
//                                } catch (URISyntaxException var11) {
//                                    selectedFolder = (new File(proposedURI)).getParentFile();
//                                }
//                            } else {
//                                selectedFolder = (new File(selectedURI)).getParentFile();
//                            }
//
//                            File file = new File(selectedFolder, (new File(proposedURI)).getName());
//                            String name = file.getName();
//                            int p = name.lastIndexOf(46);
//                            if (p != -1) {
//                                name = name.substring(0, p);
//                                file = new File(selectedFolder, name);
//                                proposedURI = file.toURI();
//                            }
//                        } catch (IllegalArgumentException var12) {
//                        }
//                    }
//
//                    fileChooser.setSelectedURI(proposedURI);
//                }
//
//                JSheet.showSheet(fileChooser, view.getComponent(), labels.getString("filechooser.export"), new SheetListener() {
//                    public void optionSelected(SheetEvent evt) {
//                        if (evt.getOption() == 0) {
//                            URI uri = evt.getChooser().getSelectedURI();
//                            if (evt.getChooser() instanceof JFileURIChooser && evt.getFileChooser().getFileFilter() instanceof ExtensionFileFilter) {
//                                uri = ((ExtensionFileFilter)evt.getFileChooser().getFileFilter()).makeAcceptable(evt.getFileChooser().getSelectedFile()).toURI();
//                            } else {
//                                uri = evt.getChooser().getSelectedURI();
//                            }
//
//                            Preferences prefs = Preferences.userNodeForPackage(org.jhotdraw.app.action.file.ExportFileAction.this.getApplication().getModel().getClass());
//                            prefs.put("recentExportFile", uri.toString());
//                            if (evt.getChooser() instanceof JFileURIChooser) {
//                                org.jhotdraw.app.action.file.ExportFileAction.this.exportView(view, uri, evt.getChooser());
//                            } else {
//                                org.jhotdraw.app.action.file.ExportFileAction.this.exportView(view, uri, (URIChooser)null);
//                            }
//                        } else {
//                            view.setEnabled(true);
//                            if (org.jhotdraw.app.action.file.ExportFileAction.this.oldFocusOwner != null) {
//                                org.jhotdraw.app.action.file.ExportFileAction.this.oldFocusOwner.requestFocus();
//                            }
//                        }
//
//                    }
//                });
//            } catch (Error var13) {
//                view.setEnabled(true);
//                throw var13;
//            } catch (Throwable var14) {
//                view.setEnabled(true);
//                var14.printStackTrace();
//            }
//        }
//
//    }
//
//    protected void exportView(final View view, final URI uri, @Nullable final URIChooser chooser) {
//        view.execute(new BackgroundTask() {
//            protected void construct() throws IOException {
//                view.write(uri, chooser);
//            }
//
//            protected void failed(Throwable value) {
//                System.out.flush();
//                value.printStackTrace();
//                JComponent var10000 = view.getComponent();
//                String var10001 = UIManager.getString("OptionPane.css");
//                JSheet.showMessageSheet(var10000, "<html>" + var10001 + "<b>Couldn't export to the file \"" + URIUtil.getName(uri) + "\".<p>Reason: " + value, 0);
//            }
//
//            protected void finished() {
//                view.setEnabled(true);
//                SwingUtilities.getWindowAncestor(view.getComponent()).toFront();
//                if (org.jhotdraw.app.action.file.ExportFileAction.this.oldFocusOwner != null) {
//                    org.jhotdraw.app.action.file.ExportFileAction.this.oldFocusOwner.requestFocus();
//                }
//
//            }
//        });
//    }
}
