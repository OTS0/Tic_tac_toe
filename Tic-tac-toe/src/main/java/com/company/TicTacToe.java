package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class TicTacToe extends JComponent {
    public static final int fieldEmpty =0;
    public static final int fieldX =10;//field for cross
    public static final int field0 =200;//field for zero
    int[][] fields;
    boolean isXturn;
    public TicTacToe(){
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
        fields=new int[3][3];
        initGame();
    }

    public void initGame() {
        for (int i=0; i<3; ++i){
            for (int j=0; j<3; ++j){
                fields[i][j]=fieldEmpty;//clear our squares
            }
        }
        isXturn=true;
    }
    int checkGame()
    {
        int d1=0;
        int d2=0;
        for (int i=0; i<3; i++){
            d1+=fields[i][i];
            d2+=fields[i][2-i];
        }
        if (d1==field0*3||d1==fieldX*3){return d1;}
        if (d2==field0*3||d2==fieldX*3){return d2;}
        int check_i, check_j;
        boolean hasEmpty =false;
        for(int i=0; i<3; i++){
            check_i=0;
            check_j=0;
        for(int j=0; j<3; j++){
            if (fields[i][j]==0){hasEmpty=true;}
            check_i+=fields[i][j];
            check_j+=fields[j][i];
        }
        if (check_i==field0*3||check_i==fieldX*3){return check_i;}
        if (check_j==field0*3||check_j==fieldX*3){return check_j;}
        }
        if(hasEmpty) return 0; else return  -1;//define draw
    }

    @Override
    protected void paintComponent (Graphics graphics){
        super.paintComponent(graphics);
        graphics.clearRect(0,0,getWidth(),getHeight());//clear to canvas
        drawGrid(graphics);//net
        drawX0(graphics);
    }

    @Override
    protected void processMouseEvent(MouseEvent e) {
        super.processMouseEvent(e);
        if (e.getButton()==MouseEvent.BUTTON1){
            int x=e.getX();
            int y=e.getY();
            //translate coordinates to indexes of array
            int i =(int) ((float)x/getWidth()*3);
            int j =(int) ((float)y/getWidth()*3);
            if (fields[i][j]==fieldEmpty){
                fields[i][j]=isXturn?fieldX:field0;
                isXturn=!isXturn;
                repaint();
                int res=checkGame();
                if (res!=0){
                    if (res==field0*3){
                        JOptionPane.showMessageDialog(this,"Zeros win!", "Crosses are losers", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else if(res==fieldX*3){JOptionPane.showMessageDialog(this,"Crosses win!", "Zeros are round losers ", JOptionPane.INFORMATION_MESSAGE);}
                    else{ JOptionPane.showMessageDialog(this,"Nobody win!","Dead heat",JOptionPane.INFORMATION_MESSAGE); }
                    initGame();
                    repaint();
                }
            }
        }
    }

    void drawGrid(Graphics graphics){
        int width=getWidth();
        int height= getHeight();
        int w=width/3; //width of one square
        int h=height/3;//height of one square
        graphics.setColor(Color.GREEN);
        for (int i=1;i<3;i++){
            graphics.drawLine(0,h*i, width,h*i);
            graphics.drawLine(w*i,0, w*i,height);
        }
    }
    void drawX(int i, int j, Graphics graphics) {
        graphics.setColor(Color.BLACK);
        int w=getWidth()/3;
        int h=getHeight()/3;
        int x=i*w;
        int y=j*h;
        graphics.drawLine(x,y,x+w,y+h);
        graphics.drawLine(x,y+h,x+w,y);
    }
    void draw0(int i, int j, Graphics graphics){
        graphics.setColor(Color.ORANGE);
        int w=getWidth()/3;
        int h=getHeight()/3;
        int x=i*w;
        int y=j*h;
        graphics.drawOval(x+5*w/100,y,w*9/10,h);//coefficients for improving picture
    }
    void drawX0(Graphics graphics){
        for (int i=0; i<3; ++i){
            for (int j=0; j<3; ++j){
                if (fields[i][j]==fieldX){ drawX(i,j,graphics);}
                else if (fields[i][j]==field0){ draw0(i,j,graphics);}
            }
        }
    }
}
