package ru.wilddisk.retrofitcoroutine.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.comments_item.view.*
import ru.wilddisk.retrofitcoroutine.R
import ru.wilddisk.retrofitcoroutine.model.Comment

class CommentsAdapter(var items: List<Comment>) :
    RecyclerView.Adapter<CommentsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.comments_item, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount() = items.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: Comment) = with(itemView) {
            tvCommentsPostId?.text = item.mPostId.toString()
            tvCommentsId?.text = item.mId.toString()
            tvCommentsName?.text = item.mName
            tvCommentsEmail?.text = item.mEmail
            tvCommentsBody?.text = item.mBody
        }
    }
}