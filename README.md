# DataComparisonTool
Comparison tool that compares mapped columns for two data files.  There is also ability to do level comparisons. 

##Features

Scenario: Matching on levels of column attributes if the first level fails.
  * `'Given'`: that file1_columnA1 is to be compared to file2_columnB6 or file2_columnB7
  * `'And'`: file1_columnA2 has value ABC or DEF to do comparisons to file2_columnB6 or file2_columnB7
  * `'When'`: I run a comparison and file1_columnA2 is ABC
  * `'Then'`: file1_columnA1 will be compared against file2_columnB6
  * `'When'`: I run a comparison and file1_columnA2 is DEF
  * `'Then'`: file1_columnA1 will be compared against file2_columnB7
  
Scenario: Giving positives matches if values are within a given threshold.
  * `'Given'`: that file1_columnA1 (123.235) is compared to file2_columnB6 (123.240)
  * `'When'`: I run a comparison for file1_columnA1 and file2_columnB6.
  * `'Then'`: they should be considered a match

##To Do
  * ~~`'Comparison using key'` - As a tool user, I want to do comparison against columns from product and the source-data matching on a key, so that I can lower the frequency of false positives.~~
  * ~~`'Comparison based on column values'` - As a tool user, I want to do comparisons using a mapping column, so that I can do comparison depending on the value status.~~
  * ~~`'Finish status printer'` - As a tool user, I want mis-matches output some how, so that I can visually see mis-matches and investigate why there are they occurred.~~
  * ~~`'Finish UI'` - As a tool user, I want to have a UI interface, so that I can select data comparison inputs and save comparison outputs.~~
  * `'Executable jar'` - As a tool user, I want to open the tool by an executable, so that I dont require and IDE to run it.
  * `'Create map'` creator feature - As a tool user, I want a map creator interface, so that I don't have to manually create one
  * `'Optimize comparison performance'` - As a tool user, I want the comparisons completion time to be XXX, so that I can get results back quicker 
  * `'Create history report comparer'` - As a tool user, I want to history comparison, so that I can keep track of common mismatch trends and link them with common issues to minimize investigation time
  * `'Validate map file'` creator feature - As a tool user, I want to validate my map file, so that I can ensure any errors cause will be through other sources than the map file
  * `'Create web-service'`As a tool user, I want to use tool without having to have copy of tool on my computer.
  
##Design
Current comparisons are done by brute force approach.  This can be sped up by storing data in extra data structures to speed up look up time.  Will need to get 'real' test data before doing this implementation.

  
