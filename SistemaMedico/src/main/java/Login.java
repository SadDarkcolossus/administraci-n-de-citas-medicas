import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    private JPanel panel1;
    private JButton LoginB;
    private JButton ExitB;
    private JTextField UserTxt;
    private JPasswordField PasswordTxt;
    private JFrame frame = new JFrame("Login");
    public Login() {
        LoginB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = UserTxt.getText();
                String password = new String(PasswordTxt.getPassword());

                // Aquí puedes agregar el código para verificar el nombre de usuario y la contraseña
                try {
                    // Establece la conexión con tu base de datos
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/adminmedico", "root", "Root1234");

                    // Prepara la consulta SQL
                    PreparedStatement statement = connection.prepareStatement("SELECT * FROM usuarios WHERE usuario = ? AND contraseña = ?");
                    statement.setString(1, username);
                    statement.setString(2, password);

                    // Ejecuta la consulta y obtén los resultados
                    ResultSet resultSet = statement.executeQuery();

                    // Si la consulta devuelve un resultado, entonces las credenciales son correctas
                    if (resultSet.next()) {
                        JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso!");
                        // Crea una instancia de la nueva interfaz y hazla visible
                        Menu menu = new Menu();
                        menu.setVisible(true);
                        // Cierra la interfaz de inicio de sesión
                        frame.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.");
                    }

                    // Cierra la conexión a la base de datos
                    connection.close();
                } catch (SQLException sqlException) {
                    sqlException.printStackTrace();
                }
            }
        });

        ExitB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(new Login().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
