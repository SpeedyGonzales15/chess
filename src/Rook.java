public class Rook extends ChessPiece {

    public Rook(String color) {
        super(color);
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

        // Проверка, что движение происходит только по прямой (либо по вертикали, либо по горизонтали)
        if (line != toLine && column != toColumn) {
            return false; // Должна двигаться только по одной оси
        }

        // Проверка препятствий на пути
        // Определяем, по какой оси двигаемся и направление движения
        int lineDirection = 0;
        int columnDirection = 0;

        if (line != toLine) { // Движение по вертикали
            lineDirection = (toLine > line) ? 1 : -1;
        } else { // Движение по горизонтали
            columnDirection = (toColumn > column) ? 1 : -1;
        }

        int currentLine = line + lineDirection;
        int currentColumn = column + columnDirection;

        // Итерируемся по клеткам между начальной и конечной позицией
        while (currentLine != toLine || currentColumn != toColumn) {
            if (chessBoard.getPiece(currentLine, currentColumn) != null) {
                return false; // Есть препятствие на пути
            }
            currentLine += lineDirection;
            currentColumn += columnDirection;
        }

        // Проверка, не занята ли целевая клетка фигурой того же цвета
        ChessPiece targetPiece = chessBoard.getPiece(toLine, toColumn);
        if (targetPiece != null && targetPiece.getColor().equals(this.getColor())) {
            return false; // Ход невозможен
        }

        return true; // Ход возможен
    }

    @Override
    public String getSymbol() {
        return "R";
    }
}

