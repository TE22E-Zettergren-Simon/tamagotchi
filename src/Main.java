import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final Scanner in = new Scanner(System.in);
    private static Tamagotchi tamagotchi;

    public static void main(String[] args) {
        System.out.print("Name your Tamagotchi > ");
        var name = in.nextLine();

        tamagotchi = new Tamagotchi(name);

        while (true) {
            tamagotchi.printStats();
            System.out.println();

            boolean shouldExit;

            if (tamagotchi.isAlive()) {
                shouldExit = aliveMenu();
            } else {
                shouldExit = deadMenu();
            }

            if (shouldExit) break;

            System.out.println("\n");
        }

        System.out.println("Goodbye");
    }

    private static boolean aliveMenu() {
        var choices = new String[]{
                "Feed",
                "Speak",
                "Teach",
                "Wait",
                "Abandon",
        };

        var choice = chooseInMenu(choices);

        switch (choice) {
            case 0:
                tamagotchi.feed();

                tamagotchi.tick();
                break;

            case 1:
                tamagotchi.speak();

                tamagotchi.tick();
                break;

            case 2:
                System.out.print("Teach " + tamagotchi.name + " a new word > ");
                var newWord = in.nextLine();
                tamagotchi.teach(newWord);

                tamagotchi.tick();
                break;

            case 3:
                tamagotchi.tick();
                break;

            case 4:
                return true;
        }

        return false;
    }

    private static boolean deadMenu() {
        var choices = new String[]{
                "Get a new Tamagotchi",
                "Leave",
        };

        var choice = chooseInMenu(choices);

        switch (choice) {
            case 0:
                System.out.print("Name your Tamagotchi > ");
                var name = in.nextLine();

                tamagotchi = new Tamagotchi(name);
                break;

            case 1:
                return true;
        }

        return false;
    }

    private static int chooseInMenu(String[] choices) {
        for (int i = 0; i < choices.length; i++) {
            System.out.println(i+1 + ". " + choices[i]);
        }
        System.out.print("Choose > ");

        while (true) {
            try {
                var choice = in.nextInt();

                if (choice <= 0 || choice > choices.length) {
                    System.out.print("Invalid choice > ");
                    continue;
                }

                in.nextLine();
                return choice - 1;
            } catch (InputMismatchException e) {
                System.out.print("Enter a number > ");
                in.nextLine();
            }
        }
    }
}