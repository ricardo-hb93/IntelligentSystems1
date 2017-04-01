package fnl;

import java.util.Random;

public class MapGenerator {
	public static boolean[][] gen(int size, int numBots, int seed){
		Random rand = new Random(seed);
		boolean[][] map = new boolean[size][size];
		int x, y;
		
		for (int j = 1; j < numBots; j++) {
			x = rand.nextInt();
			x = Math.abs(x);
			y = (x/size)%size;
			x%=size;
			boolean done = false;
			while (!done) {
				if(map[x][y]){
					 x = rand.nextInt();
					 x = Math.abs(x);
					 y = (x/size)%size;
					 x%=size;
				}else{
					done=true;
					map[x][y]=true;
				}
			}
		}
		
		return map;
	}
	
	public static void showMap(boolean[][] map){
		for (int i=0; i<map.length; i++){
			for(int j=0; j<map.length; j++){
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
}
