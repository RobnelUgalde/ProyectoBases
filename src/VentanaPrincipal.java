import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPrincipal extends JFrame {
    private JTabbedPane tabbedPane1;
    private JPanel panel1;

    private JTextField txtIdDueno;
    private JTextField txtNombreDueno;
    private JTextField txtApellidoDueno;
    private JTextField txtDireccionDueno;
    private JTextField txtTelefonoDueno;
    private JButton btnRegistrarDueno;
    private JButton btnActualizarDueno;
    private JButton btnEliminarDueno;

    private JTextField txtIdFilial;
    private JTextField txtIdDuenoFilial;
    private JTextField txtNumeroFilial;
    private JTextField txtUbicacionFilial;
    private JButton btnRegistrarFilial;
    private JButton btnActualizarFilial;
    private JButton btnEliminarFilial;

    private JTextField txtIdCuotaCondominal;
    private JTextField txtIdFilialCuotaCondominal;
    private JTextField txtFechaPagoCuotaCondominal;
    private JTextField txtMontoCuotaCondominal;
    private JButton btnRegistrarCuotaCondominal;
    private JButton btnActualizarCuotaCondominal;
    private JButton btnEliminarCuotaCondominal;

    private JTextField txtIdAcceso;
    private JTextField txtIdFilialAcceso;
    private JTextField txtFechaIngresoAcceso;
    private JTextField txtHoraIngresoAcceso;
    private JButton btnRegistrarAcceso;
    private JButton btnActualizarAcceso;
    private JButton btnEliminarAcceso;

    private JTextField txtIdCuotaExtraordinaria;
    private JTextField txtIdFilialCuotaExtraordinaria;
    private JTextField txtFechaPagoCuotaExtraordinaria;
    private JTextField txtMontoCuotaExtraordinaria;
    private JButton btnRegistrarCuotaExtraordinaria;
    private JButton btnActualizarCuotaExtraordinaria;
    private JButton btnEliminarCuotaExtraordinaria;

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
    }

    private void initComponents() {
        panel1 = new JPanel();
        panel1.setLayout(new BorderLayout());

        tabbedPane1 = new JTabbedPane();
        panel1.add(tabbedPane1, BorderLayout.CENTER);


        // Pestaña para SP_DUEÑOS
        JPanel panelDuenos = new JPanel(new GridLayout(0, 2));
        tabbedPane1.addTab("Dueños", panelDuenos);

        panelDuenos.add(new JLabel("ID Dueño:"));
        txtIdDueno = new JTextField();
        panelDuenos.add(txtIdDueno);

        panelDuenos.add(new JLabel("Nombre:"));
        txtNombreDueno = new JTextField();
        panelDuenos.add(txtNombreDueno);

        panelDuenos.add(new JLabel("Apellido:"));
        txtApellidoDueno = new JTextField();
        panelDuenos.add(txtApellidoDueno);

        panelDuenos.add(new JLabel("Dirección:"));
        txtDireccionDueno = new JTextField();
        panelDuenos.add(txtDireccionDueno);

        panelDuenos.add(new JLabel("Teléfono:"));
        txtTelefonoDueno = new JTextField();
        panelDuenos.add(txtTelefonoDueno);

        btnRegistrarDueno = new JButton("Registrar Dueño");
        panelDuenos.add(btnRegistrarDueno);

// Inicializar el botón btnRegistrarCuotaCondominal
        btnActualizarDueno = new JButton("Actualizar Dueño");
        panelDuenos.add(btnActualizarDueno); // Asegúrate de que esté inicializado antes de agregar el ActionListener

        btnEliminarDueno = new JButton("Eliminar Dueño");
        panelDuenos.add(btnEliminarDueno); // Asegúrate de que esté inicializado antes de agregar el ActionListener


        // ActionListener para el botón Registrar Dueño
        btnRegistrarDueno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Obtener los valores de los campos de texto
                    int idDueno = Integer.parseInt(txtIdDueno.getText());
                    String nombre = txtNombreDueno.getText();
                    String apellido = txtApellidoDueno.getText();
                    String direccion = txtDireccionDueno.getText();
                    String telefono = txtTelefonoDueno.getText();

                    // Preparar la declaración SQL
                    String sql = "INSERT INTO DUEÑOS (ID_DUEÑO, NOMBRE, APELLIDO, DIRECCIÓN, TELEFONO) VALUES (?, ?, ?, ?, ?)";
                    pst = con.prepareStatement(sql);

                    // Establecer los parámetros de la declaración SQL
                    pst.setInt(1, idDueno);
                    pst.setString(2, nombre);
                    pst.setString(3, apellido);
                    pst.setString(4, direccion);
                    pst.setString(5, telefono);

                    // Ejecutar la declaración SQL
                    pst.executeUpdate();

                    // Notificar al usuario que el registro se ha completado
                    JOptionPane.showMessageDialog(null, "Registro de dueño exitoso");

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al registrar dueño");
                }
            }
        });

// ActionListener para el botón Actualizar Dueño
        btnActualizarDueno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Obtener los valores de los campos de texto
                    int idDueno = Integer.parseInt(txtIdDueno.getText());
                    String nombre = txtNombreDueno.getText();
                    String apellido = txtApellidoDueno.getText();
                    String direccion = txtDireccionDueno.getText();
                    String telefono = txtTelefonoDueno.getText();

                    // Preparar la declaración SQL
                    String sql = "UPDATE DUEÑOS SET NOMBRE = ?, APELLIDO = ?, DIRECCIÓN = ?, TELEFONO = ? WHERE ID_DUEÑO = ?";
                    pst = con.prepareStatement(sql);

                    // Establecer los parámetros de la declaración SQL
                    pst.setString(1, nombre);
                    pst.setString(2, apellido);
                    pst.setString(3, direccion);
                    pst.setString(4, telefono);
                    pst.setInt(5, idDueno);

                    // Ejecutar la declaración SQL
                    pst.executeUpdate();

                    // Notificar al usuario que la actualización se ha completado
                    JOptionPane.showMessageDialog(null, "Actualización de dueño exitosa");

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al actualizar dueño");
                }
            }
        });

// ActionListener para el botón Eliminar Dueño
        btnEliminarDueno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Obtener el ID del dueño a eliminar
                    int idDueno = Integer.parseInt(txtIdDueno.getText());

                    // Preparar la declaración SQL
                    String sql = "DELETE FROM DUEÑOS WHERE ID_DUEÑO = ?";
                    pst = con.prepareStatement(sql);

                    // Establecer el parámetro de la declaración SQL
                    pst.setInt(1, idDueno);

                    // Ejecutar la declaración SQL
                    pst.executeUpdate();

                    // Notificar al usuario que la eliminación se ha completado
                    JOptionPane.showMessageDialog(null, "Eliminación de dueño exitosa");

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al eliminar dueño");
                }
            }
        });

        // Pestaña para SP_FILIALES
        JPanel panelFiliales = new JPanel(new GridLayout(0, 2));
        tabbedPane1.addTab("Filiales", panelFiliales);

        panelFiliales.add(new JLabel("ID Filial:"));
        txtIdFilial = new JTextField();
        panelFiliales.add(txtIdFilial);

        panelFiliales.add(new JLabel("ID Dueño:"));
        txtIdDuenoFilial = new JTextField();
        panelFiliales.add(txtIdDuenoFilial);

        panelFiliales.add(new JLabel("Número Filial:"));
        txtNumeroFilial = new JTextField();
        panelFiliales.add(txtNumeroFilial);

        panelFiliales.add(new JLabel("Ubicación:"));
        txtUbicacionFilial = new JTextField();
        panelFiliales.add(txtUbicacionFilial);

        btnRegistrarFilial = new JButton("Registrar Filial");
        panelFiliales.add(btnRegistrarFilial);

        // ActionListener para el botón Registrar Filial
        btnRegistrarFilial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Obtener los valores de los campos de texto
                    int idFilial = Integer.parseInt(txtIdFilial.getText());
                    int idDueno = Integer.parseInt(txtIdDuenoFilial.getText());
                    int numeroFilial = Integer.parseInt(txtNumeroFilial.getText());
                    String ubicacion = txtUbicacionFilial.getText();

                    // Preparar la declaración SQL
                    String sql = "INSERT INTO FILIALES (ID_FILIAL, ID_DUEÑO, NUMERO_FILIAL, UBICACIÓN) VALUES (?, ?, ?, ?)";
                    pst = con.prepareStatement(sql);

                    // Establecer los parámetros de la declaración SQL
                    pst.setInt(1, idFilial);
                    pst.setInt(2, idDueno);
                    pst.setInt(3, numeroFilial);
                    pst.setString(4, ubicacion);

                    // Ejecutar la declaración SQL
                    pst.executeUpdate();

                    // Notificar al usuario que el registro se ha completado
                    JOptionPane.showMessageDialog(null, "Registro de filial exitoso");

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al registrar filial");
                }
            }
        });

        // Pestaña para SP_CUOTAS_CONDOMINALES
        JPanel panelCuotasCondominales = new JPanel(new GridLayout(0, 2));
        tabbedPane1.addTab("Cuotas Condominales", panelCuotasCondominales);

        panelCuotasCondominales.add(new JLabel("ID Cuota:"));
        txtIdCuotaCondominal = new JTextField();
        panelCuotasCondominales.add(txtIdCuotaCondominal);

        panelCuotasCondominales.add(new JLabel("ID Filial:"));
        txtIdFilialCuotaCondominal = new JTextField();
        panelCuotasCondominales.add(txtIdFilialCuotaCondominal);

        panelCuotasCondominales.add(new JLabel("Fecha Pago:"));
        txtFechaPagoCuotaCondominal = new JTextField();
        panelCuotasCondominales.add(txtFechaPagoCuotaCondominal);

        panelCuotasCondominales.add(new JLabel("Monto:"));
        txtMontoCuotaCondominal = new JTextField();
        panelCuotasCondominales.add(txtMontoCuotaCondominal);

        // Inicializar el botón btnRegistrarCuotaCondominal
        btnRegistrarCuotaCondominal = new JButton("Registrar Cuota Condominal");
        panelCuotasCondominales.add(btnRegistrarCuotaCondominal); // Asegúrate de que esté inicializado antes de agregar el ActionListener

        // ActionListener para el botón Registrar Cuota Condominal
        btnRegistrarCuotaCondominal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Obtener los valores de los campos de texto
                    int idCuota = Integer.parseInt(txtIdCuotaCondominal.getText());
                    int idFilial = Integer.parseInt(txtIdFilialCuotaCondominal.getText());
                    String fechaPago = txtFechaPagoCuotaCondominal.getText(); // Asegúrate de que este campo tenga un formato de fecha válido
                    double monto = Double.parseDouble(txtMontoCuotaCondominal.getText());

                    // Preparar la declaración SQL
                    String sql = "INSERT INTO CUOTAS_CONDOMINALES (ID_CUOTA, ID_FILIAL, FECHA_PAGO, MONTO) VALUES (?, ?, ?, ?)";
                    pst = con.prepareStatement(sql);

                    // Establecer los parámetros de la declaración SQL
                    pst.setInt(1, idCuota);
                    pst.setInt(2, idFilial);
                    pst.setString(3, fechaPago);
                    pst.setDouble(4, monto);

                    // Ejecutar la declaración SQL
                    pst.executeUpdate();

                    // Notificar al usuario que el registro se ha completado
                    JOptionPane.showMessageDialog(null, "Registro de cuota condominal exitoso");

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al registrar cuota condominal");
                }
            }
        });

        btnRegistrarCuotaCondominal = new JButton("Registrar Cuota Condominal");
        panelCuotasCondominales.add(btnRegistrarCuotaCondominal);

        // Pestaña para SP_ACCESOS
        JPanel panelAccesos = new JPanel(new GridLayout(0, 2));
        tabbedPane1.addTab("Accesos", panelAccesos);

        panelAccesos.add(new JLabel("ID Acceso:"));
        txtIdAcceso = new JTextField();
        panelAccesos.add(txtIdAcceso);

        panelAccesos.add(new JLabel("ID Filial:"));
        txtIdFilialAcceso = new JTextField();
        panelAccesos.add(txtIdFilialAcceso);

        panelAccesos.add(new JLabel("Fecha Ingreso:"));
        txtFechaIngresoAcceso = new JTextField();
        panelAccesos.add(txtFechaIngresoAcceso);

        panelAccesos.add(new JLabel("Hora Ingreso:"));
        txtHoraIngresoAcceso = new JTextField();
        panelAccesos.add(txtHoraIngresoAcceso);

        btnRegistrarAcceso = new JButton("Registrar Acceso");
        panelAccesos.add(btnRegistrarAcceso);

// ActionListener para el botón Registrar Acceso
        btnRegistrarAcceso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Obtener los valores de los campos de texto
                    int idAcceso = Integer.parseInt(txtIdAcceso.getText());
                    int idFilial = Integer.parseInt(txtIdFilialAcceso.getText());
                    String fechaIngreso = txtFechaIngresoAcceso.getText(); // Asegúrate de que este campo tenga un formato de fecha válido
                    String horaIngreso = txtHoraIngresoAcceso.getText(); // Asegúrate de que este campo tenga un formato de hora válido

                    // Preparar la declaración SQL
                    String sql = "INSERT INTO ACCESOS (ID_ACCESO, ID_FILIAL, FECHA_INGRESO, HORA_INGRESO) VALUES (?, ?, ?, ?)";
                    pst = con.prepareStatement(sql);

                    // Establecer los parámetros de la declaración SQL
                    pst.setInt(1, idAcceso);
                    pst.setInt(2, idFilial);
                    pst.setString(3, fechaIngreso);
                    pst.setString(4, horaIngreso);

                    // Ejecutar la declaración SQL
                    pst.executeUpdate();

                    // Notificar al usuario que el registro se ha completado
                    JOptionPane.showMessageDialog(null, "Registro de acceso exitoso");

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al registrar acceso");
                }
            }
        });

        // Pestaña para SP_CUOTAS_EXTRAORDINARIAS
        JPanel panelCuotasExtraordinarias = new JPanel(new GridLayout(0, 2));
        tabbedPane1.addTab("Cuotas Extraordinarias", panelCuotasExtraordinarias);

        panelCuotasExtraordinarias.add(new JLabel("ID Cuota Extra:"));
        txtIdCuotaExtraordinaria = new JTextField();
        panelCuotasExtraordinarias.add(txtIdCuotaExtraordinaria);

        panelCuotasExtraordinarias.add(new JLabel("ID Filial:"));
        txtIdFilialCuotaExtraordinaria = new JTextField();
        panelCuotasExtraordinarias.add(txtIdFilialCuotaExtraordinaria);

        panelCuotasExtraordinarias.add(new JLabel("Fecha Pago:"));
        txtFechaPagoCuotaExtraordinaria = new JTextField();
        panelCuotasExtraordinarias.add(txtFechaPagoCuotaExtraordinaria);

        panelCuotasExtraordinarias.add(new JLabel("Monto:"));
        txtMontoCuotaExtraordinaria = new JTextField();
        panelCuotasExtraordinarias.add(txtMontoCuotaExtraordinaria);

        btnRegistrarCuotaExtraordinaria = new JButton("Registrar Cuota Extraordinaria");
        panelCuotasExtraordinarias.add(btnRegistrarCuotaExtraordinaria);


        // ActionListener para el botón Registrar Cuota Extraordinaria
        btnRegistrarCuotaExtraordinaria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Obtener los valores de los campos de texto
                    int idCuotaExtra = Integer.parseInt(txtIdCuotaExtraordinaria.getText());
                    int idFilial = Integer.parseInt(txtIdFilialCuotaExtraordinaria.getText());
                    String fechaPago = txtFechaPagoCuotaExtraordinaria.getText(); // Asegúrate de que este campo tenga un formato de fecha válido
                    double monto = Double.parseDouble(txtMontoCuotaExtraordinaria.getText());

                    // Preparar la declaración SQL
                    String sql = "INSERT INTO CUOTAS_EXTRAORDINARIAS (ID_CUOTA_EXTRA, ID_FILIAL, FECHA_PAGO, MONTO) VALUES (?, ?, ?, ?)";
                    pst = con.prepareStatement(sql);

                    // Establecer los parámetros de la declaración SQL
                    pst.setInt(1, idCuotaExtra);
                    pst.setInt(2, idFilial);
                    pst.setString(3, fechaPago);
                    pst.setDouble(4, monto);

                    // Ejecutar la declaración SQL
                    pst.executeUpdate();

                    // Notificar al usuario que el registro se ha completado
                    JOptionPane.showMessageDialog(null, "Registro de cuota extraordinaria exitoso");

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al registrar cuota extraordinaria");
                }
            }
        });
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
