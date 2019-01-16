package com.inner.lovetao.channel.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.jess.arms.base.AdapterViewPager;

import java.util.List;

/**
 * Author feihaokui.
 * Date 2019-01-15.
 */
public class ShelvesAdapterViewPager extends AdapterViewPager {

    public ShelvesAdapterViewPager(FragmentManager fragmentManager, List<Fragment> list) {
        super(fragmentManager, list);
    }
}
