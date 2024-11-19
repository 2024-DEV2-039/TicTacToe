package com.interview.tictactoe

import com.interview.domain.model.Cell
import com.interview.domain.model.Symbol
import com.interview.domain.usecase.IsCellEmptyUseCase
import junit.framework.TestCase.assertFalse
import org.junit.Test

/**
 * Unit Tests of the [IsCellEmptyUseCase]
 */
class IsCellEmptyUseCaseTests {

    private val mockIsCellEmptyUseCase = IsCellEmptyUseCase()

    @Test
    fun `When the symbol of the cell is empty, it is not filled yet`() {
        // Given
        val cells = arrayOf(
            Cell(0, Symbol.X),
            Cell(1, Symbol.O),
            Cell(2, Symbol.O),
            Cell(3, Symbol.X),
            Cell(4, Symbol.X),
            Cell(5, Symbol.EMPTY),
            Cell(6, Symbol.O),
            Cell(7, Symbol.X),
            Cell(8, Symbol.O)
        )

        // When
        val isCellEmpty = mockIsCellEmptyUseCase(cells, 5)

        // Then
        assert(isCellEmpty)
    }

    @Test
    fun `When the symbol of the cell is not empty, it is already filled`() {
        // Given
        val cells = arrayOf(
            Cell(0, Symbol.X),
            Cell(1, Symbol.O),
            Cell(2, Symbol.O),
            Cell(3, Symbol.X),
            Cell(4, Symbol.X),
            Cell(5, Symbol.EMPTY),
            Cell(6, Symbol.O),
            Cell(7, Symbol.X),
            Cell(8, Symbol.O)
        )

        // When
        val isCellEmpty = mockIsCellEmptyUseCase(cells, 4)

        // Then
        assertFalse(isCellEmpty)
    }
}
