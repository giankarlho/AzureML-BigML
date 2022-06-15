package controller;

import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import lombok.Data;
import model.CustomVision;
import org.primefaces.shaded.json.JSONObject;
import services.CustomVisonS;

@Data
@Named(value = "customController")
@SessionScoped
public class CustomVisionC implements Serializable {

    String urlImagen;
    String urlImagenVista;
    CustomVision modelo;   

    public CustomVisionC() {    
        modelo = new CustomVision();        
    }
    
    public void obtenerDatos() throws Exception {                
        try {
            JSONObject cadenaJson = CustomVisonS.obtenerJson(urlImagen);
            modelo.setProbabilityDog(cadenaJson.getDouble("probability"));
            modelo.setTagName(cadenaJson.getString("tagName")); 
            modelo.setProbabilityCat(1-modelo.getProbabilityDog());
            urlImagenVista = urlImagen;
        } catch (IOException e) {
            System.out.println("Error en " + e.getMessage());
        }
    }
    
    public static void main(String[] args) throws Exception {
        CustomVisionC modelo = new CustomVisionC();
        modelo.obtenerDatos();
    }
}
