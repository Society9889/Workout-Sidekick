package com.usability.workoutsidekick;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Data access object class that provides an easy interface for 
 * connecting to and using the sqllite database.
 * 
 * @author Jake
 */
public class ScheduleDAO {

	  // Database fields
	  private SQLiteDatabase database;
	  private ScheduleDatabase dbHelper;
	  private String[] allColumns = { ScheduleDatabase.COLUMN_ID,
			  ScheduleDatabase.COLUMN_DAY, ScheduleDatabase.COLUMN_EXERCISE_ID};
	  
	  private ExerciseDAO exerciseDAO;

	  public ScheduleDAO(Context context) {
	    dbHelper = new ScheduleDatabase(context);
	    exerciseDAO = new ExerciseDAO(context);
	  }

	  public void open() throws SQLException {
	    database = dbHelper.getWritableDatabase();
	    exerciseDAO.open();
	  }

	  public void close() {
	    dbHelper.close();
	    exerciseDAO.close();
	  }

	  /**
	   * Add a new exercise to a day. 
	   */
	  public void addExerciseToDay(String day, long exerciseId) {
	    ContentValues values = new ContentValues();
	    
	    values.put(ScheduleDatabase.COLUMN_DAY, day);
	    values.put(ScheduleDatabase.COLUMN_EXERCISE_ID, (int)exerciseId);
	    database.insert(ScheduleDatabase.TABLE_NAME, null, values);
	  }

	  /**
	   * Delete an exercise from the database, found by id.
	   */
	  public void deleteExerciseFromDay(String day, long id) {
	    System.out.println("Exercise deleted from " + day + " with id: " + id);
	    
	    String stringArray[] = {day, String.valueOf(id)};
	    
	    database.delete(ScheduleDatabase.TABLE_NAME, ScheduleDatabase.COLUMN_DAY
	        + " = ? AND " + ScheduleDatabase.COLUMN_EXERCISE_ID + " = ?", stringArray);
	  }

	  /**
	   * Returns a list of all exercises for a specific day from the database. 
	   */
	  public List<Exercise> getExercisesByDay(String day) {
	    List<Exercise> exercises = new ArrayList<Exercise>();

	    //get all rows in database with mainMuscle
	    Cursor cursor = database.query(ScheduleDatabase.TABLE_NAME,
	        allColumns, ScheduleDatabase.COLUMN_DAY + "=?", new String[] { day }, null, null, null);
	    
	    //iterate through cursor, converting each returned row into an exercise
	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      Exercise exercise = cursorToExercise(cursor);
	      exercises.add(exercise);
	      cursor.moveToNext();
	    }
	    // Make sure to close the cursor
	    cursor.close();
	    return exercises;
	  }
	  

	  /**
	   * Converts a cursor(database row) into an Exercise object.
	   */
	  private Exercise cursorToExercise(Cursor cursor) {
	    Exercise exercise = exerciseDAO.getExercise(cursor.getLong(2));
	    exercise.setDay(cursor.getString(1));
	    return exercise;
	  }
	
}
