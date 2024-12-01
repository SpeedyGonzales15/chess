public class Pawn extends ChessPiece {

    public Pawn(String color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public boolean isValidMove(int newX, int newY, ChessBoard board) {
        if (color.equals("white")) {
            if (newY == y + 1 && newX == x && !board.isOccupied(newX, newY)) {
                return true;
            }
            if (y == 1 && newY == y + 2 && newX == x && !board.isOccupied(newX, newY)) {
                return true;
            }
            if (newY == y + 1 && Math.abs(newX - x) == 1 && board.isOccupied(newX, newY)) {
                return true; // Атака
            }
        } else {
            if (newY == y - 1 && newX == x && !board.isOccupied(newX, newY)) {
                return true;
            }
            if (y == 6 && newY == y - 2 && newX == x && !board.isOccupied(newX, newY)) {
                return true;
            }
            if (newY == y - 1 && Math.abs(newX - x) == 1 && board.isOccupied(newX, newY)) {
                return true; // Атака
            }
        }
        return false;
    }
}