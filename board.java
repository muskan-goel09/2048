
public class board {
	private tile [][] b;
	private int score = 0;
	private int size;
	int border = 0;
	
	public board() {
		b = new tile[4][4];
		this.size = 4;
		for(int i=0; i < this.b.length; i++) {
			for(int j= 0; j < this.b[i].length; j++) {
				b[i][j] = new tile();
			}
		}
	}
	
	public board(int size){
		this.size = size;
		System.out.println(this.size);
		this.b = new tile[this.size][this.size];
		for(int i=0; i < this.size; i++) {
			for(int j= 0; j < this.size; j++) {
				b[i][j] = new tile();
			}
		}
		
	}
	
	public void resetGame() {
		this.score = 0;
		for(int i=0; i < this.b.length; i++) {
			for(int j= 0; j < this.b[i].length; j++) {
				b[i][j] = new tile();
			}
		}
		spawn();
		spawn();
	}
	
	public tile[][] getBoard(){
		return this.b;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getHighestTile() {
		int high = this.b[0][0].getValue();
		for(int i=0; i<this.b.length; i++) {
			for(int j=0; j<this.b[i].length; j++) {
				if(this.b[i][j].getValue() > high)
					high = this.b[i][j].getValue();
			}
		}
		return high;
	}
		
	public int getSize() {
		return this.size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
		
	public void print() {
		for(int i=0; i<this.size; i++) {
			for(int j=0; j<this.size; j++) {
				System.out.print(b[i][j].to_String() + " ");
				System.out.print(" ");
			}
			System.out.println("");
		}
		System.out.println("Score:" + this.score);
	}
	
	public boolean gameOver() {
		int count = 0;
		for(int i=0; i<this.size; i++) {
			for(int j=0; j<this.size; j++) {
				if(this.b[i][j].getValue() > 0) {
					if(i == 0 && j == 0) {
						if(this.b[i][j].getValue() != this.b[i+1][j].getValue() && this.b[i][j].getValue() != this.b[i][j+1].getValue())
							count++;
					}
					else if(i == this.size-1 && j == 0) {
						if(this.b[i][j].getValue() != this.b[i-1][j].getValue() && this.b[i][j].getValue() != this.b[i][j+1].getValue())
							count++;
					}
					else if(i == 0 && j == this.size-1) {
						if(this.b[i][j].getValue() != this.b[i+1][j].getValue() && this.b[i][j].getValue() != this.b[i][j-1].getValue())
							count++;
					}
					else if(i == this.size-1 && j== this.size-1) {
						if(this.b[i][j].getValue() != this.b[i-1][j].getValue() && this.b[i][j].getValue() != this.b[i][j-1].getValue())
							count++;
					}
					else if(i == 0 && (j < this.size && j > 0)) {
						if(this.b[i][j].getValue() != this.b[i][j-1].getValue() && this.b[i][j].getValue() != this.b[i][j+1].getValue() && this.b[i][j].getValue() != this.b[i+1][j].getValue())
							count++;
					}
					else if(j == 0 && (i < this.size && i > 0)) {
						if(this.b[i][j].getValue() != this.b[i][j+1].getValue() && this.b[i][j].getValue() != this.b[i-1][j].getValue() && this.b[i][j].getValue() != this.b[i+1][j].getValue())
							count++;
					}
					else if(j == this.size-1 && (i < this.size - 1 && i > 0)) {
						if(this.b[i][j].getValue() != this.b[i][j-1].getValue() && this.b[i][j].getValue() != this.b[i-1][j].getValue() && this.b[i][j].getValue() != this.b[i+1][j].getValue())
							count++;
					}
					else if(i == this.size-1 && (j < this.size - 1 && j > 0)) {
						if(this.b[i][j].getValue() != this.b[i-1][j].getValue() && this.b[i][j].getValue() != this.b[i][j-1].getValue() && this.b[i][j].getValue() != this.b[i][j+1].getValue())
							count++;
					}
					else {
						if(this.b[i][j].getValue() != this.b[i][j-1].getValue() && this.b[i][j].getValue() != this.b[i-1][j].getValue() && this.b[i][j].getValue() != this.b[i][j+1].getValue() && this.b[i][j].getValue() != this.b[i+1][j].getValue())
							count++;
					}
				}
			}
		}
		if(count == 16)
			return true;
		return false;
	}
	
	public void spawn() {
		boolean empty = true;
		while(empty) {
			int row = (int)(Math.random() * this.size);
			int col = (int)(Math.random() * this.size);
			System.out.println("row: " + row + "col: " + col);
			double x = Math.random();
			if(this.b[row][col].getValue() == 0) {
				if(x < 0.2) {
					this.b[row][col] = new tile(4);
					empty = false;
				}
				else {
					this.b[row][col] = new tile(2);
					empty = false;
				}
			}
		}
	}
	
	public boolean blackOut() {
		int count = 0;
		for(int i=0; i<this.b.length; i++) {
			for(int j=0; j<this.b[i].length; j++) {
				if(this.b[i][j].getValue() > 0)
					count++;
			}
		}
		if(count == 16)
			return true;
		return false;
	}
	
	public void right() {
		for(int i=0; i<this.size; i++) {
			int r = this.size-1;
			for(int j=this.size-1; j>= 0; j--) {
				if(this.b[i][j].getValue() != 0 && r>0) {
					this.b[i][r--].setValue(this.b[i][j].getValue());
					if(j != r+1)
						this.b[i][j].setValue(0);
				}
			}
		}
		for(int i=0; i<this.size; i++) {
			for(int j=this.size-1; j>0; j--) {
				if(this.b[i][j].getValue() == this.b[i][j-1].getValue()) {
					this.b[i][j].setValue(this.b[i][j].getValue() + this.b[i][j-1].getValue());
					this.b[i][j-1].setValue(0);
					this.score += this.b[i][j].getValue();
				}
			}
		}
		for(int i=0; i<this.size; i++) {
			int r = this.size-1;
			for(int j=this.size-1; j>= 0; j--) {
				if(this.b[i][j].getValue() != 0 && r>0) {
					this.b[i][r--].setValue(this.b[i][j].getValue());
					if(j != r+1)
						this.b[i][j].setValue(0);
				}
			}
		}
		spawn();
	}
	
	public void down() {
		for(int j=0; j < this.size; j++) {
			int d = this.size-1;
			for(int i=this.size-1; i >= 0; i--) {
				if(this.b[i][j].getValue() != 0 && d>0) {
					this.b[d--][j].setValue(this.b[i][j].getValue());
					if( i != d+1)
						this.b[i][j].setValue(0);
				}
			}
		}
		for(int j=0; j < this.size; j++) {
			for(int i=this.size-1; i > 0; i--) {
				if(this.b[i][j].getValue() == this.b[i-1][j].getValue()) {
					this.b[i][j].setValue(this.b[i][j].getValue() + this.b[i-1][j].getValue());
					this.b[i-1][j].setValue(0);
					this.score += this.b[i][j].getValue();
				}
			}
		}
		for(int j=0; j < this.size; j++) {
			int d = this.size-1;
			for(int i=this.size-1; i >= 0; i--) {
				if(this.b[i][j].getValue() != 0 && d>0) {
					this.b[d--][j].setValue(this.b[i][j].getValue());
					if( i != d+1)
						this.b[i][j].setValue(0);
				}
			}
		}
		spawn();
	}
	
	public void up() {
		for(int j=0; j < this.size; j++) {
			int u = 0;
			for(int i=0; i < this.size; i++) {
				if(this.b[i][j].getValue() != 0 && u < this.size) {
					this.b[u++][j].setValue(this.b[i][j].getValue());
					if(i != u-1)
						this.b[i][j].setValue(0);
				}
			}
		}
		for(int j=0; j < this.size; j++) {
			for(int i=0; i < this.size-1; i++) {
				if(this.b[i][j].getValue() == this.b[i+1][j].getValue()) {
					this.b[i][j].setValue(this.b[i][j].getValue() + this.b[i+1][j].getValue());
					this.b[i+1][j].setValue(0);
					this.score += this.b[i][j].getValue();
				}
			}
		}
		for(int j=0; j < this.size; j++) {
			int u = 0;
			for(int i=0; i < this.size; i++) {
				if(this.b[i][j].getValue() != 0 && u < this.size) {
					this.b[u++][j].setValue(this.b[i][j].getValue());
					if( i != u-1)
						this.b[i][j].setValue(0);
				}
			}
		}
		spawn();
	}
	
	public void left() {
		for(int i=0; i<this.size; i++) {
			int l=0;
			for(int j=0; j<this.size; j++) {
				if(this.b[i][j].getValue() != 0) {
					this.b[i][l++].setValue(this.b[i][j].getValue());
					if(j != l-1)
						this.b[i][j].setValue(0);
				}
			}
		}
		for(int i=0; i<this.size; i++) {
			for(int j=0; j<this.size-1; j++) {
				if(this.b[i][j].getValue() == this.b[i][j+1].getValue()) {
					this.b[i][j].setValue(this.b[i][j].getValue() * 2);
					this.b[i][j+1].setValue(0);
					this.score += this.b[i][j].getValue();
				}
			}
		}
		for(int i=0; i<this.size; i++) {
			int l=0;
			for(int j=0; j<this.size; j++) {
				if(this.b[i][j].getValue() != 0) {
					this.b[i][l++].setValue(this.b[i][j].getValue());
					if(j != l-1)
						this.b[i][j].setValue(0);
				}
			}
		}
		spawn();
	}
}
