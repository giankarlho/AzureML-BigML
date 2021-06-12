package controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import lombok.Data;
import model.Diabetes;
import services.Services;

@Named(value = "diabetesC")
@SessionScoped
@Data
public class DiabetesC implements Serializable {

    Diabetes diabetes;

    public DiabetesC() {
        diabetes = new Diabetes();    
        diabetes.setEdad(33);
        diabetes.setGlucosa(121);
        diabetes.setPresion(69);
        diabetes.setInsulina(79);
        diabetes.setImc(32.16743);
        diabetes.setPedigri(0.47253);
        diabetes.setPliegue(20);
        diabetes.setEmbarazos(3);        
    }
      

    public void obtenerDatos() throws IOException, InterruptedException {
        String cadenaJson = Services.datosAPI(diabetes);        
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject) jsonParser.parse(cadenaJson);
        diabetes = new Diabetes();
        diabetes.setCategory(jsonObject.get("output").getAsString());
        diabetes.setProbability(jsonObject.get("probability").getAsDouble());
        if (diabetes.getCategory().equals("No")) {
            diabetes.setResult("Que bien, no tienes diabetes");
        } else {
            diabetes.setResult("Lo sentimos, de acuerdo a nuestro modelo tienes diabetes");
        }
    }

}
