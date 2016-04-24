package yandex.app.parseclasses;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import yandex.app.DataManager;

public class JSONParser {
    private Context context;


    public JSONParser(Context context) {
        this.context = context;
    }

    public List<AuthorItem> parse() {
        String jsonStr = DataManager.restoreJSON(context);

        if (jsonStr == null) {
            JSONGetThread thread = new JSONGetThread();
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }
            jsonStr = thread.getJsonStr();
            if (jsonStr == null)
                return null;
            DataManager.storeJSON(context, jsonStr);
        }

        Type itemsListType = new TypeToken<List<AuthorItem>>() {}.getType();
        return new Gson().fromJson(jsonStr, itemsListType);
    }
}
