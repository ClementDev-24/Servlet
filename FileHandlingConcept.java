package com.pailagam;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class FileHandlingConcept {
	
	static String Extension;
	public static void main(String[] args) {
	FilterUsingExtension();
	}

	public static void CreateFolder() {
		File f = new File("C:\\Users\\P3INDW23\\Desktop\\new");
		/*
		 * use double\\ in the path because when single \ exists some times java takes
		 * it as escape character \n - new line , \t - 5 tab space
		 */
		boolean pres = f.exists();
		System.out.println("Folder is present " + pres);
		if (pres == false) {
			f.mkdir(); // used to create the folder
			boolean press = f.exists(); // used to check whether the folder or file exists
			if (press == true) {
				System.out.println("The folder is created");
			} else {
				System.out.println("The folder is not created");
			}
		}
		else {
			System.out.println("Folder is present " + pres);
		}
	}

	public static void SubFolder() {
		File f = new File("C:\\Users\\P3INDW23\\Desktop\\new\\hello\\bye");
		boolean pres = f.exists();
		System.out.println("Folder is present " + pres);
		
		if (pres == false) {
			f.mkdirs(); // used to create the sub directories
			boolean press = f.exists(); // used to check whether the folder or file exists
			if (press == true) {
				System.out.println("The folder is created");
			} else {
				System.out.println("The folder is not created");
			}
		} else {
			System.out.println("Folder is present " + pres);
		}
	}

	public static void FileCreate() {
		File f = new File("C:\\Users\\P3INDW23\\Desktop\\new\\hello\\bye\\bye.txt");
		/*
		 * use double\\ in the path because when single \ exists some times java takes
		 * it as escape character \n - new line , \t - 5 tab space
		 */
		boolean pres = f.exists();
		if (pres == false) {
			try {
				f.createNewFile(); // used to create new file
				boolean press = f.exists(); // used to check whether the folder or file exists
				if (press == true) {
					System.out.println("The file is created");
				} else {
					System.out.println("The file is not created");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			System.out.println("File is present " + pres);
		}
	}

	public static void FileDelete() {
		File f = new File("C:\\Users\\P3INDW23\\Desktop\\new\\hello\\bye\\bye.txt");
		boolean pres = f.exists();
		if (pres == true) {
			f.delete(); // used to create new file
			boolean press = f.exists(); // used to check whether the folder or file exists
			if (press == false) {
				System.out.println("The file is Deleted");
			} else {
				System.out.println("The file is not created");
			}
		} 
		else {
			System.out.println("File is present" + pres);
		}
	}
	
	public static void FileRename() {
		File f = new File("C:\\Users\\P3INDW23\\Desktop\\new\\hello\\bye.txt");
		
		boolean pres = f.exists();
		if (pres == false) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("There is an error in file creation");
			}
			File f1 = new File("C:\\Users\\P3INDW23\\Desktop\\new\\hello\\hello.txt");// rename the file with hello from bye
			boolean rename = f.renameTo(f1);// use to rename from old name to new name this returns boolean
			if(rename==true) {
			System.out.println("The rename is successfully done ");
			}
			else {
				System.out.println("The rename is not done");
			}
	}
		else {
			System.out.println("The file is present "+ pres);
		}
}
	
	public static void PrintingFileName() {
		File f1 = new File("C:\\Users\\P3INDW23\\Desktop\\new\\hello\\hello.txt");
		System.out.println("The file name is "+ f1.getName());
		System.out.println(f1.canExecute()); // Checks whether the file can be executed
		System.out.println(f1.canRead()); // Checks whether the file can be readed
		System.out.println("Can write the file "+f1.canWrite()); // when there is read only file then it becomes false
	}
	public static void ListingFilesFoldersInDirectory() {
		// printing all the folders and files
		Scanner sc = new Scanner(System.in);
		File f = new File("C:\\Users\\P3INDW23\\Desktop");
		String data[] = f.list();//list returns in string array
		// using for loop
		/*for (int i = 0; i < data.length; i++) {
			System.out.println(data[i]);
		}*/
		// Using for each
		for(String a : data) {
			System.out.println(a);
		}
		System.out.println("=============================================================");
		
		// listing the files and folder with the path
		File[] data1 = f.listFiles(); // File is used because the return type is File[] 
		// list of file gives the path of the file 
		for(File f1 : data1) {
			System.out.println(f1); // this prints all the folders and file
			System.out.println("Please choose your preference \n 1. To show the file \n 2. To show the folder");
			int num = sc.nextInt();
			switch (num) {
			case 1: 
				for(File f2 : data1) {
					if(f2.isFile())
					System.out.println(f2); // this prints all the file
				}
				break;
			
			case 2:
				for(File f3 : data1) {
					if(f3.isDirectory())
					System.out.println(f3); // this prints all the folders
				}
				break;
			
			default:
				throw new IllegalArgumentException("Unexpected value: " + num);
			}
				 
		}
	}
	// used to get only the specific type of file
	public static void FilterUsingExtension()
	{
		File f = new File("C:\\Users\\P3INDW23\\Desktop");
		File[] folder=f.listFiles();
		for(File f1 : folder) {
			if(f1.isFile()) {
				String fileName = f1.getName();// gives the filename
				int dot = fileName.lastIndexOf(".");// last dot of the file name then the extension starts
				String Extension=fileName.substring(dot+1);// The dot has the int index value so we use +1 to skip the dot and saving the return type in string
				if(Extension.equals("txt")) {// filter the extension and showing the size of the file
					System.out.println(fileName+" Size: "+f1.length());// helps to list only txt file
				}
			}
		}
		System.out.println("Printing all  the files above 5MB");
		for(File f2: folder) {// Used to print the 
			if (f2.isFile()) {
				String fileName = f2.getName();// gives the filename
				if(f2.length()>5000000) {//this is used to return all the file above 5mb 
					System.out.println(fileName+" Size: "+f2.length());// helps to list only txt file			
				}
			}
		}
	   System.out.println("Deleting the file according to the extension");
	   for(File f3:folder) {
		   String f3name = f3.getName();
		   int doot = f3name.lastIndexOf(".");// getting the index value of .
		   String Exten = f3name.substring(doot+1);// doot+1 is used to Get after the . in the file name
	       if(Exten.equals("java")) {
	    	   f3.delete();
	    	   
	       }
	   }
	}
	public static void fileWritingReader() {
		File file = new File("C:\\Users\\P3INDW23\\Desktop\\new\\hello.txt");
		try {
			file.createNewFile();// used to create the file
		//	FileWriter fw = new FileWriter("C:\\Users\\P3INDW23\\Desktop\\new\\hello.txt"); we are giving the address in the constructor
			FileWriter fw =new FileWriter(file);// using the file variable to give the address to the writer
			fw.write(65);//prints the hashkey value ex: this prints A
			fw.write("hi How are you");
			fw.flush();// flush is used to empty the writer
			fw.close();
			System.out.println();
			System.out.println("File reading");
			System.out.println("==============================");
			System.out.println();
			FileReader fr =new FileReader(file);
			int output = fr.read();// if the stream is empty it returns -1
			/*while(output != -1) {// printing in hash key
				System.out.println(output);
				// this prints only hashkey of the file nad not the characters
				output = fr.read();
			    // it helps to transmit to next hashkey in the file
			}*/
			while (output != -1){
				System.out.println((char)output);
				output = fr.read();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static void fileCharacterCount() {// when using character it reads by character so next buffer reader is used in next method
		File file = new File("C:\\Users\\P3INDW23\\Desktop\\new\\hello.txt");
		try {
			System.out.println("File reading");
			System.out.println("==============================");
			System.out.println();
			FileReader fr =new FileReader(file);
			char[] ch =new char[(int) file.length()]; // casting is done because length is in long  
			fr.read(ch);// Reads the character to provide the length on the above step
			
			for(char c : ch) {
				System.out.println(c);
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void bufferWriterReader() {
		File file = new File("C:\\Users\\P3INDW23\\Desktop\\new\\bye.txt");
		FileWriter fw;
		try {
			fw = new FileWriter(file);
			// BufferWriter rewrites the whole file and if the changes are made by external ways by typing in document if we run the 
			// program it deletes the whole content and saves the content which we said to write on the document
			BufferedWriter bw = new BufferedWriter(fw);//accepts an object
			 // BufferWriter cannot load the string by giving the address so the file is created and then the variable is passed inside
			System.out.println("Buffer Writer");
			bw.write("Hello");
			bw.newLine();
			bw.write("bye");
			bw.flush();
			bw.close();
			System.out.println();
			System.out.println("Buffer reader");
			System.out.println("_____________________________________________________");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);// Bufferreader accepts an object so filereader is created and the variable is passed
		    String line = br.readLine();// read line reads the full line and goes to next if there is no line present it give null
		    int linecount =0;// to find the number of line present in the file this gets incremented inside while loop
		    
		    while(line != null) {
		    	linecount++;
		    	System.out.println(line);
		    	line = br.readLine();
		    }
		    System.out.println();
		    System.out.println("Number of lines present inside the document is "+ linecount);
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
   
		
	}
	public static void bufferAppend() {
		File file = new File("C:\\Users\\P3INDW23\\Desktop\\new\\bye.txt");
		try {
			// mention true to add the txt in the document and it will not delete the existing document it just adds
			FileWriter fw = new FileWriter(file,true);
			BufferedWriter bw = new BufferedWriter(fw);// mention true to add the txt in the document and it will not delete the existing document it just adds
		    bw.newLine();
		    bw.write("kerala");
		    bw.write("Dubai");
		    bw.flush();
		    bw.close();
		    System.out.println();
			System.out.println("Buffer reader");
			System.out.println("_____________________________________________________");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String s =br.readLine();
			 String line = br.readLine();// read line reads the full line and goes to next if there is no line present it give null
			    int linecount =0;// to find the number of line present in the file this gets incremented inside while loop
			    
			    while(line != null) {
			    	linecount++;
			    	System.out.println(line);
			    	line = br.readLine();
			    }
			    System.out.println();
			    System.out.println("Number of lines present inside the document is "+ linecount);
			    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	public static void sentenceWordCount() {
		File file = new File("C:\\Users\\P3INDW23\\Desktop\\new\tata.txt");
		FileReader fr;
		try {
			fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);
			String s =br.readLine();
			 String line = br.readLine();// read line reads the full line and goes to next if there is no line present it give null
			    int linecount =0;// to find the number of line present in the file this gets incremented inside while loop
			    int sentencecount =0;// to find the sentence count and the addition is done in while loop
			    int wordcount =0;
			    int charcount = 0;
			    while(line != null) {
			    	linecount++;
			    	String[] sentence = line.split("[.]");// this splits with the help of "."
			    	sentencecount+=sentence.length;
			    	String[] word =line.split(" ");// this splits with the space
			    	wordcount+=word.length;
			    	charcount+=line.length();// the while prints in the character format so you don't need to split
			    	System.out.println(line);
			    	line = br.readLine();
			    }
			    System.out.println();
			    System.out.println("Number of lines present inside the document is "+ linecount);
			    System.out.println();
			    System.out.println("Number of sentence present inside the document is "+ sentencecount);
			    System.out.println();
			    System.out.println("Number of word present inside the document is "+ wordcount);
			    System.out.println();
			    System.out.println("Number of Character present inside the document is "+ charcount);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 public static void copyImage() {
	    	// copying the image file
		 try {
			InputStream input = new FileInputStream("C:\\Users\\P3INDW23\\Desktop\\BatchId_as_string.png");
			OutputStream output =new FileOutputStream("C:\\Users\\P3INDW23\\Desktop\\sss.png");
			int content = input.read();// this returns -1 when the file doesn't contain any more value
			while(content != -1) 
			{
				output.write(content);
				content = input.read();	
			}
			output.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }
	}
