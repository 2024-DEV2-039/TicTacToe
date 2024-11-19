package com.interview.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.interview.domain.model.Cell

/**
 * BoardGrid is a composable function that displays a grid of cells in the game board.
 * @param modifier the modifier to be applied to the grid
 * @param cells the array of cells in the game. Each cell is displayed using the [BoardCell]
 * composable.
 * @param onCellClick the callback that is invoked when a cell is clicked.
 */
@Composable
fun BoardGrid(
    modifier: Modifier = Modifier,
    cells: Array<Cell>,
    onCellClick: (Cell) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(count = 3),
        modifier = modifier.background(MaterialTheme.colorScheme.primary),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        userScrollEnabled = false
    ) {
        items(cells.size) { index ->
            BoardCell(
                cell = cells[index]
            ) {
                onCellClick(cells[index])
            }
        }
    }
}
