package id.kotlin.android.data

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class OmniScience(
        @SerializedName("psize") val size: Long,
        @SerializedName("word") val word: List<String>,
        @SerializedName("category") val categories: List<Category>,
        @SerializedName("user") val users: List<User>,
        @SerializedName("history") val histories: List<History>,
        @SerializedName("keyword") val keywords: List<Keyword>,
        @SerializedName("product") val products: List<Product>,
        @SerializedName("time") val time: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class Category(
        @SerializedName("category") val category: String,
        @SerializedName("url") val url: String,
        @SerializedName("name") val name: String,
        @SerializedName("id") val id: Long
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class User(
        @SerializedName("id") val id: Long,
        @SerializedName("name") val name: String,
        @SerializedName("url") val url: String,
        @SerializedName("img") val img: String
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class History(@SerializedName("id") val id: Long) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class Keyword(@SerializedName("id") val id: Long) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class Product(
        @SerializedName("id") val id: Long,
        @SerializedName("img") val img: String,
        @SerializedName("url") val url: String,
        @SerializedName("name") val name: String,
        @SerializedName("price") val price: Long
) : Parcelable

@SuppressLint("ParcelCreator")
@Parcelize
data class TopKeywords(
        @SerializedName("status") val status: String,
        @SerializedName("top_keywords") val topKeywords: List<String>,
        @SerializedName("message") val message: String
) : Parcelable