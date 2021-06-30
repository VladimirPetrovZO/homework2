package programm;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class map extends JPanel {
        private int mapSizeX;
        private int mapSizeY;
        private int winLength;
        private int[][] map;
        private GlavWindow glavWindow;
        private int cellWidth;
        private int cellHeight;
        private Random random = new Random();
        private int human = 1;
        private int ai = 2;
        private int emptyField = 0;
        private final int stateHumanWin = 1;
        private final String msgHumanWin = "Победа игрока";
        private final int stateAiWin = 2;
        private final String msgAiWin = "Победа компьютера";
        private final int stateDraw = 0;
        private final String msgDraw = "Ничья";
        private boolean isExistMap;
        private boolean isGameOver;



        public map(GlavWindow mainWindow) {
            this.glavWindow = mainWindow;
            setBackground(new Color(0, 0, 0));

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent mouseObject) {
                    super.mouseReleased(mouseObject);
                    update(mouseObject);
                }
            });
            isExistMap = false;
        }
        void startGame(int mapSizeX, int mapSizeY, int winLength) {
            this.mapSizeX = mapSizeX;
            this.mapSizeY = mapSizeY;
            this.winLength = winLength;
            this.isExistMap = true;
            this.isGameOver = false;
            this.map = new int[mapSizeY][mapSizeX];
            repaint();
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            render(g);
        }
        private void update(MouseEvent mouseObject) {
            if (!isExistMap) {
                return;
            }
            if (isGameOver) {
                return;
            }
            int cellX = mouseObject.getX() / cellWidth;
            int cellY = mouseObject.getY() / cellHeight;
            if ((!isValidCell(cellX, cellY)) || (!isEmptyCell(cellX, cellY))) {
                return;
            }
            map[cellY][cellX] = human;
            glavWindow.saveLog("Ход игрока [" + (cellX + 1) + ":" + (cellY + 1) + "]");
            if (checkWin(human)) {
                setGameOver(stateHumanWin);
                return;
            }
            if (isFullMap()) {
                setGameOver(stateDraw);
                return;
            }
            aiTurn();
            repaint();
            if (checkWin(ai)) {
                setGameOver(stateAiWin);
                return;
            }
            if (isFullMap()) {
                setGameOver(stateDraw);
                return;
            }
        }
        private void setGameOver(int stateGameOverPlayer) {
            repaint();
            this.isGameOver = true;
            showGameOverMessage(stateGameOverPlayer);
        }
        private void showGameOverMessage(int state) {
            switch (state) {
                case stateHumanWin:
                    glavWindow.saveLog(msgHumanWin);
                    JOptionPane.showMessageDialog(this, msgHumanWin);
                    break;
                case stateAiWin:
                    glavWindow.saveLog(msgAiWin);
                    JOptionPane.showMessageDialog(this, msgAiWin);
                    break;
                case stateDraw:
                    glavWindow.saveLog(msgDraw);
                    JOptionPane.showMessageDialog(this, msgDraw);
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Ошибка, неизвестный исход " + state);
                    break;
            }
        }
        private void render(Graphics g) {
            if (!isExistMap) {
                return;
            }
            createMap(g);

            for (int y = 0; y < mapSizeY; y++) {
                for (int x = 0; x < mapSizeX; x++) {

                    if (isEmptyCell(x, y)) {
                        continue;
                    }

                    if (map[y][x] == human) {
                        g.setColor(Color.WHITE);
                        g.fillOval(x * cellWidth + 10, y * cellHeight + 10, cellWidth - 20, cellHeight - 20);
                    }

                    if (map[y][x] == ai) {
                        g.setColor(Color.BLUE);
                        g.fillRect(x * cellWidth + 10, y * cellHeight + 10, cellWidth - 20, cellHeight - 20);
                    }
                }
            }
            if (isGameOver) {
                return;
            }
        }

        private void createMap(Graphics g) {
            int width = getWidth();
            int height = getHeight();
            cellWidth = width / mapSizeX;
            cellHeight = height / mapSizeY;
            g.setColor(Color.WHITE);

            for (int i = 0; i <= mapSizeY; i++) {
                int y = i * cellHeight;
                g.drawLine(0, y, width, y);
            }

            for (int i = 0; i < mapSizeX; i++) {
                int x = i * cellWidth;
                g.drawLine(x, 0, x, height);
            }
        }

        private void aiTurn() {
            if (turnAIWinCell()) {
                return;
            }
            if (turnHumanWinCell()) {
                return;
            }
            int x;
            int y;
            do {
                x = random.nextInt(mapSizeX);
                y = random.nextInt(mapSizeY);
            } while (!isEmptyCell(x, y));
            map[y][x] = ai;
            glavWindow.saveLog("Ход компьютера [" + (x + 1) + ":" + (y + 1) + "]");
        }

        private boolean turnAIWinCell() {
            for (int i = 0; i < mapSizeY; i++) {
                for (int j = 0; j < mapSizeX; j++) {
                    if (isEmptyCell(j, i)) {
                        map[i][j] = ai;
                        if (checkWin(ai)) {
                            glavWindow.saveLog("Ход компьютера [" + (j + 1) + ":" + (i + 1) + "]");
                            return true;
                        }
                        map[i][j] = emptyField;
                    }
                }
            }
            return false;
        }

        private boolean turnHumanWinCell() {
            for (int i = 0; i < mapSizeY; i++) {
                for (int j = 0; j < mapSizeX; j++) {
                    if (isEmptyCell(j, i)) {
                        map[i][j] = human;
                        if (checkWin(human)) {
                            map[i][j] = ai;
                            glavWindow.saveLog("Ход компьютера [" + (j + 1) + ":" + (i + 1) + "]");
                            return true;
                        }
                        map[i][j] = emptyField;
                    }
                }
            }
            return false;
        }

        private boolean checkWin(int player) {
            for (int i = 0; i < mapSizeX; i++) {
                for (int j = 0; j < mapSizeY; j++) {
                    if (checkLine(i, j, 1, 0, winLength, player)) {
                        return true;
                    }
                    if (checkLine(i, j, 1, 1, winLength, player)) {
                        return true;
                    }
                    if (checkLine(i, j, 0, 1, winLength, player)) {
                        return true;
                    }
                    if (checkLine(i, j, 1, -1, winLength, player)) {
                        return true;
                    }
                }
            }
            return false;
        }

        // проверка линии
        private boolean checkLine(int x, int y, int vx, int vy, int len, int player) {
            final int farX = x + (len - 1) * vx;
            final int farY = y + (len - 1) * vy;
            if (!isValidCell(farX, farY)) {
                return false;
            }
            for (int i = 0; i < len; i++) {
                if (map[y + i * vy][x + i * vx] != player) {
                    return false;
                }
            }
            return true;
        }

        private boolean isFullMap() {
            for (int i = 0; i < mapSizeY; i++) {
                for (int j = 0; j < mapSizeX; j++) {
                    if (map[i][j] == emptyField) {
                        return false;
                    }
                }
            }
            return true;
        }

        private boolean isValidCell(int x, int y) {
            return x >= 0 && x < mapSizeX && y >= 0 && y < mapSizeY;
        }

        private boolean isEmptyCell(int x, int y) {
            return map[y][x] == emptyField;
        }


    }


