package com.company;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
public class Main {
    public static void main (String[] args){
        System.out.println("Start");
        JFrame window = new JFrame("TicTacToe");//main window
        Window.madeWindow(window);
        TicTacToe game = new TicTacToe();
        window.add(game);
        System.out.println("Finish");
    }
}
