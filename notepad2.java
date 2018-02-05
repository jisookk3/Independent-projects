/*
 * Description : Notepad GUI. Menu in Korean. Implemented a simple menu configuration
 */


package notepad;


import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.soap.Text;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.InputEvent;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;

 

public class notepad2 extends JFrame {
    private JPanel contentPane;
    private JScrollPane scrollPane;
    private JTextArea textArea;
    private JMenuBar menuBar;
    private JMenu mnNewMenu;
    private JMenu mnNewMenu_1;
    private JMenu menu;
    private JMenu mnNewMenu_2;
    private JMenu mnNewMenu_3;
    private JMenuItem mntmNew;
    private JMenuItem mntmOpen;
    private JMenuItem mntmSave;
    private JSeparator separator;
    private JMenuItem mntmSaveAs;
    private JMenuItem mntmPageSetting;
    private JMenuItem mntmEnd;
    private JMenuItem menuItem_2;
    private JMenuItem mntmPrint;
    private JSeparator separator_1;
    private JLabel Status;
    boolean count;
    boolean open_count;  // check if typing
    String absolute_filename;
    
    /*
     * Launch the application.
     */

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                 try {
                    notepad2 frame = new notepad2();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /*
     * Create the frame for the notepad
     */
    public notepad2() {
        count = false;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        /////// File Menu
        mnNewMenu = new JMenu("\uD30C\uC77C"); // 파일
        menuBar.add(mnNewMenu);

        /////// File - New
        mntmNew = new JMenuItem("\uC0C8\uB85C\uB9CC\uB4E4\uAE30"); 
        mntmNew.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_mntmNew_actionPerformed(e);
            }
        });
        
        // Sets the key combination which invokes 
    		// the menu item's action listeners without navigating the menu hierarchy.
        // key stroke(ctrl+N): new note
        mntmNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
        mnNewMenu.add(mntmNew); // add menuItem to menu
        
        /////// File - Open / FileChooser.openDialogTitleText
        mntmOpen = new JMenuItem("\uC5F4\uAE30");
        mntmOpen.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_mntmOpen_actionPerformed(e);
            }
        });
        // CTRL+O: open
        mntmOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        mnNewMenu.add(mntmOpen);

        ///// File - Save / FileChooser.saveButtonText
        mntmSave = new JMenuItem("\uC800\uC7A5");
        mntmSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                do_mntmSave_actionPerformed(arg0);
            }
        });
        // CTRL+S: save
        mntmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        mnNewMenu.add(mntmSave);

        ////// File - Save As
        mntmSaveAs = new JMenuItem("\uB2E4\uB978\uC774\uB984\uC73C\uB85C \uC800\uC7A5");
        mntmSaveAs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_mntmSaveAs_actionPerformed(e);
            }
        });

        mnNewMenu.add(mntmSaveAs);
        separator = new JSeparator(); // separate line adding
        mnNewMenu.add(separator);

        ////// File - Page Setup
        mntmPageSetting = new JMenuItem("\uD398\uC774\uC9C0\uC124\uC815");
        mnNewMenu.add(mntmPageSetting);

        ////// File - Print
        mntmPrint = new JMenuItem("\uC778\uC1C4");
        mntmPrint.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_MASK));
        mnNewMenu.add(mntmPrint);

        separator_1 = new JSeparator();
        mnNewMenu.add(separator_1);
        
        ////// File - Exit
        mntmEnd = new JMenuItem("\uB05D\uB0B4\uAE30");
        mntmEnd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                do_mntmEnd_actionPerformed(e);
            }
        });

        mnNewMenu.add(mntmEnd);

        ////// Edit
        mnNewMenu_1 = new JMenu("\uD3B8\uC9D1");
        menuBar.add(mnNewMenu_1);
        
        // Edit - Undo
        menuItem_2 = new JMenuItem("\uC2E4\uD589\uCDE8\uC18C");
        mnNewMenu_1.add(menuItem_2);

        /////// Format
        menu = new JMenu("\uC11C\uC2DD");
        menuBar.add(menu);

        ////// View
        mnNewMenu_2 = new JMenu("\uBCF4\uAE30");
        menuBar.add(mnNewMenu_2);
        
        ////// Help
        mnNewMenu_3 = new JMenu("\uB3C4\uC6C0\uB9D0");
        menuBar.add(mnNewMenu_3);
        
        ////// create an object of JPanel
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        
        // add scroll pane in JPanel content
        scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        // textArea adding action
        textArea = new JTextArea();
        textArea.addKeyListener(new KeyAdapter() {

            @Override  
            public void keyTyped(KeyEvent arg0) {
                do_textArea_keyTyped(arg0);
            }
        });
        
        // Creates a viewport if necessary and then sets its view. 
        scrollPane.setViewportView(textArea);

        // adding JLable at the bottom
        Status = new JLabel("New label");
        contentPane.add(Status, BorderLayout.SOUTH);
    }

 

    protected void do_mntmOpen_actionPerformed(ActionEvent e) {
        String tmp = textArea.getText();
        
        if(do_mntmNew_actionPerformed(null))
        {
            return;
        }
        
        textArea.setText(tmp);
        JFileChooser chooser = new JFileChooser();
        // Pops up an "Open File" file chooser dialog
        // after opening the file, get the result.
        int result = chooser.showOpenDialog(this);
        
        if (result == JFileChooser.APPROVE_OPTION)
        {// when jfileChooser is Yes, get the file path
            String fileName = chooser.getSelectedFile().getAbsolutePath(); 
            try {
                File file = new File(fileName);
                FileReader fileReader = new FileReader(file);

                int fileSize = (int) file.length();
                char[] data = new char[fileSize];
                
                //Reads characters into an array
                fileReader.read(data); // read data

                String contents = new String(data); // save data to contents
                textArea.setText(contents); // showing contents
                absolute_filename = fileName;
                open_count = true;
                fileReader.close();
            } catch (FileNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }


    protected boolean do_mntmNew_actionPerformed(ActionEvent e) 
    {
        if(count)
        {
            int option = JOptionPane.showConfirmDialog(this, "저장하시겠습니까?","저장", JOptionPane.YES_NO_CANCEL_OPTION);
            String text = textArea.getText();
           
            // if want to save, YES
            if(option == JOptionPane.YES_OPTION)
            {
                // ok to leave null
                //do_mntmSave_actionPerformed(null);
//              System.out.println(open_count + "");

            	if(opensave())
                {
                    textArea.setText("");
                    open_count = false;
                    return false;
                }
                

                JFileChooser chooser = new JFileChooser();
                int result = chooser.showSaveDialog(this);
                
                if (result == JFileChooser.APPROVE_OPTION) {
                    String fileName = chooser.getSelectedFile().getAbsolutePath(); 

                    File file = new File(fileName);
                    FileWriter filewriter;
                    String contents = textArea.getText();

                    try {
                        filewriter = new FileWriter(file);
                        filewriter.write(contents);
                        filewriter.close();
                        textArea.setText("");
                        open_count = false;
                        count = false;

                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    } 

                }             
            }
            
            // if NO option,
            else if(option == JOptionPane.NO_OPTION)
            {
                textArea.setText("");
                count = false;
                open_count = false;
                return false;
            }
            
            // if CANCEL
            else
            {
                return true;
            }
            //dialog dia = new dialog(text);
            //dia.setVisible(true);
        }

        // if count == false
        else
        {
            textArea.setText("");
            count =false;
        }
        
        return count;   
    }
    
    
    protected void do_mntmSave_actionPerformed(ActionEvent arg0) 
    {
        if(count)
        {
            if(opensave())
                {
                    return;
                }
            JFileChooser chooser = new JFileChooser();
            int result = chooser.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION)
            {// jfileChooser의 확인일때만
                String fileName = chooser.getSelectedFile().getAbsolutePath(); 
                File file = new File(fileName);
                FileWriter filewriter;
                String contents = textArea.getText();

                try {
                    filewriter = new FileWriter(file);
                    filewriter.write(contents);
                    filewriter.close();
                    count = false;
                    absolute_filename = fileName;
                    open_count = true;
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
             }
        }
    }
    public boolean opensave()
    {
        boolean shut = false;
        System.out.println(open_count + "");
        if(open_count)
        {
            System.out.println(absolute_filename);
            String contents = textArea.getText();
            File file = new File(absolute_filename);
            FileWriter filewriter ;

            try {
                filewriter = new FileWriter(file);
                filewriter.write(contents);
                filewriter.close();
                count = false;
                shut = true;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }          
        }
        return shut;
    }
    
    // check if typing; count = true
    protected void do_textArea_keyTyped(KeyEvent arg0) 
    {
        count = true;
    }
    
    protected void do_mntmSaveAs_actionPerformed(ActionEvent e) 
    {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION)
        {
            String fileName = chooser.getSelectedFile().getAbsolutePath(); 
            File file = new File(fileName);
            FileWriter filewriter;
            String contents = textArea.getText();

            try {
                filewriter = new FileWriter(file);
                filewriter.write(contents);
                filewriter.close();
                count = false;
                absolute_filename = fileName;
                open_count = true;
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }

    protected void do_mntmEnd_actionPerformed(ActionEvent e) {
        this.dispose();  
    }
}


