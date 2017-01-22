package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import multithreads.Thread1;
import sql.CallStoredProcedure;

public class UIPro extends JFrame implements ActionListener {
	Connection con;
    Statement stmt;

     PreparedStatement preStatement,updatePreStmt;

    JLabel title, idLabel, nameLabel, genderLabel, addressLabel, contactLabel;

    JTextField idField, nameField, genderField, addressField, contactField;

    JButton registerButton, exitButton,updateButton,deleteButton,resetButton,

            refresh;

    JRadioButton male, female;
    ButtonGroup bg;

    JPanel panel;
    JTable table;

    DefaultTableModel model;
    
    JScrollPane scrollpane;
    public UIPro() {
         // TODO Auto-generated constructor stub

         super("Procedure call");
          setSize(770, 420);
          setLayout(null);

          // Calling connect method, this will connect us to database
         // connect();

          // Defining Labels 

          title = new JLabel("Procedure details");

          title.setBounds(60, 7, 200, 30);

          idLabel = new JLabel("Name");

          idLabel.setBounds(30, 45, 60, 30);

          nameLabel = new JLabel("Parameter"); 

          nameLabel.setBounds(30, 85, 100, 30);

       //   genderLabel = new JLabel("Gender"); 

       //   genderLabel.setBounds(30, 120, 60, 30);

          addressLabel = new JLabel("Parameter"); 

          addressLabel.setBounds(30, 125, 100, 30); 

          contactLabel = new JLabel("Parameter"); 

          contactLabel.setBounds(30, 165, 100, 30);

          // Defining ID field
          idField = new JTextField(); 

          idField.setBounds(95, 45, 130, 30);
          //idField.setEnabled(false);
          // Defining Name field
          nameField = new JTextField(); 

          nameField.setBounds(95, 85, 130, 30);         

          // Defining Gender Buttons
       //   male = new JRadioButton("Male");

      //    male.setBounds(95, 120, 60, 30);

       //   female = new JRadioButton("Female");

       //   female.setBounds(155, 120, 70, 30);            

        //  bg = new ButtonGroup(); 

        //  bg.add(male); 

        //  bg.add(female); 

          addressField = new JTextField(); 

          addressField.setBounds(95, 125, 130, 30);

          contactField = new JTextField(); 

          contactField.setBounds(95, 165, 130, 30);



          // fixing all Label,TextField,RadioButton

          add(title);

          add(idLabel);

          add(nameLabel);

        //  add(genderLabel);

          add(addressLabel);

          add(contactLabel);

          add(idField);

          add(nameField);

       //   add(male);

        //  add(female);
          
          add(addressField);

          add(contactField);


          // Defining Exit Button

          exitButton = new JButton("Run procedure"); 

          exitButton.setBounds(25, 250, 180, 25);            

          // Defining Register Button

         // registerButton = new JButton("View details");

          //registerButton.setBounds(110, 250, 100, 25);


          // Defining Update Button

          updateButton = new JButton("Create Threads");

          updateButton.setBounds(25, 285, 180, 25);

         // updateButton.setEnabled(false);


          // Defining Delete Button

          deleteButton = new JButton("Create Threads");

          deleteButton.setBounds(25, 285, 170, 25);

          //deleteButton.setEnabled(false);


          // Defining Reset Button

          resetButton = new JButton("Reset");

          resetButton.setBounds(60, 320, 100, 25);

          resetButton.setEnabled(false); 


          // fixing all Buttons

          add(exitButton);
          
       //   add(registerButton);

          add(updateButton);

        //  add(deleteButton);

        //  add(resetButton);    


          // Defining Panel

          panel = new JPanel();

          panel.setLayout(new GridLayout());

          panel.setBounds(250, 20, 480, 330);

          panel.setBorder(BorderFactory.createDashedBorder(Color.blue));

          add(panel);

          // Defining Refresh Button

          refresh = new JButton("see details");

          refresh.setBounds(350, 350, 270, 15);

          add(refresh);

          exitButton.addActionListener(this);
          //Defining Model for table

          model = new DefaultTableModel();

          //Adding object of DefaultTableModel into JTable

          table = new JTable(model);


          //Fixing Columns move

          table.getTableHeader().setReorderingAllowed(false);


          // Defining Column Names on model

          model.addColumn("S.No");

         // model.addColumn("ID");

          model.addColumn("Name");
          
          model.addColumn("Parameter");

          model.addColumn("Parameter");
          model.addColumn("Parameter");



          // Enable Scrolling on table

         scrollpane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,

                                         JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

          panel.add(scrollpane);


          //Displaying Frame in Center of the Screen

          Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

          this.setLocation(dim.width/2-this.getSize().width/2,

                           dim.height/2-this.getSize().height/2);

          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

          setResizable(false);

          setVisible(true);



	

}
    
    public static void main(String args[])
    {
    	new UIPro();
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==exitButton)
		{
			String nameoftheparameter=idField.getText();
			String parameter=nameField.getText();
			int param=Integer.parseInt(parameter);
			
			
			
			try {
				new Thread1(nameoftheparameter,param);
				Integer outparam=CallStoredProcedure.executeStoredProcedure(nameoftheparameter,param);
				//addressField.setText(outparam.toString());
				JOptionPane.showMessageDialog(null, outparam);
				
				
				
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
	}
}
