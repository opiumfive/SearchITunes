package com.opiumfive.searchitunes.ui.songDetail;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import com.opiumfive.searchitunes.R;
import com.opiumfive.searchitunes.model.Song;
import com.opiumfive.searchitunes.ui.BaseActivity;

import static com.opiumfive.searchitunes.ui.songsList.SongsAdapter.EXTRA_KEY_SONG;


public class DetailActivity extends BaseActivity {

    private PlayerFragment mPlayerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        if (intent != null) {
            Song song = intent.getParcelableExtra(EXTRA_KEY_SONG);
            if (song != null) {
                if (savedInstanceState == null) {
                    initFragment(PlayerFragment.newInstance(song));
                }
            }
        }
    }

    // using retain fragment to save media player
    // later it will be easy to make master-detail flow if needed
    private void initFragment(Fragment detailFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.container, detailFragment);
        transaction.commit();
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        mPlayerFragment = (PlayerFragment) fragment;
    }

    @Override
    public void onPause() {
        super.onPause();

        boolean isJustBack = isFinishing() && !isChangingConfigurations();
        boolean isHiding = !isFinishing() && !isChangingConfigurations();

        // handling hiding app and config changes
        if (mPlayerFragment != null) {
            if (isHiding) {
                mPlayerFragment.pause();
            } else if (isJustBack) {
                mPlayerFragment.release();
            }
        }
    }
}
