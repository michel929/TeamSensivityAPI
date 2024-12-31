package team.sensivity.teamsensivityapi.request;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import team.sensivity.teamsensivityapi.geheim.Panel;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Delete {
    public static void doDelete(String url1) {
        try {
            URL url = new URL(url1);
            HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
            httpCon.setDoOutput(true);
            httpCon.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpCon.setRequestProperty("Authorization", "Bearer " + Panel.panel);
            httpCon.setRequestMethod("DELETE");
            int responseCode = httpCon.getResponseCode();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
