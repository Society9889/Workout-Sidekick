package com.usability.workoutsidekick;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Creates the initial sqllite database and inserts initial values.
 * 
 * @author Jake
 */
public class ExerciseDatabase extends SQLiteOpenHelper{

	//table name
	public static final String TABLE_NAME = "exercises";
	
	//column names
	public static final String COLUMN_ID = "_id";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_MAIN_MUSCLE = "mainMuscle";
	public static final String COLUMN_DESCRIPTION = "description";
	public static final String COLUMN_OTHER_MUSCLES = "otherMuscles";
	public static final String COLUMN_EQUIPMENT = "equipment";
	public static final String COLUMN_FAVORITE = "favorite";
	public static final String COLUMN_USER_ADDED = "userAdded";

	//database name/version
	private static final String DATABASE_NAME = "workoutsidekick.db";
	private static final int DATABASE_VERSION = 1;
	
	// Database creation sql statement
	private static final String DATABASE_CREATE = "create table "
	      + TABLE_NAME + "(" //table name
		  + COLUMN_ID + " integer primary key autoincrement, " //add 5 columns
	      + COLUMN_NAME + " text not null, " 
		  + COLUMN_MAIN_MUSCLE + " text not null, "
	      + COLUMN_DESCRIPTION + " text, " 
		  + COLUMN_OTHER_MUSCLES + " text, " 
		  + COLUMN_EQUIPMENT + " text not null, "
		  + COLUMN_FAVORITE + " integer, "
		  + COLUMN_USER_ADDED + " integer"
	      + ");";   //end statement
	
	//first part of the insert statement
	private static final String INSERT_STATEMENT = "INSERT INTO " + TABLE_NAME + " (" + COLUMN_NAME + ", " + COLUMN_MAIN_MUSCLE + ", "
			+ COLUMN_DESCRIPTION + ", " + COLUMN_OTHER_MUSCLES + ", " + COLUMN_EQUIPMENT + ", " 
			+ COLUMN_FAVORITE + ", " + COLUMN_USER_ADDED + ") " + " VALUES(";
	
	public ExerciseDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	/**
	 * Creates the database using our sql statement defined above.
	 */
	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(DATABASE_CREATE);
		addInitialExercises(database);
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

	/**
	 * Adds several exercises to the database upon creation.
	 */
	private void addInitialExercises(SQLiteDatabase database){
		//Chest
		addExercise(database, "Bench Press", "Chest", 
	"1. Lie back on a flat bench. Using a medium width grip (a grip that creates a 90-degree angle in the middle of the movement between the forearms and the upper arms), lift the bar from the rack and hold it straight over you with your arms locked. This will be your starting position."
    + "\n2. From the starting position, breathe in and begin coming down slowly until the bar touches your middle chest."
    + "\n3. After a brief pause, push the bar back to the starting position as you breathe out. Focus on pushing the bar using your chest muscles. Lock your arms and squeeze your chest in the contracted position at the top of the motion, hold for a second and then start coming down slowly again. Tip: Ideally, lowering the weight should take about twice as long as raising it."
    + "\n4. Repeat the movement for the prescribed amount of repetitions."
    + "\n5. When you are done, place the bar back in the rack."
    , "Shoulders, Triceps", "Barbell");
		addExercise(database, "Push Up", "Chest", 
	"1. Lie on the floor face down and place your hands about 36 inches apart while holding your torso up at arms length."
    + "\n2. Next, lower yourself downward until your chest almost touches the floor as you inhale."
    + "\n3. Now breathe out and press your upper body back up to the starting position while squeezing your chest."
    + "\n4. After a brief pause at the top contracted position, you can begin to lower yourself downward again for as many repetitions as needed."
    , "Shoulders, Triceps", "Body Only");
		addExercise(database, "Dumbbell Bench Press", "Chest", 
	"1. Lie down on a flat bench with a dumbbell in each hand resting on top of your thighs. The palms of your hands will be facing each other."
    + "\n2. Then, using your thighs to help raise the dumbbells up, lift the dumbbells one at a time so that you can hold them in front of you at shoulder width."
    + "\n3. Once at shoulder width, rotate your wrists forward so that the palms of your hands are facing away from you. The dumbbells should be just to the sides of your chest, with your upper arm and forearm creating a 90 degree angle. Be sure to maintain full control of the dumbbells at all times. This will be your starting position."
    + "\n4. Then, as you breathe out, use your chest to push the dumbbells up. Lock your arms at the top of the lift and squeeze your chest, hold for a second and then begin coming down slowly. Tip: Ideally, lowering the weight should take about twice as long as raising it."
    + "\n5. Repeat the movement for the prescribed amount of repetitions of your training program."
    , "Shoulders, Triceps", "Dumbbell");
		
		//Back
		addExercise(database, "One-Arm Dumbbell Row", "Back", 
				"1. Choose a flat bench and place a dumbbell on each side of it."
			    + "\n2. Place the right leg on top of the end of the bench, bend your torso forward from the waist until your upper body is parallel to the floor, and place your right hand on the other end of the bench for support."
			    + "\n3. Use the left hand to pick up the dumbbell on the floor and hold the weight while keeping your lower back straight. The palm of the hand should be facing your torso. This will be your starting position."
			    + "\n4. Pull the resistance straight up to the side of your chest, keeping your upper arm close to your side and keeping the torso stationary. Breathe out as you perform this step."
			    + "\n5. Lower the resistance straight down to the starting position. Breathe in as you perform this step."
			    + "\n6. Repeat the movement for the specified amount of repetitions."
			    + "\n7. Switch sides and repeat again with the other arm."
			    , "Biceps, Shoulders", "Dumbbell");
		addExercise(database, "Barbell Deadlift", "Back", 
				"1. Stand in front of a loaded barbell."
			    + "\n2. While keeping the back as straight as possible, bend your knees, bend forward and grasp the bar using a medium (shoulder width) overhand grip. This will be the starting position of the exercise."
			    + "\n3. While holding the bar, start the lift by pushing with your legs while simultaneously getting your torso to the upright position as you breathe out. In the upright position, stick your chest out and contract the back by bringing the shoulder blades back. Think of how the soldiers in the military look when they are in standing in attention."
			    + "\n4. Go back to the starting position by bending at the knees while simultaneously leaning the torso forward at the waist while keeping the back straight. When the weights on the bar touch the floor you are back at the starting position and ready to perform another repetition."
			    + "\n5. Perform the amount of repetitions prescribed in the program."
			    , "Quadriceps, Hamstrings, Calves", "Barbell");
		addExercise(database, "Leverage High Row", "Back", 
				"1. Load an appropriate weight onto the pins and adjust the seat height so that you can just reach the handles above you. Adjust the knee pad to help keep you down. Grasp the handles with a pronated grip. This will be your starting position."
			    + "\n2. Pull the handles towards your torso, retracting your shoulder blades as you flex the elbow."
			    + "\n3. Pause at the bottom of the motion, and then slowly return the handles to the starting position. "
			    + "\n4. For multiple repetitions, avoid completely returning the weight to the stops to keep tension on the muscles being worked. "
			    , "Lats", "Machine");
		
		//Shoulders
		addExercise(database, "Seated Barbell Military Press", "Shoulders", 
				"1. Sit on a Military Press Bench with a bar behind your head and either have a spotter give you the bar (better on the rotator cuff this way) or pick it up yourself carefully with a pronated grip (palms facing forward)."
			    + "\n2. Once you pick up the barbell with the correct grip length, lift the bar up over your head by locking your arms. Hold at about shoulder level and slightly in front of your head. This is your starting position."
			    + "\n3. Lower the bar down to the collarbone slowly as you inhale."
			    + "\n4. Lift the bar back up to the starting position as you exhale."
			    + "\n5. Repeat for the recommended amount of repetitions."
			    , "Triceps", "Barbell");
		addExercise(database, "Lateral Raise", "Shoulders", 
				"1. Pick a dumbbell and place it in one of your hands. Your non lifting hand should be used to grab something steady such as an incline bench press. Lean towards your lifting arm and away from the hand that is gripping the incline bench as this will allow you to keep your balance."
			    + "\n2. Stand with a straight torso and have the dumbbell by your side at arms length with the palm of the hand facing you. This will be your starting position."
			    + "\n3. While maintaining the torso stationary (no swinging), lift the dumbbell to your side with a slight bend on the elbow and your hand slightly tilted forward as if pouring water in a glass. Continue to go up until you arm is parallel to the floor. Exhale as you execute this movement and pause for a second at the top."
			    + "\n4. Lower the dumbbell back down slowly to the starting position as you inhale."
			    + "\n5. Repeat for the recommended amount of repetitions."
			    + "\n6. Switch arms and repeat the exercise."
			    , "None", "Dumbbell");
		addExercise(database, "Reverse Flyes", "Shoulders", 
				"1. To begin, lie down on an incline bench with the chest and stomach pressing against the incline. Have the dumbbells in each hand with the palms facing each other (neutral grip)."
			    + "\n2. Extend the arms in front of you so that they are perpendicular to the angle of the bench. The legs should be stationary while applying pressure with the ball of your toes. This is the starting position."
			    + "\n3. Maintaining the slight bend of the elbows, move the weights out and away from each other (to the side) in an arc motion while exhaling."
			    + "\n4. The arms should be elevated until they are parallel to the floor."
			    + "\n5. Feel the contraction and slowly lower the weights back down to the starting position while inhaling."
			    + "\n6. Repeat for the recommended amount of repetitions."
			    , "None", "Dumbbell");
		
		//Biceps
		addExercise(database, "Wide-Grip Barbell Curl", "Biceps", 
				"1. Stand up with your torso upright while holding a barbell at the wide outer handle. The palm of your hands should be facing forward. The elbows should be close to the torso. This will be your starting position."
			    + "\n2. While holding the upper arms stationary, curl the weights forward while contracting the biceps as you breathe out. Tip: Only the forearms should move."
			    + "\n3. Continue the movement until your biceps are fully contracted and the bar is at shoulder level. Hold the contracted position for a second and squeeze the biceps hard."
			    + "\n4. Slowly begin to bring the bar back to starting position as your breathe in."
			    + "\n5. Repeat for the recommended amount of repetitions."
			    , "None", "Dumbbell");
		addExercise(database, "Zottman Curl", "Biceps", 
				"1. Stand up with your torso upright and a dumbbell in each hand being held at arms length. The elbows should be close to the torso."
			    + "\n2. Make sure the palms of the hands are facing each other. This will be your starting position."
			    + "\n3. While holding the upper arm stationary, curl the weights while contracting the biceps as you breathe out. Only the forearms should move. Your wrist should rotate so that you have a supinated (palms up) grip. Continue the movement until your biceps are fully contracted and the dumbbells are at shoulder level."
			    + "\n4. Hold the contracted position for a second as you squeeze the biceps."
			    + "\n5. Now during the contracted position, rotate your wrist until you now have a pronated (palms facing down) grip with the thumb at a higher position than the pinky."
			    + "\n6. Slowly begin to bring the dumbbells back down using the pronated grip."
			    + "\n7. As the dumbbells close your thighs, start rotating the wrist so that you go back to a neutral (palms facing your body) grip."
			    + "\n8. Repeat for the recommended amount of repetitions."
			    , "Forearms", "Dumbbell");
		addExercise(database, "Dumbbell Curls", "Biceps", 
				"1. Stand up straight with a dumbbell in each hand at arms length. Keep your elbows close to your torso and rotate the palms of your hands until they are facing forward. This will be your starting position."
			    + "\n2. Now, keeping the upper arms stationary, exhale and curl the weights while contracting your biceps. Continue to raise the weights until your biceps are fully contracted and the dumbbells are at shoulder level. Hold the contracted position for a brief pause as you squeeze your biceps."
			    + "\n3. Then, inhale and slowly begin to lower the dumbbells back to the starting position."
			    + "\n4. Repeat for the recommended amount of repetitions."
			    , "Forearms", "Dumbbell");
		
		//Triceps
		addExercise(database, "Dips", "Triceps", 
				"1. To get into the starting position, hold your body at arms length with your arms nearly locked above the bars."
			    + "\n2. Now, inhale and slowly lower yourself downward. Your torso should remain upright and your elbows should stay close to your body. This helps to better focus on tricep involvement. Lower yourself until there is a 90 degree angle formed between the upper arm and forearm."
			    + "\n3. Then, exhale and push your torso back up using your triceps to bring your body back to the starting position."
			    + "\n4. Repeat the movement for the prescribed amount of repetitions."
			    , "Chest", "Body Only");
		addExercise(database, "Close Grip Bench Press", "Triceps", 
				"1. Lie back on a flat bench. Using a close grip (around shoulder width), lift the bar from the rack and hold it straight over you with your arms locked. This will be your starting position."
			    + "\n2. As you breathe in, come down slowly until you feel the bar on your middle chest."
			    + "\n3. After a second pause, bring the bar back to the starting position as you breathe out and push the bar using your triceps muscles. Lock your arms in the contracted position, hold for a second and then start coming down slowly again."
			    + "\n4. Repeat the movement for the prescribed amount of repetitions."
			    + "\n5. When you are done, place the bar back in the rack."
			    , "Chest, Shoulders", "Barbell");
		addExercise(database, "Triceps Overhead Extension", "Triceps", 
				"1. Attach a rope to a low pulley. After selecting an appropriate weight, grasp the rope with both hands and face away from the cable."
			    + "\n2. Position your hands behind your head with your elbows point straight up. Your elbows should start out flexed, and you can stagger your stance and lean gently away from the machine to create greater stability. This will be your starting position."
			    + "\n3. To perform the movement, extend through the elbow while keeping the upper arm in position, raising your hands above your head."
			    + "\n4. Squeeze your triceps at the top of the movement, and slowly lower the weight back to the start position. "
			    , "None", "Dumbbell");
		
		//Abdominals
		addExercise(database, "Plank", "Abdominals", 
				"1. Get into a prone position on the floor, supporting your weight on your toes and your forearms. Your arms are bent and directly below the shoulder."
			    + "\n2. Keep your body straight at all times, and hold this position as long as possible. To increase difficulty, an arm or leg can be raised."
			    , "None", "Body Only");
		addExercise(database, "Cross-Body Crunch", "Abdominals", 
				"1. Lie flat on your back and bend your knees about 60 degrees."
			    + "\n2. Keep your feet flat on the floor and place your hands loosely behind your head. This will be your starting position."
			    + "\n3. Now curl up and bring your right elbow and shoulder across your body while bring your left knee in toward your left shoulder at the same time. Reach with your elbow and try to touch your knee. Exhale as you perform this movement."
			    + "\n4. Now go back down to the starting position as you inhale and repeat with the left elbow and the right knee."
			    + "\n5. Continue alternating in this manner until all prescribed repetitions are done."
			    , "None", "Body Only");
		addExercise(database, "Bicycle Crunches", "Abdominals", 
				"1. Lie flat on the floor with your lower back pressed to the ground. For this exercise, you will need to put your hands beside your head. Be careful however to not strain with the neck as you perform it. Now lift your shoulders into the crunch position."
			    + "\n2. Bring knees up to where they are perpendicular to the floor, with your lower legs parallel to the floor. This will be your starting position."
			    + "\n3. Now simultaneously, slowly go through a cycle pedal motion kicking forward with the right leg and bringing in the knee of the left leg. Bring your right elbow close to your left knee by crunching to the side, as you breathe out."
			    + "\n4. Go back to the initial position as you breathe in."
			    + "\n5. Crunch to the opposite side as you cycle your legs and bring closer your left elbow to your right knee and exhale."
			    + "\n6. Continue alternating in this manner until all of the recommended repetitions for each side have been completed."
			    , "None", "Body Only");
		
		//Hamstrings
		addExercise(database, "Lying Leg Curls ", "Hamstrings", 
				"1. Adjust the machine lever to fit your height and lie face down on the leg curl machine with the pad of the lever on the back of your legs (just a few inches under the calves). "
			    + "\n2. Keeping the torso flat on the bench, ensure your legs are fully stretched and grab the side handles of the machine. Position your toes straight (or you can also use any of the other two stances described on the foot positioning section). This will be your starting position."
			    + "\n3. As you exhale, curl your legs up as far as possible without lifting the upper legs from the pad. Once you hit the fully contracted position, hold it for a second."
			    + "\n4. As you inhale, bring the legs back to the initial position. Repeat for the recommended amount of repetitions."
			    + "\n5. "
			    + "\n6. "
			    + "\n7. "
			    , "None", "Machine");
		addExercise(database, "Clean Deadlift", "Hamstrings", 
				"1. Begin standing with a barbell close to your shins. Your feet should be directly under your hips with your feet turned out slightly. Grip the bar with a double overhand grip or hook grip, about shoulder width apart. Squat down to the bar. Your spine should be in full extension, with a back angle that places your shoulders in front of the bar and your back as vertical as possible."
			    + "\n2. Begin by driving through the floor through the front of your heels. As the bar travels upward, maintain a constant back angle. Flare your knees out to the side to help keep them out of the bars path."
			    + "\n3. After the bar crosses the knees, complete the lift by driving the hips into the bar until your hips and knees are extended."
			    , "Quadriceps, Back, Traps", "Barbell");
		addExercise(database, "Box Jump", "Hamstrings", 
				"1. Assume a relaxed stance facing the box or platform approximately an arms length away. Arms should be down at the sides and legs slightly bent."
			    + "\n2. Using the arms to aid in the initial burst, jump upward and forward, landing with feet simultaneously on top of the box or platform."
			    + "\n3. Immediately drop or jump back down to the original starting place; then repeat the sequence."
			    , "Quadriceps, Calves", "Other");
		
		//Quadriceps
		addExercise(database, "Rope Jumping", "Quadriceps", 
				"1. Hold an end of the rope in each hand. Position the rope behind you on the ground. Raise your arms up and turn the rope over your head bringing it down in front of you. When it reaches the ground, jump over it. Find a good turning pace that can be maintained. Different speeds and techniques can be used to introduce variation."
			    + "\n2. Rope jumping is exciting, challenges your coordination, and requires a lot of energy."
			    , "Hamstrings, Calves", "Other");
		addExercise(database, "Barbell Squat", "Quadriceps", 
				"1. This exercise is best performed inside a squat rack for safety purposes. To begin, first set the bar on a rack to just below shoulder level. Once the correct height is chosen and the bar is loaded, step under the bar and place the back of your shoulders (slightly below the neck) across it."
			    + "\n2. Hold on to the bar using both arms at each side and lift it off the rack by first pushing with your legs and at the same time straightening your torso."
			    + "\n3. Step away from the rack and position your legs using a shoulder width medium stance with the toes slightly pointed out. Keep your head up at all times and also maintain a straight back. This will be your starting position"
			    + "\n4. Begin to slowly lower the bar by bending the knees and hips as you maintain a straight posture with the head up. Continue down until the angle between the upper leg and the calves becomes slightly less than 90-degrees. Inhale as you perform this portion of the movement."
			    + "\n5. Begin to raise the bar as you exhale by pushing the floor with the heel of your foot as you straighten the legs again and go back to the starting position."
			    + "\n6. Repeat for the recommended amount of repetitions."
			    , "Calves, Hamstrings", "Barbell");
		addExercise(database, "Leg Extension", "Quadriceps", 
				"1. Seat yourself in the machine and adjust it so that you are positioned properly. The pad should be against the lower part of the shin but not in contact with the ankle. Adjust the seat so that the pivot point is in line with your knee. Select a weight appropriate for your abilities."
			    + "\n2. Maintaining good posture, fully extend one leg, pausing at the top of the motion."
			    + "\n3. Return to the starting position without letting the weight stop, keeping tension on the muscle."
			    + "\n4. Repeat for the desired number of repetitions. "
			    , "None", "Machine");
		
		//Calves
		addExercise(database, "Standing Dumbbell Calf Raise", "Calves", 
				"1. Stand with your torso upright holding two dumbbells in your hands by your sides. Place the ball of the foot on a sturdy and stable wooden board (that is around 2-3 inches tall) while your heels extend off and touch the floor. This will be your starting position."
			    + "\n2. With the toes pointing either straight (to hit all parts equally), inwards (for emphasis on the outer head) or outwards (for emphasis on the inner head), raise the heels off the floor as you exhale by contracting the calves. Hold the top contraction for a second."
			    + "\n3. As you inhale, go back to the starting position by slowly lowering the heels."
			    + "\n4. Repeat for the recommended amount of times."
			    , "None", "Dumbbell");
		addExercise(database, "Smith Machine Calf Raise", "Calves", 
				"1. Place a block or weight plate below the bar on the Smith machine. Set the bar to a position that best matches your height. Once the correct height is chosen and the bar is loaded, step onto the plates with the balls of your feet and place the bar on the back of your shoulders."
			    + "\n2. Take the bar with both hands facing forward. Rotate the bar to unrack it. This will be your starting position."
			    + "\n3. Raise your heels as high as possible by pushing off of the balls of your feet, flexing your calf at the top of the contraction. Your knees should remain extended. Hold the contracted position for a second before you start to go back down."
			    + "\n4. Return slowly to the starting position as you breathe in while lowering your heels."
			    + "\n5. Repeat for the recommended amount of repetitions."
			    , "None", "Machine");
		addExercise(database, "Seated Calf Raise", "Calves", 
				"1. Sit on the machine and place your toes on the lower portion of the platform provided with the heels extending off. Choose the toe positioning of your choice (forward, in, or out) as per the beginning of this chapter."
			    + "\n2. Place your lower thighs under the lever pad, which will need to be adjusted according to the height of your thighs. Now place your hands on top of the lever pad in order to prevent it from slipping forward."
			    + "\n3. Lift the lever slightly by pushing your heels up and release the safety bar. This will be your starting position."
			    + "\n4. Slowly lower your heels by bending at the ankles until the calves are fully stretched. Inhale as you perform this movement."
			    + "\n5. Raise the heels by extending the ankles as high as possible as you contract the calves and breathe out. Hold the top contraction for a second."
			    + "\n6. Repeat for the recommended amount of repetitions."
			    , "None", "Machine");
	}
	
	private void addExercise(SQLiteDatabase database, String name, String mainMuscle, String description,
			String otherMuscles, String equipment){
		database.execSQL(INSERT_STATEMENT + "'" + name + "', '" + mainMuscle + "', '" +
				description + "', '" + otherMuscles + "', '" + equipment + "', " + 0 + ", " + 0 + ");");
	}
	
}
