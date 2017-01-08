package client;

import java.util.*;
import client.config.*;


public class ClientRunner {
	
	
	public static void main(String[]args) throws Throwable{
		Conf conf = new Conf();
		UI ui = new UI();
		XMLParse parser = new XMLParse(conf);
		parser.parse();
		System.out.println(conf);		
		int choice;
		FileOperations fo = new FileOperations(conf);
		Scanner in = new Scanner(System.in);

		
		while(ui.isActive()){
			ui.menu();	
			choice = in.nextInt();
			
			switch(choice){
			case 1:
				//connect to server
				ui.connecting();
				fo.connect();
				break;
			case 2:
				//file list from server
				ui.fileList();
				fo.fileList();
				break;
			case 3:
				// get from server
				ui.getFrom();
				fo.recieveFIle();
				break;
			case 4:
				//send to server
				ui.sendTo();
				String file = in.nextLine();
				fo.sendFile(file);
				break;
			case 5:
				//quit
				fo.quit();
				ui.quit();
				break;
			}//switch
			
		}//while the ui is active
		
		System.out.println("Goodbye :)");
		in.close();
	}//main
	
	
}//class


