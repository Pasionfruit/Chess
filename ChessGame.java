import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Scanner;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ChessGame extends JFrame {
    private static final int BOARD_SIZE = 8;
    private static char[][] board = new char[BOARD_SIZE][BOARD_SIZE];

    public ChessGame() {
        setTitle("Chess Game");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel menuPanel = createMenuPanel();
        add(menuPanel);

        // Center the frame on the screen
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createMenuPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Welcome to Chess", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(5, 1));
        JButton onePlayerButton = new JButton("One Player");
        JButton twoPlayersButton = new JButton("Two Players");
        JButton rulesButton = new JButton("Rules");
        JButton startGameButton = new JButton("Start");

        JPanel durationPanel = new JPanel(new GridLayout(1, 5));
        JButton oneMinuteButton = new JButton("1 MIN");
        JButton threeMinuteButton = new JButton("3 MIN");
        JButton fiveMinuteButton = new JButton("5 MIN");
        JButton tenMinuteButton = new JButton("10 MIN");
        JButton noTimerButton = new JButton("∞");


        onePlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleMenuSelection(1);
            }
        });

        twoPlayersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleMenuSelection(2);
            }
        });

        rulesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showRulesDialog();
            }
        });

        durationPanel.add(oneMinuteButton);
        durationPanel.add(threeMinuteButton);
        durationPanel.add(fiveMinuteButton);
        durationPanel.add(tenMinuteButton);
        durationPanel.add(noTimerButton);

        buttonPanel.add(onePlayerButton);
        buttonPanel.add(twoPlayersButton);
        buttonPanel.add(rulesButton);
        buttonPanel.add(durationPanel);
        buttonPanel.add(startGameButton);

        panel.add(buttonPanel, BorderLayout.CENTER);

        return panel;
    }

    private void showRulesDialog() {
        JFrame rulesFrame = new JFrame("Chess Rules");
        rulesFrame.setSize(800, 600);
        rulesFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        try {
            // Load the image from file
            File imageFile = new File("Chess_Rules.jpg");
            Image image = ImageIO.read(imageFile);

            // Create a JLabel to display the image
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

    private void handleMenuSelection(int players) {
        getContentPane().removeAll();
        revalidate();
        repaint();

        if (players == 1) {
            System.out.println("One player mode selected.");
            // Add logic for one-player mode
        } else if (players == 2) {
            System.out.println("Two players mode selected.");
            playTwoPlayersGame();  // Removed the argument
        } else if (players == 3) {
            System.out.println("You made it here");
            playTwoPlayersGame();  // Removed the argument
        } else {
            System.out.println("Invalid choice. Exiting the game.");
            System.exit(0);
        }
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
}
