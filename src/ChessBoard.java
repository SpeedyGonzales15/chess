import java.util.HashMap;

public class ChessBoard {
    public ChessPiece[][] board = new ChessPiece[8][8]; // creating a field for game
    String nowPlayer;

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public String nowPlayerColor() {
        return this.nowPlayer;
    }

    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {
        boolean isValidPos = checkPos(startLine) && checkPos(startColumn) && checkPos(endLine) && checkPos(endColumn);

        if (!isValidPos) {
            return false;
        }

        ChessPiece piece = board[startLine][startColumn];

        if (piece == null || !nowPlayer.equals(piece.getColor())) {
            return false;
        }

        if (piece.canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {
            // Устанавливаем check в false для короля и ладьи после первого хода
            if (piece instanceof King || piece instanceof Rook) {
                piece.setCheck(false);
            }

            board[endLine][endColumn] = piece;
            board[startLine][startColumn] = null;
            nowPlayer = nowPlayer.equals("White") ? "Black" : "White";
            return true;
        }

        return false;
    }
    
    public boolean castling0() {
        // Рокировка с ладьей в 0-м столбце
        if (nowPlayer.equals("White")) {
            // Рокировка для белых
            return castling(0, 2);
        } else {
            // Рокировка для черных
            return castling(7, 2);
        }
    }

    public boolean castling7() {
        // Рокировка с ладьей в 7-м столбце
        if (nowPlayer.equals("White")) {
            // Рокировка для белых
            return castling(0, 6);
        } else {
            // Рокировка для черных
            return castling(7, 6);
        }
    }

    // Вспомогательный метод для реализации логики рокировки
    private boolean castling(int line, int toColumn) {
        int kingColumn = 4;
        int rookColumn;

        if (toColumn == 2) {
            rookColumn = 0; // Длинная рокировка
        } else if (toColumn == 6) {
            rookColumn = 7; // Короткая рокировка
        } else {
            return false; // Некорректный вызов метода
        }


        ChessPiece king = board[line][kingColumn];
        ChessPiece rook = board[line][rookColumn];

        // Проверяем, что король и ладья на месте и еще не двигались
        if (!(king instanceof King) || !(rook instanceof Rook) || !king.isCheck() || !rook.isCheck()) {
            return false;
        }

        // Проверяем, что между королем и ладьей нет фигур
        int direction = (toColumn > kingColumn) ? 1 : -1;
        int currentColumn = kingColumn + direction;
        while (currentColumn != rookColumn) {
            if (board[line][currentColumn] != null) {
                return false; // Есть препятствие
            }
            currentColumn += direction;
        }

        // Проверяем, что поля, через которые двигается король, не находятся под атакой
         if (isUnderAttack(line, kingColumn) || isUnderAttack(line, kingColumn + direction) || isUnderAttack(line, toColumn)) {
                return false; // Рокировка невозможна из-за шаха
         }


        // Выполняем рокировку
        board[line][kingColumn] = null;
        board[line][rookColumn] = null;
        board[line][toColumn] = king;
        board[line][kingColumn + direction] = rook;
        board[line][toColumn].setCheck(false); //убираем возможность рокировки в дальнейшем
        board[line][kingColumn + direction].setCheck(false);


        nowPlayer = nowPlayer.equals("White") ? "Black" : "White"; // Меняем игрока

        return true;
    }

    //вспомогательный метод для проверки, находится ли поле под атакой
    public boolean isUnderAttack(int line, int column) {
        String opponentColor = nowPlayer.equals("White") ? "Black" : "White";
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    ChessPiece piece = board[i][j];
                    if (piece != null && !piece.getColor().equals(nowPlayer)) {
                        if (piece.canMoveToPosition(this, i, j, line, column)) {
                            return true; // Клетка под атакой
                        }
                    }
                }
            }
            return false; // Клетка не под атакой
        }

    public ChessPiece getPiece(int line, int column) {
        if (checkPos(line) && checkPos(column)) {
            return board[line][column];
        }
        return null;
    }

    public void printBoard() {  //print board in console
        System.out.println("Turn " + nowPlayer);
        System.out.println();
        System.out.println("Player 2(Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");
        
        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].getSymbol() + board[i][j].getColor().substring(0, 1).toLowerCase() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Player 1(White)");
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }
}