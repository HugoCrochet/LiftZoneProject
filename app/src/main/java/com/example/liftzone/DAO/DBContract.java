package com.example.liftzone.DAO;

public final class DBContract {

    public static class Workout {
        public static final String TABLE_NAME = "workout";
        public static final String _ID = "id";
        public static final String COLUMN_NAME = "workout_name";
        //public static final String COLUMN_TIME = "workout_time";
    }

    public static class Exercise {
        public static final String TABLE_NAME = "exercise";
        public static final String _ID = "id";
        public static final String COLUMN_NAME = "exercise_name";
    }

    public static class WorkoutExercise {
        public static final String TABLE_NAME = "workout_exercise";
        public static final String _ID_EX = "id_exercise";
        public static final String _ID_WORKOUT = "id_workout";
        public static final String COLUMN_REP = "exercise_reps";
        public static final String COLUMN_SERIES = "exercise_series";
    }

}
