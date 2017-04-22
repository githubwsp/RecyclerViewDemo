package com.wsp.thinkpad.recyclerviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    LinearLayout oneLl,twoLl,threeLl,fourLl;
    TextView oneTv,twoTv,threeTv,fourTv;
    ImageView oneIv,twoIv,threeIv,fourIv;
    FragmentSwitchTool fragmentSwitchTool;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        initView();
        initFragment();

    }

    private void initFragment() {
        fragmentSwitchTool =new FragmentSwitchTool(getFragmentManager(),R.id.framelayout);
        fragmentSwitchTool.setClickableViews(oneLl,twoLl,threeLl,fourLl);
        fragmentSwitchTool.addSelectedViews(new View[]{oneTv,oneIv})
                            .addSelectedViews(new View[]{twoTv,twoIv})
                            .addSelectedViews(new View[]{threeTv,threeIv})
                            .addSelectedViews(new View[]{fourTv,fourIv});
        fragmentSwitchTool.setFragments(OneFragment.class,TwoFragment.class,ThreeFragment.class,FourFragment.class);
        fragmentSwitchTool.changeTag(oneLl);

    }

    private void initView() {
        oneLl= (LinearLayout) findViewById(R.id.one_ll);
        twoLl= (LinearLayout) findViewById(R.id.two_ll);
        threeLl= (LinearLayout) findViewById(R.id.three_ll);
        fourLl= (LinearLayout) findViewById(R.id.four_ll);

        oneIv= (ImageView) findViewById(R.id.one_iv);
        twoIv= (ImageView) findViewById(R.id.two_iv);
        threeIv= (ImageView) findViewById(R.id.three_iv);
        fourIv= (ImageView) findViewById(R.id.four_iv);

        oneTv= (TextView) findViewById(R.id.one_tv);
        twoTv= (TextView) findViewById(R.id.two_tv);
        threeTv= (TextView) findViewById(R.id.three_tv);
        fourTv= (TextView) findViewById(R.id.four_tv);
    }
}
