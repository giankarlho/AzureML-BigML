package services;

import java.io.IOException;
import model.Diabetes;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.primefaces.shaded.json.JSONException;
import org.primefaces.shaded.json.JSONObject;

public class Services2 {

    public static JSONObject obtenerJson(Diabetes modelo) throws Exception, JSONException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "        {\r\n        "
                + " \"model\":\"model/627bcfc58f679a53980002a9\",\r\n         "
                + " \"input_data\":{\r\n "
                + " \"Embarazos\":" + modelo.getEmbarazos() + ",\r\n "
                + " \"Glucosa\":" + modelo.getGlucosa() + ",\r\n  "
                + " \"Presión sanguínea\":" + modelo.getPresion() + ",\r\n "
                + " \"Pliegue cutáneo\":" + modelo.getPliegue() + ",\r\n          "
                + " \"Insulina\":" + modelo.getInsulina() + ",\r\n           "
                + " \"Índice de masa corporal\":" + modelo.getImc() + ",\r\n            "
                + " \"Pedigrí diabetes\":" + modelo.getPedigri() + ",\r\n           "
                + " \"Edad\":" + modelo.getEdad() + "\r\n          }\r\n        }");
        Request request = new Request.Builder()
                .url("https://bigml.io/andromeda/prediction?username=gvalencia-1;api_key=6c105f5229d2238cf5722ab7c77a454723f33e65")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        }
        JSONObject jsonObject = new JSONObject(response.body().string());
        JSONObject cadenaJson = jsonObject.getJSONObject("Results")
                .getJSONArray("output1")
                .getJSONObject(0);
        return cadenaJson;
    }
}
