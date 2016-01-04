package com.materialdesignam.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.material.am.button.CircleButton;
import com.materialdesignam.R;

/**
 * Created by AMing on 15/12/17.
 * Company RongCloud
 */
public class OtherActivity extends Activity {
    private CircleButton mNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_activity);
        mNext = (CircleButton) findViewById(R.id.button2);
        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OtherActivity.this, DialogPlusActivity.class));
                finish();
            }
        });
    }
}
