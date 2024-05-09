import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Menu extends JFrame {
    private JTabbedPane OpcionesMenu;
    private JPanel AltaPaciente;
    private JPanel AltaDoctor;
    private JPanel Citas;
    private JTextField Nom_Doc;
    private JTextField Especialidad_Doc;
    private JTable tab_Pacientes;
    private JTextField Nom_paciente;
    private JTable tab_Citas;
    private JTextField idDoctor;
    private JTextField IDPaciente;
    private JTextField FechaCita;
    private JTable tab_Doctor;
    private JButton btn_Add_Doc;
    private JButton btn_Add_Paciente;
    private JButton btn_Add_Cita;

    public Menu() {
        // Agrega los paneles al JTabbedPane
        OpcionesMenu.addTab("Alta Doctor", AltaDoctor);
        OpcionesMenu.addTab("Alta Paciente", AltaPaciente);
        OpcionesMenu.addTab("Citas", Citas);

        // Agrega el JTabbedPane al JFrame
        add(OpcionesMenu);

        // Configura el JFrame
        setTitle("Menu");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Agrega funcionalidad a los botones
        btn_Add_Doc.addActionListener(e -> addDoctor());
        btn_Add_Paciente.addActionListener(e -> addPaciente());
        btn_Add_Cita.addActionListener(e -> addCita());

        loadTables();
    }

    private void addDoctor() {
        // Aquí puedes agregar el código para agregar un doctor a la base de datos
        // Por ejemplo, puedes usar un PreparedStatement para insertar los datos en la tabla
    }

    private void addPaciente() {
        // Aquí puedes agregar el código para agregar un paciente a la base de datos
        // Por ejemplo, puedes usar un PreparedStatement para insertar los datos en la tabla
    }

    private void addCita() {
        // Aquí puedes agregar el código para agregar una cita a la base de datos
        // Por ejemplo, puedes usar un PreparedStatement para insertar los datos en la tabla
    }

    private void loadTables() {
        try {
            // Establece la conexión con tu base de datos
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/adminmedico", "root", "Root1234");

            // Crea una sentencia SQL
            Statement statement = connection.createStatement();

            // Ejecuta una consulta para obtener los datos de los doctores
            ResultSet resultSet = statement.executeQuery("SELECT * FROM doctor");
            while (resultSet.next()) {
                // Agrega los datos de los doctores a la tabla
                ((DefaultTableModel) tab_Doctor.getModel()).addRow(new Object[]{
                    resultSet.getString("iddoctor"),
                    resultSet.getString("nombredoc"),
                    resultSet.getString("especialidad")
                });
            }

            // Ejecuta una consulta para obtener los datos de los pacientes
            resultSet = statement.executeQuery("SELECT * FROM paciente");
            while (resultSet.next()) {
                // Agrega los datos de los pacientes a la tabla
                ((DefaultTableModel) tab_Pacientes.getModel()).addRow(new Object[]{
                    resultSet.getString("idpaciente"),
                    resultSet.getString("nombrepaciente")
                });
            }

            // Ejecuta una consulta para obtener los datos de las citas
            resultSet = statement.executeQuery("SELECT * FROM citas");
            while (resultSet.next()) {
                // Agrega los datos de las citas a la tabla
                ((DefaultTableModel) tab_Citas.getModel()).addRow(new Object[]{
                    resultSet.getString("idcitas"),
                    resultSet.getString("iddoctor"),
                    resultSet.getString("idpaciente"),
                    resultSet.getString("fechacita")
                });
            }

            // Cierra la conexión a la base de datos
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.setVisible(true);
    }
}
