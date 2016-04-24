package yandex.app.activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

import java.util.List;

import yandex.app.parseclasses.AuthorItem;
import yandex.app.CustomListAdapter;
import yandex.app.parseclasses.JSONParser;
import yandex.app.R;

public class ScrollingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int position = getIntent().getIntExtra(MainActivity.EXTRA_MESSAGE, 0);
        List<AuthorItem> contentList = new JSONParser(this).parse();
        AuthorItem item = contentList.get(position);

        setContentView(R.layout.activity_scrolling);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(item.getName());
        toolbar.setTitleTextColor(getResources().getColor(R.color.primary_text));
        toolbar.setNavigationIcon(R.drawable.arrow_left);
        setSupportActionBar(toolbar);

        TextView infotxt = (TextView) findViewById(R.id.textInfo);
        infotxt.setText("альбомов: " + item.getAlbums() + ", песен: " + item.getTracks());
        TextView extratxt = (TextView) findViewById(R.id.textGenres);
        extratxt.setText(CustomListAdapter.transformArray(item.getGenres()));
        ImageView imageView = (ImageView) findViewById(R.id.iView);
        //скачиваем картинку и ставим ее в наш ImageView, также она кешируется
        Ion.with(getApplicationContext())
                .load(item.getCover().getBig())
                .intoImageView(imageView);
        TextView textView = (TextView) findViewById(R.id.textViewDescription);

        textView.setText(item.getDescription());
    }
}
