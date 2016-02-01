package cz.jalasoft.mobile.swimming.android.activity.fragment.setting;

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

        checkBox = findCheckBox(view);

        final boolean isEnabled = serviceRegistry().poolTrackingService().configuration().isEnabled();
        checkBox.setChecked(isEnabled);

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isTrackingEnabled = ((CheckBox) v).isChecked();
                saveEnabled(isTrackingEnabled);
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = !checkBox.isChecked();
                checkBox.setChecked(isChecked);
                saveEnabled(isChecked);
            }
        });

        return view;
    }

    private CheckBox findCheckBox(View view) {
        return (CheckBox) view.findViewById(R.id.enableTrackingCheckboxId);
    }

    private void saveEnabled(boolean enabled) {
        serviceRegistry().poolTrackingService().enableTracking(enabled);
    }
}
