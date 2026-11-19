package third;

public class ClassB {
    private int x;
    private int y;//x,y不能被查看
    public ClassB(int x,int y){
        this.x = x;
        this.y = y;
    }
    public int plus(){
        return x*y;
    }
}
