package UserAcc;


import java.io.*;
import java.util.Scanner;

public class fileexample {
public static void main (String[] args) {
	try(FileWriter fw = new FileWriter ("transaction.txt",true);
			Scanner s = new Scanner(System.in)){
		BufferedWriter bf = new BufferedWriter(fw);
		String test = s.next();
		bf.write(test+" ");
		bf.append(test);
	}
	catch(Exception e) {
		System.out.println("error ocurred");
	}
}
}