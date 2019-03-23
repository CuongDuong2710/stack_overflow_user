package cuongduong.developer.android.stackoverflow.ui.home

import androidx.lifecycle.ViewModel;
import cuongduong.developer.android.stackoverflow.data.repository.StackExchangeRepository
import cuongduong.developer.android.stackoverflow.internal.lazyDeferred

class UserListViewModel(
    private val stackExchangeRepository: StackExchangeRepository
) : ViewModel() {
    val userList by lazyDeferred {
        stackExchangeRepository.getUserList()
    }
}
