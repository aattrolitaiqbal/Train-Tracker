import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;

public class TrainTracker extends JFrame {

    JComboBox<String> combo;
    JTextArea area;
    JProgressBar bar;

    Train silkcity;

    public TrainTracker() {

        setTitle("Bangladesh Train Tracker");
        setSize(700, 500);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel title = new JLabel("Bangladesh Train Tracker");
        title.setBounds(220, 10, 300, 30);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        add(title);

        combo = new JComboBox<>();
        combo.addItem("754-Silkcity Express");
        combo.setBounds(30, 70, 180, 30);
        add(combo);

        area = new JTextArea();
        area.setBounds(250, 70, 400, 250);
        area.setFont(new Font("Monospaced", Font.PLAIN, 16));
        add(area);

        bar = new JProgressBar();
        bar.setBounds(250, 350, 350, 30);
        add(bar);

        JButton btn = new JButton("Track");
        btn.setBounds(30, 120, 100, 30);
        add(btn);

        createTrain();

        btn.addActionListener(e -> showTrain());

        setVisible(true);
    }

    void createTrain() {

        silkcity = new Train(754, "Silkcity Express",
                "Rajshahi", "Dhaka",
                "07:40", "13:20") {

            @Override
            public void displayInfo() {
            }
        };

        silkcity.addStop("Rajshahi", 0);
        silkcity.addStop("Ishwardi", 60);
        silkcity.addStop("Natore", 90);
        silkcity.addStop("Sirajganj", 150);
        silkcity.addStop("Solop", 180);
        silkcity.addStop("Jamtail", 210);
        silkcity.addStop("Dhaka", 255);
    }

    void showTrain() {

        String day = LocalDate.now().getDayOfWeek().toString();

        if (day.equals("FRIDAY")) {
            area.setText("Train Off Day Today.");
            return;
        }

        Train t = silkcity;

        String text = "";
        text += "Train Number : " + t.number + "\n";
        text += "Train Name   : " + t.name + "\n";
        text += "Start        : " + t.startStation + "\n";
        text += "Departure    : " + t.departureTime + "\n";
        text += "Destination  : " + t.destination + "\n";
        text += "Arrival      : " + t.arrivalTime + "\n";
        text += "Stops        : " + t.totalStops() + "\n";
        text += "Current Loc  : " + t.currentLocation() + "\n";
        text += "Total Dist   : " + t.totalDistance() + " km\n";
        text += "Now Time     : " + LocalTime.now();

        area.setText(text);

        int passed = t.passedStops();
        bar.setMaximum(t.totalStops());
        bar.setValue(passed);
        bar.setStringPainted(true);
        bar.setString("Passed Stops: " + passed);

        DataManager.save(text);
    }

    public static void main(String[] args) {
        new TrainTracker();
    }
}