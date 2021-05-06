/**
 * @file BoardT.java
 * @author Sarib Kashif (kashis2)
 * @brief model module for controlling data
 * @date April 12 2021
 */

package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class BoardT {

    private int[][] board;
    private int score;
    private boolean status;
    private boolean trackScore = true;
    private static int size = 4;
    private static int baseNum = 2;

    /**
     * @brief initialize the board and add two random values into the board
     */
    public BoardT()
    {
        status = true;
        score = 0;
        board = new int[size][size];
        addValue();
        addValue();
    }

    /**
     * @brief display a ending message
     * @param b - the game board
     */
    public BoardT(int[][] b)
    {
        status = true;
        score = 0;
        board = b;
    }

    /**
     * @brief add a random value into the board
     * @details checks if there are any empty slots, and then adds the value randomly
     */
    public void addValue() {
        boolean emptySlot = false;
        for (int[] row : board)
            for (int num : row)
                if (num == 0) {
                    emptySlot = true;
                    break;
                }
        if (!emptySlot)
            return;
        Random rand = new Random();
        int[] values = new int[] {baseNum,baseNum,baseNum,baseNum,baseNum,baseNum,baseNum,baseNum,baseNum,2 * baseNum};
        int i = rand.nextInt(size);
        int j = rand.nextInt(size);
        while (this.board[i][j] != 0) {
            i = rand.nextInt(size);
            j = rand.nextInt(size);
        }
        this.board[i][j] = values[rand.nextInt(10)];
    }

    /**
     * @brief checks if winning condition has been met
     * @return true if winning condition is met, false if it is not
     */
    public boolean checkWin()
    {
        for (int[] row : board)
            for (int num : row)
                if (num >= baseNum * 1024)
                    return true;
        return false;
    }

    /**
     * @brief sets the size to provided int
     */
    public static void setSize(int s)
    {
        size = s;
    }

    /**
     * @brief sets the base to provided int
     */
    public static void setBase(int bNum)
    {
        baseNum = bNum;
    }

    /**
     * @return the dimension of board
     */
    public static int getSize() { return size; }

    /**
     * @return the game board
     */
    public int[][] getBoard() { return board; }

    /**
     * @brief calls isPlayable to update the status
     * @return the status
     */
    public boolean getStatus() {
        trackScore = false;
        if (!isPlayable())
            status = false;
        trackScore = true;
        return status;
    }

    /**
     * @return the score
     */
    public int getScore() { return score; }

    /**
     * @brief shifts the board in a certain direction depending on input
     * @param direction - ControlsT object specifying the direction to move the board in
     */
    public void shiftBoard(ControlsT direction)
    {
        if (direction == ControlsT.left)
            shiftLeft();
        if (direction == ControlsT.right)
            shiftRight();
        if (direction == ControlsT.up)
            shiftUp();
        if (direction == ControlsT.down)
            shiftDown();
    }

    private boolean isPlayable()
    {
        int[][] temp = board.clone();
        shiftLeft();
        if (!Arrays.deepEquals(temp, board)) {
            board = temp;
            return true;
        }
        shiftRight();
        if (!Arrays.deepEquals(temp, board)) {
            board = temp;
            return true;
        }
        shiftDown();
        if (!Arrays.deepEquals(temp, board)) {
            board = temp;
            return true;
        }
        shiftUp();
        if (!Arrays.deepEquals(temp, board)) {
            board = temp;
            return true;
        }
        return false;
    }

    private void shiftLeft()
    {
        for (int i = 0; i < size; i++)
        {
            ArrayList<Integer> temp = new ArrayList<>();
            int[] temp2 = new int[size];
            for (int j = 0; j < size; j++)
                if (board[i][j] != 0)
                    temp.add(board[i][j]);
            int x = 0;
            for (int j = 0; j < temp.size(); j++)
            {
                if (j < temp.size() - 1 && temp.get(j).equals(temp.get(j + 1)))
                {
                    temp2[x] = 2 * temp.get(j);
                    if (trackScore)
                        score += temp2[x];
                    j++;
                }
                else
                    temp2[x] = temp.get(j);
                x++;
            }
            board[i] = temp2;
        }
    }


    private void shiftRight()
    {
        for (int i = 0; i < size; i++)
        {
            ArrayList<Integer> temp = new ArrayList<>();
            int[] temp2 = new int[size];
            for (int j = size - 1; j >= 0; j--)
                if (board[i][j] != 0)
                    temp.add(board[i][j]);

            int x = size - 1;
            for (int j = 0; j < temp.size(); j++)
            {
                if (j < temp.size() - 1 && temp.get(j).equals(temp.get(j + 1)))
                {
                    temp2[x] = 2 * temp.get(j);
                    if (trackScore)
                        score += temp2[x];
                    j++;
                }
                else
                    temp2[x] = temp.get(j);
                x--;
            }
            board[i] = temp2;
        }
    }

    private void shiftUp()
    {
        for (int i = 0; i < size; i++)
        {
            ArrayList<Integer> temp = new ArrayList<>();
            int[] temp2 = new int[size];
            for (int j = 0; j < size; j++)
                if (board[j][i] != 0)
                    temp.add(board[j][i]);

            int x = 0;
            for (int j = 0; j < temp.size(); j++)
            {
                if (j < temp.size() - 1 && temp.get(j).equals(temp.get(j + 1)))
                {
                    temp2[x] = 2 * temp.get(j);
                    if (trackScore)
                        score += temp2[x];
                    j++;
                }
                else
                    temp2[x] = temp.get(j);
                x++;
            }
            for (int j = 0; j < size; j++)
                board[j][i] = temp2[j];
        }
    }

    private void shiftDown()
    {
        for (int i = 0; i < size; i++)
        {
            ArrayList<Integer> temp = new ArrayList<>();
            int[] temp2 = new int[size];
            for (int j = size - 1; j >= 0; j--)
                if (board[j][i] != 0)
                    temp.add(board[j][i]);

            int x = size - 1;
            for (int j = 0; j < temp.size(); j++)
            {
                if (j < temp.size() - 1 && temp.get(j).equals(temp.get(j + 1)))
                {
                    temp2[x] = 2 * temp.get(j);
                    if (trackScore)
                        score += temp2[x];
                    j++;
                }
                else
                    temp2[x] = temp.get(j);
                x--;
            }
            for (int j = size - 1; j >= 0; j--)
                board[j][i] = temp2[j];
        }
    }
}
