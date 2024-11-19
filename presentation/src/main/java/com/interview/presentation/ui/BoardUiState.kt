package com.interview.presentation.ui

import com.interview.domain.model.Cell
import com.interview.domain.model.Player

/**
 * BoardUiState is a data class that represents the ui state of the game.
 * @param cells the array of cells in the game.
 * @param playerPlaying the current player playing the game.
 * @param isGameInProgress a boolean value indicating whether the game is in progress or not.
 * @param hasHumanWon a boolean value indicating whether the human player has won the game or not.
 * @param hasComputerWon a boolean value indicating whether the computer has won the game or not.
 * @param isDraw a boolean value indicating whether the game is a draw or not.
 */
data class BoardUiState(
    val cells: Array<Cell>,
    val playerPlaying: Player,
    val isGameInProgress: Boolean,
    val hasHumanWon: Boolean,
    val hasComputerWon: Boolean,
    val isDraw: Boolean,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BoardUiState

        if (!cells.contentEquals(other.cells)) return false
        if (playerPlaying != other.playerPlaying) return false
        if (isGameInProgress != other.isGameInProgress) return false
        if (hasHumanWon != other.hasHumanWon) return false
        if (hasComputerWon != other.hasComputerWon) return false
        if (isDraw != other.isDraw) return false

        return true
    }

    override fun hashCode(): Int {
        var result = cells.contentHashCode()
        result = 31 * result + playerPlaying.hashCode()
        result = 31 * result + isGameInProgress.hashCode()
        result = 31 * result + hasHumanWon.hashCode()
        result = 31 * result + hasComputerWon.hashCode()
        result = 31 * result + isDraw.hashCode()
        return result
    }
}
