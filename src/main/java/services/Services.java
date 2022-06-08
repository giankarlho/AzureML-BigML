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
        entity.append("\"model\":\"model/627bcfc58f679a53980002a9\",");
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
                .uri(URI.create("https://bigml.io/andromeda/prediction?username=gvalencia-1;api_key=6c105f5229d2238cf5722ab7c77a454723f33e65"))
                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        try {
            
            Diabetes diabetes = new Diabetes();
        diabetes.setEdad(33);
        diabetes.setGlucosa(121);
        diabetes.setPresion(69);
        diabetes.setInsulina(79);
        diabetes.setImc(32.16743);
        diabetes.setPedigri(0.47253);
        diabetes.setPliegue(20);
        diabetes.setEmbarazos(3);
        String cadenaJson = Services.datosAPI(diabetes);
        System.out.println("CadenaJson " + cadenaJson);
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }
        
    }
}

//        POST
//                
//        Request Body
//
//        {
//          "model":"model/627bcfc58f679a53980002a9",
//          "input_data":{
//            "Embarazos":1,
//            "Glucosa":78,
//            "Presión sanguínea":16,
//            "Pliegue cutáneo":78,
//            "Insulina":65,
//            "Índice de masa corporal":15,
//            "Pedigrí diabetes":156,
//            "Edad":58
//          }
//        }
