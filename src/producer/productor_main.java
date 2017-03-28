package producer;

public class productor_main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		producer_thread th1 = new producer_thread( "hilo 1" );
		
		th1.start();

		producer_thread th2 = new producer_thread( "hilo 2" );
		th2.start();
		
		th2.getClass();

	}

}
