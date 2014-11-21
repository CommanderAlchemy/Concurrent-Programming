package threads;

import java.util.Random;

import main.CharacterBuffer;

/**
 * Writer thread for the bounded-buffer problem
 * @author Artur O.
 *
 */
public class WriterThread extends Thread {
	private CharacterBuffer buffer;
	
	private Random 			rand = new Random();
	private String 			que;
	
	private boolean			isSynchronized;

	/**
	 * Const.
	 * @param isSynchronized
	 * @param str
	 * @param buffer
	 */
	public WriterThread(boolean isSynchronized, String str,  CharacterBuffer buffer) {
		super("Writer");
		this.isSynchronized = isSynchronized;
		this.que 			= str + "\n";
		this.buffer 		= buffer;
	}

	@Override
	public void run() {

		// Write to buffer until the whole String is processed.
		for (int i = 0; i < que.length(); i++) {

			// Run w/ sync.
			if (isSynchronized) {
				try {

					// Slow stuff down
					buffer.writeBufferSync(que.charAt(i));
					sleep(rand.nextInt(1000));
					

				} catch (InterruptedException e) {
					System.out.println("[S]Writer: Woops!");
					e.printStackTrace();
				}

				// Run w/o sync.
			} else {
			
				try {
					// Slow stuff down
					buffer.writeBuffer(que.charAt(i));
					sleep(rand.nextInt(1000));
					
				} catch (InterruptedException e) {
					System.out.println("[ ]Writer: Woops!");
					e.printStackTrace();
				}

			}
		}
	}
}
