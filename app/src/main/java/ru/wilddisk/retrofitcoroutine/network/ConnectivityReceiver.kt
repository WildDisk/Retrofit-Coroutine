package ru.wilddisk.retrofitcoroutine.network

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.util.Log
import android.widget.Toast

/**
 * Check of network status
 *
 * @param context
 */
class ConnectivityReceiver(var context: Context) : ConnectivityManager.NetworkCallback() {
    /**
     * Network is available
     *
     * @param network
     */
    override fun onAvailable(network: Network) {
        super.onAvailable(network)
        context.sendBroadcast(getConnectivityIntent(false))
        Toast.makeText(context, "Network is available", Toast.LENGTH_SHORT).show()
        Log.d("!@#", "Network: Available")
    }

    /**
     * Network is lost
     *
     * @param network
     */
    override fun onLost(network: Network) {
        super.onLost(network)
        context.sendBroadcast(getConnectivityIntent(true))
        Toast.makeText(context, "Network is lost", Toast.LENGTH_SHORT).show()
        Log.d("!@#", "Network: Lost")
    }

    /**
     * Transfer network status in activity [context]
     *
     * @param noConnection
     */
    private fun getConnectivityIntent(noConnection: Boolean): Intent {
        val intent = Intent()
        intent.action = CONNECTIVITY_CHANGE
        intent.putExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, noConnection)
        return intent
    }

    companion object {
        private const val CONNECTIVITY_CHANGE: String =
            "ru.wilddisk.retrofitcoroutine.CONNECTIVITY_CHANGE"
    }
}