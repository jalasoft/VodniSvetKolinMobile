package cz.jalasoft.mobile.swimming.android.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cz.jalasoft.mobile.swimming.R;

/**
 * Created by Honza "Honzales" Lastovicka on 1/26/16.
 */
public final class SettingsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings, container, false);

        return view;
    }
}
