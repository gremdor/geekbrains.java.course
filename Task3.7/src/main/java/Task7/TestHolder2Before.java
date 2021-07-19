package Task7;

public class TestHolder2Before {
    public static void main(String[] args) {

    }

    @BeforeSuite
    public void prepareTest1() {
        System.out.println("1. Called to Prepare test data");
    }

    @BeforeSuite
    public void prepareTest2() {
        System.out.println("2. Called to Prepare test data");
    }

    @AfterSuite
    public void clearTest() {
        System.out.println("Called to Clear test data");
    }

    @Test(1)
    public void testCasePr1_1() {
        System.out.println("Test case with Priority 1");
    }

    @Test(1)
    public void testCasePr1_2() {
        System.out.println("Test case with Priority 1");
    }

    @Test()
    public void testCasePrDefault() {
        System.out.println("Test case with default Priority (5)");
    }

    public void testCase() {
        System.out.println("Test case with no annotation");
    }

    @Test(9)
    public void testCasePr9() {
        System.out.println("Test case with Priority 9");
    }

    @Test(10)
    public void testCasePr1_10() {
        System.out.println("Test case with Priority 10");
    }

}
