package services;

import model.Diabetes;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Services {

    String url = "https://bigml.io/andromeda/";
    String credenciales = "?username=gvalencia-1;api_key=6c105f5229d2238cf5722ab7c77a454723f33e65";

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

//        return entity.toString();
        // HttpClient funciona a partir del JDK11
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = (HttpRequest) HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(entity.toString()))
                .uri(URI.create("https://bigml.io/andromeda/prediction?username=gvalencia-1;api_key=6c105f5229d2238cf5722ab7c77a454723f33e65"))                                
                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .header("Content-Type", "application/json")
                .build();

        HttpResponse<String> response = client.send(request,HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        return response.body();
    }

//    public static void main(String[] args) throws Exception {
//        Diabetes diabetes = new Diabetes();
//        diabetes.setEdad(33);
//        diabetes.setGlucosa(121);
//        diabetes.setPresion(69);
//        diabetes.setInsulina(79);
//        diabetes.setImc(32.16743);
//        diabetes.setPedigri(0.47253);
//        diabetes.setPliegue(20);
//        diabetes.setEmbarazos(3);
//        System.out.println("Cadena" + Services.datosAPI(diabetes));   
//        
//    }

}

// https://bigml.io/andromeda/model/60a678f5048f411328000003?username=gvalencia-1;api_key=6c105f5229d2238cf5722ab7c77a454723f33e65
//BIGML_USERNAME=gvalencia-1
//BIGML_API_KEY=6c105f5229d2238cf5722ab7c77a454723f33e65
//set BIGML_AUTH=username^=%BIGML_USERNAME%;api_key^=%BIGML_API_KEY%
// https://bigml.io/andromeda/prediction/60c294dbe4279b24a1002f32?username=gvalencia-1;api_key=6c105f5229d2238cf5722ab7c77a454723f33e65
// Test POST
//{"model":"model/60c28b19e4279b24a1002f2d",
//"input_data":{
//        "000006": 0.47253,
//        "000007": 33,
//        "000004": 79,
//        "000005": 32.16743,
//        "000002": 69,
//        "000003": 20,
//        "000000": 3,
//        "000001": 121
//    }
//}
//{"model":"model/60c28b19e4279b24a1002f2d",
//"input_data":{
//        "Embarazos": 5,
//        "Glucosa": 150,
//        "Presión sanguínea": 69,
//        "Pliegue cutáneo": 20,
//        "Insulina": 79,
//        "Índice de masa corporal": 32.16743,
//        "Pedigrí diabetes": 0.47253,
//        "Edad": 45
//    }
//}
//fields:
//  '000000':
//    datatype: int8
//    name: Embarazos
//  '000001':
//    datatype: int16
//    name: Glucosa
//  '000002':
//    datatype: int8
//    name: Presión sanguínea
//  '000003':
//    datatype: int8
//    name: Pliegue cutáneo
//  '000004':
//    datatype: int8
//    name: Insulina
//  '000005':
//    datatype: double
//    name: Índice de masa corporal
//  '000006': 
//    datatype: double
//    name: Pedigrí diabetes
//  '000007':
//    datatype: int8
//    name: Edad
//  000008:
//    datatype: string
//    name: Diabetes
