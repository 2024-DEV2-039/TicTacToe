package com.interview.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.interview.domain.model.Cell
import com.interview.domain.model.Player
import com.interview.presentation.R

/**
 * BoardLayout is a composable function that displays the game board and the reset button and texts
 * such as the game title and the status (current player, winner, etc.).
 * @param modifier the modifier to be applied to the layout
 * @param cells the array of cells in the game.
 * @param hasHumanWon a boolean value indicating whether the human player has won the game or not.
 * @param hasComputerWon a boolean value indicating whether the computer has won the game or not.
 * @param isDraw a boolean value indicating whether the game is a draw or not.
 * @param playerPlaying the current player playing the game.
 * @param onCellClick the callback that is invoked when a cell is clicked.
 * @param onResetClick the callback that is invoked when the reset button is clicked.
 */
@Composable
fun BoardLayout(
    modifier: Modifier = Modifier,
    cells: Array<Cell>,
    hasHumanWon: Boolean,
    hasComputerWon: Boolean,
    isDraw: Boolean,
    playerPlaying: Player,
    onCellClick: (Cell) -> Unit,
    onResetClick: () -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 50.dp),
            fontSize = 50.sp,
            text = stringResource(R.string.title),
        )
        BoardGrid(
            modifier = modifier,
            cells = cells,
            onCellClick = onCellClick
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = 30.dp),
            fontSize = 30.sp,
            text = getDescriptionText(playerPlaying, hasHumanWon, hasComputerWon, isDraw)
        )
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = onResetClick
        ) {
            Text(stringResource(R.string.reset_game_action))
        }
    }
}

/**
 * Helper function that returns a string resource based on the current state of the game.
 * @param playerPlaying the current player playing the game.
 * @param hasHumanWon a boolean value indicating whether the human player has won the game or not.
 * @param hasComputerWon a boolean value indicating whether the computer has won the game or not.
 * @param isDraw a boolean value indicating whether the game is a draw or not.
 */
@Composable
private fun getDescriptionText(
    playerPlaying: Player,
    hasHumanWon: Boolean,
    hasComputerWon: Boolean,
    isDraw: Boolean
): String {
    val resource = if (hasHumanWon) {
        R.string.human_win_message
    }  else if (hasComputerWon) {
        R.string.computer_win_message
    } else if (isDraw) {
        R.string.draw_message
    } else if (playerPlaying is Player.Human) {
        R.string.human_playing_message
    } else {
        R.string.computer_playing_message
    }
    return stringResource(resource)
}