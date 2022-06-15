package services;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.primefaces.shaded.json.JSONObject;

public class CustomVisonS {

    public static JSONObject obtenerJson(String url) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n "
                + "   \"url\":\"" + url + "\"\r\n}\r\n");
        Request request = new Request.Builder()
                .url("XXXXXX Uri XXXXXXXXXXXXXXXXXX")
                .method("POST", body)
                .addHeader("Prediction-Key", "XXXXX Api Key XXXXXX")
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()){
            throw new IOException("Unexpected code " + response);
        }        
        JSONObject jsonObject = new JSONObject(response.body().string());
        JSONObject cadenaJSON = jsonObject.getJSONArray("predictions")
                .getJSONObject(0);                
        return cadenaJSON;        
    }
    
}
