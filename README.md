# RecyclerCoverFlow
使用RecyclerView，自定义LayoutManager实现旋转木马相册效果

![image](https://github.com/ChenLittlePing/RecyclerCoverFlow/blob/master/gif/demo.gif)

<b>Gradle依赖</b><br>
 `compile 'com.chenlittleping:recyclercoverflow:1.0.5'`

# 使用方式
### 1,xml中加入
```xml
    <recycler.coverflow.RecyclerCoverFlow
            android:id="@+id/list"
            android:layout_width="match_parent"
            android:layout_height="match_parent">
    </recycler.coverflow.RecyclerCoverFlow>
```
### 2，Activity中初始化，其中Adapter与RecyclerView的Adapter完全一致
```java
    mList = (RecyclerCoverFlow) findViewById(R.id.list);
    //        mList.setFlatFlow(true); //平面滚动
    mList.setAdapter(new Adapter(this));
    mList.setOnItemSelectedListener(new CoverFlowLayoutManger.OnSelected() {
        @Override
        public void onItemSelected(int position) {
            ((TextView)findViewById(R.id.index)).setText((position+1)+"/"+mList.getLayoutManager().getItemCount());
        }
    });
```
