package com.practice.englishcards.words;

import com.practice.englishcards.db.entry.Classes;

import java.util.List;

public interface IMainView {
    void show(List<Classes> list);
    void showError(Object msg);
}
