import javax.swing.*;
import java.awt.*;

private void handleMenuSelection(int players) {
        getContentPane().removeAll();
        revalidate();
        repaint();

        // Display the game interface panel
        getContentPane().add(createGamePanel());
        revalidate();
        repaint();
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
        private static final int SQUARE_SIZE = 50; // Adjust this based on your preference
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
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChessGame(); // Close the rules window
            }
        });
        gamePanel.add(goBackButton, BorderLayout.SOUTH);

        return gamePanel;
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