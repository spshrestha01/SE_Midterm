import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static int num;
    public static boolean loop = true;
    public static boolean game = true;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out
                .println("Hello, this is the minesweeper game. We would now introduce to you the rules you need to know to play.\n");
        System.out
                .println("We recommend setting the console font to a truetype font, such as Courier for the best play experience\n");
        System.out
                .println("Printed below will be the board for you. \n"
                        + "Dashes (-) are spaces that you have not opened yet. \n"
                        + "O's are spaces that are clear. \n"
                        + "Numbers are the same. \n"
                        + "!'s are flags and if you hit a mine all mines will show up as M's and game over. \n"
                        + "Try not to hit a mine :)\n");
        System.out
                .println("To click on a space you will need to enter the coordinate you intend to open. Input the row number first and press enter. \n"
                        + "Then the column number and press enter again. \n\n"
                        + "You will always be asked if you would like to place a flag at the beginning of each turn. \n"
                        + "If you would like to place a flag just type in Y else type in N and press enter.\n");
        System.out.println("Good luck on your game and have fun!");

        while (game) {
            System.out.println("Please select a game mode");
            System.out.println("1) Beginner  [9x9, 10 bombs]");
            System.out.println("2) Medium    [16x16, 40 bombs]");
            System.out.println("3) Expert    [16x30, 99 bombs]");
            System.out.println("4) Custom    [Select your own size]");

            loop = true;

            while (loop) {
                String inp = "";
                try {
                    inp = scan.nextLine().trim();
                } catch (InputMismatchException e) {
                    System.out
                            .println("A letter was entered when a number was expected. The program must now exit");
                    loop = false;
                    game = false;
                    break;
                }
                if (inp.charAt(0) == '1') {
                    num = 10;
                    new Begin(9, 9, num);
                    loop = false;
                } else if (inp.charAt(0) == '2') {
                    num = 40;
                    new Begin(16, 16, num);
                    loop = false;
                } else if (inp.charAt(0) == '3') {
                    num = 99;
                    new Begin(16, 30, num);
                    loop = false;
                } else if (inp.charAt(0) == '4') {
                    System.out.println("Please input the number of rows");
                    int r = -1, c = -1, b = -1;
                    while (loop) {
                        r = scan.nextInt();
                        if (r > 0) {
                            loop = false;
                        } else {
                            System.out
                                    .println("Please input a positive number");
                        }
                    }
                    loop = true;
                    System.out.println("Please input the number of columns");
                    while (loop) {
                        c = scan.nextInt();
                        if (c > 0) {
                            loop = false;
                        } else {
                            System.out
                                    .println("Please input a positive number");
                        }
                    }
                    loop = true;
                    System.out.println("Please input the number of bombs");
                    while (loop) {
                        b = scan.nextInt();
                        if (b > 0 && b <= (r * c)) {
                            loop = false;
                        } else {
                            System.out
                                    .println("Please input a positive number that allows for each bomb to fit on a board");
                        }
                    }
                    num = b;
                    new Begin(r, c, num);
                } else {
                    System.out.println("Please input a number listed above");
                }
            }

            loop = true;

            while (loop) {

                System.out.println(Begin.to_String());
                // user input String

                System.out
                        .println("Do you want to put a flag down or remove a flag? (yes enter Y, no enter N)");
                boolean tryIn = true;
                String c = "";
                while (tryIn) {
                    try {
                        c = scan.nextLine().toUpperCase();

                    } catch (Throwable e) {

                    } finally {
                        if (!c.equals(""))
                            tryIn = false;
                    }
                }

                if (c.compareTo("Y") != 0 && c.compareTo("N") != 0)
                    System.out.println("Please enter Y or N.");

                if (c.compareTo("Y") == 0) {
                    System.out.println("What spot do you want to flag?");
                    tryIn = true;
                    int FLAGR = -1, FLAGC = -1;
                    while (tryIn) {
                        try {
                            FLAGR = scan.nextInt();
                        } catch (InputMismatchException e) {
                            System.out
                                    .println("A letter has been entered when a number was expected. The program must now exit.");
                            loop = false;
                            break;
                        } catch (Throwable e) {

                        } finally {
                            if (FLAGR != -1)
                                tryIn = false;
                        }
                    }

                    tryIn = true;
                    while (tryIn) {
                        try {
                            FLAGC = scan.nextInt();
                        } catch (InputMismatchException e) {
                            System.out
                                    .println("A letter has been entered when a number was expected. The program must now exit.");
                            loop = false;
                            game = false;
                            break;
                        } catch (Throwable e) {

                        } finally {
                            if (FLAGC != -1)
                                tryIn = false;
                        }
                    }
                    if (FLAGR > 0 && FLAGR <= Begin.begin.length && FLAGC > 0
                            && FLAGC <= Begin.begin[0].length)
                        Begin.changeFlag(FLAGR, FLAGC);
                    else
                        System.out.println("Please enter a valid number");
                }

                if (c.compareTo("N") == 0) {
                    // Remove spot
                    tryIn = true;
                    System.out.println("What spot do you want to click?");

                    int a = -1;
                    int b = -1;
                    while (tryIn) {
                        try {
                            a = scan.nextInt();
                        } catch (InputMismatchException e) {
                            System.out
                                    .println("A letter has been entered when a number was expected. The program must now exit.");
                            loop = false;
                            game = false;
                            break;
                        } finally {
                            if (a != -1)
                                tryIn = false;
                        }
                    }

                    tryIn = true;
                    while (tryIn) {
                        try {
                            b = scan.nextInt();
                        } catch (InputMismatchException e) {
                            System.out
                                    .println("A letter has been entered when a number was expected. The program must now exit.");
                            loop = false;
                            game = false;
                            break;
                        } catch (Throwable e) {

                        } finally {
                            if (b != -1)
                                tryIn = false;
                        }
                    }
                    if (a > 0 && a <= Begin.begin.length && b > 0
                            && b <= Begin.begin[0].length)
                        Begin.clickbomb(a, b);
                    else
                        System.out.println("Please enter a valid number");

                }
                if (Begin.winTest() == true) {
                    loop = false;
                    System.out.println("You win!");
                    System.out.println(Begin.to_String());
                }
            }

            System.out.println("Would you like to play again?");
            boolean flag = true;
            String inp = "";
            while (flag) {
                inp = scan.nextLine().trim().toUpperCase();
                if (!inp.equals("")) {
                    if (inp.charAt(0) == 'Y') {
                        flag = false;
                    } else if (inp.charAt(0) == 'N') {
                        flag = false;
                        game = false;
                    } else {
                        System.out.println("Please enter either 'Yes' or 'No'");
                    }
                }
            }
        }
        scan.close();
    }
}

