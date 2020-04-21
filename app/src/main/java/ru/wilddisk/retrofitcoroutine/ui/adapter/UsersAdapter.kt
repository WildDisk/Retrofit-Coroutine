package ru.wilddisk.retrofitcoroutine.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.user_item.view.*
import ru.wilddisk.retrofitcoroutine.R
import ru.wilddisk.retrofitcoroutine.model.User

class UsersAdapter(
    var items: List<User>,
    private val clickListener: ItemUserClickListener
) : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], clickListener)
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: User, click: ItemUserClickListener) = with(itemView) {
            tvUserId?.text = item.mId.toString()
            tvUserName?.text = item.mName
            tvUserUsername?.text = item.mUsername
            tvUserEmail?.text = item.mEmail
            itemView.setOnClickListener {
                click.onItemClick(item, adapterPosition)
            }
        }
    }

    interface ItemUserClickListener {
        fun onItemClick(item: User, position: Int)
    }
}