import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ver {
    public JPanel rootver;
    private JButton buscarButton;
    private JTextField fieldproducto;
    private JLabel nomprod;
    private JLabel precprod;
    private JLabel descprod;
    private JLabel provprod;
    private JLabel direcprod;
    private JLabel telefonoprod;
    private JButton regresarButton;

    //
    public String producto;
    public static final String DB_URL = "jdbc:mysql://localhost/tienda";
    public static final String USER = "root";
    public static final String PASSWORD = "root_bas3";


    public ver() {
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                producto = fieldproducto.getText();

                try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
                    String QUERY = "SELECT\n" +
                            "    p.nombre,\n" +
                            "    p.precio,\n" +
                            "    p.descripcion,\n" +
                            "    pr.nombre_empresa,\n" +
                            "    pr.direccion,\n" +
                            "    pr.telefono\n" +
                            "FROM\n" +
                            "    Productos p\n" +
                            "JOIN\n" +
                            "    Proveedores pr ON p.id_proveedor = pr.id\n" +
                            "WHERE\n" +
                            "    p.nombre = ?";
                    PreparedStatement stmt = conn.prepareStatement(QUERY);
                    stmt.setString(1, producto);
                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()) {
                        String nombreProducto = rs.getString("nombre");
                        nomprod.setText("Nombre: " + nombreProducto);

                        String precioProducto = rs.getString("precio");
                        precprod.setText("Precio: " + precioProducto);

                        String descripcionprod = rs.getString("descripcion");
                        descprod.setText("Descripcion: " + descripcionprod);

                        String proveedor = rs.getString("nombre_empresa");
                        provprod.setText("Proveedor: " + proveedor);

                        String direccion = rs.getString("direccion");
                        direcprod.setText("Direccion: " + direccion);

                        String telefono = rs.getString("telefono");
                        telefonoprod.setText("Telefono: " + telefono);


                    } else {
                        JOptionPane.showMessageDialog(rootver, "Producto no existe");
                    }
                } catch (SQLException x) {
                    throw new RuntimeException(x);
                }
            }
        });

        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the current window
                Component component = (Component) e.getSource();
                JFrame currentFrame = (JFrame) SwingUtilities.getRoot(component);
                currentFrame.dispose();

                // Open the new window
                JFrame frame = new JFrame("updateform");
                frame.setContentPane(new main().rootmain);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ver");
        frame.setContentPane(new ver().rootver);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
