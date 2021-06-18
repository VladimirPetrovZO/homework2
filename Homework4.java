package homework2;
import java.util.Random;
import java.util.Scanner;

public class Homework4 {
    public static void main(String[] args) {
        createField();                    // создаем поле
        printMap();                   // рисуем поле

        switch (new Random().nextInt(2)) {                      // рандом на первый ход
            case 0: {
                System.out.println("Ваш ход первый!");
                while (true) {
                    player();
                    printMap();
                    if (pobeda(PLAYER_1_DOT)) {
                        System.out.println("Победил Игрок 1");
                        break;
                    }
                    if (isDraw()) {
                        System.out.println("Ничья");
                        break;
                    }
                    iiMove();
                    printMap();
                    if (pobeda(II_DOT)) {
                        System.out.println("Победил ИИ");
                        break;
                    }
                    if (isDraw()) {
                        System.out.println("Ничья");
                        break;
                    }
                }
                break;
            }
            case 1: {
                System.out.println("Первый ход за ИИ!");
                while (true) {
                    iiMove();
                    printMap();
                    if (pobeda(II_DOT)) {
                        System.out.println("Победил ИИ");
                        break;
                    }
                    if (isDraw()) {
                        System.out.println("Ничья");
                        break;
                    }
                    player();
                    printMap();
                    if (pobeda(PLAYER_1_DOT)) {
                        System.out.println("Победил Игрок 1");
                        break;
                    }
                    if (isDraw()) {
                        System.out.println("Ничья");
                        break;
                    }
                }
            }
        }
    }

    public static final int MAP_SIZE = 5;
    public static final int DOTS_TO_WIN = 4;

    public static final char EMPTY_DOT = '.';
    public static final char PLAYER_1_DOT = 'X';
    public static final char II_DOT = 'O';

    public static Scanner input = new Scanner(System.in);
    public static char[][] map;

    public static void createField() {
        map = new char[MAP_SIZE][MAP_SIZE];
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                map[i][j] = EMPTY_DOT;
            }
        }
    }

    public static void printMap() {                // распечатка поля
        for (int i = 0; i <= map.length; i++) {
            System.out.print(i == 0 ? "  " : i + " ");   // двойной пробел
        }
        System.out.println();
        for (int i = 0; i < map.length; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < map.length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static boolean nalichieYacheiki(int x, int y) {
        return x >= 0 && x < MAP_SIZE && y >= 0 && y < MAP_SIZE && map[y][x] == EMPTY_DOT;    // чтобы не выходить за рамки координат
    }

    public static void player() {
        int x, y;
        do {
            System.out.printf("Введите координаты X= Y=\n", MAP_SIZE, MAP_SIZE);
            x = Integer.valueOf(input.next()) - 1;
            y = Integer.valueOf(input.next()) - 1;
        } while (!nalichieYacheiki(x, y));
        map[y][x] = PLAYER_1_DOT;
    }

    public static void iiMove() {
        int x, y;
        do {
            x = new Random().nextInt(MAP_SIZE);
            y = new Random().nextInt(MAP_SIZE);
        } while (!nalichieYacheiki(x, y));
        System.out.println("Ход ИИ " + (x + 1) + " " + (y + 1));
        map[y][x] = II_DOT;
    }

    public static boolean pobeda(char playerDot) {
        int gorizontal, vertical;
        int diagMain, diagReverse;
        for (int i = 0; i < MAP_SIZE; i++) {
            gorizontal = 0;
            vertical = 0;
            for (int j = 0; j < MAP_SIZE; j++) {
                if (map[i][j] == playerDot) {                          // проверяем горизонтальные линии
                    gorizontal++;
                } else if (map[i][j] != playerDot && gorizontal < DOTS_TO_WIN) {
                    gorizontal = 0;
                }
                if (map[j][i] == playerDot) {                          // проверяем вертикальные линии
                    vertical++;
                } else if (map[j][i] != playerDot && vertical < DOTS_TO_WIN) {
                    vertical = 0;
                }
            }
            if (gorizontal >= DOTS_TO_WIN || vertical >= DOTS_TO_WIN) {
                System.out.println("По горизонтали или вертикали " + gorizontal + " " + vertical);
                return true;
            }
        }

        for (int j = 0; j < MAP_SIZE; j++) {
            diagMain = 0;
            for (int i = 0; i < MAP_SIZE; i++) {
                int k = j + i;
                if (k < MAP_SIZE) {
                    if (map[i][k] == playerDot) {                      // проверяем главную диагональ от центральной оси вправо
                        diagMain++;
                    } else if (map[i][k] != playerDot && diagMain < DOTS_TO_WIN) {
                        diagMain = 0;
                    }
                }
                if (diagMain >= DOTS_TO_WIN) {
                    System.out.println("По главной диагонали от центральной оси вправо " + diagMain);
                    return true;
                }
            }
        }
        for (int j = 1; j < MAP_SIZE; j++) {
            diagMain = 0;
            for (int i = 0; i < MAP_SIZE; i++) {
                int k = j + i;
                if (k < MAP_SIZE) {
                    if (map[k][i] == playerDot) {                      // проверяем главную диагональ от центральной оси
                        diagMain++;
                    } else if (map[k][i] != playerDot && diagMain < DOTS_TO_WIN) {
                        diagMain = 0;
                    }
                }
                if (diagMain >= DOTS_TO_WIN) {
                    System.out.println("По главной диагонали от центральной оси вниз " + diagMain);
                    return true;
                }
            }
        }
        for (int j = 0; j < MAP_SIZE; j++) {
            diagReverse = 0;
            for (int i = 0; i < MAP_SIZE; i++) {
                int k = (MAP_SIZE - 1) - i;
                int l = j + i;
                if (k >= 0 && l < MAP_SIZE) {
                    if (map[l][k] == playerDot) {                     // проверяем побочную диагональ от центральной оси вниз
                        diagReverse++;
                    } else if (map[l][k] != playerDot && diagReverse < DOTS_TO_WIN) {
                        diagReverse = 0;
                    }
                }
                if (diagReverse >= DOTS_TO_WIN) {
                    System.out.println("По побочной диагонали от центральной оси вниз " + diagReverse);
                    return true;
                }
            }
        }
        for (int j = 1; j < MAP_SIZE; j++) {
            diagReverse = 0;
            for (int i = 0; i < MAP_SIZE; i++) {
                int k = (MAP_SIZE - 1) - j - i;
                if (k >= 0) {
                    if (map[i][k] == playerDot) {     // проверяем побочную диагональ от центральной оси влево
                        diagReverse++;
                    } else if (map[i][k] != playerDot && diagReverse < DOTS_TO_WIN) {
                        diagReverse = 0;
                    }
                }
                if (diagReverse >= DOTS_TO_WIN) {
                    System.out.println("По побочной диагонали от центральной оси влево " + diagReverse);
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isDraw() {                            // будет ли ничьей
        for (char[] aGameField : map) {
            for (int j = 0; j < map.length; j++) {
                if (aGameField[j] == EMPTY_DOT) {
                    return false;
                }
            }
        }
        return true;
    }                                                           // мягко говоря не успею прописать улучшение для ИИ
                                                                // думаю с помощью доп проверки после хода игрока, если он уже занимает 2 или 3 точки в одной из выигрышных сторон

}
