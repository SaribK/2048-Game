/**
 * @file Display.java
 * @author Sarib Kashif (kashis2)
 * @brief View module for printing to the console
 * @date April 12 2021
 */

package src;

public class Display {

    private static Display visual = null;

    private Display() {}

    /**
     * @brief creates the Display type if it is currently null, otherwise returns the instance
     * @details follows singleton pattern because only one is created for this type
     * @return instance of the Display type that was created once
     */
    public static Display getInstance(){
        if (visual == null)
            return visual = new Display();
        return visual;
    }

    /**
     * @brief display a prompt naming the game as a welcome message
     */
    public void printWelcomeMessage(){
        System.out.println("-------------------------------------------------");
        System.out.println("                 Welcome to 2048                 ");
        System.out.println("-------------------------------------------------");
    }

    /**
     * @brief display a prompt providing the user with instructions on how to play
     */
    public void printInstructionsPrompt(){
        System.out.println("                     New game                    ");
        System.out.println("                 -----------------               ");
        System.out.println("");
        System.out.println("Enter any key to start");
        System.out.println("Once the game starts, press r to reset, e to exit");
        System.out.println("Use wasd to shift up, left, down, and right respectively");
    }

    /**
     * @brief display a prompt providing the user to give a size
     */
    public void printSizePrompt(){
        System.out.println("Enter a size for the board dimensions that is between 3 and 15, inclusive");
    }

    /**
     * @brief display a prompt providing the user with instructions on how to play
     */
    public void printBasePrompt(){
        System.out.println("Enter the base value for the board that is greater than 1");
    }

    /**
     * @brief display the winning message
     */
    public void printWinningMessage(){
        System.out.println("You have reached the winning condition!!");
    }

    /**
     * @brief Display the board on the screen
     * @details separate the elements with '||'. If element length gets longer
     * more spaces are added for smaller elements to compensate spacing.
     * @param model - the game board
     */
    public void printBoard(BoardT model){
        int max = maxLength(model.getBoard());
        for (int[] row : model.getBoard())
        {
            for (int num : row)
            {
                System.out.print("||");
                int numLength = max - Integer.toString(num).length() + 1;
                if (max % 2 == 0)
                    addSpaces(max - 1);
                else
                    addSpaces(max);
                if (num == 0)
                    System.out.print(" ");
                else
                    System.out.print(num);
                addSpaces(numLength);
            }
            System.out.print("||");
            System.out.println();
        }
    }

    /**
     * @brief display the score on the screen
     * @param model - the game board
     */
    public void printScore(BoardT model){
        System.out.println("Current Score: " + model.getScore());
    }

    /**
     * @brief display an ending message after user choose to exit the game
     * or no more moves can be made
     */
    public void printEndingMessage(){
        System.out.println("-------------------------------------------------");
        System.out.println("             Thank You For Playing !!!           ");
        System.out.println("-------------------------------------------------");
    }

    private void addSpaces(int num)
    {
        for (int i = 0; i < num; i++)
            System.out.print(" ");
    }

    private int maxLength(int[][] board)
    {
        int max = 1;
        for (int[] row : board)
            for (int num : row)
                if (Integer.toString(num).length() > max)
                    max = Integer.toString(num).length();
        return max;
    }
}
