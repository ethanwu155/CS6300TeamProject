package edu.gatech.seclass.sdpvocabquiz.database;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = {
        @ForeignKey(entity = Student.class,
            parentColumns = "id",
            childColumns = "studentId",
            onDelete = CASCADE)},
        indices = {@Index(value = {"name"}, unique = true)})
public class Quiz {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    public String description;
    public ArrayList<String> incorrectDefinitions = new ArrayList<>();
    //public ArrayList<Word> wordList = new ArrayList<>();

    public int studentId;

    public int getId() {
        return id;
    }

    public Quiz(String name, String description, ArrayList<String> incorrectDefinitions, int studentId) {
        this.name = name;
        this.description = description;
        this.incorrectDefinitions.addAll(incorrectDefinitions);
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }
}
