package producer;

public class productor_main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
//		String[] nombres_empresas = {"BBVA"};
		String[] nombres_empresas = {"BBVA","Vodafone","Movistar","Apple","Dell","Fontbella","GSK","Adidas","Nike","Reebook"};
		
		
		for (String topic : nombres_empresas) {
			
			producer_thread t = new producer_thread(topic.toUpperCase());
			t.start();
			
		} 


	}

}
