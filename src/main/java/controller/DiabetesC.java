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

import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


@Named(value = "diabetesC")
@SessionScoped
@Data
public class DiabetesC implements Serializable {

    Diabetes diabetes;

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

    public void obtenerDatos2() throws Exception, InterruptedException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            Diabetes dia =  mapper.readValue(new File(Services.datosAPI(diabetes)), Diabetes.class);
            diabetes.setCategory(dia.getCategory());
            diabetes.setProbability(dia.getProbability());
            if (diabetes.getCategory().equals("No")) {
                diabetes.setResult("Que bien, no tienes diabetes");
            } else {
                diabetes.setResult("Lo sentimos, de acuerdo a nuestro modelo tienes diabetes");
            }
        } catch (Exception e) {

        }
    }

}
