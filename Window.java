package com.company;

import javax.swing.*;
import java.awt.*;

public class Window {
    public static void madeWindow(JFrame window){
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//add button for closing
        window.setSize(400, 400);//size of window
        window.setLayout(new BorderLayout());//location manager
        window.setLocationRelativeTo(null); //window locate to center
        window.setVisible(true);
    }
}
