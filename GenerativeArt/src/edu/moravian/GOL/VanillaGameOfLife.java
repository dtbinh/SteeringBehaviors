
package edu.moravian.GOL;

/**
 *
 * @author James Moore (moore.work@live.com)
 */
public class VanillaGameOfLife extends GameOfLife {

    @Override
    protected boolean[][] runOneStep(boolean[][] world)
      {
       
        boolean[][] nextWorld = new boolean[world.length][];
        for (int x = 0; x < world.length; x++) {
            nextWorld[x] = new boolean[world[x].length];
            nextWorld[x] = world[x].clone();
        }



        for (int x = 0; x < world.length; x++) {
            for (int y = 0; y < world[x].length; y++) {

                int ne = countLivingNeighbors(world, x, y);

                if (world[x][y] == true) {
                    if (ne < STARVE_THRESHOLD) {
                        nextWorld[x][y] = false;
                    }

                    if (ne > OVERCROWDING_THRESHOLD) {
                        nextWorld[x][y] = false;
                    }
                } else {
                    if (ne == RESURRECTION_AMOUNT) {
                        nextWorld[x][y] = true;
                    }
                }
            }
        }
        return nextWorld;
      }

}
