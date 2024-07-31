package com.parasoft.parabank.graphwalker;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.parasoft.parabank.graphwalker.modelimpl.*;
import com.parasoft.parabank.graphwalker.utils.Driver;
import com.parasoft.parabank.graphwalker.utils.Helpers;
import com.parasoft.parabank.graphwalker.utils.ResultHandler;
import com.parasoft.parabank.graphwalker.utils.Urls;
import com.parasoft.parabank.it.util.DriverFactory;
import org.graphwalker.core.generator.SingletonRandomGenerator;
import org.graphwalker.core.model.Model;
import org.graphwalker.core.utils.BenchmarkPath;
import org.graphwalker.core.utils.BenchmarkPathParser;
import org.graphwalker.core.utils.LateInitBenchmarkPath;
import org.graphwalker.java.test.Result;
import org.graphwalker.java.test.UnifiedTestExecutor;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(value = Parameterized.class)
public class GraphWalkerBenchmarkIT {
    protected static final long SEED = 0;
    /**
     * <p>The path to a specific benchmark directory.</p>
     * <p>Example: "org/graphwalker/parabank_benchmark_edge_coverage"</p>
     * <p>NOTE: This cannot be in the resources folder, since otherwise installation will spend significant time
     * scanning through this folder. Place it outside of the project.</p>
     */
    protected static final String BENCHMARK_PATH = "";
    protected static final boolean SKIP_SUCCESSFUL_TESTS = true;
    protected static final int RUN_LIMIT = Integer.MAX_VALUE;
    protected static final int MAX_TESTS = Integer.MAX_VALUE;
    protected static final boolean FORCE_HEADLESS_DRIVER = true;
    protected static Logger logger = LoggerFactory.getLogger(GraphWalkerBenchmarkIT.class);

    @Parameter
    public LateInitBenchmarkPath lateInitBenchmarkPath;

    @Parameters(name = "{0}")
    public static List<LateInitBenchmarkPath> data() {
        try {
            return BenchmarkPathParser.getLateInitBenchmarkPaths(BENCHMARK_PATH, RUN_LIMIT).stream().filter(lateInitBenchmarkPath -> !(SKIP_SUCCESSFUL_TESTS && doTestResultExistsANDSuccess(lateInitBenchmarkPath.pathFile))).limit(MAX_TESTS).collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            logger.error("Could not parse benchmark paths: {}", e.getMessage());
            return new ArrayList<>();
        }
    }


    /**
     * <p>We check whether the container is running, and return false if not.</p>
     */
    @BeforeClass
    public static void checkSiteReachable() {
        try {
            final URLConnection connection = new URL(Urls.BASE_URL).openConnection();
            connection.connect();
        } catch (MalformedURLException e) {
            logger.error("Malformed URL: {}", e.getMessage());
        } catch (IOException e) {
            logger.error("Could not connect to the URL: {}", e.getMessage());
            logger.info("Skipping GraphWalker benchmark tests...");
            Assume.assumeFalse(true);
        }
    }

    @BeforeClass
    public static void checkBenchmarkPath() {
        if (!BenchmarkPathParser.isValidBenchmarkPath(BENCHMARK_PATH)) {
            logger.error("Invalid benchmark path: {}", BENCHMARK_PATH);
            logger.info("Skipping GraphWalker benchmark tests...");
            Assume.assumeFalse(true);
        }
    }

    public static boolean doTestResultExistsANDSuccess(File pathFile) {
        File directory = pathFile.getParentFile();
        String fileName = pathFile.getName().replace("_path", "_test_results");
        File file = new File(directory, fileName);
        if (!file.exists()) {
            return false;
        }

        try (FileReader reader = new FileReader(file)) {
            JsonObject resultJson = JsonParser.parseReader(reader).getAsJsonObject();
            if (resultJson.has("failures")) {
                return false;
            }
        } catch (IOException e) {
            logger.error("Could not read results from file: {}", e.getMessage());
            return false;
        }

        return true;
    }

    /**
     * <p>We set up the WebDriver, and navigate to the base URL of the ParaBank application. We also set the seed for
     * both our own Random instance and the SingletonRandomGenerator used by GraphWalker.</p>
     */
    @Before
    public void driverSetup() {
        Driver.setDriver(DriverFactory.getDriver("Chrome", FORCE_HEADLESS_DRIVER));
        Driver.getDriver().get(Urls.BASE_URL);
        Driver.getDriver().manage().window().maximize();
        Helpers.seedRandom(SEED);
        SingletonRandomGenerator.setSeed(SEED);
    }

    /**
     * <p>We quit the WebDriver after each run to clean up.</p>
     */
    @After
    public void driverTeardown() {
        Driver.getDriver().quit();
    }

    public void writeResults(Result result, File pathFile, long duration) {
        File directory = pathFile.getParentFile();
        String fileName = pathFile.getName().replace("_path", "_test_results");
        File file = new File(directory, fileName);

        JsonObject resultJson = JsonParser.parseString(result.getResults().toString()).getAsJsonObject();
        resultJson.addProperty("testDuration", duration);
        resultJson.addProperty("driverTimeSpentWaiting", Driver.getTimeSpentWaiting());
        Driver.resetTimeSpentWaiting();

        try {
            FileWriter writer = new FileWriter(file);
            writer.write(resultJson.toString());
            writer.close();
        } catch (IOException e) {
            logger.error("Could not write results to file: {}", e.getMessage());
        }
    }

    /**
     * <p>Runs the benchmark test for the given edge.</p>
     *
     * @throws IOException if an I/O error occurs
     */
    @Test
    public void runSingleBenchmark() throws IOException {
        Helpers.resetAndSetupParaBank();

        UnifiedTestExecutor executor = new UnifiedTestExecutor(LoginImpl.class, AccountOverviewImpl.class, AccountActivityImpl.class, TransferFundsImpl.class, BillPayImpl.class, FindTransactionsImpl.class, RequestLoanImpl.class, OpenNewAccountImpl.class, UpdateContactInfoImpl.class, RegisterImpl.class);

        BenchmarkPath benchmarkPath = lateInitBenchmarkPath.constructBenchmarkPath(new Model(executor.getMachine().getCurrentContext().getModel()));

        executor.setPredefinedPath(benchmarkPath.path);

        long startTime = System.currentTimeMillis();
        Result result = executor.execute(true, true);
        long duration = System.currentTimeMillis() - startTime;

        writeResults(result, lateInitBenchmarkPath.pathFile, duration);

        Assert.assertTrue(ResultHandler.handleResult(result, true));
    }
}