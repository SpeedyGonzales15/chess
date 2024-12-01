public class Bishop extends ChessPiece {

    public Bishop(String color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public boolean isValidMove(int newX, int newY, ChessBoard board) {
        if (Math.abs(newX - x) == Math.abs(newY - y)) {
            return !board.isPathBlocked(x, y, newX, newY);
        }
        return false;
    }
}