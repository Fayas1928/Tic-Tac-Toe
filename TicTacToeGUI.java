import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToeGUI extends JFrame implements ActionListener {
    private JButton[][] buttons = new JButton[3][3];
    private boolean playerX = true; // true = X, false = O
    private int moves = 0;
    private JLabel statusLabel;

    public TicTacToeGUI() {
        setTitle("Tic-Tac-Toe");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(40, 44, 52));
        setLayout(new BorderLayout());

        // Status label
        statusLabel = new JLabel("Player X's turn");
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 18));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add(statusLabel, BorderLayout.NORTH);

        // Board panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 3, 5, 5));
        panel.setBackground(new Color(40, 44, 52));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 50));
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].setBackground(new Color(51, 51, 51));
                buttons[i][j].setForeground(Color.WHITE);
                buttons[i][j].addActionListener(this);
                panel.add(buttons[i][j]);
            }
        }

        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();
        if (!clicked.getText().equals("")) return; // Ignore if already clicked

        clicked.setText(playerX ? "X" : "O");
        clicked.setForeground(playerX ? Color.CYAN : Color.PINK);
        moves++;

        if (checkWin()) {
            JOptionPane.showMessageDialog(this, "Player " + (playerX ? "X" : "O") + " wins!");
            resetBoard();
            return;
        } else if (moves == 9) {
            JOptionPane.showMessageDialog(this, "It's a Draw!");
            resetBoard();
            return;
        }

        playerX = !playerX;
        statusLabel.setText("Player " + (playerX ? "X" : "O") + "'s turn");
    }

    private boolean checkWin() {
        String curr;
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (!buttons[i][0].getText().equals("") &&
                buttons[i][0].getText().equals(buttons[i][1].getText()) &&
                buttons[i][1].getText().equals(buttons[i][2].getText())) return true;

            if (!buttons[0][i].getText().equals("") &&
                buttons[0][i].getText().equals(buttons[1][i].getText()) &&
                buttons[1][i].getText().equals(buttons[2][i].getText())) return true;
        }

        // Check diagonals
        if (!buttons[0][0].getText().equals("") &&
            buttons[0][0].getText().equals(buttons[1][1].getText()) &&
            buttons[1][1].getText().equals(buttons[2][2].getText())) return true;

        if (!buttons[0][2].getText().equals("") &&
            buttons[0][2].getText().equals(buttons[1][1].getText()) &&
            buttons[1][1].getText().equals(buttons[2][0].getText())) return true;

        return false;
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setBackground(new Color(51, 51, 51));
            }
        }
        moves = 0;
        playerX = true;
        statusLabel.setText("Player X's turn");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TicTacToeGUI::new);
    }
}
