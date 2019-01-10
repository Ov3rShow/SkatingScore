package com.sartore.matteo.skatingscore;

import android.content.Context;
import android.content.res.Resources;
import android.support.design.internal.ScrimInsetsFrameLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Matteo on 17/06/2017.
 */

public class ScoresListAdapter extends ArrayAdapter<AthleteClass> {
    private int layoutResource;

    public ScoresListAdapter(Context context, int layoutResource, List<AthleteClass> threeStringsList) {
        super(context, layoutResource, threeStringsList);
        this.layoutResource = layoutResource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(layoutResource, null);
        }

        AthleteClass threeStrings = getItem(position);

        if (threeStrings != null) {
            TextView nametxtview = view.findViewById(R.id.scorelist_nametxtview);
            TextView scorestxtview = view.findViewById(R.id.scorelist_scoresxtview);
            TextView totaltxtview = view.findViewById(R.id.scorelist_totaltxtview);

            if (nametxtview != null) {
                String str = threeStrings.getName().substring(0,1).toUpperCase()+threeStrings.getName().substring(1);
                nametxtview.setText(str);
            }

            if (scorestxtview != null) {
                try
                {
                    scorestxtview.setText(threeStrings.getScore().ToString().replace("/", " - "));
                }
                catch (Exception ex)
                {
                    scorestxtview.setText(Resources.getSystem().getString(R.string.str_scoresMissing));
                }
            }

            if (totaltxtview != null) {
                totaltxtview.setText(String.valueOf(threeStrings.getScore().getTotal()).substring(0, String.valueOf(threeStrings.getScore().getTotal()).indexOf(".")+2));
            }
        }
        return view;
    }
}
