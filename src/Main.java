import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Name your Tamagotchi > ");
        var name = in.nextLine();

        var tamagotchi = new Tamagotchi(name);

        var choices = new String[]{
                "Feed",
                "Speak",
                "Teach",
                "Wait",
                "Abandon",
        };
        loop: while (true) {
            tamagotchi.printStats();
            System.out.println();
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
                    break loop;
            }

            if (!tamagotchi.isAlive()) {
                choices[4] = "Leave";
            }

            System.out.println("\n");
        }

        System.out.println("Goodbye");
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