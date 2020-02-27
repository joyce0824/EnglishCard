package com.practice.englishcards;

import java.util.ArrayList;
import java.util.List;

/** Data
 * [{"className":"class_1","data":[{"En":"American","Ch":"美國1"},{"En":"American","Ch":"美國2"},{"En":"American","Ch":"美國3"},{"En":"American","Ch":"美國4"},{"En":"American","Ch":"美國5"},{"En":"American","Ch":"美國6"},{"En":"American","Ch":"美國7"},{"En":"American","Ch":"美國8"},{"En":"American","Ch":"美國9"},{"En":"American","Ch":"美國10"}]},{"className":"class_2","data":[{"En":"American","Ch":"美國1"},{"En":"American","Ch":"美國2"},{"En":"American","Ch":"美國3"},{"En":"American","Ch":"美國4"},{"En":"American","Ch":"美國5"},{"En":"American","Ch":"美國6"},{"En":"American","Ch":"美國7"},{"En":"American","Ch":"美國8"},{"En":"American","Ch":"美國9"},{"En":"American","Ch":"美國10"}]}]
 */
public class DataEntry<Vocabulary>{

    private String className;
    private List<Vocabulary> data;

    public String getClassName() {
        return className;
    }

    public List<Vocabulary> getData() {
        return data;
    }

    class Vocabulary {
        String En = "";
        String Ch = "";
    }
}
