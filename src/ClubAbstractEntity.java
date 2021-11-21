import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;
/**
 * this class extends {@link javax.swing.JFrame} and implements {@link java.io.Serializable} and it in charge of the gui window and all of it's inheritates,
 * it also in charge of adding the frames.
 */
public abstract class ClubAbstractEntity extends JFrame implements Serializable {
    private static final long serialVersionUID = 1L;
    private JButton okButton, cancelButton;
    private JPanel centerPanel, labelGrid, textGrid, starGrid;
    private ButtonHandler handler;

    /**
     * main constructor of the ClubABstractEntity class, it initialises all the variables of buttons and labels,and the JPanels.
     * it uses {@link #setDefaultCloseOperation} to set closing operation and {@link #setResizable} to disable or enable resize.
     */
    public ClubAbstractEntity() {
        okButton = new JButton("Ok");
        cancelButton = new JButton("Cancel");
        centerPanel = new JPanel();
        handler = new ButtonHandler();
        JPanel buttonPanel = new JPanel();
        okButton.addActionListener(handler);
        cancelButton.addActionListener(handler);
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        add(centerPanel);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setResizable(false);
        this.add(buttonPanel, BorderLayout.SOUTH);
        labelGrid = new JPanel(new GridLayout(5, 1, 0, 18));
        textGrid = new JPanel(new GridLayout(5, 1, 0, 14));
        starGrid = new JPanel(new GridLayout(5, 1, 0, 18));
        centerPanel.add(labelGrid, BorderLayout.CENTER);
        centerPanel.add(textGrid, BorderLayout.EAST);
        centerPanel.add(starGrid, BorderLayout.WEST);
        centerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    }

    /**
     * This method get gui component and adding it to the center of the window by the given layout
     * this method will be called by all the inherited classes
     * @param guiComponent this is varieable of type {@link Component}
     */
    protected void addToCenter(Component guiComponent) {
        //centerPanel.add(guiComponent,BorderLayout.CENTER);
        if (guiComponent instanceof JTextField)
            textGrid.add(guiComponent);
        else if (guiComponent.getForeground() == Color.RED)
            starGrid.add(guiComponent);
        else
            labelGrid.add(guiComponent);

    }

    /**
     * this function is used to disable the 'cancel' button
     * it uses {@link #setEnabled}
     */
    protected void disableCancelBtn() {
        cancelButton.setEnabled(false);
    }

    /**
     * this function is used to enable the 'cancel' button
     * it uses {@link #setEnabled}
     */
    protected void enableCancelBtn() {
        cancelButton.setEnabled(true);
    }

    /**
     * abstratc method wich defines a "match" for each inherited class
     * @param key this is a string which the method get to find out if it matches
     * @return true if it match or false otherwise
     */
    public abstract boolean match(String key);

    /**
     * abstract method which validates the input in each textBox
     *
     * @return true if it valid or false otherwise
     */
    protected abstract boolean validateData();

    /**
     * abtract method that save the info for the current member
     */
    protected abstract void commit();

    /**
     * abtract method which takes all the saved data to its own JtextField
     */
    protected abstract void rollBack();

    /**
     * This is an inner class that impliments {@link java.awt.event.ItemListener} and {@link java.io.Serializable }, it handles both ok and cancel button and calls methods accordingly.
     */
    class ButtonHandler implements ActionListener, Serializable {
        private static final long serialVersionUID = 1L;
        public void actionPerformed(ActionEvent event) {
            if (event.getActionCommand() == okButton.getText() && validateData()) {
                commit();
                enableCancelBtn();
                setVisible(false);

            } else if (event.getActionCommand() == cancelButton.getText()) {
                rollBack();
                setVisible(false);
            }
        }
    }
}