import javax.swing.*;
import java.awt.*;
/**
 * A class that extends {@link Person},this class represent the student.
 * it holds the same information as the person class and adds one more parameter for students.
 */
public class Student extends Person {
    private static final long serialVersionUID = 1L;
    private String studentID;
    private JTextField stIdField;
    private JLabel stIdlbl,star;
    private final String StudentIDRegex = "[A-Z]{3}[/][1-9][0-9]{4}";

    /**
     * main contructer , initialises all the variables and sets title and size of the panel.
     * it create components and usess addToCenter function from the ClubAbstractEntity class.
     * it uses  {@link #setForeground} and {@link #setVisible}
     *
     * @param id        id of the student
     * @param name      name of the student
     * @param surname   surname of the student
     * @param tel       phone number of the student
     * @param studentID student id of college
     */
    public Student(String id, String name, String surname, String tel, String studentID) {
        super(id, name, surname, tel);
        star = new JLabel("*");
        star.setForeground(Color.red);
        star.setVisible(false);
        this.studentID = studentID;
        stIdField = new JTextField(fieldSize);
        stIdlbl = new JLabel("Student ID",JLabel.RIGHT);
        setSize(450, 250);
        setTitle("Student Clubber's Data");
        addToCenter(stIdlbl);
        addToCenter(stIdField);
        addToCenter(star);
    }
    
    /**
     * this is an empty constructor, calls other constructor with empty fields.
     * made for creation purposes. 
     */
    public Student(){
        this("","","","","");

    }

    /**
     * this method checks if the given string is equal to the id
     * it uses the match method from person class
     * the method is taken from {@link Person#match(String)}, also uses {@link #equals}
     * @param key this is a string which the method get to find out if it matches
     * @return returns true if matches false otherwise
     */
    public boolean match(String key) {
        return super.match(key) || studentID.substring(4).equals(key);
    }

    /**
     * this method checks if the given data in the textboxes matches the required standards.
     *  it uses the validateData method from person class
     *  this method is taken from {@link Person#validateData()}
     * @return true if all testboxes are valid, false otherwise
     */
    protected boolean validateData() {
        if (!super.validateData()){
            star.setVisible(false);
            return false;
        }
        if (!stIdField.getText().matches(StudentIDRegex)) {
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
    protected void commit(){
        studentID = stIdField.getText();
        super.commit();
    }

    /**
     * this method take the inner variables and puts them into the text fields using the rollback method from person class
     */
    protected void rollBack(){
        stIdField.setText(studentID);
        super.rollBack();
    }
}