import java.io.*;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class view extends JFrame
{
    Container c;
    JTextArea labAnswer;
    JButton goBack,viewHistory,clearHistory;
    view()
    { 
             c=getContentPane();
             c.setLayout(new FlowLayout(FlowLayout.CENTER,50,50));
             c.setBackground(Color.BLUE);
             labAnswer=new JTextArea("");
             goBack=new JButton("Go Back");
             viewHistory=new JButton("viewHistory");
             clearHistory=new JButton("clearHistory");
             goBack.setForeground(Color.RED);
             viewHistory.setForeground(Color.RED);
             clearHistory.setForeground(Color.RED);
             labAnswer.setForeground(Color.RED);
             c.add(goBack);
             c.add(viewHistory);
             c.add(clearHistory);
             c.add(labAnswer);
             ActionListener b=(be)->{
                    Calculate c=new Calculate();
                      dispose();
             };
             ActionListener a=(ae)->{
                try
                {
                        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
          		String url="jdbc:mysql://localhost:3306/project25jan24";
          		Connection con=DriverManager.getConnection(url,"root","abc123");
          		String sql="select * from calci";
                        PreparedStatement pst=con.prepareStatement(sql);
                        ResultSet rs=pst.executeQuery();
          		while(rs.next())
          		{
               			labAnswer.append(rs.getDouble(1)+" "+rs.getString(2)+" "+rs.getDouble(3)+" "+rs.getString(4)+" "+rs.getDouble(5)+"\n");
          		}
                        con.close();
                     
                }
                catch(SQLException e)
                {
        			JOptionPane.showMessageDialog(c,e.getMessage());
     	  	}
             };
             ActionListener d=(de)->{
                try
                {
                     DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
          	     String url="jdbc:mysql://localhost:3306/project25jan24";
          	     Connection con=DriverManager.getConnection(url,"root","abc123");
          	     String sql="delete from calci";
                     PreparedStatement pst=con.prepareStatement(sql);
                     pst.executeUpdate();
                     JOptionPane.showMessageDialog(c,"Your table records are deleted. Go back to enter new records");
                     con.close();
     	  	}catch(SQLException e){
        			JOptionPane.showMessageDialog(c,e.getMessage());
     	  	}
             };
             goBack.addActionListener(b);
             viewHistory.addActionListener(a);
             clearHistory.addActionListener(d);
             setTitle("Calculator powered by Java GUI");
             setSize(700,700);
             setResizable(false);
             setLocationRelativeTo(null);
             setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             setVisible(true);
    }
}