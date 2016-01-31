# DataComparisonTool
Comparison tool that compares mapped columns for two data files.  There is also ability to do level comparisons. 

##Features

Scenario: If columnA1 doesn't match with columnB6, then go compare with columnB10 if levels are set.
  * `'Given'`: that file1_columnA1 is compared to file2_columnB6
  * `'And'`: they do not match
  * `'When'`: I run a comparison for file1_columnA1 and file2_columnB6.
  * `'That'`: another comparison with file1_columnA1 and file2_columnB10 will be made.
  
Scenario: If a number comparison is made is is within threshold (0.01)
  * `'Given'`: that file1_columnA1 (123.235) is compared to file2_columnB6 (123.240)
  * `'When'`: I run a comparison for file1_columnA1 and file2_columnB6.
  * `'That'`: they should be considered a match

##To Do
  * `'Finish status printer'` - As a tool user, I want mis-matches out, so that I can investigate why there are data mismatches
  * `'Finish UI'` - As a tool user, I want to have a UI interface, so that I can select data comparison inputs and save comparison outputs
  * `'Create map'` creator feature - As a tool user, I want a map creator interface, so that I don't have to manually create one
  * `'Create history report comparer'` - As a tool user, I want to history comparison, so that I can keep track of common mismatch trends and link them with common issues to minimize investigation time
  
##Design

  