package cuongduong.developer.android.stackoverflow.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cuongduong.developer.android.stackoverflow.data.db.entity.BookmarkItem

@Dao
interface BookmarkListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(bookmarkList: List<BookmarkItem>)

    @Query("select * from bookmark")
    fun getBookMarkList(): LiveData<List<BookmarkItem>>
}