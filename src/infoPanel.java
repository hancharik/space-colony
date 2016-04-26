
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author markhancharik
 */
public class infoPanel extends JPanel implements ActionListener, ChangeListener{
    
    
    int infoSpeed = 60;
    JLabel labTenLabel = new JLabel();
    JLabel gameLabel = new JLabel();
    JButton labTenButton = new JButton("Lab 10");
    JButton fightButton = new JButton("Ultra Fight Game");
    JButton warButton = new JButton("Biker Casino");
    boolean thereIsFoodip = false;
    JButton crab = new JButton();
    ImageIcon crabPic = new ImageIcon("images/crab.png");
    ImageIcon background = new ImageIcon("images/bg1.jpg");
    Image myImage1 = background.getImage();
    tButtonff fish = new tButtonff(80.0);
    Timer infoTimer = new Timer(infoSpeed, this);
    JSlider fishSpeed = new JSlider();
    JSlider fishAmount = new JSlider();
    int move = 1;
    int componentMoveDistance = 50;
    int ix = 900;
    int iy = 900;
    boolean moveEm = false;
    boolean isFood = false;
    double foodX;
    double foodY;
    tButtonff food = new tButtonff(40.0);
    int fishEat = 0;
    int crabEat = 0;
    int moveDistance = 3;
    
   
    
    
    
    
    
    
    
    public infoPanel(){
        super();
        createScreen();
    }
  
    
    
    
    public void createScreen(){
        setBackground(Color.gray);
	setLayout(null);
        // the use of the html tags below is from http://stackoverflow.com/questions/1090098/newline-in-jlabel  
        labTenLabel.setText("<html><h1>"
                + "Fish Eat = " +fishEat + "  The main class is the tbutton (the fish was originally named target).  </h1><br><h2>We are having difficulty when creating an Arraylist <br>"
                   + " of fish called school.  </h2>We have gone back to the working lab 10, added the food button. <br>"
                   + "The next step is to remove the wall limitations on the fish, as an <br> unintended benefit of the food is to call the lost fish back onto the screen<br>"
                + "</html>");
        gameLabel.setText("<html><h1>Crab Eat = " +crabEat + "The game is called Feeding Frenzy.</h1><br><h2>We have the food moving, but for gameplay, I think stationary food works better.<br>"
                   + " The moving food was actually developed as part of this infoPanel, to give the animated fish something to do.</h2><br>"
                   + "The next step is to remove the wall limitations on the fish, as an <br> unintended benefit of the food is to call the lost fish back onto the screen<br>"
                + "</html>");
        labTenLabel.setBounds(80, 0, 600, 300);
        gameLabel.setBounds(600, 100, 600, 300);
        fish.setBounds(800, 840, 80, 80);
        fish.setIcon(fish.imageNormal);
        fightButton.addActionListener(this);
        labTenButton.setBounds(200, 400, 200, 100);
        warButton.setBounds(600, 400, 200, 100);
        fightButton.setBounds(400, 600, 200, 100);
        createCrab();
        add(warButton);
        add(gameLabel);
        add(labTenLabel);
        add(labTenButton);
        add(fish);
        add(fightButton);
        infoTimer.start();  
        
    }
    
    
    
       public void paintComponent(Graphics g) 
	{
    	super.paintComponent(g); 
    	g.drawImage(myImage1, 0, 0, this);
     }

    @Override
    public void actionPerformed(ActionEvent e) {
       Object o = e.getSource();
       
       
       if(o == infoTimer){
           
           if(isFood){
               fish.setIcon(crabPic);
               moveFood();
              moveCrab();
               
           //fish.moveIn(5.0, food);
                  if( (Math.abs(fish.getX()-food.getX()) < 20) && (Math.abs(fish.getY()-food.getY()) < 20)){
                        this.remove(food);
                        isFood = false;
                        fishEat++;
                        
                  }
           }   
           
            if(moveEm){      
                  
           moveComponents(labTenLabel, gameLabel, labTenButton, warButton);
            }
           
           move++;
           if(!isFood){
               checkForFood();
               fish.setIcon(fish.imageNormal);
           }
           //gameLabel.setText("<html><h1>Crab Eat = " +crabEat + "</h1></html>");
           //labTenLabel.setText("<html><h1>Fish Eat = " +fishEat + "</h1></html>");
           
       }
       
       if(o == fightButton){
           if(!moveEm){
           moveEm = true;
           fightButton.setText("Stop Moving");
           }else{
            moveEm = false;
           fightButton.setText("Move Components");   
           }
       }
       
       
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider obj = (JSlider)e.getSource();
	   int count = obj.getValue();
	    if(obj == fishAmount){
                
                
               
                warButton.setBackground(Color.pink);
                
               
	    }
    }
    
    public void moveComponents(JLabel a, JLabel b, JButton c, JButton d){
        
        
            if(move % 40 == 0){
            if(move % 10 < 5){ 
            a.setLocation(a.getX()+ 5, a.getY() + 5); 
            } else{  
            a.setLocation(a.getX()- 5, a.getY() - 5);
            }}
            if(move % 30 == 1){
                if(move % 10 < 5){ 
           b.setLocation(b.getX(), b.getY() - 5);
            } else{  
            b.setLocation(b.getX(), b.getY() + 5);
            }}
           if(move % 50 == 1){
               if(move % 8 < 4){ 
          c.setLocation(c.getX()- 5, c.getY() + 5 );
            } else{  
            c.setLocation(c.getX()+ 5, c.getY() - 5 );
            } }
           if(move % 60== 0){
               if(move % 6 < 3){ 
          d.setLocation(d.getX()+ 5, d.getY() + 5);
            } else{  
            d.setLocation(d.getX()- 5, d.getY() - 5);
            }
           }
           
          
        }  
      
    public void createFood(){
        if(!isFood){
        isFood = true;
       int xPart = (int) (Math.random() * 1000) + 100;
       int yPart = 1;//
       //food.setLocation(xPart, yPart);
       food.setBounds(xPart, yPart, 40, 40);
       food.setVisible(true);
        this.add(food);
        this.repaint();
       
        }
    }  
    
   public void moveFood(){
       
      
       if(food.getY()>960){
         this.remove(food); 
         isFood = false;
       }else{ 
            food.setLocation(food.getX(), food.getY() + (moveDistance*2));
       }
   } 
    
   
   public void checkForFood(){
       int chance = (int) (Math.random() * 100) + 1;
       if(chance < 10){
           createFood();
       }
   }
   
          public void createCrab(){
           
                
                crab.setBounds(new Rectangle(10, 800, 80, 80));
                crab.setIcon(crabPic);
                add(crab);
           
       }
       
       
       public void moveCrab(){
           
           if(isFood){
              
               if( (Math.abs(crab.getX()-food.getX()) < 20) && (Math.abs(crab.getY()-food.getY()) < 20)){
                        this.remove(food);
                        isFood = false;
                        crabEat++;
           }
             
               
             if( Math.abs(crab.getX()-food.getX()) > 20){  
                  if(crab.getX() < food.getX()){ 
          crab.setLocation(crab.getX() + (moveDistance*5), crab.getY());
            } else{  
            crab.setLocation(crab.getX() - (moveDistance*5), crab.getY());
            } 
           } 
           }
       }
      
   
       
       
       
       
       
       
       
       
       
       
   } 
   

