package com.interview.tictactoe

import com.interview.domain.model.Cell
import com.interview.domain.model.Player
import com.interview.domain.model.Symbol
import com.interview.domain.usecase.HasPlayerWonUseCase
import junit.framework.TestCase.assertFalse
import org.junit.Test

/**
 * Unit Tests of the [HasPlayerWonUseCase]
 */
class HasPlayerWonUseCaseTests {

    private val mockHasPlayerWonUseCase = HasPlayerWonUseCase()

    @Test
    fun `When a player has no identical symbols on a row, he does not win`() {
        // Given
        val cells = arrayOf(
            Cell(0, Symbol.X),
            Cell(1, Symbol.O),
            Cell(2, Symbol.O),
            Cell(3, Symbol.X),
            Cell(4, Symbol.EMPTY),
            Cell(5, Symbol.EMPTY),
            Cell(6, Symbol.O),
            Cell(7, Symbol.EMPTY),
            Cell(8, Symbol.EMPTY)
        )
        val player = Player.Human

        // When
        val hasPlayerWon = mockHasPlayerWonUseCase(cells, player)

        // Then
        assertFalse(hasPlayerWon)
    }

    @Test
    fun `When a player has three identical symbols on a vertical row, he wins`() {
        // Given
        val cells = arrayOf(
            Cell(0, Symbol.X),
            Cell(1, Symbol.O),
            Cell(2, Symbol.O),
            Cell(3, Symbol.X),
            Cell(4, Symbol.EMPTY),
            Cell(5, Symbol.EMPTY),
            Cell(6, Symbol.X),
            Cell(7, Symbol.EMPTY),
            Cell(8, Symbol.EMPTY)
        )
        val player = Player.Human

        // When
        val hasPlayerWon = mockHasPlayerWonUseCase(cells, player)

        // Then
        assert(hasPlayerWon)
    }

    @Test
    fun `When a player has three identical symbols on an horizontal row, he wins`() {
        // Given
        val cells = arrayOf(
            Cell(0, Symbol.X),
            Cell(1, Symbol.O),
            Cell(2, Symbol.O),
            Cell(3, Symbol.X),
            Cell(4, Symbol.X),
            Cell(5, Symbol.X),
            Cell(6, Symbol.EMPTY),
            Cell(7, Symbol.EMPTY),
            Cell(8, Symbol.EMPTY)
        )
        val player = Player.Human

        // When
        val hasPlayerWon = mockHasPlayerWonUseCase(cells, player)

        // Then
        assert(hasPlayerWon)
    }

    @Test
    fun `When a player has three identical symbols on a diagonal row, he wins`() {
        // Given
        val cells = arrayOf(
            Cell(0, Symbol.X),
            Cell(1, Symbol.O),
            Cell(2, Symbol.O),
            Cell(3, Symbol.O),
            Cell(4, Symbol.X),
            Cell(5, Symbol.EMPTY),
            Cell(6, Symbol.O),
            Cell(7, Symbol.EMPTY),
            Cell(8, Symbol.X)
        )
        val player = Player.Human

        // When
        val hasPlayerWon = mockHasPlayerWonUseCase(cells, player)

        // Then
        assert(hasPlayerWon)
    }
}
