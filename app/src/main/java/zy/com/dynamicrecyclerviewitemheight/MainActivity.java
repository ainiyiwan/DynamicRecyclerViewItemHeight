package zy.com.dynamicrecyclerviewitemheight;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;

    private LiveEventAdapter mAdapter;
    private List<EventBean> list = new ArrayList<>();
    private List<String> stringList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        stringList.add("中国队进世界杯啦！");
        stringList.add("梅西进球啦！巴塞罗那万岁！巴塞罗那万岁！");
        stringList.add("C罗复出了！");
        stringList.add("为什么NBA的球员也出现了。。。勇士牛逼！库里牛逼！勇士总冠军！");
        stringList.add("库里牛逼！");
        for (int i = 0; i < 50; i++) {
            Random random = new Random();
            int itemType = random.nextInt(2);
            int time = random.nextInt(90);
            int event = random.nextInt(5);
            EventBean bean = new EventBean(itemType, time+"'", stringList.get(event));
            list.add(bean);
        }
        mAdapter = new LiveEventAdapter(list);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerview.setHasFixedSize(false);
        mRecyclerview.setAdapter(mAdapter);
    }
}
