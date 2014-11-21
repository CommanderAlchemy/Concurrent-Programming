package main;

/**
 * CharacterBuffer, a simple char buffer that is a shared resource for the: bounded-buffer problem
 * Contains both synchronized and regular methods to display the problem.
 * @author Artur O.
 */
public class CharacterBuffer {
	private char buffer;
	private boolean isFull = false; 
	
	/**
	 * Write character to buffer:
	 * <br>
	 * <ul>
	 * 		<li>Writing to buffer toggles isFull!</li>
	 * 		<li><b>NOT synchronized</b></li>
	 * </ul>
	 * @param c	char
	 */
	public void writeBuffer(char c){
		System.out.println("[ ]Writer: Writing buffer[" + c + "]");
		this.buffer = c;
		this.isFull = true;
	}
	
	/**
	 * Write character to buffer:
	 * <br>
	 * <ul>
	 * 		<li>Writing to buffer toggles isFull!</li>
	 * 		<li><b>synchronized</b></li>
	 * </ul>
	 * @param c	char
	 * @throws InterruptedException 
	 */
	public synchronized void writeBufferSync(char c) throws InterruptedException{
		while(isFull)
			wait();
		
		System.out.println("[S]Writer: Writing buffer[" + c + "]");
		buffer = c;
		isFull = true;
		notify();
	}
	
	/**
	 * Read character from buffer.
	 * <br>
	 * <ul>
	 * 		<li>Reading from buffer toggles isFull!</li>
	 * 		<li><b>NOT synchronized</b></li>
	 * </ul>
	 * @return char c
	 */
	public char readBuffer(){
		this.isFull = false;
		System.out.println("[ ]Reader: Reading buffer[" + buffer + "]");
		return buffer;
	}
	
	/**
	 * Read character from buffer.
	 * <br>
	 * <ul>
	 * 		<li><b>synchronized</b></li>
	 * </ul>
	 * @return char c
	 * @throws InterruptedException 
	 */
	public synchronized char readBufferSync() throws InterruptedException{
		while(!isFull)
			wait();
		
		this.isFull = false;
		notify();
		System.out.println("[S]Reader: Reading buffer[" + buffer + "]");
		return buffer;
	}
	
	/**
	 * Returns true if the buffer is full.
	 * <br>
	 * <ul>
	 * 		<li><b>NOT synchronized</b></li>
	 * </ul>
	 * @return boolean
	 */
	public boolean isFull() {
		return isFull;
	}
	
	/**
	 * Returns true if the buffer is full.
	 * <br>
	 * <ul>
	 * 		<li><b>synchronized</b></li>
	 * </ul>
	 * @return boolean
	 * @throws InterruptedException 
	 */
	public synchronized boolean isFullSync() throws InterruptedException {
		return isFull;
	}
 
}