package com.base.luiss.proyectobase.Util;

import android.content.Context;
import android.widget.Toast;

import com.base.luiss.proyectobase.modelo.ciudades;
import com.base.luiss.proyectobase.modelo.currentWeather;
import com.parse.signpost.http.HttpResponse;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

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
            request.addProperty("CityName", "Culiacan");
            request.addProperty("CountryName", "Mexico");
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.implicitTypes = true;
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(sUrl);
            String sTexto = sNameSpace + "/" + sMetodo;
            androidHttpTransport.call(sTexto, envelope);
            Object response = envelope.getResponse();
            sStatus = response.toString();

            //DOM (Por ejemplo)
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = factory.newDocumentBuilder();
            Document dom = builder.parse(new InputSource(new StringReader(sStatus)));
            //Document dom = builder.parse(sStatus);

            //A partir de aquí se trataría el árbol DOM como siempre.
            //Por ejemplo:
            Element root = dom.getDocumentElement();
            currentWeather obj = new currentWeather();

            NodeList items = root.getElementsByTagName("Location");
            obj.setLocation(items.item(0).getFirstChild().getNodeValue());
            items = root.getElementsByTagName("Time");
            obj.setTime(items.item(0).getFirstChild().getNodeValue());
            items = root.getElementsByTagName("Wind");
            obj.setWind(items.item(0).getFirstChild().getNodeValue());
            items = root.getElementsByTagName("Visibility");
            obj.setVisibility(items.item(0).getFirstChild().getNodeValue());
            items = root.getElementsByTagName("SkyConditions");
            obj.setSkyConditions(items.item(0).getFirstChild().getNodeValue());
            items = root.getElementsByTagName("Temperature");
            obj.setTemperature(items.item(0).getFirstChild().getNodeValue());
            items = root.getElementsByTagName("DewPoint");
            obj.setDewPoint(items.item(0).getFirstChild().getNodeValue());
            items = root.getElementsByTagName("RelativeHumidity");
            obj.setRelativeHumidity(items.item(0).getFirstChild().getNodeValue());
            items = root.getElementsByTagName("Pressure");
            obj.setPressure(items.item(0).getFirstChild().getNodeValue());
            items = root.getElementsByTagName("Status");
            obj.setStatus(items.item(0).getFirstChild().getNodeValue());


        } catch (Exception e) {
            e.printStackTrace();
            sStatus = "-1"; //error
        }

        return sStatus;
    }

    /**
     * Funcion que se conecta al webservice y regrasa un valor string
     * esta funcion remplazara a cualquiera que tome info del webservice
     * @param sUrl contiene la direccion completa a la que se va conectar
     * @param sNameSpace contiene el espacio de nombres Ejem. http://tempuri.org/
     * @param sMetodo contiene el metodo del webservice al que se va accesar
     * @return sStatus regresa el valor obtenido desde el webservice
     */
    public String getDatoWebService2(String sUrl,String sNameSpace,String sMetodo)
    {
        System.out.println("Entro a getDatoWebService2");

        String sStatus;
        try {

            SoapObject request = new SoapObject(sNameSpace, sMetodo);
            request.addProperty("CountryName", "Mexico");
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.implicitTypes = true;
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);
            HttpTransportSE androidHttpTransport = new HttpTransportSE(sUrl);
            String sTexto = sNameSpace + "/" + sMetodo;
            androidHttpTransport.call(sTexto, envelope);
            Object response = envelope.getResponse();
            sStatus = response.toString();

            //DOM (Por ejemplo)
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            DocumentBuilder builder = factory.newDocumentBuilder();
            Document dom = builder.parse(new InputSource(new StringReader(sStatus)));
            //Document dom = builder.parse(sStatus);

            //A partir de aquí se trataría el árbol DOM como siempre.
            //Por ejemplo:
            Element root = dom.getDocumentElement();
            NodeList items = root.getElementsByTagName("Table");

            ciudades beCiudades  = new ciudades();
            ArrayList<ciudades> listCiudades = new ArrayList<>();

            //Recorremos la lista de noticias
            for (int i=0; i<items.getLength(); i++)
            {
                //Obtenemos la noticia actual
                Node item = items.item(i);

                //Obtenemos la lista de datos de la noticia actual
                NodeList datosNoticia = item.getChildNodes();

                beCiudades = new ciudades();

                //Procesamos cada dato de la noticia
                for (int j=0; j<datosNoticia.getLength(); j++)
                {
                    Node dato = datosNoticia.item(j);
                    String etiqueta = dato.getNodeName();

                    if(etiqueta.equals("Country")){
                        beCiudades.setCountry(dato.getFirstChild().getNodeValue());
                    }
                    if(etiqueta.equals("City")){
                        beCiudades.setCity(dato.getFirstChild().getNodeValue());
                    }
                }

                listCiudades.add(beCiudades);
            }


        } catch (Exception e) {
            e.printStackTrace();
            sStatus = "-1"; //error
        }

        return sStatus;
    }

    private String obtenerTexto(Node dato)
    {
        StringBuilder texto = new StringBuilder();
        NodeList fragmentos = dato.getChildNodes();

        for (int k=0;k<fragmentos.getLength();k++)
        {
            texto.append(fragmentos.item(k).getNodeValue());
        }

        return texto.toString();
    }
}
