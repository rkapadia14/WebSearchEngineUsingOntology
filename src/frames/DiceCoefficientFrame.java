/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import AntColonyOptimization.Route;

/**
 *
 * @author Rinali Kapadia
 */
public class DiceCoefficientFrame extends javax.swing.JFrame {

    /**
     * Creates new form DiceCoefficientFrame
     */
    public DiceCoefficientFrame(ArrayList<Route> Rout) {
        initComponents();
        setLocationRelativeTo(null);
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        model.addColumn("*");
        for (int i = 0; i < Rout.size(); i++) {
            int temp = i;
            model.addColumn("Route:"+temp);
        }
        Object toadd[] = new Object[Rout.size()+1];
        
         int temp = 0;
        for (Route rout2 : Rout) {
            double DiceCoefficient[] = new double[Rout.size()];
            toadd[0] = "Route:"+temp;
            temp++;
            double score2 = rout2.getAntScore();
            int loop2 = 0;
            for (Route rout3 : Rout) {
                
                
                int match = 0;
                            
                            double ele_rout2[] = rout2.getElements();
                            double ele_rout3[] = rout3.getElements();
                            
                             int len_rout2 = 0;
                              int len_rout3 = 0;
                             for (double d : ele_rout3) {
                    if(!(d==32165.0)){
                        len_rout3 ++;
                    }
                }
                             for (double d : ele_rout2) {
                    if(!(d==32165.0)){
                        len_rout2 ++;
                    }
                }
                             
                             
                              
                              
                             for (double d : ele_rout2) {
                                 for (double e : ele_rout3) {
                                     if(d==e && !(d==32165.0)){
                                         match++;
                                     }
                                 }
                            }
                             
                              double D = 2 *match;
                    D = D/(len_rout2+len_rout3);

//                double score3 = rout3.getAntScore();
//                double A = 2 * (score2 - score3);
//                double B = (score2 + score3);
//                double D = A / B;
                DiceCoefficient[loop2] = D;
                toadd[loop2+1] = D;
                loop2++;
            }
             model.addRow(toadd);
            rout2.setDiceCoefficient(DiceCoefficient);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
       
       //jTable1.setPreferredScrollableViewportSize(new Dimension(1200, 1800));
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 811, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
       //setSize(new Dimension(900, 400)); // Set your desired width and height
        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DiceCoefficientFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DiceCoefficientFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DiceCoefficientFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DiceCoefficientFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new DiceCoefficientFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
