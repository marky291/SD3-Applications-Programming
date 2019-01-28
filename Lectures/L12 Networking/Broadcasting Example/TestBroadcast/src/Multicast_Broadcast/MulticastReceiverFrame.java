/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Multicast_Broadcast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import javax.swing.JTextArea;

/**
 *
 * @author alan.ryan
 */
public class MulticastReceiverFrame extends javax.swing.JFrame {
    
    public MulticastReceiverFrame() {
       
        
      
        initComponents();
        new UpDateClientGUI(jta).start();

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
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jta = new javax.swing.JTextArea();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Client");

        jta.setEditable(false);
        jta.setColumns(20);
        jta.setRows(5);
        jScrollPane2.setViewportView(jta);

        getContentPane().add(jScrollPane2, java.awt.BorderLayout.CENTER);

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
            java.util.logging.Logger.getLogger(MulticastReceiverFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MulticastReceiverFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MulticastReceiverFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MulticastReceiverFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MulticastReceiverFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jta;
    // End of variables declaration//GEN-END:variables
}

class UpDateClientGUI extends Thread {
   
   MulticastSocket socket = null;
   DatagramPacket inPacket = null;
   byte[] inBuf = new byte[256];
   JTextArea jta;
    
    public UpDateClientGUI(JTextArea jta) {
        
        this.jta = jta;
    }
    
    @Override
    public void run() {
        
      
        
        try {
            
            //Prepare to join multicast group
            socket = new MulticastSocket(8888);
            InetAddress address = InetAddress.getByName("224.2.2.3");
            socket.joinGroup(address);
               
            while (true) {
         
            inPacket = new DatagramPacket(inBuf, inBuf.length);
          
            socket.receive(inPacket);
        
            String msg = new String(inBuf, 0, inPacket.getLength());
        
            jta.append("Date and time on " + inPacket.getAddress().getHostName()+  " is " + msg + "\n");
            
            }
        }
       catch (IOException ioe) {
        System.out.println(ioe);
        }
        
    }
    
    
}