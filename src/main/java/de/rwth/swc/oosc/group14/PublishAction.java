package de.rwth.swc.oosc.group14;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import de.rwth.swc.oosc.group14.util.CircuitBreaker;
import de.rwth.swc.oosc.group14.util.CircuitBreakerConfig;
import org.jhotdraw.annotation.Nullable;
import org.jhotdraw.app.Application;
import org.jhotdraw.app.View;
import org.jhotdraw.app.action.AbstractViewAction;
import org.jhotdraw.util.ResourceBundleUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
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
    CircuitBreaker breaker = new CircuitBreaker("publishCircuitBreaker", CircuitBreakerConfig.newDefault());


    public PublishAction(Application app, @Nullable View view) {
        super(app, view);
        ResourceBundleUtil labels = AppLabels.getLabels();
        labels.configureAction(this, "file.publish");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(breaker.isOpen()){
            // check if into half-open state
            if(breaker.isOpen2HalfOpenTimeout()){
                // set half-open state
                breaker.openHalf();
            }else{
                //TODO show info window
                JOptionPane.showMessageDialog(null,"Time out!","Error", JOptionPane.WARNING_MESSAGE);	//消息对话框
            }
        }else if(breaker.isClosed()){
            task();
        }else if(breaker.isHalfOpen()){
            task();
            if(true){
                breaker.close();
            } else {
                breaker.open();
            }
        }

    }

    private void task() {
        InputStream is = null;
        OutputStream os = null;
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

            javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ISO_DATE_TIME));
            mapper.registerModule(javaTimeModule);
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

            os.write(mapper.writeValueAsString(image).getBytes());
            if (connection.getResponseCode() == 200) {
                System.out.println("success");
            } else {
                breaker.open();
                Thread.sleep(5000);
                breaker.incrFailCount();
                if(breaker.isCloseFailThresholdReached()){
                    breaker.open();
                }

                if (connection.getResponseCode() == 200) {
                    System.out.println("success");
                } else {
                    Thread.sleep(5000);
                    if (connection.getResponseCode() == 200) {
                        System.out.println("success");
                    } else{
                        //TODO show info window
                        JOptionPane.showMessageDialog(null,"Time out!","Error", JOptionPane.WARNING_MESSAGE);	//消息对话框
                    }
                }
            }


        } catch (MalformedURLException e1) {
            e1.printStackTrace();
        } catch (ProtocolException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
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

}
