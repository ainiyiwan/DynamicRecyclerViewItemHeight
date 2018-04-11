package zy.com.dynamicrecyclerviewitemheight;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class EventBean implements MultiItemEntity {
    public static final int HOME = 0;
    public static final int AWAY = 1;

    private int itemType;
    private String time;
    private String event;

    public EventBean(int itemType, String time, String event) {
        this.itemType = itemType;
        this.time = time;
        this.event = event;
    }

    public String getTime() {
        return time;
    }

    public String getEvent() {
        return event;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
