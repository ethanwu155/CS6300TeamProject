package edu.gatech.seclass.sdpvocabquiz;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

public interface StudentDao {

    @Query("SELECT * FROM Student")
    List<Student> getAllStudents();

    @Insert
    void registerNewStudent(Student student);

    @Delete
    void delete(Student student);

}
