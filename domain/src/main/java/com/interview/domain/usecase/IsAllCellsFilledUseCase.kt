package com.interview.domain.usecase

import com.interview.domain.model.Cell
import com.interview.domain.model.Symbol
import javax.inject.Inject

/**
 * IsAllCellsFilledUseCase is a use case that returns true if all the cells in the array are empty.
 * @param array the array of cells in the game
 * @return [Boolean] true if all the cells are filled, false otherwise
 */
class IsAllCellsFilledUseCase @Inject constructor() {

    operator fun invoke(array: Array<Cell>): Boolean {
        return array.none { it.symbol == Symbol.EMPTY }
    }
}
