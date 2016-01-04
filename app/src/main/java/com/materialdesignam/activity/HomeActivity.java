package com.materialdesignam.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.materialdesignam.R;
import com.materialdesignam.widget.circlerefresh.CircleRefreshLayout;

/**
 * Created by AMing on 15/12/17.
 * Company RongCloud
 */
public class HomeActivity extends Activity {

    private CircleRefreshLayout mRefreshLayout;
    private ListView mList;
    private Button mStop, mNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);

        mRefreshLayout = (CircleRefreshLayout) findViewById(R.id.refresh_layout);
        mList = (ListView) findViewById(R.id.list);
        mStop = (Button) findViewById(R.id.stop_refresh);
        mNext = (Button) findViewById(R.id.next);
        String[] strs = {
                "The",
                "Canvas",
                "class",
                "holds",
                "the",
                "draw",
                "calls",
                ".",
                "To",
                "draw",
                "something,",
                "you",
                "need",
                "4 basic",
                "components",
                "Bitmap",
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strs);
        mList.setAdapter(adapter);
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Class<?> activity = null;
                if (position % 2 == 0) {
                    activity = RotateActivity.class;
                } else {
                    activity = NewtonCradleActivity.class;
                }
                if (null != activity) {
                    Intent intent = new Intent(HomeActivity.this, activity);
                    startActivity(intent);
                }
            }
        });

        mStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRefreshLayout.finishRefreshing();
            }
        });

        mRefreshLayout.setOnRefreshListener(
                new CircleRefreshLayout.OnCircleRefreshListener() {
                    @Override
                    public void refreshing() {
                        // do something when refresh starts
                    }

                    @Override
                    public void completeRefresh() {
                        // do something when refresh complete
                    }
                });
        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, OtherActivity.class));
                finish();
            }
        });

    }
}
