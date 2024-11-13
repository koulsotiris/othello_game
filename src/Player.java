import java.util.Scanner;
import java.util.ArrayList;

public class Player{
    private int maxDepth;
    private char PlayerColor;
    Pair BestMove;
    
    Player(){}

    Player(int maxDepth, char PlayerColor)
    {
        this.maxDepth = maxDepth;
        this.PlayerColor = PlayerColor;
    }
    
    char getColor(){
        return PlayerColor;
    }

    
    /////////////
    // PLAYER //
    ////////////

    public int desideDepth(){
        Scanner in = new Scanner(System.in);
        System.out.println("Type the Max depth of the MiniMax Algorithm between 0 to 10");
        System.out.print("Depth = ");
        int depth = in.nextInt();
        while(depth > 10 || depth < 1){
            System.out.println("Type valid number!");
            System.out.print("Depth = ");
            depth = in.nextInt();
        }
        return depth;
    }

    Pair DesideMove(){  //synarthsh pou kalei ton paixth na epile3ei thn epomenh kinhsh tou
        Scanner in = new Scanner(System.in);
        System.out.println("Type your move");
        System.out.print("y = ");
        int x = in.nextInt();
        while(x > 8 || x < 1){
            System.out.println("Type your y again!");
            System.out.print("y = ");
            x = in.nextInt();
        }
        
        System.out.print("x = ");
        int y = in.nextInt();
        while(y > 8 || y < 1){
            System.out.println("Type your x again!");
            System.out.print("x = ");
            x = in.nextInt();
        }
        return new Pair(x -1, y -1);
    }

    
    //////
    //AI//
    //////

    int MoveMiniMax(Board game, char[][] board , int depth,boolean maxi , int alpha , int beta){ 
        if (depth==0){
            return game.evaluate();
        }
        if (maxi){
            int best = Integer.MIN_VALUE;
            ArrayList<Pair> availablemoves = new ArrayList<Pair>(game.getPairlist());
            for(Pair moves: availablemoves)
            {
                char[][] mimicboard = mimic(board);
                game.makeMove(moves,mimicboard,'O');
                int value = MoveMiniMax(game,mimicboard,depth-1,!maxi,alpha,beta);
                
                best = Math.max(best, value);
                alpha = Math.max(alpha, best);
 
                // Alpha Beta Pruning
                if (beta <= alpha){
                    BestMove=moves;
                    break;}
            }
            return best;
        }
        else{   
            int best = Integer.MAX_VALUE;
            ArrayList<Pair> availablemoves = new ArrayList<Pair>(game.getPairlist());
            for(Pair moves: availablemoves)
            {
                char[][] mimicboard = mimic(board);
                game.makeMove(moves,mimicboard,'X');
                int value = MoveMiniMax(game,mimicboard,depth-1,!maxi,alpha,beta);
                
                best = Math.min(best, value);
                alpha = Math.min(alpha, best);
 
                // Alpha Beta Pruning
                if (beta <= alpha){
                    BestMove=moves;
                    break;}
            }
            return best;

        }    
    }
     
    
    Pair AImove(){  //H kinhsh tou AI
        return BestMove;
    }
    
    char[][] mimic(char[][] board){ //Apomimhsh tou board
            return board.clone();
        }

    int getDepth(){
        return maxDepth;
    }
}