import java.util.Scanner;

public class ChessGame {

   public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       ChessBoard board = new ChessBoard();
       
       while(true){
           board.printBoard();
           System.out.println("\nВведите ход в формате: <фигура> <целевая_позиция> (например: e2 e4)");
           String input = scanner.nextLine();
           
           String[] parts = input.split("\\s+");
           if(parts.length !=2){
               System.out.println("Неверный ввод!");
               continue;
           }

           String fromPos = parts[0];
           String toPos = parts[1];

           // Получаем фигуру с позиции fromPos и целевые координаты toPos.
           char fromFile = fromPos.charAt(0);
           char fromRank = fromPos.charAt(1);

           char toFile = toPos.charAt(0);
           char toRank = toPos.charAt(1);

           int fromX = fromFile - 'a';
           int fromY = fromRank - '1';
           
           int toX = toFile - 'a';
           int toY = toRank - '1';

           String keyFrom = getKey(fromFile- 'a',fromRank- '1');
           
           if(!board.isOccupied(fromX ,fromY)){
               System.out.println("Нет фигуры на данной позиции!");
               continue;
           }

          ChessPiece piece=board.pieces.get(keyFrom);

          board.movePiece(piece,toX,toY);

       }
   }

   private static String getKey(int x,int y){
       return (char)(x + 'a') + "" + (y + 1);
   }
}