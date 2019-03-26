package cuongduong.developer.android.stackoverflow.ui.home

import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import cuongduong.developer.android.stackoverflow.R
import cuongduong.developer.android.stackoverflow.internal.glide.GlideApp
import kotlinx.android.synthetic.main.item_user_list_fragment.*
import java.text.SimpleDateFormat
import java.util.*


class UserListItem(
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
        }
    }

    override fun getLayout() = R.layout.item_user_list_fragment

    private fun ViewHolder.updateAvatar() {
        GlideApp.with(this.containerView)
            .load(itemEntity.profileImage)
            .into(imageView_avatar)
    }

    private fun ViewHolder.updateDate() {
        val date = Date(itemEntity.lastAccessDate * 1000L)
        val df2 = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.US)
        val dateText = df2.format(date)
        textView_date_value.text = dateText
    }
}