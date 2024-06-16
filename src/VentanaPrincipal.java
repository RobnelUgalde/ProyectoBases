import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import oracle.jdbc.OracleTypes;
import java.util.Vector;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class VentanaPrincipal extends JFrame {
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JTable table;

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
        frame.setMaximumSize(new Dimension(100, 150));
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
        JPanel panelDuenos = new JPanel(new GridLayout());
        tabbedPane1.addTab("Dueños", panelDuenos);

        panelDuenos.setBorder(new EmptyBorder(10, 10, 10, 10));

        panelDuenos.setLayout(new FlowLayout(FlowLayout.LEFT,20, 10));
        panelDuenos.add(new JLabel("ID Dueño:"));
        txtIdDueno = new JTextField(50);
        panelDuenos.add(txtIdDueno);


        panelDuenos.add(new JLabel("Nombre:"));
        txtNombreDueno = new JTextField(50);
        panelDuenos.add(txtNombreDueno);

        panelDuenos.add(new JLabel("Apellido:"));
        txtApellidoDueno = new JTextField(50);
        panelDuenos.add(txtApellidoDueno);

        panelDuenos.add(new JLabel("Dirección:"));
        txtDireccionDueno = new JTextField(50);
        panelDuenos.add(txtDireccionDueno);

        panelDuenos.add(new JLabel("Teléfono:"));
        txtTelefonoDueno = new JTextField(50);
        panelDuenos.add(txtTelefonoDueno);

        panelDuenos.setLayout(new FlowLayout(FlowLayout.LEFT,20, 10));
        btnRegistrarDueno = new JButton("Registrar Dueño");
        panelDuenos.add(btnRegistrarDueno);

        btnActualizarDueno = new JButton("Actualizar Dueño");
        panelDuenos.add(btnActualizarDueno); // Asegúrate de que esté inicializado antes de agregar el ActionListener

        btnEliminarDueno = new JButton("Eliminar Dueño");
        panelDuenos.add(btnEliminarDueno); // Asegúrate de que esté inicializado antes de agregar el ActionListener


        // ActionListener para el botón Registrar Dueño
        btnRegistrarDueno.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idDueno = Integer.parseInt(txtIdDueno.getText());
                    String nombre = txtNombreDueno.getText();
                    String apellido = txtApellidoDueno.getText();
                    String direccion = txtDireccionDueno.getText();
                    String telefono = txtTelefonoDueno.getText();

                    // Llamar al procedimiento almacenado
                    CallableStatement cst = con.prepareCall("{call SP_DUEÑOS(?, ?, ?, ?, ?, ?)}");
                    cst.setString(1, "INSERT");
                    cst.setInt(2, idDueno);
                    cst.setString(3, nombre);
                    cst.setString(4, apellido);
                    cst.setString(5, direccion);
                    cst.setString(6, telefono);

                    cst.execute();

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
                    int idDueno = Integer.parseInt(txtIdDueno.getText());
                    String nombre = txtNombreDueno.getText();
                    String apellido = txtApellidoDueno.getText();
                    String direccion = txtDireccionDueno.getText();
                    String telefono = txtTelefonoDueno.getText();

                    // Llamar al procedimiento almacenado
                    CallableStatement cst = con.prepareCall("{call SP_DUEÑOS(?, ?, ?, ?, ?, ?)}");
                    cst.setString(1, "UPDATE");
                    cst.setInt(2, idDueno);
                    cst.setString(3, nombre);
                    cst.setString(4, apellido);
                    cst.setString(5, direccion);
                    cst.setString(6, telefono);

                    cst.execute();

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
                    int idDueno = Integer.parseInt(txtIdDueno.getText());

                    // Llamar al procedimiento almacenado
                    CallableStatement cst = con.prepareCall("{call SP_DUEÑOS(?, ?, NULL, NULL, NULL, NULL)}");
                    cst.setString(1, "DELETE");
                    cst.setInt(2, idDueno);

                    cst.execute();

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

        panelFiliales.setBorder(new EmptyBorder(10, 10, 10, 10));

        panelFiliales.setLayout(new FlowLayout(FlowLayout.LEFT,20, 10));

        panelFiliales.add(new JLabel("ID Filial:"));
        txtIdFilial = new JTextField(50);
        panelFiliales.add(txtIdFilial);

        panelFiliales.add(new JLabel("ID Dueño:"));
        txtIdDuenoFilial = new JTextField(50);
        panelFiliales.add(txtIdDuenoFilial);

        panelFiliales.add(new JLabel("Número Filial:"));
        txtNumeroFilial = new JTextField(50);
        panelFiliales.add(txtNumeroFilial);

        panelFiliales.add(new JLabel("Ubicación:"));
        txtUbicacionFilial = new JTextField(50);
        panelFiliales.add(txtUbicacionFilial);

        btnRegistrarFilial = new JButton("Registrar Filial");
        panelFiliales.add(btnRegistrarFilial);
        btnActualizarFilial = new JButton("Actualizar Filial");
        panelFiliales.add(btnActualizarFilial);

        btnEliminarFilial = new JButton("Eliminar Filial");
        panelFiliales.add(btnEliminarFilial);

        btnRegistrarFilial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idFilial = Integer.parseInt(txtIdFilial.getText());
                    int idDueno = Integer.parseInt(txtIdDuenoFilial.getText());
                    int numeroFilial = Integer.parseInt(txtNumeroFilial.getText());
                    String ubicacion = txtUbicacionFilial.getText();

                    // Llamar al procedimiento almacenado
                    CallableStatement cst = con.prepareCall("{call SP_FILIALES(?, ?, ?, ?, ?)}");
                    cst.setString(1, "INSERT");
                    cst.setInt(2, idFilial);
                    cst.setInt(3, idDueno);
                    cst.setInt(4, numeroFilial);
                    cst.setString(5, ubicacion);

                    cst.execute();

                    JOptionPane.showMessageDialog(null, "Registro de filial exitoso");

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al registrar filial");
                }
            }
        });

// ActionListener para el botón Actualizar Filial
        btnActualizarFilial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idFilial = Integer.parseInt(txtIdFilial.getText());
                    int idDueno = Integer.parseInt(txtIdDuenoFilial.getText());
                    int numeroFilial = Integer.parseInt(txtNumeroFilial.getText());
                    String ubicacion = txtUbicacionFilial.getText();

                    // Llamar al procedimiento almacenado
                    CallableStatement cst = con.prepareCall("{call SP_FILIALES(?, ?, ?, ?, ?)}");
                    cst.setString(1, "UPDATE");
                    cst.setInt(2, idFilial);
                    cst.setInt(3, idDueno);
                    cst.setInt(4, numeroFilial);
                    cst.setString(5, ubicacion);

                    cst.execute();

                    JOptionPane.showMessageDialog(null, "Actualización de filial exitosa");

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al actualizar filial");
                }
            }
        });

// ActionListener para el botón Eliminar Filial
        btnEliminarFilial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idFilial = Integer.parseInt(txtIdFilial.getText());

                    // Llamar al procedimiento almacenado
                    CallableStatement cst = con.prepareCall("{call SP_FILIALES(?, ?, NULL, NULL, NULL)}");
                    cst.setString(1, "DELETE");
                    cst.setInt(2, idFilial);

                    cst.execute();

                    JOptionPane.showMessageDialog(null, "Eliminación de filial exitosa");

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al eliminar filial");
                }
            }
        });

        // Pestaña para SP_CUOTAS_CONDOMINALES
        JPanel panelCuotasCondominales = new JPanel(new GridLayout(0, 2));
        tabbedPane1.addTab("Cuotas Condominales", panelCuotasCondominales);

        panelCuotasCondominales.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelCuotasCondominales.setLayout(new FlowLayout(FlowLayout.LEFT,20, 10));

        panelCuotasCondominales.add(new JLabel("ID Cuota:"));
        txtIdCuotaCondominal = new JTextField(50);
        panelCuotasCondominales.add(txtIdCuotaCondominal);

        panelCuotasCondominales.add(new JLabel("ID Filial:"));
        txtIdFilialCuotaCondominal = new JTextField(50);
        panelCuotasCondominales.add(txtIdFilialCuotaCondominal);

        panelCuotasCondominales.add(new JLabel("Fecha Pago:"));
        txtFechaPagoCuotaCondominal = new JTextField(50);
        panelCuotasCondominales.add(txtFechaPagoCuotaCondominal);

        panelCuotasCondominales.add(new JLabel("Monto:"));
        txtMontoCuotaCondominal = new JTextField(50);
        panelCuotasCondominales.add(txtMontoCuotaCondominal);

        btnRegistrarCuotaCondominal = new JButton("Registrar Cuota Condominal");
        panelCuotasCondominales.add(btnRegistrarCuotaCondominal);

        btnActualizarCuotaCondominal = new JButton("Actualizar Cuota");
        panelCuotasCondominales.add(btnActualizarCuotaCondominal);

        btnEliminarCuotaCondominal = new JButton("Eliminar Cuota");
        panelCuotasCondominales.add(btnEliminarCuotaCondominal);

        // ActionListener para el botón Registrar Cuota Condominal
        btnRegistrarCuotaCondominal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idCuota = Integer.parseInt(txtIdCuotaCondominal.getText());
                    int idFilial = Integer.parseInt(txtIdFilialCuotaCondominal.getText());
                    String fechaPago = txtFechaPagoCuotaCondominal.getText();
                    double monto = Double.parseDouble(txtMontoCuotaCondominal.getText());

                    // Convertir la fecha de String a java.sql.Date
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date parsedDate = sdf.parse(fechaPago);
                    java.sql.Date sqlFechaPago = new java.sql.Date(parsedDate.getTime());

                    // Llamar al procedimiento almacenado
                    CallableStatement cst = con.prepareCall("{call SP_CUOTAS_CONDOMINALES(?, ?, ?, ?, ?)}");
                    cst.setString(1, "INSERT");
                    cst.setInt(2, idCuota);
                    cst.setInt(3, idFilial);
                    cst.setDate(4, sqlFechaPago);
                    cst.setDouble(5, monto);

                    cst.execute();

                    JOptionPane.showMessageDialog(null, "Registro de cuota condominal exitoso");

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al registrar cuota condominal");
                } catch (ParseException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al parsear la fecha");
                }
            }
        });

// ActionListener para el botón Actualizar Cuota Condominal
        btnActualizarCuotaCondominal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idCuota = Integer.parseInt(txtIdCuotaCondominal.getText());
                    int idFilial = Integer.parseInt(txtIdFilialCuotaCondominal.getText());
                    String fechaPago = txtFechaPagoCuotaCondominal.getText();
                    double monto = Double.parseDouble(txtMontoCuotaCondominal.getText());

                    // Convertir la fecha de String a java.sql.Date
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date parsedDate = sdf.parse(fechaPago);
                    java.sql.Date sqlFechaPago = new java.sql.Date(parsedDate.getTime());

                    // Llamar al procedimiento almacenado
                    CallableStatement cst = con.prepareCall("{call SP_CUOTAS_CONDOMINALES(?, ?, ?, ?, ?)}");
                    cst.setString(1, "UPDATE");
                    cst.setInt(2, idCuota);
                    cst.setInt(3, idFilial);
                    cst.setDate(4, sqlFechaPago);
                    cst.setDouble(5, monto);

                    int rowsAffected = cst.executeUpdate();

                    // Verificar si se actualizó correctamente
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Registro de cuota condominal actualizado exitosamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró la cuota con ID " + idCuota);
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al actualizar cuota condominal");
                } catch (ParseException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al parsear la fecha");
                }
            }
        });

// ActionListener para el botón Eliminar Cuota Condominal
        btnEliminarCuotaCondominal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idCuota = Integer.parseInt(txtIdCuotaCondominal.getText());

                    // Llamar al procedimiento almacenado
                    CallableStatement cst = con.prepareCall("{call SP_CUOTAS_CONDOMINALES(?, ?, NULL, NULL, NULL)}");
                    cst.setString(1, "DELETE");
                    cst.setInt(2, idCuota);

                    int rowsAffected = cst.executeUpdate();

                    // Verificar si se eliminó correctamente
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(null, "Registro de cuota condominal eliminado exitosamente");
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró la cuota con ID " + idCuota);
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al eliminar cuota condominal");
                }
            }
        });

        // Pestaña para SP_ACCESOS
        JPanel panelAccesos = new JPanel(new GridLayout(0, 2));
        tabbedPane1.addTab("Accesos", panelAccesos);

        panelAccesos.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelAccesos.setLayout(new FlowLayout(FlowLayout.LEFT,20, 10));

        panelAccesos.add(new JLabel("ID Acceso:"));
        txtIdAcceso = new JTextField(50);
        panelAccesos.add(txtIdAcceso);

        panelAccesos.add(new JLabel("ID Filial:"));
        txtIdFilialAcceso = new JTextField(50);
        panelAccesos.add(txtIdFilialAcceso);

        panelAccesos.add(new JLabel("Fecha Ingreso:"));
        txtFechaIngresoAcceso = new JTextField(50);
        panelAccesos.add(txtFechaIngresoAcceso);

        panelAccesos.add(new JLabel("Hora Ingreso:"));
        txtHoraIngresoAcceso = new JTextField(50);
        panelAccesos.add(txtHoraIngresoAcceso);

        btnRegistrarAcceso = new JButton("Registrar Acceso");
        panelAccesos.add(btnRegistrarAcceso);

        btnActualizarAcceso = new JButton("Actualizar Acceso");
        panelAccesos.add(btnActualizarAcceso);

        btnEliminarAcceso = new JButton("Eliminar Acceso");
        panelAccesos.add(btnEliminarAcceso);

        btnRegistrarAcceso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Obtener valores de los campos de texto
                    int idAcceso = Integer.parseInt(txtIdAcceso.getText());
                    int idFilial = Integer.parseInt(txtIdFilialAcceso.getText());
                    String fechaIngreso = txtFechaIngresoAcceso.getText(); // asumiendo formato 'yyyy-MM-dd'
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date parsedDate = sdf.parse(fechaIngreso);
                    java.sql.Date sqlFechaIngreso = new java.sql.Date(parsedDate.getTime());
                    String horaIngreso = txtHoraIngresoAcceso.getText(); // asumiendo formato 'HH:mm'

                    // Llamar al procedimiento almacenado
                    CallableStatement cstmt = con.prepareCall("{ call SP_ACCESOS(?, ?, ?, ?, ?) }");
                    cstmt.setString(1, "INSERT");
                    cstmt.setInt(2, idAcceso);
                    cstmt.setInt(3, idFilial);
                    cstmt.setDate(4, sqlFechaIngreso);
                    cstmt.setString(5, horaIngreso);

                    cstmt.execute();

                    JOptionPane.showMessageDialog(null, "Acceso registrado correctamente");

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al registrar acceso: " + ex.getMessage());
                } catch (ParseException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al parsear la fecha: " + ex.getMessage());
                }
            }
        });


// Listener para el botón Actualizar Acceso
        btnActualizarAcceso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Obtener valores de los campos de texto
                    int idAcceso = Integer.parseInt(txtIdAcceso.getText());
                    int idFilial = Integer.parseInt(txtIdFilialAcceso.getText());
                    String fechaIngreso = txtFechaIngresoAcceso.getText(); // asumiendo formato 'yyyy-MM-dd'
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date parsedDate = sdf.parse(fechaIngreso);
                    java.sql.Date sqlFechaIngreso = new java.sql.Date(parsedDate.getTime());
                    String horaIngreso = txtHoraIngresoAcceso.getText(); // asumiendo formato 'HH:mm'

                    // Llamar al procedimiento almacenado
                    CallableStatement cstmt = con.prepareCall("{ call SP_ACCESOS(?, ?, ?, ?, ?) }");
                    cstmt.setString(1, "UPDATE");
                    cstmt.setInt(2, idAcceso);
                    cstmt.setInt(3, idFilial);
                    cstmt.setDate(4, sqlFechaIngreso);
                    cstmt.setString(5, horaIngreso);

                    cstmt.execute();

                    JOptionPane.showMessageDialog(null, "Acceso actualizado correctamente");

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al actualizar acceso");
                } catch (ParseException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al parsear la fecha");
                }
            }
        });

// Listener para el botón Eliminar Acceso
        btnEliminarAcceso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Obtener el ID de acceso a eliminar
                    int idAcceso = Integer.parseInt(txtIdAcceso.getText());

                    // Llamar al procedimiento almacenado
                    CallableStatement cstmt = con.prepareCall("{ call SP_ACCESOS(?, ?, ?, ?, ?) }");
                    cstmt.setString(1, "DELETE");
                    cstmt.setInt(2, idAcceso);
                    cstmt.setNull(3, Types.INTEGER); // P_ID_FILIAL, no se necesita para DELETE
                    cstmt.setNull(4, Types.DATE);    // P_FECHA_INGRESO, no se necesita para DELETE
                    cstmt.setNull(5, Types.VARCHAR); // P_HORA_INGRESO, no se necesita para DELETE

                    cstmt.execute();

                    JOptionPane.showMessageDialog(null, "Acceso eliminado correctamente");

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al eliminar acceso");
                }
            }
        });

        // Pestaña para SP_CUOTAS_EXTRAORDINARIAS
        JPanel panelCuotasExtraordinarias = new JPanel(new GridLayout(0, 2));
        tabbedPane1.addTab("Cuotas Extraordinarias", panelCuotasExtraordinarias);

        panelCuotasExtraordinarias.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelCuotasExtraordinarias.setLayout(new FlowLayout(FlowLayout.LEFT,20, 10));

        panelCuotasExtraordinarias.add(new JLabel("ID Cuota Extra:"));
        txtIdCuotaExtraordinaria = new JTextField(50);
        panelCuotasExtraordinarias.add(txtIdCuotaExtraordinaria);

        panelCuotasExtraordinarias.add(new JLabel("ID Filial:"));
        txtIdFilialCuotaExtraordinaria = new JTextField(50);
        panelCuotasExtraordinarias.add(txtIdFilialCuotaExtraordinaria);

        panelCuotasExtraordinarias.add(new JLabel("Fecha Pago:"));
        txtFechaPagoCuotaExtraordinaria = new JTextField(50);
        panelCuotasExtraordinarias.add(txtFechaPagoCuotaExtraordinaria);

        panelCuotasExtraordinarias.add(new JLabel("Monto:"));
        txtMontoCuotaExtraordinaria = new JTextField(50);
        panelCuotasExtraordinarias.add(txtMontoCuotaExtraordinaria);

        btnRegistrarCuotaExtraordinaria = new JButton("Registrar Cuota Extraordinaria");
        panelCuotasExtraordinarias.add(btnRegistrarCuotaExtraordinaria);

        btnActualizarCuotaExtraordinaria = new JButton("Actualizar Cuota Extraordinaria");
        panelCuotasExtraordinarias.add(btnActualizarCuotaExtraordinaria);

        btnEliminarCuotaExtraordinaria = new JButton("Eliminar Cuota Extraordinaria");
        panelCuotasExtraordinarias.add(btnEliminarCuotaExtraordinaria);

        btnRegistrarCuotaExtraordinaria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Obtener valores de los campos de texto
                    int idCuotaExtra = Integer.parseInt(txtIdCuotaExtraordinaria.getText());
                    int idFilial = Integer.parseInt(txtIdFilialCuotaExtraordinaria.getText());
                    String fechaPago = txtFechaPagoCuotaExtraordinaria.getText(); // asumiendo formato 'yyyy-MM-dd'
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date parsedDate = sdf.parse(fechaPago);
                    java.sql.Date sqlFechaPago = new java.sql.Date(parsedDate.getTime());
                    double monto = Double.parseDouble(txtMontoCuotaExtraordinaria.getText());

                    // Llamar al procedimiento almacenado
                    CallableStatement cstmt = con.prepareCall("{ call SP_CUOTAS_EXTRAORDINARIAS(?, ?, ?, ?, ?) }");
                    cstmt.setString(1, "INSERT");
                    cstmt.setInt(2, idCuotaExtra);
                    cstmt.setInt(3, idFilial);
                    cstmt.setDate(4, sqlFechaPago);
                    cstmt.setDouble(5, monto);

                    cstmt.execute();

                    JOptionPane.showMessageDialog(null, "Cuota extraordinaria registrada correctamente");

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al registrar cuota extraordinaria");
                } catch (ParseException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al parsear la fecha");
                }
            }
        });

// Listener para el botón Actualizar Cuota Extraordinaria
        btnActualizarCuotaExtraordinaria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Obtener valores de los campos de texto
                    int idCuotaExtra = Integer.parseInt(txtIdCuotaExtraordinaria.getText());
                    int idFilial = Integer.parseInt(txtIdFilialCuotaExtraordinaria.getText());
                    String fechaPago = txtFechaPagoCuotaExtraordinaria.getText(); // asumiendo formato 'yyyy-MM-dd'
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date parsedDate = sdf.parse(fechaPago);
                    java.sql.Date sqlFechaPago = new java.sql.Date(parsedDate.getTime());
                    double monto = Double.parseDouble(txtMontoCuotaExtraordinaria.getText());

                    // Llamar al procedimiento almacenado
                    CallableStatement cstmt = con.prepareCall("{ call SP_CUOTAS_EXTRAORDINARIAS(?, ?, ?, ?, ?) }");
                    cstmt.setString(1, "UPDATE");
                    cstmt.setInt(2, idCuotaExtra);
                    cstmt.setInt(3, idFilial);
                    cstmt.setDate(4, sqlFechaPago);
                    cstmt.setDouble(5, monto);

                    cstmt.execute();

                    JOptionPane.showMessageDialog(null, "Cuota extraordinaria actualizada correctamente");

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al actualizar cuota extraordinaria");
                } catch (ParseException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al parsear la fecha");
                }
            }
        });

// Listener para el botón Eliminar Cuota Extraordinaria
        btnEliminarCuotaExtraordinaria.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Obtener el ID de cuota extraordinaria a eliminar
                    int idCuotaExtra = Integer.parseInt(txtIdCuotaExtraordinaria.getText());

                    // Llamar al procedimiento almacenado
                    CallableStatement cstmt = con.prepareCall("{ call SP_CUOTAS_EXTRAORDINARIAS(?, ?, ?, ?, ?) }");
                    cstmt.setString(1, "DELETE");
                    cstmt.setInt(2, idCuotaExtra);
                    cstmt.setNull(3, Types.INTEGER); // P_ID_FILIAL, no se necesita para DELETE
                    cstmt.setNull(4, Types.DATE);    // P_FECHA_PAGO, no se necesita para DELETE
                    cstmt.setNull(5, Types.DOUBLE);  // P_MONTO, no se necesita para DELETE

                    cstmt.execute();

                    JOptionPane.showMessageDialog(null, "Cuota extraordinaria eliminada correctamente");

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al eliminar cuota extraordinaria");
                }
            }
        });
//-------------Mostrar listado-----------------

        JPanel panelListado = new JPanel(new GridLayout(0, 2));
        tabbedPane1.addTab("Listado", panelListado);
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        panelListado.add(scrollPane);
        panelListado.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

        JButton btnConsultarDueñosFiliales = new JButton("Consultar Listado de Clientes y Filiales");
        panelListado.add(btnConsultarDueñosFiliales);

        JButton btnActualizarTabla = new JButton("Actualizar Tabla");
        panelListado.add(btnActualizarTabla);

        btnConsultarDueñosFiliales.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    CallableStatement cst = con.prepareCall("{call SP_LISTAR_DUEÑOS_FILIALES(?)}");

                    cst.registerOutParameter(1, OracleTypes.CURSOR);
                    cst.execute();

                    ResultSet rs = (ResultSet) cst.getObject(1);

                    if (!rs.isBeforeFirst()) {
                        JOptionPane.showMessageDialog(null, "No se encontraron resultados para listar dueños y filiales.");
                        return;
                    }
                    DefaultTableModel model = new DefaultTableModel();
                    model.addColumn("ID_DUEÑO");
                    model.addColumn("NOMBRE");
                    model.addColumn("APELLIDO");
                    model.addColumn("ID_FILIAL");
                    model.addColumn("NUMERO_FILIAL");
                    model.addColumn("UBICACIÓN");

                    // Llenar el modelo con los datos del ResultSet
                    while (rs.next()) {
                        Object[] row = new Object[6];
                        row[0] = rs.getInt("ID_DUEÑO");
                        row[1] = rs.getString("NOMBRE");
                        row[2] = rs.getString("APELLIDO");
                        row[3] = rs.getInt("ID_FILIAL");
                        row[4] = rs.getInt("NUMERO_FILIAL");
                        row[5] = rs.getString("UBICACIÓN");
                        model.addRow(row);
                    }

                    // Establecer el modelo en la tabla
                    table.setModel(model);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al listar dueños y filiales: " + ex.getMessage());
                } catch (NullPointerException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "NullPointerException: " + ex.getMessage());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error inesperado: " + ex.getMessage());
                }
            }
        });
        btnActualizarTabla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    CallableStatement cst = con.prepareCall("{call SP_LISTAR_DUEÑOS_FILIALES(?)}");

                    cst.registerOutParameter(1, OracleTypes.CURSOR);

                    cst.execute();

                    ResultSet rs = (ResultSet) cst.getObject(1);

                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.setRowCount(0); // Elimina todas las filas actuales


                    while (rs.next()) {
                        Object[] row = new Object[6];
                        row[0] = rs.getInt("ID_DUEÑO");
                        row[1] = rs.getString("NOMBRE");
                        row[2] = rs.getString("APELLIDO");
                        row[3] = rs.getInt("ID_FILIAL");
                        row[4] = rs.getInt("NUMERO_FILIAL");
                        row[5] = rs.getString("UBICACIÓN");
                        model.addRow(row);
                    }

                    model.fireTableDataChanged();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al actualizar la tabla: " + ex.getMessage());
                } catch (NullPointerException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "NullPointerException: " + ex.getMessage());
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error inesperado: " + ex.getMessage());
                }
            }
        });

        //-------------Mostrar AUDITORIA-----------------
        JPanel panelAuditoria = new JPanel(new GridLayout(0, 2));
        tabbedPane1.addTab("Auditoria", panelAuditoria);

        panelAuditoria.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        JButton mostrarAuditoriaButton = new JButton("Mostrar Auditoría");
        panelAuditoria.add(mostrarAuditoriaButton, BorderLayout.NORTH);

        mostrarAuditoriaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    String query = "SELECT * FROM AUDITORIA_CUOTAS";
                    pst = con.prepareStatement(query);
                    ResultSet rs = pst.executeQuery();

                    ResultSetMetaData rsmd = rs.getMetaData();
                    int columnCount = rsmd.getColumnCount();


                    Vector<String> columnNames = new Vector<>();
                    for (int i = 1; i <= columnCount; i++) {
                        columnNames.add(rsmd.getColumnName(i));
                    }

                    Vector<Vector<Object>> data = new Vector<>();
                    while (rs.next()) {
                        Vector<Object> row = new Vector<>();
                        for (int i = 1; i <= columnCount; i++) {
                            row.add(rs.getObject(i));
                        }
                        data.add(row);
                    }

                    DefaultTableModel model = new DefaultTableModel(data, columnNames);
                    JTable tableAuditoria = new JTable(model);

                    JScrollPane scrollPane = new JScrollPane(tableAuditoria);

                    scrollPane.setPreferredSize(new Dimension(800, 600));
                    JOptionPane.showMessageDialog(null, scrollPane, "Tabla de Auditoría de Cuotas", JOptionPane.INFORMATION_MESSAGE);

                } catch (SQLException ex) {
                    ex.printStackTrace();
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
