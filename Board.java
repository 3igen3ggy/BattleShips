package nauka;

import java.util.Arrays;

public class Board {
	
	public static char[][] CreateBoard(int N) {
		
		char[][] board = new char[N][N];
		int i = 0;
		int j = 0;
		
		for (i = 0; i < N; i++) {
			for (j = 0; j < N; j++) {
				board[i][j] = '~';
			}
			j = 0;
		}
		
		return board;
	}
	
	public static void PrintBoard(char[][] board) {
		
		int i = 0;
		int j = 0;
		int N = board.length;
		
		
		System.out.println();
		System.out.print("~");
		for (i = 0; i < N; i++) {
			System.out.print(" " + i );
		}
		System.out.println();
		
		for (i = 0; i < N; i++) {
			System.out.print(i);
			for (j = 0; j < N; j++) {
				System.out.print(" " + board[i][j]);
			}
			System.out.println();
			j = 0;
		}
		System.out.println();
	}
	
	public static void PrintBoardTest(boolean[][] board) {
		
		int i = 0;
		int j = 0;
		int N = board.length;
		
		System.out.println();
		
		for (i = 0; i < N; i++) {
			for (j = 0; j < N; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
			j = 0;
		}
	}
	
	public static char[][] GenPosAux(char[][] board) {
		
		int i = 0;
		int j = 0;
		int N = board.length;
		
		char[][] avPosAux = new char[N + 2][N + 2];
		
		for (i = 0; i < N + 2; i++) {
			avPosAux[i][0] = '~';
			avPosAux[i][N + 1] = '~';
			avPosAux[0][i] = '~';
			avPosAux[N + 1][i] = '~';

		}
		
		for (i = 0; i < N; i++) {
			for (j = 0; j < N; j++) {
				
				avPosAux[i + 1][j + 1] = board[i][j];
			}
		}
		
	return avPosAux;
	}
	
	public static boolean[][] avPos(char[][] avPosAux) {
		
	int N = avPosAux.length - 2;
		
	boolean[][] avPos = new boolean[N][N];
		
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			avPos[i][j] = true;
		}
	}	
		
	for (int i = 0; i < N; i++) {
			
		for (int j = 0; j < N; j++) {
				
			if (avPosAux [i + 1][j+ 1] == '~' && avPosAux[i][j] == '~' && avPosAux[i + 1][j] == '~' && avPosAux[i + 2][j] == '~' && avPosAux[i][j + 1] == '~' && avPosAux[i + 2][j + 1] == '~' && avPosAux[i][j + 2] == '~' && avPosAux[i + 1][j + 2] == '~' && avPosAux[i + 2][j + 2] == '~') {	
				avPos[i][j] = true;
			} else {
				avPos[i][j] = false;
					
			}
				
		}
			
	}
	
	return avPos;
	}
	
	public static void testAvPos(boolean[][] avPos) {
		
	int N = avPos.length;
		
	for (int i = 0; i < N; i++) {
			
		for (int j = 0; j < N; j++) {
			
			if (avPos[i][j] == true) {
					
				System.out.print('v');
					
			} else {
					
				System.out.print('x');
			}
				
				System.out.println();
				
			}
		}
		
	}
	
	public static int howManyAv(boolean[][] avPos) {
		
		int howManyAv = 0;
		
		for (boolean c[] : avPos) {
			for (boolean d : c)
				if (d == true) {
					howManyAv++;
				}
		}	
		return howManyAv;
	}
	
	public static int maxPosAlong(boolean[][] avPos) {
		
		
		int maxHor = 0;
		int maxVer = 0;
		int num = 0;
		int N = avPos.length;
		
		//largest gap horizontally
		for (int i = 0; i < N; i++) {
			num = 0;
			for (int j = 0; j < N; j++) {
				if (avPos[i][j] == true) {
					num++;
					if (num > maxHor) {
						maxHor = num;
					} 
				} else {
					num = 0;
				}
			}
		}
		
		//largest gap vertically
		for (int i = 0; i < N; i++) {
			num = 0;
			for (int j = 0; j < N; j++) {
				if (avPos[j][i] == true) {
					num++;
					if (num > maxHor) {
						maxVer = num;
					} 
				} else {
					num = 0;
				}
			}
		}
		
		//return max gap
		if (maxHor >= maxVer) {
			return maxHor;
		} else {
			return maxVer;
		}
		
	}
	
	public static int[][] spawnMapHor(boolean[][] avPos) {
		int N = avPos.length;
		int[][] spawnMapHor = new int[N * N][4];	
		int num = 0;
		int No = 0;
		
		//horizontal gaps map
		for (int i = 0; i < N; i++) {
			num = 0;
			for (int j = 0; j < N; j++) {
				if (avPos[i][j] == true) {
					num++;
					spawnMapHor[No][0] = No + 1;
					spawnMapHor[No][1] = num;
					spawnMapHor[No][2] = i;
					spawnMapHor[No][3] = j;
					No++;
				} else {
					num = 0;
					spawnMapHor[No][0] = No + 1;
					spawnMapHor[No][1] = num;
					spawnMapHor[No][2] = i;
					spawnMapHor[No][3] = j;
					No++;
				}
			}
		}
		return spawnMapHor;
	}
	
	public static int[][] spawnMapVer(boolean[][] avPos) {
		int N = avPos.length;
		int[][] spawnMapVer = new int[N * N][4];
		int num = 0;
		int No = 0;
		
		//vertical gaps map
		for (int j = 0; j < N; j++) {
			num = 0;
			for (int i = 0; i < N; i++) {
				if (avPos[i][j] == true) {
					num++;
					spawnMapVer[No][0] = No + 1;
					spawnMapVer[No][1] = num;
					spawnMapVer[No][2] = i;
					spawnMapVer[No][3] = j;
					No++;
				} else {
					num = 0;
					spawnMapVer[No][0] = No + 1;
					spawnMapVer[No][1] = num;
					spawnMapVer[No][2] = i;
					spawnMapVer[No][3] = j;
					No++;
				}
			}
		}
		return spawnMapVer;
	}
}
