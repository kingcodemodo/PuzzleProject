import java.awt.*; //IMPORT ALL .AWT METHODS

import java.awt.event.*;

import javax.swing.*; //IMPORT ALL JAVAX SWING CLASSES/METHODS

import javax.swing.border.*;

import java.util.*;

import java.lang.*;
import java.lang.Object;
import javax.swing.ImageIcon;

import java.lang.Object;
import java.lang.Math;

import java.util.concurrent.ThreadLocalRandom;

/**Declares All Variables Required For The Operation Of The Program And Construction Of The Puzzle Blueprint*/
public class PuzzleWorkingClean extends JFrame implements ActionListener
{
int rows = 3;//value for y rows 
int columns = 4;//value for x columns
int rowscounter = 0; //counter for for loops for y axis
int columnscounter = 0; //counter for for loops x axis
int insertcounter = 0; //insertcounter to count across executions

JButton[][] TileArray = new JButton[rows][columns]; //JButton 2d Array To Put Tiles On
JButton[][] CompletedComparisonTileArray = new JButton[rows][columns]; //Copy Of Array Taken To Be Populated Before Randomisation
Icon[] IconArray = new ImageIcon[12]; //Image Icons With Extra For Potential Temp Storage
int counter = 0; //Generic Global Counter

Icon GreyBoxGlobalStorageIcon = new ImageIcon("bart0.jpg"); //Permanent Global Storage OF GreyBox Image
String GreyBoxGlobalStorageString = GreyBoxGlobalStorageIcon.toString(); //Permanent Global Storage Of String For GreyBox Image
 
Icon ImageToBeChecked; //Icon Image To Be Checked To Be Declared In Global For Accesibility

int greyrowlocation; //GreyRowLocation Declared In Global For Accessibility Is Re-Written Each Time Function Is Called Anyway
int greycolumnlocation; //GreyColumnLocation Declared In Global For Accessibility Is Re-Written Each Time Function Is Called Anyway
 
int clickedrowlocation; //clickedrowlocation declared in global for accesibility
int clickedcolumnlocation; //clicked column declared in global for accesibility

int numberofclicks; //numberofclicks declared in global to carry over across whole program AKA Accesibility

JButton clickediconreference; //JButton declared in global so as to be accessed across constructor/accessor/mutator methods - ACCESSIBLE

JPanel WindowContents = new JPanel(); //PUSHED TO GLOBAL FOR ACCESS
  
JPanel TopMenuBar = new JPanel(); //PUSHED TO GLOBAL FOR ACCESS
  	
JLabel textLabel = new JLabel("Score =" + numberofclicks, SwingConstants.CENTER); //PUSHED TO GLOBAL FOR ACESS
  		  
JPanel TopMenuPanel = new JPanel(); //TOP MENU BAR PUSHED TO GLOBAL FOR ACCESS
  		   
JButton TopMenuButtonLeft = new JButton("Show High Scores");

JButton TopMenuButtonMiddle = new JButton("Randomiser Button");
			
JButton TopMenuButtonRight = new JButton("New Game");
			
JPanel NewOverPanel = new JPanel();
				 
int completioncounter = 0; //used to gauge how many tiles are in the right location
				 
boolean randomiserfinished = false; //used to gauge whether randomiser has been completed
				
boolean result = false;

JLabel TopMenuLabelMiddle = new JLabel("Lower Is Better!");

JPanel UnderPanel = new JPanel();
			
//VARIABLES ARE DECLARED IN ORDER OF USE




	/**Constructor For The Puzzle Working Method Which Declares It's Size & Title & Calls The Populate() Function To Create The Puzzle*/
	 public PuzzleWorkingClean() 
	  {
	   setSize(600,500);
	   Populate();
	   setTitle("Enjoy!");
	   setResizable(false);
	  }

/**Creates The Puzzle's Full Structure From BluePrint*/
 public void Populate()
 {
	 Dimension OverPanelSize = new Dimension(380,430); //Set Size Of OverPanel
	 //JPanel NewOverPanel = new JPanel(); //MOVED TO GLOBAL KEPT FOR REFERENCE
	 NewOverPanel.setPreferredSize(OverPanelSize);
	 NewOverPanel.setVisible(true); //Set Overpanel To Size (480,400);
	 setContentPane(NewOverPanel); //Create This Windows As The OverPanel
	
		  TopMenuBar(); //Creates Top Menu Bar
			 
		  WindowContents.setSize(220,220); //SetSize of Panel
		  WindowContents.setVisible(true); //Set Panel To Visible
		  WindowContents.setLayout(new GridLayout(3, 4)); 
		  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
				Icon IconArray[] = new ImageIcon[12];
				
				for (int imagecounter = 0; imagecounter <12; imagecounter++)
				{
					IconArray[imagecounter] = new ImageIcon("bart" + imagecounter + ".jpg");
				}
				

		for(rowscounter = 0; rowscounter< rows;rowscounter++)
			{
				   for(columnscounter = 0; columnscounter < columns;columnscounter++)
				   {
					JButton Tile = new JButton(IconArray[insertcounter]);
					Tile.addActionListener(this);
					TileArray[rowscounter][columnscounter] = Tile;
					insertcounter = insertcounter + 1;
				   }
			}
			

		for(rowscounter = 0; rowscounter< rows;rowscounter++)
		  {
			   for(columnscounter = 0; columnscounter < columns;columnscounter++)
			   {
				WindowContents.add(TileArray[rowscounter][columnscounter]);
			   }
		  }

	Randomiser(); //RANDOMISER CODE TO COMPLETE

	NewOverPanel.add(WindowContents); //ADD ALL CONTENTS TO BACKGROUND PANEL
	
	TopMenuBar2(); //INSERT LOWER MENU BAR
 }
 
		 public boolean FinishedCheck()
		 {
					 if(TileArray[0][0].getIcon() == IconArray[0] && TileArray[0][1].getIcon() == IconArray[1] && TileArray[0][2].getIcon() == IconArray[2] && TileArray[0][3].getIcon() == IconArray[3] && TileArray[1][0].getIcon() == IconArray[4] && TileArray[1][1].getIcon() == IconArray[5] && TileArray[1][2].getIcon() == IconArray[6] && TileArray[1][3].getIcon() == IconArray[7] && TileArray[2][0].getIcon() == IconArray[8] && TileArray[2][1].getIcon() == IconArray[9] && TileArray[2][2].getIcon() == IconArray[10] && TileArray[2][3].getIcon() == IconArray[11])
				 {
					 result = true;
				 }
				 else
				 {
					 result = false;
				 }
				 return result;
		 }
	 
		 /**Method For Tile Swap Registers Two Temporary Variables To Clone Two Icons To Be Swapped Then Replaces & Prints A Message To Confirm Execution*/
		 public void TileSwap()
		 {
					Icon storeblankTile = new ImageIcon("bart0.jpg");	//Create An Icon Of Bart 0.jpg
					Icon storeclickedicon = new ImageIcon(clickediconreference.getIcon().toString()); //Copy The Image  From The Tile That Was Clicked
					TileArray[greyrowlocation][greycolumnlocation].setIcon(storeclickedicon); // Assign The Image Of The Tile That Was Clicked To The Reference For The Tile Where Thre Grey Box Is
					clickediconreference.setIcon(storeblankTile); //Assign The GreyBox Image To The Tile That Was Clicked
					//DEBUG CODESystem.out.println("Eligible And Executed 4");
		 }
		
		
		 /**Method To Create TopMenuBar And Panel To Place It In, Lastly Adds It To The OverPanel Container*/
				 public class Leaderboard extends JFrame
				{
					JPanel LeaderPanel = new JPanel();
					JLabel LeaderLabel = new JLabel("Username",2);
					
						public Leaderboard()
						{
						LeaderPanel.setLayout(new GridLayout(10,2));
						setTitle("Congratulations - Enter You're Score");
						setSize(320,320);
						setVisible(true);
						LeaderPanel.setVisible(true);
						LeaderPanel.add(LeaderLabel);
						setContentPane(LeaderPanel);
						}
				}


		 public void TopMenuBar2()
		 {

		 TopMenuPanel.setLayout(new GridLayout(1,3));
		 TopMenuPanel.setSize(474,50);
		 TopMenuPanel.setVisible(true);
		 
		 //JButton TopMenuButtonLeft = new JButton("Current Score = " + numberofclicks); MOVED TO GLOBAL
		 TopMenuButtonLeft.addActionListener(this);
		 //JButton TopMenuButtonMiddle = new JButton("Randomiser Button"); MOVED TO GLOBAL
		 TopMenuButtonMiddle.addActionListener(this);
		// JButton TopMenuButtonRight = new JButton("New Game"); MOVED TO GLOBAL
		 TopMenuButtonRight.addActionListener(this);
		 
		 TopMenuPanel.add(TopMenuButtonLeft);
		 TopMenuPanel.add(TopMenuButtonMiddle);
		 TopMenuPanel.add(TopMenuButtonRight);
		 
		 NewOverPanel.add(TopMenuPanel);
		 
		 }
 
					  public void TopMenuBar()
					 {

					 TopMenuPanel.setLayout(new GridLayout(1,1));
					 TopMenuPanel.setSize(474,50);
					 TopMenuPanel.setVisible(true);

					 TopMenuPanel.add(TopMenuLabelMiddle);
					 
					 NewOverPanel.add(TopMenuPanel);
					
					 }

	/**Randomiser Simulates 200 Clicks Upon The Tiles Of The Puzzle To Instigate Randomisation*/
	 public void Randomiser()
	 { 
				 for(int clickcounter = 0; clickcounter<50; clickcounter++) //50 Iterations
				 {
				 Random rand = new Random(); //Random X & Y Array Co-Ordinates
				 Random rand2 = new Random();
				 
				 int robotrandomisercolumn = rand.nextInt(3);//To Fit With Array's Length & Depth
				 int robotrandomiserrow = rand2.nextInt(2);
				 
				 TileArray[robotrandomiserrow][robotrandomisercolumn].doClick(); //Simulates A Click Upon Random Tiles
				 System.out.println("Robo Click " + clickcounter);
				 }
	 randomiserfinished = true;
	 }
	 
				public static void main(String [] args)
				 {
				PuzzleWorkingClean ShowMe = new PuzzleWorkingClean();
				ShowMe.setVisible(true);
				 }
							@Override
							public void actionPerformed(ActionEvent e) 
							{	
							clickediconreference = (JButton) e.getSource();


							for(rowscounter = 0; rowscounter< rows;rowscounter++) //IDENTIFIES GREYBOX
							  {
								   columnscounter = 0;
							       for(columnscounter = 0; columnscounter < columns;columnscounter++) //walks through whole array to find the grey tile
								   {
									    if(TileArray[rowscounter][columnscounter].getIcon().toString().compareTo(GreyBoxGlobalStorageString) == 0) //confirms by comparing strings
										{
										greyrowlocation = rowscounter; //when grey box is found stored it's row value
										greycolumnlocation = columnscounter; //when grey box is found do a flash copy of it's column location
												break;		
										}
								   }
							 }

							 for(rowscounter = 0; rowscounter < rows; rowscounter++) //IDENTIFIES CLICKED BOX
							 {
							 columnscounter = 0;
										 for(columnscounter = 0; columnscounter < columns; columnscounter++)
										 {
											    if(TileArray[rowscounter][columnscounter].getIcon().toString().compareTo(clickediconreference.getIcon().toString()) == 0)
												{
													clickedrowlocation = rowscounter;
													clickedcolumnlocation = columnscounter;
													break;
												}
										 }
							 }
							 
							 
							if(clickedcolumnlocation == greycolumnlocation) //Evaluates For Eligiblity Of Tile
							{
									if((clickedrowlocation + 1 == greyrowlocation))
									{
										TileSwap();
										numberofclicks++;
									}
									
									if((clickedrowlocation - 1 == greyrowlocation)) //Evaluates For Eligiblity Of Tile
										{
										TileSwap();
										numberofclicks++;
										}
							}
									
									if(clickedrowlocation == greyrowlocation) //Evaluates For Eligiblity Of Tile
									{
									    if((clickedcolumnlocation + 1 == greycolumnlocation)) 
										{
											TileSwap();
											numberofclicks++;
										}
									
										if((clickedrowlocation == greyrowlocation && clickedcolumnlocation - 1 == greycolumnlocation)) //Evaluates For Eligiblity Of Tile
										{
											TileSwap();
											numberofclicks++;
										}			
									} 
									
									if(randomiserfinished == true)
									{
										System.out.println("Entered Validation Loop");
										if(FinishedCheck() == true)
										{
										System.out.println("Done Fam The Ting Say - " );
										}
										
									}	
										
													
										if(e.getSource() == TopMenuButtonLeft)
										{
										
											//NO PARTICULAR FUNCTION NEEDED IF SCORE BUTTON IS CLICKED
										}
										
										if(e.getSource() == TopMenuButtonMiddle)
										{
										
											//Programmable Button
										}
										
										if(e.getSource() == TopMenuButtonRight)
										{
											//Programmable Button
										}
										if(randomiserfinished == false)
										{
										numberofclicks = 0;
										}
											TopMenuLabelMiddle.setText("Current Score = " + numberofclicks);
											
											if(FinishedCheck() == true)
											{
												dispose();
											}
								}//END ACTION EVENT

						}//CLOSE PROGRAM
