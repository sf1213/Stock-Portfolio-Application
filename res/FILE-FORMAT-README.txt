This file is meant to specify how to input Portfolio and Strategy as JSON file, 
to be specific, their necessary format. 

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Part 1: 

Portfolio JSON file. 

Two necessary attributes in the input JSON file by the names of(String): 

1), "syms": a String attribute, consisting of all the stock Symbol Tickers 
separated by " ", cannot to be empty String. (Example: "GOOG AAPL MFST")

2), "stocks": a String attribute, consisting of all the owned stocks in the 
portfolio to be built, separated by "\n". This can be empty String if there 
is no stock in the portfilio. 

The format of each line of a StockOwnership is as below (all the information 
is needed and the sequence must be kept): 

"1: StockTickerSymbol: GOOG, ShareOwned: 0.965204, BuyPrice: 1036.05, 
Cost: 1005.00, Commission: 5.00, BuyDate: 11/13/18\n"


~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Part 2: 

Strategy JSON file.

5 mandatory attributes and one optional attribute should be included in the JSON: 

Mandatory: 

1), "interval":  a String representing the interval day of the strategy. It must be 
able to be parsed into an int. 

2), "amount": a double value representing the overall value of the strategy 
for each interval. It must be able to be parsed into a double. 

3), "comm": a double value representing the commission fee of the strategy for 
each interval. It must be able to be parsed into a double. 

4), "startDate": a String representing the startDate of the strategy, The format 
must comply with "YYYY-MM-DD". 

5), "ratioMap": a JSON Array representing all the stock and its ratio in the strategy. 
For each each, the item is composed of  two parts concatinated with ":", the first
part as the Symbol Ticker of the stock, such as "GOOG", the second part as the percentage
of the stock, able to be parsed to a double value, such as "0.75". 

Optional: 


1), "endDate" : a String representing the endDate of the strategy, The format 
must comply with "YYYY-MM-DD". 






