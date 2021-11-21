// File: NightClubMgmtApp.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

/**
 * this class manages all the objects and handles gui elements for the user.
 * it contains the main function and holds the array of the database.
 * it can also save and load database into an external file.
 */
public class NightClubMgmtApp {
    // Night-Club Regular Customers Repository
    private ArrayList<ClubAbstractEntity> clubbers;
    private JFrame mainPanel;
    private JButton serachButton, createButton, exitButton;
    private ButtonHandler handler;
    private JPanel panelTop, panelBot, panelCenter;
    private JComboBox createOption;
    private static final String[] options = {"Person", "Student", "Soldier"};
    private final String fileName = "BKCustomers.dat";

    /**
     * main constructor, it initializes all the varieables,lables,and buttons.
     * it also holds the main frame and shows the gui for the user.
     */
    public NightClubMgmtApp() {
        createOption = new JComboBox<>(options);
        createButton = new JButton("Create");
        exitButton = new JButton("Exit");
        panelTop = new JPanel();
        panelBot = new JPanel();
        panelCenter = new JPanel();
        mainPanel = new JFrame();
        mainPanel.setLocationRelativeTo(null);
        mainPanel.setDefaultCloseOperation(0);
        serachButton = new JButton("Search");
        handler = new ButtonHandler();
        mainPanel.setTitle("NightClub Management App");
        mainPanel.add(panelTop, BorderLayout.NORTH);
        mainPanel.add(panelBot, BorderLayout.SOUTH);
        mainPanel.add(panelCenter, BorderLayout.CENTER);
        serachButton.addActionListener(handler);
        createButton.addActionListener(handler);
        exitButton.addActionListener(handler);
        panelTop.add(serachButton);
        mainPanel.setVisible(true);
        mainPanel.setSize(400, 200);
        clubbers = new ArrayList<>();
        panelCenter.add(createButton);
        panelCenter.add(createOption);
        panelBot.add(exitButton);
        mainPanel.setResizable(false);
        loadClubbersDBFromFile();
        dataCheck();
    }

    /**
     * This method checks our data with each run of this program, it goes through the database checking if the values are valid.
     * this will delete objects that were saved by an unusual mistake or crash.
     */
    private void dataCheck(){
        for(int i=0; i< clubbers.size(); i++){
            if(! clubbers.get(i).validateData())
                clubbers.remove(i);
        }
    }

    /**
     * this method is in charge of searching for the club memmber by his key(id,studentId,or personalNum)
     * which is done by the help of match functoin by each of the classes in our database
     */
    private void manipulateDB() {
        String input = JOptionPane.showInputDialog("Enter the key to search:");
        if (input == null)
            return;
        else if ("".equals(input))
            JOptionPane.showMessageDialog(mainPanel, "Didnt insert key!", "No Key", JOptionPane.INFORMATION_MESSAGE);
        else {
            boolean found = false;
            if (input.trim().equalsIgnoreCase("exit")) {
                writeClubbersDBtoFile();
                System.exit(0);
            }
            for (ClubAbstractEntity clubber : clubbers)
                if (clubber.match(input)) {
                    found = true;
                    clubber.rollBack();
                    clubber.setVisible(true);
                    break;
                }
            if (!found)
                JOptionPane.showMessageDialog(mainPanel, "Clubber with key " + input + " does not exist", "Not Found!",
                        JOptionPane.INFORMATION_MESSAGE);
            else
                found = !found;
        }
    }// End of method manipulateDB

    /**
     * this method is in charge if loading the database from the external file using path fileName var.
     * and saves the database into the clubbers array.
     */
    private void loadClubbersDBFromFile() {
        try{
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            clubbers = (ArrayList<ClubAbstractEntity>) ois.readObject();
            fis.close();
            ois.close();
        } catch(Exception e) {
            JOptionPane.showMessageDialog(mainPanel, "Error Loading!", "Load Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * this method is in charge of saving the database from clubbers array into the path.
     */
    private void writeClubbersDBtoFile() {
        try {
            FileOutputStream fis = new FileOutputStream(fileName);
            ObjectOutputStream ois = new ObjectOutputStream(fis);
            ois.writeObject(clubbers);
            ois.close();
            fis.close();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(mainPanel, "Error Saving File.", "Error Saving", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * The type Button handler. it implements the {@link java.awt.event.ActionListener} and calls the function
     * accordingly.
     */
    class ButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getActionCommand() == serachButton.getText()) {
                manipulateDB();
            } else if (event.getActionCommand() == createButton.getText()) {
                createEntity();
            }  else if (event.getActionCommand() == exitButton.getText()) {
                writeClubbersDBtoFile();
                System.exit(0);
            }
        }
    }

    /**
     * this function is responsible for creating new objects depending on the user selection.
     * and saves them in the clubbers array (note that this method does not save into external file.)
     */
    private void createEntity() {
        switch (createOption.getSelectedItem().toString()) {
            case "Person":
                clubbers.add(new Person());
                break;
            case "Student":
                clubbers.add(new Student());
                break;
            case "Soldier":
                clubbers.add(new Soldier());
                break;
        }
        clubbers.get(clubbers.size() - 1).setVisible(true);
        clubbers.get(clubbers.size() - 1).disableCancelBtn();
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        NightClubMgmtApp appliction = new NightClubMgmtApp();

    }
}//End of class NightClubMgmtApp