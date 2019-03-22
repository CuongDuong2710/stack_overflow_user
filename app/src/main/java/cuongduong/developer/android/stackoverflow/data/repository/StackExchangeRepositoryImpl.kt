package cuongduong.developer.android.stackoverflow.data.repository

import androidx.lifecycle.LiveData
import cuongduong.developer.android.stackoverflow.data.db.dao.ItemListDao
import cuongduong.developer.android.stackoverflow.data.db.entity.Item
import cuongduong.developer.android.stackoverflow.data.network.StackExchangeNetworkDataSource
import cuongduong.developer.android.stackoverflow.data.network.response.UserListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StackExchangeRepositoryImpl(
    private val itemListDao: ItemListDao,
    private val stackExchangeNetworkDataSource: StackExchangeNetworkDataSource
) : StackExchangeRepository {

    // observer for data changes in API and save in local database
    init {
        stackExchangeNetworkDataSource.apply {
            downloadUserList.observeForever {
                newUserList ->
                persistFetchedUserList(newUserList)
            }
        }
    }

    // save newest data in local database
    private fun persistFetchedUserList(fetchedUserList: UserListResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            val userList = fetchedUserList.items
            itemListDao.insert(userList)
        }
    }

    // get user list from local database
    override suspend fun getUserList(): LiveData<List<Item>> {
        return withContext(Dispatchers.IO) {
            return@withContext itemListDao.getUserList()
        }
    }
}