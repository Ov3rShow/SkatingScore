package com.sartore.matteo.skatingscore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
                TextView nameTxtView = view.findViewById(R.id.resultlist_nametxtview);
                TextView infoTxtView = view.findViewById(R.id.resultlist_info);
                ImageView placementImgView = view.findViewById(R.id.resultlist_PlacementIcon);
                TextView placementTxtView = view.findViewById(R.id.resultlist_PlacementTextView);


                if (nameTxtView != null) {
                    String str = threeStrings.getName().substring(0,1).toUpperCase()+threeStrings.getName().substring(1);
                    nameTxtView.setText(str);
                }

                if(infoTxtView != null)
                {
                    infoTxtView.setText(threeStrings.getTitolo().concat(" - ").concat(threeStrings.getSocieta()));
                }

                if(placementImgView != null)
                {
                    switch (position)
                    {
                        case 0:
                        {
                            placementImgView.setImageDrawable(getContext().getDrawable(R.drawable.best));
                            placementImgView.setVisibility(View.VISIBLE);
                            placementTxtView.setVisibility(View.INVISIBLE);
                            break;
                        }
                        case 1:
                        {
                            placementImgView.setImageDrawable(getContext().getDrawable(R.drawable.second));
                            placementImgView.setVisibility(View.VISIBLE);
                            placementTxtView.setVisibility(View.INVISIBLE);
                            break;
                        }
                        case 2:
                        {
                            placementImgView.setImageDrawable(getContext().getDrawable(R.drawable.third));
                            placementImgView.setVisibility(View.VISIBLE);
                            placementTxtView.setVisibility(View.INVISIBLE);
                            break;
                        }
                        default:
                        {
                            placementTxtView.setText(String.valueOf(position + 1));
                            placementTxtView.setVisibility(View.VISIBLE);
                            placementImgView.setVisibility(View.INVISIBLE);
                            break;
                        }
                    }
                }
            }

            return view;
        }
}
