package com.interview.presentation

import com.interview.domain.model.Cell
import com.interview.domain.model.Player
import com.interview.domain.model.Symbol
import com.interview.domain.usecase.GetRandomComputerCellUseCase
import com.interview.domain.usecase.HasPlayerWonUseCase
import com.interview.domain.usecase.IsAllCellsFilledUseCase
import com.interview.domain.usecase.IsCellEmptyUseCase
import com.interview.presentation.ui.BoardUiState
import com.interview.presentation.viewmodel.TicTacToeViewModel
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.util.Timer
import kotlin.concurrent.schedule

/**
 * Unit Tests of the [TicTacToeViewModel]
 */
class TicTacToeViewModelTests {

    private lateinit var viewModel: TicTacToeViewModel

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

    private val defaultComputerCell = Cell(
        index = 1,
        symbol = Symbol.O
    )

    private val isAllCellsFilledUseCase = mockk<IsAllCellsFilledUseCase>()
    private val isCellEmptyUseCase = mockk<IsCellEmptyUseCase>()
    private val hasPlayerWonUseCase = mockk<HasPlayerWonUseCase>()
    private val getRandomComputerCellUseCase = mockk<GetRandomComputerCellUseCase>()

    @Before
    fun setup() {
        viewModel = TicTacToeViewModel(
            isCellEmptyUseCase = isCellEmptyUseCase,
            hasPlayerWonUseCase = hasPlayerWonUseCase,
            isAllCellsFilledUseCase = isAllCellsFilledUseCase,
            getRandomComputerCellUseCase = getRandomComputerCellUseCase
        )
    }

    @Test
    fun `When the game starts, the player X always goes first`() {
        assertEquals(viewModel.uiState.value.playerPlaying, Player.Human)
    }

    @Test
    fun `When the game starts, all the cells are empty`() {
        assert(viewModel.uiState.value.cells.all { it.symbol == Symbol.EMPTY })
    }

    @Test
    fun `When a reset is requested, the game goes back to the default state`() {
        // Given
        every { isCellEmptyUseCase(any(), any()) } returns true
        every { isAllCellsFilledUseCase(any()) } returns false
        every { hasPlayerWonUseCase(any(), any()) } returns false
        every { getRandomComputerCellUseCase(any()) } returns defaultComputerCell

        // When
        viewModel.onCellClick(Cell(0, Symbol.X))
        viewModel.onResetClick()

        // Then
        assertEquals(viewModel.uiState.value, defaultState)
    }

    @Test
    fun `When the gams is finished (win, loose or draw), if the user clicks on a cell, nothing happens`() {
        // Given
        every { isCellEmptyUseCase(any(), any()) } returns false
        every { isAllCellsFilledUseCase(any()) } returns true
        every { hasPlayerWonUseCase(any(), any()) } returns false
        every { getRandomComputerCellUseCase(any()) } returns defaultComputerCell
        val previousState = viewModel.uiState.value

        // When
        viewModel.onCellClick(Cell(0, Symbol.X))

        // Then
        assertEquals(previousState, viewModel.uiState.value)
    }

    @Test
    fun `When the user clicks on an empty cell, the cell is updated and the player is switched`() {
        // Given
        every { isCellEmptyUseCase(any(), any()) } returns true
        every { isAllCellsFilledUseCase(any()) } returns false
        every { hasPlayerWonUseCase(any(), any()) } returns false
        every { getRandomComputerCellUseCase(any()) } returns defaultComputerCell
        val previousState = viewModel.uiState.value

        // When
        viewModel.onCellClick(Cell(0, Symbol.X))

        // Then
        assertNotEquals(previousState, viewModel.uiState.value)
        assertEquals(viewModel.uiState.value.playerPlaying, Player.Computer)
    }

    @Test
    fun `When the user wins, the game is finished`() {
        // Given
        every { isCellEmptyUseCase(any(), any()) } returns true
        every { isAllCellsFilledUseCase(any()) } returns false
        every { hasPlayerWonUseCase(any(), Player.Human) } returns true
        every { getRandomComputerCellUseCase(any()) } returns defaultComputerCell

        // When
        viewModel.onCellClick(Cell(0, Symbol.X))

        // Then
        assertTrue(viewModel.uiState.value.hasHumanWon)
        assertFalse(viewModel.uiState.value.isGameInProgress)
    }

    @Test
    fun `When the computer wins, the game is finished`() {
        // Given
        every { isCellEmptyUseCase(any(), any()) } returns true
        every { isAllCellsFilledUseCase(any()) } returns false
        every { hasPlayerWonUseCase(any(), Player.Human) } returns false
        every { hasPlayerWonUseCase(any(), Player.Computer) } returns true
        every { getRandomComputerCellUseCase(any()) } returns defaultComputerCell

        // When
        viewModel.onCellClick(Cell(0, Symbol.X))

        // Wait for the computer to play
        Timer().apply {
            schedule(2500) {
                // Then
                assertTrue(viewModel.uiState.value.hasComputerWon)
                assertFalse(viewModel.uiState.value.isGameInProgress)
            }
        }
    }

    @Test
    fun `When the user clicks on an empty cell and all the cells are filled without a win, the gams is a draw`() {
        // Given
        every { isCellEmptyUseCase(any(), any()) } returns true
        every { isAllCellsFilledUseCase(any()) } returns true
        every { hasPlayerWonUseCase(any(), any()) } returns false
        every { getRandomComputerCellUseCase(any()) } returns defaultComputerCell

        // When
        viewModel.onCellClick(Cell(0, Symbol.X))

        // Then
        assertTrue(viewModel.uiState.value.isDraw)
        assertFalse(viewModel.uiState.value.isGameInProgress)
    }
}
