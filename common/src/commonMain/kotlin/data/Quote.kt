package data

import kotlinx.serialization.Serializable

@Serializable
data class Quote(
    val author: String,
    val id: Int,
    val permalink: String,
    val quote: String
)