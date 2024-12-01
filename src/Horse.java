public class Horse extends ChessPiece {

    public Horse(String color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public boolean isValidMove(int newX, int newY, ChessBoard board) {
        return (Math.abs(newX - x) == 2 && Math.abs(newY - y) == 1) ||
               (Math.abs(newX - x) == 1 && Math.abs(newY - y) == 2);
    }
}