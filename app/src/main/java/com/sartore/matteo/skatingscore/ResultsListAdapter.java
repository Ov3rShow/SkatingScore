package com.sartore.matteo.skatingscore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Matteo on 20/06/2017.
 */

public class ResultsListAdapter extends ArrayAdapter<RankClass>
{
        private int layoutResource;

        public ResultsListAdapter(Context context, int layoutResource, List<RankClass> threeStringsList) {
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

            RankClass threeStrings = getItem(position);

            if (threeStrings != null) {
                TextView nametxtview = view.findViewById(R.id.resultlist_nametxtview);
                //TextView placetxtview = view.findViewById(R.id.resultlist_place);
                TextView infotxtview = view.findViewById(R.id.resultlist_info);

                if (nametxtview != null) {
                    String str = String.valueOf(position+1) + " - " + threeStrings.getName().substring(0,1).toUpperCase()+threeStrings.getName().substring(1);
                    nametxtview.setText(str);
                }

                /*if (placetxtview != null) {
                    /tring placetxtiew = "Vitt. maggiori: " + String.valueOf(threeStrings.getMajorityDecisions());
                    placetxtview.setText(placetxtiew);
                }*/

                if(infotxtview != null)
                {
                    infotxtview.setText(threeStrings.getTitolo() + " - " + threeStrings.getSocieta());
                }
            }

            return view;
        }
}
