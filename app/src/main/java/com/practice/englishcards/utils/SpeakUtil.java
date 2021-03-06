package com.practice.englishcards.utils;

import android.content.Context;
import android.speech.tts.TextToSpeech;

import com.practice.englishcards.db.entry.WordItem;
import com.socks.library.KLog;

import java.util.List;
import java.util.Locale;

/**
 * TextToSpeech
 * setLanguage() : 設定語言
 * setPitch() : 語調(1為正常語調；0.5比正常語調低一倍；2比正常語調高一倍)
 * setSpeechRate() :  速度(1為正常速度；0.5比正常速度慢一倍；2比正常速度快一倍)
 */
public class SpeakUtil {
    private static TextToSpeech tts;
//    private static TextToSpeech ttsCH;
    private static SpeakUtil ins;
    private static Context context;

    public SpeakUtil(Context context) {
        this.context = context;
        initTts();
//        initTtsCh();
    }

    public static synchronized SpeakUtil getIns(Context context) {
        if (ins == null) {
            ins = new SpeakUtil(context);
        }
        if(tts == null){
            initTts();
        }
        return ins;
    }

//    private void initTtsCh() {
//        if (ttsCH == null) {
//            ttsCH = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
//                @Override
//                public void onInit(int status) {
//                    KLog.i("TTS", "status :" + status);
//                    if (status == TextToSpeech.SUCCESS) {
//                        int result = ttsCH.setLanguage(Locale.CHINESE);
//                        KLog.i("TTS", "result :" + result);
//                        if (result == TextToSpeech.LANG_MISSING_DATA
//                                || result == TextToSpeech.LANG_NOT_SUPPORTED) {
//                            KLog.e("TTS", "This Language is not supported");
//                        } else {
//                            ttsCH.setPitch(1);
//                            ttsCH.setSpeechRate(1);
//                        }
//                    } else if (status == TextToSpeech.ERROR) {
//                        ttsCH = null;
//                    }
//                }
//            });
//        }
//    }

    public void speakEn(String str) {
        tts.speak(str, TextToSpeech.QUEUE_FLUSH, null);
        tts.playSilentUtterance(1000, TextToSpeech.QUEUE_ADD, null);
    }

    public void speakCH(String str){
        if(tts == null){
            initTts();
        }
        tts.speak(str, TextToSpeech.QUEUE_FLUSH, null);
        tts.playSilentUtterance(1000, TextToSpeech.QUEUE_ADD, null);
    }

    char ch = ' ';
    String temp = "";

    public void speak(final List<WordItem> speech) {

        initTts();
        for (int counter = 0; counter < speech.size(); counter++) {
            temp = speech.get(counter).en;
            if (ch == ' ' || counter == (speech.size() - 1)) {
                tts.speak(temp, TextToSpeech.QUEUE_ADD, null, null);
                tts.playSilentUtterance(1000, TextToSpeech.QUEUE_ADD, null);
                temp = "";
            }
        }
    }

    private static void initTts() {
        if (tts == null) {
            tts = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    KLog.i("TTS", "status :" + status);
                    if (status == TextToSpeech.SUCCESS) {
                        int result = tts.setLanguage(Locale.US);
                        KLog.i("TTS", "result :" + result);
                        if (result == TextToSpeech.LANG_MISSING_DATA
                                || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                            KLog.e("TTS", "This Language is not supported");
                        } else {
                            tts.setPitch(1);
                            tts.setSpeechRate(1);
                        }
                    } else if (status == TextToSpeech.ERROR) {
                        tts = null;
                    }
                }
            });
        }
    }

    public void shutdown() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
            tts = null;
        }
    }
}
