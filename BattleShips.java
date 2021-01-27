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
			int Cno = 0;
			int Bno = 0;
			int Dno = 0;
			int Pno = 0;
			int Sno = 1;
			
			//players signs, please use uppercase
			char signPl = 'X';
			char signCpu = 'O';
			
			//sum of hits for each player
			int plHits = 0;
			int cpuHits = 0;
			
			//amount of possible hits (steers when game ends / who won)

			int hitsNum = Clength * Cno + Blength * Bno + Dlength * Dno + Plength * Pno + Slength * Sno;

		
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
			
			System.out.println("CPU");
			Board.PrintBoard(boardCpu);
			
			//actual game
			while (plHits < hitsNum && cpuHits < hitsNum) {
//				Ships.playerShot(boardPlHits, boardCpu, signCpu, signPl);
//				Ships.cpuShot(boardCpuHits, boardPl, signCpu, signPl);
				
				System.out.println("PLAYER");
				Board.PrintBoard(boardPl);
				

				
				plHits += Ships.playerShot(boardPlHits, boardCpu, signCpu, signPl, plHits, hitsNum);
				
				if (plHits == hitsNum || cpuHits == hitsNum) {
					break;
				}
				
				cpuHits += Ships.cpuShot(boardCpuHits, boardPl, signCpu, signPl, cpuHits, hitsNum);

			}
			
		}
}
