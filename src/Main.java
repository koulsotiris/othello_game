import java.util.ArrayList;

public class Main
{
    public static void main(String[] args) {
        //We create the players and the board
        //MaxDepth for the MiniMax algorithm is set to 2; feel free to change the values

        Player player = new Player();
        int maxDepth = player.desideDepth();
        player = new Player(maxDepth, 'X');

        Player AI = new Player(maxDepth, 'O');
        Board board = new Board();
        boolean nomoveplayer=false;boolean nomoveai=false;
        

        //Put this out of comments for the O to play first
        board.DesideFirstOrNot();
        while(board.isTerminal()==false && (nomoveai==false || nomoveplayer==false))
        {
            if (board.getLastPlayer()=='O')
            {
                //If O played last, then X plays now
                    System.out.println("Black plays");
                    System.out.println("Score is " + board.score()[0] +" - "+ board.score()[1] );
                    board.printBoard(board.getGameBoard());
                    System.out.println();
                    board.availablePairs(board.getGameBoard(),1);
                    System.out.println("Those are your available moves, please select one of them:");
                    System.out.println();
                    for(Pair pair : board.getPairlist()){
                        System.out.print("y= " + (pair.getX()+1));
                        System.out.print(" ");
                        System.out.print("x= "+(pair.getY()+1));
                        System.out.println();
            
                    }
                    System.out.println();
                    boolean input = false ;
                    if (!board.NoMoves(board.getPairlist())){
                        while(!input){
                            Pair coordinates = player.DesideMove();
                            if (board.checkMove(coordinates)){
                                board.makeMove(coordinates, board.getGameBoard(), 'X');
                                nomoveplayer=false;
                                board.updateBoard('X', coordinates,   board.getOGlist(), board.getPairlist(), board.getGameBoard());
                                input = true ;
                                board.printBoard(board.getGameBoard());
                            }
                            else{
                                System.out.println("You cannot play this move . Please try again");
                            }
                        }
                    }
                    else{
                        nomoveplayer=true;
                        board.setLastPlayer('X');
                        System.out.println("No available moves . White plays");}
                    }
                //If X played last, then O plays now
            else{
                    System.out.println();
                    board.availablePairs(board.getGameBoard(),0);
                    if (!board.NoMoves(board.getPairlist())){
                        System.out.print("White played");
                        AI.MoveMiniMax(board, board.getGameBoard() , player.getDepth(),true, 1000 , -1000);
                        board.makeMove(AI.AImove(), board.getGameBoard(), 'O');
                        board.updateBoard('O', AI.AImove(),   board.getOGlist(), board.getPairlist(), board.getGameBoard());
                        nomoveai=false;
                        System.out.print(" at "+(AI.AImove().getX()+1) + " " + (AI.AImove().getY()+1) );
                        System.out.println();
                        System.out.println();
                    }
                    else{
                        nomoveai=true;
                        board.setLastPlayer('O');
                        System.out.println("No available moves . Black plays");}
                    
                }
            }
            System.out.println();
            System.out.println("The game has ended!");
            System.out.println("Score is " + board.score()[0] +" - "+ board.score()[1] );
            if(board.score()[0]>board.score()[1]){
                System.out.println("The winner is: Player"   );
            }
            else if(board.score()[0]<board.score()[1]){
                System.out.println("The winner is: AI"   );
            }
            else{
                System.out.println("We have a draw"   );
            }
            
    }
}
