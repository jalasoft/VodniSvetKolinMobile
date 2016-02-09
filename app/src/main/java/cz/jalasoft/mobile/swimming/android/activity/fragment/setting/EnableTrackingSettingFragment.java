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

    private Listener listener = Listener.DUMMY;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setting_enable_tracking, container, false);

        checkBox = findCheckBox(view);

        final boolean isEnabled = applicationService().trackingConfiguration().isEnabled();
        checkBox.setChecked(isEnabled);

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isTrackingEnabled = ((CheckBox) v).isChecked();
                notifyListener(isTrackingEnabled);
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isChecked = !checkBox.isChecked();
                checkBox.setChecked(isChecked);
                notifyListener(isChecked);
            }
        });

        return view;
    }

    private CheckBox findCheckBox(View view) {
        return (CheckBox) view.findViewById(R.id.enableTrackingCheckboxId);
    }

    private void notifyListener(boolean enabled) {
        if (enabled) {
            listener.onTrackingEnabled();
        } else {
            listener.onTrackingDisabled();
        }
    }

    //---------------------------------------------------------------------------------
    //PUBLIC INTERFACE
    //---------------------------------------------------------------------------------

    public void setListener(Listener listener) {
        if (listener == null) {
            throw new IllegalArgumentException("Listener must not be null.");
        }
        this.listener = listener;
    }

    public void unsetListener() {
        this.listener = Listener.DUMMY;
    }

    //----------------------------------------------------------------------------------
    //LISTENER
    // ---------------------------------------------------------------------------------

    public interface Listener {

        Listener DUMMY = new Listener() {
            @Override
            public void onTrackingEnabled() {}

            @Override
            public void onTrackingDisabled() {}
        };

        void onTrackingEnabled();

        void onTrackingDisabled();
    }
}
