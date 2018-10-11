package edu.gatech.seclass.sdpvocabquiz.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;

@Dao
public interface WordDao {

    @Insert
    void addWord(Word word);

    @Delete
    void deleteWord(Word word);

}
