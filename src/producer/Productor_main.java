package producer;

public class Productor_main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
//		String[] nombres_empresas = {"BBVA"};
		String[] nombres_empresas = {"BBVA","Vodafone","Movistar","Apple","Dell","Fontbella","GSK","Adidas","Nike","Reebook"};
		
		
		for (String topic : nombres_empresas) {
			
			Producer_thread t = new Producer_thread(topic.toUpperCase());
			t.start();
			
		} 


	}

}
