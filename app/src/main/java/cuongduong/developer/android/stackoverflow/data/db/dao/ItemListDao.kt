package cuongduong.developer.android.stackoverflow.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import cuongduong.developer.android.stackoverflow.data.db.entity.Item

@Dao
interface ItemListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(itemList: List<Item>)
}