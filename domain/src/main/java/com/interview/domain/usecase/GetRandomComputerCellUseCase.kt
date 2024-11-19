package com.interview.domain.usecase

import com.interview.domain.model.Cell
import com.interview.domain.model.Symbol
import javax.inject.Inject

/**
 * GetRandomComputerCellUseCase is a use case that returns a random empty cell for the computer.
 * @param cells the array of cells in the game
 * @return [Cell] the random empty cell with the computer's symbol (O). It can be null if no
 * empty cell was found.
 */
class GetRandomComputerCellUseCase @Inject constructor() {

    operator fun invoke(cells: Array<Cell>): Cell? {
        val emptyCells = cells.filter { it.symbol == Symbol.EMPTY }
        return if (emptyCells.isNotEmpty()) emptyCells.random().copy(symbol = Symbol.O) else null
    }
}
