package com.sjsu.swethamuchukota.cmpe277finalproject_movierecommender;

import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by rwatsh on 10/11/16.
 */

public class CommonUtils {
    public static final String HOST_PORT = "192.168.1.150:9090";
    public static final String MOVIES_API_FORAMT = "https://moviesapi.com/m.php?i={0}&type=movie&r=json";

    public static <T> List<T> convertJsonArrayToList(String jsonArrayStr, Class<T> clazz) throws java.io.IOException {
        /*ObjectMapper mapper = new ObjectMapper();
        //jsonArrayStr = removeIdField(jsonArrayStr);
        return mapper.readValue(jsonArrayStr,
                TypeFactory.defaultInstance().constructCollectionType(List.class, clazz));*/
        Type listType = new TypeToken<List<T>>() {}.getType();
        return new Gson().fromJson(jsonArrayStr, listType);
    }

    /**
     * Convert JSON string to object.
     *
     * @param jsonStr
     * @param clazz
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> T convertJsonToObject(String jsonStr, Class<T> clazz) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        //jsonStr = removeIdField(jsonStr);
        return mapper.readValue(jsonStr, clazz);
    }

    public static void httpPostRequest(String url, JSONArray jsonObject) throws IOException {
        URL object=new URL(url);

        HttpURLConnection con = (HttpURLConnection) object.openConnection();
        con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        con.setRequestProperty("Accept", "application/json; charset=UTF-8");
        con.setRequestMethod("POST");

        OutputStream os = con.getOutputStream();
        os.write(jsonObject.toString().getBytes("UTF-8"));
        os.close();
    }

    public static String httpGetRequest(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn =
                (HttpURLConnection) url.openConnection();

        if (conn.getResponseCode() != 200) {
            throw new IOException(conn.getResponseMessage());
        }

        // Buffer the result into a string
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();

        conn.disconnect();
        return sb.toString();
    }

    public static <T> String convertObjectToJson(T object)  {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            Log.d("MainActivity", "convertObjectToJson error: ", e);
            return "";
        }
    }

    /**
     * Perform http get and convert JSON to java object.
     *
     * @param resourceUri
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T get(String resourceUri, Class<T> clazz) {
        T retVal = null;
        try {

            Client client = ClientBuilder.newClient();
            WebTarget webTarget = client.target(resourceUri);

            Response response = webTarget
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get();
            String respStr = response.readEntity(String.class);
            System.out.println(respStr);
            retVal = CommonUtils.convertJsonToObject(respStr, clazz);
        } catch (Exception e) {

            e.printStackTrace();

        }
        return retVal;
    }


}
