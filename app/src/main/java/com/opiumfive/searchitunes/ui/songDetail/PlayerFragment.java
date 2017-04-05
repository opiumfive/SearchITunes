package com.opiumfive.searchitunes.ui.songDetail;


import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import com.opiumfive.searchitunes.R;
import com.opiumfive.searchitunes.model.Song;
import com.squareup.picasso.Picasso;
import java.io.IOException;


public class PlayerFragment extends Fragment implements
        MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {

    private static final String ARGUMENT_SONG_KEY = "key_song";

    private Button mButton;
    private MediaPlayer mMediaPlayer;
    private Song mSong;
    private SeekBar mSeekBar;
    private int mMusicPosition;
    private Handler mHandler = new Handler();

    public PlayerFragment() {
    }

    public static PlayerFragment newInstance(Song song) {
        PlayerFragment fragment = new PlayerFragment();
        Bundle arguments = new Bundle();
        arguments.putParcelable(ARGUMENT_SONG_KEY, song);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSong = getArguments().getParcelable(ARGUMENT_SONG_KEY);
        setRetainInstance(true); // set fragment retaining to prevent media player recreation
    }

    private void releaseMP() {
        if (mMediaPlayer != null) {
            try {
                mMediaPlayer.release();
                mMediaPlayer = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_player, container, false);

        ImageView imageView = (ImageView) v.findViewById(R.id.imageView2);
        TextView songText = (TextView) v.findViewById(R.id.textView);
        TextView artistText = (TextView) v.findViewById(R.id.textView2);
        mSeekBar = (SeekBar) v.findViewById(R.id.seekBar);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (mMediaPlayer != null && b) {
                    mMusicPosition = i * 1000;
                    mMediaPlayer.seekTo(mMusicPosition);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        mSeekBar.setMax(mSong.getTrackTimeMillis() / 10000);
        mButton = (Button) v.findViewById(R.id.button);
        boolean isPlaying = mMediaPlayer != null && mMediaPlayer.isPlaying();
        mButton.setText(isPlaying ? R.string.pause : R.string.start);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mButton.getText().toString().equals(getString(R.string.start))) {
                    startOrResume();
                } else {
                    pause();
                }
            }
        });
        Picasso.with(getActivity()).load(mSong.getArtworkUrl100()).into(imageView);
        songText.setText(mSong.getTrackName());
        artistText.setText(mSong.getArtistName());

        return v;
    }

    private void startOrResume() {
        if (mMediaPlayer != null && mMusicPosition > 0) {
            mMediaPlayer.seekTo(mMusicPosition);
            mMediaPlayer.start();
        } else {
            releaseMP();
            startMusic(mSong.getPreviewUrl());
        }
        mButton.setText(R.string.pause);
    }

    public void pause() {
        mButton.setText(R.string.start);
        if (mMediaPlayer != null && mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            mMusicPosition = mMediaPlayer.getCurrentPosition();
        }
    }

    public void startMusic(String uri) {
        try {
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setDataSource(uri);
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.setOnPreparedListener(this);
            mMediaPlayer.prepareAsync();
            if (mMediaPlayer == null) return;
            mMediaPlayer.setLooping(false);
            mMediaPlayer.setOnCompletionListener(this);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        mButton.setText(R.string.start);
        mSeekBar.setProgress(0);
        mMusicPosition = 0;
    }

    public void release() {
        releaseMP();
        mHandler.removeCallbacks(null);
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
        getActivity().runOnUiThread(mSongPositionRunnable);
    }

    private Runnable mSongPositionRunnable = new Runnable() {
        @Override
        public void run() {
            if (mMediaPlayer != null && mMediaPlayer.isPlaying()){
                int mCurrentPosition = mMediaPlayer.getCurrentPosition() / 1000;
                mSeekBar.setProgress(mCurrentPosition);
            }
            mHandler.postDelayed(this, 500);
        }
    };
}
