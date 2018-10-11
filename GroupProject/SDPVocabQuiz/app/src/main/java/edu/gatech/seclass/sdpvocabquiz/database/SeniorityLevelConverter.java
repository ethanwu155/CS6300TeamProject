package edu.gatech.seclass.sdpvocabquiz.database;

import android.arch.persistence.room.TypeConverter;

public class SeniorityLevelConverter {

    @TypeConverter
    public static Student.SeniorityLevel toSeniorityLevel(int value) {
        if (value == Student.SeniorityLevel.FRESHMAN.value) {
            return Student.SeniorityLevel.FRESHMAN;
        } else if (value == Student.SeniorityLevel.SOPHOMORE.value) {
            return Student.SeniorityLevel.SOPHOMORE;
        } else if (value == Student.SeniorityLevel.JUNIOR.value) {
            return Student.SeniorityLevel.JUNIOR;
        } else if (value == Student.SeniorityLevel.SENIOR.value) {
            return Student.SeniorityLevel.SENIOR;
        }
        throw new IllegalArgumentException("Value invalid.");
    }

    @TypeConverter
    public static Integer toInteger(Student.SeniorityLevel seniorityLevel) {
        return seniorityLevel.value;
    }

}
