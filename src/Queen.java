public class Queen extends ChessPiece {

    public Queen(String color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public boolean isValidMove(int newX, int newY, ChessBoard board) {
        if (newX == x || newY == y || Math.abs(newX - x) == Math.abs(newY - y)) {
            return !board.isPathBlocked(x, y, newX, newY);
        }
        return false;
    }
}