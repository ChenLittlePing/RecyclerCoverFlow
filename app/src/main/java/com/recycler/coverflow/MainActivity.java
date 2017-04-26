package com.recycler.coverflow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.recycler.coverflow.lib.CoverFlowLayoutManger;
import com.recycler.coverflow.lib.RecyclerCoverFlow;

public class MainActivity extends AppCompatActivity {

    private RecyclerCoverFlow mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initList();
    }

    private void initList() {
        mList = (RecyclerCoverFlow) findViewById(R.id.list);
        mList.setFlatFlow(true); //平面滚动
        mList.setAdapter(new Adapter(this));
        mList.setOnItemSelectedListener(new CoverFlowLayoutManger.OnSelected() {
            @Override
            public void onItemSelected(int position) {
                ((TextView)findViewById(R.id.index)).setText((position+1)+"/"+mList.getLayoutManager().getItemCount());
            }
        });
    }
}
