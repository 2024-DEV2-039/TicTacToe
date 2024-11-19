package com.interview.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.interview.domain.model.Cell
import com.interview.domain.model.Player
import com.interview.domain.model.Symbol
import com.interview.presentation.viewmodel.TicTacToeViewModel

/**
 * BoardScreen is a composable function that displays the whole game board and listen to the
 * ViewModel state to update the UI and notify the ViewModel when a cell or reset button is clicked.
 * @param modifier the modifier to be applied to the screen
 * @param viewModel the [TicTacToeViewModel] that contains the state of the game and exposes cell
 * click action and reset click action.
 */
@Composable
fun BoardScreen(
    modifier: Modifier,
    viewModel: TicTacToeViewModel = hiltViewModel()
) {
    val boardUiState by viewModel.uiState.collectAsState()

    BoardLayout(
        modifier = modifier,
        cells = boardUiState.cells,
        hasHumanWon = boardUiState.hasHumanWon,
        hasComputerWon = boardUiState.hasComputerWon,
        isDraw = boardUiState.isDraw,
        playerPlaying = boardUiState.playerPlaying,
        onCellClick = {
            viewModel.onCellClick(it)
        },
        onResetClick = {
            viewModel.onResetClick()
        }
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun BoardScreenPreview() {
    val cells = Array(9) {
        Cell(
            index = it,
            symbol = Symbol.EMPTY
        )
    }
    BoardLayout(
        modifier = Modifier,
        cells,
        hasHumanWon = true,
        hasComputerWon = false,
        playerPlaying = Player.Computer,
        isDraw = false,
        onCellClick = {
        },
        onResetClick = {
        }
    )
}
