package com.fk.humanfactortrack;

import android.util.Log;
import android.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    public static final String TAG = "com.fk.humanfactortrack/StringUtil";

    public static List<String> extractMessageByRegular(String msg) {
        List<String> list = new ArrayList<>();
        Pattern p = Pattern.compile("\\((.*?)\\)");
        Matcher m = p.matcher(msg);
        while (m.find()) {
            list.add(m.group().substring(1, m.group().length() - 1));
        }
        return list;
    }

    public static Pair<Double, Double> parseStrToPair(String str) {
        //s代表空格
        String[] strs = str.split(" ");
        String number0 = strs[0].substring(strs[0].indexOf(":") + 1);
        String number1 = strs[1].substring(strs[1].indexOf(":") + 1);
//        Log.i(TAG, number0 + "," + number1);
        return new Pair<>(
                Double.parseDouble(number0)
                , Double.parseDouble(number1)
        );
    }

    public static List<Pair<Double, Double>> pathStrToPairList(String pathStr) {
        List<String> list = extractMessageByRegular(pathStr);
        Log.i(TAG, list.toString());
        List<Pair<Double, Double>> pairs = new ArrayList<>();
        for (String ele : list) {
            Pair<Double, Double> e = parseStrToPair(ele);
            pairs.add(e);
        }
        return pairs;
    }

}
