package com.example.james.science;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;


public class SurveyAdapter extends ArrayAdapter<SurveyItem> {
    private ArrayList<SurveyItem> objects = new ArrayList<SurveyItem>();
    private LayoutInflater inflater = (LayoutInflater)getContext().getSystemService
            (Context.LAYOUT_INFLATER_SERVICE);

    public SurveyAdapter(Context context, int resource, List<SurveyItem>objects) {
        super(context, resource, objects);
        this.objects = (ArrayList<SurveyItem>) objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = inflater.inflate(R.layout.list_item_view, null);
        }
        SurveyItem surveyItem = objects.get(position);
        ((TextView)convertView.findViewById(R.id.listViewName))
                .setText(surveyItem.getUsername());
        ((RatingBar)convertView.findViewById(R.id.listViewRatingBar))
                .setRating(surveyItem.getTotalRating());
        return convertView;
    }
}
