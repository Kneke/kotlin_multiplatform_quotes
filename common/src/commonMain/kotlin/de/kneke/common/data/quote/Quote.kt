package de.kneke.common.data.quote

import kotlinx.serialization.Serializable

@Serializable
data class Quote(
    val id: Int,
    val quote: String,
    val author: String,
    val permalink: String?
)