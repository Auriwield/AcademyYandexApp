package yandex.app.parseclasses;

import java.io.Serializable;

/**
 * Created by Dinozavrik on 22.04.2016.
 */

public class AuthorItem implements Serializable {
    private long id;
    private String name;
    private String[] genres;
    private int tracks;
    private int albums;
    private String link;
    private String description;
    private Cover cover;

    public long getId() {
        return id;
    }

    public String[] getGenres() {
        return genres;
    }

    public int getTracks() {
        return tracks;
    }

    public String getDescription() {
        return description;
    }

    public Cover getCover() {
        return cover;
    }

    public String getLink() {
        return link;
    }

    public int getAlbums() {
        return albums;
    }

    public String getName() {
        return name;
    }
}
