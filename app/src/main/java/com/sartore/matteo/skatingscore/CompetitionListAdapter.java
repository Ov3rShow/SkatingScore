package com.sartore.matteo.skatingscore;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Matteo on 12/06/2017.
 */

public class CompetitionListAdapter extends ArrayAdapter<CompetitionClass> {

    private int layoutResource;
    ColorStateList oldColors;

    public CompetitionListAdapter(Context context, int layoutResource, List<CompetitionClass> threeStringsList) {
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



        TextView titletextview = view.findViewById(R.id.list_titletxtview);
        TextView datetextview = view.findViewById(R.id.list_datetxtview);
        TextView placetextview = view.findViewById(R.id.list_placetxtview);

        //Se è la posizione attualmente selezionata coloro la riga di verde e il testo di bianco
        if(position == IndexActivity.current_competition_index)
        {
            view.setBackgroundColor(Color.parseColor("#4CAF50"));
            /*titletextview.setTextColor(Color.WHITE);
            datetextview.setTextColor(Color.WHITE);
            placetextview.setTextColor(Color.WHITE);*/
        }
        //Altrimenti riporto la riga ai colori standard
        else
        {
            //oldColors = titletextview.getTextColors();
            view.setBackgroundColor(Color.TRANSPARENT);
            /*titletextview.setTextColor(oldColors);
            datetextview.setTextColor(oldColors);
            placetextview.setTextColor(oldColors);*/
        }

        CompetitionClass threeStrings = getItem(position);

        if (threeStrings != null) {
            if (titletextview != null) {
                titletextview.setText(threeStrings.getTitle());
            }

            if (datetextview != null) {
                datetextview.setText(threeStrings.getDate());
            }

            if (placetextview != null) {
                placetextview.setText(threeStrings.getPlace());
            }
        }
        return view;
    }
}
