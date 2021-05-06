/**
 * @file GameController.java
 * @author Sarib Kashif (kashis2)
 * @brief Controller module for controlling the game
 * @date April 12 2021
 */

package src;

import java.util.Arrays;
import java.util.Scanner;

public class GameController{

    private BoardT model;
    private Display view;
    private static GameController controller = null;

    private final Scanner keyboard = new Scanner(System.in);

    private GameController(BoardT board, Display display)
    {
        model = board;
        view = display;
    }

    /**
     * @brief creates the GameController type if it is currently null, otherwise returns the instance
     * @details follows singleton pattern because only one is created for this type
     * @return instance of the GameController type that was created once
     */
    public static GameController getInstance(BoardT model, Display view)
    {
        if (controller == null)
            controller = new GameController(model, view);
        return controller;
    }

    /**
     * @brief initialize the BoardT object
     */
    public void initializeGame(){
        this.model = new BoardT();
    }

    /**
     * @brief reads user input
     * @return returns the input
     */
    public String readInput(){
        String input;
        input = keyboard.nextLine();
        return input;
    }

    /**
     * @return gets the game status from the board
     */
    public boolean getStatus(){
        return this.model.getStatus();
    }

    /**
     * @brief display a welcome message
     */
    public void displayWelcomeMessage(){
        view.printWelcomeMessage();
    }

    /**
     * @brief display the board
     */
    public void displayBoard(){
        view.printBoard(model);
    }

    /**
     * @brief display a ending message
     */
    public void displayEnding(){
        view.printEndingMessage();
    }

    /**
     * @brief display score
     */
    public void displayScore() { view.printScore(model); }

    /**
     * @brief displays the instructions for how the game is played
     */
    public void displayInstructionsPrompt(){
        view.printInstructionsPrompt();
    }

    /**
     * @brief runs the game
     */
    public void runGame(){
        boolean setUp = false;
        String input = "";
        displayWelcomeMessage();
        displayInstructionsPrompt();
        readInput();
        while (!setUp)
        {
            try
            {
                view.printSizePrompt();
                input = readInput();
                int size = Integer.parseInt(input);
                if (size > 2 && size < 15)
                {
                    BoardT.setSize(size);
                    setUp = true;
                } else
                    System.out.println("Invalid input");
            }
            catch (NumberFormatException e){
                System.out.println("Must input an integer value");
            }
        }
        setUp = false;
        while (!setUp)
        {
            try
            {
                view.printBasePrompt();
                input = readInput();
                int base = Integer.parseInt(input);
                if (base > 1)
                {
                    BoardT.setBase(base);
                    setUp = true;
                } else
                    System.out.println("Invalid input");
            }
            catch (NumberFormatException e){
                System.out.println("Must input an integer value");
            }
        }
        boolean printWin = true;
        initializeGame();
        input = " ";
        while(getStatus() && !(input.equals("r"))){
            if (model.checkWin() && printWin)
            {
                view.printWinningMessage();
                printWin = false;
            }
            System.out.println();
            displayScore();
            displayBoard();
            int[][] temp = copyArray();
            try{
                input = readInput();
                if (input.equals("a"))
                    model.shiftBoard(ControlsT.left);
                else if (input.equals("d"))
                    model.shiftBoard(ControlsT.right);
                else if (input.equals("s"))
                    model.shiftBoard(ControlsT.down);
                else if (input.equals("w"))
                    model.shiftBoard(ControlsT.up);
                else if (input.equals("e") || input.equals("r"))
                    break;
                else
                    throw new IllegalArgumentException();
                if (canAdd() && !Arrays.deepEquals(temp, model.getBoard()))
                    model.addValue();
            }
            catch (IllegalArgumentException e){
                System.out.println("Invalid Input");
            }
        }
        if (input.equals("r"))
            runGame();
        displayEnding();
        System.exit(0);
    }

    private boolean canAdd()
    {
        int[][] b = model.getBoard();
        for (int[] row : b)
            for (int num : row)
                if (num == 0)
                    return true;
        return false;
    }

    private int[][] copyArray()
    {
        int[][] temp = new int[BoardT.getSize()][BoardT.getSize()];
        for (int i = 0; i < BoardT.getSize(); ++i)
            for (int j = 0; j < BoardT.getSize(); ++j)
                temp[i][j] = model.getBoard()[i][j];
        return temp;
    }
}
