import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class MyJDBC_GUI extends Frame implements ActionListener {
    TextField tfName, tfNIM;
    TextArea taOutput;
    Button btnSave, btnDisplay;
    MyJDBC jdbcConnector;

    public MyJDBC_GUI() {
        setLayout(null); // Mengatur layout menjadi null untuk menggunakan setBounds
        setSize(500, 400);
        setTitle("Database Mahasiswa GUI");

        Label lblName = new Label("Nama:");
        lblName.setBounds(50, 50, 50, 20);
        add(lblName);

        tfName = new TextField(20);
        tfName.setBounds(120, 50, 150, 20);
        add(tfName);

        Label lblNIM = new Label("NIM:");
        lblNIM.setBounds(50, 80, 50, 20);
        add(lblNIM);

        tfNIM = new TextField(20);
        tfNIM.setBounds(120, 80, 150, 20);
        add(tfNIM);

        btnSave = new Button("Save");
        btnSave.setBounds(50, 120, 80, 30);
        btnSave.addActionListener(this);
        add(btnSave);

        btnDisplay = new Button("Display");
        btnDisplay.setBounds(150, 120, 80, 30);
        btnDisplay.addActionListener(this);
        add(btnDisplay);

        taOutput = new TextArea();
        taOutput.setBounds(50, 170, 400, 150);
        add(taOutput);

        jdbcConnector = new MyJDBC();

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
                jdbcConnector.close();
                System.exit(0);
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnSave) {
            saveData();
        } else if (ae.getSource() == btnDisplay) {
            displayData();
        }
    }

    public void saveData() {
        String name = tfName.getText();
        String nim = tfNIM.getText();

        try {
            String query = "INSERT INTO mahasiswa (NAMA, NIM) VALUES ('" + name + "', '" + nim + "')";
            jdbcConnector.executeUpdate(query);
            taOutput.setText("Data saved successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            taOutput.setText("Error saving data.");
        }
    }

    public void displayData() {
        try {
            ResultSet resultSet = jdbcConnector.executeQuery("SELECT * FROM mahasiswa");
            taOutput.setText("");
            while (resultSet.next()) {
                taOutput.append("Nama: " + resultSet.getString("NAMA") + "\n");
                taOutput.append("NIM: " + resultSet.getString("NIM") + "\n\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            taOutput.setText("Error retrieving data.");
        }
    }
}
