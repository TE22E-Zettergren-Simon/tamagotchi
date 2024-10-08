import java.util.ArrayList;
import java.util.Random;

public class Tamagotchi {
    private int hunger = 3;
    private int boredom = 5;
    private final ArrayList<String> words = new ArrayList<>();
    private boolean isAlive = true;
    public String name;

    private static final Random generator = new Random();

    public Tamagotchi(String name) {
        this.name = name;

        words.add("hi");
    }

    public void tick() {
        hunger += 1;
        boredom += 1;

        if (hunger >= 10 || boredom >= 10) {
            isAlive = false;
        }
    }

    public void feed() {
        reduceHunger();
    }

    public void speak() {
        var randIndex = generator.nextInt(words.size());
        System.out.println(name + " says " + words.get(randIndex));

        reduceBoredom();
    }

    public void teach(String newWords) {
        words.add(newWords);

        reduceBoredom();
    }

    public void printStats() {
        if (isAlive) {
            System.out.println(name + " is alive with");
            System.out.println("Hunger: " + hunger);
            System.out.println("Boredom: " + boredom);
        } else {
            System.out.println(name + " is dead");
        }
    }

    public boolean isAlive() {
        return isAlive;
    }

    private void reduceHunger() {
        hunger = Integer.max(hunger - 3, 0);
    }

    private void reduceBoredom() {
        boredom = Integer.max(boredom - 3, 0);
    }
}
