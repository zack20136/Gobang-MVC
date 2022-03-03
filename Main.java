import javax.swing.JOptionPane;

public class Main{
	public static void main(String[] args){
		Gobangmanageer manager = new Gobangmanageer();
		manager.H_OR_C();
		while(true){
			manager.receive();
		}
	}
}