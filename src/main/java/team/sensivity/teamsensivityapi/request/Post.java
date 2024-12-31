package team.sensivity.teamsensivityapi.request;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import team.sensivity.teamsensivityapi.geheim.Panel;

public class Post {

    public static void doPost(String url, String body) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpPost request = new HttpPost(url);
            StringEntity params = new StringEntity(body);
            request.addHeader("content-type", "application/x-www-form-urlencoded");
            request.addHeader("Authorization", "Bearer " + Panel.panel);
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void doPost(String url) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        try {
            HttpPost request = new HttpPost(url);
            request.addHeader("content-type", "application/x-www-form-urlencoded");
            request.addHeader("Authorization", "Bearer " + Panel.panel);
            HttpResponse response = httpClient.execute(request);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
