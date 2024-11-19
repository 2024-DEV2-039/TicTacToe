package com.interview.tictactoe

import com.interview.domain.model.Cell
import com.interview.domain.model.Symbol
import com.interview.domain.usecase.IsAllCellsFilledUseCase
import junit.framework.TestCase.assertFalse
import org.junit.Test

/**
 * Unit Tests of the [IsAllCellsFilledUseCase]
 */
class IsAllCellsFilledUseCaseTests {

    private val mockIsAllCellsFilledUseCase = IsAllCellsFilledUseCase()

    @Test
    fun `When there is at least one empty cell, all cells are not filled`() {
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
        val isAllCellsFilled = mockIsAllCellsFilledUseCase(cells)

        // Then
        assertFalse(isAllCellsFilled)
    }

    @Test
    fun `When there is at no empty cell left, all cells are not filled`() {
        // Given
        val cells = arrayOf(
            Cell(0, Symbol.X),
            Cell(1, Symbol.O),
            Cell(2, Symbol.O),
            Cell(3, Symbol.X),
            Cell(4, Symbol.O),
            Cell(5, Symbol.X),
            Cell(6, Symbol.O),
            Cell(7, Symbol.X),
            Cell(8, Symbol.O)
        )

        // When
        val isAllCellsFilled = mockIsAllCellsFilledUseCase(cells)

        // Then
        assert(isAllCellsFilled)
    }
}
