package com.interview.tictactoe

import com.interview.domain.model.Cell
import com.interview.domain.model.Symbol
import com.interview.domain.usecase.GetRandomComputerCellUseCase
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test

/**
 * Unit Tests of the [GetRandomComputerCellUseCase]
 */
class GetRandomComputerCellUseCaseTests {

    private val mockGetRandomComputerCellUseCase = GetRandomComputerCellUseCase()

    @Test
    fun `When there is at least one empty cell, the computer return a random one`() {
        // Given
        val cells = arrayOf(
            Cell(0, Symbol.X),
            Cell(1, Symbol.O),
            Cell(2, Symbol.O),
            Cell(3, Symbol.X),
            Cell(4, Symbol.EMPTY),
            Cell(5, Symbol.EMPTY),
            Cell(6, Symbol.EMPTY),
            Cell(7, Symbol.X),
            Cell(8, Symbol.O)
        )

        // When
        val computerCell = mockGetRandomComputerCellUseCase(cells)

        // Then
        assertNotNull(computerCell)
    }

    @Test
    fun `When there is no empty cell left, the computer cell returned is null`() {
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
        val computerCell = mockGetRandomComputerCellUseCase(cells)

        // Then
        assertNull(computerCell)
    }
}
