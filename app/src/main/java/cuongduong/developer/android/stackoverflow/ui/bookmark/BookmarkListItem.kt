package cuongduong.developer.android.stackoverflow.ui.bookmark

import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import cuongduong.developer.android.stackoverflow.R
import cuongduong.developer.android.stackoverflow.data.db.entity.BookmarkItem
import cuongduong.developer.android.stackoverflow.internal.glide.GlideApp
import cuongduong.developer.android.stackoverflow.ui.provider.FormatDateProvider
import kotlinx.android.synthetic.main.item_bookmark_list_fragment.*
import kotlinx.android.synthetic.main.item_user_list_fragment.*

class BookmarkListItem (
    val bookmarkEntity: BookmarkItem
): Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            textView_bookmark_username.text = bookmarkEntity.displayName
            textView_bookmark_reputation.text = "Reputation:"
            textView_bookmark_reputation_value.text = bookmarkEntity.reputation.toString()
            textView_bookmark_location.text = bookmarkEntity.location
            textView_last_access_date.text = "Access date:"
            updateAvatar()
            updateDate()
        }
    }

    override fun getLayout()= R.layout.item_bookmark_list_fragment

    private fun ViewHolder.updateAvatar() {
        GlideApp.with(this.containerView)
            .load(bookmarkEntity.profileImage)
            .into(imageView_avatar)
    }

    private fun ViewHolder.updateDate() {
        textView_date_value.text = FormatDateProvider().formatLongToDateString(bookmarkEntity.lastAccessDate)
    }

}