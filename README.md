# RecyclerCoverFlow
使用RecyclerView，自定义LayoutManager实现旋转木马相册效果

![image](https://github.com/ChenLittlePing/RecyclerCoverFlow/blob/master/gif/demo.gif)

## Gradle依赖
请查看最新版本：[Release](https://github.com/ChenLittlePing/RecyclerCoverFlow/releases)

如：`compile 'com.chenlittleping:recyclercoverflow:1.0.6'`

## 使用方式
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

## 实现原理：

https://www.jianshu.com/p/1837a801e599
