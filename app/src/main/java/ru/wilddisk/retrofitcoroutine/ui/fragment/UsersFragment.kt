package ru.wilddisk.retrofitcoroutine.ui.fragment

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.wilddisk.retrofitcoroutine.R
import ru.wilddisk.retrofitcoroutine.model.User
import ru.wilddisk.retrofitcoroutine.network.api.JsonApi
import ru.wilddisk.retrofitcoroutine.ui.activity.UserDetailActivity
import ru.wilddisk.retrofitcoroutine.ui.adapter.UsersAdapter

class UsersFragment(private var mContext: Context) : Fragment(),
    UsersAdapter.ItemUserClickListener {
    private val jsonApi = JsonApi.apiFactory()
    private val mAdapter = UsersAdapter(listOf(), this)
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mSwipeView: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.container_view, container, false)
        mSwipeView = view.findViewById(R.id.swipeView)
        mRecyclerView = view.findViewById(R.id.recyclerView)
        mRecyclerView.layoutManager =
            LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false)
        mRecyclerView.adapter = mAdapter
        loadData()
        mSwipeView.setOnRefreshListener {
            loadData()
        }
        this.fragmentManager
        return view
    }

    /**
     * Creation coroutine for receiving [ru.wilddisk.retrofitcoroutine.model.User]
     * data from the API and uploading them to View
     */
    private fun loadData() = GlobalScope.launch(Dispatchers.Main) {
        try {
            val postResponse = jsonApi.getUsersAsync().await()
            mSwipeView.isRefreshing = false
            if (postResponse.isSuccessful) {
                mAdapter.items = postResponse.body() ?: listOf()
                mAdapter.notifyDataSetChanged()
            }
            Log.d("!@#", "$postResponse")
        } catch (e: Exception) {
            Log.d("!@#", "$e")
            Toast.makeText(
                mContext,
                "Something wrong. $e",
                Toast.LENGTH_LONG
            ).show()
            mSwipeView.isRefreshing = false
        }
    }

    override fun onItemClick(item: User, position: Int) {
        userId = item.mId
        val intent = Intent(context, UserDetailActivity::class.java)
        val options = ActivityOptions
            .makeSceneTransitionAnimation(
                context as Activity,
                this.view,
                "transition"
            )
        intent.putExtra(USER_ID, userId)
        startActivity(intent, options.toBundle())
    }

    companion object {
        private var userId: Long = 0
        private const val USER_ID = "USER_ID"
    }
}