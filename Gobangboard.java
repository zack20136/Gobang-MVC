import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Gobangboard extends JFrame{
	private static final int FRAME_WIDTH    = 900;
    private static final int FRAME_HEIGHT   = 600;
    private static final int FRAME_X_ORIGIN = 500;
    private static final int FRAME_Y_ORIGIN = 150;
	private static final int SIZE_OF_BOARD = 9;
	
	protected MButton[] buttons;
	protected MButton surrender;
	protected JPanel chessboard;
	protected JPanel west, east;
	protected JTextField who;
	
	public Gobangboard(ActionListener listener){
		super();
		
		Container contentPane = getContentPane( );

        //set the frame properties
        setSize      (FRAME_WIDTH, FRAME_HEIGHT);
        setResizable (false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation  (FRAME_X_ORIGIN, FRAME_Y_ORIGIN);
		
		//set buttons
		contentPane.setLayout(new BorderLayout());
		chessboard = new JPanel();
		chessboard.setLayout(new GridLayout(9,9));
		buttons = new MButton[SIZE_OF_BOARD * SIZE_OF_BOARD];
		for(int i=0; i<SIZE_OF_BOARD; i++){
			for(int j=0; j<SIZE_OF_BOARD; j++){
				MButton tmp = new MButton(i,j);
				chessboard.add(tmp);
				tmp.addActionListener(listener);
				tmp.setBackground(Color.white);
				buttons[(i * SIZE_OF_BOARD) + j] = tmp;
			}
		}
		add(chessboard, BorderLayout.CENTER);
		west = new JPanel();
		east = new JPanel();
		who = new JTextField("PLAYER 1's TURN");
		west.add(who);
		who.setEditable(false);
		who.setFont(new Font("Microsoft Uighur",Font.ITALIC,15));
		surrender = new MButton(999,999);
		surrender.setText("surrender");
		surrender.addActionListener(listener);
		east.add(surrender);
		west.setPreferredSize(new Dimension(150,600));
		east.setPreferredSize(new Dimension(150,600));
		add(west, BorderLayout.WEST);
		add(east, BorderLayout.EAST);
		
		setVisible(true);
	}
	
	public void reset(){
		for(MButton b: buttons){
			b.setIcon(null);
		}
	}
}

class MButton extends JButton{
	protected int x;
	protected int y;
	
	public MButton(int x, int y){
		super();
		this.x = x;
		this.y = y;
	}
}