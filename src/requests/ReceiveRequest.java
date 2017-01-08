package requests;

import java.io.*;

public class ReceiveRequest extends Request {

	private static final long serialVersionUID = 1L;
	
	private String path;
	private String name;

	public ReceiveRequest(String ip, String name){
		super(ip);
		this.name = name;
	}//constructor

	public void run(){
		File file;
		byte[] bytes;
		FileInputStream fis;
		BufferedInputStream bis;
		String loc = path+"/"+name;
		
		//assign the file to the file at the location
		//create a byte array to the size of the file
		file = new File(loc);
		bytes = new byte[(int) file.length()];
		
		try{
			/*	1	create a file output stream to write to the file represented by the File object.
			 * 	2	add ability to buffer the input and to support the mark and reset methods. 
			 * 	3	Reads bytes from this byte-input stream into the specified byte array.
			 */	
			fis = new FileInputStream(file);		//	1
			bis = new BufferedInputStream(fis);		//	2
			bis.read(bytes, 0, bytes.length);		//	3
			
			super.streamSetup(bytes);
			
			bis.close();//free up resources
			
		}catch(Exception e){
			e.printStackTrace();
		}//try catch
		
	}//run

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		String message;
		message = "[ ReceiveRequest | " +super.getClient() + " | " + super.getDate().toString() +" ]";
		return message;
	}//to string
	
}
