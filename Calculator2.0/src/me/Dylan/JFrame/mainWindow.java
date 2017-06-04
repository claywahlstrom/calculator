package me.Dylan.JFrame;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class mainWindow extends noeditMaterial implements MouseListener, KeyListener{
	//mainJframe
	private JFrame mainWindow;
	//JPanels
	private JPanel jf,textField,equationTextField;
	//Jbuttons
	private JButton num1b, num2b, num3b, num4b, num5b, num6b, num7b, num8b, num9b, num0b, squareb, subtractb, addb, multb, divb,equals,period,plusorminus,backspace, clear, clearall,sinb,cosb,rootb,eb,pib,tanb,radian;
	//Displayer is what displays the numbers and operators
	private JTextPane calcDisplayer, equationDisplayer;
	//number saves the first number into a string and number2 saves the second number into a string. AnswerConvToString is what is used to display the number after calculations
	private String number,number2,answerConvToString;
	//presNumber is the current number that was pressed and answer is the final answer before it is converted to String
	private int presNumber,answerT;
	private double answer;
	//bunch of booleans. the operator booleans are turned true when they are pressed. That way I know which operation to apply to the two numbers. 
	private boolean numberEnd,subtract,mult,add,div,square,isInteger,isCycling,shift,root,sin,cos,tan,degrees = false; 

	//I added the display boolean in case you didn't want the display but still wanted to create a new mainWindow object outside the class
	public mainWindow(boolean display){
		if(display){
			
			mainWindow = new JFrame();
			jf = new JPanel();
			textField = new JPanel();
			equationTextField = new JPanel();
	    
			GridLayout fl = new GridLayout();
		
			//make it look purty
			fl.setRows(7);
			fl.setVgap(10);
			fl.setHgap(50);
			jf.setLayout(fl);
			
			//For some reason the textField only extends across the window when you apply BorderLayout to it. It doesn't work if you apply the BorderLayout to the mainWindow instead of the textField. 
			BorderLayout textFieldbl = new BorderLayout();
			textField.setLayout(textFieldbl);
			textField.add(calcDisplayer = new JTextPane());

			BorderLayout equationTextFieldbl = new BorderLayout();		
			equationTextField.setLayout(equationTextFieldbl);
			equationTextField.add(equationDisplayer = new JTextPane());

			//This is what disables the text field at the top from being typed in.
			calcDisplayer.setEditable(false);
			equationDisplayer.setEditable(false);
			
			//Mouse listeners

			jf.add(backspace = new JButton("<"));jf.add(clear = new JButton("C"));jf.add(clearall = new JButton("CE"));jf.add(rootb = new JButton("\u221A"));jf.add(sinb = new JButton("sin()"));jf.add(cosb = new JButton("cos()"));jf.add(tanb = new JButton("tan()"));jf.add(squareb = new JButton("^2"));jf.add(eb = new JButton("e"));jf.add(pib = new JButton("\u03C0"));jf.add(plusorminus = new JButton("+-"));jf.add(multb = new JButton("*"));jf.add(num1b = new JButton("1"));jf.add(num2b = new JButton("2"));jf.add(num3b = new JButton("3"));jf.add(subtractb = new JButton("-"));jf.add(num4b = new JButton("4"));jf.add(num5b = new JButton("5"));jf.add(num6b = new JButton("6"));jf.add(addb = new JButton("+"));jf.add(num7b = new JButton("7"));jf.add(num8b = new JButton("8"));jf.add(num9b = new JButton("9"));jf.add(divb = new JButton("/"));jf.add(radian = new JButton("RT"));jf.add(num0b = new JButton("0"));jf.add(period = new JButton("."));jf.add(equals = new JButton("="));
			num1b.addMouseListener(this);num2b.addMouseListener(this);num3b.addMouseListener(this);num4b.addMouseListener(this);num5b.addMouseListener(this);num6b.addMouseListener(this);num7b.addMouseListener(this);num8b.addMouseListener(this);num9b.addMouseListener(this);num0b.addMouseListener(this);
			divb.addMouseListener(this);subtractb.addMouseListener(this);addb.addMouseListener(this);multb.addMouseListener(this);squareb.addMouseListener(this);rootb.addMouseListener(this);sinb.addMouseListener(this);cosb.addMouseListener(this);
			equals.addMouseListener(this);plusorminus.addMouseListener(this);period.addMouseListener(this);eb.addMouseListener(this);pib.addMouseListener(this);tanb.addMouseListener(this);
			clear.addMouseListener(this);clearall.addMouseListener(this);backspace.addMouseListener(this);radian.addMouseListener(this);
			
            //for some reason... I have to have add all of these... otherwise things get weird...
			num1b.addKeyListener(this);num2b.addKeyListener(this);num3b.addKeyListener(this);num4b.addKeyListener(this);num5b.addKeyListener(this);num6b.addKeyListener(this);num7b.addKeyListener(this);num8b.addKeyListener(this);num9b.addKeyListener(this);num0b.addKeyListener(this);
			divb.addKeyListener(this);subtractb.addKeyListener(this);addb.addKeyListener(this);multb.addKeyListener(this);squareb.addKeyListener(this);rootb.addKeyListener(this);sinb.addKeyListener(this);cosb.addKeyListener(this);
			equals.addKeyListener(this);plusorminus.addKeyListener(this);period.addKeyListener(this);eb.addKeyListener(this);pib.addKeyListener(this);tanb.addKeyListener(this);radian.addKeyListener(this);
			clear.addKeyListener(this);clearall.addKeyListener(this);backspace.addKeyListener(this);
			equationDisplayer.addKeyListener(this);calcDisplayer.addKeyListener(this);
			
			
			jf.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			jf.setBackground(java.awt.Color.black);
			BorderLayout bl2 = new BorderLayout();
			mainWindow.setLayout(bl2);
			mainWindow.add(equationTextField, BorderLayout.NORTH);
			mainWindow.add(textField, BorderLayout.CENTER);
			mainWindow.add(jf, BorderLayout.SOUTH);
			
			//more purty things
			mainWindow.setTitle("Boss Calculator");
			Font displayerFont = new Font("Serif", Font.BOLD, 33);
			calcDisplayer.setFont(displayerFont);
			Font buttonFont = new Font("Serif", Font.BOLD, 28);
			num1b.setFont(buttonFont);num2b.setFont(buttonFont);num3b.setFont(buttonFont);num4b.setFont(buttonFont);num5b.setFont(buttonFont);num6b.setFont(buttonFont);num7b.setFont(buttonFont);num8b.setFont(buttonFont);num9b.setFont(buttonFont);num0b.setFont(buttonFont);
			equals.setFont(buttonFont);backspace.setFont(buttonFont);plusorminus.setFont(buttonFont);clear.setFont(buttonFont);clearall.setFont(buttonFont);addb.setFont(buttonFont);
			subtractb.setFont(buttonFont);multb.setFont(buttonFont);squareb.setFont(buttonFont);divb.setFont(buttonFont);period.setFont(buttonFont);rootb.setFont(buttonFont);sinb.setFont(buttonFont);cosb.setFont(buttonFont);pib.setFont(buttonFont);eb.setFont(buttonFont);tanb.setFont(buttonFont);radian.setFont(buttonFont);
	        String path = "calculator.png";
	        File file = new File(path);
	        BufferedImage image = null;
			try {
				image = ImageIO.read(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			mainWindow.setIconImage(image);

			//finally
			changeDimension(mainWindow, 300,250);
			windowPacker(mainWindow);
		}
			
	}
	
	public static void main(String[] args){
		@SuppressWarnings("unused")
		mainWindow mw = new mainWindow(true);

		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(e.getSource() == num1b){
			displayNumbers(1,calcDisplayer,isNumberEnd(), this);
		}else if(e.getSource() == num2b){
			displayNumbers(2,calcDisplayer,isNumberEnd(), this);
		}else if(e.getSource() == num3b){
			displayNumbers(3,calcDisplayer,isNumberEnd(), this);
		}else if (e.getSource() == num4b){
			displayNumbers(4,calcDisplayer,isNumberEnd(), this);
		}else if(e.getSource() == num5b){
			displayNumbers(5,calcDisplayer,isNumberEnd(), this);
		}else if(e.getSource() == num6b){
			displayNumbers(6,calcDisplayer,isNumberEnd(), this);
		}else if (e.getSource() == num7b){
			displayNumbers(7,calcDisplayer,isNumberEnd(), this);
		}else if(e.getSource() == num8b){
			displayNumbers(8,calcDisplayer,isNumberEnd(), this);
		}else if(e.getSource() == num9b){
		    displayNumbers(9,calcDisplayer,isNumberEnd(), this);
		}else if(e.getSource() == num0b){
			displayNumbers(0,calcDisplayer,isNumberEnd(), this);
		}else if(e.getSource() == pib){
			displayNumbers(Math.PI,calcDisplayer,isNumberEnd(),this);
		}else if(e.getSource() == eb){
			displayNumbers(Math.E,calcDisplayer,isNumberEnd(),this);
		}
		//addition, subtraction etc buttons.

		if(e.getSource() == subtractb){
			
			setSubtract(keyPressedOperators(0, 0, "-", isSubtract(),shift,calcDisplayer,this,isCycling()));
			
		}else if(e.getSource() == multb){
			
			setMult(keyPressedOperators(0,0,"*",isMult(),shift,calcDisplayer,this,isCycling()));
			
		}else if(e.getSource() == divb){
			
			setDiv(keyPressedOperators(0,0,"/",isDiv(),shift,calcDisplayer,this,isCycling()));
			
		}else if(e.getSource() == addb){
			setAdd(keyPressedOperators(0,0,"+",isAdd(),shift,calcDisplayer,this,isCycling()));

		}else if(e.getSource() == squareb){
			if(getNumberS() != null && getNumberS() != ""){
				setSquare(keyPressedOperators(0,0,getNumberS() + "^2",isSquare(),shift,calcDisplayer,this,isCycling()));
			}else{
				completeClear(calcDisplayer, this);
			}
			
		}else if(e.getSource() == rootb){
			setRoot(keyPressedOperators(0,0,"\u221A" + getNumberS(),isRoot(),shift,calcDisplayer,this,isCycling));
			
		}else if(e.getSource() == sinb){
			if(getNumberS() != null && getNumberS() != ""){
				setSquare(keyPressedOperators(0,0,getNumberS() + "^2",isSquare(),shift,calcDisplayer,this,isCycling()));
			}else{
				completeClear(calcDisplayer, this);
			}
		}else if(e.getSource() == cosb){
			if(getNumberS() != null && getNumberS() != ""){
				setCos(keyPressedOperators(0,0,"Cos(" + getNumberS() + ")",isCos(),shift,calcDisplayer,this,isCycling()));
			}else{
				setCos(keyPressedOperators(0,0,"Cos(",isCos(),shift,calcDisplayer,this,isCycling()));
			}
    	}else if(e.getSource() == tanb){
    		if(getNumberS() != null && getNumberS() != ""){
    			setTan(keyPressedOperators(0,0,"Tan(" + getNumberS() + ")",isTan(),shift,calcDisplayer,this,isCycling()));
    		}else{
    			setTan(keyPressedOperators(0,0,"Tan(",isTan(),shift,calcDisplayer,this,isCycling()));
    		}
	}
		
		if(e.getSource() == radian){
			setDegrees(!isDegrees());
		}
		
		if(e.getSource() == equals){
			
			calculations();
			
		}
		
		if(e.getSource() == clearall){
			
			completeClear(calcDisplayer, this);
			
		}
		
		if(e.getSource() == clear){
			if(isNumberEnd()){
				setNumberS2("");
				calcDisplayer.setText("");
			}else{
				if(isCycling()){
					setCycling(false);
					System.out.println("No longer cycling");
				}
				setNumberS("");
				calcDisplayer.setText("");
			}
		}
		
		if(e.getSource() == backspace){
			moreBackspace(this, calcDisplayer);
		}
		
		if(e.getSource() == period){
			periodAdder(this, calcDisplayer);

			
		}
		
		if(e.getSource() == plusorminus){
			if(isNumberEnd()){
				setNumberS2(negativeConverter(getNumberS2()));
				calcDisplayer.setText(getNumberS2());
			}else{
				setNumberS(negativeConverter(getNumberS()));
				calcDisplayer.setText(getNumberS());
			}
		}
		

		
	}
	public void calculations(){
		//If you are wondering why this has so many parameters, dont worry, everything will be ok.
		calcDisplayer.setText(finalCalculations(getNumberS(), getNumberS2(), isSquare(), isSubtract(), isMult(), 
				isDiv(), isAdd(), isRoot(), isSin(), isCos(),isTan(), getAnswerDouble(), getAnswerInt(), answerConvToString, calcDisplayer, this));
	}
	//Next 5 lines are useless (almost)
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void keyTyped(KeyEvent e) {}
	
	@Override
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();

		if(key == 16){//shift
			shift = true;
		}
		if(key == KeyEvent.VK_ENTER){//for some reason the vk thing is only working for enter. So I just leave this one
			calculations();
		}
		
		keyPressedNumbers(key, KeyEvent.VK_0,0,shift,calcDisplayer,this);
		keyPressedNumbers(key, KeyEvent.VK_1,1,shift,calcDisplayer,this);
		keyPressedNumbers(key, KeyEvent.VK_2,2,shift,calcDisplayer,this);
		keyPressedNumbers(key, KeyEvent.VK_3,3,shift,calcDisplayer,this);
		keyPressedNumbers(key, KeyEvent.VK_4,4,shift,calcDisplayer,this);
		keyPressedNumbers(key, KeyEvent.VK_5,5,shift,calcDisplayer,this);
		keyPressedNumbers(key, KeyEvent.VK_6,6,shift,calcDisplayer,this);
		keyPressedNumbers(key, KeyEvent.VK_7,7,shift,calcDisplayer,this);
		keyPressedNumbers(key, KeyEvent.VK_8,8,shift,calcDisplayer,this);
		keyPressedNumbers(key, KeyEvent.VK_9,9,shift,calcDisplayer,this);
		
		//the secondary method is for the extra shift functionality
		setAdd(secondaryKeyPressedOperators(key,107,61,"+", isAdd(),shift,isNumberEnd(),isCycling(),calcDisplayer,this));
		setMult(secondaryKeyPressedOperators(key,106,56,"*", isMult(), shift, isNumberEnd(), isCycling(), calcDisplayer,this));
		if(getNumberS() != null && getNumberS() != ""){
			setSquare(secondaryKeyPressedOperators(key,192,54,getNumberS() + "^2",isSquare(),shift,isNumberEnd(),isCycling(),calcDisplayer,this));
		}
        setRoot(secondaryKeyPressedOperators(key,40,50,"\u221A" + getNumberS(),isRoot(),shift,isNumberEnd(),isCycling,calcDisplayer,this));
		setDiv(keyPressedOperators(key,47,"/",isDiv(),shift,calcDisplayer,this,isCycling()));
		setDiv(keyPressedOperators(key,111,"/",isDiv(), shift, calcDisplayer, this, isCycling()));
		setSubtract(keyPressedOperators(key,45,"-",isSubtract(),shift,calcDisplayer,this,isCycling()));
		setSubtract(keyPressedOperators(key,109,"-",isSubtract(),shift,calcDisplayer,this,isCycling()));

		
		if(key == 8){//backspace
			moreBackspace(this, calcDisplayer);
		}
		if(key == 67){//clear
			completeClear(calcDisplayer, this);
		}
		if(key == 46 || key == 127){//period on left or period on numpad
			periodAdder(this, calcDisplayer);
		}
		if(shift && key == 45){//converts to negative if you ctrl minus
			if(isNumberEnd()){
				setNumberS2(negativeConverter(getNumberS2()));
				calcDisplayer.setText(getNumberS2());

			}else{
				setNumberS(negativeConverter(getNumberS()));
				calcDisplayer.setText(getNumberS());
			}
		}
		if(key == 32){//press space
			completeClear(calcDisplayer, this);
			calcDisplayer.setText("spacebar? really?");
		}
		
		if(key == 69){//... It's the e key... for the e button
			displayNumbers(Math.E,calcDisplayer,isNumberEnd(),this);
		}
		
		if(key == 83){//S for sign
			if(getNumberS() != null && getNumberS() != "" && !isNumberEnd()){
				setSin(keyPressedOperators(0,0,"Sin(" + getNumberS() + ")",isSin(),shift,calcDisplayer,this,isCycling()));
			}else{
				setSin(keyPressedOperators(0,0,"Sin(",isSin(),shift,calcDisplayer,this,isCycling()));
			}
		}
		if(key == 79){//Using an o for cosign because I used c for clear
			if(getNumberS() != null && getNumberS() != "" && !isNumberEnd()){
				setCos(keyPressedOperators(0,0,"Cos(" + getNumberS() + ")",isCos(),shift,calcDisplayer,this,isCycling()));
			}else{
				setCos(keyPressedOperators(0,0,"Cos(",isCos(),shift,calcDisplayer,this,isCycling()));
			}
		}
		if(key == 84){//t for tan
    		if(getNumberS() != null && getNumberS() != "" && !isNumberEnd()){
    			setTan(keyPressedOperators(0,0,"Tan(" + getNumberS() + ")",isTan(),shift,calcDisplayer,this,isCycling()));
    		}else{
    			setTan(keyPressedOperators(0,0,"Tan(",isTan(),shift,calcDisplayer,this,isCycling()));
    		}
		}
		if(key == 80){//p for pi
			displayNumbers(Math.PI,calcDisplayer,isNumberEnd(),this);
		}
		if(key == 82){//r for radians cycles betweens degrees and radians when using sin cos and tan
			setDegrees(!isDegrees());
		}
		

	}
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == 16){//shift
			shift = false;
		}
	}
	//just a bunch of getters and setters for the no edit method.
	public String getOperator(){
		if(isMult()){
			return "*";
		}else if(isAdd()){
			return "+";
		}else if(isSubtract()){
			return "-";
		}else if(isSquare()){
			return "^2";
		}else if(isDiv()){
			return "/";
		}else if(isRoot()){
			return "\u221A";
		}else if(isSin()){
			return "Sin(";
		}else if(isCos()){
			return "Cos(";
		}else if(isTan()){
			return "Tan(";
		}
		return "NA";
	}
	
	public void setDisplayer(String message){
		calcDisplayer.setText(message);
	}
	public void setEquationDisplayer(String text){equationDisplayer.setText(text);}
	public String getEquationDisplayer(){return equationDisplayer.getText();}
	public boolean isNumberEnd() {return numberEnd;}
	public void setNumberEnd(boolean numberEnd) {this.numberEnd = numberEnd;}
	public boolean isInteger() {return isInteger;}
	public void setInteger(boolean isInteger) {this.isInteger = isInteger;}
	public int getAnswerInt() {return answerT;}
	public void setAnswerInt(int answerT) {this.answerT = answerT;}
	public double getAnswerDouble() {return answer;}
	public void setAnswerDouble(double answer) {this.answer = answer;}
	public String getNumberS() {return number;}
	public void setNumberS(String number) {this.number = number;}
	public String getNumberS2() {return number2;}
	public void setNumberS2(String number2) {this.number2 = number2;}
	public boolean isCycling() {return isCycling;}
	public void setCycling(boolean isCycling) {this.isCycling = isCycling;}
	public void setSin(boolean sin){this.sin=sin;}
	public boolean isSin(){return sin;}
	public boolean isDiv() {return div;}
	public void setDiv(boolean div) {this.div = div;}
	public boolean isAdd() {return add;}
	public void setAdd(boolean add) {this.add = add;}
	public void setRoot(boolean root){this.root = root;}
	public boolean isRoot(){return root;}
	public void setCos(boolean cos){this.cos = cos;}
	public boolean isCos(){return cos;}
	public void setTan(boolean tan){this.tan = tan;}
	public boolean isTan(){return tan;}
	public boolean isMult() {return mult;}
	public void setMult(boolean mult) {this.mult = mult;}
	public boolean isSubtract() {return subtract;}
	public void setSubtract(boolean subtract) {this.subtract = subtract;}
	public boolean isSquare() {return square;}
	public void setSquare(boolean square) {this.square = square;}
	public int getPresNumber() {return presNumber;}
	public void setPresNumber(int presNumber) {this.presNumber = presNumber;}
	public boolean isDegrees() {return degrees;}
	public void setDegrees(boolean degrees) {this.degrees = degrees;}
}