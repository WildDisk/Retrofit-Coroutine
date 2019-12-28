package ru.wilddisk.retrofitcoroutine.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    val mId: Long,
    @SerializedName("name")
    val mName: String,
    @SerializedName("username")
    val mUsername: String,
    @SerializedName("email")
    val mEmail: String,
    @SerializedName("address")
    val mAddress: AddressSerialize,
    @SerializedName("phone")
    val mPhone: String,
    @SerializedName("website")
    val mWebsite: String,
    @SerializedName("company")
    val mCompany: Company
) {
    data class AddressSerialize(
        @SerializedName("street")
        val mStreet: String,
        @SerializedName("suite")
        val mSuite: String,
        @SerializedName("city")
        val mCity: String,
        @SerializedName("zipcode")
        val mZipcode: String,
        @SerializedName("geo")
        val mGeo: Geo
    ) {
        data class Geo(
            @SerializedName("lat")
            val mLat: String,
            @SerializedName("lng")
            val mLng: String
        )
    }

    data class Company(
        @SerializedName("name")
        val mCompanyName: String,
        @SerializedName("catchPhrase")
        val mCatchPhrase: String,
        @SerializedName("bs")
        val mBs: String
    )
}