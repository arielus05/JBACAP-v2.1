//made by arielus05, huge credits go to RyiSnow on YT and the people at Stack Overflow for the majority of the code here. I just modified it to work how I wanted it to work :)

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

public class helperProgram {

  JFrame window;
  JLabel counterLabel, counterLabel2, label24HourTime, labelTimer;
  Font font1 = new Font("Arial", Font.PLAIN, 40);
  Font font2 = new Font("Arial", Font.PLAIN, 80);
  Font font3 = new Font("Arial", Font.PLAIN, 20);
  Timer timer;
  int second, minute, hour;
  String ddSecond, ddMinute, ddHour;
  String url = "https://www.roblox.com/games/6798894589?privateServerLinkCode=14953110956829127988251274602395";
  DecimalFormat dFormat = new DecimalFormat("00");
  

  public void functionJB() {

      new helperProgram();
  }

  public helperProgram() {

    window = new JFrame();
    window.setSize(800, 600);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setLayout(null);
    
    counterLabel = new JLabel("");
    counterLabel.setBounds(185, 300, 400, 200);
    counterLabel.setVerticalAlignment(JLabel.BOTTOM);
    counterLabel.setHorizontalAlignment(JLabel.CENTER);
    counterLabel.setFont(font1);
    
    counterLabel2 = new JLabel("");
    counterLabel2.setBounds(190, 150, 400, 200);
    counterLabel2.setVerticalAlignment(JLabel.CENTER);
    counterLabel2.setHorizontalAlignment(JLabel.CENTER);
    counterLabel2.setFont(font2);
    
    label24HourTime = new JLabel("24-Hour Time");
    label24HourTime.setBounds(190, 100, 400, 200);
    label24HourTime.setVerticalAlignment(JLabel.CENTER);
    label24HourTime.setHorizontalAlignment(JLabel.CENTER);
    label24HourTime.setFont(font3);
    
    labelTimer = new JLabel("Countdown Timer");
    labelTimer.setBounds(185, 335, 400, 200);
    labelTimer.setVerticalAlignment(JLabel.CENTER);
    labelTimer.setHorizontalAlignment(JLabel.CENTER);
    labelTimer.setFont(font3);
    
    window.add(counterLabel);
    window.add(counterLabel2);
    window.add(label24HourTime);
    window.add(labelTimer);
    window.setVisible(true);

    // Countdown Timer
    counterLabel.setText("00:00:00");
    counterLabel2.setText(jbMainProgram.finalTime);
    second = 0;
    minute = jbMainProgram.minutesNeeded;
    hour = jbMainProgram.hoursNeeded;
    countdownTimer();
    timer.start();
    
  
    // Autoclicker (to not make PC go to sleep)
    try {
      while (true) {
          Robot r = new Robot();
          int button = InputEvent.BUTTON1_DOWN_MASK;
          r.mousePress(button);
          Thread.sleep(400);
          r.mouseRelease(button);
          Thread.sleep(30000);
       }
         
       } catch (Exception a) {
            a.printStackTrace();
         }
  }

  public void countdownTimer() {
    timer = new Timer(1000, new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {

        second--;
        ddSecond = dFormat.format(second);
        ddMinute = dFormat.format(minute);
        ddHour = dFormat.format(hour);
        counterLabel.setText(ddHour + ":" + ddMinute + ":" + ddSecond);
        
        if (second == -1) {
          second = 59;
          minute--;
          ddSecond = dFormat.format(second);
          ddMinute = dFormat.format(minute);
          ddHour = dFormat.format(hour);
          counterLabel.setText(ddHour + ":" + ddMinute + ":" + ddSecond);
        }

        if (minute == -1) {
          minute = 59;
          hour--;
          ddSecond = dFormat.format(second);
          ddMinute = dFormat.format(minute);
          ddHour = dFormat.format(hour);
          counterLabel.setText(ddHour + ":" + ddMinute + ":" + ddSecond);
        }

        if (hour <= -1) {
          timer.stop();
          if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            try {
              desktop.browse(new URI(url));
            } catch (IOException | URISyntaxException f) {
              f.printStackTrace();
            }
          } else {
            Runtime runtime = Runtime.getRuntime();
            try {
              runtime.exec("xdg-open " + url);
            } catch (IOException g) {
              g.printStackTrace();
            }
          }
          System.exit(0);
        }
      }
    });
  }
}