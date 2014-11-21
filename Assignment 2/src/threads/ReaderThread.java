package threads;

import java.util.Random;

import main.CharacterBuffer;

/**
 * Reader thread for the bounded-buffer problem
 * @author Artur O.
 *
 */
public class ReaderThread extends Thread {
	private CharacterBuffer buffer;
	
	private Random 			rand = new Random();
	private String 			que;
	
	private boolean 		isSynchronized;
	private char[] 			tempCharArray;

	// Counter
	private int counter = 0;

	/**
	 * Const.
	 * 
	 * @param isSynchronized
	 * @param str
	 * @param buffer
	 */
	public ReaderThread(boolean isSynchronized, String str, CharacterBuffer buffer) {
		super("Reader");
		this.isSynchronized = isSynchronized;
		this.que 			= str + "\n";
		this.buffer 		= buffer;
		
		this.tempCharArray 	= new char[str.length() + 1];
	}

	private boolean checkString(int length) {
		if (que.length() == counter || tempCharArray[counter] == '\n') {
			String result = new String(tempCharArray);
			if (result.equals(que)) {
				System.out.println("---------------------------------");
				System.out.println("Success! Reader got full message.");
			} else
				System.out.println("Derp!");

			System.out.println(" Details:");
			System.out.println("\t I String: " + que.replace('\n', ' '));
			System.out.println();
			System.out.println("\t O String: " + result.replace('\n', ' '));

			return true;
		}
		return false;
	}

	@Override
	public void run() {

		// Read from buffer.
		while (true) {

			// Run w/ sync.
			if (isSynchronized) {

				try {
					sleep(rand.nextInt(1000));
					tempCharArray[counter] = buffer.readBufferSync();

					if (checkString(counter))
						break;

					counter++;
				} catch (Exception e) {
					System.out.println("[S]Reader: Woops!");
					if (checkString(counter))
						break;
				}

			// Run w/o sync.
			} else {
			
				try {
					sleep(rand.nextInt(1000));
					tempCharArray[counter] = buffer.readBuffer();
					
					if (checkString(counter))
						break;

					counter++;
				} catch (Exception e) {
					System.out.println("[ ]Reader: Woops!");
					if (checkString(counter))
						break;
				}

			}
		}
	}
}
