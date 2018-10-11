/* DateConverter taken from: https://github.com/googlecodelabs/android-persistence/blob/master/app/src/main/java/com/example/android/persistence/codelab/db/DateConverter.java */

package edu.gatech.seclass.sdpvocabquiz.database;
import android.arch.persistence.room.TypeConverter;

import java.util.Date;

public class DateConverter {
    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
