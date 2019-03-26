package cuongduong.developer.android.stackoverflow.ui.bookmark

import android.graphics.drawable.Animatable
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import cuongduong.developer.android.stackoverflow.R
import cuongduong.developer.android.stackoverflow.data.db.entity.BookmarkItem
import cuongduong.developer.android.stackoverflow.internal.glide.GlideApp
import cuongduong.developer.android.stackoverflow.ui.provider.FormatDateProvider
import kotlinx.android.synthetic.main.item_bookmark_list_fragment.*

class BookmarkListItem(
    private val bookmarkEntity: BookmarkItem,
    private val onFavoriteListener: (item: BookmarkListItem, favorite: Boolean) -> Unit?
) : Item() {

    private var checked = false
    private var inProgress = false

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            textView_bookmark_username.text = bookmarkEntity.displayName
            textView_bookmark_reputation.text = "Reputation:"
            textView_bookmark_reputation_value.text = bookmarkEntity.reputation.toString()
            textView_bookmark_location.text = bookmarkEntity.location
            textView_bookmark_last_access_date.text = "Access date:"
            updateAvatar()
            updateDate()
            bindBookmark(viewHolder)
            onClickBookmark(viewHolder)
        }
    }

    override fun getLayout() = R.layout.item_bookmark_list_fragment

    private fun ViewHolder.updateAvatar() {
        GlideApp.with(this.containerView)
            .load(bookmarkEntity.profileImage)
            .into(imageView_bookmark_avatar)
    }

    private fun ViewHolder.updateDate() {
        textView_bookmark_date_value.text = FormatDateProvider().formatLongToDateString(bookmarkEntity.lastAccessDate)
    }

    private fun ViewHolder.bindBookmark(holder: ViewHolder) {
        if (inProgress)
            animateProgress(holder)
        else
            holder.favorite_bookmark_page.setImageResource(R.drawable.favorite_state_list)

        holder.favorite_bookmark_page.isChecked = checked
    }

    private fun animateProgress(holder: ViewHolder) {
        holder.favorite_bookmark_page.apply {
            setImageResource(R.drawable.favorite_progress)
            (drawable as Animatable).start()
        }
    }

    private fun ViewHolder.onClickBookmark(holder: ViewHolder) {
        favorite_bookmark_page.setOnClickListener{
            inProgress = true
            animateProgress(holder)
            onFavoriteListener(this@BookmarkListItem, !checked)
        }
    }

}