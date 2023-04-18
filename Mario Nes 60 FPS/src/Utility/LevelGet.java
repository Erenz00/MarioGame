
package Utility;

import Gears.Construct;
import Levels.One.Level_11;

public class LevelGet {
    
    Level_11 lvl = new Level_11();
    
    public static void main(String[] args) {
        
        LevelGet L = new LevelGet();
        L.Begin();
        
    }

    private void Begin() {
        
        for (int i = 0; i < lvl.Get_Items_Amount(); i++) {
            
            Construct temp = lvl.get_Item_Number(i);
            
            // System.out.println("\t\tAdd(\""+temp.getItem_Type() +"\" , "+ temp.getX()+" , "+ temp.getY() +" , "+ temp.getLength_X()+" , "+ temp.getLength_Y()+" ) ;");
            // System.out.println();
            
        }
        
    }
    

}
