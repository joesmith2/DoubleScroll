package com.example.josephsmith.doublescroll;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;


public class MainActivity extends ActionBarActivity {

    ScrollView textScrollView, imageScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textScrollView = (ScrollView) findViewById(R.id.textScrollView);
        imageScrollView = (ScrollView) findViewById(R.id.imageScrollView);


        final ViewTreeObserver.OnScrollChangedListener onScrollChangedListener = new
                ViewTreeObserver.OnScrollChangedListener() {

                    @Override
                    public void onScrollChanged() {
                        Log.d("Joe's App", "scroll position is:" + textScrollView.getScrollY());
                        imageScrollView.scrollTo(0, textScrollView.getScrollY()*1000/1260 );
                    };
                };

        textScrollView.setOnTouchListener(new View.OnTouchListener() {
            private ViewTreeObserver observer;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (observer == null) {
                    observer = textScrollView.getViewTreeObserver();
                    observer.addOnScrollChangedListener(onScrollChangedListener);
                } else if (!observer.isAlive()) {
                    observer.removeOnScrollChangedListener(onScrollChangedListener);
                    observer = textScrollView.getViewTreeObserver();
                    observer.addOnScrollChangedListener(onScrollChangedListener);
                }
                return false;
            }
        });
}
}
