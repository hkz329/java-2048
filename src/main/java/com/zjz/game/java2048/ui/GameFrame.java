package com.zjz.game.java2048.ui;

import com.zjz.game.java2048.core.Board;
import com.zjz.game.java2048.core.Direction;
import com.zjz.game.java2048.util.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 游戏主窗口类
 * 负责组织游戏界面和处理用户输入
 */
public class GameFrame extends JFrame {
    private  Board board;
    private final GamePanel gamePanel;
    private final ScorePanel scorePanel;
    private boolean gameOver;
    private boolean won;

    public GameFrame() {
        board = new Board();
        gamePanel = new GamePanel(board);
        scorePanel = new ScorePanel();
        gameOver = false;
        won = false;

        setupUI();
        setupKeyListener();
    }

    /**
     * 设置UI界面
     */
    private void setupUI() {
        setTitle("2048 Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // 创建主面板
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(0, 20));
        mainPanel.setBackground(new Color(250, 248, 239));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // 添加标题
        JLabel titleLabel = new JLabel("2048");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 48));
        titleLabel.setForeground(new Color(119, 110, 101));

        // 添加组件
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(scorePanel, BorderLayout.CENTER);
        mainPanel.add(gamePanel, BorderLayout.SOUTH);

        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
    }

    /**
     * 设置键盘监听器
     */
    private void setupKeyListener() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (gameOver || won) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        resetGame();
                    }
                    return;
                }

                boolean moved = false;
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT -> moved = board.move(Direction.LEFT);
                    case KeyEvent.VK_RIGHT -> moved = board.move(Direction.RIGHT);
                    case KeyEvent.VK_UP -> moved = board.move(Direction.UP);
                    case KeyEvent.VK_DOWN -> moved = board.move(Direction.DOWN);
                }

                if (moved) {
                    board.addRandomNumber();
                    scorePanel.updateScore(board.getScore());
                    gamePanel.repaint();
                    checkGameState();
                }
            }
        });
    }

    /**
     * 检查游戏状态
     */
    private void checkGameState() {
        if (!won && board.hasWon()) {
            won = true;
            showGameMessage("Congratulations! You've won!\nPress Enter to start a new game.");
        } else if (board.isGameOver()) {
            gameOver = true;
            showGameMessage("Game Over!\nPress Enter to try again.");
        }
    }

    /**
     * 显示游戏消息
     */
    private void showGameMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    /**
     * 重置游戏
     */
    private void resetGame() {
        board = new Board();
        gamePanel.setBoard(board);
        gameOver = false;
        won = false;
        scorePanel.updateScore(0);
        gamePanel.repaint();
    }
} 