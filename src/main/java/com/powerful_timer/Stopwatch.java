package com.powerful_timer;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
 * what i am trying to do now: 
 * make a newTimerButton  DONE 
 * make the timer buttons buiding code reuseable
*/

public class Stopwatch implements ActionListener {
    // static variables, JFrame, JButton, JLabel are all javax.swing classes

    // JButton newTimerButton = new JButton("New Timer");

    // JButton testTest = makeNewButton("test", 600, 200, 150, 50, false);
    JButton setTimeButton = makeNewButton("Set", 100, 50, false);
    JButton repeatButton = makeNewButton("Repeat", 100, 50, false);
    JButton startButton = makeNewButton("Start", 100, 50, false);
    JButton resetButton = makeNewButton("Reset", 100, 50, false);

    JLabel timeLabel = new JLabel();
    JTextField setTimeBox = new JTextField();
    JLabel inputTimeLabel = new JLabel();

    // variables
    int elapsedTime;
    int seconds = (elapsedTime / 1000) % 60;
    int mins = (elapsedTime / 60000) % 60;
    int hours = (elapsedTime / 3600000);
    boolean started = false;
    boolean isRepeating = false;

    // placeholder for hours numbers and seconds: 00 00 00
    String seconds_string = String.format("%02d", seconds);
    String mins_string = String.format("%02d", mins);
    String hours_string = String.format("%02d", hours);

    // countdown
    Timer timer = new Timer(1000, e -> {
        // TODO Auto-generated method stub
        // System.out.println(elapsedTime);
        if (this.elapsedTime > 0) {
            perfromCountingDown();
        }

        else if (this.elapsedTime == 0 && isRepeating) {
            Toolkit.getDefaultToolkit().beep();
            setTime(Integer.parseInt(setTimeBox.getText()));
            perfromCountingDown();
        } else if (this.elapsedTime <= 0 && !isRepeating) {
            stop();
            Toolkit.getDefaultToolkit().beep(); // ---------------------------
        }

    });

    // constructor to create a new instance
    public Stopwatch() {

        JPanel masterPanel = new JPanel(new GridLayout(3, 1, 5, 0)); // 3 rows, 1 column
        JPanel firstRowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0)); // Horizontal gap of 10 pixe
        JPanel secondRowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0)); // Horizontal gap of 10 pixe
        JPanel thirdRowPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0)); // Horizontal gap of 10 pixe

        // add first row buttons to firstRowPanel
        firstRowPanel.add(setTimeBox);
        firstRowPanel.add(inputTimeLabel);
        firstRowPanel.add(setTimeButton);
        firstRowPanel.add(repeatButton);

        masterPanel.add(firstRowPanel);

        // add second row buttons to secondRowPanel
        secondRowPanel.add(timeLabel);

        masterPanel.add(secondRowPanel);

        // add third row buttons to thirdRowPanel
        thirdRowPanel.add(startButton);
        thirdRowPanel.add(resetButton);

        masterPanel.add(thirdRowPanel);

        // Main.frame.add(startButton);
        // Main.frame.add(resetButton);
        // Main.frame.add(timeLabel);
        // Main.frame.add(setTimeBox);
        // Main.frame.add(inputTimeLabel);
        // Main.frame.add(setTimeButton);
        // Main.frame.add(repeatButton);
        // Main.frame.add(newTimerButton);

        timeLabel.setText(hours_string + " : " + mins_string + " : " + seconds_string);
        timeLabel.setBounds(50, 100, 300, 100);
        timeLabel.setFont(new Font("Verdana", Font.PLAIN, 35));
        timeLabel.setBorder(BorderFactory.createBevelBorder(1));
        timeLabel.setOpaque(true);
        timeLabel.setHorizontalAlignment(JTextField.CENTER);

        inputTimeLabel.setText("minutes");
        inputTimeLabel.setBounds(150, 10, 150, 50);
        inputTimeLabel.setFont(new Font("Verdana", Font.PLAIN, 35));
        inputTimeLabel.setBorder(BorderFactory.createBevelBorder(1));
        inputTimeLabel.setOpaque(true);
        // inputTimeLabel.setHorizontalAlignment(JTextField.CENTER);

        setTimeBox.setText("");
        // setTimeBox.setBounds(50, 10, 100, 50);
        setTimeBox.setPreferredSize(new Dimension(100, 50));
        setTimeBox.setFont(new Font("Verdana", Font.PLAIN, 20));

        // startButton.setBounds(50, 200, 150, 50);
        // startButton.setFont(new Font("Ink Free", Font.PLAIN, 20));
        // startButton.setFocusable(false);
        // startButton.addActionListener(this); // what trigger the action to happen

        // resetButton.setBounds(220, 200, 150, 50);
        // resetButton.setFont(new Font("Ink Free", Font.PLAIN, 20));
        // resetButton.setFocusable(false);
        // resetButton.addActionListener(this); // what trigger the action to happen

        // repeatButton.setBounds(4 00, 10,100, 50);
        // repeatButton.setFont(new Font("Ink Free", Font.PLAIN, 20));
        // repeatButton.setFocusable(false);
        // repeatButton.addActionListener(this); // what trigger the action to happen
        // repeatButton.setOpaque(true);

        // newTimerButton.setBounds(500, 10, 130, 50);
        // newTimerButton.setFont(new Font("Ink Free", Font.PLAIN, 20));
        // newTimerButton.setFocusable(false);
        // newTimerButton.addActionListener(this); // what trigger the action to happen
        // newTimerButton.setOpaque(true);

        // setTimeButton.setBounds(300, 10, 100, 50);
        // setTimeButton.setFont(new Font("Ink Free", Font.PLAIN, 25));
        // setTimeButton.setFocusable(false);
        // setTimeButton.addActionListener(this); // what trigger the action to happen

        Main.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Main.frame.setSize(800, 800);
        Main.frame.setVisible(true);
        Main.frame.add(masterPanel);
        Main.frame.pack();

    }

    // method for making new button

    public JButton makeNewButton(String buttonText, int width, int height, boolean setFocusable) {

        JButton button = new JButton(buttonText);
        button.setPreferredSize(new Dimension(width, height));
        button.setFont(new Font("Ink Free", Font.PLAIN, 20));
        button.addActionListener(this); // what trigger the action to happen
        button.setOpaque(true);
        button.setFocusable(setFocusable);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            if (!started) {
                started = true;
                startButton.setText("Stop");
                timer.start();
            } else {
                started = false;
                startButton.setText("Start");
                timer.stop();
            }
        } else if (e.getSource() == resetButton) {
            started = false;
            startButton.setText("Start");
            reset();
        } else if (e.getSource() == setTimeButton) {
            setTime(Integer.parseInt(setTimeBox.getText()));

        } else if (e.getSource() == repeatButton) { // when times runs out, it will re-run the set time again
            if (isRepeating == false) {
                isRepeating = true;
                repeatButton.setBackground(Color.green);

            } else {
                isRepeating = false;
                repeatButton.setBackground(UIManager.getColor("Button.background"));
            }
        }

    }

    public void start() {
        timer.start();

    }

    public void stop() {
        timer.stop();
    }

    public void reset() {
        timer.stop();
        setTime(0);

    }

    public void setTime(int myTime) {
        this.elapsedTime = myTime * 1000 * 60;
        this.seconds = (this.elapsedTime / 1000) % 60;
        this.mins = (this.elapsedTime / 60000) % 60;
        this.hours = (this.elapsedTime / 3600000);
        // placeholder for hours numbers and seconds: 00 00 00
        this.seconds_string = String.format("%02d", this.seconds);
        this.mins_string = String.format("%02d", this.mins);
        this.hours_string = String.format("%02d", this.hours);
        this.timeLabel.setText(hours_string + " : " + mins_string + " : " + seconds_string);
    }

    public void perfromCountingDown() {
        elapsedTime -= 1000;
        hours = (elapsedTime / 3600000);
        mins = (elapsedTime / 60000) % 60;
        seconds = (elapsedTime / 1000) % 60;
        String seconds_string = String.format("%02d", seconds);
        String mins_string = String.format("%02d", mins);
        String hours_string = String.format("%02d", hours);
        timeLabel.setText(hours_string + " : " + mins_string + " : " + seconds_string);
    }

    // public Stopwatch addNewTimer() {

    // }
}

/*
 * OBJECTIVE: adding new timer within the same instances
 * 
 * BIGGER PICTURE:
 * 1. create diff timer with diff variable name and store them in a collection
 * 2. since the timer logic is already in place, jsut need to figure out the way
 * to tell javafx to stack them
 * 
 */
