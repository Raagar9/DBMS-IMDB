/*
 * Created by JFormDesigner on Tue Oct 31 10:24:59 IST 2023
 */

package Project.GUI;

import Project.DatabaseConnection;
import Project.MainPage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

/**
 * @author lenovo
 */
public class AnimePage extends JFrame  {
    private DatabaseConnection dbConnection;

    private JFrame this2;
    private JButton HomePageButton, BackPageButton, AnimeButton1,AnimeButton2,AnimeButton3,AnimeButton4,AnimeButton5,NextPageButton;
    private ButtonGroup buttonGroup;
    private JLabel label7;
    private JButton[] animeButtons;
    private int DisplayPageCount = 5;

    private int pagecount = 0 ;
    public AnimePage(int intialPage){
        initComponents(getEntries(), intialPage);
        setVisible(true);
        setSize(500, 420);
        // to close only the frame that is opened seprately like chrome
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Handle the close operation for this frame
                dispose(); // Close the current frame
            }
        });
    }
    private void initComponents(List<String> entries ,  int intialPage ) {
        pagecount = intialPage;
        buttonGroup = new ButtonGroup();
        this2 = new JFrame();
        AnimeButton1 = new JButton();
        AnimeButton2 = new JButton();
        AnimeButton3 = new JButton();
        AnimeButton4 = new JButton();
        AnimeButton5 = new JButton();
        NextPageButton = new JButton();
        HomePageButton = new JButton();
        BackPageButton = new JButton();
        label7 = new JLabel();

        // The Labeling of the Movies to the Movie Page.
        animeButtons = new JButton[]{AnimeButton1, AnimeButton2, AnimeButton3, AnimeButton4, AnimeButton5};
        int buttonCounter = 0;
        for (int i = intialPage; i < entries.size() && buttonCounter < DisplayPageCount; i++) {
            animeButtons[buttonCounter].setText(entries.get(i));
            buttonCounter++;
            pagecount++;
        }
        System.out.println(pagecount);

        // Adding the buttons to the buttonGroup
        buttonGroup.add(AnimeButton1);
        buttonGroup.add(AnimeButton2);
        buttonGroup.add(AnimeButton3);
        buttonGroup.add(AnimeButton4);
        buttonGroup.add(AnimeButton5);
        ActionListener commonActionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton clickedButton = (JButton) e.getSource();
                // Handle the button click here
                String AnimeTitle = clickedButton.getText();
                AnimeUserRatingProgressPage object = new AnimeUserRatingProgressPage(AnimeTitle);
            }
        };

        //======== this2 ========
        {

            this2.setIconImage(new ImageIcon("E:\\photo\\2pac.jpg").getImage());
            this2.setTitle("Movie Page");
            var contentPane = getContentPane();
            contentPane.setLayout(null);

            //---- AnimeButton1 ----

            AnimeButton1.setBackground(new Color(0x00ccdb));
            contentPane.add(AnimeButton1);
            AnimeButton1.setBounds(20, 20, 180, 35);
            AnimeButton1.addActionListener(commonActionListener);
            //---- AnimeButton2 ----

            AnimeButton2.setBackground(new Color(0x00ccdb));
            contentPane.add(AnimeButton2);
            AnimeButton2.setBounds(20, 75, 180, 35);
            AnimeButton2.addActionListener(commonActionListener);

            //---- AnimeButton3 ----

            AnimeButton3.setBackground(new Color(0x00ccdb));
            contentPane.add(AnimeButton3);
            AnimeButton3.setBounds(20, 125, 180, 35);
            AnimeButton3.addActionListener(commonActionListener);

            //---- AnimeButton4 ----

            AnimeButton4.setBackground(new Color(0x00ccdb));
            contentPane.add(AnimeButton4);
            AnimeButton4.setBounds(20, 175, 180, 35);
            AnimeButton4.addActionListener(commonActionListener);

            //---- AnimeButton5 ----

            AnimeButton5.setBackground(new Color(0x00ccdb));
            contentPane.add(AnimeButton5);
            AnimeButton5.setBounds(20, 225, 180, 35);
            AnimeButton5.addActionListener(commonActionListener);

            //---- NextPageButton ----
            NextPageButton.setText("NEXT");
            NextPageButton.setBackground(new Color(0x00ccdb));
            contentPane.add(NextPageButton);
            NextPageButton.setBounds(350, 305, 110, 35);
            NextPageButton.addActionListener(e ->{

                if (pagecount < entries.size()) {
                    AnimePage object = new AnimePage(pagecount);
                    dispose();
                } else {
                    // All movies have been displayed
                    NextPageButton.setText("Finished");
                    System.out.println("All movies displayed");
                }
            });

            //---- HomePageButton ----
            HomePageButton.setText("Home ");
            HomePageButton.setBackground(new Color(0x00ccdb));
            HomePageButton.setIcon(null);
            contentPane.add(HomePageButton);
            HomePageButton.setBounds(175, 305, 110, 35);
            HomePageButton.addActionListener(e -> {
                MainPage object = new MainPage();
            });

            //---- BackPageButton ----
            BackPageButton.setText("BACK");
            BackPageButton.setBackground(new Color(0x00ccdb));
            contentPane.add(BackPageButton);
            BackPageButton.setBounds(35, 305, 95, 35);
            BackPageButton.addActionListener(e -> {
                if(pagecount <= 5){
                    BackPageButton.setText("Page 1..");
                    pagecount = 5;
                }else{
                    pagecount -= DisplayPageCount;
                    AnimePage object = new AnimePage(pagecount - 5);
                    dispose();
                }
            });

            //---- label7 ----
            label7.setText("text");
            label7.setIcon(new ImageIcon("E:\\photo\\4k-captain-america-1g.jpg"));
            contentPane.add(label7);
            label7.setBounds(0, 0, 500, 420);

            {
                // compute preferred size
                Dimension preferredSize = new Dimension();
                for(int i = 0; i < contentPane.getComponentCount(); i++) {
                    Rectangle bounds = contentPane.getComponent(i).getBounds();
                    preferredSize.width = Math.max(bounds.x + bounds.width, preferredSize.width);
                    preferredSize.height = Math.max(bounds.y + bounds.height, preferredSize.height);
                }
                Insets insets = contentPane.getInsets();
                preferredSize.width += insets.right;
                preferredSize.height += insets.bottom;
                contentPane.setMinimumSize(preferredSize);
                contentPane.setPreferredSize(preferredSize);
            }
            this2.pack();
            this2.setLocationRelativeTo(this2.getOwner());
        }
    }


    private java.util.List<String> getEntries() {
        List<String> entries = new ArrayList<>();
        openDatabaseConnection();
        try {
            Connection connection = dbConnection.getConnection();
            String query = "SELECT Title FROM anime";

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    String entry = resultSet.getString("Title");
                    entries.add(entry);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception here, e.g., show an error message to the user
        } finally {
            closeDatabaseConnection();
        }

        return entries;
    }

    private void openDatabaseConnection() {
        dbConnection = new DatabaseConnection();
    }

    private void closeDatabaseConnection() {
        if (dbConnection != null) {
            dbConnection.closeConnection();
        }
    }

    public static void main(String[] args){
        AnimePage object = new AnimePage(0);
    }

}
