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

    private JTextField txtIdFilial;
    private JTextField txtIdDuenoFilial;
    private JTextField txtNumeroFilial;
    private JTextField txtUbicacionFilial;
    private JButton btnRegistrarFilial;

    private JTextField txtIdCuotaCondominal;
    private JTextField txtIdFilialCuotaCondominal;
    private JTextField txtFechaPagoCuotaCondominal;
    private JTextField txtMontoCuotaCondominal;
    private JButton btnRegistrarCuotaCondominal;

    private JTextField txtIdAcceso;
    private JTextField txtIdFilialAcceso;
    private JTextField txtFechaIngresoAcceso;
    private JTextField txtHoraIngresoAcceso;
    private JButton btnRegistrarAcceso;

    private JTextField txtIdCuotaExtraordinaria;
    private JTextField txtIdFilialCuotaExtraordinaria;
    private JTextField txtFechaPagoCuotaExtraordinaria;
    private JTextField txtMontoCuotaExtraordinaria;
    private JButton btnRegistrarCuotaExtraordinaria;

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

        btnRegistrarDueno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener los valores de los campos de texto
                int idDueno = Integer.parseInt(txtIdDueno.getText());
                String nombre = txtNombreDueno.getText();
                String apellido = txtApellidoDueno.getText();
                String direccion = txtDireccionDueno.getText();
                String telefono = txtTelefonoDueno.getText();

                try {
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
