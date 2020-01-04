package ru.wilddisk.retrofitcoroutine.ui.adapter

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.post_item.view.*
import ru.wilddisk.retrofitcoroutine.R
import ru.wilddisk.retrofitcoroutine.model.Post

class PostsAdapter(var items: List<Post>) : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.post_item, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            val rippleEffect = TypedValue()
            it.context.theme.resolveAttribute(
                android.R.attr.selectableItemBackground,
                rippleEffect,
                true
            )
            it.setBackgroundResource(rippleEffect.resourceId)
        }
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: Post) = with(itemView) {
            tvPostId?.text = item.mId.toString()
            tvPostTitle?.text = item.mTitle
            tvPpostBody?.text = item.mBody
        }
    }
}