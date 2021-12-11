package snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer; 
public class GameJpanel extends JPanel implements ActionListener {
    final int W =500;
    final int H = 500;
    final int U = 20;
    int body = 5;  
    int level = 4;
    int Ax;
    int Ay;
    int x[] = new int[(W*H)/(U*U)];
    int y[] = new int[(W*H)/(U*U)];
    char Key = 'R';
    Timer time ;
    Random randoms = new Random();
    boolean running = true; 
    GameKey e = new GameKey();
    public GameJpanel() {
        setFocusable(true);
        this.setBackground(Color.BLACK);
        this.setSize(W,H);
        this.addKeyListener(e);
        this.GameStart();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        draw(g);
    } 
    
    public void draw(Graphics g){
        if (running) {
            
            g.setColor(new Color(204,204,204));
            g.fillOval(Ax*U,Ay*U,U,U);
            for (int i = 1; i < W/U; i++) {
                g.drawLine(i*U,0,i*U,W);
                g.drawLine(0,i*U,H,i*U);
            }
            g.setColor(Color.red);
            g.fillOval(Ax*U,Ay*U,U,U);
            g.setColor(Color.gray);
            g.fillRect(x[0]*U,y[0]*U,U,U);
            for (int i = 1;i < body; i++) {
                g.setColor(Color.GREEN);
                g.fillRect(x[i]*U,y[i]*U,U,U);
             }    
            g.setColor(Color.red);
            g.setFont(new Font("Georgia",Font.ITALIC,35));      
            g.drawString("Score : " +(body*level-5*level) ,W/3,H/20);
            g.setColor(Color.GREEN);
            
            
        }else{
            Gameover(g);
        }
    } 
    
    public void GameStart(){
       newApple();
       time = new Timer(500-level*100,this);
       time.start();
       x[0] = 1;y[0] = 0;
       time.restart();
    }
    
    public  void Gameover(Graphics g){
        g.setColor(Color.red);
        g.setFont(new Font("Georgia",Font.ITALIC,75));      
        g.drawString("GameOver",W/6,H/2);
    }
    @Override
    public void actionPerformed(ActionEvent arg0) {
        Running();
        if (running){
           CheckA();
           run();
        }
        repaint();
    } 
    
    public void newApple(){
         Ax = randoms.nextInt(W/U); 
         Ay = randoms.nextInt(W/U);   
    }
    
    public void run(){
   
            for(int i=body; i > 0; i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
            }   
            switch (Key){
                case 'R':
                    x[0] += 1;
                    break;
                case 'L':
                    x[0] -= 1;break;
                case 'U':
                    y[0] -= 1;break;
                case 'D':
                    y[0] += 1;break;
            }
            
        
   }    
    
    public void Running(){ 
        if (   (x[0]<0)||(x[0] ==(W/U))||  
               (y[0]<0)||(y[0] ==(H/U))   ) {
            running = false;
        }
        for (int i = 1; i < body; i++) {
            if ((x[0]== x[i])&&(y[0]== y[i])) {
                running = false;
            }   
        }
        
    } 
 
    public void CheckA(){
         for (int i = 0; i < body; i++) {
            if ((x[i]==Ax)&&(y[i]==Ay)) {
                newApple();
                body += 1;
            }   
        }
        
    }
    public class GameKey extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent E) {
            switch (E.getKeyCode()){
            case KeyEvent.VK_LEFT :
              System.out.println(Key);
              if((Key == 'D')||(Key == 'U')) Key = 'L';
              break;
            case KeyEvent.VK_RIGHT :
                
             if((Key == 'D')||(Key == 'U')) Key = 'R';
              System.out.println(Key);
              break;
            case KeyEvent.VK_DOWN:
                
              if((Key == 'R')||(Key == 'L')) Key = 'D';
              System.out.println(Key);
              break;       
            case KeyEvent.VK_UP :
              if((Key == 'R')||(Key == 'L')) Key = 'U'; 
              System.out.println(Key);
              break;
        }
      }      
    }
}