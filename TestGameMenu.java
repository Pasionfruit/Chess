import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout.*;
import javax.swing.LayoutStyle.*;

public class TestGameMenu extends JFrame {

    /* ********************************************************************** *\
     * ************************ Variable Declaration ************************ *
    \* ********************************************************************** */

    // ************************** menuBar Variables ************************* //
    private JMenuBar menuBar;
        private JMenu TestGameMenuMenu;
            private JMenuItem aboutMenuItem;
            private JMenuItem quitMenuItem;
        private JMenu fileMenu;
            private JMenuItem newGameMenuItem;
            private JMenuItem loadGameMenuItem;
            private JMenuItem saveGameMenuItem;
        private JMenu viewMenu;
            private JMenuItem fullScreenMenuItem;
            private JMenuItem zoomInMenuItem;
            private JMenuItem zoomOutMenuItem;
        private JMenu helpMenu;
            private JMenuItem rulesMenuItem;

    // ********************* Variables in gameMenuPanel ********************* //
    private JPanel gameMenuPanel;
        private JPanel leftGameMenuPanel;
            private JPanel topLeftGameMenuPanel;
                private JLabel chessLabel;
            private JPanel bottomLeftGameMenuPanel;
                private JPanel leftGameMenuImagePanel;
                    private JLabel leftGameMenuImageLabel;
                private JPanel gameMenuButtonPanel;
                    private JButton newGameButton, loadGameButton, rulesButton;
        private JPanel rightGameMenuPanel;
            private JLabel rightGameMenuImageLabel;

    // ******************** Variables for gameBoardPanel ******************** //
    // Main panel
    private JPanel gamePanel;
        // Panel to the left of the gameBoardPanel
        private JPanel leftGamePanel;
            private JLabel eightLabel, sevenLabel, sixLabel, fiveLabel, fourLabel, threeLabel, twoLabel, oneLabel;
        // Panel above the gameBoardPanel
        private JPanel topGamePanel;
            private JLabel aLabel, bLabel, cLabel, dLabel, eLabel, fLabel, gLabel, hLabel;
        // Panel to the right of the gameBoardPanel
        private JPanel rightGamePanel;
            private JLabel aLabel2, bLabel2, cLabel2, dLabel2, eLabel2, fLabel2, gLabel2, hLabel2;
        // Panel below the gameBoardPanel
        private JPanel bottomGamePanel;
            private JLabel eightLabel2, sevenLabel2, sixLabel2, fiveLabel2, fourLabel2, threeLabel2, twoLabel2, oneLabel2;
        // Panel that holds the chessboard and occupies the center of gamePanel
        private JPanel gameBoardPanel;
            private JLabel a1Label, a2Label, a3Label, a4Label, a5Label, a6Label, a7Label, a8Label;
            private JLabel b1Label, b2Label, b3Label, b4Label, b5Label, b6Label, b7Label, b8Label;
            private JLabel c1Label, c2Label, c3Label, c4Label, c5Label, c6Label, c7Label, c8Label;
            private JLabel d1Label, d2Label, d3Label, d4Label, d5Label, d6Label, d7Label, d8Label;
            private JLabel e1Label, e2Label, e3Label, e4Label, e5Label, e6Label, e7Label, e8Label;
            private JLabel f1Label, f2Label, f3Label, f4Label, f5Label, f6Label, f7Label, f8Label;
            private JLabel g1Label, g2Label, g3Label, g4Label, g5Label, g6Label, g7Label, g8Label;
            private JLabel h1Label,h2Label, h3Label, h4Label, h5Label, h6Label, h7Label, h8Label;

    // ******************* Variables in gameOptionsDialog ******************* //
    private JDialog gameOptionsDialog;
        private JLabel numPlayersLabel;
        private JComboBox<String> numPlayersComboBox;
        private JLabel timeForMoveLabel;
        private JComboBox<String> timeForMoveComboBox;
        private JLabel difficultyLabel;
        private JComboBox<String> difficultyComboBox;
        private JLabel colorLabel;
        private JComboBox<String> colorComboBox;
        private JButton startGameButton;
        private JButton cancelButton;

    // ******************* newGameWarningDialog Variables ******************* //
    private JDialog newGameWarningDialog;
        private JScrollPane topnewGameWarningDialogScrollPane;
            private JTextPane newGameWarningDialogTextPane;
    private JPanel bottomnewGameWarningDialogPanel;
        private JButton yesNewGameButton;
        private JButton noNewGameButton;
    
    // ************************ Additional Variables ************************ //
    String currentCard = null;
    boolean zoomedIn = false;
    // ******************** End of variables declaration ******************** //

    // Constructor that calls the method initComponents() to setup the JFrame 
    //  and its contents.
    public TestGameMenu() {
        initComponents();
        setPreferredSize(new Dimension(500,298));
        pack();
    }
                             
    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chess Game");
        setResizable(true);
        getContentPane().setLayout(new CardLayout());

        createMenuBar();
        createGameOptionsDialog();
        createGameMenuPanel();
        createGamePanel();
        getContentPane().add(gameMenuPanel, "gameMenuCard");
        getContentPane().add(gamePanel, "gamePanelCard");
        setJMenuBar(menuBar);
        currentCard = "gameMenuCard";
        pack();
    }// </editor-fold>                        

/* ************************************************************************** *\
 * ************************ Design Component Methods ************************ *
\* ************************************************************************** */
    private void createMenuBar()
    {
        menuBar = new JMenuBar();

        TestGameMenuMenu = new JMenu("TestGameMenu");
        aboutMenuItem = new JMenuItem("About TestGameMenu");
        aboutMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        TestGameMenuMenu.add(aboutMenuItem);
        quitMenuItem = new JMenuItem("Quit TestGameMenu");
        quitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                quitMenuItemActionPerformed(evt);
            }
        });
        TestGameMenuMenu.add(quitMenuItem);

        menuBar.add(TestGameMenuMenu);

        fileMenu = new JMenu("File");
        newGameMenuItem = new JMenuItem("New Game");
        newGameMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                newGameMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(newGameMenuItem);

        loadGameMenuItem = new JMenuItem("Load Game");
        loadGameMenuItem.addActionListener(new ActionListener () {
            public void actionPerformed(ActionEvent evt)
            {
                loadGameMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(loadGameMenuItem);

        saveGameMenuItem = new JMenuItem("Save Game");
        saveGameMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt)
            {
                saveGameMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(saveGameMenuItem);

        menuBar.add(fileMenu);

        viewMenu = new JMenu("View");
        fullScreenMenuItem = new JMenuItem("Enter Full-Screen");
        fullScreenMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                fullScreenMenuItemActionPerformed(evt);
            }
        });
        viewMenu.add(fullScreenMenuItem);

        zoomInMenuItem = new JMenuItem("Zoom In");
        zoomInMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt)
            {
                zoomInMenuItemActionPerformed(evt);
            }
        });
        viewMenu.add(zoomInMenuItem);

        zoomOutMenuItem = new JMenuItem("Zoom Out");
        zoomOutMenuItem.setEnabled(false);
        zoomOutMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                zoomOutMenuItemActionPerformed(evt);
            }
        });
        viewMenu.add(zoomOutMenuItem);

        menuBar.add(viewMenu);

        helpMenu = new JMenu("Help");
        rulesMenuItem = new JMenuItem("Rules");
        rulesMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                rulesMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(rulesMenuItem);

        menuBar.add(helpMenu);
    }

    private void createGameMenuPanel()
    {
        // Create a JPanel to hold the game menu components and containers
        gameMenuPanel = new JPanel();
        gameMenuPanel.setBackground(new Color(81, 38, 14));
        gameMenuPanel.setMaximumSize(null);
        gameMenuPanel.setMinimumSize(new Dimension(500,250));
        gameMenuPanel.setLayout(new GridLayout());

        leftGameMenuPanel = new JPanel();
        leftGameMenuPanel.setBackground(new Color(81, 38, 14));
        leftGameMenuPanel.setMaximumSize(null);
        leftGameMenuPanel.setMinimumSize(new Dimension(250,250));
        leftGameMenuPanel.setLayout(new GridLayout(2, 1));

        topLeftGameMenuPanel = new JPanel();
        topLeftGameMenuPanel.setBackground(new Color(81, 38, 14));
        topLeftGameMenuPanel.setMaximumSize(null);
        topLeftGameMenuPanel.setMinimumSize(new Dimension(250,125));
        topLeftGameMenuPanel.setLayout(new BorderLayout());

        chessLabel = new JLabel("Chess");
        chessLabel.setFont(new Font("Luminari", 1, 75)); // NOI18N
        chessLabel.setForeground(new Color(221, 221, 211));
        chessLabel.setHorizontalAlignment(SwingConstants.CENTER);
        chessLabel.setMaximumSize(null);
        chessLabel.setMinimumSize(new Dimension(250, 125));
        chessLabel.setPreferredSize(new Dimension(250, 125));
        topLeftGameMenuPanel.add(chessLabel, BorderLayout.CENTER);

        leftGameMenuPanel.add(topLeftGameMenuPanel);

        bottomLeftGameMenuPanel = new JPanel();
        bottomLeftGameMenuPanel.setBackground(new Color(81, 38, 14));
        bottomLeftGameMenuPanel.setMaximumSize(null);
        bottomLeftGameMenuPanel.setMinimumSize(new Dimension(125,125));
        bottomLeftGameMenuPanel.setLayout(new GridLayout(1, 2));

        leftGameMenuImagePanel = new JPanel();
        leftGameMenuImagePanel.setBackground(new Color(81, 38, 14));
        leftGameMenuImagePanel.setMaximumSize(null);
        leftGameMenuImagePanel.setMinimumSize(new Dimension(125,125));
        leftGameMenuImagePanel.setLayout(new BorderLayout());

        leftGameMenuImageLabel = new JLabel();
        leftGameMenuImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        leftGameMenuImageLabel.setMaximumSize(null);
        leftGameMenuImageLabel.setMinimumSize(new Dimension(125, 125));
        leftGameMenuImageLabel.setPreferredSize(new Dimension(125, 125));
        leftGameMenuImagePanel.add(leftGameMenuImageLabel, BorderLayout.CENTER);

        bottomLeftGameMenuPanel.add(leftGameMenuImagePanel);

        gameMenuButtonPanel = new JPanel();
        gameMenuButtonPanel.setBackground(new Color(81, 38, 14));
        gameMenuButtonPanel.setMaximumSize(null);
        gameMenuButtonPanel.setMinimumSize(new Dimension(125, 125));
        gameMenuButtonPanel.setLayout(new GridLayout(4, 1));

        newGameButton = new JButton("New Game");
        newGameButton.setBackground(new Color(221, 221, 221));
        newGameButton.setForeground(new Color(0, 0, 0));
        newGameButton.setToolTipText("Start a new game");
        newGameButton.setFocusable(false);
        newGameButton.setMaximumSize(null);
        newGameButton.setMinimumSize(new Dimension(125, 27));
        newGameButton.setPreferredSize(new Dimension(125, 27));
        newGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                newGameButtonActionPerformed(evt);
            }
        });
        gameMenuButtonPanel.add(newGameButton);

        loadGameButton = new JButton("Load Game");
        loadGameButton.setBackground(new Color(221, 221, 221));
        loadGameButton.setForeground(new Color(0, 0, 0));
        loadGameButton.setToolTipText("Load existing save game");
        loadGameButton.setFocusable(false);
        loadGameButton.setMaximumSize(null);
        loadGameButton.setMinimumSize(new Dimension(125, 27));
        loadGameButton.setPreferredSize(new Dimension(125, 27));
        loadGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                loadGameButtonActionPerformed(evt);
            }
        });
        gameMenuButtonPanel.add(loadGameButton);

        rulesButton = new JButton("Rules");
        rulesButton.setBackground(new Color(221, 221, 221));
        rulesButton.setForeground(new Color(0, 0, 0));
        rulesButton.setToolTipText("Display game rules");
        rulesButton.setFocusable(false);
        rulesButton.setMaximumSize(null);
        rulesButton.setMinimumSize(new Dimension(125, 27));
        rulesButton.setPreferredSize(new Dimension(125, 27));
        rulesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                rulesButtonActionPerformed(evt);
            }
        });
        gameMenuButtonPanel.add(rulesButton);

        bottomLeftGameMenuPanel.add(gameMenuButtonPanel);

        leftGameMenuPanel.add(bottomLeftGameMenuPanel);

        gameMenuPanel.add(leftGameMenuPanel);

        rightGameMenuPanel = new JPanel();
        rightGameMenuPanel.setBackground(new Color(81, 38, 14));
        rightGameMenuPanel.setMaximumSize(null);
        rightGameMenuPanel.setMinimumSize(new Dimension(250, 250));
        rightGameMenuPanel.setLayout(new BorderLayout());

        rightGameMenuImageLabel = new JLabel();
        rightGameMenuImageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        rightGameMenuImageLabel.setMaximumSize(null);
        rightGameMenuImageLabel.setMinimumSize(new Dimension(250, 250));
        rightGameMenuImageLabel.setPreferredSize(new Dimension(250, 250));
        rightGameMenuPanel.add(rightGameMenuImageLabel, BorderLayout.CENTER);

        gameMenuPanel.add(rightGameMenuPanel);
    }

    private void createGamePanel()
    {
        GridBagConstraints gridBagConstraints;
        gamePanel = new JPanel();
        gamePanel.setBackground(new Color(81, 38, 14));
        gamePanel.setMinimumSize(new Dimension(500, 500));
        gamePanel.setPreferredSize(new Dimension(500, 500));
        gamePanel.setLayout(new GridBagLayout());

        gameBoardPanel = new JPanel();
        gameBoardPanel.setBackground(new Color(196, 164, 132));
        gameBoardPanel.setMaximumSize(new Dimension(800, 800));
        gameBoardPanel.setMinimumSize(new Dimension(400, 400));
        gameBoardPanel.setPreferredSize(new Dimension(400, 400));
        gameBoardPanel.setLayout(new GridLayout(8, 8));

        a8Label = new JLabel();
        a8Label.setHorizontalAlignment(SwingConstants.CENTER);
        a8Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown light_1x_ns.png"))); // NOI18N
        a8Label.setToolTipText("A8");
        gameBoardPanel.add(a8Label);

        b8Label = new JLabel();
        b8Label.setHorizontalAlignment(SwingConstants.CENTER);
        b8Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown dark_1x_ns.png"))); // NOI18N
        b8Label.setToolTipText("B8");
        gameBoardPanel.add(b8Label);

        c8Label = new JLabel();
        c8Label.setHorizontalAlignment(SwingConstants.CENTER);
        c8Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown light_1x_ns.png"))); // NOI18N
        c8Label.setToolTipText("C8");
        gameBoardPanel.add(c8Label);

        d8Label = new JLabel();
        d8Label.setHorizontalAlignment(SwingConstants.CENTER);
        d8Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown dark_1x_ns.png"))); // NOI18N
        d8Label.setToolTipText("D8");
        gameBoardPanel.add(d8Label);

        e8Label = new JLabel();
        e8Label.setHorizontalAlignment(SwingConstants.CENTER);
        e8Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown light_1x_ns.png"))); // NOI18N
        e8Label.setToolTipText("E8");
        gameBoardPanel.add(e8Label);

        f8Label = new JLabel();
        f8Label.setHorizontalAlignment(SwingConstants.CENTER);
        f8Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown dark_1x_ns.png"))); // NOI18N
        f8Label.setToolTipText("F8");
        gameBoardPanel.add(f8Label);

        g8Label = new JLabel();
        g8Label.setHorizontalAlignment(SwingConstants.CENTER);
        g8Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown light_1x_ns.png"))); // NOI18N
        g8Label.setToolTipText("G8");
        gameBoardPanel.add(g8Label);

        h8Label = new JLabel();
        h8Label.setHorizontalAlignment(SwingConstants.CENTER);
        h8Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown dark_1x_ns.png"))); // NOI18N
        h8Label.setToolTipText("H8");
        gameBoardPanel.add(h8Label);

        a7Label = new JLabel();
        a7Label.setHorizontalAlignment(SwingConstants.CENTER);
        a7Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown dark_1x_ns.png"))); // NOI18N
        a7Label.setToolTipText("A7");
        gameBoardPanel.add(a7Label);

        b7Label = new JLabel();
        b7Label.setHorizontalAlignment(SwingConstants.CENTER);
        b7Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown light_1x_ns.png"))); // NOI18N
        b7Label.setToolTipText("B7");
        gameBoardPanel.add(b7Label);

        c7Label = new JLabel();
        c7Label.setHorizontalAlignment(SwingConstants.CENTER);
        c7Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown dark_1x_ns.png"))); // NOI18N
        c7Label.setToolTipText("C7");
        gameBoardPanel.add(c7Label);

        d7Label = new JLabel();
        d7Label.setHorizontalAlignment(SwingConstants.CENTER);
        d7Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown light_1x_ns.png"))); // NOI18N
        d7Label.setToolTipText("D7");
        gameBoardPanel.add(d7Label);

        e7Label = new JLabel();
        e7Label.setHorizontalAlignment(SwingConstants.CENTER);
        e7Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown dark_1x_ns.png"))); // NOI18N
        e7Label.setToolTipText("E7");
        gameBoardPanel.add(e7Label);

        f7Label = new JLabel();
        f7Label.setHorizontalAlignment(SwingConstants.CENTER);
        f7Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown light_1x_ns.png"))); // NOI18N
        f7Label.setToolTipText("F7");
        gameBoardPanel.add(f7Label);

        g7Label = new JLabel();
        g7Label.setHorizontalAlignment(SwingConstants.CENTER);
        g7Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown dark_1x_ns.png"))); // NOI18N
        g7Label.setToolTipText("G7");
        gameBoardPanel.add(g7Label);

        h7Label = new JLabel();
        h7Label.setHorizontalAlignment(SwingConstants.CENTER);
        h7Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown light_1x_ns.png"))); // NOI18N
        h7Label.setToolTipText("H7");
        gameBoardPanel.add(h7Label);

        a6Label = new JLabel();
        a6Label.setHorizontalAlignment(SwingConstants.CENTER);
        a6Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown light_1x_ns.png"))); // NOI18N
        a6Label.setToolTipText("A6");
        gameBoardPanel.add(a6Label);

        b6Label = new JLabel();
        b6Label.setHorizontalAlignment(SwingConstants.CENTER);
        b6Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown dark_1x_ns.png"))); // NOI18N
        b6Label.setToolTipText("B6");
        gameBoardPanel.add(b6Label);

        c6Label = new JLabel();
        c6Label.setHorizontalAlignment(SwingConstants.CENTER);
        c6Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown light_1x_ns.png"))); // NOI18N
        c6Label.setToolTipText("C6");
        gameBoardPanel.add(c6Label);

        d6Label = new JLabel();
        d6Label.setHorizontalAlignment(SwingConstants.CENTER);
        d6Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown dark_1x_ns.png"))); // NOI18N
        d6Label.setToolTipText("D6");
        gameBoardPanel.add(d6Label);

        e6Label = new JLabel();
        e6Label.setHorizontalAlignment(SwingConstants.CENTER);
        e6Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown light_1x_ns.png"))); // NOI18N
        e6Label.setToolTipText("E6");
        gameBoardPanel.add(e6Label);

        f6Label = new JLabel();
        f6Label.setHorizontalAlignment(SwingConstants.CENTER);
        f6Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown dark_1x_ns.png"))); // NOI18N
        f6Label.setToolTipText("F6");
        gameBoardPanel.add(f6Label);

        g6Label = new JLabel();
        g6Label.setHorizontalAlignment(SwingConstants.CENTER);
        g6Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown light_1x_ns.png"))); // NOI18N
        g6Label.setToolTipText("G6");
        gameBoardPanel.add(g6Label);

        h6Label = new JLabel();
        h6Label.setHorizontalAlignment(SwingConstants.CENTER);
        h6Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown dark_1x_ns.png"))); // NOI18N
        h6Label.setToolTipText("H6");
        gameBoardPanel.add(h6Label);

        a5Label = new JLabel();
        a5Label.setHorizontalAlignment(SwingConstants.CENTER);
        a5Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown dark_1x_ns.png"))); // NOI18N
        a5Label.setToolTipText("A5");
        gameBoardPanel.add(a5Label);

        b5Label = new JLabel();
        b5Label.setHorizontalAlignment(SwingConstants.CENTER);
        b5Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown light_1x_ns.png"))); // NOI18N
        b5Label.setToolTipText("B5");
        gameBoardPanel.add(b5Label);

        c5Label = new JLabel();
        c5Label.setHorizontalAlignment(SwingConstants.CENTER);
        c5Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown dark_1x_ns.png"))); // NOI18N
        c5Label.setToolTipText("C5");
        gameBoardPanel.add(c5Label);

        d5Label = new JLabel();
        d5Label.setHorizontalAlignment(SwingConstants.CENTER);
        d5Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown light_1x_ns.png"))); // NOI18N
        d5Label.setToolTipText("D5");
        gameBoardPanel.add(d5Label);

        e5Label = new JLabel();
        e5Label.setHorizontalAlignment(SwingConstants.CENTER);
        e5Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown dark_1x_ns.png"))); // NOI18N
        e5Label.setToolTipText("E5");
        gameBoardPanel.add(e5Label);

        f5Label = new JLabel();
        f5Label.setHorizontalAlignment(SwingConstants.CENTER);
        f5Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown light_1x_ns.png"))); // NOI18N
        f5Label.setToolTipText("F5");
        gameBoardPanel.add(f5Label);

        g5Label = new JLabel();
        g5Label.setHorizontalAlignment(SwingConstants.CENTER);
        g5Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown dark_1x_ns.png"))); // NOI18N
        g5Label.setToolTipText("G5");
        gameBoardPanel.add(g5Label);

        h5Label = new JLabel();
        h5Label.setHorizontalAlignment(SwingConstants.CENTER);
        h5Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown light_1x_ns.png"))); // NOI18N
        h5Label.setToolTipText("H5");
        gameBoardPanel.add(h5Label);

        a4Label = new JLabel();
        a4Label.setHorizontalAlignment(SwingConstants.CENTER);
        a4Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown light_1x_ns.png"))); // NOI18N
        a4Label.setToolTipText("A4");
        gameBoardPanel.add(a4Label);

        b4Label = new JLabel();
        b4Label.setHorizontalAlignment(SwingConstants.CENTER);
        b4Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown dark_1x_ns.png"))); // NOI18N
        b4Label.setToolTipText("B4");
        gameBoardPanel.add(b4Label);

        c4Label = new JLabel();
        c4Label.setHorizontalAlignment(SwingConstants.CENTER);
        c4Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown light_1x_ns.png"))); // NOI18N
        c4Label.setToolTipText("C4");
        gameBoardPanel.add(c4Label);

        d4Label = new JLabel();
        d4Label.setHorizontalAlignment(SwingConstants.CENTER);
        d4Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown dark_1x_ns.png"))); // NOI18N
        d4Label.setToolTipText("D4");
        gameBoardPanel.add(d4Label);

        e4Label = new JLabel();
        e4Label.setHorizontalAlignment(SwingConstants.CENTER);
        e4Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown light_1x_ns.png"))); // NOI18N
        e4Label.setToolTipText("E4");
        gameBoardPanel.add(e4Label);

        f4Label = new JLabel();
        f4Label.setHorizontalAlignment(SwingConstants.CENTER);
        f4Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown dark_1x_ns.png"))); // NOI18N
        f4Label.setToolTipText("F4");
        gameBoardPanel.add(f4Label);

        g4Label = new JLabel();
        g4Label.setHorizontalAlignment(SwingConstants.CENTER);
        g4Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown light_1x_ns.png"))); // NOI18N
        g4Label.setToolTipText("G4");
        gameBoardPanel.add(g4Label);

        h4Label = new JLabel();
        h4Label.setHorizontalAlignment(SwingConstants.CENTER);
        h4Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown dark_1x_ns.png"))); // NOI18N
        h4Label.setToolTipText("H4");
        gameBoardPanel.add(h4Label);

        a3Label = new JLabel();
        a3Label.setHorizontalAlignment(SwingConstants.CENTER);
        a3Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown dark_1x_ns.png"))); // NOI18N
        a3Label.setToolTipText("A3");
        gameBoardPanel.add(a3Label);

        b3Label = new JLabel();
        b3Label.setHorizontalAlignment(SwingConstants.CENTER);
        b3Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown light_1x_ns.png"))); // NOI18N
        b3Label.setToolTipText("B3");
        gameBoardPanel.add(b3Label);

        c3Label = new JLabel();
        c3Label.setHorizontalAlignment(SwingConstants.CENTER);
        c3Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown dark_1x_ns.png"))); // NOI18N
        c3Label.setToolTipText("C3");
        gameBoardPanel.add(c3Label);

        d3Label = new JLabel();
        d3Label.setHorizontalAlignment(SwingConstants.CENTER);
        d3Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown light_1x_ns.png"))); // NOI18N
        d3Label.setToolTipText("D3");
        gameBoardPanel.add(d3Label);

        e3Label = new JLabel();
        e3Label.setHorizontalAlignment(SwingConstants.CENTER);
        e3Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown dark_1x_ns.png"))); // NOI18N
        e3Label.setToolTipText("E3");
        gameBoardPanel.add(e3Label);

        f3Label = new JLabel();
        f3Label.setHorizontalAlignment(SwingConstants.CENTER);
        f3Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown light_1x_ns.png"))); // NOI18N
        f3Label.setToolTipText("F3");
        gameBoardPanel.add(f3Label);

        g3Label = new JLabel();
        g3Label.setHorizontalAlignment(SwingConstants.CENTER);
        g3Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown dark_1x_ns.png"))); // NOI18N
        g3Label.setToolTipText("G3");
        gameBoardPanel.add(g3Label);

        h3Label = new JLabel();
        h3Label.setHorizontalAlignment(SwingConstants.CENTER);
        h3Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown light_1x_ns.png"))); // NOI18N
        h3Label.setToolTipText("H3");
        gameBoardPanel.add(h3Label);

        a2Label = new JLabel();
        a2Label.setHorizontalAlignment(SwingConstants.CENTER);
        a2Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown light_1x_ns.png"))); // NOI18N
        a2Label.setToolTipText("A2");
        gameBoardPanel.add(a2Label);

        b2Label = new JLabel();
        b2Label.setHorizontalAlignment(SwingConstants.CENTER);
        b2Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown dark_1x_ns.png"))); // NOI18N
        b2Label.setToolTipText("B2");
        gameBoardPanel.add(b2Label);

        c2Label = new JLabel();
        c2Label.setHorizontalAlignment(SwingConstants.CENTER);
        c2Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown light_1x_ns.png"))); // NOI18N
        c2Label.setToolTipText("C2");
        gameBoardPanel.add(c2Label);

        d2Label = new JLabel();
        d2Label.setHorizontalAlignment(SwingConstants.CENTER);
        d2Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown dark_1x_ns.png"))); // NOI18N
        d2Label.setToolTipText("D2");
        gameBoardPanel.add(d2Label);

        e2Label = new JLabel();
        e2Label.setHorizontalAlignment(SwingConstants.CENTER);
        e2Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown light_1x_ns.png"))); // NOI18N
        e2Label.setToolTipText("E2");
        gameBoardPanel.add(e2Label);

        f2Label = new JLabel();
        f2Label.setHorizontalAlignment(SwingConstants.CENTER);
        f2Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown dark_1x_ns.png"))); // NOI18N
        f2Label.setToolTipText("F2");
        gameBoardPanel.add(f2Label);

        g2Label = new JLabel();
        g2Label.setHorizontalAlignment(SwingConstants.CENTER);
        g2Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown light_1x_ns.png"))); // NOI18N
        g2Label.setToolTipText("G2");
        gameBoardPanel.add(g2Label);

        h2Label = new JLabel();
        h2Label.setHorizontalAlignment(SwingConstants.CENTER);
        h2Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown dark_1x_ns.png"))); // NOI18N
        h2Label.setToolTipText("H2");
        gameBoardPanel.add(h2Label);

        a1Label = new JLabel();
        a1Label.setHorizontalAlignment(SwingConstants.CENTER);
        a1Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown dark_1x_ns.png"))); // NOI18N
        a1Label.setToolTipText("A1");
        gameBoardPanel.add(a1Label);

        b1Label = new JLabel();
        b1Label.setHorizontalAlignment(SwingConstants.CENTER);
        b1Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown light_1x_ns.png"))); // NOI18N
        b1Label.setToolTipText("B1");
        gameBoardPanel.add(b1Label);

        c1Label = new JLabel();
        c1Label.setHorizontalAlignment(SwingConstants.CENTER);
        c1Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown dark_1x_ns.png"))); // NOI18N
        c1Label.setToolTipText("C1");
        gameBoardPanel.add(c1Label);

        d1Label = new JLabel();
        d1Label.setHorizontalAlignment(SwingConstants.CENTER);
        d1Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown light_1x_ns.png"))); // NOI18N
        d1Label.setToolTipText("D1");
        gameBoardPanel.add(d1Label);

        e1Label = new JLabel();
        e1Label.setHorizontalAlignment(SwingConstants.CENTER);
        e1Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown dark_1x_ns.png"))); // NOI18N
        e1Label.setToolTipText("E1");
        gameBoardPanel.add(e1Label);

        f1Label = new JLabel();
        f1Label.setHorizontalAlignment(SwingConstants.CENTER);
        f1Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown light_1x_ns.png"))); // NOI18N
        f1Label.setToolTipText("F1");
        gameBoardPanel.add(f1Label);

        g1Label = new JLabel();
        g1Label.setHorizontalAlignment(SwingConstants.CENTER);
        g1Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown dark_1x_ns.png"))); // NOI18N
        g1Label.setToolTipText("G1");
        gameBoardPanel.add(g1Label);

        h1Label = new JLabel();
        h1Label.setHorizontalAlignment(SwingConstants.CENTER);
        h1Label.setIcon(new ImageIcon(getClass().getResource("/assets/JohnPablok Cburnett Chess set/PNGs/No shadow/1x/square brown light_1x_ns.png"))); // NOI18N
        h1Label.setToolTipText("H1");
        gameBoardPanel.add(h1Label);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gamePanel.add(gameBoardPanel, gridBagConstraints);

        leftGamePanel = new JPanel();
        leftGamePanel.setBackground(new Color(81, 38, 14));
        leftGamePanel.setPreferredSize(new Dimension(50, 400));
        leftGamePanel.setLayout(new GridLayout(8, 1));

        eightLabel = new JLabel("8");
        eightLabel.setFont(new Font("Helvetica Neue", 0, 24)); // NOI18N
        eightLabel.setForeground(new Color(0, 0, 0));
        eightLabel.setHorizontalAlignment(SwingConstants.CENTER);
        eightLabel.setMaximumSize(new Dimension(100, 100));
        eightLabel.setMinimumSize(new Dimension(50, 50));
        eightLabel.setPreferredSize(new Dimension(50, 50));
        leftGamePanel.add(eightLabel);

        sevenLabel = new JLabel("7");
        sevenLabel.setFont(new Font("Helvetica Neue", 0, 24)); // NOI18N
        sevenLabel.setForeground(new Color(0, 0, 0));
        sevenLabel.setHorizontalAlignment(SwingConstants.CENTER);
        sevenLabel.setMaximumSize(new Dimension(100, 100));
        sevenLabel.setMinimumSize(new Dimension(50, 50));
        sevenLabel.setPreferredSize(new Dimension(50, 50));
        leftGamePanel.add(sevenLabel);

        sixLabel = new JLabel("6");
        sixLabel.setFont(new Font("Helvetica Neue", 0, 24)); // NOI18N
        sixLabel.setForeground(new Color(0, 0, 0));
        sixLabel.setHorizontalAlignment(SwingConstants.CENTER);
        sixLabel.setMaximumSize(new Dimension(100, 100));
        sixLabel.setMinimumSize(new Dimension(50, 50));
        sixLabel.setPreferredSize(new Dimension(50, 50));
        leftGamePanel.add(sixLabel);

        fiveLabel = new JLabel("5");
        fiveLabel.setFont(new Font("Helvetica Neue", 0, 24)); // NOI18N
        fiveLabel.setForeground(new Color(0, 0, 0));
        fiveLabel.setHorizontalAlignment(SwingConstants.CENTER);
        fiveLabel.setMaximumSize(new Dimension(100, 100));
        fiveLabel.setMinimumSize(new Dimension(50, 50));
        fiveLabel.setPreferredSize(new Dimension(50, 50));
        leftGamePanel.add(fiveLabel);

        fourLabel = new JLabel("4");
        fourLabel.setFont(new Font("Helvetica Neue", 0, 24)); // NOI18N
        fourLabel.setForeground(new Color(0, 0, 0));
        fourLabel.setHorizontalAlignment(SwingConstants.CENTER);
        fourLabel.setMaximumSize(new Dimension(100, 100));
        fourLabel.setMinimumSize(new Dimension(50, 50));
        fourLabel.setPreferredSize(new Dimension(50, 50));
        leftGamePanel.add(fourLabel);

        threeLabel = new JLabel("3");
        threeLabel.setFont(new Font("Helvetica Neue", 0, 24)); // NOI18N
        threeLabel.setForeground(new Color(0, 0, 0));
        threeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        threeLabel.setMaximumSize(new Dimension(100, 100));
        threeLabel.setMinimumSize(new Dimension(50, 50));
        threeLabel.setPreferredSize(new Dimension(50, 50));
        leftGamePanel.add(threeLabel);

        twoLabel = new JLabel("2");
        twoLabel.setFont(new Font("Helvetica Neue", 0, 24)); // NOI18N
        twoLabel.setForeground(new Color(0, 0, 0));
        twoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        twoLabel.setMaximumSize(new Dimension(100, 100));
        twoLabel.setMinimumSize(new Dimension(50, 50));
        twoLabel.setPreferredSize(new Dimension(50, 50));
        leftGamePanel.add(twoLabel);

        oneLabel = new JLabel("1");
        oneLabel.setFont(new Font("Helvetica Neue", 0, 24)); // NOI18N
        oneLabel.setForeground(new Color(0, 0, 0));
        oneLabel.setHorizontalAlignment(SwingConstants.CENTER);
        oneLabel.setMaximumSize(new Dimension(100, 100));
        oneLabel.setMinimumSize(new Dimension(50, 50));
        oneLabel.setPreferredSize(new Dimension(50, 50));
        leftGamePanel.add(oneLabel);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gamePanel.add(leftGamePanel, gridBagConstraints);

        topGamePanel = new JPanel();
        topGamePanel.setBackground(new Color(81, 38, 14));
        topGamePanel.setMinimumSize(new Dimension(400, 50));
        topGamePanel.setPreferredSize(new Dimension(400, 50));
        topGamePanel.setLayout(new GridLayout(1, 8));

        aLabel = new JLabel("A");
        aLabel.setFont(new Font("Helvetica Neue", 0, 24)); // NOI18N
        aLabel.setForeground(new Color(0, 0, 0));
        aLabel.setHorizontalAlignment(SwingConstants.CENTER);
        aLabel.setMaximumSize(new Dimension(100, 100));
        aLabel.setMinimumSize(new Dimension(50, 50));
        aLabel.setPreferredSize(new Dimension(50, 50));
        topGamePanel.add(aLabel);

        bLabel = new JLabel("B");
        bLabel.setFont(new Font("Helvetica Neue", 0, 24)); // NOI18N
        bLabel.setForeground(new Color(0, 0, 0));
        bLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bLabel.setMaximumSize(new Dimension(100, 100));
        bLabel.setMinimumSize(new Dimension(50, 50));
        bLabel.setPreferredSize(new Dimension(50, 50));
        topGamePanel.add(bLabel);

        cLabel = new JLabel("C");
        cLabel.setFont(new Font("Helvetica Neue", 0, 24)); // NOI18N
        cLabel.setForeground(new Color(0, 0, 0));
        cLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cLabel.setMaximumSize(new Dimension(100, 100));
        cLabel.setMinimumSize(new Dimension(50, 50));
        cLabel.setPreferredSize(new Dimension(50, 50));
        topGamePanel.add(cLabel);

        dLabel = new JLabel("D");
        dLabel.setFont(new Font("Helvetica Neue", 0, 24)); // NOI18N
        dLabel.setForeground(new Color(0, 0, 0));
        dLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dLabel.setMaximumSize(new Dimension(100, 100));
        dLabel.setMinimumSize(new Dimension(50, 50));
        dLabel.setPreferredSize(new Dimension(50, 50));
        topGamePanel.add(dLabel);

        eLabel = new JLabel("E");
        eLabel.setFont(new Font("Helvetica Neue", 0, 24)); // NOI18N
        eLabel.setForeground(new Color(0, 0, 0));
        eLabel.setHorizontalAlignment(SwingConstants.CENTER);
        eLabel.setMaximumSize(new Dimension(100, 100));
        eLabel.setMinimumSize(new Dimension(50, 50));
        eLabel.setPreferredSize(new Dimension(50, 50));
        topGamePanel.add(eLabel);

        fLabel = new JLabel("F");
        fLabel.setFont(new Font("Helvetica Neue", 0, 24)); // NOI18N
        fLabel.setForeground(new Color(0, 0, 0));
        fLabel.setHorizontalAlignment(SwingConstants.CENTER);
        fLabel.setMaximumSize(new Dimension(100, 100));
        fLabel.setMinimumSize(new Dimension(50, 50));
        fLabel.setPreferredSize(new Dimension(50, 50));
        topGamePanel.add(fLabel);

        gLabel = new JLabel("G");
        gLabel.setFont(new Font("Helvetica Neue", 0, 24)); // NOI18N
        gLabel.setForeground(new Color(0, 0, 0));
        gLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gLabel.setMaximumSize(new Dimension(100, 100));
        gLabel.setMinimumSize(new Dimension(50, 50));
        gLabel.setPreferredSize(new Dimension(50, 50));
        topGamePanel.add(gLabel);

        hLabel = new JLabel("H");
        hLabel.setFont(new Font("Helvetica Neue", 0, 24)); // NOI18N
        hLabel.setForeground(new Color(0, 0, 0));
        hLabel.setHorizontalAlignment(SwingConstants.CENTER);
        hLabel.setMaximumSize(new Dimension(100, 100));
        hLabel.setMinimumSize(new Dimension(50, 50));
        hLabel.setPreferredSize(new Dimension(50, 50));
        topGamePanel.add(hLabel);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gamePanel.add(topGamePanel, gridBagConstraints);

        rightGamePanel = new JPanel();
        rightGamePanel.setBackground(new Color(81, 38, 14));
        rightGamePanel.setPreferredSize(new Dimension(50, 400));
        rightGamePanel.setLayout(new GridLayout(8, 0));

        eightLabel2 = new JLabel("8");
        eightLabel2.setFont(new Font("Helvetica Neue", 0, 24)); // NOI18N
        eightLabel2.setForeground(new Color(0, 0, 0));
        eightLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        eightLabel2.setMaximumSize(new Dimension(100, 100));
        eightLabel2.setMinimumSize(new Dimension(50, 50));
        eightLabel2.setPreferredSize(new Dimension(50, 50));
        rightGamePanel.add(eightLabel2);

        sevenLabel2 = new JLabel("7");
        sevenLabel2.setFont(new Font("Helvetica Neue", 0, 24)); // NOI18N
        sevenLabel2.setForeground(new Color(0, 0, 0));
        sevenLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        sevenLabel2.setMaximumSize(new Dimension(100, 100));
        sevenLabel2.setMinimumSize(new Dimension(50, 50));
        sevenLabel2.setPreferredSize(new Dimension(50, 50));
        rightGamePanel.add(sevenLabel2);

        sixLabel2 = new JLabel("6");
        sixLabel2.setFont(new Font("Helvetica Neue", 0, 24)); // NOI18N
        sixLabel2.setForeground(new Color(0, 0, 0));
        sixLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        sixLabel2.setMaximumSize(new Dimension(100, 100));
        sixLabel2.setMinimumSize(new Dimension(50, 50));
        sixLabel2.setPreferredSize(new Dimension(50, 50));
        rightGamePanel.add(sixLabel2);

        fiveLabel2 = new JLabel("5");
        fiveLabel2.setFont(new Font("Helvetica Neue", 0, 24)); // NOI18N
        fiveLabel2.setForeground(new Color(0, 0, 0));
        fiveLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        fiveLabel2.setMaximumSize(new Dimension(100, 100));
        fiveLabel2.setMinimumSize(new Dimension(50, 50));
        fiveLabel2.setPreferredSize(new Dimension(50, 50));
        rightGamePanel.add(fiveLabel2);

        fourLabel2 = new JLabel("4");
        fourLabel2.setFont(new Font("Helvetica Neue", 0, 24)); // NOI18N
        fourLabel2.setForeground(new Color(0, 0, 0));
        fourLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        fourLabel2.setMaximumSize(new Dimension(100, 100));
        fourLabel2.setMinimumSize(new Dimension(50, 50));
        fourLabel2.setPreferredSize(new Dimension(50, 50));
        rightGamePanel.add(fourLabel2);

        threeLabel2 = new JLabel("3");
        threeLabel2.setFont(new Font("Helvetica Neue", 0, 24)); // NOI18N
        threeLabel2.setForeground(new Color(0, 0, 0));
        threeLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        threeLabel2.setMaximumSize(new Dimension(100, 100));
        threeLabel2.setMinimumSize(new Dimension(50, 50));
        threeLabel2.setPreferredSize(new Dimension(50, 50));
        rightGamePanel.add(threeLabel2);

        twoLabel2 = new JLabel("2");
        twoLabel2.setFont(new Font("Helvetica Neue", 0, 24)); // NOI18N
        twoLabel2.setForeground(new Color(0, 0, 0));
        twoLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        twoLabel2.setMaximumSize(new Dimension(100, 100));
        twoLabel2.setMinimumSize(new Dimension(50, 50));
        twoLabel2.setPreferredSize(new Dimension(50, 50));
        rightGamePanel.add(twoLabel2);

        oneLabel2 = new JLabel("1");
        oneLabel2.setFont(new Font("Helvetica Neue", 0, 24)); // NOI18N
        oneLabel2.setForeground(new Color(0, 0, 0));
        oneLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        oneLabel2.setMaximumSize(new Dimension(100, 100));
        oneLabel2.setMinimumSize(new Dimension(50, 50));
        oneLabel2.setPreferredSize(new Dimension(50, 50));
        rightGamePanel.add(oneLabel2);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gamePanel.add(rightGamePanel, gridBagConstraints);

        bottomGamePanel = new JPanel();
        bottomGamePanel.setBackground(new Color(81, 38, 14));
        bottomGamePanel.setPreferredSize(new Dimension(400, 50));
        bottomGamePanel.setLayout(new GridLayout(1, 8));

        aLabel2 = new JLabel("A");
        aLabel2.setFont(new Font("Helvetica Neue", 0, 24)); // NOI18N
        aLabel2.setForeground(new Color(0, 0, 0));
        aLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        aLabel2.setMaximumSize(new Dimension(100, 100));
        aLabel2.setMinimumSize(new Dimension(50, 50));
        aLabel2.setPreferredSize(new Dimension(50, 50));
        bottomGamePanel.add(aLabel2);

        bLabel2 = new JLabel("B");
        bLabel2.setFont(new Font("Helvetica Neue", 0, 24)); // NOI18N
        bLabel2.setForeground(new Color(0, 0, 0));
        bLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        bLabel2.setMaximumSize(new Dimension(100, 100));
        bLabel2.setMinimumSize(new Dimension(50, 50));
        bLabel2.setPreferredSize(new Dimension(50, 50));
        bottomGamePanel.add(bLabel2);

        cLabel2 = new JLabel("C");
        cLabel2.setFont(new Font("Helvetica Neue", 0, 24)); // NOI18N
        cLabel2.setForeground(new Color(0, 0, 0));
        cLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        cLabel2.setMaximumSize(new Dimension(100, 100));
        cLabel2.setMinimumSize(new Dimension(50, 50));
        cLabel2.setPreferredSize(new Dimension(50, 50));
        bottomGamePanel.add(cLabel2);

        dLabel2 = new JLabel("D");
        dLabel2.setFont(new Font("Helvetica Neue", 0, 24)); // NOI18N
        dLabel2.setForeground(new Color(0, 0, 0));
        dLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        dLabel2.setMaximumSize(new Dimension(100, 100));
        dLabel2.setMinimumSize(new Dimension(50, 50));
        dLabel2.setPreferredSize(new Dimension(50, 50));
        bottomGamePanel.add(dLabel2);

        eLabel2 = new JLabel("E");
        eLabel2.setFont(new Font("Helvetica Neue", 0, 24)); // NOI18N
        eLabel2.setForeground(new Color(0, 0, 0));
        eLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        eLabel2.setMaximumSize(new Dimension(100, 100));
        eLabel2.setMinimumSize(new Dimension(50, 50));
        eLabel2.setPreferredSize(new Dimension(50, 50));
        bottomGamePanel.add(eLabel2);

        fLabel2 = new JLabel("F");
        fLabel2.setFont(new Font("Helvetica Neue", 0, 24)); // NOI18N
        fLabel2.setForeground(new Color(0, 0, 0));
        fLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        fLabel2.setMaximumSize(new Dimension(100, 100));
        fLabel2.setMinimumSize(new Dimension(50, 50));
        fLabel2.setPreferredSize(new Dimension(50, 50));
        bottomGamePanel.add(fLabel2);

        gLabel2 = new JLabel("G");
        gLabel2.setFont(new Font("Helvetica Neue", 0, 24)); // NOI18N
        gLabel2.setForeground(new Color(0, 0, 0));
        gLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        gLabel2.setMaximumSize(new Dimension(100, 100));
        gLabel2.setMinimumSize(new Dimension(50, 50));
        gLabel2.setPreferredSize(new Dimension(50, 50));
        bottomGamePanel.add(gLabel2);

        hLabel2 = new JLabel("H");
        hLabel2.setFont(new Font("Helvetica Neue", 0, 24)); // NOI18N
        hLabel2.setForeground(new Color(0, 0, 0));
        hLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        hLabel2.setMaximumSize(new Dimension(100, 100));
        hLabel2.setMinimumSize(new Dimension(50, 50));
        hLabel2.setPreferredSize(new Dimension(50, 50));
        bottomGamePanel.add(hLabel2);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gamePanel.add(bottomGamePanel, gridBagConstraints);
    }

    private void createGameOptionsDialog()
    {
        // Create new gameOptionsDialog JDialog window
        gameOptionsDialog = new JDialog();
        gameOptionsDialog.setMinimumSize(new Dimension(242, 213));
        gameOptionsDialog.setResizable(false);

        // Create a JComboBox to allow player to select the number of players
        numPlayersLabel = new JLabel("Number of Players:");
        numPlayersComboBox = new JComboBox<>();
        numPlayersComboBox.setMaximumRowCount(3);
        numPlayersComboBox.setModel(new DefaultComboBoxModel<>(new String[] { "0", "1", "2" }));
        numPlayersComboBox.setSelectedIndex(1);
        numPlayersComboBox.setToolTipText("Select the number of players");
        numPlayersComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                numPlayersComboBoxActionPerformed(evt);
            }
        });

        // Create a JComboBox to allow player to select the time to make a move
        timeForMoveLabel = new JLabel("Time for Move:");
        timeForMoveComboBox = new JComboBox<>();
        timeForMoveComboBox.setMaximumRowCount(4);
        timeForMoveComboBox.setModel(new DefaultComboBoxModel<>(new String[] { "1 MIN", "3 MIN", "5 MIN", "∞" }));
        timeForMoveComboBox.setSelectedIndex(0);
        timeForMoveComboBox.setToolTipText("Choose how long the game will last");
        timeForMoveComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                timeForMoveComboBoxActionPerformed(evt);
            }
        });

        // Create a JComboBox to allow player to select the game's difficulty
        difficultyLabel = new JLabel("Game Difficulty:");
        difficultyComboBox = new JComboBox<>();
        difficultyComboBox.setMaximumRowCount(3);
        difficultyComboBox.setModel(new DefaultComboBoxModel<>(new String[] { "Easy", "Medium", "Hard" }));
        difficultyComboBox.setSelectedIndex(1);
        difficultyComboBox.setToolTipText("Select a game difficulty");
        difficultyComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                difficultyComboBoxActionPerformed(evt);
            }
        });

        // Create JComboBox to allow player to select what color they want to be
        colorLabel = new JLabel("Color:");
        colorComboBox = new JComboBox<>();
        colorComboBox.setMaximumRowCount(2);
        colorComboBox.setModel(new DefaultComboBoxModel<>(new String[] { "Light", "Dark" }));
        colorComboBox.setSelectedIndex(0);
        colorComboBox.setToolTipText("Select which color piece you want");
        colorComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                colorComboBoxActionPerformed(evt);
            }
        });

        // Create JButton to start the chess game using the selected settings
        startGameButton = new JButton("Start");
        startGameButton.setToolTipText("Start game with selected settings");
        startGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                startGameButtonActionPerformed(evt);
            }
        });

        // Create JButton to cancel and return to menu
        cancelButton = new JButton("Cancel");
        cancelButton.setToolTipText("Return to menu");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        // Setup the layout of the settingDialog JDialog box
        GroupLayout settingsDialogLayout = new GroupLayout(gameOptionsDialog.getContentPane());
        gameOptionsDialog.getContentPane().setLayout(settingsDialogLayout);
        settingsDialogLayout.setHorizontalGroup(
            settingsDialogLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(settingsDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(settingsDialogLayout.createParallelGroup(Alignment.TRAILING, false)
                    .addComponent(startGameButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(colorLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(difficultyLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(timeForMoveLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(numPlayersLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(ComponentPlacement.RELATED)
                .addGroup(settingsDialogLayout.createParallelGroup(Alignment.LEADING)
                    .addGroup(settingsDialogLayout.createParallelGroup(Alignment.LEADING, false)
                        .addComponent(numPlayersComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(timeForMoveComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cancelButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(difficultyComboBox, 0, 112, Short.MAX_VALUE))
                    .addComponent(colorComboBox, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        settingsDialogLayout.setVerticalGroup(
            settingsDialogLayout.createParallelGroup(Alignment.LEADING)
            .addGroup(settingsDialogLayout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addGroup(settingsDialogLayout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(numPlayersComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(numPlayersLabel))
                .addPreferredGap(ComponentPlacement.UNRELATED)
                .addGroup(settingsDialogLayout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(timeForMoveComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(timeForMoveLabel))
                .addPreferredGap(ComponentPlacement.UNRELATED)
                .addGroup(settingsDialogLayout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(difficultyComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(difficultyLabel))
                .addPreferredGap(ComponentPlacement.UNRELATED)
                .addGroup(settingsDialogLayout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(colorComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(colorLabel))
                .addPreferredGap(ComponentPlacement.UNRELATED)
                .addGroup(settingsDialogLayout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(startGameButton))
                .addContainerGap())
        );
    }

    private void createNewGameWarningDialog()
    {
        newGameWarningDialog = new JDialog();
        newGameWarningDialog.setBackground(new java.awt.Color(0, 0, 0));
        newGameWarningDialog.setMinimumSize(new java.awt.Dimension(288, 116));
        newGameWarningDialog.setPreferredSize(new java.awt.Dimension(288, 116));
        newGameWarningDialog.setResizable(false);
        newGameWarningDialog.setSize(new java.awt.Dimension(288, 116));
        newGameWarningDialog.getContentPane().setLayout(new java.awt.GridLayout(2, 1));

        topnewGameWarningDialogScrollPane = new JScrollPane();
        topnewGameWarningDialogScrollPane.setBorder(null);
        topnewGameWarningDialogScrollPane.setMixingCutoutShape(null);

        newGameWarningDialogTextPane = new JTextPane();
        newGameWarningDialogTextPane.setEditable(false);
        newGameWarningDialogTextPane.setBorder(null);
        newGameWarningDialogTextPane.setContentType("text/html"); // NOI18N
        newGameWarningDialogTextPane.setForeground(new java.awt.Color(0, 0, 0));
        newGameWarningDialogTextPane.setText("<html>\n  <head>\n    <style>\n      p {\n        text-align: center;\n        margin: 0; /* Set margin to 0 to remove spacing between paragraphs */\n      }\n    </style>\n  </head>\n  <body>\n    <p>Are you sure you want to start a new game?</p>\n    <p>All current data will be lost.</p>\n  </body>\n</html>");
        newGameWarningDialogTextPane.setFocusable(false);
        newGameWarningDialogTextPane.setOpaque(false);
        topnewGameWarningDialogScrollPane.setViewportView(newGameWarningDialogTextPane);

        newGameWarningDialog.getContentPane().add(topnewGameWarningDialogScrollPane);

        bottomnewGameWarningDialogPanel = new JPanel();
        bottomnewGameWarningDialogPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 25, 5));

        yesNewGameButton = new JButton("Yes");
        yesNewGameButton.setBackground(new java.awt.Color(0, 0, 255));
        yesNewGameButton.setForeground(new java.awt.Color(0, 0, 0));
        yesNewGameButton.setFocusable(false);
        yesNewGameButton.setMaximumSize(new java.awt.Dimension(100, 27));
        yesNewGameButton.setMinimumSize(new java.awt.Dimension(100, 27));
        yesNewGameButton.setPreferredSize(new java.awt.Dimension(100, 27));
        bottomnewGameWarningDialogPanel.add(yesNewGameButton);

        noNewGameButton = new JButton("No");
        noNewGameButton.setBackground(new java.awt.Color(255, 0, 0));
        noNewGameButton.setFocusable(false);
        noNewGameButton.setMaximumSize(new java.awt.Dimension(100, 27));
        noNewGameButton.setMinimumSize(new java.awt.Dimension(100, 27));
        noNewGameButton.setPreferredSize(new java.awt.Dimension(100, 27));
        bottomnewGameWarningDialogPanel.add(noNewGameButton);

        newGameWarningDialog.getContentPane().add(bottomnewGameWarningDialogPanel);
    }
/* ************************************************************************** *\
 * ***************************** Action Methods ***************************** *
\* ************************************************************************** */
    // *********************** menuBar Action Methods *********************** //
    private void aboutMenuItemActionPerformed(ActionEvent evt) {                                              
        // TODO add your handling code here:
    }                                             
    private void quitMenuItemActionPerformed(ActionEvent evt) {                                             
        System.exit(0);
    }                                            

    private void newGameMenuItemActionPerformed(ActionEvent evt)
    {
        createNewGameWarningDialog();
        // Calculate the center position of the parent frame
        int parentX = getContentPane().getX();
        int parentY = getContentPane().getY();
        int parentWidth = getContentPane().getWidth();
        int parentHeight = getContentPane().getHeight();

        int dialogX = parentX + (parentWidth - newGameWarningDialog.getWidth()) / 2;
        int dialogY = parentY + (parentHeight - newGameWarningDialog.getHeight()) / 2;

        // Set the dialog location
        newGameWarningDialog.setLocation(dialogX, dialogY);
        newGameWarningDialog.setVisible(true);
    }
    private void loadGameMenuItemActionPerformed(ActionEvent evt)
    {
        // TODO add your handling code here:
    }
    private void saveGameMenuItemActionPerformed(ActionEvent evt)
    {
        // TODO add your handling code here:
    }

    private void fullScreenMenuItemActionPerformed(ActionEvent evt) 
    {
        setResizable(true);
        if ("Enter Full-Screen".equals(fullScreenMenuItem.getText()))
        {
            zoomedIn = zoomInMenuItem.isEnabled();
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            setSize(MAXIMIZED_HORIZ, MAXIMIZED_VERT);
            zoomInMenuItem.setEnabled(false);
            zoomOutMenuItem.setEnabled(false);
            fullScreenMenuItem.setText("Exit Full-Screen");
            resizeComponents();
        }
        else if ("Exit Full-Screen".equals(fullScreenMenuItem.getText()))
        {
            fullScreenMenuItem.setText("Enter Full-Screen");
            setExtendedState(NORMAL);
            if (zoomedIn) {
                setSize(500,298);
                zoomInMenuItem.setEnabled(true);
                zoomOutMenuItem.setEnabled(false);
                resizeComponents();
            }
            else {
                setPreferredSize(new Dimension(750,375));
                setSize(750,375);
                zoomInMenuItem.setEnabled(false);
                zoomOutMenuItem.setEnabled(true);
                resizeComponents();
            }
        }
        setResizable(false);
    }
    private void zoomInMenuItemActionPerformed(ActionEvent evt)
    {
        setResizable(true);
        if ("gameMenuCard".equals(currentCard)) {
            setSize(750, 375);
        }
        else if ("gamePanelCard".equals(currentCard)) {
            setSize(800,800);
        }
        zoomInMenuItem.setEnabled(false);
        zoomOutMenuItem.setEnabled(true);
        resizeComponents();
        setResizable(false);
    }
    private void zoomOutMenuItemActionPerformed(ActionEvent evt)
    {
        setResizable(true);
        if ("gameMenuCard".equals(currentCard)) {
            setSize(500,298);
        }
        else if ("gamePanelCard".equals(currentCard)) {
            setSize(500,548);
        }
        zoomOutMenuItem.setEnabled(false);
        zoomInMenuItem.setEnabled(true);
        resizeComponents();
        setResizable(false);
    }

    private void rulesMenuItemActionPerformed(ActionEvent evt)
    {
        // TODO add your handling code here:
    }

    // ******************** gameMenuPanel Action Methods ******************** //
    private void resizeComponents()
    {
        Font font = null;
        if ("gameMenuCard".equals(currentCard)) {
            if ("Exit Full-Screen".equals(fullScreenMenuItem.getText())) {
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                Dimension screenSize = toolkit.getScreenSize();
                font = chessLabel.getFont();
                chessLabel.setFont(new Font(font.getName(), font.getStyle(), (int)(screenSize.getWidth() * 0.15)));
                font = new Font("Helvetica Neue", Font.PLAIN, (int)(screenSize.getWidth() * 0.026));
            }
            else if (zoomInMenuItem.isEnabled()) {
                font = chessLabel.getFont();
                chessLabel.setFont(new Font(font.getName(), font.getStyle(), 75));
                font = new Font("Helvetica Neue", Font.PLAIN, 13);
            } 
            else if (!zoomInMenuItem.isEnabled()) {
                font = chessLabel.getFont();
                chessLabel.setFont(new Font(font.getName(), font.getStyle(), 112));
                font = new Font("Helvetica Neue", Font.PLAIN, 19);
            }

            newGameButton.setFont(font);
            loadGameButton.setFont(font);
            rulesButton.setFont(font);
        }
        else if ("gameBoardCard".equals(currentCard))
        {
            // TODO add your handling code here:
        }
    }
    private void newGameButtonActionPerformed(ActionEvent evt)     {                                                   
        // Get the location of the "Start New Game" button
        int x = newGameButton.getLocationOnScreen().x;
        int y = newGameButton.getLocationOnScreen().y;

        // Set the location of the settings dialog next to the button
        gameOptionsDialog.setLocation(x + newGameButton.getWidth(), y);

        // Show the settings dialog
        gameOptionsDialog.setVisible(true);
    }

    private void loadGameButtonActionPerformed(ActionEvent evt) {                                               
        // TODO add your handling code here:
    }

    private void rulesButtonActionPerformed(ActionEvent evt) {                                            
        // TODO add your handling code here:
    }     

    // ********************** gamePanel Action Methods ********************** //

    // ****************** gameOptionsDialog Action Methods ****************** //
    private void numPlayersComboBoxActionPerformed(ActionEvent evt) {                                                   
        // TODO add your handling code here:
    }

    private void timeForMoveComboBoxActionPerformed(ActionEvent evt)
    {
        // TODO add your handling code here:
    }
    
    private void difficultyComboBoxActionPerformed(ActionEvent evt)
    {
        // TODO add your handling code here:
    }

    private void colorComboBoxActionPerformed(ActionEvent evt)
    {
        // TODO add your handling code here:
    }

    private void startGameButtonActionPerformed(ActionEvent evt) {                                                
        gameOptionsDialog.setVisible(false);
        // Switch to the third card (gameBoardPanel)
        CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
        setPreferredSize(new Dimension(500,548));
        cardLayout.show(getContentPane(), "gamePanelCard");
        currentCard = "gamePanelCard";
        pack();
    }                                               

    private void cancelButtonActionPerformed(ActionEvent evt) {                                             
        // Reset the settings to default values
        numPlayersComboBox.setSelectedIndex(0);
        timeForMoveComboBox.setSelectedIndex(0);
        difficultyComboBox.setSelectedIndex(1);
        colorComboBox.setSelectedIndex(0);
 
        // Close the settings dialog
        gameOptionsDialog.setVisible(false);
    }

/* ************************************************************************** *\
 * ****************************** More Methods ****************************** *
\* ************************************************************************** */
    private void setFontSize(JLabel label) 
    {
        if (zoomInMenuItem.isEnabled())
        {

        }
        else if (!zoomInMenuItem.isEnabled())
        {

        }
    }

/* ************************************************************************** *\
 * ****************************** Main Methods ****************************** *
\* ************************************************************************** */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TestGameMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TestGameMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TestGameMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TestGameMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TestGameMenu().setVisible(true);
            }
        });
    }
}
