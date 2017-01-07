package client;

import java.io.*;
import java.net.*;
import java.util.*;

import client.config.*;
import requests.*;

public class ClientRunner {
	
	
	public static void main(String[]args) throws Throwable{
		Conf conf = new Conf();
		UI ui = new UI();
		XMLParse parser = new XMLParse(conf);
		parser.parse();
		System.out.println(conf);		
		int choice;
		
		Scanner in = new Scanner(System.in);

		
		while(ui.isActive()){
			ui.menu();	
			choice = in.nextInt();
			
			switch(choice){
			case 1:
				//connect to server
				break;
			case 2:
				//file list from server
				break;
			case 3:
				// get from server
				break;
			case 4:
				//quit
				ui.quit();
				break;
			}//switch
			
		}//while the ui is active
		
		System.out.println("Goodbye :)");
		in.close();
	}//main
	
	
}//class


