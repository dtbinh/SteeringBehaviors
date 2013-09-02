/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.moravian.GOL;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Bryan
 */
public class GameOfLifeTest {

    GameOfLife gol;

    public GameOfLifeTest() {
        gol = new GameOfLife();
    }

    @Test
    public void ruleOneTest() {
        boolean[][] board = new boolean[4][4];
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                board[x][y] = false;
            }
        }
        board[2][2] = true;

        board = GameOfLife.runOneStep(board);

        assertTrue(GameOfLife.isBoardDead(board));

    }

    @Test
    public void testBoardLife() {
        boolean[][] board = new boolean[5][5];
        for (boolean[] bool : board) {
            for (boolean boolElement : bool) {
                boolElement = false;
            }
        }
        assertTrue(GameOfLife.isBoardDead(board));


        for (boolean[] bool : board) {
            for (boolean boolElement : bool) {
                boolElement = false;
            }
        }

        board[0][1] = true;

        assertFalse(GameOfLife.isBoardDead(board));

        for (boolean[] bool : board) {
            for (boolean boolElement : bool) {
                boolElement = true;
            }
        }

        assertFalse(GameOfLife.isBoardDead(board));


    }

    @Test
    public void testNearNeighbors() {
        boolean[][] board = new boolean[5][5];
        for (boolean[] bool : board) {
            for (boolean boolElement : bool) {
                boolElement = false;
            }
        }
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                assertEquals(0, GameOfLife.countLivingNeighbors(board, x, y));
            }
        }
        board[2][2] = true;

        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                if (x > 0 && x <= 3 && y > 0 && y <= 3) {
                    //System.out.printf("assertEquals(false, board[%d][%d]);", x, y);
                    //System.out.println(x + " " + y);
                    if (x != 2 && y != 2) {
                        assertEquals(1, GameOfLife.countLivingNeighbors(board, x, y));
                    }
                } else {
                    if (x == 1 && y == 3) {
                        System.out.println("fo");
                    }
                    //      System.out.println(x + " " + y);
                    assertEquals(0, GameOfLife.countLivingNeighbors(board, x, y));
                }
            }
        }

        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                board[x][y] = false;
            }
        }
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                // System.out.println( x+ " "+ y);
                assertFalse(board[x][y]);
            }
        }


    }

    @Test
    public void testNearNeighborsCorners() {
                boolean[][] board = new boolean[5][5];
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                board[x][y] = false;
            }
        }
        board[2][1] = true;
        board[3][3] = true;
        board[1][3] = true;
        
        
        assertEquals(0, GameOfLife.countLivingNeighbors(board, 4, 0));
        assertEquals(0, GameOfLife.countLivingNeighbors(board, 4,1));
        assertEquals(1, GameOfLife.countLivingNeighbors(board, 4,2));
        assertEquals(2, GameOfLife.countLivingNeighbors(board, 2,4));
        
    }

    @Test
    public void testStarvationDeathOneElement() {
        boolean[][] board = new boolean[5][5];
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                board[x][y] = false;
            }
        }
        board[1][1] = true;

        board = GameOfLife.runOneStep(board);
        assertTrue(GameOfLife.isBoardDead(board));
    }

    @Test
    public void testStarvationDeathMultipleElements() {
        boolean[][] board = new boolean[5][5];
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                board[x][y] = false;
            }
        }
        board[1][1] = true;
        board[4][4] = true;

        board = GameOfLife.runOneStep(board);
        assertTrue(GameOfLife.isBoardDead(board));
    }

    @Test
    public void testResurrectionSimple() {
        boolean[][] board = new boolean[5][5];
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                board[x][y] = false;
            }
        }
        board[2][1] = true;
        board[3][3] = true;
        board[1][3] = true;
       // GameOfLife.outputBoard(board);

        board = GameOfLife.runOneStep(board);
  
        assertFalse(GameOfLife.isBoardDead(board));

        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                if (x == 2 && y == 2) {
                    assertTrue(board[x][y]);
                } else {
                    assertFalse(board[x][y]);
                }
            }
        }
        
        board = GameOfLife.runOneStep(board);
        
        assertTrue(GameOfLife.isBoardDead(board));
    }
}
