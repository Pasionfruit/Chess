// Libraries
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Timer;
import java.util.TimerTask;

public class ChessGame extends JFrame {
    private static final int BOARD_SIZE = 8;
    private static char[][] board = new char[BOARD_SIZE][BOARD_SIZE];

    public ChessGame() {
        setTitle("Chess Game");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel menuPanel = createMenuPanel();
        add(menuPanel);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createMenuPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Welcome text
        JLabel titleLabel = new JLabel("Welcome to Chess", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(6, 1));

        // Player and Timer labels
        JLabel players = new JLabel("Number of Players");
        JLabel timer = new JLabel("Duration of the Game");
        players.setFont(new Font("Arial", Font.BOLD, 14));
        timer.setFont(new Font("Arial", Font.BOLD, 14));

        // Player panels and buttons group initalized
        JPanel playerPanel = new JPanel(new GridLayout(1, 2));
        JRadioButton onePlayerButton = new JRadioButton("One Player");
        JRadioButton twoPlayersButton = new JRadioButton("Two Players");

        ButtonGroup playerGroup = new ButtonGroup();
        playerGroup.add(onePlayerButton);
        playerGroup.add(twoPlayersButton);

        playerPanel.add(onePlayerButton);
        playerPanel.add(twoPlayersButton);

        // Duration panels and buttons group initalized
        JPanel durationPanel = new JPanel(new GridLayout(1, 5));
        JRadioButton oneMinuteButton = new JRadioButton("1 MIN");
        JRadioButton threeMinuteButton = new JRadioButton("3 MIN");
        JRadioButton fiveMinuteButton = new JRadioButton("5 MIN");
        JRadioButton tenMinuteButton = new JRadioButton("10 MIN");
        JRadioButton noTimerButton = new JRadioButton("∞");

        ButtonGroup durationGroup = new ButtonGroup();
        durationGroup.add(oneMinuteButton);
        durationGroup.add(threeMinuteButton);
        durationGroup.add(fiveMinuteButton);
        durationGroup.add(tenMinuteButton);
        durationGroup.add(noTimerButton);

        durationPanel.add(oneMinuteButton);
        durationPanel.add(threeMinuteButton);
        durationPanel.add(fiveMinuteButton);
        durationPanel.add(tenMinuteButton);
        durationPanel.add(noTimerButton);

        // Buttons for rules and start initalized
        JButton rulesButton = new JButton("Rules");
        JButton startGameButton = new JButton("Start");
        startGameButton.setEnabled(false);
        
        // Adds the buttons to the panel
        buttonPanel.add(players);
        buttonPanel.add(playerPanel);
        buttonPanel.add(timer);
        buttonPanel.add(durationPanel);
        buttonPanel.add(rulesButton);
        buttonPanel.add(startGameButton);

        ItemListener itemListener = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (onePlayerButton.isSelected() || twoPlayersButton.isSelected()) {
                    if (oneMinuteButton.isSelected() || threeMinuteButton.isSelected() ||
                        fiveMinuteButton.isSelected() || tenMinuteButton.isSelected() ||
                        noTimerButton.isSelected()) {
                        startGameButton.setEnabled(true);
                        startGameButton.setBackground(Color.GREEN);
                    } else {
                        startGameButton.setEnabled(false);
                    }
                } else {
                    startGameButton.setEnabled(false);
                }
            }
        };

        rulesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showRulesDialog();
            }
        });

        onePlayerButton.addItemListener(itemListener); 
        twoPlayersButton.addItemListener(itemListener);
        oneMinuteButton.addItemListener(itemListener);
        threeMinuteButton.addItemListener(itemListener);
        fiveMinuteButton.addItemListener(itemListener);
        tenMinuteButton.addItemListener(itemListener);
        noTimerButton.addItemListener(itemListener);

        panel.add(buttonPanel, BorderLayout.CENTER);

        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleMenuSelection(onePlayerButton.isSelected() ? 1 : 2);
            }
        });

        return panel;
    }

    private void showRulesDialog() {
        JFrame rulesFrame = new JFrame("Chess Rules");
        rulesFrame.setSize(1300, 800);
        rulesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        try {
            File imageFile = new File("Chess_Rules.jpg");
            Image image = ImageIO.read(imageFile);

            JLabel imageLabel = new JLabel(new ImageIcon(image));
            rulesFrame.add(imageLabel);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Create "Go Back" button
        JButton goBackButton = new JButton("Go Back");
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rulesFrame.dispose(); // Close the rules window
            }
        });
        rulesFrame.add(goBackButton, BorderLayout.SOUTH);

        // Center the frame on the screen
        rulesFrame.setLocationRelativeTo(null);
        rulesFrame.setVisible(true);
    }

    public class ChessBoardPanel extends JPanel {
        private static final int SQUARE_SIZE = 100; // Adjust this based on your preference
        private static final int BOARD_SIZE = 8;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Draw the chessboard
            for (int i = 0; i < BOARD_SIZE; i++) {
                for (int j = 0; j < BOARD_SIZE; j++) {
                    Color color = (i + j) % 2 == 0 ? Color.WHITE : Color.GRAY;
                    g.setColor(color);
                    g.fillRect(j * SQUARE_SIZE, i * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                }
            }

            // Draw the pieces
            for (int i = 0; i < BOARD_SIZE; i++) {
                for (int j = 0; j < BOARD_SIZE; j++) {
                    if (board[i][j] != '-') {
                        drawPiece(g, j * SQUARE_SIZE, i * SQUARE_SIZE, board[i][j]);
                    }
                }
            }
        }

        private void drawPiece(Graphics g, int x, int y, char piece) {
            // Implement drawing logic for each piece type
            // Example: g.drawString(String.valueOf(piece), x + SQUARE_SIZE / 2, y + SQUARE_SIZE / 2);
        }
    }
    
    private JPanel createGamePanel() {
        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new BorderLayout());

        JLabel test = new JLabel("Welcome to Chess", SwingConstants.CENTER);
        test.setFont(new Font("Arial", Font.BOLD, 20));
        gamePanel.add(test, BorderLayout.NORTH);

        // Create a panel for the chessboard (you need to implement ChessBoardPanel)
        ChessBoardPanel chessBoardPanel = new ChessBoardPanel();
        gamePanel.add(chessBoardPanel, BorderLayout.CENTER);

        JButton goBackButton = new JButton("Go Back");
        gamePanel.add(goBackButton, BorderLayout.SOUTH);

        return gamePanel;
    }

    private void handleMenuSelection(int players) {
        getContentPane().removeAll();
        revalidate();
        repaint();

        // Display the game interface panel
        getContentPane().add(createGamePanel());
        revalidate();
        repaint();
    }

    private static void initializeBoard() {
        // Set up the initial chess board
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = '-';
            }
        }

        // Place pawns
        for (int j = 0; j < BOARD_SIZE; j++) {
            board[1][j] = 'P';
            board[6][j] = 'p';
        }

        // Place other pieces
        char[] pieces = {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'};
        for (int j = 0; j < BOARD_SIZE; j++) {
            board[0][j] = pieces[j];
            board[7][j] = Character.toLowerCase(pieces[j]);
        }
    }

    private static void displayBoard() {
        System.out.println("  a b c d e f g h");
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.print((BOARD_SIZE - i) + " ");
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ChessGame();
        });
    }

    private boolean isValidMove(String move) {
        // Placeholder implementation, replace with actual validation logic
        return true;
    }

    private void makeMove(String move) {
        // Placeholder implementation, replace with actual move logic
        System.out.println("Move made: " + move);
    }

    private void playTwoPlayersGame() {
        Scanner scanner = new Scanner(System.in);  // Create a Scanner instance
        initializeBoard();
        displayBoard();

        while (true) {
            System.out.println("Enter move (e.g., e2 e4): ");
            String move = scanner.nextLine();

            if (isValidMove(move)) {
                makeMove(move);
                displayBoard();
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
    }
}
