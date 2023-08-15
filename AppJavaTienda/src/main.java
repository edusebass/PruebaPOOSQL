import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class main {
    public JPanel rootmain;
    private JButton verProductoButton;
    private JButton añadirProductoButton;
    private JButton actualizarProductoButton;
    private JButton eliminarProductoButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("main");
        frame.setContentPane(new main().rootmain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public main() {
    verProductoButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Component component = (Component) e.getSource();
            JFrame currentFrame = (JFrame) SwingUtilities.getRoot(component);
            currentFrame.dispose();

            // Open the new window
            JFrame frame = new JFrame("updateform");
            frame.setContentPane(new ver().rootver);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        }
    });
    actualizarProductoButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Component component = (Component) e.getSource();
            JFrame currentFrame = (JFrame) SwingUtilities.getRoot(component);
            currentFrame.dispose();

            JFrame frame = new JFrame("actualizar");
            frame.setContentPane(new actualizar().rootupdate);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        }
    });


        eliminarProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                JFrame currentFrame = (JFrame) SwingUtilities.getRoot(component);
                currentFrame.dispose();

                JFrame frame = new JFrame("eliminar");
                frame.setContentPane(new eliminar().rootdelete);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
        añadirProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                JFrame currentFrame = (JFrame) SwingUtilities.getRoot(component);
                currentFrame.dispose();

                JFrame frame = new JFrame("añadir");
                frame.setContentPane(new añadir().rootañadir);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}
