package com.practice.englishcards.db.entry;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.Locale;

public class WordItem implements Serializable {
    public String en = "";
    public String ch = "";
    public int frequency = 21;
    public boolean isSuc = false ;

    public WordItem() {

    }

    public WordItem(String en, String ch, int frequency, boolean isSuc) {
        this.en = en;
        this.ch = ch;
        this.frequency = frequency;
        this.isSuc = isSuc;
    }

    public WordItem(String en ,String ch){
        this.en = en ;
        this.ch = ch ;
    }

    @NonNull
    @Override
    public String toString() {
        String log = String.format(Locale.US,"%s = %s",en,ch);
        return log;
    }

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getCh() {
        return ch;
    }

    public void setCh(String ch) {
        this.ch = ch;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public boolean isSuc() {
        return isSuc;
    }

    public void setSuc(boolean suc) {
        isSuc = suc;
    }
}
