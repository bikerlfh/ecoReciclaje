package Clases;

import android.content.Context;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fercho on 5/22/2016.
 */
public class SincronizarDatos
{
    private WebService webService;
    private SoapObject response;
    private List<PropertyInfo> ListadoPropertyInfo;
    private Context context;

    private TipoInformacion tipoInformacion;
    private TipoMaterial tipoMaterial;
    private Informacion informacion;
    private Material material;
    private SitioReciclaje sitioReciclaje;
    private SitioReciclajeMaterial sitioReciclajeMaterial;

    public SincronizarDatos(Context context)
    {
        this.context = context;
    }

    public String SincronizarTipoInformacion()
    {
        tipoInformacion = new TipoInformacion(this.context);
        int ultimoIdTipoInformacion = tipoInformacion.consultarMaxId();
        ListadoPropertyInfo = new ArrayList<>();
        PropertyInfo prop = new PropertyInfo();
        prop.setName("ultimoIdTipoInformacion");
        prop.setValue(ultimoIdTipoInformacion);
        prop.setType(int.class);
        ListadoPropertyInfo.add(prop);

        response = webService.callWebService("sincronizarTipoInformacion",ListadoPropertyInfo);
        if (response!= null && response.getPropertyCount() > 0)
        {
            response = (SoapObject) ((SoapObject) response.getProperty(0)).getProperty("consulta");
            if (response!=null)
            {
                tipoInformacion = new TipoInformacion(this.context);
                for (int i = 0; i < response.getPropertyCount(); i++)
                {
                    SoapObject so = (SoapObject) response.getProperty(i);
                    if (so != null)
                    {
                        if(!tipoInformacion.insertTipoInformacion(Integer.parseInt(so.getProperty("idTipoInformacion").toString()),so.getProperty("descripcion").toString()))
                        {
                            return "Error: al intentar insertar tipo informacion";
                        }
                    }
                }
                return "true";
            }
        }
        return "Error: No se encontraron datos en tipo información";
    }

    public String SincronizarTipoMaterial()
    {
        tipoMaterial = new TipoMaterial(this.context);
        int ultimoIdTipoMaterial = tipoInformacion.consultarMaxId();
        ListadoPropertyInfo = new ArrayList<>();
        PropertyInfo prop = new PropertyInfo();
        prop.setName("ultimoIdTipoMaterial");
        prop.setValue(ultimoIdTipoMaterial);
        prop.setType(int.class);
        ListadoPropertyInfo.add(prop);

        response = webService.callWebService("sincronizarTipoMaterial",ListadoPropertyInfo);
        if (response!= null && response.getPropertyCount() > 0)
        {
            response = (SoapObject) ((SoapObject) response.getProperty(0)).getProperty("consulta");
            if (response!=null)
            {
                tipoMaterial = new TipoMaterial(this.context);
                for (int i = 0; i < response.getPropertyCount(); i++)
                {
                    SoapObject so = (SoapObject) response.getProperty(i);
                    if (so != null)
                    {
                        if(!tipoMaterial.insertTipoMaterial(Integer.parseInt(so.getProperty("idTipoMaterial").toString()),so.getProperty("descripcion").toString()))
                        {
                            return "Error: al intentar insertar tipo material";
                        }
                    }
                }
                return "true";
            }
        }
        return "Error: No se encontraron datos en tipo material";
    }

    public String SincronizarInformacion()
    {
        informacion = new Informacion(this.context);
        int ultimoId = informacion.consultarMaxId();
        ListadoPropertyInfo = new ArrayList<>();
        PropertyInfo prop = new PropertyInfo();
        prop.setName("ultimoIdInformacion");
        prop.setValue(ultimoId);
        prop.setType(int.class);
        ListadoPropertyInfo.add(prop);

        response = webService.callWebService("sincronizarInformacion",ListadoPropertyInfo);
        if (response!= null && response.getPropertyCount() > 0)
        {
            response = (SoapObject) ((SoapObject) response.getProperty(0)).getProperty("consulta");
            if (response!=null)
            {
                for (int i = 0; i < response.getPropertyCount(); i++)
                {
                    SoapObject so = (SoapObject) response.getProperty(i);
                    if (so != null)
                    {
                        if(!informacion.insertInformacion(Integer.parseInt(so.getProperty("idInformacion").toString()),
                                                          so.hasProperty("idTipoInformacion")?Integer.parseInt(so.getProperty("idTipoInformacion").toString()) : 0,
                                                          so.hasProperty("idMaterial")?Integer.parseInt(so.getProperty("idMaterial").toString()) : 0,
                                                          so.getProperty("titulo").toString(),
                                                          so.getProperty("descripcion").toString(),
                                                          so.getProperty("fecha").toString()))
                        {
                            return "Error: al intentar insertar información";
                        }
                    }
                }
                return "true";
            }
        }
        return "Error: No se encontraron datos en información";
    }

    public String SincronizarMaterial()
    {
        material = new Material(this.context);
        int ultimoId = material.consultarMaxId();
        ListadoPropertyInfo = new ArrayList<>();
        PropertyInfo prop = new PropertyInfo();
        prop.setName("ultimoIdMaterial");
        prop.setValue(ultimoId);
        prop.setType(int.class);
        ListadoPropertyInfo.add(prop);

        response = webService.callWebService("sincronizarMaterial",ListadoPropertyInfo);
        if (response!= null && response.getPropertyCount() > 0)
        {
            response = (SoapObject) ((SoapObject) response.getProperty(0)).getProperty("consulta");
            if (response!=null)
            {
                for (int i = 0; i < response.getPropertyCount(); i++)
                {
                    SoapObject so = (SoapObject) response.getProperty(i);
                    if (so != null)
                    {
                        if(!material.insertMaterial(Integer.parseInt(so.getProperty("idMaterial").toString()),
                                Integer.parseInt(so.getProperty("idTipoMaterial").toString()),
                                so.getProperty("codigo").toString(),
                                so.getProperty("nombre").toString()))
                        {
                            return "Error: al intentar insertar materiales";
                        }
                    }
                }
                return "true";
            }
        }
        return "Error: No se encontraron datos en materiales";
    }

    public String SincronizarSitioReciclaje()
    {
        sitioReciclaje = new SitioReciclaje(this.context);
        int ultimoId = sitioReciclaje.consultarMaxId();
        ListadoPropertyInfo = new ArrayList<>();
        PropertyInfo prop = new PropertyInfo();
        prop.setName("ultimoIdSitioReciclaje");
        prop.setValue(ultimoId);
        prop.setType(int.class);
        ListadoPropertyInfo.add(prop);

        response = webService.callWebService("sincronizarSitioReciclaje",ListadoPropertyInfo);
        if (response!= null && response.getPropertyCount() > 0)
        {
            response = (SoapObject) ((SoapObject) response.getProperty(0)).getProperty("consulta");
            if (response!=null)
            {
                for (int i = 0; i < response.getPropertyCount(); i++)
                {
                    SoapObject so = (SoapObject) response.getProperty(i);
                    if (so != null)
                    {
                        if(!sitioReciclaje.insertSitioReciclaje(Integer.parseInt(so.getProperty("idSitioReciclaje").toString()),
                                so.getProperty("nombre").toString(),
                                so.getProperty("direccion").toString(),
                                so.getProperty("propietario").toString(),
                                so.getProperty("latitud").toString(),
                                so.getProperty("longitud").toString()))
                        {
                            return "Error: al intentar insertar sitio de reciclaje";
                        }
                    }
                }
                return "true";
            }
        }
        return "Error: No se encontraron datos en sitio de reciclaje";
    }
    public String SincronizarSitioReciclajeMaterial()
    {
        sitioReciclajeMaterial = new SitioReciclajeMaterial(this.context);
        int ultimoId = sitioReciclajeMaterial.consultarMaxId();
        ListadoPropertyInfo = new ArrayList<>();
        PropertyInfo prop = new PropertyInfo();
        prop.setName("ultimoIdSitioReciclajeMaterial");
        prop.setValue(ultimoId);
        prop.setType(int.class);
        ListadoPropertyInfo.add(prop);

        response = webService.callWebService("sincronizarSitioReciclajeMaterial",ListadoPropertyInfo);
        if (response!= null && response.getPropertyCount() > 0)
        {
            response = (SoapObject) ((SoapObject) response.getProperty(0)).getProperty("consulta");
            if (response!=null)
            {
                for (int i = 0; i < response.getPropertyCount(); i++)
                {
                    SoapObject so = (SoapObject) response.getProperty(i);
                    if (so != null)
                    {
                        if(!sitioReciclajeMaterial.insertSitioReciclajeMaterial(Long.parseLong(so.getProperty("idSitioReciclajeMaterial").toString()),
                                Integer.parseInt(so.getProperty("idSitioReciclajeMaterial").toString()),
                                Long.parseLong(so.getProperty("idSitioReciclajeMaterial").toString())))
                        {
                            return "Error: al intentar insertar sitio de reciclaje Material";
                        }
                    }
                }
                return "true";
            }
        }
        return "Error: No se encontraron datos en sitio de reciclaje Material";
    }
}
