package com.opiumfive.searchitunes;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.IOException;


public class PlayerFragment extends Fragment implements MediaPlayer.OnPreparedListener,
        MediaPlayer.OnCompletionListener {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ImageView imageView;
    private TextView songText;
    private TextView artistText;
    private SeekBar seekBar;
    private Button button;
    MediaPlayer mediaPlayer;
    AudioManager am;

    // TODO: Rename and change types of parameters


    String audio_url = "";
    String art_url = "";
    String song = "";
    String artist = "";
    int song_length = 0;

    public PlayerFragment() {
        // Required empty public constructor
    }


    public static PlayerFragment newInstance(String param1, String param2) {
        PlayerFragment fragment = new PlayerFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        am = (AudioManager) getActivity().getSystemService(getActivity().AUDIO_SERVICE);

    }

    private void releaseMP() {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.release();
                mediaPlayer = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_player, container, false);
        Intent intent = getActivity().getIntent();
        audio_url = intent.getStringExtra("audio_url");
        art_url = intent.getStringExtra("artwork_url");
        song = intent.getStringExtra("song");
        artist = intent.getStringExtra("artist");
        song_length = Integer.parseInt(intent.getStringExtra("song_length"));
        imageView = (ImageView) v.findViewById(R.id.imageView2);
        songText = (TextView) v.findViewById(R.id.textView);
        artistText = (TextView) v.findViewById(R.id.textView2);
        seekBar = (SeekBar) v.findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if(mediaPlayer != null && b){
                   mediaPlayer.seekTo(i * 1000);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        seekBar.setMax(song_length / 10000);
        button = (Button) v.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (button.getText().toString().equals("Старт")) {

                    releaseMP();
                    startMusic(audio_url);
                    button.setText("Пауза");
                } else

                {
                    button.setText("Старт");
                    if (mediaPlayer.isPlaying())
                        mediaPlayer.pause();
                }


            }
        });
        Picasso.with(getActivity()).load(art_url).into(imageView);
        songText.setText(song);
        artistText.setText(artist);
        return v;
    }

    public void startMusic(String uri) {
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(uri);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.prepareAsync();
            if (mediaPlayer == null)
                return;
            mediaPlayer.setLooping(false);
            mediaPlayer.setOnCompletionListener(this);

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        button.setText("Старт");
        seekBar.setProgress(0);
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
        final Handler mHandler = new Handler();

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer != null){
                    int mCurrentPosition = mediaPlayer.getCurrentPosition() / 1000;
                    seekBar.setProgress(mCurrentPosition);
                }
                mHandler.postDelayed(this, 1000);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releaseMP();

    }
}
