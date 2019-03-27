package cuongduong.developer.android.stackoverflow.ui.bookmark

import androidx.lifecycle.ViewModel;
import cuongduong.developer.android.stackoverflow.data.db.entity.BookmarkItem
import cuongduong.developer.android.stackoverflow.data.repository.StackExchangeRepository
import cuongduong.developer.android.stackoverflow.internal.lazyDeferred

class BookmarkViewModel(
    private val stackExchangeRepository: StackExchangeRepository
) : ViewModel() {
    val bookmarkList by lazyDeferred {
        stackExchangeRepository.getBookmarksList()
    }
    val insertBookmarkItem by lazyDeferred {
        stackExchangeRepository.insertBookmark(BookmarkItem(1553571689, 600814, 5445, "Guatemala", "https://www.gravatar.com/avatar/932fb89b9d4049cec5cba357bf0ae388?s=128&d=identicon&r=PG", "CMS"))
    }
}
