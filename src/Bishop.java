public class Bishop extends ChessPiece {

    public Bishop(String color) {
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

        // Проверка, что движение происходит по диагонали
        int lineDiff = Math.abs(toLine - line);
        int columnDiff = Math.abs(toColumn - column);

        if (lineDiff != columnDiff) {
            return false; // Должны быть равны при диагональном движении
        }

        // Проверка препятствий на пути
        int lineDirection = (toLine > line) ? 1 : -1;
        int columnDirection = (toColumn > column) ? 1 : -1;

        int currentLine = line + lineDirection;
        int currentColumn = column + columnDirection;

        while (currentLine != toLine) {
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
        return "B";
    }
}

