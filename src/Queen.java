public class Queen extends ChessPiece {

    public Queen(String color) {
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

        // Ферзь ходит как ладья или слон
        boolean rookMove = (line == toLine || column == toColumn);
        boolean bishopMove = (Math.abs(toLine - line) == Math.abs(toColumn - column));

        if (!rookMove && !bishopMove) {
            return false; // Ход не является ни прямым, ни диагональным
        }

        // Проверка препятствий на пути (аналогично Rook и Bishop)
        int lineDirection = 0;
        int columnDirection = 0;

        if (line != toLine) {
            lineDirection = (toLine > line) ? 1 : -1;
        }
        if (column != toColumn) {
            columnDirection = (toColumn > column) ? 1 : -1;
        }

        int currentLine = line + lineDirection;
        int currentColumn = column + columnDirection;

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
        return "Q";
    }
}
