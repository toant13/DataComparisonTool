# DataComparisonTool
Comparison tool that compares mapped columns for two data files.  There is also ability to do level comparisons. 

###Scenario: If columnA1 doesn't match with columnB6, then go compare with columnB10 if levels are set.
Given that file1_columnA1 is compared to file2_columnB6.
And they do not match.
When I run a comparison for file1_columnA1 and file2_columnB6.
Then another comparison with file1_columnA1 and file2_columnB10 will be made.
