
package Objects;

public interface BasicEnemy {
     
    public void update(long l);
    
    public void CollidedWithBrick_GoToLeft();
    
    public void CollidedWithBrick_GoToRight();
    
    public int getType();

    public void setActive(boolean b);

    public void MarioJumpedOnEnemy();

    public void KilledByFireBall();

    public void bounce();

    public void setYloc(double d);

    public void CollidedWithShell();
    
    public void CollidedWithMovingShell();

    public void OtherEnemyTouchedFromRight();

    public void OtherEnemyTouchedFromLeft();

    public void CollidedWithMarioFromTOLeft();

    public void CollidedWithMarioTORight();

    public void EnemyJumperOnMario();

    public int Life();
    
    public void CollidedWithJumping_Brick();
    
    public boolean MariotoRight();

}
