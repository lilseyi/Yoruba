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
public class PhrasesFragment extends Fragment {
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


    public PhrasesFragment() {
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

        words.add(new Word("what's new?","Kí ló nṣẹlẹ̀?",R.raw.phrase_whats_new));
        words.add(new Word("what's your name?","Kínni orúkọ yín?",R.raw.phrase_whats_your_name));
        words.add(new Word("nice to meet you","Ó dára láti rí ọ",R.raw.phrase_it_was_nice_meeting_you));
        words.add(new Word("you're very kind","O ní inú rere púpọ̀",R.raw.phrase_youre_very_kind));
        words.add(new Word("where are you from?","Níbo ni o ti wá?",R.raw.phrase_where_are_you_from));
        words.add(new Word("are you married?","Ṣé o ti ṣe ìgbéyàwó?",R.raw.phrase_are_you_married));
        words.add(new Word("I will be right back","Mà á padà wá",R.raw.phrase_i_will_be_right_back));
        words.add(new Word("it was nice meeting you","Ó dára láti bá ọ yín pàdé",R.raw.phrase_it_was_nice_meeting_you));
        words.add(new Word("I love you","Mo nífẹ̀ẹ́ rẹ",R.raw.phrase_i_love_you));
        words.add(new Word("I really like it","Mo fẹ́ ẹ nítòótọ́",R.raw.phrase_i_really_like_it));


        //LinearLayout rootView = (LinearLayout) findViewById(R.id.rootView);

        WordAdaptor adapter = new WordAdaptor(getActivity(), words,R.color.category_phrases);

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
