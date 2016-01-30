package cz.jalasoft.mobile.swimming.android.fragment.setting;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import cz.jalasoft.mobile.swimming.R;

import static cz.jalasoft.mobile.swimming.android.Application.*;

/**
 * Created by Honza "Honzales" Lastovicka on 1/26/16.
 */
public final class EnableTrackingSettingFragment extends Fragment {

    private CheckBox checkBox;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting_enable_tracking, container, false);

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isTrackingEnabled = ((CheckBox) v).isChecked();
                serviceRegistry().poolTrackingService().enableTracking(isTrackingEnabled);
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = checkBox.isChecked();
                checkBox.setChecked(!isChecked);
            }
        });
        checkBox = (CheckBox) getView().findViewById(R.id.enableTrackingCheckboxId);
    }
}
