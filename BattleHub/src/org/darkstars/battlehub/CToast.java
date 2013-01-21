/*
 * CToast.java
 *
 * Created on 27 May 2006, 21:51
 */

package org.darkstars.battlehub;

import org.darkstars.battlehub.framework.CEvent;
import org.darkstars.battlehub.gui.CUISettings;
import org.darkstars.battlehub.framework.IModule;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.SwingUtilities;
import org.darkstars.battlehub.framework.CCore;
import org.darkstars.battlehub.framework.ICentralClass;
import org.darkstars.battlehub.framework.Misc;

/**
 *
 * @author  AF
 */

public class CToast extends javax.swing.JFrame implements IModule{
    public ArrayList<String> msgs = new ArrayList<String>();
    
    
    /**
     * Creates new form CToast
     */
    public CToast () {
        SwingUtilities.invokeLater ( new Runnable () {
            @Override
            public void run() {
                initComponents ();
            }
        });
    }
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        JMsgArea = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("org/darkstars/battlehub/languages"); // NOI18N
        setTitle(bundle.getString("CToast.title")); // NOI18N
        setAlwaysOnTop(true);
        setIconImage(CUISettings.GetWindowIcon());

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        JMsgArea.setColumns(20);
        JMsgArea.setEditable(false);
        JMsgArea.setFont(JMsgArea.getFont().deriveFont(JMsgArea.getFont().getSize()+2f));
        JMsgArea.setLineWrap(true);
        JMsgArea.setRows(1);
        JMsgArea.setWrapStyleWord(true);
        jScrollPane1.setViewportView(JMsgArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    
    public int a=0;
    
    @Override
    public void Update (){
        //
        a++;
        if(a > 5000){
            a = 0;
            SwingUtilities.invokeLater ( new Runnable () {
                @Override
                public void run () {
                    
                    if(!msgs.isEmpty ()){
                        
                        msgs.remove (0);
                        RedrawMsgs ();
                    }else if(isVisible ()){
                        setVisible (false);
                    }
                }
            });
        }
    }
    
    
    @Override
    public void NewGUIEvent (CEvent e){
        if(e.IsEvent("TOAST")){
            AddMessage (Misc.makeSentence (e.data,1));
        }
    }
    
    
    public void AddMessage (String s){
        final String a2 = s;
        SwingUtilities.invokeLater ( new Runnable () {
            @Override
            public void run () {
                if(!isVisible ()){
                    setVisible (true);
                }
                msgs.add (a2+"\n");
            }
        });
        
        RedrawMsgs ();
    }
    
    
    @Override
    public void NewEvent (CEvent e){
        // do stuff
    }
    
    
    public void RedrawMsgs (){
        SwingUtilities.invokeLater (new Runnable () {
            @Override
            public void run () {
                JMsgArea.setText ("");
                Iterator<String> i = msgs.iterator ();
                while(i.hasNext ()){
                    JMsgArea.append (i.next ());
                }
            }
        });
        
        //int sh = ;
    }
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea JMsgArea;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void Init(ICentralClass L) {
        L.GetCore().AddModule(this, CCore.LOBBY_GUI_EVENTS);
        
        SwingUtilities.invokeLater ( new Runnable () {
            @Override
            public void run () {
                JMsgArea.setText ("");
                int width = getWidth ();
                int height = getHeight ();
                Dimension screen = Toolkit.getDefaultToolkit ().getScreenSize ();
                int x = screen.width-width-50;
                int y = screen.height-height-50;
                setBounds (x,y,width,height);
            }
        });
    }

    @Override
    public void OnRemove() {

    }

    @Override
    public void OnEvent(CEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void OnRemove(int channel) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}