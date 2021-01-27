package nauka;

import java.util.Arrays;
import java.util.Scanner;

public class Ships {

	// 5 Carrier
	// 4 Battleship
	// 3 Destroyer
	// 2 Patrol
	// 1 Submarine
	
//	public static char[][] submarine(char[][] board, boolean[][] avPos, int[] pos, char sign) {
//		int i = pos[0];
//		int j = pos[1];
//		int k = 0;
//		
//		int N = board.length;
//				
//			if (avPos[i][j] == true) {
//				board[i][j] = sign;
//			} else {
//				System.out.println("ERRORERROR");
//			}
//
//		
//		return board;
//	}
	
	public static int[] generatePos(int L, int LMax, int[][] MapHor, int[][] MapVer) {
		int[] pos = new int[3];
		int N = MapHor.length;
		int horVer = 0;
		int i = 0;
		
		if (L <= LMax) {
			
			while(true) {	
				
				horVer = (int) Math.floor(2 * Math.random());
				if (horVer == 0) {
					System.out.println("Horizontal");

				} else if (horVer == 1) {
					System.out.println("Vertical");

				}
				
				i = (int) Math.floor(N * Math.random());
				
				if (horVer == 0) {
					if (MapHor[i][1] >= L) {
						
						System.out.println("GenPos - i: " + MapHor[i][2] + ", j: " + MapHor[i][3]);
						
						pos[0] = MapHor[i][2];
						pos[1] = MapHor[i][3];
						pos[2] = 0;
						break;
						
					} else {
						
						System.out.println("WRONG GenPos - i: " + MapHor[i][2] + ", j: " + MapHor[i][3]);
					}
				} else if (horVer == 1) {
					
						if (MapVer[i][1] >= L) {
						
						System.out.println("GenPos - i: " + MapVer[i][2] + ", j: " + MapVer[i][3]);
						
						pos[0] = MapVer[i][2];
						pos[1] = MapVer[i][3];
						pos[2] = 1;
						break;
						
					} else {
						
						System.out.println("WRONG GenPos - i: " + MapVer[i][2] + ", j: " + MapVer[i][3]);
					}
					
				}
			}
		}
		return pos;
	}
	
	public static void placeShip (char[][] board, int L, int[] pos, char sign) {
		int i = 0;
		
		if (pos[2] == 0) {
			while (i < L) {
				
				board[pos[0]][pos[1] - i] = sign;
				i++;
				
			}
			
		} else if (pos[2] == 1) {
			while (i < L) {
				
				board[pos[0] - i][pos[1]] = sign;

				i++;
				
			}
			
		}
		
	}
	
	public static void spawn(char[][] board, int L, int No, char sign) {
		
		int N = board.length;
		char[][] avPosAux = new char[N + 2][N + 2];
		boolean[][] avPos = new boolean[N][N];
		int i = 0;
		
		while (i < No) {
			avPosAux = Board.GenPosAux(board);
			avPos = Board.avPos(avPosAux);
			int[] pos = (Ships.generatePos(L, Board.maxPosAlong(avPos), Board.spawnMapHor(avPos), Board.spawnMapVer(avPos)));
			Ships.placeShip(board, L, pos, sign);	
			i++;
		}
		
	}
	
	public static void playerShot(char[][] boardPl, char[][] boardCpu, char signCpu, char signPl) {
		
		char shot = Character.toLowerCase(signCpu);
		Scanner sc = new Scanner(System.in);
		int i, j;
	
		while (true) {
			
			System.out.println("Give shot position i: ");
			i = sc.nextInt();
			System.out.println("Give shot position j: ");
			j = sc.nextInt();
			
			if (boardCpu[i][j] == '~') {
				boardPl[i][j] = '•';
				System.out.println("MISS!");
				System.out.println();
				Board.PrintBoard(boardPl);
				break;
			} else if (boardCpu[i][j] == shot || boardCpu[i][j] == '•') {
				System.out.println("You shot this place before!");
				System.out.println();
				Board.PrintBoard(boardPl);
			} else if (boardCpu[i][j] == signCpu) {
				boardCpu[i][j] = '•';
				boardPl[i][j] = shot;
				System.out.println("HIT!");
				
				System.out.println();
				
				System.out.println("PLAYER");
				Board.PrintBoard(boardPl);
			}
			
		}
	}
	
	public static void cpuShot(char[][] boardPl, char[][] boardCpu, char signCpu, char signPl) {
		
		char shot = Character.toLowerCase(signPl);
		int i, j;
		int N = boardPl.length;
		
		i = (int) Math.floor(N * Math.random());
		j = (int) Math.floor(N * Math.random());
		System.out.println("CPU i: " + i + " j: " + j);

		
		while(true) {
		
			if (boardPl[i][j] == '~') {
				boardCpu[i][j] = '•';
				System.out.println("MISS at  i: " + i + " j: " + j);
				System.out.println();
				Board.PrintBoard(boardPl);
				break;
			} else if (boardPl[i][j] == shot || boardPl[i][j] == '•') {
				System.out.println("CPU shot this place before! i: " + i + " j: " + j);
				System.out.println();
				Board.PrintBoard(boardCpu);
			} else if (boardPl[i][j] == signPl) {
				boardCpu[i][j] = '•';
				boardPl[i][j] = shot;
				System.out.println("HIT at i: " + i + " j: " + j);
				
				System.out.println();
				
				System.out.println("CPU");
				Board.PrintBoard(boardPl);
			
			}
		}
	}
	
}
