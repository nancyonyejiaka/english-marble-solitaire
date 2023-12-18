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

```shell
move (<fromRow>, <fromColumn>) (<toRow>, <toColumn>)
