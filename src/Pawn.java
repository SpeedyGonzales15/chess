public class Pawn extends ChessPiece {

    public Pawn(String color) {
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

        // Определение направления движения в зависимости от цвета
        int moveDirection = this.getColor().equals("White") ? 1 : -1;

        // Проверка движения на одну клетку вперед
        if (toColumn == column && toLine == line + moveDirection) {
            // Проверка, что клетка свободна
            if (chessBoard.getPiece(toLine, toColumn) == null) {
                return true;
            } else return false;
        }

        // Проверка движения на две клетки вперед (только для первого хода)
        if (toColumn == column && toLine == line + 2 * moveDirection) {
            // Проверка, что пешка находится на начальной позиции
            if ((this.getColor().equals("White") && line == 1) || (this.getColor().equals("Black") && line == 6)) {
                // Проверка, что обе клетки на пути свободны
                if (chessBoard.getPiece(line + moveDirection, column) == null && chessBoard.getPiece(toLine, toColumn) == null) {
                    return true;
                } else return false;
            } else return false;
        }

        // Проверка взятия по диагонали
        if (Math.abs(toColumn - column) == 1 && toLine == line + moveDirection) {
            // Проверяем, есть ли на клетке фигура противника
            ChessPiece targetPiece = chessBoard.getPiece(toLine, toColumn);
            if (targetPiece != null && !targetPiece.getColor().equals(this.getColor())) {
                return true; // Взятие по диагонали возможно
            }
        }

        // Взятие на проходе (пока не реализовано)

        return false; // Ход невозможен
    }

    @Override
    public String getSymbol() {
        return "P";
    }
}

