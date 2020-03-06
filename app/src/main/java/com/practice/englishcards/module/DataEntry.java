package com.practice.englishcards.module;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.socks.library.KLog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/** Data
 * [{"className":"class_1","data":[{"En":"American","Ch":"美國1"},{"En":"American","Ch":"美國2"},{"En":"American","Ch":"美國3"},{"En":"American","Ch":"美國4"},{"En":"American","Ch":"美國5"},{"En":"American","Ch":"美國6"},{"En":"American","Ch":"美國7"},{"En":"American","Ch":"美國8"},{"En":"American","Ch":"美國9"},{"En":"American","Ch":"美國10"}]},{"className":"class_2","data":[{"En":"American","Ch":"美國1"},{"En":"American","Ch":"美國2"},{"En":"American","Ch":"美國3"},{"En":"American","Ch":"美國4"},{"En":"American","Ch":"美國5"},{"En":"American","Ch":"美國6"},{"En":"American","Ch":"美國7"},{"En":"American","Ch":"美國8"},{"En":"American","Ch":"美國9"},{"En":"American","Ch":"美國10"}]}]
 */
public class DataEntry<Vocabulary> {

    // annotations : 當php 後台用 下劃線風格時，可以容錯 , 當多種情況同時出現，以最後一個出現的值為準
    @SerializedName(value = "className", alternate = {"name", "class_name"})
    private String className;
    private List<Vocabulary> data;

    public String getClassName() {
        return className;
    }

    public List<Vocabulary> getData() {
        return data;
    }

    @NonNull
    @Override
    public String toString() {
        String log = String.format(Locale.US,"className : %s ",className);
        return log;
    }

    public class Vocabulary implements Serializable{
        public String En = "";
        public String Ch = "";
        private List<String> studyDay ;

        public List<String> getStudyDay() {
            KLog.i("TAG","days :"+studyDay.size());
            return studyDay;
        }

        public void setStudyDay(String day) {
            KLog.i("TAG","setStudyDay :"+studyDay.size());
            this.studyDay.add(day);
            KLog.i("TAG","days :"+studyDay.size());
        }

        @NonNull
        @Override
        public String toString() {
            String log = String.format(Locale.US,"En : %s ",En);
            return log;
        }
    }
}
