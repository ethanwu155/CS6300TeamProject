package edu.gatech.seclass.sdpvocabquiz;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;

public interface WordDao {

    @Insert
    void addWord(Word word);

    @Delete
    void deleteWord(Word word);

}
