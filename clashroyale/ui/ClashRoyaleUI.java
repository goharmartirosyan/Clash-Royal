package clashroyale.ui;
import clashroyale.cli.ClashRoyaleConsole;
import clashroyale.core.Card;
import clashroyale.core.ClashRoyale;
import clashroyale.ui.ClashRoyaleUIButtons;
import clashroyale.core.Position;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ClashRoyaleUI extends JFrame{
    private ClashRoyale game;
    private ClashRoyaleUIButtons[][] boardButtons;
    private ClashRoyaleUIButtons[] cards = new ClashRoyaleUIButtons[5];
    private Card[] randomCards = new Card[5];
    private Card preserved;

    public ClashRoyaleUI() {

        game = null;
        try {
            game = new ClashRoyale();
        } catch (Exception e) {}
        boardButtons = new ClashRoyaleUIButtons[ClashRoyale.HEIGHT_BOARD][ClashRoyale.LENGTH_BOARD];

        this.setTitle("Clash Royale");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(700, 800));

        JPanel background = new JPanel();
        JPanel board = new JPanel();
        JPanel cards = new JPanel();
        JPanel text = new JPanel();
        JLabel infoLabel = new JLabel();

        background.setLayout(new BorderLayout());
        background.setPreferredSize(new Dimension(500, 800));

        board.setLayout(new GridLayout(ClashRoyale.HEIGHT_BOARD, ClashRoyale.LENGTH_BOARD));
        board.setPreferredSize(new Dimension(300,700));

        cards.setLayout(new GridLayout(1, 5));
        cards.setPreferredSize(new Dimension(500, 100));

        infoLabel.setHorizontalAlignment(SwingConstants.LEFT);
        infoLabel.setPreferredSize(new Dimension(200, 800));
        String elixirText = game.getElixirLevelText();


// Assuming clashRoyaleConsole is an instance of ClashRoyaleConsole



        infoLabel.setText("<html>" + elixirText.replaceAll("\n", "<br>") + "</html>");


        // Set padding around the text to prevent it from touching the borders
        infoLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));




        for (int i = 0; i < 5; i++) {
            ClashRoyaleUIButtons button = new ClashRoyaleUIButtons(0, i);
            button.setPreferredSize(new Dimension(50,50));
            button.setBackground(Color.GRAY);
            button.setCard();
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    ClashRoyaleUIButtons source = (ClashRoyaleUIButtons) e.getSource();
                    int[] coords = source.getCoordinates();
                    boardClicked(coords);
                    String elixirText = updateText();
                    infoLabel.setText("<html>" + elixirText.replaceAll("\n", "<br>") + "</html>");
                }});

            this.cards[i] = button;
            cards.add(button);
        }

        for (int i = 0; i < ClashRoyale.HEIGHT_BOARD; i++) {
            for (int j = 0; j < ClashRoyale.LENGTH_BOARD; j++) {
                ClashRoyaleUIButtons button = new ClashRoyaleUIButtons(i, j);
                button.setPreferredSize(new Dimension(10,10));
                if(i == ClashRoyale.BRIDGE_ROW){
                    if(j == ClashRoyale.BRIDGE_COLUMN ){
                        button.setBackground(Color.GREEN);
                    } else {
                        button.setBackground(Color.BLUE);
                    }
                } else {
                    button.setBackground(Color.cyan);
                }

                button.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        ClashRoyaleUIButtons source = (ClashRoyaleUIButtons) e.getSource();
                        int[] coords = source.getCoordinates();
                        if(preserved != null)
                            boardClicked(coords, preserved);
                        String elixirText = updateText();
                        infoLabel.setText("<html>" + elixirText.replaceAll("\n", "<br>") + "</html>");
                    }});
                git

                this.boardButtons[i][j] = button;
                board.add(button);
            }
        }




       // board.add(text);

        board.add(infoLabel);
        background.add(board, BorderLayout.CENTER);
        background.add(cards, BorderLayout.SOUTH);
        background.add(infoLabel, BorderLayout.EAST);
        this.add(background);
        this.pack();
        this.setLocationRelativeTo(null);
        updateCardsCenter();
        updateCardsSouth();
        this.setVisible(true);
    }
    private String updateText(){
       return game.getElixirLevelText();
    }
    private void updateCardsCenter(){
        Card c;
        for (int i = 0; i < ClashRoyale.HEIGHT_BOARD; i++) {
            for (int j = 0; j < ClashRoyale.LENGTH_BOARD; j++) {
                c = game.getCardAt(Position.generateFromRowAndColumn(i, j));
                if (c != null)
                    boardButtons[i][j].setCard(game.getCardAt(Position.generateFromRowAndColumn(i, j)).name()); //Add the Card.
                else
                    boardButtons[i][j].setCard(); //clear the buttonIcon.
            }
        }


        this.boardButtons[ClashRoyale.BLUETOWER_ROW][ClashRoyale.BRIDGE_COLUMN].setIcon(new ImageIcon("./GUIImages/TowerBlue.png"));
        this.boardButtons[ClashRoyale.REDTOWER_ROW][ClashRoyale.BRIDGE_COLUMN].setIcon(new ImageIcon("./GUIImages/TowerRed.png"));
    }

    private void updateCardsSouth() {
        this.randomCards = ClashRoyale.randomCards(game.getTurn());
        for (int i = 0; i < cards.length; i++) {
            this.cards[i].setCard(randomCards[i].name());// using polymorphism we set each Card.
        }

    }

    private Card[] randomCaller() {
        Card[] cards = ClashRoyale.randomCards(game.getTurn());
        return cards;
    }

    private void boardClicked(int[] coords) {
        this.preserved = randomCards[coords[1]];
    }

    private void boardClicked(int[] coords, Card preserved) {
        Position p = Position.generateFromRowAndColumn(coords[0], coords[1]);
        if(preserved != null) {
            if(p.getRow() == ClashRoyale.BRIDGE_ROW && p.getColumn() != ClashRoyale.BRIDGE_COLUMN){
            }
            else {
                game.moveAllCards();
                game.performMove(p, preserved);
                preserved = null;
                updateCardsSouth();
                updateCardsCenter();
            }
        } else {
            //nothing
        }

    }

}