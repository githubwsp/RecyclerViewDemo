package com.wsp.thinkpad.recyclerviewdemo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${吴心良}
 * on 2017/4/22.
 * description:
 */

public class FragmentSwitchTool implements View.OnClickListener {

    private FragmentManager fragmentManager;
    private Fragment currentFragment;
    private View[] currentSelectedView;
    private View[] clickableViews;
    private List<View[]> selectedViews;
    private Class<? extends Fragment>[] fragments;
    private Bundle[] bundles;
    private int containerId;
    private boolean showAnimator;

    public FragmentSwitchTool(FragmentManager fragmentManager, int containerId) {
        super();
        this.fragmentManager = fragmentManager;
        this.containerId = containerId;
    }

    public void setClickableViews(View... clickableViews) {
        this.clickableViews = clickableViews;
        for (View view : clickableViews) {
            view.setOnClickListener(this);
        }
    }

    public void setSelectedViews(List<View[]> selectedViews) {
        this.selectedViews = selectedViews;
    }

    public FragmentSwitchTool addSelectedViews(View... views){
        if (selectedViews == null) {
            selectedViews = new ArrayList<View[]>();
        }
        selectedViews.add(views);
        return this;
    }

    public void setFragments(Class<? extends Fragment>... fragments) {
        this.fragments = fragments;
    }

    public void setBundles(Bundle... bundles) {
        this.bundles = bundles;
    }

    public void changeTag(View v) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = fragmentManager.findFragmentByTag(String.valueOf(v.getId()));
        for (int i = 0; i < clickableViews.length; i++) {
            if (v.getId() == clickableViews[i].getId()) {

                //过渡动画
                if (showAnimator) {
                    int exitIndex = selectedViews.indexOf(currentSelectedView);
                    if (i > exitIndex){
//                        fragmentTransaction.setCustomAnimations(R.anim.slide_right_in, R.anim.slide_left_out);
                    } else if (i < exitIndex) {
//                        fragmentTransaction.setCustomAnimations(R.anim.slide_left_in, R.anim.slide_right_out);
                    }
                }
                //过渡动画

                if (fragment == null) {
                    if (currentFragment != null) {
                        fragmentTransaction.hide(currentFragment);
                        for (View view : currentSelectedView) {
                            view.setSelected(false);
                        }
                    }
                    try {
                        fragment = fragments[i].newInstance();

                        if (bundles != null && bundles[i] != null) {
                            fragment.setArguments(bundles[i]);
                        }

                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    fragmentTransaction.add(containerId, fragment, String.valueOf(clickableViews[i].getId()));
                } else if (fragment == currentFragment) {
                } else {
                    fragmentTransaction.hide(currentFragment);
                    for (View view : currentSelectedView) {
                        view.setSelected(false);
                    }
                    fragmentTransaction.show(fragment);
                }

                fragmentTransaction.commit();
                currentFragment = fragment;
                for (View view : selectedViews.get(i)) {
                    view.setSelected(true);
                }
                currentSelectedView = selectedViews.get(i);
                break;
            }
        }
    }

    @Override
    public void onClick(View v)
    {
        changeTag(v);
    }
}
