package ru.wilddisk.retrofitcoroutine.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_user.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ru.wilddisk.retrofitcoroutine.R
import ru.wilddisk.retrofitcoroutine.network.api.JsonApi

class UserDetailActivity : AppCompatActivity() {
    private lateinit var bundle: Bundle
    private val jsonApi = JsonApi.apiFactory()
    private var userId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        intent.extras?.let { bundle = it }
        userId = bundle.getLong(USER_ID)
        loadData()
    }

    /**
     * Creation of a coroutine for receiving [ru.wilddisk.retrofitcoroutine.model.User]
     * data from the API and uploading them to View
     */
    @SuppressLint("SetTextI18n")
    private fun loadData() = GlobalScope.launch(Dispatchers.Main) {
        try {
            val postResponse = jsonApi.getUserAsync(userId).await()
            tvActivityUserId?.text = "id: ${postResponse.mId}"
            tvActivityUserName?.text = "name: ${postResponse.mName}"
            tvActivityUserUsername?.text = "username: ${postResponse.mUsername}"
            tvActivityUserEmail?.text = "email: ${postResponse.mEmail}"
            tvActivityUserAddress?.text = "address"
            tvActivityUserStreet?.text = "street: ${postResponse.mAddress.mStreet}"
            tvActivityUserSuite?.text = "suite: ${postResponse.mAddress.mSuite}"
            tvActivityUserCity.text = "city: ${postResponse.mAddress.mCity}"
            tvActivityUserZipcode?.text = "zipcode: ${postResponse.mAddress.mZipcode}"
            tvActivityUserLat?.text = "lat: ${postResponse.mAddress.mGeo.mLat}"
            tvActivityUserLng?.text = "lng: ${postResponse.mAddress.mGeo.mLng}"
            tvActivityUserPhone?.text = "phone: ${postResponse.mPhone}"
            tvActivityUserWebsite?.text = "website: ${postResponse.mWebsite}"
            tvActivityUserCompany?.text = "company"
            tvActivityUserCompanyName?.text = "name: ${postResponse.mCompany.mCompanyName}"
            tvActivityUserCatchPhrase?.text = "catchPhrase: ${postResponse.mCompany.mCatchPhrase}"
            tvActivityUserBs?.text = "bs: ${postResponse.mCompany.mBs}"
            Log.d("!@#", postResponse.mWebsite)
        } catch (e: Exception) {
            Log.d("!@#", "$e")
            Toast.makeText(
                this@UserDetailActivity,
                "Something wrong. $e",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    companion object {
        private const val USER_ID = "USER_ID"
    }
}