package zy.com.dynamicrecyclerviewitemheight;

import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class LiveEventAdapter extends BaseMultiItemQuickAdapter<EventBean, BaseViewHolder> {
    public LiveEventAdapter(List<EventBean> data) {
        super(data);
        addItemType(EventBean.HOME, R.layout.item_live_home);
        addItemType(EventBean.AWAY, R.layout.item_live_away);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder bvh = super.onCreateViewHolder(parent, viewType);

        //动态设置View高度 第一种方式
//        TextView view = bvh.getView(R.id.text_live_view);
//        RelativeLayout relativeLayout = bvh.getView(R.id.root);
//        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
//        relativeLayout.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
//        int height = relativeLayout.getMeasuredHeight();
//        params.height = height;
//        view.setLayoutParams(params);

        //动态设置View高度 第二种方式
//        final TextView view = bvh.getView(R.id.text_live_view);
//        final RelativeLayout relativeLayout = bvh.getView(R.id.root);
//        final RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
//        relativeLayout.post(new Runnable()
//        {
//            public void run()
//            {
//                int height = relativeLayout.getMeasuredHeight();
//                params.height = height;
//                view.setLayoutParams(params);
//            }
//        });

        //动态设置View高度 第三种方式
//        final TextView view = bvh.getView(R.id.text_live_view);
//        final RelativeLayout relativeLayout = bvh.getView(R.id.root);
//        final RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) view.getLayoutParams();
//        relativeLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                int height = relativeLayout.getMeasuredHeight();
//                params.height = height;
//                view.setLayoutParams(params);
//            }
//        });

        return bvh;
    }

    @Override
    protected void convert(BaseViewHolder helper, EventBean item) {

        switch (helper.getItemViewType()) {
            case EventBean.HOME:
                helper.setText(R.id.text_live_time, item.getTime());
                helper.setText(R.id.text_live_text, item.getEvent());
                setHeight(helper);
                break;
            case EventBean.AWAY:
                helper.setText(R.id.text_live_time, item.getTime());
                helper.setText(R.id.text_live_text, item.getEvent());
                setHeight(helper);
                break;
        }
    }

    private void setHeight(BaseViewHolder bvh) {
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
    }

}
