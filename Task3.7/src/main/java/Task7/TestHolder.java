package Task7;

public class TestHolder {
    public static void main(String[] args) {

    }

    @BeforeSuite
    public void prepareTest() {
        System.out.println("Called to Prepare test data");
    }

    @AfterSuite
    public void clearTest() {
        System.out.println("Called to Clear test data");
    }

    @Test(1)
    public void testCasePr1_1() {
        System.out.println("Called: Test case with Priority 1 (1)");
    }

    @Test(1)
    public void testCasePr1_2() {
        System.out.println("Called: Test case with Priority 1 (2)");
    }

    @Test()
    public void testCasePrDefault() {
        System.out.println("Called: Test case with default Priority (5)");
    }

    public void testCase() {
        System.out.println("Test case with no annotation");
    }

    @Test(9)
    public void testCasePr9() {
        System.out.println("Called: Test case with Priority 9");
    }

    @Test(10)
    public void testCasePr1_10() {
        System.out.println("Called: Test case with Priority 10");
    }

}
