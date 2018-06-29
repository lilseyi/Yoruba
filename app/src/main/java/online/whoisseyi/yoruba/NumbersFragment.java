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
public class NumbersFragment extends Fragment {
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


    public NumbersFragment() {
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

        words.add(new Word("one","ẹyokan",R.drawable.number_one,R.raw.number_one));
        words.add(new Word("two","meji",R.drawable.number_two,R.raw.number_two));
        words.add(new Word("three","meta",R.drawable.number_three,R.raw.number_three));
        words.add(new Word("four","merin",R.drawable.number_four,R.raw.number_four));
        words.add(new Word("five","marun",R.drawable.number_five,R.raw.number_five));
        words.add(new Word("six","mefa",R.drawable.number_six,R.raw.number_six));
        words.add(new Word("seven","meje",R.drawable.number_seven,R.raw.number_seven));
        words.add(new Word("eight","mejo",R.drawable.number_eight,R.raw.number_eight));
        words.add(new Word("nine","mesan",R.drawable.number_nine,R.raw.number_nine));
        words.add(new Word("ten","mewa",R.drawable.number_ten,R.raw.number_ten));


        //LinearLayout rootView = (LinearLayout) findViewById(R.id.rootView);

        WordAdaptor adapter = new WordAdaptor(getActivity(), words,R.color.category_numbers);

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
