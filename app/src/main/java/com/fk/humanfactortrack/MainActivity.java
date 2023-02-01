package com.fk.humanfactortrack;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseAty {

    private Button btn_test_track;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initViews() {
        btn_test_track=findViewById(R.id.btn_test_track);
    }

    @Override
    protected void initViewsData() {
        btn_test_track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHumanFactor(MainActivity.this);
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void updateUI() {

    }

    private void toHumanFactor(Context pContext) {
        Intent intent = new Intent(this, HumanFactorAty.class);
        pContext.startActivity(intent);
    }
}