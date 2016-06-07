package com.bikerlfh.ecoreciclaje.Clases;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.Toast;

import com.bikerlfh.ecoreciclaje.MenuPrincipal;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by fercho on 5/22/2016.
 */
public class SincronizarDatos {
    private WebService webService;
    private SoapObject response;
    private List<PropertyInfo> ListadoPropertyInfo;
    private Context context;

    private String messageError;
    private List<Method> methods;

    public SincronizarDatos(Context context) {
        this.context = context;
    }

    public String getMessageError() {
        return this.messageError;
    }

    public Boolean SincronizarPais() {
        Pais pais = new Pais(this.context);
        int ultimoId = pais.consultarMaxId();
        ListadoPropertyInfo = new ArrayList<>();
        addPopertyInfo("ultimoIdPais", ultimoId, int.class);

        response = webService.callWebService("sincronizarPais", ListadoPropertyInfo);
        if (response != null && response.getPropertyCount() > 0) {
            response = (SoapObject) ((SoapObject) response.getProperty(0)).getProperty("consulta");
            if (response != null) {
                for (int i = 0; i < response.getPropertyCount(); i++) {
                    SoapObject so = (SoapObject) response.getProperty(i);
                    if (so != null) {
                        if (!pais.insertPais(Integer.parseInt(so.getProperty("idPais").toString()), so.getProperty("codigo").toString(), so.getProperty("descripcion").toString())) {
                            this.messageError = "Error: al intentar insertar pais";
                            return false;
                        }
                    }
                }
                return true;
            }
        }
        this.messageError = "Error: No se encontraron datos en pais";
        return false;
    }

    public Boolean SincronizarDepartamento() {
        Departamento departamento = new Departamento(this.context);
        int ultimoId = departamento.consultarMaxId();
        ListadoPropertyInfo = new ArrayList<>();
        addPopertyInfo("ultimoIdDepartamento", ultimoId, int.class);

        response = webService.callWebService("sincronizarDepartamento", ListadoPropertyInfo);
        if (response != null && response.getPropertyCount() > 0) {
            response = (SoapObject) ((SoapObject) response.getProperty(0)).getProperty("consulta");
            if (response != null) {
                for (int i = 0; i < response.getPropertyCount(); i++) {
                    SoapObject so = (SoapObject) response.getProperty(i);
                    if (so != null) {
                        if (!departamento.insertDepartamento(Integer.parseInt(so.getProperty("idDepartamento").toString()), Integer.parseInt(so.getProperty("idPais").toString()), so.getProperty("codigo").toString(), so.getProperty("descripcion").toString())) {
                            this.messageError = "Error: al intentar insertar departamento";
                            return false;
                        }
                    }
                }
                return true;
            }
        }
        this.messageError = "Error: No se encontraron datos en departamento";
        return false;
    }

    public Boolean SincronizarMunicipio() {
        Municipio municipio = new Municipio(this.context);
        int ultimoId = municipio.consultarMaxId();
        ListadoPropertyInfo = new ArrayList<>();
        addPopertyInfo("ultimoIdMunicipio", ultimoId, int.class);

        response = webService.callWebService("sincronizarMunicipio", ListadoPropertyInfo);
        if (response != null && response.getPropertyCount() > 0) {
            response = (SoapObject) ((SoapObject) response.getProperty(0)).getProperty("consulta");
            if (response != null) {
                for (int i = 0; i < response.getPropertyCount(); i++) {
                    SoapObject so = (SoapObject) response.getProperty(i);
                    if (so != null) {
                        if (!municipio.insertMunicipio(Integer.parseInt(so.getProperty("idMunicipio").toString()), Integer.parseInt(so.getProperty("idDepartamento").toString()), so.getProperty("codigo").toString(), so.getProperty("descripcion").toString())) {
                            this.messageError = "Error: al intentar insertar Municipio";
                            return false;
                        }
                    }
                }
                return true;
            }
        }
        this.messageError = "Error: No se encontraron datos en Municipio";
        return false;
    }

    public Boolean SincronizarTipoInformacion() {
        TipoInformacion tipoInformacion = new TipoInformacion(this.context);
        int ultimoId = tipoInformacion.consultarMaxId();
        ListadoPropertyInfo = new ArrayList<>();
        addPopertyInfo("ultimoIdTipoInformacion", ultimoId, int.class);

        response = webService.callWebService("sincronizarTipoInformacion", ListadoPropertyInfo);
        if (response != null && response.getPropertyCount() > 0) {
            response = (SoapObject) ((SoapObject) response.getProperty(0)).getProperty("consulta");
            if (response != null) {
                tipoInformacion = new TipoInformacion(this.context);
                for (int i = 0; i < response.getPropertyCount(); i++) {
                    SoapObject so = (SoapObject) response.getProperty(i);
                    if (so != null) {
                        if (!tipoInformacion.insertTipoInformacion(Integer.parseInt(so.getProperty("idTipoInformacion").toString()), so.getProperty("codigo").toString(), so.getProperty("descripcion").toString())) {
                            this.messageError = "Error: al intentar insertar tipo informacion";
                            return false;
                        }
                    }
                }
                return true;
            }
        }
        this.messageError = "Error: No se encontraron datos en tipo información";
        return false;
    }

    public Boolean SincronizarTipoMaterial() {
        TipoMaterial tipoMaterial = new TipoMaterial(this.context);
        int ultimoId = tipoMaterial.consultarMaxId();
        ListadoPropertyInfo = new ArrayList<>();
        addPopertyInfo("ultimoIdTipoMaterial", ultimoId, int.class);

        response = webService.callWebService("sincronizarTipoMaterial", ListadoPropertyInfo);
        if (response != null && response.getPropertyCount() > 0) {
            response = (SoapObject) ((SoapObject) response.getProperty(0)).getProperty("consulta");
            if (response != null) {
                tipoMaterial = new TipoMaterial(this.context);
                for (int i = 0; i < response.getPropertyCount(); i++) {
                    SoapObject so = (SoapObject) response.getProperty(i);
                    if (so != null) {
                        if (!tipoMaterial.insertTipoMaterial(Integer.parseInt(so.getProperty("idTipoMaterial").toString()), so.getProperty("codigo").toString(), so.getProperty("descripcion").toString())) {
                            this.messageError = "Error: al intentar insertar tipo material";
                            return false;
                        }
                    }
                }
                return true;
            }
        }
        this.messageError = "Error: No se encontraron datos en tipo material";
        return false;
    }

    public Boolean SincronizarInformacion() {
        Informacion informacion = new Informacion(this.context);
        int ultimoId = informacion.consultarMaxId();
        ListadoPropertyInfo = new ArrayList<>();
        addPopertyInfo("ultimoIdInformacion", ultimoId, int.class);

        response = webService.callWebService("sincronizarInformacion", ListadoPropertyInfo);
        if (response != null && response.getPropertyCount() > 0) {
            response = (SoapObject) ((SoapObject) response.getProperty(0)).getProperty("consulta");
            if (response != null) {
                for (int i = 0; i < response.getPropertyCount(); i++) {
                    SoapObject so = (SoapObject) response.getProperty(i);
                    if (so != null) {
                        if (!informacion.insertInformacion(Integer.parseInt(so.getProperty("idInformacion").toString()),
                                so.hasProperty("idTipoInformacion") ? Integer.parseInt(so.getProperty("idTipoInformacion").toString()) : 0,
                                so.hasProperty("idMaterial") ? Integer.parseInt(so.getProperty("idMaterial").toString()) : 0,
                                so.getProperty("titulo").toString(),
                                so.getProperty("descripcion").toString(),
                                so.getProperty("fecha").toString())) {
                            this.messageError = "Error: al intentar insertar información";
                            return false;
                        }
                    }
                }
                return true;
            }
        }
        this.messageError = "Error: No se encontraron datos en información";
        return false;
    }

    public Boolean SincronizarMaterial() {
        Material material = new Material(this.context);
        int ultimoId = material.consultarMaxId();
        ListadoPropertyInfo = new ArrayList<>();
        addPopertyInfo("ultimoIdMaterial", ultimoId, int.class);

        response = webService.callWebService("sincronizarMaterial", ListadoPropertyInfo);
        if (response != null && response.getPropertyCount() > 0) {
            response = (SoapObject) ((SoapObject) response.getProperty(0)).getProperty("consulta");
            if (response != null) {
                for (int i = 0; i < response.getPropertyCount(); i++) {
                    SoapObject so = (SoapObject) response.getProperty(i);
                    if (so != null) {
                        if (!material.insertMaterial(Integer.parseInt(so.getProperty("idMaterial").toString()),
                                Integer.parseInt(so.getProperty("idTipoMaterial").toString()),
                                so.getProperty("codigo").toString(),
                                so.getProperty("nombre").toString(),
                                so.getProperty("descripcion").toString())) {
                            this.messageError = "Error: al intentar insertar materiales";
                            return false;
                        }
                    }
                }
                return true;
            }
        }
        this.messageError = "Error: No se encontraron datos en materiales";
        return false;
    }

    public Boolean SincronizarSitioReciclaje() {
        SitioReciclaje sitioReciclaje = new SitioReciclaje(this.context);
        int ultimoId = sitioReciclaje.consultarMaxId();
        ListadoPropertyInfo = new ArrayList<>();
        addPopertyInfo("ultimoIdSitioReciclaje", ultimoId, int.class);

        response = webService.callWebService("sincronizarSitioReciclaje", ListadoPropertyInfo);
        if (response != null && response.getPropertyCount() > 0) {
            response = (SoapObject) ((SoapObject) response.getProperty(0)).getProperty("consulta");
            if (response != null) {
                for (int i = 0; i < response.getPropertyCount(); i++) {
                    SoapObject so = (SoapObject) response.getProperty(i);
                    if (so != null) {
                        if (!sitioReciclaje.insertSitioReciclaje(Integer.parseInt(so.getProperty("idSitioReciclaje").toString()),
                                so.getProperty("nombre").toString(),
                                so.getProperty("direccion").toString(),
                                so.getProperty("telefono").toString(),
                                Integer.parseInt(so.getProperty("idMunicipio").toString()),
                                Double.parseDouble(so.getProperty("latitud").toString()),
                                Double.parseDouble(so.getProperty("longitud").toString()))) {
                            this.messageError = "Error: al intentar insertar sitio de reciclaje";
                            return false;
                        }
                    }
                }
                return true;
            }
        }
        this.messageError = "Error: No se encontraron datos en sitio de reciclaje";
        return false;
    }

    public Boolean SincronizarSitioReciclajeMaterial() {
        SitioReciclajeMaterial sitioReciclajeMaterial = new SitioReciclajeMaterial(this.context);
        int ultimoId = sitioReciclajeMaterial.consultarMaxId();
        ListadoPropertyInfo = new ArrayList<>();
        addPopertyInfo("ultimoIdSitioReciclajeMaterial", ultimoId, int.class);

        response = webService.callWebService("sincronizarSitioReciclajeMaterial", ListadoPropertyInfo);
        if (response != null && response.getPropertyCount() > 0) {
            response = (SoapObject) ((SoapObject) response.getProperty(0)).getProperty("consulta");
            if (response != null) {
                for (int i = 0; i < response.getPropertyCount(); i++) {
                    SoapObject so = (SoapObject) response.getProperty(i);
                    if (so != null) {
                        if (!sitioReciclajeMaterial.insertSitioReciclajeMaterial(Long.parseLong(so.getProperty("idSitioReciclajeMaterial").toString()),
                                Integer.parseInt(so.getProperty("idMaterial").toString()),
                                Long.parseLong(so.getProperty("idSitioReciclaje").toString()))) {
                            this.messageError = "Error: al intentar insertar sitio de reciclaje Material";
                            return false;
                        }
                    }
                }
                return true;
            }
        }
        this.messageError = "Error: No se encontraron datos en sitio de reciclaje Material";
        return false;
    }

    private void addPopertyInfo(String name, Object value, Object type) {
        if (ListadoPropertyInfo == null)
            ListadoPropertyInfo = new ArrayList<>();
        PropertyInfo propertyInfo = new PropertyInfo();
        propertyInfo.setName(name);
        propertyInfo.setValue(value);
        propertyInfo.setType(type);
        ListadoPropertyInfo.add(propertyInfo);
    }

    /*
    public void RefreshInformacion(SwipeRefreshLayout swipeRefresh)
    {
        Boolean retorno = true;
        try {
            this.addMethod(getClass().getMethod("SincronizarTipoInformacion",null));
            this.addMethod(getClass().getMethod("SincronizarTipoMaterial",null));
            this.addMethod(getClass().getMethod("SincronizarInformacion",null));
            this.addMethod(getClass().getMethod("SincronizarMaterial",null));

            new AsyncSincronizar().execute(swipeRefresh);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
    private void addMethod(Method method)
    {
        if (this.methods == null)
            this.methods = new ArrayList<>();
        this.methods.add(method);
    }

    class AsyncSincronizar extends AsyncTask<SwipeRefreshLayout,String,Boolean>
    {
        SwipeRefreshLayout swipeRefresh = null;
        @Override
        protected Boolean doInBackground(SwipeRefreshLayout... params) {
            Boolean retorno = true;
            swipeRefresh = params[0];
            for (Method method: methods) {
                try
                {
                    boolean result =(boolean)method.invoke(SincronizarDatos.this,null);
                    if (!result) {
                        retorno = false;
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }

            return retorno;
        }
        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            swipeRefresh.setRefreshing(false);
            try {
                Method methodVisualizardatos = context.getClass().getMethod("visualizarDatos",null);
                if (methodVisualizardatos != null)
                {
                    try {
                        methodVisualizardatos.invoke(context,null);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }
    */
}