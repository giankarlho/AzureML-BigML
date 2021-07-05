package services;

import model.Diabetes;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Services {

    public static String datosAPI(Diabetes json) throws IOException, InterruptedException {
        StringBuilder entity = new StringBuilder();
        entity.append("{");
        entity.append("\"model\":\"model/60c28b19e4279b24a1002f2d\",");
        entity.append("\"input_data\":{");
        entity.append("\"Embarazos\":").append(json.getEmbarazos()).append(",");
        entity.append("\"Glucosa\":").append(json.getGlucosa()).append(",");
        entity.append("\"Presión sanguínea\":").append(json.getPresion()).append(",");
        entity.append("\"Pliegue cutáneo\":").append(json.getPliegue()).append(",");
        entity.append("\"Insulina\":").append(json.getInsulina()).append(",");
        entity.append("\"Índice de masa corporal\":").append(json.getImc()).append(",");
        entity.append("\"Pedigrí diabetes\":").append(json.getPedigri()).append(",");
        entity.append("\"Edad\":").append(json.getEdad()).append("");
        entity.append("}");
        entity.append("}");
        
        // HttpClient funciona a partir del JDK11
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = (HttpRequest) HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(entity.toString()))
                .uri(URI.create(" XXXXXXXX aquí va el uri XXXXXXXXX"))                                
                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());          
        return response.body();
    }




