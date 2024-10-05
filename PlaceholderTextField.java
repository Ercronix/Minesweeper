
import javax.swing.*;
import java.awt.*;

// Custom JTextField class to add placeholder functionality
class PlaceholderTextField extends JTextField {
    private String placeholder;

    public PlaceholderTextField(String placeholder) {
        this.placeholder = placeholder;
        setForeground(Color.GRAY); // Set placeholder text color
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (getText().isEmpty()) {
            g.drawString(placeholder, 5, g.getFontMetrics().getHeight());
        }
    }

    @Override
    public void setText(String t) {
        super.setText(t);
        repaint(); // Repaint to update placeholder visibility
    }
}
