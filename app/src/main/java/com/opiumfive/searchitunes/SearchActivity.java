package com.opiumfive.searchitunes;

import android.app.SearchManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.List;

public class SearchActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    StaggeredGridLayoutManager sglm;
    List<Result> results;

    Switch toggleButton;
    int span_num = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        toggleButton = (Switch) findViewById(R.id.switch1);

        recyclerView = (RecyclerView) findViewById(R.id.rv);
        if (savedInstanceState != null) {
            Results re = savedInstanceState.getParcelable("array");
            results = re.getResults();
            adapter = new RecyclerViewAdapter(this, results);
            recyclerView.setAdapter(adapter);
        }
        recyclerView.setHasFixedSize(true);
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            span_num = 2;
        sglm = new StaggeredGridLayoutManager(span_num,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(sglm);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                sglm.setSpanCount(sglm.getSpanCount() == span_num ? 1 : span_num);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.search) {
            onSearchRequested();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        ///
        if(Intent.ACTION_SEARCH.equals(intent.getAction())) {
            // Здесь будет храниться то, что пользователь ввёл в поисковой строке
            String search = intent.getStringExtra(SearchManager.QUERY); // поисковой запрос
            if (search.length()>=5) { // проверяем количество символов
                //запросик апи
                doStuff(search);
            } else
                Toast.makeText(this,"Минимальный запрос - 5 символов.", Toast.LENGTH_SHORT).show();
        }
    }

    public void doStuff (String s) {
        RetrofitHelper rfh = new RetrofitHelper(s);
        try {
            results = rfh.execute().get();
        }
        catch (Exception e) {
            System.out.println("Some Exception");
        }

        adapter = new RecyclerViewAdapter(this, results);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Results re = new Results();
        re.setResults(results);
        outState.putParcelable("array", re);
    }
}
