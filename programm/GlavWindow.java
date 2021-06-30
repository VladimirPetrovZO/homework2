package programm;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * в общем и целом понятно
 * попытался переписать не подглядывая-не получилось, запутываюсь в переменных
 *
*/
public class GlavWindow extends JFrame {

    private int winWidth = 750;
    private int winHeight = 600;
    private int winPositionX = 300;
    private int winPositionY = 200;



    private int minMapSize = 3;
    private int maxMapSize = 10;
    private int minWinLength = 3;



    private JButton btnStart;
    private JButton btnExit;
    private JButton btnClearLog;



    private JPanel panelSettingsGame;
    private JPanel panelControlsElement;



    private JSlider sliderMapSize;
    private JLabel labelMapSize;
    private final String mapSizeLabelPrefix = "Длинна поля";



    private JSlider sliderWinLength;
    private JLabel labelWinLength;
    private final String winLengthLabelPrefix = "полей для выигрыша";



    private JTextArea gameLog;
    private JScrollPane scrollPanel;



    private map gameMap;
    private int countRound = 0;



    public GlavWindow() {
        setupWindow();
        prepareGameSettingsPanel();
        prepareButtons();
        prepareGameSettingsControl();
        prepareGameLogElement();

        gameMap = new map(this);

        panelSettingsGame.add(panelControlsElement, BorderLayout.NORTH);
        panelSettingsGame.add(scrollPanel, BorderLayout.SOUTH);

        add(panelSettingsGame, BorderLayout.EAST);
        add(gameMap);

        setVisible(true);
    }

    private void setupWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(winWidth, winHeight);
        setLocation(winPositionX, winPositionY);
        setTitle("Крестики нолики");
        setResizable(false);
    }

    private void prepareGameSettingsPanel() {
        panelSettingsGame = new JPanel();
        panelSettingsGame.setLayout(new GridLayout(2, 1));
    }

    private void prepareButtons() {
        btnStart = new JButton("Начать игру");
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                collectUserSettingAndLaunchGame();
            }
        });

        btnExit = new JButton("Выход");
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        btnClearLog = new JButton("Очистить окно логов");
        btnClearLog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameLog.setText("");
            }
        });
    }

    private void prepareGameSettingsControl() {
        panelControlsElement = new JPanel();
        panelControlsElement.setLayout(new GridLayout(7, 1));

        labelMapSize = new JLabel(mapSizeLabelPrefix + minMapSize);
        sliderMapSize = new JSlider(minMapSize, maxMapSize, minMapSize);
        sliderMapSize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int currentPosition = sliderMapSize.getValue();
                labelMapSize.setText(mapSizeLabelPrefix + currentPosition);
                sliderWinLength.setMaximum(currentPosition);
            }
        });

        labelWinLength = new JLabel(winLengthLabelPrefix + minWinLength);
        sliderWinLength = new JSlider(minWinLength, minMapSize, minMapSize);
        sliderWinLength.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                labelWinLength.setText(winLengthLabelPrefix + sliderWinLength.getValue());
            }
        });

        panelControlsElement.add(labelMapSize);
        panelControlsElement.add(sliderMapSize);
        panelControlsElement.add(labelWinLength);
        panelControlsElement.add(sliderWinLength);
        panelControlsElement.add(btnStart);
        panelControlsElement.add(btnExit);
        panelControlsElement.add(btnClearLog);
    }

    private void prepareGameLogElement() {
        gameLog = new JTextArea();
        scrollPanel = new JScrollPane(gameLog);
        gameLog.setEditable(false);
        gameLog.setLineWrap(true);
    }

    void saveLog(String text) {
        gameLog.append(text + "\n");
    }

    private void collectUserSettingAndLaunchGame() {
        int mapSize = sliderMapSize.getValue();
        int winLen = sliderWinLength.getValue();
        ++countRound;
        saveLog(" Партия " + countRound );
        saveLog("Размер карты" + mapSize + "x" + mapSize + "\n" + "Полей для выигрыша = " + winLen);

        gameMap.startGame(mapSize, mapSize, winLen);
    }
}


