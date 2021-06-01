import java.util.Scanner;
import game.Game;

public class App {
    public static void main(String[] args) {
        int input = 0;
        Scanner sc = new Scanner(System.in);

        while (input != 2) {
            showLogo();
            System.out.println("Type \"1\" for new game, \"2\" for quit program.");

            while (true) {
                System.out.print(">>> ");
                try {
                    input = sc.nextInt();
                    break;
                } catch (Exception e) {
                    sc = new Scanner(System.in);
                }
            }

            System.out.println();

            switch (input) {
                case 1:
                    new Game(3);
                    break;
                case 2:
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }

    public static void showLogo() {
        System.out.println();
        System.out.println(" $$$$$$\\                                                                 $$\\ ");
        System.out.println("$$  __$$\\                                                                $$ |");
        System.out.println("$$ /  $$ |$$$$$$$\\   $$$$$$\\           $$$$$$$\\ $$$$$$\\   $$$$$$\\   $$$$$$$ |");
        System.out.println("$$ |  $$ |$$  __$$\\ $$  __$$\\ $$$$$$\\ $$  _____|\\____$$\\ $$  __$$\\ $$  __$$ |");
        System.out.println("$$ |  $$ |$$ |  $$ |$$$$$$$$ |\\______|$$ /      $$$$$$$ |$$ |  \\__|$$ /  $$ |");
        System.out.println("$$ |  $$ |$$ |  $$ |$$   ____|        $$ |     $$  __$$ |$$ |      $$ |  $$ |");
        System.out.println(" $$$$$$  |$$ |  $$ |\\$$$$$$$\\         \\$$$$$$$\\\\$$$$$$$ |$$ |      \\$$$$$$$ |");
        System.out.println(" \\______/ \\__|  \\__| \\_______|         \\_______|\\_______|\\__|       \\_______|");
        System.out.println();
    }
} 