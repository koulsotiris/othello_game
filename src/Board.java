import java.util.ArrayList;
import java.util.Scanner;

public class Board{
    static char[][] board = new char[8][8]; // o pinakas tou paixnidiou
    ArrayList<Pair> Pairs = new ArrayList<Pair>(); // Lista me tis pi8anes kai apodektes kinhseis
    
    ArrayList<Pair> OGs = new ArrayList<Pair>(); //Lista me ta pionia pou pi8anws 8a "path8oun"
    
    /* Variable containing who played last; whose turn resulted in this board
     * Even a new Board has lastLetterPlayed value; it denotes which player will play first
     */
    private char lastPlayer;
     
    
    public Board() {    
        this.lastPlayer = 'O';
        
        // ftiaxnoume ton pinaka tou paixnidiou
        for(int xs = 0; xs < 8; xs++) {
            for(int ys = 0; ys < 8; ys++) {
                board[xs][ys] = ' ' ;
            }
        } 
        
        //ftiaxnoume to kentro tou pinaka 
        board[3][3] = 'O'; new Pair(3, 3, 'O'); 
        board[3][4] = 'X'; new Pair(3, 4, 'O');
        board[4][4] = 'O'; new Pair(4, 4, 'O');
        board[4][3] = 'X'; new Pair(4, 3, 'O');
    }
    
    // synarthsh gia thn epomenh kinhsh tou paixth 
    void makeMove(Pair pair , char[][] board , char lastPlayer){ 
        pair.giveColor(lastPlayer);
        board[pair.getX()][pair.getY()]=pair.getColor();
        this.lastPlayer=lastPlayer;
    }

    //synarthsh gia na tsekarw an o paikths exei dia8esimes kinhseis
    boolean NoMoves(ArrayList Pairs){
        if (Pairs.isEmpty()){
            return true;
        }
        else{return false;}
    }
    
    // synarthsh pou tsekarei an h kinhsh pou 8elei na kanei o paixths einai apodexth
    boolean checkMove(Pair pair){ 
        boolean f=false;
        for (Pair check  : Pairs){
            if (check.getX() == pair.getX() && check.getY()==pair.getY()){
                f=true;
            }
        }
        return f;
    }

    

    public void availablePairs( char[][] board, int p) { // epistrefei lista me ta diathesimes theseis gia na paiksei o paiktis  
        for(int xs = 0; xs < 8; xs++) {
            for(int ys = 0; ys < 8; ys++) {
                if (p==1){
                    if (board[xs][ys] == 'O'){
                        if(helpPair('X',xs+1, ys,true,false,true,  board)==true){
                            Pairs.add(new Pair(xs+1, ys));
                            OGs.add(new Pair(xs, ys));
                        }
                        if(helpPair('X',xs-1, ys,true,false,false,  board)==true){
                            Pairs.add(new Pair(xs-1, ys));
                            OGs.add(new Pair(xs, ys));
                        }
                        if(helpPair('X',xs, ys-1,false,true,false,  board)==true){
                            Pairs.add(new Pair(xs, ys-1));
                            OGs.add(new Pair(xs, ys));
                        }
                        if(helpPair('X',xs, ys+1,false,true,true,  board)==true){
                            Pairs.add(new Pair(xs, ys+1));
                            OGs.add(new Pair(xs, ys));
                        }
                        if(helpPairDiag('X', xs-1, ys-1,1,board)==true){
                            Pairs.add(new Pair(xs-1, ys-1));
                            OGs.add(new Pair(xs, ys));
                        }
                        if(helpPairDiag('X', xs+1, ys+1,3,board)==true){
                            Pairs.add(new Pair(xs+1, ys+1));
                            OGs.add(new Pair(xs, ys));
                        }
                        if(helpPairDiag('X',xs+1, ys-1,4,board)==true){
                            Pairs.add(new Pair(xs+1, ys-1));
                            OGs.add(new Pair(xs, ys));
                        }
                        if(helpPairDiag('X',xs-1, ys+1,2,board)==true){
                            Pairs.add(new Pair(xs-1, ys+1));
                            OGs.add(new Pair(xs, ys));
                        }              
                    }
                }else{
                    if (board[xs][ys] == 'X'){  
                        if(helpPair('O',xs+1, ys,true,false,true,  board)==true){
                            Pairs.add(new Pair(xs+1, ys));
                            OGs.add(new Pair(xs, ys));
                        }
                        if(helpPair('O',xs-1, ys,true,false,false,  board)==true){
                            Pairs.add(new Pair(xs-1, ys));
                            OGs.add(new Pair(xs, ys));
                        }
                        if(helpPair('O',xs, ys-1,false,true,false,  board)==true){
                            Pairs.add(new Pair(xs, ys-1));
                            OGs.add(new Pair(xs, ys));
                        }
                        if(helpPair('O',xs, ys+1,false,true,true,  board)==true){
                            Pairs.add(new Pair(xs, ys+1));
                            OGs.add(new Pair(xs, ys));
                        }
                        if(helpPairDiag('O', xs-1, ys-1,1,board)==true){
                            Pairs.add(new Pair(xs-1, ys-1));
                            OGs.add(new Pair(xs, ys));
                        }
                        if(helpPairDiag('O', xs+1, ys+1,3,board)==true){
                            Pairs.add(new Pair(xs+1, ys+1));
                            OGs.add(new Pair(xs, ys));
                        }
                        if(helpPairDiag('O',xs+1, ys-1,4,board)==true){
                            Pairs.add(new Pair(xs+1, ys-1));
                            OGs.add(new Pair(xs, ys));
                        }
                        if(helpPairDiag('O',xs-1, ys+1,2,board)==true){
                            Pairs.add(new Pair(xs-1, ys+1));
                            OGs.add(new Pair(xs, ys));
                        }              
                    }
                }   
            }   
        }
    }

    public boolean helpPair(char player, int coreX, int coreY, boolean coreVarX, boolean coreVarY, boolean coreDir, char[][] board){ 
        //elegxei an h thesi pou eksetazetai einai diathesimi ston paikti gia thn epomenh toy kinisi gia kateythinsi panw katw
        //coreVarX : an exoume metatopish x ; coreVarY : an exoume metatopisi y ; CoreDir : prosimo metatopisis
        boolean flag = false;

        if (coreX>=0 && coreX<8 && coreY>=0 && coreY<8){ //Elegxos gia out of bounds
            if (board[coreX][coreY] == ' '){
                if(coreDir == true){
                    for(int xs2 = 0; xs2 < 8 ; xs2++) { 
                        if(coreVarX == true){
                            coreX = coreX - 1; 
                            
                        }
                        else if(coreVarY == true){
                            coreY = coreY - 1;
                            
                        }
                        
                        if (coreX<0 || coreX>=8 || coreY<0 || coreY>=8){
                            break;}
                        
                        if (board[coreX][coreY] == player && flag == false){
                            flag = true;
                            return flag;
                            
                        }
                        else if(board[coreX][coreY] == ' ' && flag == false){
                            break;
                        }
                    }
                }else{
                    for(int xs3 = 8; xs3 > 0; xs3--) {
                        if(coreVarX == true){
                            coreX = coreX+1; 
                            
                        }else if(coreVarY == true){
                            coreY = coreY+1;
                            
                        }   

                        if (coreX<0 || coreX>=8 || coreY<0 || coreY>=8){
                            break;}
                                
                        if (board[coreX][coreY] == player && flag == false){
                            flag = true;
                            return flag;
                        }
                        else if(board[coreX][coreY] == ' ' && flag == false){
                            break;
                        }
                    }   
                }
            }
        }
        return flag;
    }


    public boolean helpPairDiag(char player,int coreX, int coreY, int dir, char[][] board){ 
        //elegxei an h thesi pou eksetazetai einai diathesimi ston paikti gia thn epomenh toy kinisi gia kateythinsi 4 diagwnies
        boolean flag = false;
        if (coreX>=0 && coreX<8 && coreY>=0 && coreY<8){
            if (board[coreX][coreY] == ' '){
            if(dir == 1){
                for(int xs2 = 0; xs2 < 8 ; xs2++) { 
                            coreX = coreX + 1; 
                            coreY = coreY + 1; 
                            
                        if (coreX<0 || coreX>=8 || coreY<0 || coreY>=8){
                            break;}

                        if (board[coreX][coreY] ==  player && flag == false){
                            flag = true;
                            break;
                            
                        }else if(board[coreX][coreY] == ' ' && flag == false){
                            break;
                        }
                    }
            }
            else if(dir == 2){
                for(int xs2 = 0; xs2 < 8 ; xs2++) { 
                    coreX = coreX + 1; 
                    coreY = coreY - 1;  
                    
                    if (coreX<0 || coreX>=8 || coreY<0 || coreY>=8){
                        break;}
                    
                    if (board[coreX][coreY] ==  player && flag == false){
                        flag = true;
                        break;   
                    }else if(board[coreX][coreY] == ' ' && flag == false){
                        break;
                    }
                }
            }else if(dir == 3){
                for(int xs2 = 0; xs2 < 8 ; xs2++) { 
                    coreX = coreX - 1; 
                    coreY = coreY - 1;    
                    
                    if (coreX<0 || coreX>=8 || coreY<0 || coreY>=8){
                        break;}

                    if (board[coreX][coreY] ==  player && flag == false){
                        flag = true;
                        break;
                    }else if(board[coreX][coreY] == ' ' && flag == false){
                        break;
                    }
                }
            }else if(dir == 4){
                    for(int xs2 = 0; xs2 < 8 ; xs2++) { 
                            coreX = coreX - 1; 
                            coreY = coreY + 1; 
                            
                        if (coreX<0 || coreX>=8 || coreY<0 || coreY>=8){
                            break;}

                        if (board[coreX][coreY] ==  player && flag == false){
                            flag = true;
                            break;
                            
                        }else if(board[coreX][coreY] == ' ' && flag == false){
                            break;
                        }
                    }
                }
            }
        }
        return flag;
    }
    
    // synartisi pou ananewnei ton pinaka afoy ginei mia kinhsh
    public void updateBoard(char p1, Pair coor, ArrayList<Pair> Oug, ArrayList<Pair> Purs, char[][] burd){ 
        //difx diafora x ; dify diafora y ; 

        for (Pair it : Purs){
            if(it.getX() == coor.getX() && it.getY() == coor.getY()){
                int pos = Purs.indexOf(it);
                Pair Og = Oug.get(pos);
                int Difx = (Og.getX() - coor.getX());
                if(Difx!=0){
                    Difx= Difx/Math.abs(Difx);
                }
                int Dify = (Og.getY() - coor.getY());
                if(Dify!=0){
                    Dify= Dify/Math.abs(Dify);
                }
                int Addx = Og.getX();
                int Addy = Og.getY();
                while(burd[Addx][Addy] != p1){
                    int Addx1 = Addx;
                    int Addy1 = Addy;
                    burd[Addx1][Addy1] = p1;
                    Addx = (Difx + Addx);
                    Addy = (Dify + Addy);
                    
                }
            }
        }
        Purs.clear();
        Oug.clear();
    }
    // synarthsh pou epistrefei to skor tou paixnidiou
    int[] score() { 
        int score_w=0;int score_b=0;
        for(int xs = 0; xs < 8; xs++) {
            for(int ys = 0; ys < 8; ys++) {
                if (board[xs][ys] == 'O'){
                    score_w+=1;
                }
                else if (board[xs][ys] == 'X'){
                    score_b+=1;
                }
            }
        }
        int[] score_total = {score_w,score_b};
        return score_total; 
    }

      static char[][] getGameBoard() // synarthsh pou epistrefei ton pinaka tou paixnidiou
     {
         return board;
     } 
     
     int getLastPlayer()
     {
         return this.lastPlayer;
     }

     void setLastPlayer(char player){
        this.lastPlayer=player;
     }


    public void printBoard(char[][]board) { // synarthsh pou ektypwnei ton pinaka tou paixnidiou
        System.out.println("     1     2     3     4     5     6     7     8");
        for (int xs = 0; xs < 8; xs++) {
            System.out.print(xs + 1);
            System.out.print(" |  ");
            for (int ys = 0; ys < 8; ys++) {
                System.out.print(board[xs][ys]);
                System.out.print("  |  ");
            }
            System.out.println();
            System.out.println("---------------------------------------------------");
        }
    }

    public ArrayList <Pair> getPairlist(){
        return Pairs;
    }

    public ArrayList <Pair> getOGlist(){
        return OGs;
    }

    //synarthsh ypologismou a3ias
    //O - X kai oi gwnies exoun bonous 
    int evaluate(){
        int w=0;int b=0;
        for(int xs = 0; xs < 8; xs++) {
            for(int ys = 0; ys < 8; ys++) {
                if (board[xs][ys] == 'O'){
                    w+=1;
                }
                else if (board[xs][ys] == 'X'){
                    b+=1;
                }
            }
        }
        int cornerbonus = 10;
        if (board[0][0]=='X') {
            b += cornerbonus;
        }
        if (board[0][7]=='X') {
            b += cornerbonus;
        }
        if (board[7][0]=='X') {
            b += cornerbonus;
        }
        if (board[7][7]=='X') {
            b += cornerbonus;
        }
        if (board[0][0]=='O') {
            w += cornerbonus;
        }
        if (board[0][7]=='O') {
            w += cornerbonus;
        }
        if (board[7][0]=='O') {
            w += cornerbonus;
        }
        if (board[7][7]=='O') {
            w += cornerbonus;
        }
        return w - b;
        }
    
    boolean isTerminal(){
        boolean flag=true;
        for(int xs = 0; xs < 8; xs++) {
            for(int ys = 0; ys < 8; ys++) {
                if (board[xs][ys] == ' '){
                    flag=false;
                }
            }
        }
        return flag;
        
    }

    void DesideFirstOrNot(){
        Scanner in = new Scanner(System.in);
        System.out.println("Which player will start:");
        System.out.println("Player  / Computer");
        
        String x = in.nextLine();

        while((!x.equals("Player")) && (!x.equals("Computer"))){
            System.out.println(x);
            System.out.println("Which player will start:");
            System.out.println("Player  / Computer");
            x = in.nextLine();
        }

        if(x.equals("Computer")){
            setLastPlayer('X');
        }
    }
}