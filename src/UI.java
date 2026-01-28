import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI extends JFrame {

    JLabel status =  new JLabel("Play: X");
    String currentPlayer = "X"; //Starts with X
    boolean victory = false;
    UI() {

        ImageIcon icon = new ImageIcon("src/icon.png");
        this.setIconImage(icon.getImage());

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(52, 3, 2));  //default: gray
        this.setTitle("Knots n Crosses");
        this.setSize(280, 320);
        this.setFocusable(true);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        //Buttons 3x3 Grid
        JButton g1x1 = new JButton();
        initButton(this,g1x1,"",30,30,60,60,5,
                e-> makeMove(g1x1,0,0,currentPlayer));
        JButton g1x2 = new JButton();
        initButton(this,g1x2,"",100,30,60,60,5,
                e-> makeMove(g1x2,0,1,currentPlayer));
        JButton g1x3 = new JButton();
        initButton(this,g1x3,"",170,30,60,60,5,
                e-> makeMove(g1x3,0,2,currentPlayer) );
        JButton g2x1 = new JButton();
        initButton(this,g2x1,"",30,100,60,60,5,
                e-> makeMove(g2x1,1,0,currentPlayer) );
        JButton g2x2 = new JButton();
        initButton(this,g2x2,"",100,100,60,60,5,
                e-> makeMove(g2x2,1,1,currentPlayer) );
        JButton g2x3 = new JButton();
        initButton(this,g2x3,"",170,100,60,60,5,
                e-> makeMove(g2x3,1,2,currentPlayer));
        JButton g3x1 = new JButton();
        initButton(this,g3x1,"",30,170,60,60,5,
                e-> makeMove(g3x1,2,0,currentPlayer));
        JButton g3x2 = new JButton();
        initButton(this, g3x2,"",100,170,60,60,5,
                e-> makeMove(g3x2,2,1,currentPlayer));
        JButton g3x3 = new JButton();
        initButton(this, g3x3,"",170,170,60,60,5,
                e-> makeMove(g3x3,2,2,currentPlayer));

        //Label
        status.setText("Play: "+currentPlayer);
        status.setBounds(100,230,100,50);
        status.setFont(new Font("Arial",Font.BOLD,20));
        status.setForeground(new Color(236, 152, 147));
        this.add(status);
    }

    void makeMove(JButton button, int row, int column, String ch){
        Main.game[row][column] = ch.charAt(0);
        button.setText(ch);
        checkWin();
        if(victory)
        {
            status.setText(currentPlayer+" Wins!");
            return;
        }
        currentPlayer = currentPlayer.equals("X") ? "O" : "X" ;
        status.setText("Play: " + currentPlayer);


    }
    void checkWin(){
        char[][] c = Main.game;

        // rows
        for (int i = 0; i < 3; i++) {
            if (c[i][0] != '\0' &&
                    c[i][0] == c[i][1] &&
                    c[i][1] == c[i][2])
                victory = true;

        }

        // columns
        for (int i = 0; i < 3; i++) {
            if (c[0][i] != '\0' &&
                    c[0][i] == c[1][i] &&
                    c[1][i] == c[2][i])
                victory = true;

        }

        // diagonals
        if (c[1][1] != '\0' &&
                ((c[0][0] == c[1][1] && c[1][1] == c[2][2]) ||
                        (c[0][2] == c[1][1] && c[1][1] == c[2][0])))
            victory = true;

    }
    //Helper Function
    void initButton(JFrame frame, JButton button, String text, int x, int y, int width,
                    int height, int roundness,  ActionListener e) {
        button.setText(text);
        button.setBounds(x, y, width, height);
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setForeground(new Color(222, 115, 109));
        button.setFocusable(false);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setBackground(new Color(147, 22, 20));
        button.addActionListener(e);
        //To add roundness and make it dark when pressed, TBR
        button.setUI(new javax.swing.plaf.basic.BasicButtonUI() {
            @Override
            public void paint(Graphics g, JComponent c) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                JButton b = (JButton) c;
                g2.setColor(b.getModel().isPressed()
                        ? b.getBackground().darker()
                        : b.getBackground());

                g2.fillRoundRect(0, 0, b.getWidth(), b.getHeight(), roundness, roundness);
                g2.dispose();

                super.paint(g, c);
            }
        });

        frame.add(button);
    }
}
