import javax.swing.*;
import java.awt.*;
/**
 * this class extends {@link ClubAbstractEntity} and creates all the textfields and button of the frame.
 * it holds all the person's information.
 */
public class Person extends ClubAbstractEntity {
    private static final long serialVersionUID = 1L;
    private String id, name, surname, tel;
    private JTextField idField, nameField, surnameField, telField;
    public final int fieldSize=30;
    private JLabel idLabel,nameLabel,surLabel,telLabel,stars[];
    private final String IDRegex = "\\d[-]\\d{7}[|][1-9]",NameRegex = "[A-Z][a-z]+",SurnameRegex = "([A-Z][a-z]*[ '-]?[a-zA-Z]*)+",TelRegex ="[+][(][1-9][0-9]{0,2}[)][1-9][0-9]{0,2}[-][1-9][0-9]{6}";


    /**
     * main constructor, initialises all the variables and sets title and size of the panel,
     * it create components and usess addToCenter function from the ClubAbstractEntity class.
     *
     * @param id      id of the person
     * @param name    name of the person
     * @param surname surname of the person
     * @param tel     phone number of the person
     */
    public Person(String id, String name, String surname, String tel) {
        stars = new JLabel[4];
        initStars(stars);
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.tel = tel;
        idField = new JTextField(fieldSize);
        nameField = new JTextField(fieldSize);
        surnameField = new JTextField(fieldSize);
        telField = new JTextField(fieldSize);
        setSize(450, 220);
        setTitle("Person Clubber's Data");
        idLabel = new JLabel("ID",JLabel.RIGHT);
        nameLabel = new JLabel("Name",JLabel.RIGHT);
        surLabel = new JLabel("Surname",JLabel.RIGHT);
        telLabel = new JLabel("Tel",JLabel.RIGHT);
        guiAdder(new Component[] {idLabel,idField,nameLabel,nameField,surLabel,surnameField,telLabel,telField});
    }

    /**
     * this is an empty constructor, calls other constructor with empty fields.
     * made for creation purposes. 
     */
    public Person(){
        this("","","","");

    }
    
    /**
     * Helper function to add all the fields and labels  to the corresponding panels via addToCenter function
     * @param c an array of Components to add to the panel
     */
    private void guiAdder(Component c[]) {
        for(int i=0;i<c.length;i++){
            addToCenter(c[i]);
        } 
    }

    /**
     * help function to initialize all the "*" labels
     * @param stars an array of star labels
     */
     private void initStars (JLabel stars[]){
        for (int i=0; i<stars.length;i++){
            stars[i] = new JLabel("*");
            stars[i].setForeground(Color.red);
            stars[i].setVisible(false);
            addToCenter(stars[i]);

        }
     }
    /**
     * this method checks if the given string is equal to the id
     * the method is taken from {@link ClubAbstractEntity#match(String)}
     * @param key this is a string which the method get to find out if it matches
     * @return returns true if matches false otherwise
     */
    public boolean match(String key) {
        if (id.equals(key))
            return true;
        else
            return false;
    }

    /**
     * this method checks if the given data in the checkboxes matches the required standards.
     * this method is taken from {@link ClubAbstractEntity#validateData()}
     * @return true if all checkboxes are valid, false otherwise
     */
    protected boolean validateData() {
        if (!(idField.getText().matches(IDRegex))) {
            stars[0].setVisible(true);
            return false;
        }
        stars[0].setVisible(false);
        if (!nameField.getText().matches(NameRegex)) {
            stars[1].setVisible(true);
            return false;
        }
        stars[1].setVisible(false);
        if (!surnameField.getText().matches(SurnameRegex)) {
            stars[2].setVisible(true);
            return false;
        }
        stars[2].setVisible(false);
        if (!telField.getText().matches(TelRegex)) {
            stars[3].setVisible(true);
            return false;
        }
        stars[3].setVisible(false);
        return true;
    }

    /**
     * commit method saves the text from the textfields to the inner variables
     * this method is taken from{@link ClubAbstractEntity#commit()}
     */
    protected void commit() {
        id = idField.getText();
        name = nameField.getText();
        surname = surnameField.getText();
        tel = telField.getText();
    }

    /**
     * this method take the inner variables and puts them into the text fields.
     */
    protected void rollBack() {
        idField.setText(id);
        nameField.setText(name);
        surnameField.setText(surname);
        telField.setText(tel);
    }
}