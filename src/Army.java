
import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mark
 */
public class Army {
    
    
    ArrayList<Soldier> platoon = new ArrayList();
    String platoonName = "";
    int loX = 800;
    int hiX = 500;
    int loY = 400;
    int hiY = 600;        
    JButton base;
    Color armyColor;// = Color.blue;
    ImageIcon fighterIcon;
    
    
    
    public Army(String n, int number, JButton b){
        
        platoonName = n;
        base = b;
            for(int i = 0; i < number; i++){
               Soldier recruit = new Soldier(5.0, this);
               platoon.add(recruit);
            }
 
        
    }
    
    public void setBoundaries(int x1, int y1, int x2, int y2){
          
        loX = x1;
        hiX = x2;
        loY = y1;
        hiY = y2;   
        
        for(int i = 0; i < platoon.size(); i++){
           platoon.get(i).xHome =  base.getX() + (base.getWidth() / 2);
           platoon.get(i).yHome =  base.getY() + (base.getHeight() / 2);
        }
        
          
    }   
    
    
    
 public void generateTroops(){
     
     Soldier newRecruit = new Soldier(10, this);
     platoon.add(newRecruit);
     newRecruit.setBounds(new Rectangle(base.getX() + 70, base.getY() + 70, 10, 10));//(int)newRecruit.side));
     setArmyColor(platoonName);
     
     newRecruit.setIcon(fighterIcon);//.setBackground(armyColor);
    //app.screen.space.add(newRecruit);
     
 }   
    
public void setArmyColor(String name){
    
    
    
    
    int choice;
    if(name.matches("Reds")){
        choice = 1;
    }else{
        choice = 2;
    }
    
    switch(choice){
            case 1: fighterIcon = new ImageIcon("images/dFighter2.png"); break;
            case 2: fighterIcon = new ImageIcon("images/aFighter2.png");  break;
            //    case 1: armyColor = Color.red; break;
          //  case 2: armyColor = Color.blue;  break; 
    }
    
}    
    
    
    
}   //end
