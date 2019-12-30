package ru.wilddisk.retrofitcoroutine.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.user_item.view.*
import ru.wilddisk.retrofitcoroutine.R
import ru.wilddisk.retrofitcoroutine.model.User
import ru.wilddisk.retrofitcoroutine.ui.activity.UserDetailActivity

class UsersAdapter(var items: List<User>) : RecyclerView.Adapter<UsersAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            userId = items[position].mId
            val intent = Intent(it.context, UserDetailActivity::class.java)
            intent.putExtra(USER_ID, userId)
            it.context.startActivity(intent)
        }
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: User) = with(itemView) {
            tvUserId?.text = item.mId.toString()
            tvUserName?.text = item.mName
            tvUserUsername?.text = item.mUsername
            tvUserEmail?.text = item.mEmail
        }
    }

    companion object {
        private var userId: Long = 0
        private const val USER_ID = "USER_ID"
    }
}