package EmployeeChat;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

    
    
    
    public class DynamicPanelList extends JPanel {
        
        public JPanel mainList;
        private  JScrollPane Scroll=null;
        public DynamicPanelList() {
            setLayout(new BorderLayout());
            
            mainList = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridwidth = GridBagConstraints.REMAINDER;
            gbc.weightx = 1;
            gbc.weighty = 1;
            mainList.add(new JPanel(), gbc);
            final JScrollPane ScrlPane=new JScrollPane(mainList);
            add(ScrlPane);
           Scroll=ScrlPane;
            JButton add = new JButton("Add");
            add.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
     
                }
            });
            
        
            
        }
        public void ADDMessage(EmployeeChat.MSG MSG,boolean IsLeft){
             GridBagConstraints gbc = new GridBagConstraints();
                    
                    gbc.gridwidth = GridBagConstraints.REMAINDER;
                    gbc.weightx = 1;
                    gbc.fill = GridBagConstraints.HORIZONTAL;
                    
                     if (IsLeft)
            {
                MSG_JPanel_LEFT panelL = new MSG_JPanel_LEFT(MSG);
                 mainList.add(panelL, gbc, mainList.getComponentCount());
            }else{
                 MSG_JPanel_Right panelR = new MSG_JPanel_Right(MSG);
                   mainList.add(panelR, gbc, mainList.getComponentCount());
            }
                    
            validate();
                    repaint();
                    Scroll.getVerticalScrollBar().setValue(Scroll.getVerticalScrollBar().getMaximum());
            
        }
        
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(200, 200);
        }
    }
