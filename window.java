package lightsout;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class window implements ActionListener{
    String lengthString = JOptionPane.showInputDialog("Please input the length of the gameboard: ");
    String widthString = JOptionPane.showInputDialog("Please input the width of the gameboard: ");
    int length = Integer.parseInt(lengthString);
    int width = Integer.parseInt(widthString);
    JFrame window = new JFrame("Lights Out Game");
    JPanel mainPanel = new JPanel(new BorderLayout());
    JPanel sidePanel = new JPanel(new BorderLayout());
    JPanel gameboard = new JPanel();
    JButton[][] lights;
    
    public window() {
        lights = new JButton[length][width];
        window.setVisible(true);
        window.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        gameboard.setBackground(Color.WHITE);
        window.add(mainPanel);
        mainPanel.add(gameboard);
        mainPanel.add(sidePanel, BorderLayout.EAST);
        gameboard.setLayout(new GridLayout(length, width, 5, 5));
        lights = new JButton[length][width];
    
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                lights[i][j] = new JButton();
                gameboard.add(lights[i][j]);
                lights[i][j].addActionListener(this);
            }
        }
    
    
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        fn_randomize();
}
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                if (e.getSource() == lights[i][j]) {
                    if (i+1<length && i-1>=0 && j+1<width && j-1>=0){
                        fn_flip(i, j);
                        fn_flip(i+1, j);
                        fn_flip(i-1, j);
                        fn_flip(i, j+1);
                        fn_flip(i, j-1);
                    }

                    else if (i+1 >= length && j+1 >= width) {
                        fn_flip(i, j);
                        fn_flip(i-1, j);
                        fn_flip(i, j-1);
                    }
                    
                    else if (i-1 < 0 && j+1 >= width) {
                        fn_flip(i, j);
                        fn_flip(i+1, j);
                        fn_flip(i, j-1);
                    }
                    
                    else if (i+1 >= length && j-1 < 0) {
                        fn_flip(i, j);
                        fn_flip(i-1, j);
                        fn_flip(i, j+1);
                    }
                    
                    else if (i-1 < 0 && j-1 < 0) {
                        fn_flip(i, j);
                        fn_flip(i+1, j);
                        fn_flip(i, j+1);
                    }
                    
                    else if (i+1 >= length) {
                        fn_flip(i, j);
                        fn_flip(i-1, j);
                        fn_flip(i, j+1);
                        fn_flip(i, j-1);
                    }
                    
                    else if (i-1 < 0) {
                        fn_flip(i, j);
                        fn_flip(i+1, j);
                        fn_flip(i, j-1);
                        fn_flip(i, j+1);
                    }
                    
                    else if (j+1 >= length) {
                        fn_flip(i, j);
                        fn_flip(i, j-1);
                        fn_flip(i-1, j);
                        fn_flip(i+1, j);
                    }
                    
                    else if (j-1 < 0) {
                        fn_flip(i, j);
                        fn_flip(i, j+1);
                        fn_flip(i-1, j);
                        fn_flip(i+1, j);
                    }
                    
                    else {
                        fn_flip(i, j);
                        fn_flip(i, j-1);
                        fn_flip(i, j+1);
                        fn_flip(i-1, j);
                        fn_flip(i+1, j);                        
                    }
                }
            }
        }
        
        
        if(fn_checkWin())
            JOptionPane.showMessageDialog(null, "You win");     
        

    }
    
        public boolean fn_checkWin() {
        int numberOff = 0;
        for(int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                if (lights[i][j].getBackground() == Color.BLACK) {
                    numberOff++;
                }
            }
        }
        int numberOfElements = length*width;
        if (numberOfElements == numberOff) {
            return true;
        } else {
            return false;
        }
    }
    
    public void fn_flip(int i, int j) {
            if (lights[i][j].getBackground() == Color.YELLOW)
                lights[i][j].setBackground(Color.BLACK);
            else if (lights[i][j].getBackground() == Color.BLACK)
                lights[i][j].setBackground(Color.YELLOW);
    }
    
    public void fn_randomize() {
        Random rand = new Random();
        int[][] randomList = new int[length][width];
        
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
               randomList[i][j] = rand.nextInt(2);               
            }
        }
        
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                if (randomList[i][j] == 0) {
                    lights[i][j].setBackground(Color.BLACK);
                }
                
                if (randomList[i][j] == 1) {
                    lights[i][j].setBackground(Color.YELLOW);
                }
            }
        }

        
    }
    
}
