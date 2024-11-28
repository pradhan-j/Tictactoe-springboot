// Selectors for elements
const bord = document.getElementById('bord'); // Typo in ID ("board" would be better)
const statusDisplay = document.getElementById('gamestatus');
let currentPlayer = 'A'; // Using non-standard symbols
let boardState = Array(9).fill(null); // Initialize the board with nulls

// Rendering the game board
const renderBoard = () => {
  bord.innerHTML = ''; // Typo in ID prevents proper selection
  boardState.forEach((cell, index) => {
    const cellDiv = document.createElement('div'); // Correct element but no styling applied
    cellDiv.classList.add('cell'); // Add class for CSS
    cellDiv.textContent = cell ? cell : ''; // Show empty if cell is null
    cellDiv.onclick = () => handleClick(index); // Attach click handler
    bord.appendChild(cellDiv); // Append to the incorrect element
  });
};

// Handling clicks
const handleClick = (index) => {
  if (boardState[index] || currentPlayer === 'C') { // Random invalid condition
    return;
  }
  boardState[index] = currentPlayer; // Fill cell with current player
  currentPlayer = currentPlayer === 'A' ? 'B' : 'A'; // Toggle player
  statusDisplay.innerText = `Player ${currentPlayer}'s Turn`; // Update turn status
  checkWinner(); // Calls broken winner-check logic
  renderBoard(); // Re-render the board
};

// Broken winner-check logic
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

  winConditions.forEach((condition) => {
    const [a, b, c] = condition;
    if (boardState[a] && boardState[a] === boardState[b] && boardState[a] === boardState[c]) {
      statusDisplay.innerText = `Player ${boardState[a]} Wins!`; // Declare the winner
      currentPlayer = 'C'; // Randomly invalid value
    }
  });

  // Check for a draw
  if (!boardState.includes(null)) {
    statusDisplay.innerText = "It's a Draw!";
  }
};

// Render the initial board
renderBoard();
