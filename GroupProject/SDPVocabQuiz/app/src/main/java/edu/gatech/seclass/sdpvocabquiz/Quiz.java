package edu.gatech.seclass.sdpvocabquiz;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;
import java.util.HashSet;

@Entity(foreignKeys = {
        @ForeignKey(entity = Student.class,
            parentColumns = "id",
            childColumns = "studentId")})
public class Quiz {
    @PrimaryKey
    public int id;

    public String name;
    public String description;
    public ArrayList<String> incorrectDefinitions = new ArrayList<>();

    public int studentId;

    public Quiz(String name, String description, ArrayList incorrectDefinitions) {
        this.name = name;
        this.description = description;
        this.incorrectDefinitions.addAll(incorrectDefinitions);
    }

}
