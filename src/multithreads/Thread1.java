package multithreads;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import sql.CallStoredProcedure;

public class Thread1 implements Runnable {
	
	Thread t;
	String name;
	Integer param;
	public Thread1(String name,Integer param)
	{
		this.name=name;
		this.param=param;
	t=new Thread(this);
	t.start();
	}
	@Override
	
	public void run() {
		// TODO Auto-generated method stub
		
		try {
			Integer outparam=CallStoredProcedure.executeStoredProcedure(name,param);
			JOptionPane.showMessageDialog(null, outparam);
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	}


