package com.fk.humanfactortrack;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Pair;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class HumanFactorView extends View {
    public static final String TAG = "com.fk.humanfactortrack/HumanFactorView";
    private final List<List<Pair<Double, Double>>> mPathPairsList = new ArrayList<>();
    private final Paint mTrackPaint = new Paint();
    private final Paint mBorderPaint = new Paint();
    private final RectF mRectF = new RectF();
    private final Path mPath = new Path();

    public HumanFactorView(Context context) {
        this(context, null);
    }

    public HumanFactorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HumanFactorView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public HumanFactorView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mTrackPaint.setAntiAlias(true);
        mTrackPaint.setColor(getContext().getResources().getColor(R.color.orange_ringffff9434));
        mTrackPaint.setStyle(Paint.Style.STROKE);
        mTrackPaint.setStrokeWidth(2f);
        mBorderPaint.setAntiAlias(true);
        mBorderPaint.setColor(getContext().getResources().getColor(R.color.white));
        mBorderPaint.setStyle(Paint.Style.STROKE);
        mBorderPaint.setStrokeWidth(1f);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //测量模式，由父控件计算得到
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        //基于测量规格的默认测量尺寸
        int widthDefaultSize = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        int heightDefaultSize = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        //对AT_MOST特殊处理，否则AT_MOST和EXACTLY效果相同，也就是wrap_content和match_parent效果相同
        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(100, 100);
        } else if (widthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(100, heightDefaultSize);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthDefaultSize, 100);
        } else {
            setMeasuredDimension(widthDefaultSize, heightDefaultSize);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mRectF.left = 0f;
        mRectF.top = 0f;
        int width = getWidth();
        int height = getHeight();
        mRectF.right = width;
        mRectF.bottom = height;
        Log.i(TAG, "width: " + width + ", height: " + height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (List<Pair<Double, Double>> pathPairs : mPathPairsList) {
            Pair<Double, Double> firstPoint = pathPairs.get(0);
            mPath.moveTo(firstPoint.first.floatValue(), firstPoint.second.floatValue());
            for (int i = 0; i < pathPairs.size(); i++) {
                Pair<Double, Double> aPoint = pathPairs.get(i);
                mPath.lineTo(aPoint.first.floatValue(), aPoint.second.floatValue());
            }
        }
        canvas.drawPath(mPath, mTrackPaint);
        canvas.drawRect(mRectF, mBorderPaint);

//        canvas.drawText("1", 0, 50, mBorderPaint);
    }

    public void addPathPairs(List<Pair<Double, Double>> pathPairs) {
        if (pathPairs != null && !pathPairs.isEmpty()) {
            mPathPairsList.add(pathPairs);
        }
    }
}
