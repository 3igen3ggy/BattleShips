package nauka;

import java.util.Arrays;

public class BattleShips {

		public static void main(String[] args) { 
			
			int N = 10;
	
			char[][] boardPl = new char[N][N];	
			char[][] boardCpu = new char[N][N];
			char[][] boardPlHits = new char[N][N];	
			char[][] boardCpuHits = new char[N][N];
			char[][] avPosAux = new char[N + 2][N + 2];
			
			boardPl = Board.CreateBoard(N);
			boardCpu = Board.CreateBoard(N);
			boardPlHits = Board.CreateBoard(N);
			boardCpuHits = Board.CreateBoard(N);
				
			// 5 Carrier
			// 4 Battleship
			// 3 Destroyer
			// 2 Patrol
			// 1 Submarine
			
			int Clength  = 5;
			int Blength = 4;
			int Dlength = 3;
			int Plength = 2;
			int Slength = 1;
			
			
			//how many of each battleship per player
			int Cno = 1;
			int Bno = 2;
			int Dno = 3;
			int Pno = 4;
			int Sno = 5;
			
			//players signs, please use uppercase
			char signPl = 'X';
			char signCpu = 'O';

			

		
			//Spawning BattleShips
	
			Ships.spawn(boardPl, Clength, Cno, signPl);
			Ships.spawn(boardCpu, Clength, Cno, signCpu);
			Ships.spawn(boardPl, Blength, Bno, signPl);
			Ships.spawn(boardCpu, Blength, Bno, signCpu);		
			Ships.spawn(boardPl, Dlength, Dno, signPl);
			Ships.spawn(boardCpu, Dlength, Dno, signCpu);		
			Ships.spawn(boardPl, Plength, Pno, signPl);
			Ships.spawn(boardCpu, Plength, Pno, signCpu);
			Ships.spawn(boardPl, Slength, Sno, signPl);
			Ships.spawn(boardCpu, Slength, Sno, signCpu);
			
			System.out.println("PLAYER");
			Board.PrintBoard(boardPl);
			
			System.out.println("CPU");
			Board.PrintBoard(boardCpu);		

			Ships.playerShot(boardPlHits, boardCpu, signCpu, signPl);
			Ships.cpuShot(boardCpuHits, boardPl, signCpu, signPl);
			
			System.out.println("PLAYER");
			Board.PrintBoard(boardPl);
			
			System.out.println("CPU");
			Board.PrintBoard(boardCpu);
			
			
		}
}
