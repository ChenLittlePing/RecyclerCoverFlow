package com.recycler.coverflow.lib;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * 继承RecyclerView重写{@link #getChildDrawingOrder(int, int)}对Item的绘制顺序进行控制
 *
 * @author Chen Xiaoping (562818444@qq.com)
 * @version V1.0
 * @Datetime 2017-04-18
 */

public class RecyclerCoverFlow extends RecyclerView {

    public RecyclerCoverFlow(Context context) {
        super(context);
        init(false);
    }

    public RecyclerCoverFlow(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(false);
    }

    public RecyclerCoverFlow(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(false);
    }

    private void init(boolean isFlat) {
        setLayoutManager(new CoverFlowLayoutManger(isFlat));
        setChildrenDrawingOrderEnabled(true); //开启重新排序
        setOverScrollMode(OVER_SCROLL_NEVER);
    }

    /**
     * 设置是否为普通平面滚动
     * @param isFlat true:平面滚动；false:叠加缩放滚动
     */
    public void setFlatFlow(boolean isFlat) {
        init(isFlat);
    }

    @Override
    public void setLayoutManager(LayoutManager layout) {
        if (!(layout instanceof CoverFlowLayoutManger)) {
            throw new IllegalArgumentException("The layout manager must be CoverFlowLayoutManger");
        }
        super.setLayoutManager(layout);
    }

    @Override
    protected int getChildDrawingOrder(int childCount, int i) {
        int center = getCoverFlowLayout().getCenterPosition()
                - getCoverFlowLayout().getFirstVisiblePosition(); //计算正在显示的所有Item的中间位置
        if (center < 0) center = 0;
        else if (center > childCount) center = childCount;
        int order;
        if (i == center) {
            order = childCount - 1;
        } else if (i > center) {
            order = center + childCount - 1 - i;
        } else {
            order = i;
        }
        return order;
    }

    /**
     * 获取LayoutManger，并强制转换为CoverFlowLayoutManger
     */
    public CoverFlowLayoutManger getCoverFlowLayout() {
        return ((CoverFlowLayoutManger)getLayoutManager());
    }

    /**
     * 获取被选中的Item位置
     */
    public int getSelectedPos() {
        return getCoverFlowLayout().getSelectedPos();
    }

    /**
     * 设置选中监听
     * @param l 监听接口
     */
    public void setOnItemSelectedListener(CoverFlowLayoutManger.OnSelected l) {
        getCoverFlowLayout().setOnSelectedListener(l);
    }
}
