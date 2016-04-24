package yandex.app;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Dinozavrik on 22.04.2016.
 */
public class JSONParser {
    public static final String JSON_HREF = "http://cache-spb10.cdn.yandex.net/download.cdn.yandex.net/mobilization-2016/artists.json";


    public List<AutorItem> parse() {
        JSONGetThread thread = new JSONGetThread();
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String jsonStr = thread.getJsonStr();
        if (jsonStr == null)
            return null;

        Type itemsListType = new TypeToken<List<AutorItem>>() {}.getType();
        return new Gson().fromJson(jsonStr, itemsListType);
    }
}
