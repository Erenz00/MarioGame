
package Bricks;


public interface BasicBrick {
    
    public void HitFromDown();

    public void RemoveIt();

    public void setActive(boolean b);

    public int getID();

    public String getInsideItem();
    
    public boolean isJump();
    
}
