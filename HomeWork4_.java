/**
 * Java 1. Home Work Lesson 4. Tic-tac-toe in console upgraded:
 * 1. game field extended up to 99 x 99 and may be different
 * 2. sequence of equally filled cells may be 1...99
 * 3 AI blocks a maximal sequence
 * 4. method isMapFull() excluded , instead turn counter entered
 * * @author Michail
 * @version dated Aug 21, 2018
 */
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
public class HomeWork4_{
    final int SIZE = 3;
    final char DOT_X = 'x';
    final char DOT_O = 'o';
    final char DOT_EMPTY = '.';
    int turns ;  // number of possible turns = (xmax+1)*(ymax+1)
    int turn_ct = 0; // counter of turns
    int xmax; // x dimension of game map
    int ymax; // y dimension of game map
    int xcur; // coordinate of current turn
    int ycur; // coordinate of current turn
    int lenwin; // number of cells for win
    int len ;
    int le[] = new int[4];  // numbers of equal cells
    int vect[] = new int[5]; // array of lenght & coordinates of equal cells sequence
    int vect_[] = new int[5]; // --//-- additional
    char[][] map = new char [99][99];
    Scanner sc = new Scanner(System.in);
    Random rand = new Random();

    public static void main(String[] arg) {new HomeWork4_();}
    HomeWork4_() {
        System.out.println("Enter game field, X, then Y (3...99)"); // game map xmax x ymax
        xmax = sc.nextInt();                                    // may be xmax <> ymax
        ymax = sc.nextInt();
        turns = xmax * ymax ;
        xmax--;
        ymax--;
        System.out.println("Enter number of filled cells for win (< X or Y)");
        lenwin = sc.nextInt() ;
        initMap();
        while (true) {
            humanTurn();
            printMap();
            if (checkWin()) {
                System.out.println("Your Win!");
                break;
            }
            if (turn_ct == turns) {
                System.out.println("Sorry, DRAW");
                break;
            }
            aiTurn();
            System.out.println("AI turns: ");
            printMap();
            if (checkWin()) {
                System.out.println("AI Win!");
                break;
            }
            if (turn_ct == turns) {
                System.out.println("Sorry, DRAW");
                break;
            }
        }
        System.out.println("GAME OVER.");
        printMap();
    }

    void initMap() {
        for (int i = 0; i < xmax + 1; i++)
            for (int j = 0; j < ymax + 1 ; j++)
                map[i][j] = DOT_EMPTY;
        printMap();
    }

    void printMap(){
        for (int i = 0; i < xmax + 1; i++) {
            for (int j = 0; j < ymax + 1 ; j++)
                System.out.print(map[i][j] + "  ");
            System.out.println();
        }
    }

    void humanTurn(){
        do {
            System.out.println("Enter X and Y");
            xcur = sc.nextInt() - 1;
            ycur = sc.nextInt() - 1;
        } while (!isCellValid());
        map[xcur][ycur] = DOT_X;
    }
    void aiTurn(){
        xcur = vect[1];
        ycur = vect[2];
        if (!isCellValid()) {
            xcur = vect[3];
            ycur = vect[4];
            if (!isCellValid())
                do {
                    xcur = rand.nextInt(xmax + 1);
                    ycur = rand.nextInt(ymax + 1);
                    //System.out.println(xcur + "  " + ycur);
                } while (!isCellValid());
        }
        map[xcur][ycur] = DOT_O;
    }

    boolean checkWin(){
//  check vertical cells to top
        int i = xcur - 1 ; // next vertical cell to top
        len = 1; // equally filled cells counter
        while (i >= 0 && len < lenwin) {
            if (areCellsEquals(xcur, ycur, i, ycur)) break;
            i--;
        }
        vect[1] = i;
        vect[2] = ycur;
//  check vertical cells to down
        i = xcur + 1; // next vertical cell to down
        while (i <= xmax && len < lenwin) {
            if (areCellsEquals(xcur, ycur, i, ycur)) break;
            i++;
        }
        vect[0] = len;
        vect[3] = i;
        vect[4] = ycur;
//  check horizontal cells to left
        int j = ycur - 1;
        len = 1; // equally filled cells counter's reset
        while (j >= 0 && len < lenwin) {
            if (areCellsEquals(xcur, ycur, xcur, j)) break;
            j--;
        }
        vect_[1] = xcur;
        vect_[2] = j;
        if (len == lenwin) return true;
//  check horizontal cells to right
        j = ycur + 1;
        while (j <= ymax && len < lenwin) {
            if (areCellsEquals(xcur, ycur, xcur, j)) break;
            j++;
        }
        vect_[0] = len;
        vect_[3] = xcur;
        vect_[4] = j;
        selectMax_vect();
//  check diagonal cells to top-left
        i = xcur - 1;
        j = ycur - 1;
        len = 1;
        while (i >= 0 && j>=0 && len < lenwin) {
            if (areCellsEquals(xcur, ycur, i, j)) break;
            i--;
            j--;
        }
        vect_[1] = i;
        vect_[2] = j;
//  check diagonal cells to down-right
        i = xcur + 1;
        j = ycur + 1;
        while (i <= xmax && j <= ymax && len < lenwin) {
            if (areCellsEquals(xcur, ycur, i, j)) break;
            i++;
            j++;
        }
        vect_[0] = len;
        vect_[3] = i;
        vect_[4] = j;
        selectMax_vect();
//  check diagonal cells to up-right
        i = xcur - 1;
        j = ycur + 1;
        len = 1;
        while (i >= 0 && j <= ymax && len < lenwin) {
            if (areCellsEquals(xcur, ycur, i, j)) break;
            i--;
            j++;
        }
            vect_[1] = i;
            vect_[2] = j;
//  check diagonal cells to top-left
        i = xcur + 1;
        j = ycur - 1;
        while (i <=xmax && j >= 0 && len != lenwin) {
            if (areCellsEquals(xcur, ycur, i, j)) break;
            i++;
            j--;
        }
        vect_[0] = len;
        vect_[3] = i;
        vect_[4] = j;
        selectMax_vect();
        if (vect[0] == lenwin) return true;
        return false;
    }

    boolean areCellsEquals(int x, int y, int i , int j) {
        if (map[x][y] != map[i][j])
            return true;
        else
            len++;
        return false;
    }
    void selectMax_vect() {
        if (vect_[0] > vect[0]) {
            for (int k=0; k < vect.length; k++)
                vect[k] = vect_[k];
        }
       // System.out.println(Arrays.toString(vect_) + Arrays.toString(vect));
    }
    boolean isMapFull() {
            for (int i = 0; i < SIZE; i++)
                for (int j = 0; j < SIZE; j++)
                    if (map[i][j] == DOT_EMPTY)
                        return false;
            return true;
    }

    boolean isCellValid() {
        if (xcur < 0 || ycur < 0 || xcur > xmax || ycur > ymax ||
                (map[xcur][ycur] != DOT_EMPTY) || (turn_ct == turns))
            return false;
        else
            turn_ct++;
        return true;
    }
}



