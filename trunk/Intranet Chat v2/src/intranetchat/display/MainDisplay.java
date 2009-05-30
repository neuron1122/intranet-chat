/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MainDisplay.java
 *
 * Created on 23-May-2009, 12:29:31
 */

package intranetchat.display;

import intranetchat.core.NetworkInterface;
import intranetchat.core.NetworkListener;
import intranetchat.core.PrivateChatCollection;
import intranetchat.core.UserCollection;
import intranetchat.core.Users;
import intranetchat.saving.SavedValues;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.text.BadLocationException;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;

/**
 * This class is the main display class for the entire program
 * @author Philip White
 * @version 1.0
 *
 */
public class MainDisplay extends javax.swing.JFrame implements Observer{
    Observable networkObservable;
    Observable saveObservable;
    SavedValues values;
    DefaultListModel list;
    PrivateChatCollection privateChats;
    NetworkInterface network;
    Image icon;
    private StyledDocument cDoc;
    public boolean traySupported;
    private StringBuffer log;
    UserCollection userscol;

    /** Creates new form MainDisplay */
    public MainDisplay(Observable obs, NetworkInterface n) {
        traySupported = true;
        userscol = UserCollection.getInstance();
        list = new DefaultListModel();
        log = new StringBuffer("");
        try {
            icon = javax.imageio.ImageIO.read(new java.net.URL(getClass().getResource("tray_clr.png"), "tray_clr.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        //Sets up the values
        values = SavedValues.getInstance();
        saveObservable = (Observable) values;
        saveObservable.addObserver(this);

        //sets up the networking
        network = n;
        networkObservable = obs;
        networkObservable.addObserver(this);
        try {
            network.sendMulticast("2~" + values.networkName + "~1~");
        } catch (IOException ex) {}

        initComponents();
        cDoc = incomingData.getStyledDocument();
        this.setLocation(values.x, values.y);
        privateChats = PrivateChatCollection.getInstance(obs);

    }




    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        incomingData = new javax.swing.JTextPane();
        outgoingData = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        users = new javax.swing.JList();
        userName = new javax.swing.JTextField();
        send = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        clearScreen = new javax.swing.JMenuItem();
        closeWindows = new javax.swing.JMenuItem();
        exit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        preference = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        About = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("IntraNet Chat");
        setIconImage(icon);
        setName("IntraNet Chat"); // NOI18N
        getContentPane().setLayout(new java.awt.GridBagLayout());

        jPanel2.setLayout(new java.awt.GridBagLayout());

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane1.setAutoscrolls(true);
        jScrollPane1.setMaximumSize(new java.awt.Dimension(500, 350));
        jScrollPane1.setMinimumSize(new java.awt.Dimension(25, 25));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(500, 350));
        jScrollPane1.setViewportView(incomingData);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 3, 0);
        jPanel2.add(jScrollPane1, gridBagConstraints);

        outgoingData.setPreferredSize(new java.awt.Dimension(10, 25));
        outgoingData.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                outgoingDataKeyPressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel2.add(outgoingData, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 3, 3, 3);
        getContentPane().add(jPanel2, gridBagConstraints);

        jPanel3.setLayout(new java.awt.GridBagLayout());

        jScrollPane2.setPreferredSize(new java.awt.Dimension(125, 325));

        users.setModel(list);
        users.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                usersMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(users);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(3, 0, 3, 0);
        jPanel3.add(jScrollPane2, gridBagConstraints);

        userName.setEditable(false);
        userName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                userNameMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel3.add(userName, gridBagConstraints);

        send.setText("Send");
        send.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                sendMousePressed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel3.add(send, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        getContentPane().add(jPanel3, gridBagConstraints);

        jMenu1.setText("File");

        clearScreen.setText("Clear Screen");
        clearScreen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                clearScreenMousePressed(evt);
            }
        });
        jMenu1.add(clearScreen);

        closeWindows.setText("Close Window");
        closeWindows.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                closeWindowsMousePressed(evt);
            }
        });
        jMenu1.add(closeWindows);

        exit.setText("Exit");
        exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                exitMouseReleased(evt);
            }
        });
        jMenu1.add(exit);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Settings");

        preference.setText("Preference");
        preference.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                preferenceMouseReleased(evt);
            }
        });
        jMenu2.add(preference);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Help");

        About.setText("About");
        About.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                AboutMouseReleased(evt);
            }
        });
        jMenu3.add(About);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sendMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sendMousePressed
        // Sends message to the group
        this.sendMessage();
}//GEN-LAST:event_sendMousePressed

    private void clearScreenMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearScreenMousePressed
        incomingData.setText("");
        log.append("*** Screen Cleared *** \n");
}//GEN-LAST:event_clearScreenMousePressed

    private void closeWindowsMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeWindowsMousePressed
        this.dispose();
    }//GEN-LAST:event_closeWindowsMousePressed

    private void preferenceMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_preferenceMouseReleased
        Preference preferences = new Preference(this,true);
    }//GEN-LAST:event_preferenceMouseReleased

    private void AboutMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AboutMouseReleased
        About about = new About(this, true);
    }//GEN-LAST:event_AboutMouseReleased

    private void exitMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseReleased
        this.saving();
        System.exit(0);
    }//GEN-LAST:event_exitMouseReleased

    private void userNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userNameMouseClicked
        if(evt.getClickCount() ==2){
            String s = JOptionPane.showInputDialog(this, "Please Enter your new username", "New Username", JOptionPane.QUESTION_MESSAGE);
            if(s != null){
                values.networkName = s;
                values.ValuesChanged();
                try{
                    network.sendMulticast("2~"+values.networkName+"~2~");
                }catch(IOException e){}
            }
        }
    }//GEN-LAST:event_userNameMouseClicked

    private void usersMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_usersMouseReleased
        //user wants to have a private chat with a mate
        if((evt.getClickCount() == 2)&&(evt.getButton() == MouseEvent.BUTTON1)){
            int i = users.getSelectedIndex();
            if(i != -1){
                Users s = userscol.getUser(i);
                if(s.getNetworkID().compareTo(values.networkName)!=0){
                    System.out.println(s.getNetworkID()+" "+s.getUsername());
                    privateChats.startNewPrivateChat(s.getNetworkID(), s.getUsername());
                }
            }
        }
    }//GEN-LAST:event_usersMouseReleased

    private void outgoingDataKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_outgoingDataKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            this.sendMessage();
        }
    }//GEN-LAST:event_outgoingDataKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem About;
    private javax.swing.JMenuItem clearScreen;
    private javax.swing.JMenuItem closeWindows;
    private javax.swing.JMenuItem exit;
    private javax.swing.JTextPane incomingData;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField outgoingData;
    private javax.swing.JMenuItem preference;
    private javax.swing.JButton send;
    private javax.swing.JTextField userName;
    private javax.swing.JList users;
    // End of variables declaration//GEN-END:variables

    /**
     * Appends a mesage to the message window in the gui
     * @param message incoming message
     */
    public void appendMessage(String message) {
        //incomingData.append(message);
        try{
        MutableAttributeSet chatAttr = new SimpleAttributeSet();
        cDoc.insertString( cDoc.getLength(), message, chatAttr );
        incomingData.setCaretPosition(cDoc.getLength());
        }catch(BadLocationException ex){}
        log.append(message);
    }

    /**
     * this is the update for the observers this method is called when an
     * observed class calls their update method this will then work out which
     * has done it and then will collect the relivant data from the class
     * @param o the observable class being watched
     * @param arg object throw by the class
     */
    public void update(Observable o, Object arg) {
        if(o instanceof NetworkListener){
            NetworkListener listen = (NetworkListener)o;
            String in = listen.getMessage();
            this.messageDeilevery(in);
        }else if(o instanceof SavedValues){
            this.updateSavedData();
        }
    }

    /**
     * Method used to transmit a message to another program in the multicast
     * network
     */
    private void sendMessage(){
        String message = outgoingData.getText();
        if(message.length() > 0){
            outgoingData.setText("");
            StringBuffer buf = new StringBuffer("1~");
            buf.append(userName.getText()+"~");
            buf.append(message+"~");
            try{
                network.sendMulticast(new String(buf));
            }catch(IOException ex){
                JOptionPane.showMessageDialog(this, "The message was unable to be sent please check the network connection", "Message Not Sent", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Sorts the message and works out whether or not the message is needed to
     * be displayed on the screen
     * @param input message from the network
     */
    private void messageDeilevery(String input){
        //this method will add the message if it is meant for the main display
        String[] breakup = input.split("~");
        int i = Integer.parseInt(breakup[1]);
        if(i == 1){
            String s;
            i = Integer.parseInt(breakup[0]);
            s = getTime()+": "+breakup[2]+" : "+breakup[3]+"\n";

            this.appendMessage(s);
        }else if(i == 2){
             switch(Integer.parseInt(breakup[3])){
                case 1:
                    try{
                        InetAddress thisIp = InetAddress.getLocalHost();
                        network.sendMulticast("2~"+values.networkName+"~2~"+thisIp.getHostAddress()+"~");
                    }catch(IOException e){}
                    break;

                case 2:
                    if(userscol.userExists(breakup[0])){
                        int k = userscol.replaceUsername(breakup[0], breakup[2]);
                        list.set(k, breakup[2]);
                    }else{
                        Users s;
                        if(breakup[4].compareTo("")!= 0){
                            s = new Users(breakup[2],breakup[0],breakup[4]);
                        }else{
                            s = new Users(breakup[2],breakup[0]);
                        }
                        userscol.addUser(s);
                        list.addElement(breakup[2]);
                        appendMessage(getTime()+": "+breakup[2]+" has joined \n");
                    }
                    break;

                case 3:
                    int j = 0;
                    j = userscol.removeUser(breakup[0]);
                    if(j != -1){
                        list.remove(j);
                        appendMessage(getTime()+": "+breakup[2]+" has left \n");
                    }
                    break;

                case 4:
                    System.exit(0);
                    break;

                case 5:
  
                    break;
            }
        }

    }

    private String getTime(){
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        String minute = calendar.get(Calendar.MINUTE)+"";
        String second = calendar.get(Calendar.SECOND)+"";
        if(calendar.get(Calendar.AM_PM)== Calendar.PM){
            hour = hour + 12;
        }
        String h = hour+"";
        if(h.length() < 2){
            h = "0"+h;
        }
        if(minute.length() < 2){
            minute = "0"+minute;
        }
        if(second.length() < 2){
            second = "0"+second;
        }
        return "["+h+":"+minute+":"+second+"]";
    }

    private String getTimeStamp(){
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        return day+""+month+""+year+""+hour+""+minute;
    }

    /**
     * Updates the saved data variables so that they can be saved to a file
     */
    private void updateSavedData(){
        this.setFontType(values.font);
        this.setBackgroundColour(values.background);
        this.setForegroundColour(values.foreGround);
        this.userName.setText(values.networkName);
        this.setDisplayLF(values.landf);
    }

    /**
     * Sets the font that is being used by the gui
     * @param f the font selected
     */
    public void setFontType(Font f){
        outgoingData.setFont(f);
        users.setFont(f);
        userName.setFont(f);
        incomingData.setFont(f);
    }
    /**
     * sets the background colour of the programs frames
     * @param c colour chosen by the user
     */
    public void setBackgroundColour(Color c){
        this.setBackground(c);
        this.outgoingData.setBackground(c);
        this.userName.setBackground(c);
        this.users.setBackground(c);
        this.incomingData.setBackground(c);
    }

    /**
     * sets the foreground colour of the programs frames
     * @param c colour chosen by the user
     */
    public void setForegroundColour(Color c){
        this.outgoingData.setForeground(c);
        this.userName.setForeground(c);
        this.users.setForeground(c);
        this.incomingData.setForeground(c);
    }

    /**
     * Changes the look and feel of the main display and all option panes 
     * linked to it
     * @param name name of the selected look and feel
     */
    private void setDisplayLF(String name){
        UIManager.LookAndFeelInfo plaf[] = UIManager.getInstalledLookAndFeels();
        for (int i = 0, n = plaf.length; i < n; i++) {
            if(plaf[i].getName().compareTo(name)==0){
                try{
                    UIManager.setLookAndFeel(plaf[i].getClassName());
                    SwingUtilities.updateComponentTreeUI(this);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    public void saving(){
        values.networkName = userName.getText();
        values.exportValues(SavedValues.DEFAULT_PATH);
        try{
        network.sendMulticast("2~"+values.networkName+"~3~");
        }catch(IOException ex){}
        if(values.publicLog){
            values.saveLog("log\\"+this.getTimeStamp()+"Public.txt", new String(log));
        }
    }

}
