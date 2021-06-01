package game;

import java.util.Deque;
import java.util.Scanner;

import game.cards.Card;
import game.cards.Suit;
import game.collections.CardList;
import game.players.Player;
import util.ConsoleColors;

public class View {
    public void showWhosTurn(String name) {
        System.out.println(ConsoleColors.YELLOW);
        System.out.print(name.toUpperCase() + " TURN");
        System.out.println(ConsoleColors.RESET);
    }

    public void showAttackLevel(int attackLevel) {
        System.out.print("- Current damage level: ");
        System.out.print(ConsoleColors.RED);
        System.out.print(attackLevel);
        System.out.println(ConsoleColors.RESET);
    }

    public void showTableCard(Card facedUp) {
        System.out.print("- Card on table: ");
        System.out.print(facedUp.toString());
        System.out.println(ConsoleColors.RESET);
    }

    public void showTableCardWithChangedSuit(Card facedUp, Suit changedSuit) {
        System.out.print("- Card on table: ");
        System.out.print(facedUp.toString());
        System.out.print(" -> " + changedSuit.getSymbol());
        System.out.println(ConsoleColors.RESET);

    }

    public void showHandSize(int size) {
        System.out.println("- Card in hand: " + size + " card(s)");
    }

    public void showHandWithPlayableCard(CardList hand, CardList playableCards) {
        int index = 0;
        for (Card card : hand) {
            System.out.print("    ");
            if (playableCards.contains(card))
                System.out.print(ConsoleColors.BLUE_BRIGHT);
            System.out.print("[" + index + "] ");
            System.out.print(ConsoleColors.RESET);
            System.out.println(card.toString());
            index++;
        }
    }

    public void showPromptMessage(String message) {
        System.out.println(message);
    }

    public int getNextInt() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print(">>> ");
            try {
                return sc.nextInt();
            } catch (Exception e) {
                sc = new Scanner(System.in);
            }
        }
    }

    public void showAllSuitsWithIndex() {
        int index = 0;
        for (Suit suit : Suit.values()) {
            System.out.print("    ");
            System.out.print("[" + index + "] ");
            System.out.println(suit.getSymbol());
            index++;
        }
        System.out.print(ConsoleColors.RESET);
    }

    public void showPlayerPlayMessage(String name, Card card) {
        if (card.getAction() != null)
            System.out.println(name.toUpperCase() + " played " + card.toString() + ": " + card.getAction().toString());
        else
            System.out.println(name.toUpperCase() + " played " + card.toString());

    }

    public void showPlayerDrawMessage(String name, int numberOfCards) {
        System.out.println(name.toUpperCase() + " drew " + numberOfCards + " card(s)");
    }

    public void showHandSizeAfterDraw(int size) {
        System.out.println("now card in hand: " + size + " card(s)");
    }

    public void showChangedSuit(Suit changedSuit) {
        System.out.println("new suit is " + changedSuit.getSymbol());
    }

    public void showWinMessage(String name) {
        System.out.println(ConsoleColors.YELLOW);
        System.out.print(name.toUpperCase() + " FINISHED");
        System.out.println(ConsoleColors.RESET);
    }

    public void showLoseMessage(String name) {
        System.out.println(ConsoleColors.YELLOW);
        System.out.print(name.toUpperCase() + " DROPPED OUT");
        System.out.println(ConsoleColors.RESET);
    }

    public void showRanking(Deque<Player> players) {
        System.out.println(ConsoleColors.YELLOW);
        System.out.print("RESULT");
        System.out.println(ConsoleColors.RESET);
        int index = 1;
        for (Player player : players) {
            System.out.print("    ");
            System.out.println(index + ". " + player.getName());
            index++;
        }
    }
}
