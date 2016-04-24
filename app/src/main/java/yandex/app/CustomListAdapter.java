package yandex.app;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

import java.util.List;

import yandex.app.parseclasses.AuthorItem;

public class CustomListAdapter extends ArrayAdapter<AuthorItem> {
    private final Activity context;
    private final List<AuthorItem> list;
    LayoutInflater lInflater;

    public List<AuthorItem> getList() {
        return list;
    }

    public CustomListAdapter(Activity context, List<AuthorItem> list) {
        super(context, R.layout.activity_main, list);
        this.context = context;
        this.list = list;
        lInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public View getView(int position,View view,ViewGroup parent) {

        if (view == null) {
            view = lInflater.inflate(R.layout.list_item, parent, false);
        }

        TextView txtTitle = (TextView) view.findViewById(R.id.item);
        ImageView imageView = (ImageView) view.findViewById(R.id.icon);
        TextView extratxt = (TextView) view.findViewById(R.id.textViewGenres);
        TextView infotxt = (TextView) view.findViewById(R.id.textViewInfo);

        txtTitle.setText(list.get(position).getName());
        Ion.with(context)
                .load(list.get(position).getCover().getSmall())
                .intoImageView(imageView);
        extratxt.setText(transformArray(list.get(position).getGenres()));
        infotxt.setText("альбомов: " + list.get(position).getAlbums() + ", песен: " + list.get(position).getTracks());
        return view;

    }

    public static String transformArray(String[] array) {
        StringBuilder genreString = new StringBuilder("");
        for (String genre : array)
            genreString.append(genre).append(", ");
        return genreString.delete(genreString.length() - 2, genreString.length() -1).toString();
    }
}
