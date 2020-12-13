package com.yaroslavm87.testtask01.View;

import android.widget.TextView;
import com.yaroslavm87.testtask01.Notifications.Subscriber;

public abstract class ActivityTextView implements Subscriber {

    protected TextView textView;

    public ActivityTextView(TextView textView) {
        this.textView = textView;
    }

    public TextView getTextView() {
        return textView;
    }
}