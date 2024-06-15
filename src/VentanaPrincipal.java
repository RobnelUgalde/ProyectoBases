import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

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
                    JOptionPane.showMessageDialog(null, "Error al registrar acceso");
                } catch (ParseException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al parsear la fecha");
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
