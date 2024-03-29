import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {

    // If board is solvable
    private boolean isSolvable;

    // Number of moves to solve initial board
    private int solutionMoves;

    // Last Search board's state that solve the puzzle
    private SearchBoard solutionBoard;

    private class SearchBoard implements Comparable<SearchBoard>
    {
        // Current board
        private Board board;

        // Previous board
        private SearchBoard previousBoard;

        private int moves;

        private int priority;

        public SearchBoard(Board board, int moves, SearchBoard previousBoard) {
            this.board = board;
            this.moves = moves;
            this.previousBoard = previousBoard;

            priority = board.manhattan() + this.moves;
        }

        public int compareTo(SearchBoard that) {
            if (this.priority > that.priority) {
                return 1;
            }

            if (this.priority < that.priority) {
                return -1;
            }

            return 0;
        }
    }

    // Find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial)
    {
        MinPQ<SearchBoard> searchBoardQueue = new MinPQ<>();
        MinPQ<SearchBoard> searchTwinBoardQueue = new MinPQ<>();

        searchBoardQueue.insert(new SearchBoard(initial, 0, null));
        searchTwinBoardQueue.insert(new SearchBoard(initial.twin(), 0, null));

        while (true) {
            SearchBoard minBoard = searchBoardQueue.delMin();
            SearchBoard minTwinBoard = searchTwinBoardQueue.delMin();

            if (minBoard.board.isGoal()) {
                solutionBoard = minBoard;
                isSolvable = true;
                solutionMoves = minBoard.moves;
                break;
            }

            if (minTwinBoard.board.isGoal()) {
                isSolvable = false;
                solutionMoves = -1;
                break;
            }

            enqueueNeighbors(minBoard, searchBoardQueue);
            enqueueNeighbors(minTwinBoard, searchTwinBoardQueue);
        }
    }

    // Is the initial board solvable?
    public boolean isSolvable()
    {
        return isSolvable;
    }

    // Min number of moves to solve initial board; -1 if unsolvable
    public int moves()
    {
        return solutionMoves;
    }

    // Sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution()
    {
        if (!isSolvable) {
            return null;
        }

        Stack<Board> solution = new Stack<>();

        SearchBoard board = solutionBoard;
        while (board != null) {
            solution.push(board.board);
            board = board.previousBoard;
        }

        return solution;
    }

    // Enqueue up to 4 neighbors for provided board
    private void enqueueNeighbors(SearchBoard searchBoard, MinPQ<SearchBoard> queue)
    {
        for (Board nextBoard: searchBoard.board.neighbors()) {
            if ((searchBoard.previousBoard == null) || (!nextBoard.equals(searchBoard.previousBoard.board))) {
                queue.insert(new SearchBoard(nextBoard, searchBoard.moves + 1, searchBoard));
            }
        }
    }

    // Solve a slider puzzle (given below)
    public static void main(String[] args)
    {

        int[][] blocks = new int[3][3];
        blocks[0][0] = 0;
        blocks[0][1] = 1;
        blocks[0][2] = 2;
        blocks[1][0] = 3;
        blocks[1][1] = 4;
        blocks[1][2] = 5;
        blocks[2][0] = 6;
        blocks[2][1] = 7;
        blocks[2][2] = 8;
        Board initial = new Board(blocks);

        Solver solver = new Solver(initial);

        if (!solver.isSolvable()) {
            StdOut.println("No solution possible");
        } else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution()) {
                StdOut.println(board);
            }
        }
    }
}