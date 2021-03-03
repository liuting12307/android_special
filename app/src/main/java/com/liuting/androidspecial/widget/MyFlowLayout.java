package com.liuting.androidspecial.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MyFlowLayout extends ViewGroup {
    private final ArrayList<List<View>> mAllViews;
    private final ArrayList<Integer> mPerLineMaxHeight;

    public MyFlowLayout(Context context) {
        super(context);
        mAllViews=new ArrayList<>();
        mPerLineMaxHeight=new ArrayList<>();
    }

    public MyFlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        mAllViews=new ArrayList<>();
        mPerLineMaxHeight=new ArrayList<>();
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        super.generateLayoutParams(attrs);
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        //获得容器中子view的个数
        int childCount=getChildCount();
        //记录每一行view的总宽度
        int totalLineWith=0;
        //记录每一行最高view的高度
        int perLineMaxHeight = 0;
        //记录当前ViewGroup的总高度
        int totalHeight = 0;
        for(int i=0;i<childCount;i++){
            View childView=getChildAt(i);
            //对子view进行测量
            measureChild(childView,widthMeasureSpec,heightMeasureSpec);
            MarginLayoutParams lp = (MarginLayoutParams)childView.getLayoutParams();
            //获得子view的测量宽度
            int childWidth=childView.getMeasuredWidth()+lp.leftMargin+lp.rightMargin;
            //获得子view的测量高度
            int childHeight=childView.getMeasuredHeight()+lp.bottomMargin+lp.topMargin;
            Log.d("lt","childHeight="+childHeight);
            if(totalLineWith+childWidth>widthSize){
                //统计总高度
                totalHeight+=perLineMaxHeight;
                //开启新的一行
                totalLineWith=childWidth;
                perLineMaxHeight=childHeight;
            }else{
                //记录每一行的总宽度
                totalLineWith+=childWidth;
                //比较每一行最高height
                perLineMaxHeight= Math.max(perLineMaxHeight,childHeight);
            }
            //当该View已是最后一个View时，将该行最大高度添加到totalHeight中
            if(i==childCount-1){
                totalHeight+=perLineMaxHeight;
            }
        }
        Log.d("lt","totalHeight="+totalHeight);
        //如果高度的测量模式是EXACTLY，则高度用测量值，否则用计算出来的总高度（这时高度的设置为wrap_content）
        heightSize=heightMode== MeasureSpec.EXACTLY?heightSize:totalHeight;
        setMeasuredDimension(widthSize,heightSize);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mAllViews.clear();
        mPerLineMaxHeight.clear();
        //存放每一行的子View
        List<View> lineViews=new ArrayList<>();
        //记录每一行已存放View的总宽度
        int totalLineWidth = 0;
        //记录每一行最高View的高度
        int lineMaxHeight = 0;
        /****遍历所有View，将View添加到List<List<View>>集合中**********/
        //获得子View的总个数
        int childCount = getChildCount();
        for(int i=0;i<childCount;i++){
            View childView = getChildAt(i);
            MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();
            int childWidth = childView.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            int childHeight = childView.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
            if (totalLineWidth + childWidth > getWidth()) {
                mAllViews.add(lineViews);
                mPerLineMaxHeight.add(lineMaxHeight);
                //开启新的一行
                totalLineWidth = 0;
                lineMaxHeight = 0;
                lineViews = new ArrayList<>();
            }
            totalLineWidth += childWidth;
            lineViews.add(childView);
            lineMaxHeight = Math.max(lineMaxHeight, childHeight);
        }
        //单独处理最后一行
        mAllViews.add(lineViews);
        mPerLineMaxHeight.add(lineMaxHeight);
        /************遍历集合中的所有View并显示出来************/
        //表示一个View和父容器左边的距离
        int mLeft = 0;
        //表示View和父容器顶部的距离
        int mTop = 0;
        for(int i=0;i<mAllViews.size();i++){
            lineViews=mAllViews.get(i);
            lineMaxHeight=mPerLineMaxHeight.get(i);
            for(int j=0;j<lineViews.size();j++){
                View childView = lineViews.get(j);
                MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();
                int leftChild = mLeft + lp.leftMargin;
                int topChild = mTop + lp.topMargin;
                int rightChild = leftChild + childView.getMeasuredWidth();
                int bottomChild = topChild + childView.getMeasuredHeight();
                //四个参数分别表示View的左上角和右下角
                childView.layout(leftChild, topChild, rightChild, bottomChild);
                mLeft += lp.leftMargin + childView.getMeasuredWidth() + lp.rightMargin;
            }
            mLeft=0;
            mTop += lineMaxHeight;
        }
    }
}
