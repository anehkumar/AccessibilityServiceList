package com.accessbilitycustom;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static final String myClass = "hike"; // Name of your class you want to show
    ArrayList<LocalObject> localObjects;
    AccessibilityAdapter accessibilityAdapter;
    ListView listView;
    int MyAppPosition;
    int CountAfterApp = 0;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView = (ListView) findViewById(R.id.accessService);
        imageView = (ImageView) findViewById(R.id.hand);
        localObjects = new ArrayList<LocalObject>();
        final AccessibilityManager accessibilityManager = (AccessibilityManager) getSystemService(Context.ACCESSIBILITY_SERVICE);
        PackageManager localPackageManager = getPackageManager();
        List localList = accessibilityManager.getInstalledAccessibilityServiceList();

        for (int a = 0; a < localList.size(); a++) {
            AccessibilityServiceInfo localObject = (AccessibilityServiceInfo) localList.get(a);
            LocalObject object = new LocalObject();
            object.setApplicationName(((AccessibilityServiceInfo) localObject).getResolveInfo().serviceInfo.loadLabel(localPackageManager).toString());
            object.setApplicationStatus((((AccessibilityServiceInfo) localObject).getResolveInfo().serviceInfo.isEnabled()) ? "On" : "Off");

            if (myClass.equals(object.getApplicationName())) {
                MyAppPosition = a;
                localObjects.add(object);
                CountAfterApp = CountAfterApp + 1;
            } else {
                if (CountAfterApp > 0) {
                    if (CountAfterApp <= 2) {
                        localObjects.add(object);
                    }
                    CountAfterApp = CountAfterApp + 1;
                }
            }
        }

        accessibilityAdapter = new AccessibilityAdapter(localObjects, this);
        listView.setAdapter(accessibilityAdapter);

        /* Animation */
        TranslateAnimation animation = new TranslateAnimation(400.0f, 200.0f,
                0.0f, 0.0f);          //  new TranslateAnimation(xFrom,xTo, yFrom,yTo)
        animation.setDuration(2000);  // animation duration
        animation.setFillAfter(true);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                listView.requestFocusFromTouch();
                listView.setSelection(0);
                listView.performItemClick(listView.getAdapter().getView(0, null, null), 0, 0);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        imageView.startAnimation(animation);


    }
}
