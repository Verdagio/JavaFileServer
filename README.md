# JavaFileServer
3rd year under-grad Object Oriented project
#### How to: 

To run the server open eclipse and run the 'ServerRunner' class. In the console you will be prompted to enter the file path from where the files reside. (The files are contained within the /res directory).

Once you have done this it will return a message in the console informing you of the port which it is running on.

You can now open a instance of the command line. Navigate to where the 'oop.jar' file resides. Once you are there type the following into the command line:
java -jar oop.jar

### What it does:

Once the above is carried out the command line should now display the menu with the following options:

	1 - Connect to the server
	2 - List all files on the server
	3 - Download a file
	4 - Send a file to the server
	5 - Quit

1 - Connect to the server

This will connect the Client to the server on the port, using a connection request.

2 - List all files on the server

This will make a request to the server that will print out each file within' the /res directory. 

3 - Download a file

This will make a request to the server that will prompt the user to input the directory where you would like the file to be downloaded to. Once this is input, the files on the server will be listed and the user prompted to type the name of the file they wish to download.

4 - Send a file to the server

This method is not working, but the aim was to have the user input where the file they wished to upload was, then input the file name and then send this to the /res folder on the server. 

5 - Quit

This method would send a poison request to the server... The request would then safely stop the server.. Unfortunately it just causes the server to crash at the moment (YIKES! :o).

### Design:

When building this application, I had hoped to keep an object oriented design. This would mean keeping Abstraction, Encapsulation, Composition, Inheritance, & polymorphism at the forefront when choosing how the structure of the files, & classes, methods etc.

Within' the /src directory of the project there are 3 packages:

	1 client
		1.a client.configurations
	2 request
	3 server

Each package contains the relevant logic to enable the program to carry out the operations that are listed above.






