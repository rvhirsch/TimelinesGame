import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameRunner {

    private static final int HANDSIZE = 15;

    private static Scanner keyboard;

    private static List<Card> deck = new ArrayList<>();
    private static List<Card> hand = new ArrayList<>();
    private static List<Card> timeline = new ArrayList<>();

    public static void main(String[] args) {
        deck = CardSetup.makeFullDeck();
        makeHand();
        timeline.add(drawCard());
        keyboard = new Scanner(System.in);

        while (hand.size() > 0) {
            takeTurn();
        }

        System.out.println("YOU EMPTIED YOUR HAND!!!");

        printTimeline();
    }

    public static void takeTurn() {
        printTimeline();
        printHand();

        int cardChoice = getHandChoice();
        Card choice = hand.get(cardChoice);
        System.out.println("\n" + cardChoice + ": " + choice);

        if (!putInTimeline(hand.get(cardChoice))) {
            System.out.println("The correct year is: " + hand.get(cardChoice).getYear() + "\n");
            hand.add(drawCard());
        }
        hand.remove(choice);
    }

    public static boolean putInTimeline(Card card) {
        System.out.println("\nChoose a timeline position for this card (0 - " + timeline.size() + "):");
        int posChoice = getTimelineChoice();

        if (posChoice == 0) {
            if (card.compareTo(timeline.get(0)) < 0) {
                timeline.add(0, card);
                return true;
            }
        } else if (posChoice == timeline.size()) {
            if (card.compareTo(timeline.get(timeline.size() - 1)) > 0) {
                timeline.add(card);
                return true;
            }
        } else if (card.compareTo(timeline.get(posChoice)) <= 0 && card.compareTo(timeline.get(posChoice - 1)) >= 0) {
            timeline.add(posChoice, card);
            return true;
        }
        return false;
    }

    public static int getTimelineChoice() {
        System.out.print("\n> ");
        int input;
        while (true) {
            try {
                input = Integer.parseInt(keyboard.nextLine());
                if (input <= timeline.size()) {
                    return input;
                } else {
                    System.out.print("Try again: ");
                }
            } catch (NumberFormatException nfe) {
                System.out.print("Try again: ");
            }
        }
    }

    public static int getHandChoice() {
        System.out.print("\n> ");
        int input;
        while (true) {
            try {
                input = Integer.parseInt(keyboard.nextLine());
                if (input < hand.size()) {
                    return input;
                } else {
                    System.out.print("Try again: ");
                }
            } catch (NumberFormatException nfe) {
                System.out.print("Try again: ");
            }
        }
    }

    public static Card drawCard() {
        return deck.remove(0);
    }

    public static void makeHand() {
        for (int i=0; i<HANDSIZE; i++) {
            Card newCard = drawCard();
            hand.add(drawCard());
        }
    }

    public static void printTimeline() {
        System.out.println("\n***** Current Timeline *****\n");

        for (int i=0; i<timeline.size(); i++) {
            System.out.println("\t" + i + ": " + timeline.get(i).timelineToString());
        }
    }

    public static void printHand() {
        System.out.println("\n ***** Choose a card in your hand (0 - " + (hand.size()-1) + "): *****\n");

        for (int i=0; i<hand.size(); i++) {
            System.out.println("\t" + i + ": " + hand.get(i));
        }
    }
}
