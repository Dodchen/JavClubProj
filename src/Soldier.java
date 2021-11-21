import javax.swing.*;
import java.awt.*;
/**
 * A class that extends {@link Person},this class represent the soldier.
 * it holds the same information as the person class and adds one more parameter for soldiers.
 */
public class Soldier extends Person {
    private static final long serialVersionUID = 1L;
    private String personalNum;
    private JTextField pnField;
    private JLabel PersonalNo, star;
    private final String PersonalNRegex = "[ROC][/][1-9][0-9]{6}";

    /**
     * main constructor, initialises all the variables and sets title and size of the panel
     * it create components and usess addToCenter function from the ClubAbstractEntity class.
     *
     * @param id      id of the person
     * @param name    name of the person
     * @param surname surname of the person
     * @param tel     phone number of the person
     */
    public Soldier(String id, String name, String surname, String tel, String personalNum) {
        super(id, name, surname, tel);
        star = new JLabel("*");
        star.setForeground(Color.red);
        star.setVisible(false);
        this.personalNum = personalNum;
        pnField = new JTextField(fieldSize);
        PersonalNo = new JLabel("Personal No.", JLabel.RIGHT);
        setSize(450, 250);
        setTitle("Soldier Clubber's Data");
        addToCenter(PersonalNo);
        addToCenter(pnField);
        addToCenter(star);

    }
    /**
     * this is an empty constructor, calls other constructor with empty fields.
     * made for creation purposes. 
     */
    public Soldier(){
        this("","","","","");

    }

    /**
     * @param key this is a string which the method get to find out if it matches
     * the method is taken from {@link Person#match(String)} and it uses {@link #equals}
     * @return true if matches false otherwise
     */
    public boolean match(String key) {
        return super.match(key) || personalNum.equals(key);
    }

    /**
     * this method checks if the given data in the textboxes matches the required standards.
     * it uses the validateData method from person class
     * this method is taken from {@link Person#validateData()}
     * @return true if all testboxes are valid, false otherwise
     */
    protected boolean validateData() {
        if (!super.validateData()) {
            star.setVisible(false);
            return false;
        }
        if (!pnField.getText().matches(PersonalNRegex)) {
            star.setVisible(true);
            return false;
        }
        star.setVisible(false);
        return true;
    }

    /**
     * commit method saves the text from the textfields to the inner variables using  the commit method from person class
     * this method is taken from{@link Person#commit()}
     */
    protected void commit() {
        personalNum = pnField.getText();
        super.commit();
    }

    /**
     * this method take the inner variables and puts them into the text fields using the rollback method from person class
     */
    protected void rollBack() {
        pnField.setText(personalNum);
        super.rollBack();
    }
}