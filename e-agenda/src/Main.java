import java.awt.Button;
import java.awt.Font;
import java.awt.Label;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.TextField;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.Event;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Main extends JFrame {	
	private String informations[];
	private Label lname;
	private Label lsurname;
	private Label ltelephone;
	private Label ldate;
	private TextField tname;
	private TextField tsurname;
	private TextField ttelephone;
	private TextField tdate;	
	private TextField updatetext;
	Button clear;
	
	MenuBar bar;
	 Menu contacts,Dates,Help;
	 MenuItem itemContacts[],itemDates[],itemHelp[];
	 public static void main(String[] args) {
		 	Main m=new Main();
		 	m.show();
	}
	Main() {			
		setTitle("e-agenda");		
		setSize(500, 500);		
		setVisible(true);		
		itemContacts=new MenuItem[6];	
		itemDates=new MenuItem[4];
		itemHelp=new MenuItem[2];
		itemContacts[0]=new MenuItem("New");
		itemContacts[1]=new MenuItem("Load");
		itemContacts[2]=new MenuItem("Save");
		itemContacts[3]=new MenuItem("Delete");
		itemContacts[4]=new MenuItem("Update");			
		
		itemDates[0]=new MenuItem("New Date"); 
		itemDates[1]=new MenuItem("Load Date");
		itemDates[2]=new MenuItem("Save Date");
		itemDates[3]=new MenuItem("Cancel Date");
		itemHelp[0]=new MenuItem("Info");
		itemHelp[1]=new MenuItem("About");
		
		contacts=new Menu("CONTACTS");
		Dates=new Menu("DATES");
		Help=new Menu("HELP");
		for(int i=0; i<5; i++) {			
			contacts.add(itemContacts[i]);			
		}
		for (int i=0; i<4; i++)		{
			Dates.add(itemDates[i]);			
		}
		for (int i=0; i<2; i++)		{
			Help.add(itemHelp[i]);
		}
		bar=new MenuBar();
		bar.add(contacts);
		bar.add(Dates);
		bar.add(Help);
		setMenuBar(bar);
		
	}	
	
	public boolean action(Event event, Object arg) {
		
		if(event.target instanceof MenuItem)
	{	
			
			String text=(String)arg;			
			if (text.equals("New")) {
				setLayout(null);
				/*Label Area-----------------------------------*/
				lname=new Label("Name:");
				lname.setFont(new Font("Times New Romans",Font.BOLD,12));
				lname.setBounds(7, 20, 60, 20);
				add(lname);
				
				lsurname=new Label("Surname:");	
				lsurname.setFont(new Font("Times New Romans",Font.BOLD,12));
				lsurname.setBounds(7, 40, 60, 20); 
				add(lsurname);
				
				ltelephone=new Label("Telephone:");	
				ltelephone.setFont(new Font("Times New Romans",Font.BOLD,12));
				ltelephone.setBounds(7, 60, 60, 20);
				add(ltelephone);				
				/*Text area-------------------------------------*/
				tname=new TextField("",20);
				tname.setBounds(90, 20, 150, 20);
				add(tname);
				
				tsurname=new TextField("",20);
				tsurname.setBounds(90, 40, 150, 20);
				add(tsurname);
								
				ttelephone=new TextField("",20);
				ttelephone.setBounds(90, 60, 150, 20);
				add(ttelephone);
				
			}
			if (text.equals("Load")) {		
				String buffer="";
		           try{		        	   
		        	    FileReader inFile = new FileReader("Contacts.txt");
	                    BufferedReader inStream = new BufferedReader(inFile);	                    
	                    while(true)
	                    {
	                        int ch=inFile.read();
	                        if(ch==-1) break;
	                        buffer=buffer+(char)ch;
	                     }
	                     JOptionPane.showMessageDialog(this,buffer);	                   
	                    inStream.close();            
		            }catch(IOException e)
		            {
		                System.out.println("Error"+e.getMessage());
		            }
			}
			
			if(text.equals("Save")) {				
				try
		        {					
					ArrayList<String> contactsinformations=new ArrayList<>();				
					contactsinformations.add(tname.getText());
					contactsinformations.add(tsurname.getText());
					contactsinformations.add(ttelephone.getText());					
					FileWriter outFile = new FileWriter("Contacts.txt",true);
	                BufferedWriter outStream = new BufferedWriter(outFile);		              
	                for (int i = 0; i < contactsinformations.size(); i++) {
	                    outStream.write(contactsinformations.get(i));
	                    outStream.newLine();
	                }
	                JOptionPane.showMessageDialog(this,"Data saved.");	
	                outStream.close();
			     }
				catch(IOException e) {
					System.out.println("ERROR IN FILE");
				}
				
			}			
			if(text.equals("Delete")) {					
				int dialogResult = JOptionPane.showConfirmDialog(null,"Are you sure?");
				if(dialogResult==JOptionPane.YES_OPTION) {
					try{							
	                   	
						FileReader inFile = new FileReader("Contacts.txt");
						Scanner in =new Scanner("Contacts.txt");
	                    FileWriter f=new FileWriter("Contacts.txt");
	                    BufferedWriter ff=new BufferedWriter(f);	                   
	                    String item=JOptionPane.showInputDialog(null,"which contact want to delete?");
	                    ArrayList<String> deleteitems=new ArrayList<String>();
 						deleteitems.add(tname.getText());
 						deleteitems.add(tsurname.getText());
 						deleteitems.add(ttelephone.getText());  
 						for(int i=0; i<deleteitems.size(); i++) {
 							ff.write(deleteitems.get(i));
 							ff.newLine();
 						}
 						while(in.hasNextLine()) {
 							String line=in.nextLine(); 							
	 							if (line.equals(item)) {								
		 							deleteitems.remove(item);  								
		 							} 
	 							else {}
	 							} 
 						in.close();
 						ff.close(); 									
	                    	JOptionPane.showMessageDialog(this,"Contact deleted");	                    	
		            }catch(IOException e)
		            {
		                System.out.println("Error "+e.getMessage());
		            }				
				}  
				else if(dialogResult==JOptionPane.NO_OPTION) {
					JOptionPane.showMessageDialog(this,"No contacts deleted");
				}
				else if (dialogResult==JOptionPane.CANCEL_OPTION) {
					JOptionPane.showMessageDialog(this,"Your choice canceled");
				}
				return true;
			}
			
			if(text.equals("Update")) {					
				int dialogResult = JOptionPane.showConfirmDialog(null, "Would you like to update your contact?");				
				if (dialogResult == JOptionPane.YES_OPTION) {
									
			           try{				        	   
			        	    FileReader inFile = new FileReader("Contacts.txt");
			        	    Scanner in =new Scanner(inFile);
			        	    FileWriter g=new FileWriter("Contacts.txt");
			        	    BufferedWriter gg=new BufferedWriter(g);	
		                    String uitem=JOptionPane.showInputDialog(this,"Which contact want to update?");
		                    ArrayList<String> updateitems=new ArrayList<String>();
		                    updateitems.add(tname.getText());
		                    updateitems.add(tsurname.getText());
		                    updateitems.add(ttelephone.getText());
		                    for(int i=0; i<updateitems.size(); i++) {
	 							gg.write(updateitems.get(i));
	 							gg.newLine();
		 							if (updateitems.get(i).equals(uitem)) {								
			 							updateitems.set(i,uitem);  								
			 							}
		 							} 
	 						in.close();
	 						gg.close(); 						
		                    	JOptionPane.showMessageDialog(this,"Item updated.");
		                    
			            }catch(IOException e)
			            {
			                System.out.println("Error "+e.getMessage());
			            }
					
				}
				else if  (dialogResult == JOptionPane.NO_OPTION)
					 {
						JOptionPane.showMessageDialog(null,"Contacts has not been updated");
					 }
				else if (dialogResult==JOptionPane.CANCEL_OPTION) 
				{JOptionPane.showMessageDialog(null, "You have choose Cancel");}				 	
			}
					 
			if(text.equals("New Date")) {
				ldate=new Label("Date:");
				ldate.setFont(new Font("Times New Romans",Font.BOLD,12));
				ldate.setBounds(7, 80, 60, 20);
				add(ldate);
				tdate=new TextField("",20);
				tdate.setBounds(90, 80, 150, 20);
				add(tdate);	
			}
			if(text.equals("Load Date")) {	
				String buffer="";
		           try{		        	   
		        	    FileReader inFile = new FileReader("Date Contacts.txt");
	                    BufferedReader inStream = new BufferedReader(inFile);
	                    while(true)
	                    {
	                        int ch=inFile.read();
	                        if(ch==-1) break;
	                        buffer=buffer+(char)ch;
	                     }
	                     JOptionPane.showMessageDialog(this,buffer);	                   
	                    inStream.close(); 

		            }catch(IOException e)
		            {
		                System.out.println("Error "+e.getMessage());
		            }

				}	
			if(text.equals("Save Date")) {
				try
		        {					
					ArrayList<String> datecontacts=new ArrayList<String>();
					datecontacts.add(tdate.getText());
					FileOutputStream outFile = new FileOutputStream("Date Contacts.txt",true);
	                DataOutputStream outStream = new DataOutputStream(outFile);
	                for (int i=0; i<datecontacts.size();i++)
	                	outStream.writeBytes(datecontacts.get(i)+"\n");                    
	                    outStream.close();
		          }
				catch(IOException e) {
					System.out.println("WARNING! - ERROR");
				}
				JOptionPane.showMessageDialog(this,"Data saved");
						}
			if(text.equals("Cancel Date")) {				
				try{
					FileWriter outFile = new FileWriter("Date Contacts.txt");
	                BufferedWriter outStream = new BufferedWriter(outFile);	                
	                ArrayList<String> deletedcontacts=new ArrayList<>();
	                deletedcontacts.remove(tname);
	                deletedcontacts.remove(tsurname);
	                deletedcontacts.remove(ttelephone);
	                for (int i=0; i<deletedcontacts.size();i++)
	                	outStream.write(deletedcontacts.get(i));	 
	                    outStream.close();
	                JOptionPane.showMessageDialog(this,"Contact, cleared!");
		            }catch(IOException e)
		            {
		                System.out.println("Error "+e.getMessage());
		            }
    			}			
			if(text.equals("Info")) {
				JOptionPane.showMessageDialog(null,"INFORMATIONS!:"+ "\n1.New contact\n"
						+ "2.Save your contacts by different names\n3.Show your contacts\n4.Choose the contact want to be saved and delete the rest\n5.Update contact");
			}
			if(text.equals("About")) {
				JOptionPane.showMessageDialog(null, "Programmer designer: Konstantinos Iakovou"+"\nAM: 13593"+"\nCreated in 19/10/2018");
			}
			
	}
		
		else
			super.action(event, arg);
		return true;	
		}	
	class Contacts { 
		private String name; String surname; String telephone;
		public Contacts(String n, String s, String t) {			
			name=n;
			surname=s;
			telephone=s;			
		}			
		public String getname() {
			return name;
		}
	    public String getsurname()
		    {
		        return surname;
		    }
	    public String gettelephone() {
	    	return telephone;
	    }
	    public String toString()
	    {
	        return name+""+surname+""+telephone;
	    }

	}	
	class Dates {
		private String date;
		public Dates(String d) {
			date=d;			
		}	
		public String getdate() {
			return date;
		}
		public String toString()
	    {
	        return date;
	    }

	}
	}