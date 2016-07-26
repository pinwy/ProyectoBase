package com.base.luiss.proyectobase.Util;

import android.content.Context;
import android.widget.Toast;

import com.parse.signpost.http.HttpResponse;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by luiss on 22/07/2016.
 */
public class general {
    public void mostrarMensaje(Context context, String sMensaje, int iDuracion){
        Toast.makeText(context,sMensaje,iDuracion).show();
    }


    /**
     * Funcion que se conecta al webservice y regrasa un valor string
     * esta funcion remplazara a cualquiera que tome info del webservice
     * @param sUrl contiene la direccion completa a la que se va conectar
     * @param sNameSpace contiene el espacio de nombres Ejem. http://tempuri.org/
     * @param sMetodo contiene el metodo del webservice al que se va accesar
     * @return sStatus regresa el valor obtenido desde el webservice
     */
    public String getDatoWebService(String sUrl,String sNameSpace,String sMetodo)
    {
        System.out.println("Entro a getDatoWebService");

        String sStatus;
        try {

            SoapObject request = new SoapObject(sNameSpace, sMetodo);
            //request.addProperty("sLed", "b");
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.implicitTypes = true;
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(sUrl);
            String sTexto = "http://tempuri.org/" + sMetodo;
            androidHttpTransport.call(sTexto, envelope);
            Object response = envelope.getResponse();
            sStatus = response.toString();

        } catch (Exception e) {
            e.printStackTrace();
            sStatus = "-1"; //error
        }

        return sStatus;
    }
}
