package com.geekapp.materialweather.views;

import android.content.Context;
import android.preference.SwitchPreference;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geekapp.materialweather.R;
import com.rey.material.widget.Switch;

/**
 * Created by james.li on 2016/1/21.
 */
public class MaterialSwitchPreference extends SwitchPreference{

    private Switch aSwitch;

    public MaterialSwitchPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public MaterialSwitchPreference(Context context) {
        super(context);
    }

    public MaterialSwitchPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected View onCreateView(ViewGroup parent) {
        super.onCreateView(parent);
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.material_switch, parent, false);
        aSwitch = (Switch) view.findViewById(R.id.switches);
        return view;
    }
}
