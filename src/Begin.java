//Begin.java. This is the class for the board, not the main function
public class Begin {
    // Displayed board
    public static String[][] begin;
    // Actual board
    public static String[][] cover;

    public static int bombs;

    //Create Beginner Board
    public Begin() {
        this(9, 9, 10);
    }

    //Create Board 'r1' tall and 'c1' wide with 'b' bombs
    public Begin(int r1, int c1, int b) {
        bombs = b;
        begin(r1, c1);
        cover(r1, c1, b);
    }

    public static String[][] begin() {
        return begin(9, 9);
    }

    // Sets begin to all "-"s
    public static String[][] begin(int r, int c) {
        begin = new String[r][c];
        for (int x = 0; x < begin.length; x++) {
            for (int y = 0; y < begin[x].length; y++) {
                begin[x][y] = "-";
            }
        }
        return begin;
    }

    public static String[][] cover() {
        return cover(9, 9, 10);
    }

    public static String[][] cover(int r, int c, int bombs) {
        cover = new String[r][c];
        // bombs left to generate
        int bomb = bombs;

        // sets cover to all "-"s
        for (int x = 0; x < cover.length; x++) {
            for (int y = 0; y < cover[x].length; y++) {
                cover[x][y] = "1";
            }
        }

        while (bomb > 0) {
            // random row
            int row1 = (int) (Math.random() * r + 0);
            // random column
            int col1 = (int) (Math.random() * c + 0);
            // if random spot is not a bomb
            if (cover[row1][col1] != "M") {
                cover[row1][col1] = "M";
                bomb--;
            }
        }

        // Sets all spots to appropriate numbers
        for (int x = 0; x < cover.length; x++) {
            for (int y = 0; y < cover[0].length; y++) {
                // bombs around spot r,c
                int count = 0;
                // how far the loop can reach up
                int xmax = 1;
                // how far the loop can reach down
                int xmin = -1;
                // how far the loop can reach right
                int ymax = 1;
                // how far the loop can reach left
                int ymin = -1;
                // Prevents OutOfBoundsExceptions
                if (x == 0) {
                    xmin = 0;
                } else if (x == cover.length-1) {
                    xmax = 0;
                }
                if (y == 0) {
                    ymin = 0;
                } else if (y == cover[0].length-1) {
                    ymax = 0;
                }
                // For each position, look around it for "M" spots. Increase
                // count on each "M" found
                for (int i = xmin; i <= xmax; i++) {
                    for (int k = ymin; k <= ymax; k++) {
                        if (cover[i + x][k + y].equals("M"))
                            count++;
                    }
                }

                // Sets the character of the position based on count
                char ch;
                if (cover[x][y].equals("M")) {
                    ch = 'M';
                } else if (count == 0) {
                    ch = 'O';
                } else if (count == 1) {
                    ch = '1';
                } else if (count == 2) {
                    ch = '2';
                } else if (count == 3) {
                    ch = '3';
                } else if (count == 4) {
                    ch = '4';
                } else if (count == 5) {
                    ch = '5';
                } else if (count == 6) {
                    ch = '6';
                } else if (count == 7) {
                    ch = '7';
                } else if (count == 8) {
                    ch = '8';
                } else {
                    ch = ' ';
                }

                cover[x][y] = "" + ch;
            }
        }

        return cover;
    }

    // Recursion method
    public static void recur(int r, int c) {
        if (r < 0)
            r = 0;
        if (c < 0)
            c = 0;
        if (r >= begin.length)
            r = begin.length - 1;
        if (c >= begin[r].length)
            c = begin[r].length - 1;

        begin[r][c] = cover[r][c];

        if (begin[r][c].equals("O")) {
            int rmax = 1;
            int rmin = -1;
            int cmax = 1;
            int cmin = -1;

            if (r == 0) {
                rmin = 0;
            } else if (r == begin.length - 1) {
                rmax = 0;
            }

            if (c == 0) {
                cmin = 0;
            } else if (c == begin[r].length - 1) {
                cmax = 0;
            }
            for (int x = r + rmin; x <= r + rmax; x++) {
                for (int y = c + cmin; y <= c + cmax; y++) {
                    if (begin[x][y].equals("-")) {
                        recur(x, y);
                    }
                }
            }
        }
    }

    public static String[][] changeFlag(int r, int c) {

        int r1 = r - 1;
        int c1 = c - 1;

        if (begin[r1][c1].compareTo("-") == 0
                || begin[r1][c1].compareTo("!") == 0) {

            if (begin[r1][c1].compareTo("-") == 0) {
                begin[r1][c1] = "!";
                Main.num--;
            } else if (begin[r1][c1].compareTo("!") == 0) {
                begin[r1][c1] = "-";
                Main.num++;
            }
        }

        return begin;
    }

    // clicking (the part we could work on together: change in board)
    public static String[][] clickbomb(int a, int b) {
        int r = a - 1;
        int c = b - 1;

        if (r < 0)
            r = 0;
        if (c < 0)
            c = 0;
        if (r >= begin.length)
            r = begin.length - 1;
        if (c >= begin[r].length)
            c = begin[r].length - 1;

        if (begin[r][c].equals("!")) {
            return begin;
        } else {
            recur(r, c);
        }

        // If the spot is a bomb
        if (begin[r][c].equals("M")) {

            for (int r1 = 0; r1 < cover.length; r1++) {
                for (int c1 = 0; c1 < cover[r].length; c1++) {
                    if (cover[r1][c1].equals("M")
                            && !begin[r1][c1].equals("!")) {
                        begin[r1][c1] = "M";
                    } else if (cover[r1][c1].equals("M")
                            && begin[r1][c1].equals("!")) {
                        begin[r1][c1] = "!";

                    } else if (!cover[r1][c1].equals("M")
                            && begin[r1][c1].equals("!")) {
                        begin[r1][c1] = "X";
                    }

                    System.out.print(begin[r1][c1]);
                }

                System.out.println();
            }

            System.out.println("You lost the game.");
            Main.loop = false;
        }

        return begin;
    }

    public static String to_String() {
        String temp = "";
        //Print mines remaining
        temp += "Mines remaining: " + Main.num + "\n\n";
        int charsForRow = (int)Math.log10(begin.length);
        int charsForCol = (int)Math.log10(begin[0].length);
        //For number of characters to represent columns
        for(int i = 0; i <= charsForCol; i++) {
            //For number of characters to represent rows
            for(int k = charsForRow + 1; k > 0; k--) {
                temp += " ";
            }
            temp += " ";
            //For each column
            for(int k = 1; k <= begin[0].length; k++) {
                int exp = charsForCol+1-i;
                temp += (int)(k/Math.pow(10,exp-1) % 10);
            }
            temp+="\n";
        }

        temp+="\n";

        for (int r = 0; r < begin.length; r++) {
            //On each row, print the row number with proceeding 0s
            for(int i = charsForRow + 1; i > 0; i--) {
                if(i > Math.log10(r+1)+1) {
                    temp += 0;
                } else {
                    temp += (r+1);
                    break;
                }
            }
            temp += " ";
            for (int c = 0; c < begin[r].length; c++) {
                temp += begin[r][c];
            }
            temp += "\n";
        }
        temp += "\n";

        // Add cover to temp
        /*for (int r = 0; r < cover.length; r++) {
            for (int c = 0; c < cover[r].length; c++) {
                temp += cover[r][c];
            }
            temp += "\n";
        }*/
        return temp;
    }
    public static boolean winTest() {
        int count = 0;
        for (String[] r : begin) {
            for (String s : r) {
                if (s.equals("-") || s.equals("!")) {
                    count++;
                }
            }
        }
        if (count == bombs) {
            return true;
        } else {
            return false;
        }
    }

}
