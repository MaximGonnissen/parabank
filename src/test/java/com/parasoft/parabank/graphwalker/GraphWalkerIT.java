package com.parasoft.parabank.graphwalker;

import com.parasoft.parabank.graphwalker.modelimpl.*;
import com.parasoft.parabank.graphwalker.utils.Driver;
import com.parasoft.parabank.graphwalker.utils.Helpers;
import com.parasoft.parabank.graphwalker.utils.ResultHandler;
import com.parasoft.parabank.graphwalker.utils.Urls;
import com.parasoft.parabank.it.util.DriverFactory;
import org.graphwalker.core.generator.SingletonRandomGenerator;
import org.graphwalker.core.machine.Machine;
import org.graphwalker.core.statistics.Profiler;
import org.graphwalker.java.test.Executor;
import org.graphwalker.java.test.Result;
import org.graphwalker.java.test.TestExecutor;
import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * <b>Integration tests for the GraphWalker model.</b>
 * <p>These tests run implementations of the GraphWalker model to test the ParaBank application, and verify that the
 * model is correct.</p>
 */
public class GraphWalkerIT {
    /**
     * Seed used for both the Random instance used by the Helpers class and the SingletonRandomGenerator used by
     * GraphWalker internally. This ensures that the tests are deterministic and reproducible.
     */
    protected static final long SEED = 0;
    protected static Logger logger = LoggerFactory.getLogger(GraphWalkerIT.class);

    public static void printProfilingInfo(Machine machine) {
        Profiler profiler = machine.getProfiler();
        logger.info("Total time: {}", profiler.getTotalExecutionTime());
        logger.info("Total visited elements: {}", profiler.getTotalVisitCount());
        logger.info("Submodel statistics:");
        machine.getContexts().forEach(context -> logger.info("> {} ({}):", context.getModel().getName(), context.getPathGenerator().toString()));
        machine.getContexts().forEach(context -> logger.info("\t|- Model time: {}", context.getProfiler().getTotalExecutionTime()));
        machine.getContexts().forEach(context -> logger.info("\t|- Model visited elements: {}", context.getProfiler().getTotalVisitCount()));
    }

    /**
     * <b>Before Class</b>
     * <p>We check whether the container is running, and skip the tests if it is not.</p>
     */
    @BeforeClass
    public static void beforeClass() {
        try {
            final URLConnection connection = new URL(Urls.BASE_URL).openConnection();
            connection.connect();
        } catch (MalformedURLException e) {
            logger.error("Malformed URL: {}", e.getMessage());
        } catch (IOException e) {
            logger.error("Could not connect to the URL: {}", e.getMessage());
            logger.info("Skipping GraphWalker tests...");
            Assume.assumeFalse(true);
        }
    }

    /**
     * <b>Before Test</b>
     * <p>We set up the WebDriver, and navigate to the base URL of the ParaBank application. We also set the seed for
     * both our own Random instance and the SingletonRandomGenerator used by GraphWalker.</p>
     */
    @Before
    public void beforeTest() {
        Driver.setDriver(DriverFactory.getDriver("Chrome"));
        Driver.getDriver().get(Urls.BASE_URL);
        Driver.getDriver().manage().window().maximize();
        Helpers.seedRandom(SEED);
        SingletonRandomGenerator.setSeed(SEED);
    }

    /**
     * <b>After Test</b>
     * <p>We quit the WebDriver after each test to clean up.</p>
     */
    @After
    public void afterTest() {
        Driver.getDriver().quit();
    }

    @Test
    public void fullTest() throws IOException {
        Executor executor = new TestExecutor(LoginImpl.class, AccountOverviewImpl.class, AccountActivityImpl.class, TransferFundsImpl.class, BillPayImpl.class, FindTransactionsImpl.class, RequestLoanImpl.class, OpenNewAccountImpl.class, UpdateContactInfoImpl.class, RegisterImpl.class);
        Result result = executor.execute(true);

        printProfilingInfo(executor.getMachine());

        Assert.assertTrue(ResultHandler.handleResult(result, false));
    }

    /**
     * <b>Account Activity implementation integration test</b>
     *
     * @throws IOException if an I/O error occurs
     * @see AccountActivityImpl
     */
    @Test
    public void testAccountActivity() throws IOException {
        Executor executor = new TestExecutor(LoginImpl.class, AccountOverviewImpl.class, AccountActivityImpl.class);
        Result result = executor.execute(true);

        printProfilingInfo(executor.getMachine());

        Assert.assertTrue(ResultHandler.handleResult(result, false));
    }

    /**
     * <b>Account Overview implementation integration test</b>
     *
     * @throws IOException if an I/O error occurs
     * @see AccountOverviewImpl
     */
    @Test
    public void testAccountOverview() throws IOException {
        Executor executor = new TestExecutor(LoginImpl.class, AccountOverviewImpl.class);
        Result result = executor.execute(true);

        printProfilingInfo(executor.getMachine());

        Assert.assertTrue(ResultHandler.handleResult(result, false));
    }

    /**
     * <b>Bill Pay implementation integration test</b>
     *
     * @throws IOException if an I/O error occurs
     * @see BillPayImpl
     */
    @Test
    public void testBillPay() throws IOException {
        Executor executor = new TestExecutor(LoginImpl.class, BillPayImpl.class);
        Result result = executor.execute(true);

        printProfilingInfo(executor.getMachine());

        Assert.assertTrue(ResultHandler.handleResult(result, false));
    }

    /**
     * <b>Find Transactions implementation integration test</b>
     *
     * @throws IOException if an I/O error occurs
     * @see FindTransactionsImpl
     */
    @Test
    public void testFindTransactions() throws IOException {
        Executor executor = new TestExecutor(LoginImpl.class, FindTransactionsImpl.class);
        Result result = executor.execute(true);

        printProfilingInfo(executor.getMachine());

        Assert.assertTrue(ResultHandler.handleResult(result, false));
    }

    /**
     * <b>Login implementation integration test</b>
     *
     * @throws IOException if an I/O error occurs
     * @see LoginImpl
     */
    @Test
    public void testLogin() throws IOException {
        Executor executor = new TestExecutor(LoginImpl.class);
        Result result = executor.execute(true);

        printProfilingInfo(executor.getMachine());

        Assert.assertTrue(ResultHandler.handleResult(result, false));
    }

    /**
     * <b>Open New Account implementation integration test</b>
     *
     * @throws IOException if an I/O error occurs
     * @see OpenNewAccountImpl
     */
    @Test
    public void testOpenNewAccount() throws IOException {
        Executor executor = new TestExecutor(LoginImpl.class, OpenNewAccountImpl.class);
        Result result = executor.execute(true);

        printProfilingInfo(executor.getMachine());

        Assert.assertTrue(ResultHandler.handleResult(result, false));
    }

    /**
     * <b>Register implementation integration test</b>
     *
     * @throws IOException if an I/O error occurs
     * @see RegisterImpl
     */
    @Test
    public void testRegister() throws IOException {
        Executor executor = new TestExecutor(LoginImpl.class, RegisterImpl.class);
        Result result = executor.execute(true);

        printProfilingInfo(executor.getMachine());

        Assert.assertTrue(ResultHandler.handleResult(result, false));
    }

    /**
     * <b>Request Loan implementation integration test</b>
     *
     * @throws IOException if an I/O error occurs
     * @see RequestLoanImpl
     */
    @Test
    public void testRequestLoan() throws IOException {
        Executor executor = new TestExecutor(LoginImpl.class, RequestLoanImpl.class);
        Result result = executor.execute(true);

        printProfilingInfo(executor.getMachine());

        Assert.assertTrue(ResultHandler.handleResult(result, false));
    }

    /**
     * <b>Transfer Funds implementation integration test</b>
     *
     * @throws IOException if an I/O error occurs
     * @see TransferFundsImpl
     */
    @Test
    public void testTransferFunds() throws IOException {
        Executor executor = new TestExecutor(LoginImpl.class, TransferFundsImpl.class);
        Result result = executor.execute(true);

        printProfilingInfo(executor.getMachine());

        Assert.assertTrue(ResultHandler.handleResult(result, false));
    }

    /**
     * <b>Update Contact Info implementation integration test</b>
     *
     * @throws IOException if an I/O error occurs
     * @see UpdateContactInfoImpl
     */
    @Test
    public void testUpdateContactInfo() throws IOException {
        Executor executor = new TestExecutor(LoginImpl.class, UpdateContactInfoImpl.class);
        Result result = executor.execute(true);

        printProfilingInfo(executor.getMachine());

        Assert.assertTrue(ResultHandler.handleResult(result, false));
    }
}
