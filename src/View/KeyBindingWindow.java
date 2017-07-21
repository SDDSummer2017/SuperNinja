package View;

import Controller.KeyController;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.HashMap;

public class KeyBindingWindow extends JFrame {
	
	private KeyBinding la;
	private KeyBinding ha;
	private JLabel lblMoveLeft;
	private JLabel lblWalljump;
        private KeyController controller;
	private JLabel label6;
	private JLabel label_2;
	private JLabel lblJump;
	private KeyBinding mr;
	private KeyBinding ml;
	private KeyBinding j;
	private KeyBinding wj;
	private ArrayList<KeyBinding> list = new ArrayList<>();
	private JButton save;
	private JButton exit;  
        
	public KeyBindingWindow(KeyController controller) {
                this.controller = controller;
            
                setSize(350, 401);
		setTitle("Key Bindings");
		JFrame window = this;
		
		JLabel lblLightAttack = new JLabel("Light Attack");
		
		la = new KeyBinding();   
		la.setColumns(10);
		
		ha = new KeyBinding();
		ha.setColumns(10);
		
		lblMoveLeft = new JLabel("Move Left");
		
		lblWalljump = new JLabel("Wall Jump");
		
		label6 = new JLabel("Move Right");
		
		label_2 = new JLabel("Heavy Attack");
		
		lblJump = new JLabel("Jump");
		lblJump.setToolTipText("");
		
		mr = new KeyBinding();
		mr.setColumns(10);
		
		ml = new KeyBinding();
		ml.setColumns(10);
		
		j = new KeyBinding();
		j.setColumns(10);
		
		wj = new KeyBinding();
		
		list.add(la);
		list.add(ha);
		list.add(mr);
		list.add(ml);
		list.add(j);
		list.add(wj);
		
		la.addKeyListener(new MyKeyListener(la, list)); 
		ha.addKeyListener(new MyKeyListener(ha, list));
		mr.addKeyListener(new MyKeyListener(mr, list));
		ml.addKeyListener(new MyKeyListener(ml, list));
		j.addKeyListener(new MyKeyListener(j, list));
		wj.addKeyListener(new MyKeyListener(wj, list));
                
                la.setText(KeyEvent.getKeyText(KeyEvent.VK_F));
                ha.setText(KeyEvent.getKeyText(KeyEvent.VK_D));
                mr.setText(KeyEvent.getKeyText(KeyEvent.VK_RIGHT));
                ml.setText(KeyEvent.getKeyText(KeyEvent.VK_LEFT));
                j.setText(KeyEvent.getKeyText(KeyEvent.VK_UP));
                wj.setText(KeyEvent.getKeyText(KeyEvent.VK_SPACE));
                
                la.oldKeyCode = KeyEvent.VK_F;
                ha.oldKeyCode = KeyEvent.VK_D;
                ml.oldKeyCode = KeyEvent.VK_LEFT;
                mr.oldKeyCode = KeyEvent.VK_RIGHT;
                j.oldKeyCode = KeyEvent.VK_UP;
                wj.oldKeyCode = KeyEvent.VK_SPACE;
                
                la.newKeyCode = KeyEvent.VK_F;
                ha.newKeyCode = KeyEvent.VK_D;
                ml.newKeyCode = KeyEvent.VK_LEFT;
                mr.newKeyCode = KeyEvent.VK_RIGHT;
                j.newKeyCode = KeyEvent.VK_UP;
                wj.oldKeyCode = KeyEvent.VK_SPACE; 
               
		wj.setColumns(10);
		
		save = new JButton("Save Changes");
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                           bindKeys();
                           storeNewKeyBindings(); 
			}
		});
		
		exit = new JButton("Exit");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				window.dispose();
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(label_2)
								.addComponent(lblLightAttack, Alignment.LEADING)
								.addComponent(label6, Alignment.LEADING)
								.addComponent(lblMoveLeft, Alignment.LEADING)
								.addComponent(lblJump, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblWalljump, Alignment.LEADING))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(la, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(ha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(mr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(ml, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(j, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(wj, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(save)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(exit, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(112, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLightAttack)
						.addComponent(la, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(ha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(label6)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblMoveLeft)
								.addComponent(ml, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblJump)
								.addComponent(j, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblWalljump)
								.addComponent(wj, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addComponent(mr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(save)
						.addComponent(exit))
					.addContainerGap(106, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
	}
	
	class MyKeyListener extends KeyAdapter{
		KeyBinding txtField;
		ArrayList<KeyBinding> otherFields = new ArrayList<>();
		
		public MyKeyListener(KeyBinding txtField, ArrayList<KeyBinding> otherFields){
			this.txtField = txtField;
			this.otherFields = otherFields;
		}
		@Override
		public void keyReleased(KeyEvent e) { 
			txtField.setText(e.getKeyText(e.getExtendedKeyCode()));
			txtField.newKeyCode = e.getKeyCode(); 
                        
			for(KeyBinding otf : otherFields)
				if(otf.getText().equals(e.getKeyText(e.getExtendedKeyCode())) && otf != txtField)
                                {
                                    otf.setText("");
                                    otf.newKeyCode = -1;
                                }
                }
	} 
        
        public void bindKeys(){
            HashMap<Integer, Integer> newKeyCodes = new HashMap<>();
            newKeyCodes.put(la.oldKeyCode, la.newKeyCode);
            newKeyCodes.put(ha.oldKeyCode, ha.newKeyCode);
            newKeyCodes.put(mr.oldKeyCode, mr.newKeyCode);
            newKeyCodes.put(ml.oldKeyCode, ml.newKeyCode);
            newKeyCodes.put(j.oldKeyCode, j.newKeyCode);
            newKeyCodes.put(wj.oldKeyCode, wj.newKeyCode);
            controller.rebindKeys(newKeyCodes);
        }
        
        public void storeNewKeyBindings(){
            la.oldKeyCode = la.newKeyCode;
            ha.oldKeyCode = ha.newKeyCode;
            mr.oldKeyCode = mr.newKeyCode;
            ml.oldKeyCode = ml.newKeyCode;
            j.oldKeyCode = j.newKeyCode;
            wj.oldKeyCode = wj.newKeyCode;
        }
        
        private class KeyBinding extends JTextField{
            public int oldKeyCode;
            public int newKeyCode;
           
        }
}
