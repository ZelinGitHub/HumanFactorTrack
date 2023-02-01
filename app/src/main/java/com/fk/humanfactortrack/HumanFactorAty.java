package com.fk.humanfactortrack;



import static com.fk.humanfactortrack.CollectionUtil.parseResultsToData;
import static com.fk.humanfactortrack.StringUtil.pathStrToPairList;

import android.util.Log;
import android.util.Pair;
import android.widget.Button;
import android.widget.GridLayout;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HumanFactorAty extends BaseAty {

    public static final String TAG = "com.fk.humanfactortrack/HumanFactorAty";


    private GridLayout grid_layout;
    private final List<HumanFactorView> mViews = new ArrayList<>();
    private final Map<Integer, List<String>> mData = new HashMap<>();

    @Override
    protected void initView() {
        setContentView(R.layout.activity_humanfactor);
    }

    @Override
    protected void initViews() {
        grid_layout = (GridLayout) findViewById(R.id.grid_layout);
    }

    @Override
    protected void initViewsData() {
        //设置行数
        grid_layout.setRowCount(6);
        //设置列数
        grid_layout.setColumnCount(3);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                HumanFactorView humanFactorView = new HumanFactorView(this);
                humanFactorView.setBackgroundColor(getResources().getColor(R.color.blueff0d79cdPrimary));
                humanFactorView.setMinimumWidth(1);
                humanFactorView.setMinimumHeight(1);
                // 添加到grid中
                GridLayout.Spec rowSpec = GridLayout.spec(i, GridLayout.FILL, 1f);
                GridLayout.Spec columnSpec = GridLayout.spec(j, GridLayout.FILL, 1f);

                GridLayout.LayoutParams params = new GridLayout.LayoutParams(rowSpec, columnSpec);
                //添加按钮到网格布局
                grid_layout.addView(humanFactorView, params);
//                grid_layout.addView(button, params);
                mViews.add(humanFactorView);
            }
        }
    }


    @Override
    protected void initData() {
        List<Result> results = CSVUtil.readCSV("testdata-1.csv");
        Log.i(TAG,"results: "+results);
        parseResultsToData(results,mData);
        for(int i=0;i<mViews.size();i++){
            HumanFactorView view=mViews.get(i);
            List<String> paths=mData.get(i);
            if (paths != null) {
                for(String path:paths){
                    List<Pair<Double, Double>> pairs = pathStrToPairList(path);
                    Log.i(TAG, "place: "+i+"pathPair: " + pairs.toString());
                    view.addPathPairs(pairs);
                }
                view.invalidate();
            }
        }
    }


    @Override
    protected void updateUI() {

    }


}
