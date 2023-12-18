# Marble Solitaire - Java Implementation

## Project Overview
This Java project presents an extensive implementation of the Marble Solitaire game, demonstrating robust software design principles through a model-view-controller (MVC) architecture. The project encompasses the development of various Marble Solitaire game variants, a controller to facilitate gameplay, and an enhanced view for improved user interaction.

## Key Features
- **Variants of Marble Solitaire**: Implements English, European, and Triangular versions of Marble Solitaire.
- **Customizable Game Settings**: Offers flexibility to modify board size and initial empty slot position.
- **MVC Architecture**: Adopts a clear separation of concerns - the model represents game logic, the view handles output, and the controller manages user input and game progression.
- **Enhanced User Interaction**: Utilizes a command-line interface for game settings and play, along with improved view functionalities to display game state and messages.

## Game Variants
1. **English Marble Solitaire**: Traditional cross-shaped board layout.
2. **European Marble Solitaire**: Octagonal board layout for a different strategic approach.
3. **Triangular Marble Solitaire**: Unique triangular board, adding a fresh challenge with distinct move rules.

## Getting Started

### Running the Game
- Compile and run the `MarbleSolitaire.java` class.
- Use command-line arguments to select the game variant and customize settings.

#### Command-Line Arguments
- Specify variant: `english`, `european`, or `triangular`.
- Optional parameters:
  - `-size N`: Set board size (N).
  - `-hole R C`: Set initial empty slot (row R, column C).

### Examples
- Play English version with a board arm-width of 6: `english -size 6`.
- Play Triangular version with default settings: `triangular`.
- Play European version with the initial hole at a specific position: `european -hole 1 4`.

## Movement Mechanics in CLI

### Making Moves
In the CLI, players make moves by specifying the starting and ending positions of a marble move. Each move command follows the format:

move <fromRow> <fromColumn> <toRow> <toColumn>

- `fromRow` and `fromColumn`: The row and column indices of the marble to be moved.
- `toRow` and `toColumn`: The destination row and column indices for the marble.

### Valid Moves

A move in Marble Solitaire is valid if it adheres to the following rules:
- A marble must jump over an adjacent marble into an empty slot.
- The jumped marble is then removed from the board.
- Moves can be made horizontally or vertically, but not diagonally.

### Example Moves

To move a marble from position (2,3) to position (4,3) in the English version, the command is:

move 2 3 4 3

### Quitting Game
Simply input `q` or `quit` to end a game.

## Development Details

### MVC Architecture Components
- **Model**: Defines game logic and state. Includes interfaces and implementations for different game variants.
- **View**: Manages textual representation of the game's state. Enhanced to output game state and arbitrary messages.
- **Controller**: Handles user inputs and game progression. It facilitates interaction between the model and view.

### Development Phases
1. **Phase 1**: Implementation of the model for English Marble Solitaire.
2. **Phase 2**: Expansion to support European and Triangular variants.
3. **Phase 3**: Development of the controller and enhancements to the view for a complete, interactive game experience.

### Testing
- Extensive JUnit tests ensure functional correctness across all components of the game.
- Continuous integration of testing from initial model development to final controller implementation.


