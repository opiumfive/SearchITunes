package com.opiumfive.searchitunes.ui.songsList;


import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
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
    private static final int SPAN_LIST = 1;
    private static final int SPAN_PORTRAIT = 2;
    private static final int SPAN_LANDSCAPE = 3;
    private static final String SAVE_INSTANCE_KEY = "key_array";

    private SongsAdapter mSongsAdapter;
    private StaggeredGridLayoutManager mLayoutManager;
    private List<Song> mResults;
    private TextView mEmptyView;
    private Switch mToggleButton;
    private int mSpanNumber = SPAN_PORTRAIT;
    private Call<Songs> mGetSongsCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mToggleButton = (Switch) findViewById(R.id.switch1);
        RecyclerView songsRecyclerView = (RecyclerView) findViewById(R.id.rv);
        songsRecyclerView.setHasFixedSize(true);
        int orientation = getResources().getConfiguration().orientation;
        if (orientation != Configuration.ORIENTATION_PORTRAIT) mSpanNumber = SPAN_LANDSCAPE;
        mLayoutManager = new StaggeredGridLayoutManager(mSpanNumber,StaggeredGridLayoutManager.VERTICAL);
        songsRecyclerView.setLayoutManager(mLayoutManager);

        mSongsAdapter = new SongsAdapter(this);
        songsRecyclerView.setAdapter(mSongsAdapter);
        mEmptyView = (TextView) findViewById(R.id.empty);

        if (savedInstanceState != null) {
            Songs songs = savedInstanceState.getParcelable(SAVE_INSTANCE_KEY);
            if (songs != null) {
                mResults = songs.getResults();
                if (mResults == null || mResults.isEmpty()) {
                    mEmptyView.setVisibility(View.VISIBLE);
                } else {
                    mEmptyView.setVisibility(View.GONE);
                }
                mSongsAdapter.updateItems(mResults);
            }
        }

        mToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mLayoutManager.setSpanCount(mLayoutManager.getSpanCount() == mSpanNumber ? SPAN_LIST : mSpanNumber);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.length() >= MIN_QUERY_LENGTH) {
                    getSongsList(query);
                } else {
                    showMessage(R.string.min_query_warning);
                }
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_search) {
            onSearchRequested();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String search = intent.getStringExtra(SearchManager.QUERY);
            if (search != null && search.length() >= MIN_QUERY_LENGTH) {
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
        mEmptyView.setVisibility(View.GONE);
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
            if (mResults.isEmpty()) mEmptyView.setVisibility(View.VISIBLE);
        } else {
            showMessage(R.string.some_error);
        }
    }

    @Override
    public void onFailure(Call<Songs> call, Throwable t) {
        removeProgress();
        if (mResults.isEmpty()) mEmptyView.setVisibility(View.VISIBLE);
        showMessage(R.string.some_error);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // release calls and listeners
        if (mGetSongsCall != null) mGetSongsCall.cancel();
        mToggleButton.setOnCheckedChangeListener(null);
    }
}
