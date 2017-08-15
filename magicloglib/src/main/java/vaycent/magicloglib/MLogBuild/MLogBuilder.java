package vaycent.magicloglib.MLogBuild;


/**
 * Created by vaycent on 2017/8/15.
 */

public class MLogBuilder extends MLogBaseBuilder{

    private MLogBaseBean obj = new MLog();

    @Override
    public void buildError(boolean error) {
        obj.setError(error);
    }

    @Override
    public void buildWarning(boolean warning) {
        obj.setWarning(warning);
    }

    @Override
    public void buildInformation(boolean information) {
        obj.setInformation(information);
    }

    @Override
    public void buildDebug(boolean debug) {
        obj.setDebug(debug);
    }

    @Override
    public void buildVerbose(boolean verbose) {
        obj.setVerbose(verbose);
    }

    @Override
    public void buildLogLevel(int logLevel) {
        obj.setLogLevel(logLevel);
    }

    @Override
    public void buildLogFilePath(String logFilePath) {
        obj.setLogFilePath(logFilePath);
    }

    @Override
    public void buildLogFilterPriority(String logFilterPriority) {
        obj.setLogFilterPriority(logFilterPriority);
    }

    @Override
    public void buildLogFilterTag(String logFilterTag) {
        obj.setLogFilterTag(logFilterTag);
    }

    @Override
    public MLogBaseBean create() {
        return obj;
    }
}
