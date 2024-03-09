package efs.task.syntax;
import java.util.Random;
import java.util.Scanner;
public class GuessNumberGame {

    public int M;
    public int L;
    public int attempts;
    public int randomNumber;
    public String progressBar;


    //Do not modify main method
    public static void main(String[] args) {
        try {
            GuessNumberGame game = new GuessNumberGame(args.length > 0 ? args[0] : "");
            game.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GuessNumberGame(String argument) {

        try {
            this.M = Integer.parseInt(argument);
            if (M > UsefulConstants.MAX_UPPER_BOUND || M < 1) {
                System.out.println(UsefulConstants.WRONG_ARGUMENT);
                throw new java.lang.IllegalArgumentException("Not in range");
            }
        }
        catch (NumberFormatException e) {
            System.out.println(UsefulConstants.WRONG_ARGUMENT);
            throw new java.lang.IllegalArgumentException("Wrong format");
        }

        this.L = (int) ((Math.log(M) / Math.log(2)) + 1);
        this.attempts = 0;
        Random rand = new Random();
        this.randomNumber = rand.nextInt(M) + 1;

        progressBar = "[";
        for(int i = 0; i < L; i++) {
            progressBar = progressBar.concat(".");
        }
        progressBar = progressBar.concat("]");
    }
    public void play() {
        Scanner input = new Scanner(System.in);

        System.out.println("Let's start our game\nGuess the number in range: <1," + M + ">");
        int playerGuess = 0;

        while(attempts < L) {
            System.out.println("Your attempts: " + progressBar);
            System.out.println(UsefulConstants.GIVE_ME);
            try {
                playerGuess = input.nextInt();
            }
            catch (NumberFormatException e) {
                System.out.println(UsefulConstants.NOT_A_NUMBER);
            }
            if (playerGuess > randomNumber) {
                System.out.println(UsefulConstants.TO_MUCH);
                attempts++;
            }
            else if (playerGuess < randomNumber) {
                System.out.println(UsefulConstants.TO_LESS);
                attempts++;
            }
            else {
                System.out.println(UsefulConstants.YES);
                attempts++;
                break;
            }
            progressBar = progressBar.substring(0, attempts) + "*" + progressBar.substring(attempts + 1);
        }

        if(attempts < L) {
            System.out.println(UsefulConstants.CONGRATULATIONS);
        }
        else {
            System.out.println(UsefulConstants.UNFORTUNATELY);
        }
    }
}
