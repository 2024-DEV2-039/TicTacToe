package com.interview.domain.usecase

import com.interview.domain.model.Cell
import com.interview.domain.model.Symbol
import javax.inject.Inject

/**
 * IsCellEmptyUseCase is a use case that returns true if the cell at the given index is empty.
 * @param cells the array of cells in the game
 * @param index the index of the cell in the array which we want to check if it is empty
 * @return [Boolean] true if the cell is empty, false otherwise
 */
class IsCellEmptyUseCase @Inject constructor() {

    operator fun invoke(cells: Array<Cell>, index: Int): Boolean {
        return cells[index].symbol == Symbol.EMPTY
    }
}
