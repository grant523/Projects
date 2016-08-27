import java.text.DecimalFormat;
import javax.swing.*;
import java.awt.event.*;
import java.math.BigDecimal;

public class PiToTheNth extends JFrame {
    JTextField input;
    JButton enter;
    JTextArea output;

    final static BigDecimal pi = new BigDecimal("3.141592653589793238462643383279502884197169399375105820974944592307816406286208998628034825342117067982148086513282306647093844609550582231725359408128");

    public static void main(String[] args) {
        new PiToTheNth();
    }

    public PiToTheNth() {
        this.setSize(400, 200);
        this.setLocationRelativeTo(null);
        this.setTitle("PiToTheNth");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();

        JLabel enterText = new JLabel("Enter desired number of decimal points (max 150):");
        panel.add(enterText);

        input = new JTextField(10);
        panel.add(input);

        enter = new JButton("enter");
        ButtonListener button = new ButtonListener();
        enter.addActionListener(button);
        panel.add(enter);

        output = new JTextArea(5, 25);
        output.setLineWrap(true);
        output.setEditable(false);
        panel.add(output);

        this.add(panel);
        this.setResizable(false);
        this.setVisible(true);
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == enter) {
                int digits = 0;
                try {
                    digits = Integer.parseInt(input.getText());
                } catch (NumberFormatException E) {
                }
                String format = "0";
                if (digits > 0) {
                    format = "0.";
                    for (int i = 0; i < digits; i++) {
                        format = format + "0";
                    }
                }
                DecimalFormat df = new DecimalFormat(format);
                if (digits > 150) {
                    output.setText("Error: Max 150 decimal places supported");
                } else if (digits < 0) {
                    output.setText("Error: negative input");
                } else {
                    output.setText("" + df.format(pi));
                }
            }

        }
    }
}
