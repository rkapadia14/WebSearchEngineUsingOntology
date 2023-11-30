package frames;

import static AntColonyOptimization.AntColonyOptimization.combParser;
import AntColonyOptimization.Combinations;
import AntColonyOptimization.Route;
import KeyphraseExtraction.KeyphraseExtractor;
import cosine_similarity.cosine_similarity;
import crawlerArticles.Articles;
import crawlerArticles.MainCrawler;

import static crawlerArticles.MainCrawler.ArticleContentCrawler;
import static crawlerArticles.MainCrawler.ArticleNameCrawler;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Rinali Kapadia
 */
public class mainFrame extends javax.swing.JFrame {

    /**
     * Creates new form mainDrame
     */
    public static ArrayList<Articles> Articles = new ArrayList<Articles>();
    public static String searchTerm;
    public static double[][] similarityMatrix;
    public static ArrayList<Route> Routes;

    public mainFrame() {
        initComponents();
        setLocationRelativeTo(null);
       
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
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Enter Query:");

        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title", "Content"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton2.setText("Extract Keyphrase");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Compute Similarity Matrix");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("AntColonyOptimization");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("DiceCoefficient");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Final Scores");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 693, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6)))
                .addContainerGap(84, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(31, 31, 31)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addContainerGap(69, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String query = jTextField1.getText();

        String SearchTerm = query;
        searchTerm = query;
        Articles.clear();
        Articles = ArticleNameCrawler(SearchTerm, Articles);
        try {
			Articles = ArticleContentCrawler(Articles);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        Thread thread = new Thread() {
            public void run() {
                try {
                    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

                    for (Articles art : Articles) {
                    	model.addRow(new Object[]{art.getTitle(), art.getContent()});
                    }
                    JOptionPane.showMessageDialog(null, "Artical Crawling Completed");
                } catch (Exception ex) {
                }
            }
        };
        thread.start();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        int row = jTable1.getSelectedRow();
        String title = (jTable1.getModel().getValueAt(row, 0).toString());
        String content = (jTable1.getModel().getValueAt(row, 1).toString());

        new individualArtical(title, content).setVisible(true);


    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        Articles = KeyphraseExtractor.KeyphraseExtractor(Articles);
        Thread thread = new Thread() {
            public void run() {
                try {
                    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

                    for (Articles art : Articles) {
//                        String sql = "insert into `articles`(`title`,`content`) values ( '"+art.getTitle()+"','"+art.getContent()+"')";
//                        db.getUpdate(sql);
                        model.addRow(new Object[]{art.getTitle(), art.getContent()});
                    }

                    JOptionPane.showMessageDialog(null, "Keyphrase Extraction Done..!");
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        };
        thread.start();


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        similarityMatrix = new double[Articles.size()][Articles.size()];

        for (int i = 0; i < Articles.size(); i++) {
            Articles art1 = Articles.get(i);
            for (int x = 0; x < Articles.size(); x++) {
                Articles art2 = Articles.get(x);
                similarityMatrix[i][x] = cosine_similarity.cosine_similarity(art1.getContent(), art2.getContent());
            }
        }

        new similarityMatrix(similarityMatrix).setVisible(true);


    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        int length = similarityMatrix.length;
        Routes = new ArrayList<Route>();
        String comb = Combinations.Combinations(length);
        double[][] combinations = new double[length][length];
        combinations = combParser(comb.trim(), length);
        int Count = 0;
        double pre = 0;
        for (double[] is : combinations) {
            double rout[] = new double[is.length];
            double avgScore = 0;
            for (int i = 0; i < is.length; i++) {
                
                pre = avgScore;
                if (similarityMatrix[Count][(int) is[i]] > avgScore || i<2) {
                    avgScore = avgScore + similarityMatrix[Count][(int) is[i]];
                    int div = i + 1;
                    avgScore = avgScore / div;
                    rout[i] = is[i];
                    
                } else {
                    rout[i] = 32165;
                }

            }
            avgScore = avgScore / is.length;
            Routes.add(new Route(rout, pre, Count + 1, null, 0));
            Count++;
            
        }

        ArrayList<String> paths = new ArrayList<>();
        ArrayList<String> AntScores = new ArrayList<>();
        for (Route rt : Routes) {
            String path = "";
            double[] rt_arr = rt.getElements();
            for (int i = 0; i < rt_arr.length; i++) {
                if (rt_arr[i] == 32165) {
//                    path = path + "" + rt_arr[i] + "--->";
                } else {
                    path = path + "" + rt_arr[i] + "--->";
                }
            }
            path = path + "EXIT";
            paths.add(path);
            AntScores.add(rt.getAntScore() + "");

        }
        new AntColony(paths, AntScores,false).setVisible(true);

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        new DiceCoefficientFrame(Routes).setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        ArrayList<String> paths = new ArrayList<>();
        ArrayList<String> AntScores = new ArrayList<>();

        
       
        for (Route rout : Routes) {
             int loop = 1;
            double ClusterAntScore = 0;
            double avgScore = 0;
            double DiceCoefficient[] = rout.getDiceCoefficient();
            
            for (int i = 0; i < DiceCoefficient.length; i++) {

                if (DiceCoefficient[i] > avgScore) {
                    ClusterAntScore = ClusterAntScore + DiceCoefficient[i];
                    ClusterAntScore = ClusterAntScore/loop;
                    avgScore = ClusterAntScore;
                    loop++;
                } else {

                }
            }
            ClusterAntScore = ClusterAntScore / DiceCoefficient.length;
            rout.setClusterAntScore(ClusterAntScore);
            String path = "";
            double[] rt_arr = rout.getElements();
            for (int i = 0; i < rt_arr.length; i++) {
                if (rt_arr[i] == 32165) {
//                    path = path + "" + rt_arr[i] + "--->";
                } else {
                    path = path + "" + rt_arr[i] + "--->";
                }
                System.out.print(rt_arr[i] + " ");
            }
            paths.add(path);
            AntScores.add(ClusterAntScore + "");
        }

        new AntColony(paths, AntScores ,true).setVisible(true);
        MainCrawler.ontologyCreation(searchTerm, Articles);
    }//GEN-LAST:event_jButton6ActionPerformed

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
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
//                   UIManager.setLookAndFeel("com.jtattoo.plaf.bernstein.BernsteinLookAndFeel");
//                   UIManager.setLookAndFeel("com.jtattoo.plaf.fast.FastLookAndFeel");
//                   UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
//                   UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
                    new mainFrame().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(mainFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}