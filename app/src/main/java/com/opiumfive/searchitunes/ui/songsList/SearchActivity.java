package com.opiumfive.searchitunes.ui.songsList;


import android.app.SearchManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import com.opiumfive.searchitunes.R;
import com.opiumfive.searchitunes.api.Api;
import com.opiumfive.searchitunes.model.Song;
import com.opiumfive.searchitunes.model.Songs;
import com.opiumfive.searchitunes.ui.BaseActivity;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchActivity extends BaseActivity implements Callback<Songs> {

    private static final int MIN_QUERY_LENGTH = 5;
    private static final int SPAN_PORTRAIT = 2;
    private static final int SPAN_LANDSCAPE = 3;
    private static final String SAVE_INSTANCE_KEY = "key_array";

    private RecyclerView mSongsRecyclerView;
    private SongsAdapter mSongsAdapter;
    private StaggeredGridLayoutManager mLayoutManager;
    private List<Song> mResults;

    private Switch mToggleButton;
    private int mSpanNumber = SPAN_PORTRAIT;

    private Call<Songs> mGetSongsCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mToggleButton = (Switch) findViewById(R.id.switch1);
        mSongsRecyclerView = (RecyclerView) findViewById(R.id.rv);
        mSongsRecyclerView.setHasFixedSize(true);
        mLayoutManager = new StaggeredGridLayoutManager(mSpanNumber,StaggeredGridLayoutManager.VERTICAL);
        mSongsRecyclerView.setLayoutManager(mLayoutManager);

        mSongsAdapter = new SongsAdapter(this);
        mSongsRecyclerView.setAdapter(mSongsAdapter);

        if (savedInstanceState != null) {
            Songs songs = savedInstanceState.getParcelable(SAVE_INSTANCE_KEY);
            if (songs != null) {
                mResults = songs.getResults();
                mSongsAdapter.updateItems(mResults);
            }
        }

        if (getResources().getConfiguration().orientation != Configuration.ORIENTATION_PORTRAIT) {
            mSpanNumber = SPAN_LANDSCAPE;
        }

        mToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mLayoutManager.setSpanCount(mLayoutManager.getSpanCount() == mSpanNumber ? 1 : mSpanNumber);
                mSongsRecyclerView.requestLayout();
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
        if( Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String search = intent.getStringExtra(SearchManager.QUERY);
            if (search.length() >= MIN_QUERY_LENGTH) {
                getSongsList(search);
            } else {
                showMessage(R.string.min_query_warning);
            }
        }
    }

    public void getSongsList(String query) {
        mGetSongsCall = Api.getApiService().listTracks(query);
        mGetSongsCall.enqueue(this);
        showProgress();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Songs re = new Songs();
        re.setResults(mResults);
        outState.putParcelable(SAVE_INSTANCE_KEY, re);
    }

    @Override
    public void onResponse(Call<Songs> call, Response<Songs> response) {
        removeProgress();
        if (response.isSuccessful()) {
            mResults = response.body().getResults();
            mSongsAdapter.updateItems(mResults);
        } else {
            showMessage(R.string.some_error);
        }
    }

    @Override
    public void onFailure(Call<Songs> call, Throwable t) {
        removeProgress();
        showMessage(R.string.some_error);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mGetSongsCall != null) mGetSongsCall.cancel();
        mToggleButton.setOnCheckedChangeListener(null);
    }
}
