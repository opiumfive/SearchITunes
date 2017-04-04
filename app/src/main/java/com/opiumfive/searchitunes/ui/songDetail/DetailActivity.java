package com.opiumfive.searchitunes.ui.songDetail;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import com.opiumfive.searchitunes.R;
import com.opiumfive.searchitunes.model.Song;
import com.opiumfive.searchitunes.ui.BaseActivity;


public class DetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        if (intent != null) {
            Song song = intent.getParcelableExtra("song");
            if (song != null) {
                if (null == savedInstanceState) {
                    initFragment(PlayerFragment.newInstance(song));
                }
            }
        }
    }

    private void initFragment(Fragment detailFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.container, detailFragment);
        transaction.commit();
    }
}
