package yandex.app.activity;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import yandex.app.parseclasses.AuthorItem;
import yandex.app.CustomListAdapter;
import yandex.app.parseclasses.JSONParser;
import yandex.app.R;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.metanit.eugene.helloapplication.MESSAGE";
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      //  setSupportActionBar(toolbar);
        List<AuthorItem> contentList = new JSONParser(this).parse();
        CustomListAdapter adapter = new CustomListAdapter(this, contentList);
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub;
                Intent intent = new Intent(view.getContext(), ScrollingActivity.class);
                intent.putExtra(EXTRA_MESSAGE, position);
                startActivity(intent);
            }
        });
    }

}
