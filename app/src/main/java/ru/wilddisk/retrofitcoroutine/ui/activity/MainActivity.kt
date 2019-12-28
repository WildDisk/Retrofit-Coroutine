package ru.wilddisk.retrofitcoroutine.ui.activity

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkRequest
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import ru.wilddisk.retrofitcoroutine.R
import ru.wilddisk.retrofitcoroutine.network.ConnectivityReceiver
import ru.wilddisk.retrofitcoroutine.ui.fragment.CommentsFragment
import ru.wilddisk.retrofitcoroutine.ui.fragment.PostsFragment
import ru.wilddisk.retrofitcoroutine.ui.fragment.UsersFragment

class MainActivity : AppCompatActivity() {
    private val mNetworkBuilder = NetworkRequest.Builder()
    private lateinit var mConnectivityManager: ConnectivityManager
    private lateinit var mBottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mBottomNavigationView = bottomNavigationView
        mBottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

        mConnectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        mConnectivityManager.registerNetworkCallback(
            mNetworkBuilder.build(),
            ConnectivityReceiver(this)
        )

        if (savedInstanceState == null) supportFragmentManager.beginTransaction()
            .add(R.id.main_container, PostsFragment(this))
            .commit()
    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener {
            return@OnNavigationItemSelectedListener when (it.itemId) {
                R.id.btnvPosts -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_container, PostsFragment(this))
                        .commit()
                    true
                }
                R.id.btnvUsers -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_container, UsersFragment(this))
                        .commit()
                    true
                }
                R.id.btnvComments -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_container, CommentsFragment(this))
                        .commit()
                    true
                }
                else -> false
            }
        }
}

