package com.fk.humanfactortrack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CollectionUtil {

    public static void parseResultsToData(List<Result> results, Map<Integer, List<String>> data) {
        for (Result result : results) {
            int place = result.getPlace();
            String track = result.getTrack();
            List<String> list1 = data.get(place);
            if (list1 != null) {
                list1.add(track);
            } else {
                list1 = new ArrayList<>();
                list1.add(track);
            }
            data.put(place, list1);
        }
    }
}
