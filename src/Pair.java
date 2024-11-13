
public class Pair {
    private int x;
    private int y;
    private char color;
    private int value;

    
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
        this.color = 'n';
        this.value = -1;
    }

    public Pair(int x, int y, char color) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.value = -1;
    }

    Pair(int value)
    {
        this.x = -1;
        this.y = -1;
        this.value = value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getColor(){
        return color;
    }

    public void giveColor(char color){
        this.color=color;
    }
}
