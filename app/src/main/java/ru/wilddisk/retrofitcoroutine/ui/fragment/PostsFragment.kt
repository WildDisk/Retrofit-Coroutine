package ru.wilddisk.retrofitcoroutine.ui.fragment

import android.content.Context
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
import ru.wilddisk.retrofitcoroutine.network.api.JsonApi
import ru.wilddisk.retrofitcoroutine.ui.adapter.PostsAdapter

class PostsFragment(private var mContext: Context) : Fragment() {
    private val jsonApi = JsonApi.apiFactory()
    private val mAdapter = PostsAdapter(listOf())
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
        return view
    }

    /**
     * Creation coroutine for receiving [ru.wilddisk.retrofitcoroutine.model.Post]
     * data from API
     */
    private fun loadData() = GlobalScope.launch(Dispatchers.Main) {
        try {
            val postResponse = jsonApi.getPostsAsync().await()
            mSwipeView.isRefreshing = false
            if (postResponse.isSuccessful) {
                mAdapter.items = postResponse.body() ?: listOf()
                mAdapter.notifyDataSetChanged()
            }
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
}