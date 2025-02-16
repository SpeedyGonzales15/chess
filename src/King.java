public class King extends ChessPiece {

    public King(String color) {
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

        // Проверка, что двигаемся на одну клетку в любом направлении
        int lineDiff = Math.abs(toLine - line);
        int columnDiff = Math.abs(toColumn - column);

        if (lineDiff > 1 || columnDiff > 1) {
            return false; // Слишком далеко
        }

        // Проверка, не занята ли целевая клетка фигурой того же цвета
        ChessPiece targetPiece = chessBoard.getPiece(toLine, toColumn);
        if (targetPiece != null && targetPiece.getColor().equals(this.getColor())) {
            return false; // Ход невозможен
        }

        // Проверка, не находится ли клетка под атакой (шах)
        if (isUnderAttack(chessBoard, toLine, toColumn)) {
            return false; // Нельзя ходить под шах
        }

        return true; // Ход возможен
    }

    @Override
    public String getSymbol() {
        return "K";
    }

    public boolean isUnderAttack(ChessBoard board, int line, int column) {
        // Проверяем, находится ли клетка (line, column) под атакой какой-либо фигуры противника

        String opponentColor = this.getColor().equals("White") ? "Black" : "White";

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessPiece piece = board.getPiece(i, j);
                if (piece != null && !piece.getColor().equals(this.getColor())) {
                    if (piece.canMoveToPosition(board, i, j, line, column)) {
                        return true; // Клетка под атакой
                    }
                }
            }
        }
        return false; // Клетка не под атакой
    }
}
