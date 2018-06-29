package online.whoisseyi.yoruba;


import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FamilyFragment extends Fragment {
    private AudioManager audioManager;
    private MediaPlayer mediaPlayer;
    private AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int i) {
            switch(i){
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                    mediaPlayer.pause();
                    mediaPlayer.seekTo(0);
                    break;
                case AudioManager.AUDIOFOCUS_GAIN:
                    mediaPlayer.start();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS:
                    releaseMediaPlayer();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    mediaPlayer.pause();
                    mediaPlayer.seekTo(0);
                    break;
            }
        }
    };

    private MediaPlayer.OnCompletionListener onCompletionListener =new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };


    public FamilyFragment() {
        // Required empty public constructor
    }

    private void releaseMediaPlayer() {
        if(mediaPlayer != null){
            audioManager.abandonAudioFocus(onAudioFocusChangeListener);
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        TextView textView = new TextView(getActivity());
        View rootView = inflater.inflate(R.layout.word_list, container, false);


        final ArrayList<Word> words = new ArrayList<>();
        audioManager =(AudioManager) getActivity().getSystemService(getContext().AUDIO_SERVICE);

        words.add(new Word("daughter","Ọmọbìnrin",R.drawable.family_daughter,R.raw.family_daugther));
        words.add(new Word("father","baba",R.drawable.family_father,R.raw.family_father));
        words.add(new Word("grandfather","baba-baba",R.drawable.family_grandfather,R.raw.family_grandfather));
        words.add(new Word("grandmother","iya-iya",R.drawable.family_grandmother,R.raw.family_grandmother));
        words.add(new Word("mother","iya",R.drawable.family_mother,R.raw.family_mother));
        words.add(new Word("brother","egbon",R.drawable.family_older_brother,R.raw.family_brother));
        words.add(new Word("sister","aburo obirin",R.drawable.family_older_sister,R.raw.family_sister));
        words.add(new Word("son","omokunrin",R.drawable.family_son,R.raw.family_son));
        words.add(new Word("husband","Ọkọ",R.drawable.family_father,R.raw.family_husband));
        words.add(new Word("wife","iyawo",R.drawable.family_mother,R.raw.family_wife));


        //LinearLayout rootView = (LinearLayout) findViewById(R.id.rootView);

        WordAdaptor adapter = new WordAdaptor(getActivity(), words,R.color.category_family);

        ListView listView = (ListView) rootView.findViewById(R.id.list);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();
                int result =  audioManager.requestAudioFocus(onAudioFocusChangeListener,
                        AudioManager.STREAM_MUSIC,
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(getActivity(), words.get(i).getAudio());
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(onCompletionListener);
                }            }
        });


        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

}
