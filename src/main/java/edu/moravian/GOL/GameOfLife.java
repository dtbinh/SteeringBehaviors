package edu.moravian.GOL;

import edu.moravian.math.Point2D;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.me.steeringbehaviors.RunnableSim;
import com.me.steeringbehaviors.Settings;
import com.me.steeringbehaviors.WorldGraphics2D;

/**
 *
 * @author James Moore (moore.work@live.com)
 */
public class GameOfLife implements RunnableSim {
//TODO refactor board sizes;  

    private static final int BOARD_WIDTH = 320;
    private static final int BOARD_HEIGHT = 200;
    static final int STARVE_THRESHOLD = 2;
    static final int RESURRECTION_AMOUNT = 3;
    static final int OVERCROWDING_THRESHOLD = 3;
    private static final double SPAWN_THRESHHOLD = .4;
    private static final int ITERATION_COUNT = 1000;
    private static boolean[][] board;
    private GraphicsEnvironment env;
    private GraphicsDevice device;
    private GraphicsConfiguration config;
    private BufferedImage buff;
    private static final boolean DRAW_LINES = true;

//TODO do a better world clone
    public GameOfLife() {
        env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        if (env.isHeadlessInstance()) {
            buff = new BufferedImage(1024, 1024, BufferedImage.TYPE_INT_RGB);
        } else {
            Dimension screenSize = Settings.getInstance().getResolution();
            device = env.getDefaultScreenDevice();
            config = device.getDefaultConfiguration();
            buff = config.createCompatibleImage(screenSize.width, screenSize.height, BufferedImage.TYPE_INT_RGB);

        }
    }

    public GameOfLife(int width, int height) {

    }

    protected boolean[][] runOneStep(boolean[][] world) {

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

//    public static void main(String[] args) {
//        boolean[][] world;
//        double spawnThreshhold = SPAWN_THRESHHOLD;
//        world = new boolean[BOARD_WIDTH][BOARD_HEIGHT];
//        for (int x = 0; x < world.length; x++) {
//            for (int y = 0; y < world[x].length; y++) {
//                if (Math.random() > spawnThreshhold) {
//                    world[x][y] = true;
//                }
//            }
//        }
//
//        outputBoard(world);
//
//        int iters = runGame(world, true);
//    }
    protected static void outputBoard(boolean[][] world) {
        String rep;
        for (int i = 0; i < world.length; i++) {
            for (int j = 0; j < world[i].length; j++) {
                if (world[i][j] == true) {
                    rep = "*";
                } else {
                    //rep = '█';
                    rep = String.valueOf(countLivingNeighbors(world, i, j));
                }
                System.out.printf("%-10s", i + "," + j + ": " + rep);
            }
            System.out.println("");
        }
        System.out.println("-----------------------------------");
    }

    private static int runGame(boolean[][] world_in, boolean b) {
        GameOfLife gol = new GameOfLife();
        int iterations = 0;
        boolean[][] world = world_in.clone();
        boolean[][] nextWorld = world_in.clone();
        boolean output = true;
        while (isBoardDead(world) == false && iterations < ITERATION_COUNT) {
            world = gol.runOneStep(world);
            if (output == true) {
                outputBoard(world);
            }
            iterations += 1;
        }
        return iterations;
    }

    private static boolean[][] generateBoard() {
        boolean[][] board_in = new boolean[BOARD_WIDTH][BOARD_HEIGHT];
        for (int i = 0; i < BOARD_HEIGHT; i++) {
            for (int j = 0; j < BOARD_WIDTH; j++) {
                if (Math.random() > SPAWN_THRESHHOLD) {
                    board_in[j][i] = true;
                } else {
                    board_in[j][i] = false;
                }
            }
        }
        return board_in;
    }

    protected static int countLivingNeighbors(boolean[][] world, int x, int y) {

        int count = 0;
        int xL = x - 1;
        int xR = x + 1;
        int yD = y - 1;
        int yU = y + 1;

        int len = world.length;
        if (withinBoundries(xL, world.length)) {
            count += accountFor(world[xL][y]);
            if (withinBoundries(y, world[xL].length)) {
                count += accountFor(world[xL][yU]);
            }
            if (withinBoundries(yD, world[xL].length)) {
                count += accountFor(world[xL][yD]);
            }
        }

        if (withinBoundries(xR, world[x].length)) {
            count += accountFor(world[xR][y]);
            if (withinBoundries(yU, world[x].length)) {
                count += accountFor(world[xR][yU]);
            }
            if (withinBoundries(yD, world[x].length)) {
                count += accountFor(world[xR][yD]);
            }
        }

        if (withinBoundries(yU, world[x].length)) {
            count += accountFor(world[x][yU]);
        }

        if (withinBoundries(yD, world[x].length)) {
            count += accountFor(world[x][yD]);
        }

        return count;
    }

    private static boolean withinBoundries(int num, int upperBound) {
        boolean withinLeft = num == Math.abs(num);
        boolean withinRight = num < (upperBound - 1);

        return withinLeft && withinRight;
    }

    private static int accountFor(boolean board) {
        if (board == true) {
            return 1;
        } else {
            return 0;
        }
    }

    protected static boolean isBoardDead(boolean[][] world) {
        boolean status = true;
        for (int x = 0; x < world.length; x++) {
            for (int y = 0; y < world[x].length; y++) {
                if (world[x][y] == true) {
                    status = false;
                    break;
                }
            }
        }
        return status;
    }

    @Override
    public void draw(WorldGraphics2D w2d) {
        Dimension res = w2d.getRes();

        int yOffset = (int) (res.height / (double) BOARD_HEIGHT);
        int xOffset = (int) (res.width / (double) BOARD_WIDTH);
        // System.out.println((xOffset) * BOARD_WIDTH);
        int xSideOffset;
        if (res.width % BOARD_WIDTH == 0) {
            xSideOffset = 0;
        } else {
            xSideOffset = res.width / BOARD_WIDTH;
        }

        if (board == null) {
            board = generateBoard();
        }

        board = runOneStep(board);

        for (int i = 0; i < BOARD_WIDTH; i++) {

            for (int j = 0; j < BOARD_HEIGHT; j++) {
                Color renderColor;

                if (board[i][j]) {
                    renderColor = Color.blue;
                } else {
                    renderColor = Color.lightGray;
                }

                if (DRAW_LINES) {
                    //  w2d.drawRectangle(new Point2D(i * xOffset, 0), new Dimension(1, res.height + yOffset), Color.black);
                    // w2d.drawRectangle(new Point2D(0, j * yOffset), new Dimension(res.width + xOffset, 1), Color.black);
                }

                w2d.drawRectangle(new Point2D(i * xOffset + xSideOffset, j * yOffset), new Dimension(xOffset, yOffset), renderColor);
            }
        }

//        try
//        {
//            Thread.sleep(500);
//        }
//        catch (InterruptedException ex)
//        {
//            Logger.getLogger(GameOfLife.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    @Override
    public void update() {
    }

    private static boolean[][] cloneBoard(boolean[][] board) {
        boolean[][] newBoard = new boolean[board.length][];

        for (int x = 0; x < board.length; x++) {
            boolean[] src = board[x];
            boolean[] recp = new boolean[src.length];
            System.arraycopy(src, 0, recp, 0, src.length);
        }
        return newBoard;
    }
}
//TODO do better unit testing
