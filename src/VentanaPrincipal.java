import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class VentanaPrincipal extends JFrame {
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JTextField txtNombre;
    private JTextField txtApellido;
    private JTextField txtTelefono;
    private JTextField txtCorreo;
    private JButton buscarButton;
    private JTextField txtBuscar;
    private JTable table1;
    private JButton registrarButton;
    private JButton eliminarRegistroButton;
    private JButton modificarButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sistema de Control de Condominios");
        frame.setContentPane(new VentanaPrincipal().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(650, 500));
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }

    public VentanaPrincipal() {
        initComponents();
        Conexion();

        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombre.getText();
                String apellido = txtApellido.getText();
                String telefono = txtTelefono.getText();
                String correo = txtCorreo.getText();

                try {
                    CallableStatement cst = con.prepareCall("{call insertar_propietario(?, ?, ?, ?)}");
                    cst.setString(1, nombre);
                    cst.setString(2, apellido);
                    cst.setString(3, telefono);
                    cst.setString(4, correo);
                    cst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Propietario Registrado");

                    txtNombre.setText("");
                    txtApellido.setText("");
                    txtTelefono.setText("");
                    txtCorreo.setText("");
                    txtNombre.requestFocus();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String idPropietario = txtBuscar.getText();
                    pst = con.prepareStatement("SELECT nombre, apellido, telefono, correo FROM propietarios WHERE id_propietario = ?");
                    pst.setString(1, idPropietario);
                    ResultSet rs = pst.executeQuery();

                    if (rs.next()) {
                        String nombre = rs.getString(1);
                        String apellido = rs.getString(2);
                        String telefono = rs.getString(3);
                        String correo = rs.getString(4);

                        JOptionPane.showMessageDialog(null, "  Nombre: " + nombre + "  Apellido: " + apellido + "  Teléfono: " + telefono + "  Correo: " + correo);
                    } else {
                        JOptionPane.showMessageDialog(null, "Propietario no encontrado");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        eliminarRegistroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idPropietario = txtBuscar.getText();

                try {
                    CallableStatement cst = con.prepareCall("{call borrar_propietario(?)}");
                    cst.setInt(1, Integer.parseInt(idPropietario));
                    cst.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Propietario Eliminado exitosamente");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });

        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idPropietario = txtBuscar.getText();
                String nombre = txtNombre.getText();
                String apellido = txtApellido.getText();
                String telefono = txtTelefono.getText();
                String correo = txtCorreo.getText();

                try {
                    CallableStatement cst = con.prepareCall("{call modificar_propietario(?, ?, ?, ?, ?)}");
                    cst.setInt(1, Integer.parseInt(idPropietario));
                    cst.setString(2, nombre);
                    cst.setString(3, apellido);
                    cst.setString(4, telefono);
                    cst.setString(5, correo);
                    cst.executeUpdate();

                    JOptionPane.showMessageDialog(null, "Propietario Modificado exitosamente");
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    private void initComponents() {
        panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());

        tabbedPane1 = new JTabbedPane();
        panel1.add(tabbedPane1, BorderLayout.CENTER);

        JPanel panelRegistro = new JPanel(new GridLayout(0, 2));
        tabbedPane1.addTab("Registro", panelRegistro);

        panelRegistro.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelRegistro.add(txtNombre);

        panelRegistro.add(new JLabel("Apellido:"));
        txtApellido = new JTextField();
        panelRegistro.add(txtApellido);

        panelRegistro.add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField();
        panelRegistro.add(txtTelefono);

        panelRegistro.add(new JLabel("Correo:"));
        txtCorreo = new JTextField();
        panelRegistro.add(txtCorreo);

        registrarButton = new JButton("Registrar");
        panelRegistro.add(registrarButton);

        modificarButton = new JButton("Modificar");
        panelRegistro.add(modificarButton);

        JPanel panelBusqueda = new JPanel(new BorderLayout());
        tabbedPane1.addTab("Buscar/Eliminar", panelBusqueda);

        JPanel panelBusquedaForm = new JPanel(new GridLayout(0, 2));
        panelBusqueda.add(panelBusquedaForm, BorderLayout.NORTH);

        panelBusquedaForm.add(new JLabel("ID Propietario:"));
        txtBuscar = new JTextField();
        panelBusquedaForm.add(txtBuscar);

        buscarButton = new JButton("Buscar");
        panelBusquedaForm.add(buscarButton);

        eliminarRegistroButton = new JButton("Eliminar");
        panelBusquedaForm.add(eliminarRegistroButton);

        table1 = new JTable();
        panelBusqueda.add(new JScrollPane(table1), BorderLayout.CENTER);
    }

    Connection con;
    PreparedStatement pst;
    
    public void Conexion() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "CONDOMINIO", "root123");
            System.out.println("Conexión Exitosa");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
