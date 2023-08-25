import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.*;

public class eliminar {
    private JTextField textField1id;
    private JButton eliminarButton;
    private JButton regresarButton;
    public JPanel rootdelete;

    static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=tienda;instance=SQLEXPRESS;encrypt=false;trustServerCertificate=true;";
    public static final String USER = "userbd";
    public static final String PASSWORD = "123";


    public eliminar() {
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int productoID = Integer.parseInt(textField1id.getText());

                try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
                    conn.setAutoCommit(false);

                    String GET_PROVIDER_ID_QUERY = "SELECT id_proveedor FROM Productos WHERE id = ?";
                    PreparedStatement getProviderIdStmt = conn.prepareStatement(GET_PROVIDER_ID_QUERY);
                    getProviderIdStmt.setInt(1, productoID);
                    ResultSet providerIdResult = getProviderIdStmt.executeQuery();

                    int providerID = -1;
                    if (providerIdResult.next()) {
                        providerID = providerIdResult.getInt("id_proveedor");
                    }

                    String DELETE_PRODUCT_QUERY = "DELETE FROM Productos WHERE id = ?";
                    PreparedStatement deleteProductStmt = conn.prepareStatement(DELETE_PRODUCT_QUERY);
                    deleteProductStmt.setInt(1, productoID);
                    int rowsAffectedProduct = deleteProductStmt.executeUpdate();


                    String COUNT_PRODUCTS_QUERY = "SELECT COUNT(*) AS num_products FROM Productos WHERE id_proveedor = ?";
                    PreparedStatement countProductsStmt = conn.prepareStatement(COUNT_PRODUCTS_QUERY);
                    countProductsStmt.setInt(1, providerID);
                    ResultSet countProductsResult = countProductsStmt.executeQuery();

                    if (countProductsResult.next()) {
                        int numProducts = countProductsResult.getInt("num_products");
                        if (numProducts == 0) {
                            String DELETE_PROVIDER_QUERY = "DELETE FROM Proveedores WHERE id = ?";
                            PreparedStatement deleteProviderStmt = conn.prepareStatement(DELETE_PROVIDER_QUERY);
                            deleteProviderStmt.setInt(1, providerID);
                            deleteProviderStmt.executeUpdate();
                        }
                    }

                    if (rowsAffectedProduct > 0) {
                        conn.commit();
                        JOptionPane.showMessageDialog(rootdelete, "Producto y proveedor relacionado eliminados");
                    } else {
                        conn.rollback();
                        JOptionPane.showMessageDialog(rootdelete, "Error al eliminar el producto");
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
        JFrame frame = new JFrame("eliminar");
        frame.setContentPane(new eliminar().rootdelete);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
