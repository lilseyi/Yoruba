package online.whoisseyi.yoruba;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

public class Word {
    private String defaultTranslation;
    private String miwokTranslation;
    private int picture;
    private int audio;


    public Word(String defaultTranslation, String miwokTranslation) {
        this.defaultTranslation = defaultTranslation;
        this.miwokTranslation = miwokTranslation;
        this.picture = -1;
        this.audio = -1;
    }
//    public Word(String defaultTranslation, String miwokTranslation, int picture) {
//        this.defaultTranslation = defaultTranslation;
//        this.miwokTranslation = miwokTranslation;
//        this.picture = picture;
//        this.audio = -1;
//    }
    public Word(String defaultTranslation, String miwokTranslation, int picture, int audio) {
        this.defaultTranslation = defaultTranslation;
        this.miwokTranslation = miwokTranslation;
        this.picture = picture;
        this.audio =audio;
    }
    public Word(String defaultTranslation, String miwokTranslation, int audio) {
        this.defaultTranslation = defaultTranslation;
        this.miwokTranslation = miwokTranslation;
        this.picture = -1;
        this.audio =audio;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public String getDefaultTranslation() {
        return defaultTranslation;
    }

    public void setDefaultTranslation(String defaultTranslation) {
        this.defaultTranslation = defaultTranslation;
    }

    public String getMiwokTranslation() {
        return miwokTranslation;
    }

    public void setMiwokTranslation(String miwokTranslation) {
        this.miwokTranslation = miwokTranslation;
    }

    public int getAudio() {
        return audio;
    }
    public void setAudio() {
        this.audio = audio;
    }
}
