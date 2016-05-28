package Clases;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.List;

/**
 * Created by TatisRamos on 13/05/2016.
 */
public class WebService {

    private static String NAMESPACE = "http://tempuri.org/";
    private static String SOAP_ACTION = "http://tempuri.org/";
    private static String ip = "http://jvmonje-002-site4.htempurl.com";
    private static String port = "80";
    private static String serviceName ="Service1.asmx";

    public static SoapObject callWebService(String nombreMetodo,List<PropertyInfo> ListPropertyInfos)
    {
        String URL = ip + ":"+ port +"/"+serviceName;

        SoapObject request = new SoapObject(NAMESPACE, nombreMetodo);

        /*
        PropertyInfo redPI = new PropertyInfo();
        redPI.setName("red");
        redPI.setValue(red);
        redPI.setType(int.class);
        */
        // agregamos los atributos a enviar al web service
        if (ListPropertyInfos !=  null) {
            for (int i = 0; i < ListPropertyInfos.size(); i++) {
                request.addProperty(ListPropertyInfos.get(i));
            }
        }

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER12);
        //envelope.setOutputSoapObject(request);
        envelope.bodyOut = request;
        envelope.dotNet = true;
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

        try
        {
            // Llama al webService
            androidHttpTransport.call(SOAP_ACTION + nombreMetodo, envelope);
            // Obtiene la respuesta
            SoapObject response = (SoapObject) envelope.bodyIn;
            if(response.getPropertyCount() > 0) {
                return response;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

