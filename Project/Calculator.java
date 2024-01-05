import java.io.*;
import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Calculate extends JFrame
{
      Container c;
      JLabel labNum1,labNum2;
      JTextField labText1,labText2;
      JButton add,sub,mul,div,deci,vie;
      Calculate()
      {
             c=getContentPane();
             c.setLayout(new FlowLayout(FlowLayout.CENTER,50,50));
             c.setBackground(Color.BLUE);
             Font f=new Font("Freestyle Script",Font.BOLD,40);
             labNum1=new JLabel("Enter your First Number: ");
             labNum1.setFont(f);
             labNum1.setForeground(new Color(0,255,51));
             labText1=new JTextField(60);
             labNum2=new JLabel("Enter your Second Number: ");
             labNum2.setFont(f);
             labNum2.setForeground(new Color(0,255,51));
             labText2=new JTextField(60);
             add=new JButton("+");
             sub=new JButton("-");
             mul=new JButton("*");
             div=new JButton("/");
             deci=new JButton("%");
             vie=new JButton("View");
             add.setForeground(Color.RED);
             sub.setForeground(Color.RED);
             mul.setForeground(Color.RED);
             div.setForeground(Color.RED);
             deci.setForeground(Color.RED);
             vie.setForeground(Color.RED);
             c.add(labNum1);
             c.add(labText1);
             c.add(labNum2);
             c.add(labText2);
             c.add(add);
             c.add(sub);
             c.add(mul);
             c.add(div);
             c.add(deci);
             c.add(vie);
             ActionListener a=(ae)->{
                   try
                   {
                        String num1=labText1.getText(),num2=labText2.getText();
                        if(num1.equals("") || num2.equals(""))
                        throw new Exception("You cannot have nothing");
                        else if(num1.contains(" ") || num2.contains(" "))
                        throw new Exception("You cannot have white space");
                        else if(num1.replaceAll("[0-9-]","").matches("[A-Za-z!@#$%&*()_+=|<>?{}\\[\\]~]+") || num2.replaceAll("[0-9-]","").matches("[A-Za-z!@#$%&*()_+=|<>?{}\\[\\]~]+"))
                        throw new Exception("You cannot enter text or You cannot enter special symbol");
                        else if(num1.length()>7 || num2.length()>7)
                        throw new Exception("You should enter only numbers and you cannot enter numbers more than 10000000");
                        double n1=Double.parseDouble(num1),n2=Double.parseDouble(num2);
                        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                        String url="jdbc:mysql://localhost:3306/project25jan24";
                        Connection con=DriverManager.getConnection(url,"root","abc123");
          		String sql="insert into calci values(?,?,?,?,?)";
          		PreparedStatement pst=con.prepareStatement(sql);
                        pst.setDouble(1,n1);
                        pst.setDouble(3,n2);
                        pst.setString(4,"=");
                        if(ae.getSource()==add) 
                        {
                                 JOptionPane.showMessageDialog(c,"Addition of numbers is "+(n1+n2));
                                 pst.setString(2,"+");
                                 pst.setDouble(5,(n1+n2));
                        }
                        if(ae.getSource()==sub) 
                        {
                                 JOptionPane.showMessageDialog(c,"Subtraction of numbers is "+(n1-n2));
                                 pst.setString(2,"-");
                                 pst.setDouble(5,(n1-n2));
                                 
                        }
			if(ae.getSource()==mul) 
                        {     
                                 JOptionPane.showMessageDialog(c,"Multiplication of numbers is "+(n1*n2));
                                 pst.setString(2,"*");
                                 pst.setDouble(5,(n1*n2));
                        }
			if(ae.getSource()==div) 
                        {
                            if(n2==0)
                            throw new Exception("You cannot have second number zero");
                            JOptionPane.showMessageDialog(c,"Division of numbers is "+(n1/n2));
                            pst.setString(2,"/");
                            pst.setDouble(5,(n1/n2));
              
                        }
			if(ae.getSource()==deci) 
                        {
                               if(n2==0)
                               throw new Exception("You cannot have second number zero"); 
                               JOptionPane.showMessageDialog(c,"Mod of numbers is "+(n1%n2));
                               pst.setString(2,"%");
                               pst.setDouble(5,(n1%n2));
                        }
                        pst.executeUpdate();
          	        con.close();
                        labText1.setText("");
                       	labText1.requestFocus();
                        labText2.setText("");
                       	labText2.requestFocus();
                   }
                   catch(SQLException se)
                   {
                         JOptionPane.showMessageDialog(c,se.getMessage());
                   }
                   catch(Exception e)
                   {
                         JOptionPane.showMessageDialog(c,e.getMessage());
                         labText1.setText("");
                       	 labText1.requestFocus();
                         labText2.setText("");
                       	 labText2.requestFocus();
                   }
             };
             ActionListener b=(be)->{
                   try
                   {
                       if(be.getSource()==vie)
                       {
                              view v=new view();
                              dispose();
                       }
                   }
                   catch(Exception e)
                   {
                         JOptionPane.showMessageDialog(c,e.getMessage());
                   }
             };
             add.addActionListener(a);
             sub.addActionListener(a);
             mul.addActionListener(a);
             div.addActionListener(a);
             deci.addActionListener(a);
             vie.addActionListener(b);
             setTitle("Calculator powered by Java GUI");
             setSize(700,700);
             setResizable(false);
             setLocationRelativeTo(null);
             setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             setVisible(true);

      }
}