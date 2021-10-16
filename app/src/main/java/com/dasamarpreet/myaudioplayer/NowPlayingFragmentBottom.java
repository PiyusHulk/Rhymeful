package com.dasamarpreet.myaudioplayer;

import static com.dasamarpreet.myaudioplayer.MainActivity.PATH_TO_FRAG;
import static com.dasamarpreet.myaudioplayer.MainActivity.SHOW_MINI_PLAYER;
import static com.dasamarpreet.myaudioplayer.MainActivity.SONG_NAME_TO_FRAG;

import android.media.MediaMetadataRetriever;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import pl.droidsonroids.gif.GifImageView;

public class NowPlayingFragmentBottom extends Fragment{
    ImageView albumArt;
    TextView songName;
    View view;
    static GifImageView gifImageView;

    public NowPlayingFragmentBottom() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_now_playing_bottom, container, false);
        gifImageView = view.findViewById(R.id.musicBarGif);
        songName = view.findViewById(R.id.song_name_miniPlayer);
        albumArt = view.findViewById(R.id.bottom_album_art);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (SHOW_MINI_PLAYER){
            if (PATH_TO_FRAG != null){
                byte[] art = getAlbumArt(PATH_TO_FRAG);
                if (art != null){
                    Glide.with(getContext()).load(art).into(albumArt);
                }
                else{
                    Glide.with(getContext()).load(R.drawable.logo_variant_no_bg).into(albumArt);
                }
                songName.setText(SONG_NAME_TO_FRAG);
            }
        }
    }

    private byte[] getAlbumArt(String uri){
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(uri);
        byte[] art = retriever.getEmbeddedPicture();
        retriever.release();
        return art;
    }
}