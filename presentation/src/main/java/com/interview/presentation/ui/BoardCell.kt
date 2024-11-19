package com.interview.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.interview.domain.model.Cell

/**
 * BoardCell is a composable function that displays a single cell in the game board.
 * It takes a [Cell] object as input, which contains the symbol to be displayed in the cell.
 * @param modifier the modifier to be applied to the cell
 * @param cell the cell object that contains the symbol to be displayed
 * @param onCellClick the callback that is invoked when the cell is clicked
 */
@Composable
fun BoardCell(
    modifier: Modifier = Modifier,
    cell: Cell,
    onCellClick: (Cell) -> Unit
) {
    Box(
        modifier = modifier
            .width(120.dp)
            .height(120.dp)
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .clickable {
                onCellClick(cell)
            }
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            fontSize = 60.sp,
            text = cell.symbol.stringValue
        )
    }
}
