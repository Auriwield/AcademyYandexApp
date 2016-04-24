package yandex.app.parseclasses;

import java.io.Serializable;

public class Cover implements Serializable{
    private String small;
    private String big;

    public String getSmall() {
        return small;
    }

    public String getBig() {
        return big;
    }
}
