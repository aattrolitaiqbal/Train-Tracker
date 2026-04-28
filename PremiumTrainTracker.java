import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.geom.*;

public class PremiumTrainTracker extends JFrame {

    JComboBox<String> trainBox;
    JPanel routePanel;

    public PremiumTrainTracker() {
        setTitle("Bangladesh Train Tracker");
        setSize(1500, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        getContentPane().setBackground(Color.BLACK);

        // ================= HEADER =================
        JPanel header = new JPanel();
        header.setBounds(20, 20, 1450, 80);
        header.setBackground(new Color(110, 0, 0));
        header.setBorder(new LineBorder(new Color(255, 100, 100), 2));
        header.setLayout(null);

        JLabel title = new JLabel("Bangladesh Train Tracker");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 42));
        title.setBounds(420, 12, 650, 50);
        header.add(title);

        JLabel close = new JLabel("✕");
        close.setForeground(Color.WHITE);
        close.setFont(new Font("Segoe UI", Font.BOLD, 28));
        close.setBounds(1380, 18, 30, 30);
        header.add(close);

        add(header);

        // ================= LEFT PANEL =================
        JPanel left = glassPanel();
        left.setBounds(20, 110, 420, 730);
        left.setLayout(null);
        add(left);

        JLabel select = new JLabel("🚆 Select Train");
        select.setForeground(new Color(180, 255, 180));
        select.setFont(new Font("Segoe UI", Font.BOLD, 26));
        select.setBounds(40, 30, 250, 40);
        left.add(select);

        trainBox = new JComboBox<>();
        trainBox.addItem("754-Silkcity Express");
        trainBox.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        trainBox.setBounds(40, 90, 320, 55);
        left.add(trainBox);

        // LOGO 1
        JLabel logo1 = new JLabel("🇧🇩");
        logo1.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 90));
        logo1.setBounds(70, 220, 120, 120);
        left.add(logo1);

        // LOGO 2
        JLabel logo2 = new JLabel("🚉");
        logo2.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 90));
        logo2.setBounds(220, 220, 120, 120);
        left.add(logo2);

        // Track Box
        JPanel trackBox = glassPanel();
        trackBox.setBounds(30, 500, 360, 170);
        trackBox.setLayout(null);

        JLabel icon = new JLabel("🚆");
        icon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 80));
        icon.setBounds(25, 30, 100, 100);
        trackBox.add(icon);

        JLabel txt = new JLabel("TRACK");
        txt.setForeground(Color.WHITE);
        txt.setFont(new Font("Segoe UI", Font.BOLD, 38));
        txt.setBounds(160, 45, 200, 40);
        trackBox.add(txt);

        JLabel txt2 = new JLabel("Journey With Confidence");
        txt2.setForeground(new Color(130, 255, 130));
        txt2.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        txt2.setBounds(150, 95, 220, 30);
        trackBox.add(txt2);

        left.add(trackBox);

        // ================= RIGHT PANEL =================
        JPanel right = glassPanel();
        right.setBounds(460, 110, 1010, 730);
        right.setLayout(null);
        add(right);

        String[][] info = {
                {"1.", "Train Number:", "754"},
                {"2.", "Train Name:", "Silkcity Express"},
                {"3.", "Start Station:", "Rajshahi"},
                {"4.", "Time of Departure:", "07:40"},
                {"5.", "Destination Station:", "Dhaka"},
                {"6.", "Time of Arrival:", "13:20"},
                {"7.", "Number of Stops:", "12"},
                {"8.", "Next Station:", "Solop"},
                {"9.", "Next Stop:", "Jamtail"},
                {"10.", "Total Distance:", "255 km"}
        };

        int y = 30;
        for (String[] row : info) {
            JLabel no = new JLabel(row[0]);
            no.setForeground(Color.WHITE);
            no.setFont(new Font("Segoe UI", Font.BOLD, 22));
            no.setBounds(40, y, 40, 30);
            right.add(no);

            JLabel label = new JLabel(row[1]);
            label.setForeground(Color.WHITE);
            label.setFont(new Font("Segoe UI", Font.PLAIN, 22));
            label.setBounds(90, y, 320, 30);
            right.add(label);

            JLabel value = new JLabel(row[2]);
            value.setForeground(new Color(0, 220, 255));
            value.setFont(new Font("Segoe UI", Font.BOLD, 22));
            value.setBounds(470, y, 400, 30);
            right.add(value);

            y += 52;
        }

        // ETA BOX
        JPanel eta = glassPanel();
        eta.setBounds(800, 210, 160, 180);
        eta.setLayout(null);

        JLabel e1 = new JLabel("ETA");
        e1.setForeground(new Color(100, 255, 100));
        e1.setFont(new Font("Segoe UI", Font.BOLD, 28));
        e1.setBounds(45, 20, 100, 30);
        eta.add(e1);

        JLabel e2 = new JLabel("02:50");
        e2.setForeground(Color.WHITE);
        e2.setFont(new Font("Segoe UI", Font.BOLD, 46));
        e2.setBounds(18, 70, 150, 50);
        eta.add(e2);

        JLabel e3 = new JLabel("hours");
        e3.setForeground(new Color(180, 255, 180));
        e3.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        e3.setBounds(50, 130, 100, 30);
        eta.add(e3);

        right.add(eta);

        // ROUTE PANEL
        routePanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                // yellow line
                g2.setColor(Color.YELLOW);
                g2.fillRoundRect(40, 60, 880, 8, 10, 10);

                int x = 50;

                String[] stations = {
                        "Rajshahi", "Ishwardi", "Natore",
                        "Sirajganj", "Solop", "Jamtail",
                        "Bhuapur", "Tangail", "Joydebpur",
                        "Airport", "Tejgaon", "Dhaka"
                };

                for (int i = 0; i < stations.length; i++) {

                    if (i < 5)
                        g2.setColor(new Color(0, 255, 100)); // green
                    else
                        g2.setColor(Color.RED); // red

                    g2.fillOval(x, 48, 22, 22);

                    g2.setColor(Color.WHITE);
                    g2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                    g2.drawString(stations[i], x - 10, 95);

                    x += 75;
                }

                // Current station highlight
                g2.setColor(Color.WHITE);
                g2.setStroke(new BasicStroke(3));
                g2.drawOval(340, 44, 30, 30);
            }
        };

        routePanel.setBounds(30, 520, 940, 120);
        routePanel.setOpaque(false);
        right.add(routePanel);

        // Bottom box
        JPanel remain = glassPanel();
        remain.setBounds(35, 650, 940, 55);
        remain.setLayout(null);

        JLabel rem = new JLabel("📍 Remaining 126 km in 02:50 hours");
        rem.setForeground(new Color(120, 255, 120));
        rem.setFont(new Font("Segoe UI", Font.BOLD, 28));
        rem.setBounds(220, 10, 550, 35);
        remain.add(rem);

        right.add(remain);

        setVisible(true);
    }

    // Premium transparent panel
    JPanel glassPanel() {
        JPanel p = new JPanel() {
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(new Color(0, 40, 0, 120));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);

                g2.setColor(new Color(80, 255, 150, 80));
                g2.drawRoundRect(0, 0, getWidth() - 1,
                        getHeight() - 1, 30, 30);
            }
        };

        p.setOpaque(false);
        return p;
    }

    public static void main(String[] args) {
        new PremiumTrainTracker();
    }
}