This is for examining TESTNG test case setup.

For reference use ZipLimiterTestv5
ZipLimiterTestv5 -uses a DateProvider class to return Object[] representing rows.
ZipLimiterTestv4 -uses a DateProvider class skip 1st row containing the column info but mapping is still done manually
ZipLimiterTestv3 -uses csv fle for input/output and local DateProvider and hardcodes reading from DateProvider row count
ZipLimiterTestv2 -uses separate input/output text files.
ZipLimiterTest   -uses setup in the test class itself