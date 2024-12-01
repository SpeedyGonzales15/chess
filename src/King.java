public class King extends ChessPiece {

    public King(String color, int x, int y) {
        super(color, x, y);
    }

    @Override
    public boolean isValidMove(int newX, int newY, ChessBoard board) {
        // Логика движения короля
        if (Math.abs(newX - x) <= 1 && Math.abs(newY - y) <= 1) {
            // Проверка на шах не реализована для простоты примера
            return true;
        }
        return false;
    }

    // Реализация рокировки может быть добавлена здесь.
}