package calculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.math.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class userInterface  extends JFrame {
	
	final int ADD=0,SUB=1,MUL=2,DIV=3,EQU=4;
	DecimalFormat format = new DecimalFormat("#.##");
	JTextField resultT = new JTextField();
	ArrayList <JButton> Num = new  ArrayList<JButton>() ;
	double x,y;
	double temp;
	boolean reset,hit;
	String text = "";
	int action;
	double result;
	int limit = 20;
	GridBagLayout mainL = new GridBagLayout();
	GridBagConstraints c = new GridBagConstraints();
	JButton dot = new JButton();
	JButton back = new JButton();
	JButton CE = new JButton();
	JButton C = new JButton();
	Dimension resultTD = new Dimension(5,50);
	
	
	public userInterface(){ 
		// construct frame
		this.setLayout(mainL);
		this.setSize(250,350);
		this.setTitle("Basic Calculator");
		this.setResizable(true);
		
		
		resultT.setEditable(false);
		resultT.setHorizontalAlignment(JTextField.RIGHT);
		
		// add display text
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 5;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		c.gridx=0;
		c.gridy=0;
		c.insets.set(5, 5, 5, 5);
		resultT.setPreferredSize(resultTD);
		resultT.setFont(new Font("SansSerif", Font.BOLD, 15));
		this.add(resultT,c);
		
		
		
		dot.setText(".");
		dot.setBackground(Color.LIGHT_GRAY);
		dot.setMargin(new Insets(0, 0, 0, 0));
		
		AbstractAction dotAction = new AbstractAction(){
			public void actionPerformed(ActionEvent arg0){
				JButton e = (JButton)arg0.getSource();
				if (resultT.getText().length()<limit){
					resultT.setText(resultT.getText()+e.getText());
					reset=false;
				}
			}
		};
		dot.addActionListener(dotAction);
		dot.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('.'), "dotAction");
		dot.getActionMap().put("dotAction",dotAction);
		AbstractAction backAction = new AbstractAction(){
			public void actionPerformed(ActionEvent arg0){
				JButton e = (JButton)arg0.getSource();
				String text = resultT.getText();
				if (text.length()>0){
					text = text.substring(0,text.length()-1);
				}
				resultT.setText(text);
				x = Double.parseDouble(resultT.getText());
			}
		};
		back.addActionListener(backAction);
		back.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE,0), "backAction");
		back.getActionMap().put("backAction",backAction);
		AbstractAction CEAction = new AbstractAction(){
			public void actionPerformed(ActionEvent arg0){
				JButton e = (JButton)arg0.getSource();			
				resultT.setText("");
				y=0;
			}
		};
		CE.addActionListener(CEAction);
		CE.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("SPACE"), "CEAction");
		CE.getActionMap().put("CEAction",CEAction);
		AbstractAction CAction = new AbstractAction(){
			public void actionPerformed(ActionEvent arg0){
				JButton e = (JButton)arg0.getSource();
				resultT.setText("");
				y=0;
				x=0;
				action=0;
			}
		};
		C.addActionListener(CAction);
		C.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('c'), "CAction");
		C.getActionMap().put("CAction",CAction);
		
		c.gridwidth = 1;
		
		back.setText("<--");
		back.setBackground(Color.LIGHT_GRAY);
		back.setMargin(new Insets(0, 0, 0, 0));
		c.gridx=0;
		c.gridy=1;
		this.add(back,c);
		CE.setText("CE");
		CE.setBackground(Color.LIGHT_GRAY);
		CE.setMargin(new Insets(0, 0, 0, 0));
		c.gridx=1;
		c.gridy=1;
		this.add(CE,c);
		C.setText("C");
		C.setBackground(Color.LIGHT_GRAY);
		C.setMargin(new Insets(0, 0, 0, 0));
		c.gridx=2;
		c.gridy=1;
		this.add(C,c);
		
		
		AbstractAction numberAction = new AbstractAction(){
			public void actionPerformed(ActionEvent arg0) {
				if (reset==true){
					resultT.setText("");
				}
				JButton e = (JButton)arg0.getSource();
				if (resultT.getText().length()<limit){
					resultT.setText(resultT.getText()+e.getText());
				}
				y = Double.parseDouble(resultT.getText());
				reset=false;
			}
		};
		for (int i =0; i<10;i++){
			Num.add(new JButton());
			Num.get(i).setMargin(new Insets(0, 0, 0, 0));
			Num.get(i).setBackground(Color.LIGHT_GRAY);
			Num.get(i).setText(Integer.toString(i));
			Num.get(i).setMnemonic(i);
			Num.get(i).addActionListener(numberAction);
			Num.get(i).getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke((char)(i+48)), "numberAction");
			Num.get(i).getActionMap().put("numberAction",numberAction);
		}
		
		c.gridx=0;
		c.gridy=2;
		for (int i = Num.size()-1; i>-1 ;i=i-3){
			if (i<=0){
				c.gridwidth = 2;
				c.gridx=0;
				c.gridy=5;
				this.add(Num.get(0),c);
				break;
			} else{
				for (int j = i-2; j<i+1;j++){ 
					this.add(Num.get(j),c);
					c.gridx++;
					
				}
			}
			c.gridx=0;
			c.gridy++;
			
		}
		
		c.gridwidth = 1;
		c.gridx=2;
		c.gridy=5;
		this.add(dot,c);
		
		ArrayList <JButton> operate = new ArrayList <JButton>();
		for (int i=0;i<5;i++){
			operate.add(new JButton());
		}
		AbstractAction operateAction = new AbstractAction(){
			public void actionPerformed(ActionEvent arg0){
				JButton e = (JButton)arg0.getSource();
				setOperate(Integer.parseInt(e.getName()));
			}
		};
		operate.get(ADD).setText("+");
		operate.get(ADD).addActionListener(operateAction);
		operate.get(ADD).setName(String.valueOf(ADD));
		operate.get(ADD).getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('+'), "operateAction");
		operate.get(ADD).getActionMap().put("operateAction",operateAction);
		
		
		operate.get(SUB).setText("-");
		operate.get(SUB).setName(String.valueOf(SUB));
		operate.get(SUB).addActionListener(operateAction);
		operate.get(SUB).getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('-'), "operateAction");
		operate.get(SUB).getActionMap().put("operateAction",operateAction);
		
		operate.get(MUL).setText("x");
		operate.get(MUL).setName(String.valueOf(MUL));
		operate.get(MUL).addActionListener(operateAction);
		operate.get(MUL).getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('*'), "operateAction");
		operate.get(MUL).getActionMap().put("operateAction",operateAction);
		
		operate.get(DIV).setText("/");
		operate.get(DIV).setName(String.valueOf(DIV));
		operate.get(DIV).addActionListener(operateAction);
		operate.get(DIV).getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke('/'), "operateAction");
		operate.get(DIV).getActionMap().put("operateAction",operateAction);
		
		AbstractAction equalAction = new AbstractAction(){
			public void actionPerformed(ActionEvent arg0){
				if (Double.isNaN(y)==false){
					resultT.setText(cal());
				}
				x = Double.parseDouble(resultT.getText());
				hit = true;
			}
		};
		operate.get(EQU).setText("=");
		operate.get(EQU).addActionListener(equalAction);
		operate.get(EQU).getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ENTER"), "equalAction");
		operate.get(EQU).getActionMap().put("equalAction",equalAction);
		
		
		c.gridx=3;
		c.gridy=1;
		for (int i=0;i<operate.size();i++){
			operate.get(i).setBackground(Color.LIGHT_GRAY);
			operate.get(i).setMargin(new Insets(0, 0, 0, 0));
			this.add(operate.get(i),c);
			c.gridy++;
		}
		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = 1;
		c.weightx =0.8 ;
		c.weighty = 1;
		c.gridx=0;
		c.gridy=1;
		c.insets.set(5, 5, 5, 5);
		
		this.setVisible(true);
		this.requestFocus();
		
	}
	public String cal(){
		String r ="";
		switch (action){
			case ADD: result = x+y; 
			break;
			case SUB: result = x-y;
			break;
			case MUL: result = x*y;
			break;
			case DIV: result = x/y;
			break;
		}
		
		if ((result*10)%10==0){
			
			r = format.format(result);
		} else{
			r=Double.toString(result);
		}
		
		return r;
	}
	public void setOperate(int setAction){
		if ((Double.isNaN(y)==false)&&(hit==false)){
			resultT.setText(cal());
		} 
		x = Double.parseDouble(resultT.getText());
		y = Double.NaN;
		reset = true;
		action = setAction;
		hit = false;
		
	}
}
