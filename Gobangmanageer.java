import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;

public class Gobangmanageer implements ActionListener{
	private static final int PORT_NUMBER = 5487;
	private ServerSocket server;
	private Socket client;
	private Socket s;
	
	private Gobangboard board;
	private Gobangmodel model;
	private static final int SIZE_OF_BOARD = 9;
	
	private int whoWin;
	private int whosTurn;
	private int player;
	private int x, y;
	
	public Gobangmanageer(){
		board = new Gobangboard(this);
		model = new Gobangmodel();
		
		whoWin = 0;
		whosTurn = 1;
	}
	
	public void H_OR_C(){
		Object[] options = {"HOST", "CLIENT" };
		int tmp = JOptionPane.showOptionDialog(board, "HOST OR CLIENT?", "CHOOSE ONE",
		JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
		if(tmp == 0){
			board.setTitle("Gobang - PLAYER 1");
			player = 1;
			try{
				server = new ServerSocket(PORT_NUMBER);
				System.out.println("Wating for Connection");
				s = server.accept();
				System.out.println("A new client is connected");
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		else{
			board.setTitle("Gobang - PLAYER 2");
			player = 2;
			String ip = JOptionPane.showInputDialog(null, "HOST IP");
			if(ip == ""){
				ip = "127.0.0.1";
			}
			try{
				client = new Socket(ip, 5487);
				s = client;
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public void send(int x, int y){
		try{
			OutputStream os = s.getOutputStream();
			String strr = x + "-" + y;
			os.write(strr.getBytes());
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void receive(){
		try{
			InputStream in = s.getInputStream();
			byte[] bys = new byte[1024];
			int len = in.read(bys);
			String strReceive = new String(bys, 0, len);
			String[] str = strReceive.split("-");
			this.x = Integer.parseInt(str[0]);
			this.y = Integer.parseInt(str[1]);
			if(x == 999 && y == 999){
				if(s == client){
					JOptionPane.showMessageDialog(board, "Player 2 wins!");
					model.reset();
					board.reset();
					whosTurn = 1;
				}
				else{
					JOptionPane.showMessageDialog(board, "Player 1 wins!");
					model.reset();
					board.reset();
					whosTurn = 1;
				}
			}
			else{
				if(whosTurn == 1){
					ImageIcon icon = new ImageIcon("black.jpg");
					board.buttons[(x * SIZE_OF_BOARD) + y].setIcon(icon);
					model.addtoboard(whosTurn, x, y);
					whosTurn = 2;
					board.who.setText("PLAYER 2's TURN");
				}
				else{
					ImageIcon icon = new ImageIcon("white.jpg");
					board.buttons[(x * SIZE_OF_BOARD) + y].setIcon(icon);
					model.addtoboard(whosTurn, x, y);
					whosTurn = 1;
					board.who.setText("PLAYER 1's TURN");
				}
				whoWins();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void whoWins(){
		whoWin = model.whowins();
		if(whoWin == 1){
			JOptionPane.showMessageDialog(board, "Player "+whoWin+" wins!");
			model.reset();
			board.reset();
			whosTurn = 1;
		}
		else if(whoWin == 2){
			JOptionPane.showMessageDialog(board, "Player "+whoWin+" wins!");
			model.reset();
			board.reset();
			whosTurn = 1;
		}
		else if(whoWin == 3){
			JOptionPane.showMessageDialog(board, "TIE!");
			model.reset();
			board.reset();
			whosTurn = 1;
		}
	}
	
	public void actionPerformed(ActionEvent a){
		MButton clicked = (MButton) a.getSource();
		
		if(clicked.x == 999 && clicked.y == 999){
			send(clicked.x, clicked.y);
			if(s == client){
				JOptionPane.showMessageDialog(board, "Player 1 wins!");
				model.reset();
				board.reset();
				whosTurn = 1;
			}
			else{
				JOptionPane.showMessageDialog(board, "Player 2 wins!");
				model.reset();
				board.reset();
				whosTurn = 1;
			}
		}
		else{
			if(whosTurn == player && whosTurn == 1){
				if(model.addtoboard(whosTurn, clicked.x, clicked.y)){
					ImageIcon icon = new ImageIcon("black.jpg");
					clicked.setIcon(icon);
					whosTurn = 2;
					send(clicked.x, clicked.y);
					board.who.setText("PLAYER 2's TURN");
				}
			}
			else if(whosTurn == player && whosTurn == 2){
				if(model.addtoboard(whosTurn, clicked.x, clicked.y)){
					ImageIcon icon = new ImageIcon("white.jpg");
					clicked.setIcon(icon);
					whosTurn = 1;
					send(clicked.x, clicked.y);
					board.who.setText("PLAYER 1's TURN");
				}
			}
			whoWins();
		}
	}
}