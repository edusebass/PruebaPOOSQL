import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.math.BigDecimal;
import java.sql.*;

public class actualizar {
    private JButton actualizarProductoButton;
    private JButton regresarButton;
    private JTextField textField1nom;
    private JTextField textField2prec;
    private JTextField textField3des;
    private JTextField textField4prov;
    private JTextField textField5dir;
    private JTextField textField6tel;
    public JPanel rootupdate;
    private JTextField textField7id;

    static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=tienda;instance=SQLEXPRESS;encrypt=false;trustServerCertificate=true;";
    public static final String USER = "userbd";
    public static final String PASSWORD = "123";

    public actualizar() {
        actualizarProductoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
                    conn.setAutoCommit(false);

                    int id = Integer.parseInt(textField7id.getText());

                    String UPDATE_PRODUCT_QUERY = "UPDATE Productos SET precio = ?, descripcion = ?, nombre = ? WHERE id = ?";
                    PreparedStatement updateProductStmt = conn.prepareStatement(UPDATE_PRODUCT_QUERY);
                    updateProductStmt.setBigDecimal(1, new BigDecimal(textField2prec.getText()));
                    updateProductStmt.setString(2, textField3des.getText());
                    updateProductStmt.setString(3, textField1nom.getText());
                    updateProductStmt.setString(4, textField7id.getText());
                    int rowsAffectedProduct = updateProductStmt.executeUpdate();

                    String UPDATE_PROVIDER_QUERY = "UPDATE Proveedores SET nombre_empresa = ?, direccion = ?, telefono = ? WHERE id = ?";
                    PreparedStatement updateProviderStmt = conn.prepareStatement(UPDATE_PROVIDER_QUERY);
                    updateProviderStmt.setString(1, textField4prov.getText());
                    updateProviderStmt.setString(2, textField5dir.getText());
                    updateProviderStmt.setString(3, textField6tel.getText());
                    updateProviderStmt.setInt(4, id);
                    int rowsAffectedProvider = updateProviderStmt.executeUpdate();

                    if (rowsAffectedProduct > 0 && rowsAffectedProvider > 0) {
                        conn.commit();
                        JOptionPane.showMessageDialog(rootupdate, "Producto actualizado");
                    } else {
                        conn.rollback();
                        JOptionPane.showMessageDialog(rootupdate, "Producto no ser actualizado");
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
        JFrame frame = new JFrame("actualizar");
        frame.setContentPane(new actualizar().rootupdate);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
