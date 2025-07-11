package JavaCore.LambdaExpression;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventListener {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Lambda Event Listener");
        JButton button = new JButton("Click Me!");

        // Cách cũ: Dùng Anonymous Inner Class
        // button.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         JOptionPane.showMessageDialog(frame, "Button clicked! (Old way)");
        //     }
        // });
        
        
        // Cách mới: Dùng Lambda Expression (vì ActionListener là một functional interface)
        button.addActionListener(event -> JOptionPane.showMessageDialog(frame, "Button clicked! (Lambda way)"));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.getContentPane().add(button);
        frame.setVisible(true);
    }
}
