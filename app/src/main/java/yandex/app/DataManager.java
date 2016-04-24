package yandex.app;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Dinozavrik on 22.04.2016.
 */
public class DataManager {

    public void storeJSON(Context context, String jsonStr) {
        final FileOutputStream output;
        String path = context.getFilesDir().getAbsoluteFile().toString() + "/data";
        File file = new File(path);

        try {
            if (!file.exists()) {
                if (!file.createNewFile())
                    return;
            }
        }
        catch (IOException e) {
            return;
        }

        try {
            output = new FileOutputStream(path);
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        try {
            output.write(jsonStr.getBytes());
        }
        catch(IOException e) {
            e.printStackTrace();

        }
        finally {
            try {
                output.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String restoreJSON(Context context) {
        final FileInputStream input;
        final byte data[];
        String path = context.getFilesDir().getAbsoluteFile().toString() + "/data";
        final File file = new File(path);
        if (!file.exists()) return null;

        try {
            input = new FileInputStream(file);
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        data = new byte[(int) file.length()];
        try {
            try {
                if (input.read(data)!= data.length)
                    return null;
            }
            finally {
                input.close();
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
            return null;
        }

        return new String(data);
    }
}
