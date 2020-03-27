package com.practice.englishcards.utils;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class AssetsUtil {

    public static String getJsonFromAssets(Context context, String fileName) {
        String jsonString;
        try {
            InputStream is = context.getAssets().open(fileName);

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            jsonString = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return jsonString;
    }

    public static List<String> getFileNames(Context context, String folderName) throws IOException {
        AssetManager assetManager = context.getAssets();
        String[] files = assetManager.list(folderName);
        return Arrays.asList(files);
    }

}
