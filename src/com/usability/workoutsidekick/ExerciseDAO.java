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
 * TODO - re-factor to add favorite field
 * 
 * @author Jake
 */
public class ExerciseDAO {

	  // Database fields
	  private SQLiteDatabase database;
	  private ExerciseDatabase dbHelper;
	  private String[] allColumns = { ExerciseDatabase.COLUMN_ID,
			  ExerciseDatabase.COLUMN_NAME, ExerciseDatabase.COLUMN_MAIN_MUSCLE,
			  ExerciseDatabase.COLUMN_DESCRIPTION, ExerciseDatabase.COLUMN_OTHER_MUSCLES,
			  ExerciseDatabase.COLUMN_EQUIPMENT, ExerciseDatabase.COLUMN_FAVORITE, ExerciseDatabase.COLUMN_USER_ADDED};

	  public ExerciseDAO(Context context) {
	    dbHelper = new ExerciseDatabase(context);
	  }

	  public void open() throws SQLException {
	    database = dbHelper.getWritableDatabase();
	  }

	  public void close() {
	    dbHelper.close();
	  }

	  /**
	   * Add a new exercise to the database. 
	   */
	  public void createExercise(String name, String mainMuscle, 
			  String description, String otherMuscles, String equipment) {
	    ContentValues values = new ContentValues();
	    values.put(ExerciseDatabase.COLUMN_NAME, name);
	    values.put(ExerciseDatabase.COLUMN_MAIN_MUSCLE, mainMuscle);
	    values.put(ExerciseDatabase.COLUMN_DESCRIPTION, description);
	    values.put(ExerciseDatabase.COLUMN_OTHER_MUSCLES, otherMuscles);
	    values.put(ExerciseDatabase.COLUMN_EQUIPMENT, equipment);
	    values.put(ExerciseDatabase.COLUMN_FAVORITE, 0);
	    values.put(ExerciseDatabase.COLUMN_USER_ADDED, 1); //adding new exercises is always user added
	    
	    database.insert(ExerciseDatabase.TABLE_NAME, null, values);
	  }

	  /**
	   * Delete an exercise from the database, found by id.
	   */
	  public void deleteExercise(Exercise exercise) {
	    long id = exercise.getId();
	    System.out.println("Exercise deleted with id: " + id);
	    database.delete(ExerciseDatabase.TABLE_NAME, ExerciseDatabase.COLUMN_ID
	        + " = " + id, null);
	  }
	  
	  /**
	   * Updates all values for a given exercise.
	   */
	  public void updateExercise(Exercise exercise){
		  long id = exercise.getId();
		  
		  ContentValues values = new ContentValues();
		  values.put(ExerciseDatabase.COLUMN_NAME, exercise.getName());
		  values.put(ExerciseDatabase.COLUMN_MAIN_MUSCLE, exercise.getMainMuscle());
		  values.put(ExerciseDatabase.COLUMN_DESCRIPTION, exercise.getDescription());
		  values.put(ExerciseDatabase.COLUMN_OTHER_MUSCLES, exercise.getOtherMuscles());
		  values.put(ExerciseDatabase.COLUMN_EQUIPMENT, exercise.getEquipment());
		  values.put(ExerciseDatabase.COLUMN_FAVORITE, exercise.getFavorite());
		  
		  database.update(ExerciseDatabase.TABLE_NAME, values, ExerciseDatabase.COLUMN_ID
			        + " = " + id, null);
	  }

	  /**
	   * Returns a list of all exercises from the database. 
	   */
	  public List<Exercise> getAllExercises() {
	    List<Exercise> exercises = new ArrayList<Exercise>();

	    //get all rows in database
	    Cursor cursor = database.query(ExerciseDatabase.TABLE_NAME,
	        allColumns, null, null, null, null, ExerciseDatabase.COLUMN_NAME + " ASC");

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
	   * Returns a list of all exercises with a given mainMuscle from the database. 
	   */
	  public List<Exercise> getExercisesByMainMuscle(String mainMuscle) {
	    List<Exercise> exercises = new ArrayList<Exercise>();

	    //get all rows in database with mainMuscle
	    Cursor cursor = database.query(ExerciseDatabase.TABLE_NAME,
	        allColumns, ExerciseDatabase.COLUMN_MAIN_MUSCLE + "=?", new String[] { mainMuscle }, null, null, null);
	    
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
	   * Return an exercise with the given id.
	   */
	  public Exercise getExercise(long id){
		    Exercise exercise = new Exercise();

		    //get all rows in database with mainMuscle
		    Cursor cursor = database.query(ExerciseDatabase.TABLE_NAME,
		        allColumns, "_id=?", new String[] { String.valueOf(id) }, null, null, null);

		    //iterate through cursor, converting each returned row into an exercise
		    cursor.moveToFirst();
		    exercise = cursorToExercise(cursor);
		    
		    // Make sure to close the cursor
		    cursor.close();
		    return exercise;
	  }
	  
	  /**
	   * Return all favorited exercises.
	   */
	  public List<Exercise> getFavorites() {
		    List<Exercise> exercises = new ArrayList<Exercise>();

		    //get all rows in database with mainMuscle
		    Cursor cursor = database.query(ExerciseDatabase.TABLE_NAME,
		        allColumns, ExerciseDatabase.COLUMN_FAVORITE + "=?", new String[] { "1" }, null, null, null);
		    
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
	   * Return all user created exercises.
	   */
	  public List<Exercise> getUserAdded() {
		    List<Exercise> exercises = new ArrayList<Exercise>();

		    //get all rows in database with mainMuscle
		    Cursor cursor = database.query(ExerciseDatabase.TABLE_NAME,
		        allColumns, ExerciseDatabase.COLUMN_USER_ADDED + "=?", new String[] { "1" }, null, null, null);
		    
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
	    Exercise exercise = new Exercise();
	    exercise.setId(cursor.getLong(0));
	    exercise.setName(cursor.getString(1));
	    exercise.setMainMuscle(cursor.getString(2));
	    exercise.setDescription(cursor.getString(3));
	    exercise.setOtherMuscles(cursor.getString(4));
	    exercise.setEquipment(cursor.getString(5));
	    exercise.setFavorite(cursor.getLong(6));
	    exercise.setUserAdded(cursor.getLong(7));
	    return exercise;
	  }
	
}
