package com.recycler.coverflow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import recycler.coverflow.CoverFlowLayoutManger;
import recycler.coverflow.RecyclerCoverFlow;

/**
 * 3D 旋转
 *
 * @author Chen Xiaoping (562818444@qq.com)
 * @version RecyclerCoverFlow
 * @Datetime 2020-09-01 09:21
 * @since RecyclerCoverFlow
 */
public class CoverFlow3DActivity extends AppCompatActivity implements Adapter.onItemClick {

    private RecyclerCoverFlow mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_just_coverflow);
        initList();
    }

    private void initList() {
        mList = findViewById(R.id.list);
        mList.set3DItem(true); //3D 滚动
        mList.setLoop(); //循环滚动
        mList.setAdapter(new Adapter(this, this, true));
        mList.setOnItemSelectedListener(new CoverFlowLayoutManger.OnSelected() {
            @Override
            public void onItemSelected(int position) {
                ((TextView)findViewById(R.id.index)).setText((position+1)+"/"+mList.getLayoutManager().getItemCount());
            }
        });
    }

    @Override
    public void clickItem(int pos) {
        mList.smoothScrollToPosition(pos);
    }
}