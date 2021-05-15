import java.util.Scanner;

public class abc{
	
	public static void main(String args[]) {
		board game;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the size of board");
		int a = sc.nextInt();
		game = new board(a);
		game.resetGame();
		game.print();
		Scanner s = new Scanner(System.in);
		while(!game.blackOut() && !game.gameOver()) {
			System.out.println("Enter L/R/U/D or E for exit");
			char move = s.next().charAt(0);
			if (Character.compare(move, 'E') == 0) {
				game.resetGame();
				game.print();
			}
			if (!game.gameOver() && !game.blackOut()) {
				if(Character.compare(move, 'L') == 0)
					game.left();
				else if(Character.compare(move, 'R') == 0)
					game.right();
				else if(Character.compare(move, 'U') == 0)
					game.up();
				else if(Character.compare(move, 'D') == 0)
					game.down();
				else
					System.out.println("Enter a valid move");
			}
			game.print();
			if(game.blackOut() || game.gameOver())
			{
				System.out.println("GAME OVER");
			}
		}
    }
}
