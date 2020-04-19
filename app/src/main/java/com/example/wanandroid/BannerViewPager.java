package com.example.wanandroid;

import android.content.Context;
import android.graphics.Path;
import android.icu.text.RelativeDateTimeFormatter;
import android.text.Layout;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class  BannerViewPager extends ViewPager {


    private int showTime = 4000;
    private Direction direction = Direction.LEFT;

    public BannerViewPager(@NonNull Context context) {
        super(context);
    }

    public BannerViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setShowTime(int showTime) {
        this.showTime = showTime;
    }

    public void start() {
        stop();
        postDelayed(player, showTime);

    }


    public void stop() {
        removeCallbacks(player);

    }

    public enum Direction {
        LEFT,
        RIGHT
    }

    private Runnable player = new Runnable() {
        @Override
        public void run() {
            play(direction);
        }
    };

    private synchronized void play(Direction direction) {
        PagerAdapter pagerAdapter = getAdapter();
        if (pagerAdapter != null) {
            int count = pagerAdapter.getCount();
            int currentItem = getCurrentItem();
            switch (direction) {
                case LEFT:
                    currentItem++;
                    if (currentItem >= count)
                        currentItem = 0;
                        break;

                case RIGHT:
                    currentItem--;

                    if (currentItem < 0)
                        currentItem = count - 1;
                    break;
            }
            setCurrentItem(currentItem);

        }
        start();

    }
}
