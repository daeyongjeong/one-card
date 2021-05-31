import java.util.Scanner;
import game.Game;

public class App {
    public static void showLogo() {
        System.out.println();
        System.out.println(" $$$$$$\\                            $$\\  $$$$$$\\                                     ");
        System.out.println(" $$  __$$\\                           $$ |$$  __$$\\                                   ");
        System.out.println(
                " $$ /  \\__| $$$$$$\\   $$$$$$\\   $$$$$$$ |$$ /  \\__| $$$$$$\\  $$$$$$\\$$$$\\   $$$$$$\\  ");
        System.out.println(
                " $$ |       \\____$$\\ $$  __$$\\ $$  __$$ |$$ |$$$$\\  \\____$$\\ $$  _$$  _$$\\ $$  __$$\\ ");
        System.out.println(" $$ |       $$$$$$$ |$$ |  \\__|$$ /  $$ |$$ |\\_$$ | $$$$$$$ |$$ / $$ / $$ |$$$$$$$$ |");
        System.out.println(" $$ |  $$\\ $$  __$$ |$$ |      $$ |  $$ |$$ |  $$ |$$  __$$ |$$ | $$ | $$ |$$   ____|");
        System.out.println(
                " \\$$$$$$  |\\$$$$$$$ |$$ |      \\$$$$$$$ |\\$$$$$$  |\\$$$$$$$ |$$ | $$ | $$ |\\$$$$$$$\\ ");
        System.out.println(
                "  \\______/  \\_______|\\__|       \\_______| \\______/  \\_______|\\__| \\__| \\__| \\_______|");
        System.out.println();
    }

    public static void main(String[] args) {
        int input;
        Scanner sc = new Scanner(System.in);

        while (true) {
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
                default:
                    break;
            }
        }
    }
}
