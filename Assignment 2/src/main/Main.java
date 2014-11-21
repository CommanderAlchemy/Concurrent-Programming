package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import threads.ReaderThread;
import threads.WriterThread;

/**
 * Bounded-buffer problem.
 * @author Artur O.
 *
 */
public class Main {
	private static String readLine(){
		BufferedReader 	br 		= new BufferedReader(new InputStreamReader(System.in));
		String 			input 	= null;
		try {
			input = br.readLine();
		} catch (IOException e) {
			System.out.println("[ERROR] Could not read input");
			e.printStackTrace();
		}
		return input;
	}

	public static void main(String[] args) {
		String testcase;
		System.out.print("Text: ");
		
		for (testcase = readLine(); testcase.length() < 1; testcase = readLine())
			System.out.println("You must enter atleast a character");
		
		System.out.print("Syncronised? [Y/n]: ");
		String sync = readLine();
		
		// Run unsyncronised methods.
		CharacterBuffer buffer = new CharacterBuffer();
		if (sync.contains("n") || sync.contains("N")){
			System.out.println("Running unsync\n");
			new WriterThread(false, testcase, buffer).start();
			new ReaderThread(false, testcase, buffer).start();;
		} else {
			System.out.println("Running sync\n");
			new WriterThread(true, testcase, buffer).start();;
			new ReaderThread(true, testcase, buffer).start();
		}
	}
}
