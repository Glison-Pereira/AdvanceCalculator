import java.io.*;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Splashwindow extends JFrame
{
      Container c;
      JLabel labShow;
      Splashwindow()
      {
             c=getContentPane();
             c.setLayout(new FlowLayout(FlowLayout.CENTER,40,280));
             c.setBackground(Color.MAGENTA);
             Font f=new Font("Curlz MT",Font.BOLD,40);
             labShow=new JLabel("<html><center>Welcome to Calculator <br><h2>powered by Java GUI</h2></center></html>");
             labShow.setFont(f);
             labShow.setForeground(Color.CYAN);
             c.add(labShow);
             setTitle("Calculator powered by Java GUI");
             setSize(700,700);
             setUndecorated(true);
             setLocationRelativeTo(null);
             setVisible(true);
             try
             {
                Thread.sleep(20000);
                dispose();
                Calculate c=new Calculate();
             }
             catch(Exception e)
             {
                  JOptionPane.showMessageDialog(c,e.getMessage());
             } 
      }
}