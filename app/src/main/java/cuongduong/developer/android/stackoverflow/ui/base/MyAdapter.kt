//package cuongduong.developer.android.stackoverflow.ui.base
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import cuongduong.developer.android.stackoverflow.R
//import cuongduong.developer.android.stackoverflow.data.db.entity.Item
//import kotlinx.android.synthetic.main.item_user_list_fragment.view.*
//import kotlinx.android.synthetic.main.loading_layout.view.*
//
//internal class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//    var progressBar = itemView.progress_bar
//}
//
//internal class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//    var txt_name = itemView.textView_username
//    var txt_reputation = itemView.textView_reputation
//    var txt_reputation_value = itemView.textView_reputation_value
//    var txt_location = itemView.textView_location
//    var txt_acess_date = itemView.textView_last_access_date
//    var txt_date_value = itemView.textView_date_value
//}
//
//class MyAdapter(recyclerView: RecyclerView,internal var fragment: Fragment,internal var items: MutableList<Item?>):
//    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder? {
//        if (viewType == VIEW_ITEMTYPE) {
//            val view = LayoutInflater.from(fragment.context)
//                .inflate(R.layout.item_user_list_fragment, parent, false)
//            return ItemViewHolder(view)
//        } else if (viewType == VIEW_LOADINGTYPE){
//            val view = LayoutInflater.from(fragment.context)
//                .inflate(R.layout.loading_layout, parent, false)
//            return LoadingViewHolder(view)
//        }
//        return null
//    }
//
//    override fun getItemCount(): Int {
//        return items.size
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        if (holder is ItemViewHolder) {
//            val item = items[position]
//            holder.txt_name.text = items[position]!!.displayName
//            holder.txt_reputation_value.text = items[position]!!.reputation.toString()
//
//        } else if (holder is LoadingViewHolder) {
//            holder.progressBar.isIndeterminate = true
//        }
//    }
//
//    val VIEW_ITEMTYPE = 0
//    val VIEW_LOADINGTYPE = 1
//
//    internal var loadMore: ILoadMore? = null
//    internal var isLoading: Boolean = false
//    internal var visibleThreshold = 5
//    internal var lastVisibleItem: Int = 0
//    internal var totalItemCount: Int = 0
//
//    init {
//        val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager
//        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                totalItemCount = linearLayoutManager.itemCount
//                lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition()
//                if (!isLoading && totalItemCount <= lastVisibleItem + visibleThreshold) {
//                    if (loadMore != null) {
//                        loadMore!!.onLoadMore()
//                    }
//                    isLoading = true
//                }
//
//            }
//        })
//    }
//}