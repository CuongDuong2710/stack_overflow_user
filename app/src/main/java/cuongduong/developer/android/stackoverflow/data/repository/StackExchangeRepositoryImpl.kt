package cuongduong.developer.android.stackoverflow.data.repository

import androidx.lifecycle.LiveData
import cuongduong.developer.android.stackoverflow.data.db.dao.ItemListDao
import cuongduong.developer.android.stackoverflow.data.db.dao.UserReputationListDao
import cuongduong.developer.android.stackoverflow.data.db.entity.Item
import cuongduong.developer.android.stackoverflow.data.db.entity.ReputationItem
import cuongduong.developer.android.stackoverflow.data.network.StackExchangeNetworkDataSource
import cuongduong.developer.android.stackoverflow.data.network.response.UserReputationResponse
import cuongduong.developer.android.stackoverflow.data.network.response.UserListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StackExchangeRepositoryImpl(
    private val itemListDao: ItemListDao,
    private val userReputationListDao: UserReputationListDao,
    private val stackExchangeNetworkDataSource: StackExchangeNetworkDataSource
) : StackExchangeRepository {

    // always observer for data changes in API and save in local database
    init {
        stackExchangeNetworkDataSource.apply {
            downloadUserList.observeForever { newUserList ->
                persistFetchedUserList(newUserList)
            }
            downloadUserReputationList.observeForever { newUserReputationList ->
                persistFetchedUserReputationList(newUserReputationList)
            }
        }
    }

    // save newest user list data into local database
    private fun persistFetchedUserList(fetchedUserList: UserListResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            val userList = fetchedUserList.items
            itemListDao.insert(userList)
        }
    }

    // get user list from local database
    override suspend fun getUserList(): LiveData<List<Item>> {
        return withContext(Dispatchers.IO) {
            initUserListData()
            return@withContext itemListDao.getUserList()
        }
    }

    // fetch user list from api
    private suspend fun initUserListData() {
        stackExchangeNetworkDataSource.fetchUserList(1, 30)
    }

    // save newest user detail reputation list data into local database
    private fun persistFetchedUserReputationList(fetchedUserReputationList: UserReputationResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            val userReputationList = fetchedUserReputationList.reputationItems
            userReputationListDao.insert(userReputationList)
        }
    }

    // get user detail reputation list from local database
    override suspend fun getUserReputationList(): LiveData<List<ReputationItem>> {
        return withContext(Dispatchers.IO) {
            initUserReputationList()
            return@withContext userReputationListDao.getUserReputation()
        }
    }

    // fetch user detail reputation list from api
    private suspend fun initUserReputationList() {
        stackExchangeNetworkDataSource.fetchUserReputation(1, 30)
    }

}