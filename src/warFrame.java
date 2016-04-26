import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class warFrame extends JFrame implements ActionListener
{
	
            int fwidth = 1600;
            int fheight = 800;
            WarPanel battlefield = new WarPanel(); 
             BasePanel battlefield1 = new BasePanel(); 
            infoPanel info = new infoPanel();
            OuterSpacePanel space = new OuterSpacePanel();
    
        public warFrame (){
            
            
		super ("Space Colony War");
		colonyWar();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize (fwidth, fheight);
		setVisible(true);
                this.requestFocus();
                
	}
        
        
        public void warScreen(){
            getContentPane().removeAll();
             battlefield = new WarPanel();
            this.getContentPane().add(battlefield,"Center");
            setSize (fwidth, fheight);
            setVisible(true);
            battlefield.requestFocus();
            repaint();
        }
        
               public void colonyWar(){
            getContentPane().removeAll();
             battlefield1 = new BasePanel();
            this.getContentPane().add(battlefield1,"Center");
            setSize (fwidth, fheight);
            setVisible(true);
            battlefield1.requestFocus();
            repaint();
        }
 
            public void outerSpace(){
            getContentPane().removeAll();
            space = new OuterSpacePanel();
            this.getContentPane().add(space,"Center");
            setSize (fwidth, fheight);
            setVisible(true);
            space.requestFocus();
            repaint();
        }
               
        public void infoScreen(){
            this.getContentPane().removeAll();
            this.getContentPane().add(info,"Center");
            setSize (fwidth, fheight);
            setVisible(true);
            info.labTenButton.addActionListener(this);
            info.warButton.addActionListener(this);
            info.requestFocus();
            repaint();
        }
        @Override
        public void actionPerformed(ActionEvent e) {
           Object o = e.getSource();
       
                if (o == info.labTenButton){
                    warScreen();
                }
                if (o == info.warButton){
                    warScreen();
                }
        }
        
        
        
        

}
