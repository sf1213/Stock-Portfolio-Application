import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import portfolio.model.StockModel;
import portfolio.model.StockModelImpl;

import static org.junit.Assert.assertEquals;

public class StockModelTest {
  private StockModel sm1;
  private StockModel sm2;

  /**
   * Build all the test objects needed for test cases.
   */
  @Before
  public void setup() {
    sm1 = new StockModelImpl();
    sm2 = new StockModelImpl();
    Set<String> sm1Set = new HashSet<>();
    sm1Set.add("GOOG");
    sm1Set.add("AAPL");
    sm1.createPortfolio("pf1", sm1Set);

  }

  /**
   * createPortfolioTest test code, to verify: 1, if createPortfolio functions correctly with right
   * input. 2, if examine() works with empty portfolio. 3, if getAllPortfolios() works correctly
   * with non-empty portfolio. 4, if getAllPortfolios() works correctly with empty portfolio. 5, if
   * create portfolio with repeated name casts correct exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void createPortfolioTest() {
    sm1.createPortfolio("pf2", new HashSet<>());
    assertEquals(sm1.getAllPortfolios(), "pf1 pf2 ");
    assertEquals(sm1.examine("pf1"), "");

    assertEquals(sm2.getAllPortfolios(), "");
    sm2.createPortfolio("pf10", new HashSet<>());
    sm2.createPortfolio("pf20", new HashSet<>());
    assertEquals(sm2.getAllPortfolios(), "pf10 pf20 ");
    assertEquals(sm1.examine("pf1"), "");

    sm1.createPortfolio("pf1", new HashSet<>());
  }

  /**
   * createPortfolioTest test code, to verify: 1, if createPortfolio functions correctly with right
   * input. 2, if examine() works with empty portfolio. 3, if getAllPortfolios() works correctly
   * with non-empty portfolio. 4, if getAllPortfolios() works correctly with empty portfolio. 5, if
   * create portfolio with repeated name casts correct exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void buyStockRealAPITest() {
    assertEquals(sm1.examine("pf1"), "");
    sm1.buyStockByAmount("pf1", "GOOG", Date.valueOf("2018-11-13"), 1000, 5.0);
    assertEquals(sm1.examine("pf1"), "1: StockTickerSymbol: GOOG, ShareOwned: "
            + "0.965204, BuyPrice: 1036.05, Cost: 1005.00, Commission: 5.00, BuyDate: 11/13/18\n"
            + "\n");
    sm1.createPortfolio("pf1", new HashSet<>());
  }

  /**
   * buyStockMockAPITest test code, to verify: 1, if buy stock by money amount works correctly with
   * correct input. 2, if buy the same stock with the same amount on the same day works correctly as
   * two separate items in the portfolio as designed 3, if buy stock by share value works correctly
   * with correct input. 4, if getPortfolioCost at a certain date works correctly to show part of
   * the portfolio. 5, if getPortfolioCost on a non-open date works correctly to show part of the
   * portfolio. 6, if getPortfolioCost with no input works correctly to show the latest status cost.
   * 7, if getPortfolioVal at a certain date works correctly to show part of the portfolio. 8, if
   * getPortfolioVal on a non-open date works correctly to show part of the portfolio. 9, if
   * getPortfolioVal with no input works correctly to show the latest status cost. 10, if by stock
   * on a not open date casts correct exception as designed. 11, if by stock on a not open date
   * casts correct exception as designed.
   */
  @Test//(expected = IllegalArgumentException.class)
  public void buyStockMockAPITest() {
    Set<String> sm2Set = new HashSet<>();
    sm2Set.add("GOOG");

    sm2.createPortfolio("pf10", sm2Set);
    sm2.addStockPortfolio("pf10", "AAPL");
    sm2.buyStockByAmount("pf10", "GOOG", Date.valueOf("2018-11-13"), 1000, 5);
    //sm2.buyStockByAmount("pf10", "GOOG", Date.valueOf("2018-11-13"), 1000, 5);
    sm2.buyStockByShare("pf10", "AAPL", Date.valueOf("2018-11-07"), 5, 5);
    assertEquals(sm2.examine("pf10"), "1: StockTickerSymbol: GOOG, ShareOwned: 0.965204, "
            + "BuyPrice: 1036.05, Cost: 1005.00, Commission: 5.00, BuyDate: 11/13/18\n"
            + "\n"
            + "2: StockTickerSymbol: AAPL, ShareOwned: 5.000000, BuyPrice: 209.95, "
            + "Cost: 1054.75, Commission: 5.00, BuyDate: 11/07/18\n"
            + "\n");

    assertEquals(sm2.getPortfolioCost("pf10", Date.valueOf("2018-10-08")), 0.0, 0.001);
    assertEquals(sm2.getPortfolioVal("pf10", Date.valueOf("2018-10-08")), 0.0, 0.001);
    assertEquals(sm2.getPortfolioCost("pf10", Date.valueOf("2018-11-08")), 1054.75, 0.001);
    assertEquals(sm2.getPortfolioCost("pf10", Date.valueOf("2018-11-10")), 1054.75, 0.001);
    assertEquals(sm2.getPortfolioCost("pf10"), 2059.75, 0.001);
    assertEquals(sm2.getPortfolioVal("pf10", Date.valueOf("2018-11-08")), 1042.45, 0.001);
    //sm2.buyStockByAmount("pf10", "AAPL", Date.valueOf("2018-11-10"), 1500, 5);
  }

  /**
   * buyStockZeroAmountTest test code, to verify if buy stock with 0 amount casts correct exception
   * as designed.
   */
  @Test(expected = IllegalArgumentException.class)
  public void buyStockZeroAmountTest() {
    sm1.buyStockByAmount("pf1", "GOOG", Date.valueOf("2018-11-13"), 0.0, 5);
  }

  /**
   * NOT DONE buyStockZeroAmountTest test code, to verify if buy stock by index in the portfolio
   * works correctly.
   */
  @Test
  public void buyStockByIdxTest() {
    Set<String> sm2Set = new HashSet<>();
    sm2Set.add("GOOG");
    sm2Set.add("AAPL");
    sm2.createPortfolio("pf10", sm2Set);
    sm2.buyStockByAmount("pf10", "GOOG", Date.valueOf("2018-11-13"), 1000, 5);
    assertEquals(sm2.examine("pf10"), "1: StockTickerSymbol: GOOG, ShareOwned: 0.965204, "
            + "BuyPrice: 1036.05, Cost: 1005.00, Commission: 5.00, BuyDate: 11/13/18\n"
            + "\n");
    sm2.buyStockByIdx("pf10", 1, 5.0, 5);
    assertEquals(sm2.examine("pf10"), "1: StockTickerSymbol: GOOG, ShareOwned: 5.965204, "
            + "BuyPrice: 1036.05, Cost: 6190.25, Commission: 10.00, BuyDate: 11/13/18\n"
            + "\n");
    sm2.savePortfolio("pf10");
    //System.out.println(sm2.getAllPortfolios());
    sm2.createPortfolio("pf30Copy", "./pf10.JSON");
    assertEquals(sm2.getStockNames("pf30Copy"), "GOOG AAPL");
    assertEquals(sm2.examine("pf30Copy"), "1: StockTickerSymbol: GOOG, ShareOwned: 5.965204, "
            + "BuyPrice: 1036.05, Cost: 6190.25, Commission: 10.00, BuyDate: 11/13/18\n"
            + "\n");
  }

  /**
   * buyStockNegativeAmountTest test code, to verify if buy stock with negative amount casts correct
   * exception as designed.
   */
  @Test(expected = IllegalArgumentException.class)
  public void buyStockNegativeAmountTest() {
    sm1.buyStockByAmount("pf1", "GOOG", Date.valueOf("2018-11-13"), -10.0, 5);
  }

  /**
   * buyStockZeroShareTest test code, to verify if buy stock with 0 amount casts correct exception
   * as designed.
   */
  @Test(expected = IllegalArgumentException.class)
  public void buyStockZeroShareTest() {
    sm1.buyStockByShare("pf1", "GOOG", Date.valueOf("2018-11-13"), 0.0, 5);
  }

  /**
   * buyStockMockAPITest test code, to verify if buy stock with negative amount casts correct
   * exception as designed.
   */
  @Test(expected = IllegalArgumentException.class)
  public void buyStockNegativeShareTest() {
    sm1.buyStockByShare("pf1", "GOOG", Date.valueOf("2018-11-13"), -10.0, 5);
  }

  /**
   * buyStockInFutureTest test code, to verify if buy stock in future casts correct exception as
   * designed.
   */
  @Test(expected = IllegalArgumentException.class)
  public void buyStockInFutureTest() {
    sm1.buyStockByShare("pf1", "GOOG", Date.valueOf("2020-11-13"), 1500.0, 5);
  }

  /**
   * buyStockInFutureTest test code, to verify if buy stock in future casts correct exception as
   * designed.
   */
  @Test(expected = IllegalArgumentException.class)
  public void buyStockInFuture2Test() {
    sm1.buyStockByAmount("pf1", "GOOG", Date.valueOf("2020-11-13"), 1500.0, 5);
  }

  /**
   * getCostInFutureTest test code, to verify if getting cost in future casts correct exception as
   * designed.
   */
  @Test(expected = IllegalArgumentException.class)
  public void getCostInFutureTest() {
    sm1.getPortfolioCost("pf1", Date.valueOf("2020-11-13"));
  }

  /**
   * getValInFutureTest test code, to verify if getting value in future casts correct exception as
   * designed.
   */
  @Test(expected = IllegalArgumentException.class)
  public void getValInFutureTest() {
    sm1.getPortfolioVal("pf1", Date.valueOf("2020-11-13"));
  }

  /**
   * getValNonNameTest test code, to verify if getting val with non-exist portfolio casts correct
   * exception as designed.
   */
  @Test(expected = IllegalArgumentException.class)
  public void getValNonNameTest() {
    sm1.getPortfolioVal("pf3", Date.valueOf("2017-11-13"));
  }

  /**
   * getCostNonNameTest test code, to verify if getting val with non-exist portfolio casts correct
   * exception as designed.
   */
  @Test(expected = IllegalArgumentException.class)
  public void getCostNonNameTest() {
    sm1.getPortfolioCost("pf3", Date.valueOf("2017-11-13"));
  }

  /**
   * buyStockNonName2Test test code, to verify if buy stock by wrong portfolio name casts correct
   * exception as designed.
   */
  @Test(expected = IllegalArgumentException.class)
  public void buyStockNonName2Test() {
    sm1.buyStockByAmount("pf3", "GOOG", Date.valueOf("2016-11-13"), 1500.0, 5);
  }

  /**
   * buyStockNonNameTest test code, to verify if buy stock by wrong portfolio name casts correct
   * exception as designed.
   */
  @Test(expected = IllegalArgumentException.class)
  public void buyStockNonNameTest() {
    sm1.buyStockByShare("pf3", "GOOG", Date.valueOf("2016-11-13"), 1500.0, 5);
  }

  /**
   * buyStockNullNameTest test code, to verify if buy stock by null portfolio name casts correct
   * exception as designed.
   */
  @Test(expected = IllegalArgumentException.class)
  public void buyStockNullNameTest() {
    sm1.buyStockByShare(null, "GOOG", Date.valueOf("2016-11-13"), 1500.0, 5);
  }

  /**
   * buyStockNullName2Test test code, to verify if buy stock by null portfolio name casts correct
   * exception as designed.
   */
  @Test(expected = IllegalArgumentException.class)
  public void buyStockNullName2Test() {
    sm1.buyStockByAmount(null, "GOOG", Date.valueOf("2016-11-13"), 1500.0, 5);
  }

  /**
   * buyNullStockTest test code, to verify if buy null stock casts correct exception as designed.
   */
  @Test(expected = IllegalArgumentException.class)
  public void buyNullStockTest() {
    sm1.buyStockByShare("pf1", null, Date.valueOf("2016-11-13"), 1500.0, 5);
  }

  /**
   * buyNullStock2Test test code, to verify if buy null stock casts correct exception as designed.
   */
  @Test(expected = IllegalArgumentException.class)
  public void buyNullStock2Test() {
    sm1.buyStockByAmount("pf1", null, Date.valueOf("2016-11-13"), 1500.0, 5);
  }

  /**
   * createPortByNullTest test code, to verify if creating null name portfolio casts correct
   * exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void createPortByNullTest() {
    Set<String> set = null;
    sm1.createPortfolio(null, set);
  }

  /**
   * createPortByNullTest test code, to verify if examining null name portfolio casts correct
   * exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void examinePortByNullTest() {
    sm1.examine(null);
  }

  /**
   * Test high-level trade: to verify if high level trade fixed value trade mode work 1, if fix
   * value trade with no terminate date works 2, if fix value trade with terminate date.
   */
  @Test
  public void highLevelFixedValueTradeTest() {
    //    System.out.println(sm1.getAllPortfolios());
    //    System.out.println(sm1.getPortfolioCost("pf1"));
    Map<String, Double> ratioMap = new HashMap<>();
    ratioMap.put("GOOG", 0.7);
    ratioMap.put("AAPL", 0.3);

    sm1.highLevelFixedValueTrade("pf1", ratioMap, Date.valueOf("2017-7-13"),
            Date.valueOf("2017-09-27"), 30, 30000, 5, true);
    System.out.println(sm1.examine("pf1"));
    assertEquals(sm1.getPortfolioVal("pf1", Date.valueOf("2017-7-18")), 30545.100585321572, 0.01);
    assertEquals(sm1.getPortfolioVal("pf1", Date.valueOf("2017-10-30")), 97521.95607225546, 0.01);


    Set<String> sm2Set = new HashSet<>();
    sm2Set.add("GOOG");
    sm2Set.add("AAPL");
    sm2.createPortfolio("pf10", sm2Set);
    sm2.highLevelFixedValueTrade("pf10", "./pf1-2017-07-13-2017-09-27-30.JSON");
    System.out.println("SM2: ");
    System.out.println(sm2.getStockNames("pf10"));
    System.out.println(sm2.examine("pf10"));
  }
}