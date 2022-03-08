import javax.swing.*;

// +1 point
public class Hangman {

    // +2 points
    public static String word;
    public static String guessedWord;

    // +3 points
    public static void getWord() {
        word = getUserInput("Hello, Hangman! Enter a word for us!");
        if(word.isEmpty()) {
            word = getUserInput("Hello, Hangman! Please try entering a word."); // passive aggressive much?
        }
    }

    // +4 points, this method should give you +2 bonus points!!!! >:(
    // It's efficient!
    public static void setGuessedWord() {
        guessedWord = new String(new char[word.length()]).replace("\0", "-"); // \0 is anything, so replace everything with -s.
    }

    // +1 point
    public static void main(String[] args) {
        boolean GameOn = true;
        // +1 point
        getWord();
        // +1 point
        setGuessedWord();

        // +2 points...
        guessedWord = guess();

        // +3 points...
        String option = "y".toLowerCase(); // it's a cheat to use the while loop below.
        int guesses = 1; // we already guessed on line 32.
        if ((option == "y" || option == "Y" ) && GameOn) {
            do {
                option = JOptionPane.showInputDialog(null, "You have guessed the letters: " + guessedWord + "\nDo you want to guess again? Y/N", "y");

                if (option == "n" || option == "N") {
                    break;
                }

                guessedWord = guess();
                guesses++;

                if (!guessedWord.contains("-")) {
                    GameOn = false;
                }
            } while (option.toLowerCase() == "y" && GameOn);
        }

        // +4 points, we just... added some flavor, we are checking if the user wins too, BONUS MARKS!!!! ffs.
        if (!GameOn) {
            JOptionPane.showMessageDialog(null, "Congratulations! You have guessed all the letters.\nThe word was: " + guessedWord);
        } else {
            JOptionPane.showMessageDialog(null, "You have guessed the letters: " + guessedWord + "\nNumber of guesses you made: " + guesses + "\nThank you for playing Hangman!" );
        }

    }

    // +7 points here..
    // This getUserInput variant, only takes 1 char and will loop asking for a single letter till the user only gives
    // 1 letter and then that's cast to a char and returned.
    public static String guess() {
        String guess; // declare guess
        while((guess=getUserInput("Guess a single letter!:")).length() != 1); // keeping asking for no less/more than 1 letter.

        // The while loop above will make sure it's only 1 letter.
        // Below, set a newGuessedWord string, add the guessed letter to the spots where it matches and add the already
        // guessed letters from guessedWord to their spots where they should go (that's when it's not a - in guessedWord)
        // then add -'s every other case.
        String newGuessedWord = "";
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == guess.charAt(0)) {
                newGuessedWord += word.charAt(i);
            } else if (guessedWord.charAt(i) != '-') {
                newGuessedWord += guessedWord.charAt(i);
            } else {
                newGuessedWord += '-';
            }
        }

        return newGuessedWord;
    }

    // Generic getUserInput method
    public static String getUserInput(String message) {
        return JOptionPane.showInputDialog(message);
    }
}