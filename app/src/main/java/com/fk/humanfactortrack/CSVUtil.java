package com.fk.humanfactortrack;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class CSVUtil {

    public static final String TAG="com.fk.humanfactortrack/CSVUtil";
    /**
     * 读取 CSV 文件
     */
    public static List<Result> readCSV(String filename) {
        File sdCard = Environment.getExternalStorageDirectory();
        Log.i(TAG,"sdcard path: "+sdCard.getAbsolutePath());
        File file = new File(sdCard.getAbsolutePath(), filename);
        LinkedList<Result> list = new LinkedList<>();
        BufferedReader reader = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis, "GBK");
            reader = new BufferedReader(isr);
            String line;
            int i=0;
            while ((line = reader.readLine()) != null) {
                if(i!=0){
                    String[] rows = line.split(",");
                    Result result = new Result();
                    result.setTester(rows.length > 0 ? rows[0] : null);
                    result.setIndex(rows.length > 1 ?Integer.parseInt(rows[1] ) : 0);
                    result.setPlace(rows.length > 2 ? Integer.parseInt(rows[2]) : 0);
                    result.setBeginTime(rows.length > 3 ? Long.parseLong(rows[3] ): 0);
                    result.setTipTime(rows.length > 4 ? Long.parseLong(rows[4]) : 0);
                    result.setTipBlankTime(rows.length > 5 ? Long.parseLong(rows[5]) : 0);
                    result.setActionTime(rows.length > 6 ? Long.parseLong(rows[6]) : 0);
                    result.setActionBlankTime(rows.length > 7 ? Long.parseLong(rows[7]) : 0);
                    result.setTrack(rows.length > 8 ? rows[8] : null);
                    result.setMisscount(rows.length > 9 ? Integer.parseInt(rows[9]) : 0);
                    list.add(result);
                }
                ++i;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
//        if (!list.isEmpty()) {
//            // 移除 CSV 首行标题
//            list.remove(0);
//        }
        return list;
    }



}
