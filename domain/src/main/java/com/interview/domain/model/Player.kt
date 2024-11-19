package com.interview.domain.model

sealed class Player(val symbol: Symbol) {
    data object Computer : Player(symbol = Symbol.O)
    data object Human : Player(symbol = Symbol.X)
}
