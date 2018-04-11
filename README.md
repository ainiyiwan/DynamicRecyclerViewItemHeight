# RecyclerView动态设置Item高度的三种方式（可用于任意子View）

### 效果
先看下要实现的效果

![这里写图片描述](https://github.com/ainiyiwan/DynamicRecyclerViewItemHeight/blob/master/png/dynamic.gif)
### 问题
**可以看到中间那条灰线的高度是动态变化的**，一开始觉得很好实现，直接获取Item的高度的然后设置给View不就好了吗？
问题来了，获取Item的高度始终为0。原因是ItemView还没有测量完成，高度自然为0。我们都知道View绘制的顺序是onMeasure()—— onLayout()—— onDraw()，所以第一种解法来了，手动让ItemView测量一下不就好了吗？
### 方法一 手动调用measure()

```
		//动态设置View高度 第一种方式
        TextView view = bvh.getView(R.id.text_live_view);
        RelativeLayout relativeLayout = bvh.getView(R.id.root);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
        relativeLayout.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int height = relativeLayout.getMeasuredHeight();
        params.height = height;
        view.setLayoutParams(params);
```
### 方法二 调用View.post()

```
//动态设置View高度 第二种方式
        final TextView view = bvh.getView(R.id.text_live_view);
        final RelativeLayout relativeLayout = bvh.getView(R.id.root);
        final RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
        relativeLayout.post(new Runnable()
        {
            public void run()
            {
                int height = relativeLayout.getMeasuredHeight();
                params.height = height;
                view.setLayoutParams(params);
            }
        });
```
### 方法三 添加addOnGlobalLayoutListener()

```
  //动态设置View高度 第三种方式
        final TextView view = bvh.getView(R.id.text_live_view);
        final RelativeLayout relativeLayout = bvh.getView(R.id.root);
        final RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
        relativeLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int height = relativeLayout.getMeasuredHeight();
                params.height = height;
                view.setLayoutParams(params);
            }
        });
```
### 总结
总体的思路都是在View显示之前改变高度，如果还有其他方法，欢迎补充！
### [GitHub地址](https://github.com/ainiyiwan/DynamicRecyclerViewItemHeight)

### 参考

 - [https://stackoverflow.com/questions/38764254/how-to-get-the-height-of-recyclerview-s-item-in-onbindviewholder](https://stackoverflow.com/questions/38764254/how-to-get-the-height-of-recyclerview-s-item-in-onbindviewholder)
 - [https://stackoverflow.com/questions/8200896/how-to-find-the-width-of-the-a-view-before-the-view-is-displayed](https://stackoverflow.com/questions/8200896/how-to-find-the-width-of-the-a-view-before-the-view-is-displayed)
 - [https://stackoverflow.com/questions/26566954/square-layout-on-gridlayoutmanager-for-recyclerview/26575808#26575808](https://stackoverflow.com/questions/26566954/square-layout-on-gridlayoutmanager-for-recyclerview/26575808#26575808)