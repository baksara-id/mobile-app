package com.baksara.app.response

import com.google.gson.annotations.SerializedName

data class GraphQLResponse(
    @field:SerializedName("data")
    val data: Data?,

    @field:SerializedName("errors")
    val errors: List<Error>?,
)

// Fill all output structure here
data class Data(
    val coba: String,
//    @field:SerializedName("register")
//    val register: User?,
//
//    @field:SerializedName("login")
//    val login: AuthToken?,
//
//    @field:SerializedName("logout")
//    val logout: Logout?,
//
//    @field:SerializedName("refresh_access_token")
//    val refreshAccessToken: AuthToken?,
//
//    @field:SerializedName("herbals")
//    val herbals: List<Herbal>?,
//
//    @field:SerializedName("herbal")
//    val herbal: Herbal?,
//
//    @field:SerializedName("bookmark_herbal")
//    val bookmarkHerbal: BookmarkHerbal?,
//
//    @field:SerializedName("products")
//    val products: List<Product>?,
//
//    @field:SerializedName("product")
//    val product: Product?,
//
//    @field:SerializedName("product_favorite_user")
//    val favoriteProduct: FavoriteProduct?,
//
//    @field:SerializedName("potd")
//    val potd: Potd?,
//
//    @field:SerializedName("ppotw")
//    val ppotw: List<Herbal>?,
//
//    @field:SerializedName("user")
//    val user: User?,
//
//    @field:SerializedName("banner")
//    val offer: List<Offer>?,
//
//    @field:SerializedName("product_health_interests")
//    val productHealthInterests: List<ProductHealthInterest>?,
//
//    @field:SerializedName("product_health_interest")
//    val productHealthInterest: ProductHealthInterest?,
//
//    @field:SerializedName("most_loved_products")
//    val mostLovedProduct: List<Product>?,
//
//    @field:SerializedName("most_review_products")
//    val mostReviewProduct: List<Product>?,
//
//    @field:SerializedName("best_sellers")
//    val bestSellerProduct: List<Product>?,
//
//    @field:SerializedName("location")
//    val locations: List<Location>?,
//
//    @field:SerializedName("save_order")
//    val saveOrder: SaveOrder?,
)

data class Error(
    @field:SerializedName("message")
    val message: String?,
)