/**
 *
 * @author a80052136
 */

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.io.*;
import java.util.ArrayList;
import javax.swing.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Import extends javax.swing.JFrame {
    private ArrayList<BIS> bisList;
    /**
     * Creates new form Frame1
     */
    public Import() {
        initComponents();
        bisList = new ArrayList<>();
        centreWindow(this);
        setResizable(false);
    }
    
    // This function will centre the window
    private void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }
    private void openFileChooser () {
        final JFileChooser fc = new JFileChooser();
        /**
        FileNameExtensionFilter filter = new FileNameExtensionFilter("xlsx");
        fc.setFileFilter(filter);
        */
        File file = null;
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fc.getSelectedFile();
            filePathTF.setText(file.getAbsolutePath());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BrowseBtn = new javax.swing.JButton();
        fileNameLbl = new javax.swing.JLabel();
        filePathTF = new javax.swing.JTextField();
        importBtn = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        BrowseBtn.setText("Browse");
        BrowseBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BrowseBtnActionPerformed(evt);
            }
        });

        fileNameLbl.setText("Import File: ");

        filePathTF.setEditable(false);
        filePathTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filePathTFActionPerformed(evt);
            }
        });

        importBtn.setText("Import");
        importBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importBtnActionPerformed(evt);
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
                .addGap(63, 63, 63)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fileNameLbl)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(importBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)
                                .addGap(36, 36, 36))
                            .addComponent(filePathTF, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BrowseBtn)))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(fileNameLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filePathTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BrowseBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(importBtn)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BrowseBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BrowseBtnActionPerformed
        openFileChooser();
    }//GEN-LAST:event_BrowseBtnActionPerformed

    private void importBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importBtnActionPerformed
        try {
            readFile(filePathTF.getText());
            displayInfo di = new displayInfo(bisList);
            di.setVisible(true);
            this.setVisible(false);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Please choose an excel file"
                    + "(.xlxs) to import.",
                            "Message", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Please choose an excel file"
                    + "(.xlxs) to import.",
                            "Message", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }//GEN-LAST:event_importBtnActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
        MainPage mp = new MainPage();
        mp.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void filePathTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filePathTFActionPerformed
        openFileChooser();
    }//GEN-LAST:event_filePathTFActionPerformed

    private void readFile(String fileName) throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(new File(fileName));
        
        //create workbook instance that refers to .xls file
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        
        
        //create a sheet object to retrive the sheet
        XSSFSheet sheet = wb.getSheetAt(0);
        
        
        //loop through the rows in the sheet
        for (Row row: sheet) {
            String idScope = null, acceptancePlan = null, category = null;
            String stepName = null, subcontractor = null;
            String onADate = null, bisDate = null, stepStatus = null;
            String stepInitiated = null, stepSubmitted = null;
            String rejected = null, resubmitted = null, stepAccepted = null;
            String site = null;
            
            //loop through the cells in the row
            for (int cn=0; cn<=14; cn++) {
                Cell cell = row.getCell(cn);
                
                //set default cell value if no value is found 
                if (cell == null) {
                    cell = row.createCell(cn);
                    cell.setCellValue("null");
                }
                
                if (cn==0) {
                    idScope = cell.toString();
                }
                else if (cn==1) {
                    acceptancePlan = cell.toString();
                }
                else if (cn==2) {
                    category = cell.toString();
                }
                else if (cn==3) {
                    stepName = cell.toString();
                }
                else if (cn==4) {
                    subcontractor = cell.toString().toUpperCase();
                }
                else if (cn==5) {
                    site = cell.toString();
                }
                else if (cn==6) {
                    onADate = cell.toString();
                }
                else if (cn==7) {
                    bisDate = cell.toString();
                }
                else if (cn==8) {
                    stepStatus = cell.toString();
                }
                else if (cn==9) {
                    stepInitiated = cell.toString();
                }
                else if (cn==10) {
                    stepSubmitted = cell.toString();
                }
                else if (cn==11) {
                    rejected = cell.toString();
                }
                else if (cn==12) {
                    resubmitted = cell.toString();
                }
                else if (cn==13) {
                    stepAccepted = cell.toString();
                }   
            }
            
            BIS bis = new BIS(idScope, acceptancePlan, category, stepName, subcontractor,
            onADate, bisDate, stepStatus, stepInitiated, stepSubmitted,
            rejected, resubmitted, stepAccepted, site);
            bisList.add(bis);

        }
        
        //remove the first row with column name
        bisList.remove(0);
        wb.close();
        fis.close();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BrowseBtn;
    private javax.swing.JLabel fileNameLbl;
    private javax.swing.JTextField filePathTF;
    private javax.swing.JButton importBtn;
    private javax.swing.JButton jButton1;
    // End of variables declaration//GEN-END:variables
}
