package com.interview.domain.usecase

import com.interview.domain.model.Cell
import com.interview.domain.model.Player
import com.interview.domain.model.winningCombinations
import javax.inject.Inject

/**
 * HasPlayerWonUseCase is a use case that determine if the given player has won the game.
 * To do so, it iterates on the [winningCombinations] and adds its value.
 * Each symbol has its own value: O == -1, X == 1 and EMPTY == 0. By multiplying the combinations,
 * we can assume that if we have 3 as a result, it means that all the symbols on the combination are
 * X. In the same way, if we have -3, all the symbols are O.
 *
 * Example:
 * | X (1) |   (0)  | X (1)  |
 * | X (1) | O (-1) | O (-1) |
 * | X (1) | O (-1) |   (0)  |
 * If we add up all the winning combinations possible will we find for the first column 3 which is
 * the winning total for the Symbol X. Thus, we can conclude that the player playing with X wins.
 *
 * @param cells the array of cells in the game
 * @param player the player that we want to check if he has won the game
 * @return [Boolean] true if the player has won, false otherwise
 */
class HasPlayerWonUseCase @Inject constructor() {

    operator fun invoke(cells: Array<Cell>, player: Player): Boolean {
        val totalPlayerSymbol = player.symbol.value * 3

        for (combination in winningCombinations) {
            val totalCombination = cells[combination[0]].symbol.value +
                    cells[combination[1]].symbol.value +
                    cells[combination[2]].symbol.value

            if (totalPlayerSymbol == totalCombination) return true
        }
        return false
    }
}
