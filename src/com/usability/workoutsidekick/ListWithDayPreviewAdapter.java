package com.usability.workoutsidekick;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.usability.workoutsidekick.ListWithCheckboxAdapter.ViewHolder;

public class ListWithDayPreviewAdapter extends ArrayAdapter<String> {

	  private final String[] list;
	  private final Activity context;
	  private ScheduleDAO dao;

	  public ListWithDayPreviewAdapter(Activity context, String[] list) {
	    super(context, R.layout.single_day, list);
	    this.context = context;
	    this.list = list;
	    dao = new ScheduleDAO(context);
	  }

	  static class ViewHolder {
	    protected TextView text;
	    protected TextView textPreview;
	  }

	  @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	    View view = null;
	    if (convertView == null) {
	      LayoutInflater inflator = context.getLayoutInflater();
	      view = inflator.inflate(R.layout.single_day, null);
	      final ViewHolder viewHolder = new ViewHolder();
	      viewHolder.text = (TextView) view.findViewById(R.id.dayName);
	      viewHolder.textPreview = (TextView) view.findViewById(R.id.dayPreview);
	      view.setTag(viewHolder);
	      viewHolder.textPreview.setTag(list[position]);
	    } else {
	      view = convertView;
	      ((ViewHolder) view.getTag()).textPreview.setTag(list[position]);
	    }
	    ViewHolder holder = (ViewHolder) view.getTag();
	    holder.text.setText(list[position]);
	    
	    dao.open();
	    List<Exercise> exercises = dao.getExercisesByDay(list[position]);
	    dao.close();
	    
	    String muscleList = getMusclesForDay(exercises);
	    
	    holder.textPreview.setText(muscleList);
	    return view;
	  }
	  
	  private String getMusclesForDay(List<Exercise> exercises){
		  
		  ArrayList<String> strings = new ArrayList<String>();
		  String muscles = "";
		  int count = 0;
		  
		  for (Exercise e : exercises){
			  if (!strings.contains(e.getMainMuscle()) && count<3){
				  strings.add(e.getMainMuscle());
				  muscles = muscles +  e.getMainMuscle();
				  count++;
				  if (count > 2){
					  muscles += "...";
				  }else{
					  muscles += ", ";
				  }
			  }
		  }
		  
		  if(count>0 && count<3)
			  muscles = muscles.substring(0, muscles.length() - 2);
		  
		  return muscles;
	  }
	  
}
