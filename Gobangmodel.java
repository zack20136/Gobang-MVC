public class Gobangmodel{
	private static final int SIZE_OF_BOARD = 9;
	
	private int[][] chess;
	
	public Gobangmodel(){
		this.reset();
	}
	
	public int whowins(){
		int flag = 0;
		int black_count = 0;
		int white_count = 0;
		
		for(int i=0; i<SIZE_OF_BOARD; i++){
			black_count = 0;
			white_count = 0;
			for(int j=0; j<SIZE_OF_BOARD; j++){
				if(chess[i][j] == 1){
					white_count = 0;
					black_count++;
					if(black_count == 5){
						flag = 1;
						return flag;
					}
				}
				else if(chess[i][j] == 2){
					black_count = 0;
					white_count++;
					if(white_count == 5){
						flag = 2;
						return flag;
					}
				}
			}
		}
		
		for(int i=0; i<SIZE_OF_BOARD; i++){
			black_count = 0;
			white_count = 0;
			for(int j=0; j<SIZE_OF_BOARD; j++){
				if(chess[j][i] == 1){
					white_count = 0;
					black_count++;
					if(black_count == 5){
						flag = 1;
						return flag;
					}
				}
				else if(chess[j][i] == 2){
					black_count = 0;
					white_count++;
					if(white_count == 5){
						flag = 2;
						return flag;
					}
				}
			}
		}
		
		for(int i=0; i<=SIZE_OF_BOARD - 5; i++){
			for(int j=0; j<=SIZE_OF_BOARD - 5; j++){
				black_count = 0;
				white_count = 0;
				for(int k=0; k<5; k++){
					if(chess[i+k][j+k] == 1){
						white_count = 0;
						black_count++;
						if(black_count == 5){
							flag = 1;
							return flag;
						}
					}
					else if(chess[i+k][j+k] == 2){
						black_count = 0;
						white_count++;
						if(white_count == 5){
							flag = 2;
							return flag;
						}
					}
				}
			}
		}
		
		for(int i=0; i<=SIZE_OF_BOARD - 5; i++){
			for(int j=SIZE_OF_BOARD - 1; j>=4; j--){
				black_count = 0;
				white_count = 0;
				for(int k=0; k<5; k++){
					if(chess[i+k][j-k] == 1){
						white_count = 0;
						black_count++;
						if(black_count == 5){
							flag = 1;
							return flag;
						}
					}
					else if(chess[i+k][j-k] == 2){
						black_count = 0;
						white_count++;
						if(white_count == 5){
							flag = 2;
							return flag;
						}
					}
				}
			}
		}
		
		for(int i=0; i<SIZE_OF_BOARD; i++){
			for(int j=0; j<SIZE_OF_BOARD; j++){
				if(chess[i][j] == 0){
					return flag;
				}
			}
		}
		
		flag = 3;
		return flag;
	}
	
	public boolean addtoboard(int who, int x, int y){
		if(this.chess[x][y] == 0){
			this.chess[x][y] = who;
			return true;
		}
		else return false;
	}
	
	public void reset(){
		chess = new int[SIZE_OF_BOARD][SIZE_OF_BOARD];
		for(int i=0; i<SIZE_OF_BOARD; i++){
			for(int j=0; j<SIZE_OF_BOARD; j++){
				chess[i][j] = 0;
			}
		}
	}
}