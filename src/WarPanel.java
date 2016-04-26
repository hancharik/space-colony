import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class WarPanel extends JPanel implements ActionListener, MouseMotionListener, ChangeListener
{
    
    int timerCount = 0;
    int scoreCount = 0;
    int armySize = (int)((Math.random() * 900) + 100); 
    int casualties = 0;
    //int schoolSize = 5;
    double side = 10.0;
    //ArrayList<tButton> school = new ArrayList<tButton>();
    JButton level = new JButton("Level");
    JButton timer = new JButton(""+timerCount);
    JButton score = new JButton("score: "+scoreCount);
    JButton startButton = new JButton("start");
    JButton stop = new JButton("sleep");
    JButton newFightButton = new JButton("New");
    JButton foodButton = new JButton("feed fish");
    JButton food = new JButton("food");
    JLabel redTotalLabel = new JLabel("<html><h1>Red Total: "+armySize+"</h1></html>");
    JLabel blueTotalLabel = new JLabel("<html><h1>Blue Total: "+armySize+"</h1></html>");
    Army reds = new Army("Reds", armySize, food);
    Army blues = new Army("Blues", armySize, food);
    
    Soldier victor;
    
    Timer t = new Timer(100,this);
    JSlider slider = new JSlider();
    double mouseX = 0.0;
    double mouseY = 0.0;
    double distance = 1.0;
    int speed = 20;
    //int width = 1280;
    //int height = 960;
    boolean sleeping = true;
    boolean thereIsFood = false;
    boolean gameOver = false;
    boolean thereIsWar = false;
    double foodX;
    double foodY;
    
    ImageIcon background = new ImageIcon("images/scbg.jpg");//= createBackground();
    Image backgroundImage = background.getImage();
    
    
    
    
         public WarPanel(){
             
		super();
		setBackground(Color.gray);
		setLayout(null);
                t = new Timer(speed,this);
                timer.setBounds(new Rectangle(460, 10, 100, 50));
               newFightButton.setBounds(new Rectangle(360, 10, 100, 50));
               startButton.setBounds(new Rectangle(750, 700, 100, 50));
               redTotalLabel.setBounds(550, 0, 180, 26);
               blueTotalLabel.setBounds(750, 0, 180, 26);
             
       ////////////////////////////////////////////////////////////////////////////////
               reds.setBoundaries(200, 200, 600, 600);
               blues.setBoundaries(1000, 200, 1400, 600);
      ////////////////////////////////////////////////////////////////////////////////         
               createBackground();
               
                addSoldiers();
           
                
                
                
                
                t.addActionListener(this);
                timer.addActionListener(this);
                newFightButton.addActionListener(this);
                 startButton.addActionListener(this);
             
                // add(newFightButton);
                 add(startButton);
                 add(redTotalLabel);
                 add(blueTotalLabel);
                 
                 t.start();
	}

         
         
         
         
         
         
         
    @Override
    public void actionPerformed(ActionEvent e) {
            Object o = e.getSource();
  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////     
                if (o == t){

                    
                 checkForWar();

                 if(!thereIsWar){
                   zonePatrol();  
                 }else{
                     
                     updateTotalLabels();
                     fightersMove();
                     checkCasualties();

                         if(reds.platoon.size() == 0 || blues.platoon.size() == 0){
                             startButton.setText("New");
                             startButton.setBackground(Color.lightGray);
                             gameOver = true;
                         }

                 }
       
       

        
       }
       
   //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
  
       if (o == startButton){
           
                    if(!gameOver){
                            if(!thereIsWar){
                             thereIsWar = true;
                             startButton.setText("WAR!");
                            }else{
                             thereIsWar = false;
                            startButton.setText("stop war");   
                            }
                    }else{
                        app.screen.warScreen();   
                    }
       //timer.setBackground(Color.green);
       }
   
 //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////            
              
       if (o == newFightButton){
            app.screen.warScreen();
       }
       
       if (o == timer){
       
       
       }
       
        if (o == reds){
            t.start();
       }
       
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        //mouseX = 0.0;
        //mouseY = 0.0;
    }

    @Override
    public void mouseMoved(MouseEvent evt)
    {
    	Point pt = evt.getPoint();
        mouseX = pt.getX();
        mouseY = pt.getY();
    	
      
    }
    
    


    @Override
    public void stateChanged(ChangeEvent e) {
       	JSlider obj = (JSlider)e.getSource();
	   int count = obj.getValue();
	    if(obj == slider)
	    {
                t.stop();
                timer.setBackground(Color.pink);
                
	    	t.setDelay(count);
                convertCount(count);
                level.setVisible(true);
	    }
    }
    
    public void convertCount(int num){
        String result = "easy";
        if(num < 80){
            result = "fun";
        }
        if(num < 65){
            result = "difficult";
        }
        if(num < 50){
            result = "frustrating";
        }
        if(num < 20){
            result = "impossible";
        }
        level.setText(result);
    }
    
    
   public void paintComponent(Graphics g) 
	{
    	super.paintComponent(g); 
    	g.drawImage(backgroundImage, 0, 0, this);
     }
    
    public void fightersMove(){   
        
        
        for(int i = 0; i < reds.platoon.size(); i++){
        
            reds.platoon.get(i).getNearestEnemy(blues);  // this needs to be here, or they will attack dead opponents
            
            if(reds.platoon.get(i).health>0){
                
                if(blues.platoon.size()==0){
                    reds.platoon.get(i).patrol(distance);
                }else{
                    reds.platoon.get(i).move(distance);
                }
                 
            }else{
                 reds.platoon.get(i).setBackground(Color.black);
                reds.platoon.remove(reds.platoon.get(i));
                casualties++;
            }
        
        
        }
        
        
         for(int i = 0; i < blues.platoon.size(); i++){
        
             blues.platoon.get(i).getNearestEnemy(reds);
             
            if(blues.platoon.get(i).health>0){
                if(reds.platoon.size()==0){
                    blues.platoon.get(i).patrol(distance);
                }else{
                    blues.platoon.get(i).move(distance);
                }
            }else{
                blues.platoon.get(i).setBackground(Color.black);
               blues.platoon.remove(blues.platoon.get(i));
               casualties++;
            }
     
        }
    
    }
    
    
    
    
    public void addSoldiers(){
        
        
                    int height = (int)side;//mongols.platoon.get(i).fightSkill;
                    int width = (int)side;//mongols.platoon.get(i).fightSkill;;
                 
                for(int i = 0; i < armySize; i++){
                
                    
                   
        
                    int redX = 0;// (int)(Math.random() * (reds.hiX - reds.loX)) + reds.loX;
                    int redY = 0;// (int)(Math.random() * (reds.hiY - reds.loY)) + reds.loY;
                        //int ringX =  (int)(Math.random() * 600) + 100;
                       // int ringY =  (int)(Math.random() * 300) + 1;
                        reds.platoon.get(i).setBounds(new Rectangle(redX, redY, height, width));
                        reds.platoon.get(i).setHealthMeter(redX, redY);
                        reds.platoon.get(i).getNearestEnemy(blues);
                        int blueX = 0;// (int)(Math.random() * (blues.hiX - blues.loX)) + blues.loX;
                        int blueY = 0;// (int)(Math.random() * (blues.hiY - blues.loY))  + blues.loY;
                        //ringX =  (int)(Math.random() * 600) + 100;
                       // ringY =  (int)(Math.random() * 200) + 400;
                        blues.platoon.get(i).setBounds(new Rectangle(blueX, blueY, height, width));
                        blues.platoon.get(i).setHealthMeter(blueX, blueY);
                        blues.platoon.get(i).getNearestEnemy(reds);
                        reds.platoon.get(i).addActionListener(this);
                        blues.platoon.get(i).addActionListener(this);
                        reds.platoon.get(i).setBackground(Color.red);
                        blues.platoon.get(i).setBackground(Color.blue);
                        
                        add(reds.platoon.get(i));
                        add(blues.platoon.get(i));
        
                }
                
                for(int i = 0; i < armySize; i++){
                
                        
                        reds.platoon.get(i).getNearestEnemy(blues);
                        
                        blues.platoon.get(i).getNearestEnemy(reds);
                        
        
                }
}

    
 public ImageIcon createBackground(){
     
     ImageIcon backgrnd = new ImageIcon("images/bggangfight2.png");
     
     int d4 = (int)((Math.random() * 4) + 1);
    
            switch(d4){
                case 1: backgrnd = new ImageIcon("images/battlefieldMap1.png"); break;
                case 2: backgrnd = new ImageIcon("images/battlefieldMap2.png"); break;
                case 3: backgrnd = new ImageIcon("images/battlefieldMap3.png"); break;
                case 4: backgrnd = new ImageIcon("images/battlefieldMap3.png"); break;
            }
    
    
    
    //backgroundImage = background.getImage();  
     
     
    return backgrnd; 
     
 }   
    
    
  public void zonePatrol(){
      
      
       for(int i = 0; i < reds.platoon.size(); i++){
                reds.platoon.get(i).patrol(distance);
       }
      
       for(int i = 0; i < blues.platoon.size(); i++){
                blues.platoon.get(i).patrol(distance);
       }
      
      
      
      
  }  
    
public void checkForWar(){
    
    if (reds.hiX > blues.loX){
        if(!thereIsWar){startButton.setBackground(Color.yellow);startButton.setText("danger");}
     int warChance = (int)((Math.random() * 2000) + 1);
    if(warChance < 5){
         thereIsWar = true;
         startButton.setText("WAR!");
         startButton.setBackground(Color.red);
     }
    }
    timerCount = timerCount + 1;
    
    if(timerCount%7==0 ){
    
    reds.hiX++;
    blues.loX--;
    
    }
    
}    
    
 public void checkCasualties(){
    
     //int average = (reds.platoon.size() + reds.platoon.size()) / 2;
     int peaceChance = (int)((Math.random() * casualties) + 1);
     int stupidity  =  380;//(int)((Math.random() * average) + 1);
     
     if(peaceChance > stupidity){
       thereIsWar = false;
       startButton.setText("Peace");
       startButton.setBackground(Color.lightGray);
       casualties = 0;
       reds.hiX = reds.hiX - 400;
       blues.loX = blues.loX + 400;
     }
     
    
}   
    
public void updateTotalLabels(){
    
    redTotalLabel.setText("<html><h1>Red Total: "+reds.platoon.size()+"</h1></html>");
    blueTotalLabel.setText("<html><h1>Blue Total: "+blues.platoon.size()+"</h1></html>");
    
}    
    
    
    
}  //end