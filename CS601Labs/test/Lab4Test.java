import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({SimpleYelpStoreTest.class, ComplexYelpStoreTest.class, ConcurrentTest.class, ClientTest.class})
public class Lab4Test {
	/*
	 * To be eligible for code review for lab 1, you must pass
	 * this test suite on the lab computers.
	 *
	 */
}