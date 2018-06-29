package online.whoisseyi.yoruba;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class WordAdaptor extends ArrayAdapter<Word>{
    private Context context;
    private ArrayList<Word> words;
    private int color;
    private MediaPlayer mediaPlayer;

    public WordAdaptor(@NonNull Context context, @NonNull ArrayList<Word> words) {
        super(context, 0,words);
        this.context = context;
        this.words = words;
    }
    public WordAdaptor(@NonNull Context context, @NonNull ArrayList<Word> words, int color) {
        super(context, 0,words);
        this.context = context;
        this.words = words;
        this.color = color;

    }

    @NonNull
    @Override

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        final Word currentWord = words.get(position);
        if(listItemView ==null) {
            listItemView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        }
//        if(currentWord.getPicture() == 0)
//            listItemView = LayoutInflater.from(context).inflate(R.layout.list_item_no_pic, parent, false);


        TextView defaultTranslation = (TextView)listItemView.findViewById(R.id.default_text_view);
        TextView miwokTranslation = (TextView)listItemView.findViewById(R.id.miwok_text_view);
        ImageView image = (ImageView) listItemView.findViewById(R.id.imageView);
        ConstraintLayout constraintLayout = (ConstraintLayout) listItemView.findViewById(R.id.constraintLayout);




        if(currentWord.getPicture() != -1)
            image.setImageResource(currentWord.getPicture());

        else
            image.setVisibility(View.GONE);

        constraintLayout.setBackgroundResource(color);

        defaultTranslation.setText(currentWord.getDefaultTranslation());
        miwokTranslation.setText(currentWord.getMiwokTranslation());

     //   mediaPlayer = MediaPlayer.create(context,currentWord.getAudio());
//        linearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mediaPlayer = MediaPlayer.create(context,currentWord.getAudio());
//                mediaPlayer.start();
//            }
//        });


        return listItemView;


    }
}
