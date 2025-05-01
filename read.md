This is for examining TESTNG test case setup.

For reference use ZipLimiterTestv5
ZipLimiterTestv5 -uses a DateProvider class to return Object[] representing rows. Column mapping is automatic. Utility class ZipPairDRO not used.
ZipLimiterTestv4 -uses a DateProvider class skip 1st row containing the column info but column mapping is still done manually
ZipLimiterTestv3 -uses csv fle for input with a local DateProvider and hardcodes reading from DateProvider columns.
ZipLimiterTestv2 -uses separate input text files with a file Reader Class (ZipFileReaderUtil).
ZipLimiterTest   -uses setup in the test class itself

XML file suite definition adds ability to trigger automatically.

References used in this project 
https://www.baeldung.com/junit-vs-testng
https://stackoverflow.com/questions/30624727/what-is-the-fastest-way-to-get-dimensions-of-a-csv-file-in-java
https://www.baeldung.com/testng

Annotations used in this project @BeforeMethod, @Test, @AfterMethod, @BeforeClass, @AfterClass, @DataProvider
TestNG can Run test suite via xml (Not covered)
Parameterized Tests Use @DataProvider or @Parameters
Dependent Tests (Not covered) @Test(dependsOnMethods = {"givenEmail_ifValid_thenTrue"})
