package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import lombok.Data;
import model.Diabetes;
import services.Services;

import java.io.File;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.PrediccionBody;
import model.PrediccionInputData;
import services.Services2;


@Named(value = "diabetesC")
@SessionScoped
@Data
public class DiabetesC implements Serializable {

    Diabetes diabetes;
    Services2 services2;

    public DiabetesC(Diabetes diabetes, Services2 services2) {
        this.diabetes = diabetes;
        this.services2 = services2;
    }  

    public DiabetesC() {
        diabetes = new Diabetes();
        diabetes.setEmbarazos(3);
        diabetes.setGlucosa(121);
        diabetes.setPresion(69);
        diabetes.setPliegue(20);
        diabetes.setInsulina(79);
        diabetes.setImc(32.16743);
        diabetes.setPedigri(0.47253);
        diabetes.setEdad(33);
        services2 = new Services2();
    }

    public void obtenerDatos() throws IOException, InterruptedException {
        try {
            String cadenaJson = Services.datosAPI(diabetes);
            JsonParser jsonParser = new JsonParser();
            JsonObject jsonObject = (JsonObject) jsonParser.parse(cadenaJson);
            diabetes.setCategory(jsonObject.get("output").getAsString());
            diabetes.setProbability(jsonObject.get("probability").getAsDouble());
            if (diabetes.getCategory().equals("No")) {
                diabetes.setResult("Que bien, no tienes diabetes");
            } else {
                diabetes.setResult("Lo sentimos, de acuerdo a nuestro modelo tienes diabetes");
            }
        } catch (Exception e) {
            System.out.println("Error en obtenerDatosC: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void predecir() throws IOException {
//        try {
//            PrediccionBody model = new PrediccionBody();
//            model.setModel("model/5f52ac1b0d052e40ea0002b6");
//            PrediccionInputData data = new PrediccionInputData();
//            model.setInput_data(data);
//            String response = services2.post(model);
//            Gson gson = new Gson();
//            PredResDTO predResDTO = gson.fromJson(response, PredResDTO.class);
//            
//            FacesContext.getCurrentInstance().addMessage(null,
//                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Predicción ", "Usted "+ predResDTO.getOutput() + " tiene Diabetes"));
//            this.clear();
//        } catch (Exception e) {
//            System.out.println("Error Controller");
//        }
    }

    public void obtenerDatos2() throws Exception, InterruptedException {
        try {
            
        } catch (Exception e) {

        }
    }

}
