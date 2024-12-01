public abstract class ChessPiece {
    protected String color;
    protected int x, y;

    public ChessPiece(String color, int x, int y) {
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public String getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public abstract boolean isValidMove(int newX, int newY, ChessBoard board);
    
    public boolean attack(int targetX, int targetY, ChessBoard board) {
        return isValidMove(targetX, targetY, board);
    }
}