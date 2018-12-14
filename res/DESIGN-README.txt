
Assignment-10 Extension changes:


Model 


Model-layer:

1, built a separate JsonReaderBuilder interface to carry out the new-added functions of saving & retrieving portfolios & strategies. 
A PortfolioBuilder class implements this interface and actualizes all the methods. 

2, added methods in the StockModel interface to realize the methods of saving & retrieving portfolios & strategies.
Therefor also added methods to the StockModelImpl class to override these methods, by call the JsonReaderBuilder object's 
reading & saving method.

3, added a method in the StockModel interface (therefore StockModelImpl) for the Portfolio price query List between a time slot, 
to support the controller and view to draw line-graph to check value change tread of a portfolio. 


Portfolio-layer:

1, added a method in Portfolio (therefore PortfolioImpl) to realize the Model method to get a List of portfolio value, for the purpose
of controller/view graph drawing. 

Stock-layer and API: unchanged. 




Controller


1. Add a Features Interface. This interface represents a set of functions. These functions are used as callbacks by the view, 
to pass control to the controller. How the view uses them as callbacks is completely up to how the view is designed. 
Each function is designed to take in the necessary data to complete the functionality.

2. Adapters design pattern. Add a PortfolioGController class that extends ICommandImpl and implements Features Interface, 
so that it implements all the methods in the Features interface by using the methods it inherited from the old ICommandImpl class. 





View

1. Add a JFrameView class that implements IView Interface. It works for Graphical user interfaces. It built the main JFrame window of GUI, 
and accept the set of callbacks from the controller, and hook up as needed to various things in this view.

2. Add an abstract class AbstractPanel that extends JPanel. It has all the protected common methods of all the individual panel(like add stock, check value, etc.)

3. Add 7 JPanle classes that extends the AbstractPanel, including GraphPanel, AddPanel, BuyPanel, CostPanel, CreatePanel, StrategyPanel and ValuePanel. 
These panels represent pop-out panels outside the main JFrame window.


~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Assignment-9 Extension changes:


Model

the most significant update is to support high-level portfolio trade modes. So these changes have been made to support. 

From top to bottom: 

Model-layer: only high-level trade functions are added into the interface and class, beside the extra commission fee input added 
for all trade function. So basically the old designed is maintained and extended, rather than changed. 

Portfolio-layer: adding high-level function to interface with Model-layer and adding commission fee into all trade methods. 

Stock-layer:  nothing changed except for the adding of commission in trade and cost value query methods. So basically the old design stays. 

API: in assignment-8, the API connected to the model is actually a Mock Value, only returns a fixed value for all case. In assignment-9, 
the actual Alpha-Vantage api is designed and used. Wait-Time is set to avoid query-exceeding-limits. A static field of Map is built to store
query information items to avoid duplicate queries in API.  By replacing an old Mock model with a real working one is consistent with SOLID
 principles. 

Enum MenuType: Add STRATEGY MenuType, nothing else change.




Controller

All Interfaces make no change.

1. In the main command function that controls menu operations, add one new else if branch:Apply Strategy, it create a new 
Strategy object as the other branches. Nothing else change.

2. Inside the command package, in the ICommandImpl Abstract class, add a protected function getCommissionFee() for subclasses
 to inherite.

3. Add a new Strategy class that extends ICommandImpl abstract class. This class contains all the functions when user choose 
to apply strategy to existing portfolios. Many private methods are re-used from the other old command classes, such as get 
the money input from user and fetch all the portfolios and numbering them for user to choose.




View

Add one line "7. Apply Strategy" to the menu. Nothing else change.




~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~


The Design include model, viewer, and controller sub model, functions as :



Model

Three layers of models: 
1, Stock-layer(bottom): as StockOwnership, which represents a single trade for a stock, with all related messages recorded in it. 
And the share-value is changable. 

2, Portfolio-layer: one single portfolio for the client, made of multiple StockOwnership objects. This layer handles the operations 
and devided them into operations on a single StockOwnership. 

3, Model-layer: the top layer represent the stock model for a client, made of 0 - many portfolios of the client. All the required 
operation interface functions are defined and designed in this layer. 

(API: which is the data service of the stock. )



Assignment-9 Extension changes:
the most significant update is to support high-level portfolio trade modes. So these changes have been made to support. 

From top to bottom: 

Model-layer: only high-level trade functions are added into the interface and class, beside the extra commission fee input added 
for all trade function. So basically the old designed is maintained and extended, rather than changed. 

Portfolio-layer: adding high-level function to interface with Model-layer and adding commission fee into all trade methods. 

Stock-layer:  nothing changed except for the adding of commission in trade and cost value query methods. So basically the old design stays. 

API: in assignment-8, the API connected to the model is actually a Mock Value, only returns a fixed value for all case. In assignment-9, 
the actual Alpha-Vantage api is designed and used. Wait-Time is set to avoid query-exceeding-limits. By replacing an old Mock model 
with a real working one is consistent with SOLID principles. 

Enum MenuType: Add STRATEGY MenuType, nothing else change.









Controller: 

It takes user inputs, tells model what to do and view what to display.It does not care how model implements functionality, 
does not care how the screen is laid out to display results. 

1. the controller, which handled the start and quit operations and also processes all the user input 
 from resources, handing necessary information to model. It also conveys the outputs, results or user
 hint request to viewer for user notifications. 

In my design, I built a IPortfoliosController Interface, with only one function startPortfolio(), which opens a 
new Stock Portfolio, returns only when the Stock Portfolio is quitted. And 3 fields : input, model and view.

The class PortfoliosController implements IPortfoliosController interface. It implements the startPortfolio() function. 
This function will create different ICommand object according to user's choice from menu, then kick off the goCommand() 
function of the ICommand object that being choose, executed corresponding operation (to model and view). Then wait for 
the next input from user. If user input "q" or “Q” to quit, then this startPortfolio function closed.

For the menu's choice, I created a enum MenuType for future flexibility. In this way, if future the menu changes or added, 
only small part of codes need to be modified. The startPortfolio function will remain unchanged for many situations.


2. the command package inside of controller, I created ICommend Inteface, it has the function goCommand(), which take 
certain actions when this menu item is selected. 

I created an abstract class ICommandImpl extends the ICommandImpl, it has all the common functions of different menu item operations.

The 6 menu item classes (AllCost, AllValue, BuyStock, Create, ExamOne, ViewAll) corresponding to each menu item in the menu 
interface. I do this separation class because it's more clear and organized, and if in future the menu interface changes or 
another menu interface need to be added, the new menu interface can reuse common menu item. In this way, fewer codes need 
to be changed.




Assignment-9 Extension changes:
All Interfaces make no change.

1. In the main command function that controls menu operations, add one new else if branch:Apply Strategy, it create a new 
Strategy object as the other branches. Nothing else change.

2. Inside the command package, in the ICommandImpl Abstract class, add a protected function getCommissionFee() for subclasses
 to inheriate.

3. Add a new Strategy class that extends ICommandImpl abstract class. This class contains all the functions when user choose 
to apply strategy to existing portfolios. Many private methods are re-used from the other old command classes, such as get 
the money input from user and fetch all the portfolios and numbering them for user to choose.








View: 

It display results to users, does not care how the results were produced, when to respond to user action.
the viewer has very direct functions: conveying all the necessary information from controller and sending to the client 
through console or other output channel.



Assignment-9 Extension changes: Add one line "7. Apply Strategy" to the menu. Nothing else change.




~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~



Overall, the controller knows only the interface of view and model, and nothing about the detail implementation of view and model.



