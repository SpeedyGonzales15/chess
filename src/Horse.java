public class Horse extends ChessPiece {

    public Horse(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return super.getColor(); // Или просто `return color;`, если color был бы protected
    }

    @Override
    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        // Проверка на выход за пределы доски
        if (!chessBoard.checkPos(toLine) || !chessBoard.checkPos(toColumn)) {
            return false;
        }

        // Проверка, что не двигаемся на ту же клетку
        if (line == toLine && column == toColumn) {
            return false;
        }

        // Проверка хода буквой "Г"
        int lineDiff = Math.abs(toLine - line);
        int columnDiff = Math.abs(toColumn - column);

        if ((lineDiff == 2 && columnDiff == 1) || (lineDiff == 1 && columnDiff == 2)) {
            // Проверка, не занята ли целевая клетка фигурой того же цвета
            ChessPiece targetPiece = chessBoard.getPiece(toLine, toColumn);
            if (targetPiece == null || !targetPiece.getColor().equals(this.getColor())) {
                return true; // Ход возможен
            }
        }

        return false; // Ход невозможен
    }

    @Override
    public String getSymbol() {
        return "H";
    }
}
