package com.usability.workoutsidekick;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Creates the database to store mappings of exercises to days of the week.
 * 
 * @author Jake
 */
public class ScheduleDatabase extends SQLiteOpenHelper{

	//table name
	public static final String TABLE_NAME = "schedule";
	
	//column names
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_DAY = "day";
	public static final String COLUMN_EXERCISE_ID = "exercise_id";

	//database name/version
	private static final String DATABASE_NAME = "workoutsidekickschedule.db";
	private static final int DATABASE_VERSION = 1;
	
	// Database creation sql statement
	private static final String DATABASE_CREATE = "create table "
	      + TABLE_NAME + "(" //table name
		  + COLUMN_ID + " integer primary key autoincrement, " //add 5 columns
	      + COLUMN_DAY + " text not null, " 
		  + COLUMN_EXERCISE_ID + " integer"
	      + ");";   //end statement
	
	//first part of the insert statement
	private static final String INSERT_STATEMENT = "INSERT INTO " + TABLE_NAME + " (" + COLUMN_DAY + ", "
			+ COLUMN_EXERCISE_ID + ") " + " VALUES(";
	
	public ScheduleDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	/**
	 * Creates the database using our sql statement defined above.
	 */
	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
		//database.execSQL(INSERT_STATEMENT + "'Monday', 1);");
		System.out.println("Database created!");
	}

	/**
	 * Upgrades database to different version.
	 */
	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
	    Log.w(ExerciseDatabase.class.getName(),
	            "Upgrading database from version " + oldVersion + " to "
	                + newVersion + ", which will destroy all old data");
	    database.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
	    onCreate(database);
	}

	
}
