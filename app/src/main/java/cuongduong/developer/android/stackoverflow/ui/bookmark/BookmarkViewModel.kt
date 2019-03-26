package cuongduong.developer.android.stackoverflow.ui.bookmark

import androidx.lifecycle.ViewModel;
import cuongduong.developer.android.stackoverflow.data.repository.StackExchangeRepository
import cuongduong.developer.android.stackoverflow.internal.lazyDeferred

class BookmarkViewModel(
    private val stackExchangeRepository: StackExchangeRepository
) : ViewModel() {
    val bookmarkList by lazyDeferred {
        stackExchangeRepository.getBookmarksList()
    }
}
