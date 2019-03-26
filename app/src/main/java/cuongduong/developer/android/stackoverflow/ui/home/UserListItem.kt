package cuongduong.developer.android.stackoverflow.ui.home

import android.content.Context
import android.graphics.drawable.Animatable
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import cuongduong.developer.android.stackoverflow.R
import cuongduong.developer.android.stackoverflow.internal.glide.GlideApp
import cuongduong.developer.android.stackoverflow.ui.provider.FormatDateProvider
import kotlinx.android.synthetic.main.item_user_list_fragment.*

val FAVORITE = "FAVORITE"

class UserListItem(
    val context: Context?,
    val itemEntity: cuongduong.developer.android.stackoverflow.data.db.entity.Item,
    private val onFavoriteListener: (item: UserListItem, favorite: Boolean) -> Unit
) : Item() {

    private var checked = false
    private var inProgress = false

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            textView_username.text = itemEntity.displayName
            textView_reputation.text = "Reputation:"
            textView_reputation_value.text = itemEntity.reputation.toString()
            textView_location.text = itemEntity.location
            textView_last_access_date.text = "Access date:"
            updateAvatar()
            updateDate()
            bindBookmark(viewHolder)
            onClickBookmark(viewHolder)
        }
    }

    override fun getLayout() = R.layout.item_user_list_fragment

    private fun ViewHolder.updateAvatar() {
        GlideApp.with(this.containerView)
            .load(itemEntity.profileImage)
            .into(imageView_avatar)
    }

    private fun ViewHolder.updateDate() {
        textView_date_value.text = FormatDateProvider().formatLongToDateString(itemEntity.lastAccessDate)
    }

    private fun bindBookmark(holder: ViewHolder) {
        if (inProgress)
            animateProgress(holder)
        else
            holder.favorite_user_page.setImageResource(R.drawable.favorite_state_list)

        holder.favorite_user_page.isChecked = checked
    }

    private fun animateProgress(holder: ViewHolder) {
        holder.favorite_user_page.apply {
            setImageResource(R.drawable.favorite_progress)
            (drawable as Animatable).start()
        }
    }

    private fun ViewHolder.onClickBookmark(holder: ViewHolder) {
        favorite_user_page.setOnClickListener {
            inProgress = true
            animateProgress(holder)
            onFavoriteListener(this@UserListItem, !checked)
        }
    }

    fun setFavorite(favorite: Boolean) {
        inProgress = false
        checked = favorite
    }

    override fun bind(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.contains(FAVORITE)) {
            bindBookmark(holder)
        }
        else
            bind(holder, position)
    }
}