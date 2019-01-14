package data

import kotlinx.serialization.*

@Serializable
data class Quote(
    val author: String,
    val id: Int,
    val permalink: String,
    val quote: String
)