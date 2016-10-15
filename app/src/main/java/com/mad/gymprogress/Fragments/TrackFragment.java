package com.mad.gymprogress.Fragments;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.mad.gymprogress.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrackFragment extends Fragment {

    TabLayout trackLayout;
    ViewPager viewPager;
    int int_items = 3;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;

/*
    public TrackFragment() {
        // Required empty public constructor
    }
*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate layout and setup views
        final View trackView = inflater.inflate(R.layout.tab_layout, null);
        trackLayout = (TabLayout) trackView.findViewById(R.id.tabs);
        viewPager = (ViewPager) trackView.findViewById(R.id.viewpager);

        //Set adapter for view pager
        viewPager.setAdapter(new TrackAdapter(getChildFragmentManager()));

        trackLayout.post(new Runnable() {
            @Override
            public void run() {
                trackLayout.setupWithViewPager(viewPager);
            }
        });

        mAuth = FirebaseAuth.getInstance();

        return trackView;
    }

    class TrackAdapter extends FragmentPagerAdapter {

        public TrackAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        /**
         * Return fragment with respect to Position .
         */
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new EnterWeightFragment();
                case 1:
                    return new HistoryFragment();
                case 2:
                    return new ProgressFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return int_items;
        }

        /**
         * This method returns the title of the tab according to the position.
         */
        @Override
        public CharSequence getPageTitle(int position) {

            switch (position) {
                case 0:
                    return "Track";
                case 1:
                    return "History";
                case 2:
                    return "Progress";
            }
            return null;
        }
    }
}
