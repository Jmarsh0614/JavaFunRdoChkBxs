/*
    This Program will go over the use of RadioButton and CheckBoxes 
    as well as responding to events of theirs
*/



/*


    RADIO BUTTON CONSTRUCTORS
    
    EXAMPLE 
            JRadioButton(String text, boolean selected?)
            JRadioButton(String text)           
*/

/*
        Once you have created JRadioButton objects you can group them so when one 
        is selected the other are deselected and do not act independently
        You must create an instance of the ButtonGroup class and then add the JRadioButton
        objects to it

        EXAMPLE 
        ButtonGroup group = new ButtonGroup();
        // Add the radio buttons to the ButtonGroup object
        EXAMPLE
        group.add(radio1);

        //ButtonGroupObjects are not containers like JPanel objects or content frames
        //If you wish to the add the radio buttons to the panel or content frame 
        // you must do so individually
        
        // ButtonGroup objects respond to clicks just like JButton objects
*/


/*

    A component can appear with several different styles of borders
around it. A Border object specifies the details of a border. You use the BorderFactory
class to create Border objects

** Sometimes it is helpful to places a boarder around a component or group 
** of components on a panel. Gives a more organized look

** JPanel components havea  method named setBorder which is used to add a border 
** to the panel

** The setBorder() method accepts a Border obj as its argument, the Border obj
** contains detail information describing the appearance of a border.

** Rather than creating Border objs yourself, you should use BorderFactory class
** to create them for you. 

** The BorderFactory class has methods that return various types of borders

    example 
    panel.setBorder(BorderFactory.createEmptyBoarder(int top, int left, int btm, int right));
    * Empty spaced border around the edges of a component

    example
        panel.setBorder(BorderFactory.createLineBorder(Color color, int thickness));
    
        *A line border is a line of a specified color that thickness that appears
        * around the edges of a component

    example
        panel.setBorder(BorderFactory.createTitledBorder(String title));

        * A titled border is an etched border with a title displayed on it
        

*/


package javafunrdochkbxs;

/**
 *
 * @author Joshua
 */
//Import 
import javax.swing.*;   // Needed for Swing classes
import java.awt.*;      // Needed for Action Listener
import java.awt.event.*; // Needed for event handler

public class JavaFunRdoChkBxs extends JFrame
{

    /**
     * This class will let the user enter a distance in km. Radio buttons can be
     * selected to convert the kilometers to miles, feet , or inches
     */
    //Variables
    private JCheckBox chkBox1;

    private JPanel panel;                   // A holding panel
    private JLabel messageLabel;            // A message to the user
    private JTextField kiloTextField;       // To hold user input
    private JRadioButton rdoMiles;          // To convert to miles
    private JRadioButton rdoFeet;           // To convert to feet
    private JRadioButton rdoInches;         // To convert to inches
    private ButtonGroup grp1;               // To group radio buttons
    private final int WINDOW_WIDTH = 650;   // Window width
    private final int WINDOW_HEIGHT = 100;  // Window height

    /**
     * Constructor
     *
     */
    public JavaFunRdoChkBxs()
    {
        //Set the title 
        setTitle("Metric Converter");

        //Set window size
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        // Set what happens when user click x 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set Layout
        setLayout(new FlowLayout(FlowLayout.CENTER));

        //Build the panel and add it to the frame
        buildPanel();

        // Add the panel to the frame's content pane
        add(panel);

        //Display the window
        setVisible(true);

    }

    /**
     * The buildPanel method adds a label, txtField, and 3 buttons to a panel
     */
    public void buildPanel()
    {

        //Create the label, text field, and buttons
        chkBox1 = new JCheckBox("Test", true);

        messageLabel = new JLabel("Enter a distance in kilometers");
        kiloTextField = new JTextField(10);
        kiloTextField.setBackground(Color.BLACK);// Set Txtfld background to black
        kiloTextField.setForeground(Color.WHITE);// Set characters used in txtfld to white

        rdoMiles = new JRadioButton("Miles");// If you want the radio button to be
        rdoFeet = new JRadioButton("Feet");// selected when @ program start
        rdoInches = new JRadioButton("Inches");// add , (string, true) to parameters

        //Group the radio buttons 
        grp1 = new ButtonGroup();
        grp1.add(rdoMiles);
        grp1.add(rdoFeet);
        grp1.add(rdoInches);

        // Add action listeners to the radio buttons
        kiloTextField.addActionListener(new RadioButtonListener());
        rdoMiles.addActionListener(new RadioButtonListener());
        rdoFeet.addActionListener(new RadioButtonListener());
        rdoInches.addActionListener(new RadioButtonListener());
        chkBox1.addItemListener(new ChkBxListener());

        // Create a panel and add the components to it
        panel = new JPanel();
        panel.add(messageLabel);
        panel.add(kiloTextField);
        panel.add(rdoMiles);
        panel.add(rdoFeet);
        panel.add(rdoInches);
        panel.add(chkBox1);
    }

    /**
     * Private inner class that handles the event when the user clicks one of
     * the radio buttons
     */
    private class RadioButtonListener implements ActionListener
    {//BEGIN RADIO BUTTON LISTENER CLASS

        public void actionPerformed(ActionEvent e)
        {

            // Declare Variables
            String input;           // To hold the user's input
            String convertTo = "";   //The units were converting to
            double dblResult = 0.0; // To hold the conversion

            //Get the Kilometers entered 
            input = kiloTextField.getText();

            try // Try Block to verify that the user has entered something
            // and verify that it is a number
            {
                dblResult = Double.parseDouble(kiloTextField.getText());

                //Determine which radio button was clicked
                if (e.getSource() == rdoMiles)
                {

                    //Convert to miles
                    convertTo = " miles.";
                    dblResult = Double.parseDouble(input) * 0.6214;
                } else if (e.getSource() == rdoFeet)
                {

                    //Convert to feet
                    convertTo = " feet.";
                    dblResult = Double.parseDouble(input) * 3281.0;
                } else if (e.getSource() == rdoInches)
                {

                    //Convert to inches
                    convertTo = " inches.";
                    dblResult = Double.parseDouble(input) * 39370.0;

                } // End IF ELSE 

                // Display the conversion
                JOptionPane.showMessageDialog(null, input + " kilometers is " + dblResult + convertTo);

            } catch (Exception ex)
            {
                JOptionPane.showMessageDialog(null, "Please enter in a number for kilometers");
                grp1.clearSelection();// Clear the buttons 
            }// End Try Catch
        }

    }// END RADIOBUTTONLISTENER CLASS

    private class ChkBxListener implements ItemListener
    // When Using check boxes you use ItemListener interface
    {// Begin ItemListener Class

        @Override
        public void itemStateChanged(ItemEvent ie)
        {

            if (chkBox1.isSelected())
            {
                // JOptionPane.showMessageDialog(null, "Box Selected");
            }

        }

    }// End ItemListener Class

    
    
    
    
    /* 
    
    
            **    MAIN METHOD
    
    
    
    
    */
    public static void main(String[] args)
    {//Begin Main Method

        // Declare Instance of Class
        JavaFunRdoChkBxs newGui = new JavaFunRdoChkBxs();
    }// End Main Method

}// End JavaFunRdoChkBxs Class
