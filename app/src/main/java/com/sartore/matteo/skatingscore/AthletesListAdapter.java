package com.sartore.matteo.skatingscore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Matteo on 15/06/2017.
 */

public class AthletesListAdapter extends ArrayAdapter<AthleteClass> {
    private int layoutResource;

    public AthletesListAdapter(Context context, int layoutResource, List<AthleteClass> threeStringsList) {
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
            TextView nametxtview = view.findViewById(R.id.athlist_nametxtview);
            TextView societytxtview = view.findViewById(R.id.athlist_societytxtview);
            TextView ordertxtview = view.findViewById(R.id.athlist_ordertxtview);

            if (nametxtview != null) {
                try{
                    String str = threeStrings.getName().substring(0,1).toUpperCase()+threeStrings.getName().substring(1);
                    nametxtview.setText(str);
                }
                catch (Exception ex)
                {

                }
            }

            if (societytxtview != null) {
                societytxtview.setText(threeStrings.getTitle() + " - " + threeStrings.getSociety());
            }

            /*if (ordertxtview != null) {
                ordertxtview.setText(String.valueOf(threeStrings.getPosition()));
            }*/
        }
        return view;
    }
}
