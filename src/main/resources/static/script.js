const board = document.getElementById('board');
const statusDisplay = document.getElementById('game-status');
const playAgainButton = document.getElementById('play-again');

let currentPlayer = 'X'; // Standardized player names
let boardState = Array(9).fill(null);

// Render the board
const renderBoard = () => {
  board.innerHTML = '';
  boardState.forEach((cell, index) => {
    const cellDiv = document.createElement('div');
    cellDiv.classList.add('cell');
    if (cell) {
      cellDiv.textContent = cell;
      cellDiv.classList.add('disabled');
    }
    cellDiv.onclick = () => handleClick(index);
    board.appendChild(cellDiv);
  });
};

// Handle cell click
const handleClick = (index) => {
  if (boardState[index]) return; // Prevent overwriting a filled cell
  boardState[index] = currentPlayer;
  currentPlayer = currentPlayer === 'X' ? 'O' : 'X';
  statusDisplay.textContent = `Player ${currentPlayer}'s Turn`;
  checkWinner();
  renderBoard();
};

// Check for a winner or draw
const checkWinner = () => {
  const winConditions = [
    [0, 1, 2],
    [3, 4, 5],
    [6, 7, 8],
    [0, 3, 6],
    [1, 4, 7],
    [2, 5, 8],
    [0, 4, 8],
    [2, 4, 6],
  ];

  for (let condition of winConditions) {
    const [a, b, c] = condition;
    if (boardState[a] && boardState[a] === boardState[b] && boardState[a] === boardState[c]) {
      statusDisplay.textContent = `Player ${boardState[a]} Wins!`;
      endGame();
      return;
    }
  }

  if (!boardState.includes(null)) {
    statusDisplay.textContent = "It's a Draw!";
    endGame();
  }
};

// End the game and show the Play Again button
const endGame = () => {
  document.querySelectorAll('.cell').forEach(cell => cell.classList.add('disabled'));
  playAgainButton.style.display = 'block';
};

// Reset the game
playAgainButton.onclick = () => {
  boardState = Array(9).fill(null);
  currentPlayer = 'X';
  statusDisplay.textContent = `Player ${currentPlayer}'s Turn`;
  playAgainButton.style.display = 'none';
  renderBoard();
};

// Initial render
renderBoard();
