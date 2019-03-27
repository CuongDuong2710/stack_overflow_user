package cuongduong.developer.android.stackoverflow.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cuongduong.developer.android.stackoverflow.data.db.entity.Item

@Dao
interface ItemListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(itemList: List<Item>)

    @Query("select * from item")
    fun getUserList(): LiveData<List<Item>>

    @Query("update item set isBookmark =:isBookmark where userId =:userId")
    fun updateBookmarkItem(isBookmark: Boolean, userId: Int)
}