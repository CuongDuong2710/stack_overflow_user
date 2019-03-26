package cuongduong.developer.android.stackoverflow.ui.home

import android.content.Context
import android.widget.Toast
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import cuongduong.developer.android.stackoverflow.R
import cuongduong.developer.android.stackoverflow.internal.glide.GlideApp
import cuongduong.developer.android.stackoverflow.ui.provider.FormatDateProvider
import kotlinx.android.synthetic.main.item_user_list_fragment.*


class UserListItem(
    val context: Context?,
    val itemEntity: cuongduong.developer.android.stackoverflow.data.db.entity.Item
) : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            textView_username.text = itemEntity.displayName
            textView_reputation.text = "Reputation:"
            textView_reputation_value.text = itemEntity.reputation.toString()
            textView_location.text = itemEntity.location
            textView_last_access_date.text = "Access date:"
            updateAvatar()
            updateDate()
            favorite.setOnClickListener {
                Toast.makeText(context, "click", Toast.LENGTH_SHORT).show()
            }
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
}