
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
public class tButtonff extends JButton{
    
    
        double side = 80.0;
        ImageIcon imageNormal = new ImageIcon("images/basic.png");
        ImageIcon imageUp = new ImageIcon("images/frownUp.png");
        ImageIcon imageDown = new ImageIcon("images/frownDown.png");
        ImageIcon imageLeft = new ImageIcon("images/frownLeft.png");
        ImageIcon imageRight = new ImageIcon("images/frownRight.png");
        ImageIcon imageFreakedOut = new ImageIcon("images/freakedUp.png");
        ImageIcon asleep = new ImageIcon("images/sleeping.png");
        
        
        
        public tButtonff(double s){
                super();
                side = s;
                this.setIcon(asleep);
        }
        
        
        public void move(double x, double y, double distance){
            
            double xMove = 0.0;
            double yMove = 0.0;
            boolean freakedOut;
            
            if(checkDistance( x,  y)){
                distance = distance * 3;
                freakedOut = true;
            }else{
                freakedOut = false;
            }
            
            
           if(this.getX()< x){
               xMove = this.getX() - distance;  this.setIcon(imageRight); if(freakedOut){this.setIcon(imageFreakedOut);}
           }else if(this.getX()> x){
               xMove = this.getX() + distance;  this.setIcon(imageLeft);  if(freakedOut){this.setIcon(imageFreakedOut);}
           }
           if(this.getY()< y){
               yMove = this.getY() - distance;  //this.setIcon(imageDown);
           }else if(this.getY()> y){
               yMove = this.getY() + distance;  //this.setIcon(imageUp);
           }
           /*
           //checkBounds(xMove,yMove);  oh! we had already been bypassing it!
            if (xMove > 1180 - side || xMove < 100 + side){ 
               xMove = this.getX();
            }
            if (yMove > 700 - side || yMove < 100 + side){ 
              yMove = this.getY(); 
            }
            */
           this.setBounds(new Rectangle((int) xMove, (int) yMove, (int) side, (int) side)); 
        }
                
        public void checkBounds(double x, double y){
            // turning this off turns off the walls  - not true! hmm...
            
          /*  
            double xMove = this.getX() + ( (Math.random() * 200) - 100);
            double yMove = this.getY() + ( (Math.random() * 200) - 100);
            
            if (x > 1280 - side || x < 100 + side || y > 700 - side || y < 100 + side){ 
               this.checkBounds(x,y); 
            } else{
                this.setBounds(new Rectangle((int) xMove, (int) yMove, (int) side, (int) side));
            }
            
           */ 
            
        }
        
        
        public boolean checkDistance(double x, double y){
            
            boolean tooClose = false;
            
           if( (Math.abs((this.getX()+(side/2))-x) < 100) && (Math.abs((this.getY()+(side/2))-y) < 100)){
               this.setBackground(Color.red);
               tooClose = true;
           }else{
               this.setBackground(Color.blue);
           }
          return tooClose;  
        }
        
        
      public void warp(){
         // int xMove = (int) (Math.random() * 1000) + 100;
         // int yMove = (int) (Math.random() * 700) + 100;
          this.setBounds(100000, 100000, (int) side, (int) side);  //throw the fish far far away, I was having ghost fish eating food
         //app.screen.game.remove(this);                               // i don't think i was removing the buttons correctly
        // app.screen.game.repaint();                                                                  
         
         // app.screen.game.net.catchAfish();
      }  
      
        public boolean tooClose(double x, double y){
       boolean close = false;
       
       if( (Math.abs((this.getX()+(side/2))-x) < 200) && (Math.abs((this.getY()+(side/2))-y) < 200)){
           close = true;
       }else {
           ImageIcon allClear = new ImageIcon("images/basic.png");
           this.setIcon(allClear);
          
       }
       
       return close;
   } 
      
   public void eat(double x, double y, double distance, WarPanel j){
       
       double xMove = 0.0;
       double yMove = 0.0;
       
       if(Math.abs(this.getX() - x) < 10.0 && Math.abs(this.getY() - y) < 10.0){
         j.food.setVisible(false);
         j.thereIsFood = false;
       }
       
       if(this.getX()< x){
               xMove = this.getX() + distance; 
           }else{
               xMove = this.getX() - distance; 
           }
           if(this.getY()< y){
               yMove = this.getY() + distance;  //this.setIcon(imageDown);
           }else {
               yMove = this.getY() - distance;  //this.setIcon(imageUp);
           }
           
            this.setLocation((int)xMove, (int)yMove);
            
            
            
       
   }   
      
       public void eatFF(JButton f){
       
       double xMove = 0.0;
       double yMove = 0.0;
      /* 
       if(Math.abs(this.getX() - f.getX()) < 10.0 && Math.abs(this.getY() - f.getY()) < 10.0){
         app.screen.game.foodff.setVisible(false);
         app.screen.game.thereIsFoodff = false;
         app.screen.game.fishEat++;
         app.screen.game.fishEatScore.setText("fish: "+app.screen.game.fishEat);
       }
       
       if(this.getX()< f.getX()){
               xMove = this.getX() + app.screen.game.distance; 
           }else{
               xMove = this.getX() - app.screen.game.distance; 
           }
           if(this.getY()< f.getY()){
               yMove = this.getY() + app.screen.game.distance;  //this.setIcon(imageDown);
           }else {
               yMove = this.getY() - app.screen.game.distance;  //this.setIcon(imageUp);
           }
           */
            this.setLocation((int)xMove, (int)yMove);
            
            
            
       
   }  
       
   public void spreadOut(double x, double y, double distance){
       
          double xMove = this.getX();
       double yMove = this.getY(); 
       
       if(Math.abs((this.getX()- x) )< 40){
       
            if(this.getX()< x){
               xMove = this.getX() - distance; 
           }else{
               xMove = this.getX() + distance; 
           }
       }
       if(Math.abs((this.getY()- y) )< 40){
           if(this.getY()< y){
               yMove = this.getY() - distance;  //this.setIcon(imageDown);
           }else {
               yMove = this.getY() + distance;  //this.setIcon(imageUp);
           }
       
       }
       
       this.setLocation((int)xMove, (int)yMove);
       
   }    
       
       
    
       
       
}
