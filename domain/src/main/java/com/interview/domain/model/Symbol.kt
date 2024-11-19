package com.interview.domain.model

sealed class Symbol(
    val value: Int,
    val stringValue: String
) {
    data object EMPTY : Symbol(0, "")
    data object X : Symbol(1, "X")
    data object O : Symbol(-1, "O")
}
