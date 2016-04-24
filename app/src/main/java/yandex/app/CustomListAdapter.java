package yandex.app;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

/**
 * Created by Dinozavrik on 22.04.2016.
 */
public class CustomListAdapter extends ArrayAdapter<AutorItem> {
    private final Activity context;
    private final List<AutorItem> list;
    LayoutInflater lInflater;

    public CustomListAdapter(Activity context, List<AutorItem> list) {
        super(context, R.layout.activity_main, list);
        this.context = context;
        this.list = list;
        lInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }

    public View getView(int position,View view,ViewGroup parent) {

        if (view == null) {
            view = lInflater.inflate(R.layout.list_item, parent, false);
        }

        TextView txtTitle = (TextView) view.findViewById(R.id.item);
        ImageView imageView = (ImageView) view.findViewById(R.id.icon);
        TextView extratxt = (TextView) view.findViewById(R.id.textViewGenres);

        txtTitle.setText(list.get(position).getName());
        Ion.with(context)
                .load(list.get(position).getCover().getBig())
                .intoImageView(imageView);
        extratxt.setText(transformArray(list.get(position).getGenres()));
        return view;

    }

    private String transformArray(String[] array) {
        StringBuilder genreString = new StringBuilder("");
        for (String genre : array)
            genreString.append(genre).append(", ");
        return genreString.delete(genreString.length() - 2, genreString.length() -1).toString();
    }
}
