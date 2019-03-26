package cuongduong.developer.android.stackoverflow.ui.detail

import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import cuongduong.developer.android.stackoverflow.R
import cuongduong.developer.android.stackoverflow.data.db.entity.ReputationItem
import cuongduong.developer.android.stackoverflow.internal.ReputationType
import kotlinx.android.synthetic.main.item_user_reputation_list_fragment.*
import java.text.SimpleDateFormat
import java.util.*

class UserReputationItem(
    val reputationItem: ReputationItem
) : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            updateImage()
            updateDate()
            textView_reputation_change.text = reputationItem.reputationChange.toString()
            textView_post_link.text = reputationItem.postId.toString()
        }
    }

    override fun getLayout() = R.layout.item_user_reputation_list_fragment

    private fun ViewHolder.updateImage() {
        val type = reputationItem.reputationHistoryType
        when (type) {
            ReputationType.POST_UPVOTED.name,
            ReputationType.POST_UNDOWNVOTED.name,
            ReputationType.ANSWER_ACCEPTED.name -> imageView_reputation_type.setImageResource(
                R.drawable.ic_upvote
            )
            ReputationType.POST_DOWNVOTED.name -> imageView_reputation_type.setImageResource(R.drawable.ic_downvote)
        }
    }

    private fun ViewHolder.updateDate() {
        val date = Date(reputationItem.creationDate * 1000L)
        val df2 = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.US)
        val dateText = df2.format(date)
        textView_creation_date.text = dateText
    }
}