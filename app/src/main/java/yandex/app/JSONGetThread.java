package yandex.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Dinozavrik on 24.04.2016.
 */
public class JSONGetThread extends Thread {
    public static final String JSON_HREF = "http://cache-spb10.cdn.yandex.net/download.cdn.yandex.net/mobilization-2016/artists.json";
    private String jsonStr = null;
    @Override
    public void run() {
        try {
            URL url = new URL(JSON_HREF);
            URLConnection connection = url.openConnection();
            InputStreamReader inputStream = new InputStreamReader(connection.getInputStream());
            BufferedReader reader = new BufferedReader(inputStream);

            String line = null;
            StringBuilder builder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            jsonStr = builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getJsonStr() {
        return jsonStr;
    }
}
