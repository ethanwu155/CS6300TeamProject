package edu.gatech.seclass.sdpvocabquiz.database;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;

@Entity(foreignKeys = {
        @ForeignKey(entity = Student.class,
            parentColumns = "id",
            childColumns = "studentId")})
public class Quiz {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    public String description;
    public ArrayList<String> incorrectDefinitions = new ArrayList<>();

    public int studentId;

    public Quiz(String name, String description, ArrayList<String> incorrectDefinitions) {
        this.name = name;
        this.description = description;
        this.incorrectDefinitions.addAll(incorrectDefinitions);
    }

}
