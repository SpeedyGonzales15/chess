import java.util.HashMap;

public class ChessBoard {
    
    HashMap<String, ChessPiece> pieces;

    public ChessBoard() {
        pieces = new HashMap<>();
        setupInitialPosition();
    }

    private void setupInitialPosition() {
        // Инициализация фигур на доске
        pieces.put("a1", new Rook("white", 0, 0));
        pieces.put("b1", new Horse("white", 1, 0));
        pieces.put("c1", new Bishop("white", 2, 0));
        pieces.put("d1", new Queen("white", 3, 0));
        pieces.put("e1", new King("white", 4, 0));
        pieces.put("f1", new Bishop("white", 5, 0));
        pieces.put("g1", new Horse("white", 6, 0));
        pieces.put("h1", new Rook("white", 7, 0));
        
        for (int i = 0; i < 8; i++) {
            pieces.put((char)(i + 'a') + "2", new Pawn("white", i , 1));
            pieces.put((char)(i + 'a') + "7", new Pawn("black", i , 6));
        }

        pieces.put("a8", new Rook("black", 0 ,7));
        pieces.put("b8", new Horse("black", 1 ,7));
        pieces.put("c8", new Bishop("black", 2 ,7));
        pieces.put("d8", new Queen("black", 3 ,7));
        pieces.put("e8", new King("black", 4 ,7));
        pieces.put("f8", new Bishop("black", 5 ,7));
        pieces.put("g8", new Horse("black", 6 ,7));
        pieces.put("h8", new Rook("black", 7 ,7));
        
    }

   public boolean isOccupied(int x,int y){
       String key = getKey(x,y);
       return pieces.containsKey(key);
   }

   public boolean isPathBlocked(int startX,int startY,int endX,int endY){
       // Проверка блокировки пути между двумя точками.
       // Упрощенная логика для примера.
       if(startX==endX){ // Вертикаль
           for(int i=Math.min(startY,endY)+1;i<Math.max(startY,endY);i++){
               if(isOccupied(startX,i)){
                   return true;
               }
           }
       }else if(startY==endY){ // Горизонталь
           for(int i=Math.min(startX,endX)+1;i<Math.max(startX,endX);i++){
               if(isOccupied(i,startY)){
                   return true;
               }
           }
       }else{ // Диагональ
           int deltaX = endX > startX ? 1 : -1;
           int deltaY = endY > startY ? 1 : -1;
           for(int i=startX+deltaX,j=startY+deltaY; i!=endX&&j!=endY; i+=deltaX,j+=deltaY){
               if(isOccupied(i,j)){
                   return true;
               }
           }
       }
       return false;
   }

   private String getKey(int x,int y){
       return (char)(x + 'a') + "" + (y + 1);
   }

   public void movePiece(ChessPiece piece,int targetX,int targetY){
       String key = getKey(piece.getX(),piece.getY());
       if(piece.isValidMove(targetX,targetY,this)){
           pieces.remove(key); // Удаляем старую позицию
           piece.x = targetX; // Обновляем координаты фигуры
           piece.y = targetY; 
           pieces.put(getKey(targetX,targetY), piece); // Добавляем новую позицию
       } else{
           System.out.println("Неверный ход!");
       }
   }

   public void printBoard() {
       for (int i = 7; i >=0; i--) { 
           for (int j = 0; j < 8; j++) { 
               String key = getKey(j,i);
               if(pieces.containsKey(key)){
                   System.out.print(pieces.get(key).getClass().getSimpleName().charAt(0)+" ");
               }else{
                   System.out.print(". ");
               }  
           }
           System.out.println();
       }
   }
}