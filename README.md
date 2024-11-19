# Tic Tac Toe Kata

<img src="https://github.com/2024-DEV2-039/TicTacToe/blob/main/screenshots/tic_tac_toe_win_screenshot.png" width="400"  alt="game screenshot"/>

This project is a result of a kata made for an interview. It is a kata based on the well-known game Tic Tac Toe.
I used a TDD approach to build up this project and thus, you will find a couple of unit tests which cover the game logic.

## Game rules
- X always goes first.
- Players cannot play on a played position.
- Players alternate placing X’s and O’s on the board until either:
    - One player has three in a row, horizontally, vertically or diagonally
    - All nine squares are filled.
- If a player is able to draw three X’s or three O’s in a row, that player wins.
- If all nine squares are filled and neither player has three in a row, the game is a draw.

## Architecture
The project architecture follows the clean architecture principles and is separate in 3 modules:
- `:app` : represents the actual applicative module and only contains the `Application` class
- `:domain` : contains the models and use cases, it has no dependency on other module
- `:presentation` : contains the UI components and `ViewModel`. It has a dependency to the domain

I decided not to have a `:data` as it seems unnecessary for this project. There is no need to get data elsewhere and thus this module would remain empty.

## Build With
* Dependency injection: [Hilt](https://dagger.dev/hilt/)
* User interface: [Compose](https://developer.android.com/compose)
* State events: [Flow](https://developer.android.com/kotlin/flow)
* Unit test: [Junit](https://junit.org/junit5/) and [MockK](https://mockk.io/ANDROID.html)

## Getting Started
Clone the repository and build the `:app` module.

## Usage
When the application launches, the game starts automatically and the first player to play is the one with the X symbol (meaning the human player).
A description text will be updated to explain who's turn and also if the game is finished.

You can reset the button at any time by clicking on the "reset" button.

## Unit Tests
- UseCase:
    - `GetRandomComputerCellUseCaseTests`: covers the computer cell choice logic;
    - `HasPlayerWonUseCaseTests`: covers the player success logic;
    - `IsAllCellsFilledUseCaseTests`: covers the draw logic;
    - `IsCellEmptyUseCaseTests`: covers the empty available cell logic.
- ViewModel:
    - `TicTacToeViewModelTests`: covers all the ui state logic based on the use cases.
