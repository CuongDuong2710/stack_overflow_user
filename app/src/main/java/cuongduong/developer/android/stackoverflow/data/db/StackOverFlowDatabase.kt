package cuongduong.developer.android.stackoverflow.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import cuongduong.developer.android.stackoverflow.data.db.dao.BookmarkListDao
import cuongduong.developer.android.stackoverflow.data.db.dao.ItemListDao
import cuongduong.developer.android.stackoverflow.data.db.dao.UserReputationListDao
import cuongduong.developer.android.stackoverflow.data.db.entity.BookmarkItem
import cuongduong.developer.android.stackoverflow.data.db.entity.Item
import cuongduong.developer.android.stackoverflow.data.db.entity.ReputationItem

@Database(
    entities = [Item::class, ReputationItem::class, BookmarkItem::class],
    version = 1
)
abstract class StackOverFlowDatabase : RoomDatabase() {
    abstract fun itemListDao(): ItemListDao
    abstract fun userReputationListDao(): UserReputationListDao
    abstract fun bookmarkListDao(): BookmarkListDao

    companion object {
        @Volatile private var instance: StackOverFlowDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                    StackOverFlowDatabase::class.java, "stackoverflow.db")
                    .build()
    }
}