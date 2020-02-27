package com.practice.englishcards.utils;

import android.content.Context;
import android.speech.tts.TextToSpeech;

import com.socks.library.KLog;

import java.util.Locale;

/** TextToSpeech
 *  setLanguage() : 設定語言
 *  setPitch() : 語調(1為正常語調；0.5比正常語調低一倍；2比正常語調高一倍)
 *  setSpeechRate() :  速度(1為正常速度；0.5比正常速度慢一倍；2比正常速度快一倍)
 */
public class SpeakUtil {
    private TextToSpeech tts;
    private static SpeakUtil ins;

    public static synchronized SpeakUtil getIns() {
        if (ins == null) {
            ins = new SpeakUtil();
        }
        return ins;
    }

    public void speak(Context context, final String str) {
        if (tts == null) {
            tts = new TextToSpeech(context, new TextToSpeech.OnInitListener() {
                @Override
                public void onInit(int status) {
                    KLog.i("status :" + status);
                    if (status == TextToSpeech.SUCCESS) {
                        int result = tts.setLanguage(Locale.US);
                        if (result == TextToSpeech.LANG_MISSING_DATA
                                || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                            KLog.e("TTS", "This Language is not supported");
                        } else {
                            tts.setPitch(1);
                            tts.setSpeechRate(1);
                        }
                        if (tts != null && !tts.isSpeaking()) {
                            KLog.d("startVoice:" + str);
                            tts.speak(str, TextToSpeech.QUEUE_FLUSH, null);
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
        }
    }
}
