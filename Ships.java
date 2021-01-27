package nauka;

import java.util.Arrays;
import java.util.Scanner;

public class Ships {
	
	public static int[] generatePos(int L, int LMax, int[][] MapHor, int[][] MapVer) {
		int[] pos = new int[3];
		int N = MapHor.length;
		int horVer = 0;
		int i = 0;
		
		if (L <= LMax) {
			
			while(true) {	
				
				horVer = (int) Math.floor(2 * Math.random());
				
				i = (int) Math.floor(N * Math.random());
				
				if (horVer == 0) {
					if (MapHor[i][1] >= L) {

						pos[0] = MapHor[i][2];
						pos[1] = MapHor[i][3];
						pos[2] = 0;
						break;
					}
					
				} else if (horVer == 1) {
					
						if (MapVer[i][1] >= L) {
					
						pos[0] = MapVer[i][2];
						pos[1] = MapVer[i][3];
						pos[2] = 1;
						break;
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
	
	public static int playerShot(char[][] boardPlHits, char[][] boardCpu, char signCpu, char signPl, int plHits, int hitsNum) {
		
		char shot = Character.toLowerCase(signCpu);
		Scanner sc = new Scanner(System.in);
		int i, j;
		int n = 0;
		boolean game = true;
	
		while (game) {
			while (true) {
				System.out.println("PLAYER HITS (" + plHits + "/" + hitsNum + ")");
				Board.PrintBoard(boardPlHits);
				System.out.println("Give shot position i: ");
				i = validateNumber(boardCpu.length);
				
				System.out.println("Give shot position j: ");
				j = validateNumber(boardCpu.length);	
				
				if ( i >= 0 & j >= 0 & i <= 9 & j <= 9) {
					break;
				}
				System.out.println("Wrong coorinates");
				System.out.println();
			}
			
			if (boardCpu[i][j] == '~' && boardPlHits[i][j] == '~') {
				boardPlHits[i][j] = '•';
				System.out.println("MISS!");
				System.out.println();
				break;
			} else if (boardPlHits[i][j] == shot || boardPlHits[i][j] == '•') {
				System.out.println("You shot this place before!");
			} else if (boardCpu[i][j] == signCpu) {
				boardPlHits[i][j] = shot;
				System.out.println("HIT!");
				System.out.println();
				plHits++;

			}
			
			if (plHits == hitsNum) {
				System.out.println();
				System.out.println();
				System.out.println("PLAYER WIN!!!");
				game = false;
				break;
			}
			
		}
		return plHits;
	}
	
	public static int cpuShot(char[][] boardCpuHits, char[][] boardPl, char signCpu, char signPl, int cpuHits, int hitsNum) {
		
		char shot = Character.toLowerCase(signPl);
		int i, j;
		int N = boardPl.length;
		boolean game = true;
		
		while(game) {
			
			i = (int) Math.floor(N * Math.random());
			j = (int) Math.floor(N * Math.random());
		
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if (boardPl[i][j] == '~' && boardCpuHits[i][j] == '~') {
				boardCpuHits[i][j] = '•';
				boardPl[i][j] = '•';
				System.out.println("CPU MISS at  i: " + i + " j: " + j);
				System.out.println();
				System.out.println("CPU HITS (" + cpuHits + "/" + hitsNum + ")");
				System.out.println();
				break;
			} else if (boardCpuHits[i][j] == shot || boardCpuHits[i][j] == '•') {
				System.out.println("CPU shot this place before! i: " + i + " j: " + j);	
			} else if (boardPl[i][j] == signPl) {
				boardCpuHits[i][j] = shot;
				boardPl[i][j] = '•';
				System.out.println("CPU HIT at i: " + i + " j: " + j);
				System.out.println();
				cpuHits++;
				System.out.println("CPU HITS (" + cpuHits + "/" + hitsNum + ")");
				System.out.println();
			}
			
			if (cpuHits == hitsNum) {
				System.out.println();
				System.out.println();
				System.out.println("CPU WON!!!");
				game = false;
				break;
			}
		}
		return cpuHits;
	}
	
	 public static int validateNumber(int N) {
	        Scanner scanner = new Scanner(System.in);

	        int number;
	        do {
	            while (!scanner.hasNextInt()) {
	                String input = scanner.next();

	            }
	            number = scanner.nextInt();
	        } while (number < 0 || number > N - 1);

	        return number;
	    }
	
}
