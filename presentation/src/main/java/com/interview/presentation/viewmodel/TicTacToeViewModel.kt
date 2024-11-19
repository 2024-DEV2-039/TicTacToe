package com.interview.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.interview.domain.model.Cell
import com.interview.domain.model.Player
import com.interview.domain.model.Symbol
import com.interview.domain.usecase.GetRandomComputerCellUseCase
import com.interview.domain.usecase.HasPlayerWonUseCase
import com.interview.domain.usecase.IsAllCellsFilledUseCase
import com.interview.domain.usecase.IsCellEmptyUseCase
import com.interview.presentation.ui.BoardUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Timer
import javax.inject.Inject
import kotlin.concurrent.schedule

/**
 * TicTacToeViewModel is a ViewModel that manages the state of the Tic Tac Toe game.
 * It uses the use cases to determine the state of the game based on the player (human) and
 * computer choices.
 * It exposes a [StateFlow] of [BoardUiState] to the UI layer.
 * Basically, it has 2 actions: [onResetClick] which resets the game state and [onCellClick] which
 * update the cell selected by the user, let the computer play and check if the game is
 * finished (win, loose or draw).
 */
@HiltViewModel
class TicTacToeViewModel @Inject constructor(
    private val isCellEmptyUseCase: IsCellEmptyUseCase,
    private val hasPlayerWonUseCase: HasPlayerWonUseCase,
    private val isAllCellsFilledUseCase: IsAllCellsFilledUseCase,
    private val getRandomComputerCellUseCase: GetRandomComputerCellUseCase
) : ViewModel() {

    companion object {
        private const val TIMER_DELAY = 2000L
    }

    private val defaultState = BoardUiState(
        cells = Array(9) {
            Cell(
                index = it,
                symbol = Symbol.EMPTY
            )
        },
        isGameInProgress = true,
        playerPlaying = Player.Human,
        hasHumanWon = false,
        hasComputerWon = false,
        isDraw = false,
    )

    private val _uiState = MutableStateFlow(defaultState)
    val uiState: StateFlow<BoardUiState> = _uiState.asStateFlow()

    private val isGameInProgress: Boolean
        get() = _uiState.value.isGameInProgress

    private val currentPlayer: Player
        get() = _uiState.value.playerPlaying

    private val currentCells: Array<Cell>
        get() = _uiState.value.cells

    private val hasHumanWon: Boolean
        get() = hasPlayerWonUseCase(currentCells, Player.Human)

    private val hasComputerWon: Boolean
        get() = hasPlayerWonUseCase(currentCells, Player.Computer)

    private val isGameADraw: Boolean
        get() = isAllCellsFilledUseCase(currentCells)

    private var timer = Timer()

    init {
        resetGame()
    }

    /**
     * onResetClick is a function that resets the game state.
     */
    fun onResetClick() {
        resetGame()
    }

    /**
     * onCellClick is a function that updates the state of the game based on the cell clicked by
     * the user. It will then switch the player to let the computer play and check if the game is
     * done.
     * @param cell the [Cell] selected by the user.
     */
    fun onCellClick(cell: Cell) {
        // If the game is not in progress or it is not human's turn, we do nothing
        if (!isGameInProgress || currentPlayer !is Player.Human) return

        // If playHuman returns true, it means the game is finished, we don't proceed
        if (playHuman(cell)) return

        // Set a timer to fake the computer thinking time
        timer = Timer().apply {
            schedule(TIMER_DELAY) {
                // If playComputer returns true, it means the game is finished, we don't proceed
                if (playComputer()) return@schedule
            }
        }
    }

    private fun playHuman(cell: Cell): Boolean {
        // Verify if the cell is empty
        val isCellEmpty = isCellEmptyUseCase(currentCells, cell.index)
        // If it is, then we can update the cell, otherwise nothing happen
        if (isCellEmpty) {
            val updateCells = updateCellInArray(currentCells, cell.copy(symbol = Symbol.X))
            updateState { copy(cells = updateCells) }
        } else {
            return true
        }

        // Then check if user has won
        if (updateStateIfHumanWon()) return true

        // Then check if the game is a draw
        if (updateStateIfDraw()) return true

        // Switch to the computer
        switchPlayer()

        return false
    }

    private fun playComputer(): Boolean {
        // Let the computer play, if he can't choose we do nothing (it should not happen as we
        // check for draw before)
        val computerCell = getRandomComputerCellUseCase(currentCells) ?: return true
        val updateCells = updateCellInArray(currentCells, computerCell)
        updateState { copy(cells = updateCells) }

        // Then check if computer has won
        if (updateStateIfComputerWon()) return true

        // Then check if the game is a draw
        if (updateStateIfDraw()) return true

        // Switch back to the human
        switchPlayer()

        return false
    }

    private fun updateCellInArray(cells: Array<Cell>, cell: Cell): Array<Cell> {
        // update the given cell in the array of cell
        val updatedCells = cells.toMutableList()
        updatedCells[cell.index] = cell
        return updatedCells.toTypedArray()
    }

    private fun switchPlayer() {
        updateState {
            copy(
                playerPlaying = when (currentPlayer) {
                    Player.Human -> Player.Computer
                    Player.Computer -> Player.Human
                }
            )
        }
    }

    private fun updateStateIfHumanWon(): Boolean {
        if (hasHumanWon) {
            updateState {
                copy(
                    hasHumanWon = true,
                    isGameInProgress = false
                )
            }
            return true
        }
        return false
    }

    private fun updateStateIfComputerWon(): Boolean {
        if (hasComputerWon) {
            updateState {
                copy(
                    hasComputerWon = true,
                    isGameInProgress = false,
                )
            }
            return true
        }
        return false
    }

    private fun updateStateIfDraw(): Boolean {
        if (isGameADraw) {
            updateState {
                copy(
                    isDraw = true,
                    isGameInProgress = false
                )
            }
            return true
        }
        return false
    }

    private fun resetGame() {
        // cancel the timer if computer was playing
        timer.cancel()
        // update the state to the initial default one
        updateState { defaultState }
    }

    private inline fun updateState(update: BoardUiState.() -> BoardUiState) {
        _uiState.value = uiState.value.update()
    }

    override fun onCleared() {
        super.onCleared()
        // cancel the timer if computer was playing
        timer.cancel()
    }
}
