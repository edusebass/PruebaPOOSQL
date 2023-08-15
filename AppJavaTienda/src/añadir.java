import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.*;

public class añadir {
    public JPanel rootañadir;
    private JPanel rootupdate;
    private JButton añadirProductoButton;
    private JButton regresarButton;
    private JTextField textField1nom;
    private JTextField textField2prec;
    private JTextField textField3des;
    private JTextField textField4prov;
    private JTextField textField5dir;
    private JTextField textField6tel;
    private JTextField textField7id;

    public static final String DB_URL = "jdbc:mysql://localhost/tienda";
    public static final String USER = "root";
    public static final String PASSWORD = "root_bas3";

    public añadir() {
        añadirProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(textField7id.getText());
                try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
                    String nombre = textField1nom.getText();
                    BigDecimal precio = new BigDecimal(textField2prec.getText());
                    String descripcion = textField3des.getText();
                    String nombreProveedor = textField4prov.getText();
                    String direccionProveedor = textField5dir.getText();
                    String telefonoProveedor = textField6tel.getText();

                    conn.setAutoCommit(false);


                    String INSERT_PROVIDER_QUERY = "INSERT INTO Proveedores (nombre_empresa, direccion, telefono) VALUES (?, ?, ?)";
                    PreparedStatement insertProviderStmt = conn.prepareStatement(INSERT_PROVIDER_QUERY, Statement.RETURN_GENERATED_KEYS);
                    insertProviderStmt.setString(1, nombreProveedor);
                    insertProviderStmt.setString(2, direccionProveedor);
                    insertProviderStmt.setString(3, telefonoProveedor);
                    int rowsAffectedProvider = insertProviderStmt.executeUpdate();

                    int idProveedor = -1;
                    ResultSet generatedKeys = insertProviderStmt.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        idProveedor = generatedKeys.getInt(1);
                    }


                    String INSERT_PRODUCT_QUERY = "INSERT INTO Productos (nombre, precio, descripcion, id_proveedor) VALUES (?, ?, ?, ?)";
                    PreparedStatement insertProductStmt = conn.prepareStatement(INSERT_PRODUCT_QUERY);
                    insertProductStmt.setString(1, nombre);
                    insertProductStmt.setBigDecimal(2, precio);
                    insertProductStmt.setString(3, descripcion);
                    insertProductStmt.setInt(4, idProveedor); // Usar el ID del proveedor recién insertado
                    int rowsAffectedProduct = insertProductStmt.executeUpdate();

                    if (rowsAffectedProvider > 0 && rowsAffectedProduct > 0) {
                        conn.commit();
                        JOptionPane.showMessageDialog(rootupdate, "Producto añadido");
                    } else {
                        conn.rollback();
                        JOptionPane.showMessageDialog(rootupdate, "Error");
                    }
                } catch (SQLException x) {
                    throw new RuntimeException(x);
                }
            }
        });
        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Component component = (Component) e.getSource();
                JFrame currentFrame = (JFrame) SwingUtilities.getRoot(component);
                currentFrame.dispose();

                JFrame frame = new JFrame("main");
                frame.setContentPane(new main().rootmain);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("añadir");
        frame.setContentPane(new añadir().rootañadir);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
