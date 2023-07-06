package com.powerful_timer;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main {

    static JFrame frame = new JFrame();
    static JPanel stopwatchContainer = new JPanel(new GridBagLayout());

    public static void main(String[] args) {

        frame.setLayout(new GridLayout(2, 1));
        Stopwatch stopwatch1 = new Stopwatch();
        Stopwatch stopwatch2 = new Stopwatch();

        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

}
