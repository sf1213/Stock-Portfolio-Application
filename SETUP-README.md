# Stock-Portfolio-Application


	
GUI
	
1. Open a command-prompt/terminal and navigate to the res folder
2. Type java -jar 10GUI.jar, press ENTER
3. The main interface is divided into two areas: 1, radio_button menu; 2, user display board
4. Choose one button, and input suitable texts according to the hinds. All information including results ,err message and successful message will be showed on the user display board.
5. There are two kinds of inputs: 1, Text; 2, drop_down (select one option, then hit anywhere on the panel, then a new panel will pop out for user to input.)
6. Commission fee: if there is no fee, just leave it blank.
7. All the file will be saved in the source folder.
8. The graphical image will be saved as a standard image in the source folder.









--------------------------------------------------------------------------------------------------------------------------
Console-based

1. Open a command-prompt/terminal and navigate to the res folder
2. Type java -jar 10CNSL.jar, press ENTER
3. The main menu looks like this:

Welcome to Stock Portfolio! Please choose one number : (Enter 'q' or 'Q' to quit)
1. View all Stock Portfolios
2. Examine one Stock Portfolio
3. Create Stock Portfolio
4. Buy Stock
5. Show total Cost Basis
6. Show total Value
7. Apply Strategy
8. Save Portfolio


	The first layer is Menu, choose one number to enter the second layer, if input "q" or "Q", exit portfolio.

	For menu 1,2,3 there are totally 2 layers. In the second layer input things according to the prompt, if enter "q" or "Q",  exit portfolio. (So the name of pro folio cannot be "q" or "Q")

	For menu 4,5,6 there are totally 3 layers. 
	In the second layer, it will ask user to choose one option, for example "Do you want to buy stock by: 1. Share 2. Money Amount 3. Back to Menu 4. Quit? ", 
	if user choose 1 or 2, it will enter third layer.Third layer may show user the result or tell user to invalid input, then back to second layer. 
	The third user don't have quit option, user must go back to second layer for quitting portfolio or going to menu.




	- Create Stock Portfolio: 
		1. input "3", press enter
		2. input "retirement", press enter, 
		3. Input all the pre_set Stock Symbols you want to include in this Portfolio. Press enter at each end, input "#" to end,
		   then it will prompt "Create Successfully. Back to Menu"




	- Buy Stock : 
		1. input "4", press enter, it will take you to second layer "Do you want to buy stock by: 1. Share 2. Money Amount 3. Back to Menu 4. Quit? please enter one number: "
		2. Input "1" or "2", it will take you to third layer
		3. Choose one Portfolio
		4. Choose one Stock
		5. Input Date, Share/Money Amount Commission Fee (if input in in the wrong format, it will ask you to re-enter until you get it right)
		6. If user input all things correctly, then it will tell you successfully buy the stock, show all the stocks, and back to the second layer "Buy Successfully. Back to Second level"


		

	
	- Check total Value
		1.input "6", it will take you to second layer "Which day do you want to see total value : 1. Today 2. Previous day, 3. Back to Menu 4. Quit?3. Quit? please enter one number: "
		2. Input "1" or "2", it will take you to third layer
		3. Choose one Portfolio
		4. Today will skip this step; Previous day will need to input Date (if input in in the wrong format, it will ask you to re-enter until you get it right)
		5. If user input all things correctly, it will show total value, then back to second layer


	
	- Check total Cost
		1. input "5", it will take you to second layer "Which day do you want to see total costs : 1. Today 2. Previous day, 3. Back to Menu 4. Quit?3. Quit? please enter one number: "
		2. Input "1" or "2", it will take you to third layer
		3. Choose one Portfolio
		4. Today will skip this step; Previous day will need to input Date (if input in in the wrong format, it will ask you to re-enter until you get it right)
		5. If user input all things correctly, it will show total cost, then back to second layer

	

	- View all Stock Portfolios
		1. input "1", press enter. It will show you all the Portfolios' names.Back to menu.




	- Examine one Stock Portfolio
		1. input "2", press enter. 
		2.  Choose one Portfolio， it will show you the Portfolios' content. Then back to menu.




	- Apply Strategy
		1. input "7", press enter
		2. Choose one Portfolio
		3. Choose Equal Weight, if "Y", goto step 5; if "N", goto step 4
		4. Specify weights for each stock
		5. Input money
		6. Input Commission Fee
		7. Input Interval days
		8. Input Start Date
		9. Choose if there is a End Date, if "Y", goto step 10; if "N", goto step 11
		10.Input End Date
		11. If any above inputs in in the wrong format, it will ask you to re-enter until you get it right;
		    If user input all things correctly, it will show apply strategy successfully, then back to menu.




	- Quit 
		1. input "q" or "Q", Quit.




	
