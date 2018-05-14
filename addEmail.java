
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author a80052136
 */
public class addEmail extends javax.swing.JFrame {
    protected ArrayList<Subcontractor> conList;
    private Subcontractor theSubcon;
    /**
     * Creates new form addEmail
     */
    public addEmail(Subcontractor theSubcon) {
        initComponents();
        this.theSubcon = theSubcon;
        SubcontractorDA da = new SubcontractorDA();
        conList = da.getAllSubcontractor();
        setResizable(false);
        setTF(theSubcon);
        centreWindow(this);
    }
    
    // change the "add" button to "update" when user choose to update
    private void setTF (Subcontractor theSubcon) {
        if (theSubcon != null) {
            subconNameTF.setText(theSubcon.getName());
            emailTF.setText(theSubcon.getEmail());
            addBtn.setText("Update");
        }
    }

    // This function will centre the window
    private void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        subconNameTF = new javax.swing.JTextField();
        emailTF = new javax.swing.JTextField();
        addBtn = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Subcontractor Name: ");

        jLabel2.setText("Email:");

        addBtn.setText("Add");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
            }
        });

        jButton1.setText("Cancel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(subconNameTF)
                    .addComponent(emailTF, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE))
                .addContainerGap(61, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(subconNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(emailTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addBtn)
                    .addComponent(jButton1))
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        String name = subconNameTF.getText();
        String email = emailTF.getText();
        boolean hasExisted = checkEmailExist(email);
        SubcontractorDA da = new SubcontractorDA();
        
        if (theSubcon == null && !name.equals("") && !email.equals("")) {
            ArrayList <Subcontractor> conList = da.getAllSubcontractor();
            if (email.contains("@")) {
                if (!hasExisted) {
                    Subcontractor con = new Subcontractor(name, email);
                    da.save(con);
                    this.dispose();
                    MaintainEmail me = new MaintainEmail();
                    me.setVisible(true);
                }
                else {
                    JOptionPane.showMessageDialog(this, "This email is already exist",
                    "Message", JOptionPane.INFORMATION_MESSAGE);
                }

            }
            else {
                JOptionPane.showMessageDialog(this, "Please enter a valid email address.",
                        "Message", JOptionPane.INFORMATION_MESSAGE);
            }
            
        }
        else if (theSubcon != null && !name.equals("") && !email.equals("")){
            if (!hasExisted) {
                da.setSubcontractor(theSubcon.getEmail(), name, email);
                this.dispose();
                MaintainEmail me = new MaintainEmail();
                me.setVisible(true);
            }
            else {
                JOptionPane.showMessageDialog(this, "This email is already exists.",
                            "Message", JOptionPane.INFORMATION_MESSAGE);
            }     
        }
        else {
            JOptionPane.showMessageDialog(this, "Please fill in all the information required.",
                        "Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_addBtnActionPerformed
    
    // check whether the email exist
    private boolean checkEmailExist(String email) {
        boolean hasExisted = false;
        for (Subcontractor s: conList) {
                if (s.getEmail().equals(email)) {
                    hasExisted = true;
                }
            }
        return hasExisted;
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
        MaintainEmail me = new MaintainEmail();
        me.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JTextField emailTF;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField subconNameTF;
    // End of variables declaration//GEN-END:variables
}
