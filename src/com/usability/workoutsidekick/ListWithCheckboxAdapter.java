package com.usability.workoutsidekick;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class ListWithCheckboxAdapter extends ArrayAdapter<Exercise> {

	  private final List<Exercise> list;
	  private final Activity context;

	  public ListWithCheckboxAdapter(Activity context, List<Exercise> list) {
	    super(context, R.layout.single_exercise_with_checkbox, list);
	    this.context = context;
	    this.list = list;
	  }

	  static class ViewHolder {
	    protected TextView text;
	    protected CheckBox checkbox;
	  }

	  @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	    View view = null;
	    if (convertView == null) {
	      LayoutInflater inflator = context.getLayoutInflater();
	      view = inflator.inflate(R.layout.single_exercise_with_checkbox, null);
	      final ViewHolder viewHolder = new ViewHolder();
	      viewHolder.text = (TextView) view.findViewById(R.id.exercise_name);
	      viewHolder.checkbox = (CheckBox) view.findViewById(R.id.exercise_checkbox);
	      viewHolder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
	            @Override
	            public void onCheckedChanged(CompoundButton buttonView,
	                boolean isChecked) {
	            	Exercise exercise = (Exercise) viewHolder.checkbox.getTag();
	            	exercise.setSelected(buttonView.isChecked());
	            }
	          });
	      view.setTag(viewHolder);
	      viewHolder.checkbox.setTag(list.get(position));
	    } else {
	      view = convertView;
	      ((ViewHolder) view.getTag()).checkbox.setTag(list.get(position));
	    }
	    ViewHolder holder = (ViewHolder) view.getTag();
	    holder.text.setText(list.get(position).getName());
	    holder.checkbox.setChecked(list.get(position).isSelected());
	    return view;
	  }
	} 
