
import java.awt.Color;
import java.awt.Rectangle;
import javax.swing.JButton;
import java.util.Random;
import javax.swing.ImageIcon;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mark
 */
public class Soldier extends JButton{
    
    
        double side = 10.0;
        ImageIcon imageNormal = new ImageIcon("images/basic.png");
        ImageIcon imageUp = new ImageIcon("images/frownUp.png");
        ImageIcon imageDown = new ImageIcon("images/frownDown.png");
        ImageIcon imageLeft = new ImageIcon("images/frownLeft.png");
        ImageIcon imageRight = new ImageIcon("images/frownRight.png");
        ImageIcon imageFreakedOut = new ImageIcon("images/freakedUp.png");
        ImageIcon asleep = new ImageIcon("images/sleeping.png");
        ImageIcon yayFood = new ImageIcon("images/yayFood.png");
        Army myArmy;
        
        int strength;
        int health;
        int MAX_HEALTH;
        int fightSkill;
        int xHome;
        int yHome;
        
        //fButton opponent;
        Soldier target;
        JButton healthMeter;// = new JButton();
        
        public Soldier(double s, Army a){
            
                super();
                side = s;
                myArmy = a;
                strength = (int) (Math.random() * 10) + 11;
                health = (int) (Math.random() * 40) + 60;
                MAX_HEALTH = health;
                fightSkill = (int) (Math.random() * 80) + 20;
                healthMeter = new JButton();
                xHome =   myArmy.loX;//(int)(Math.random() * (myArmy.hiX - myArmy.loX)) + myArmy.loX;
                yHome =  myArmy.loY;//(int)(Math.random() * (myArmy.hiY - myArmy.loY)) + myArmy.loY;
                //healthMeter.setBounds(this.getX(), this.getY(), 2, 2);
               // healthMeter.setBackground(Color.green);
                //add(healthMeter);
                
                //setText(strength + "/" + health);
                
                
                
        }
        
        
 
              
        
        
        
 
        

      
       public void backOff(double distance){
       
       double xMove = 0;
       double yMove = 0;
       double x = target.getX();
       double y = target.getY();
       
       
       
       if(this.getX() > x){
               xMove = this.getX() + distance; 
           }else{
               xMove = this.getX() - distance; 
           }
           if(this.getY() > y){
               yMove = this.getY() + distance;  //this.setIcon(imageDown);
           }else {
               yMove = this.getY() - distance;  //this.setIcon(imageUp);
           }
           
            this.setLocation((int)xMove, (int)yMove);
            
            
            
       
   }   
      
       public void moveIn(double distance){
       
       double xMove = this.getX();
       double yMove = this.getY();
       double x = target.getX();
       double y = target.getY();
       
       if(Math.abs(this.getX() - x) < 10.0 && Math.abs(this.getY() - y) < 10.0){
         fight();
       }
       
       if(Math.abs(this.getX() - x) > Math.abs(this.getY() - y)){
       
                    if(this.getX() < x){
                       xMove = this.getX() + distance; 
                   }else{
                       xMove = this.getX() - distance; 
                   }
       }else{

                   if(this.getY() < y){
                       yMove = this.getY() + distance;  //this.setIcon(imageDown);
                   }else {
                       yMove = this.getY() - distance;  //this.setIcon(imageUp);
                   }
       }   
            this.setLocation((int)xMove, (int)yMove);
            
      
   }  
       
       
         public void move(double distance){   
             
             double rand =  (Math.random() * 8) + 1;
                   if(Math.abs(this.getX() - target.getX()) < 2.0 + rand && Math.abs(this.getY() - target.getY()) < 2.0 + rand){
                    backOff(distance);
                    } else{
                       moveIn(distance);
                   }
             
             
             
             
             
         }  
       
   public void fight(){   
       
       
       int attack =  (int)(Math.random() * fightSkill) + 1;
       int opAttack =  (int)(Math.random() * target.fightSkill) + 1;
       int chance =  (int)(Math.random() * 100) + 1;
       int contact =  (int)(Math.random() * 100) + 1;
       
        if(contact < 10){
            if(attack > opAttack){
                if(chance>(101-fightSkill)){
                target.health = target.health -  ((int)(Math.random() * strength) + 1);
                //opponent.setText(opponent.strength + "/" + opponent.health);
            }
            }else{
                if(chance>(101-fightSkill)){
                health = health -  ((int)(Math.random() * target.strength) + 1); 
                //setText(strength + "/" + health);
                }
            }
       
        }
       
       setBackgroundColor();
       target.setBackgroundColor();
        
        
        
        
        
   }  
       
  public void setBackgroundColor(){
      
      double relativeHealth = (double)health / (double)MAX_HEALTH;
      
      if(relativeHealth < .98 && relativeHealth > .9){
         this.healthMeter.setBackground(Color.blue); 
      }else if(relativeHealth > .60){
          this.healthMeter.setBackground(Color.yellow);
      }else if(relativeHealth > .35){
          this.healthMeter.setBackground(Color.orange);
      }else if(relativeHealth > .01){
         this.healthMeter.setBackground(Color.magenta); 
      }else if(relativeHealth > .001){
         this.healthMeter.setBackground(Color.red); 
      }
      
  }   
  
  
  
  
  
  public void patrol(double distance){
      
       
       double xMove = this.getX();
       double yMove = this.getY();
       double distanceV = distance;
       
       
       
       if(Math.abs(this.getX() - xHome) < 2.0 && Math.abs(this.getY() - yHome) < 2){
       // toDestination = true;
        
        xHome =  (int)((Math.random() * (myArmy.hiX - myArmy.loX)) + myArmy.loX);
        yHome =  (int)((Math.random() * (myArmy.hiY - myArmy.loY))  + myArmy.loY);
        
       }
       
                if(Math.abs(this.getX() - xHome) > Math.abs(this.getY() - yHome)){

                             if(this.getX() < xHome){
                                xMove = this.getX() + distanceV; 
                            }else{
                                xMove = this.getX() - distanceV; 
                            }
                }else{

                            if(this.getY() < yHome){
                                yMove = this.getY() + distanceV;  //this.setIcon(imageDown);
                            }else {
                                yMove = this.getY() - distanceV;  //this.setIcon(imageUp);
                            }
                }   
                
                
            this.setLocation((int)xMove, (int)yMove);  
      
       
      
  }
  
  
  
  
  
  
  
 public void getNearestEnemy(Army g){
     
     
     
     double bestDistance = 10000.0;
     
     
     for(int i = 0; i < g.platoon.size(); i++){
       
       double x1 = this.getX();
       double x2 = g.platoon.get(i).getX();
       double y1 = this.getY();
       double y2 = g.platoon.get(i).getY();
       
       double first = (x2 - x1) * (x2 - x1);
       double second = (y2 - y1) * (y2 - y1);  
         
       double distance = Math.sqrt(first + second);  
         
        if(distance < bestDistance){
         bestDistance = distance;
         target = g.platoon.get(i);
        } 
         
         
     }

     
 } 
 
 public void setHealthMeter(int x, int y){
     
     this.healthMeter.setBounds(x + 20, y + 20, 20, 20);    
     this.healthMeter.repaint();
     
 }
 
  
}  // end
