import java.util.ArrayList;
import java.util.List;
 
import org.junit.Test;
 
/**
 * ���� �������, �������������� ���������� �������.
 * 
 * ������ ������� ���������� ������ �������� � �� ������� �� �����?
 * ��� ��� ����� ���������?
 * 
 * ���������� ���������������� ��������� � yield().
 * ���������� �� ��������?
 */
public class AtomicCounterTutorVer2 {
	int  counter=0;
 
	
	class TestThread implements Runnable {
		String threadName;
		
		public TestThread(String threadName) {
			this.threadName = threadName;
		}
		
		@Override
		public void run() {
			for (int i=0;i<10000;i++) {
				counter++;
				Thread.yield();
			}
			
		}
	}
	
	@Test
	public void testThread() {
		List<Thread> threads = new ArrayList<Thread>();
		for (int i=0;i<100;i++) {
			threads.add(new Thread(new TestThread("t"+i)));
		}
	    System.out.println("Starting threads");
	    try {
	    
	    	for (int i=0;i<100;i++) {
	    		if(i!=0){
	    			threads.get(i-1).join();	    			
	    		}
	    		threads.get(i).start();
	    	}
	    	
	    	threads.get(threads.size()-1).join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	    System.out.println("Counter="+counter);
 
	}
 
}