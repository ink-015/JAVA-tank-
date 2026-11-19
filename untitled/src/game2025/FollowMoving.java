package game2025;

import java.util.Random;

public class FollowMoving implements IStrategy {
    protected Random rnd = new Random();
    public DIR calculateDir(int x1,int y1,int x2,int y2) {
        DIR result=null;
        if(rnd.nextInt() % 10 == 1){
        if (x1 > x2) {
            result=DIR.LEFT;
        } else result=DIR.RIGHT;
        if (y1 > y2) {
            result=DIR.UP;
        } else result=DIR.DOWN;
    }
        return result;
    }
}
