public class Rook extends ChessPiece {

    public Rook(String color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public boolean isValidMove(int newX, int newY, ChessBoard board) {
        if (newX == x || newY == y) {
            return !board.isPathBlocked(x, y, newX, newY);
        }
        return false;
    }
}