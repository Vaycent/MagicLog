package vaycent.magicloglib.MLogBuild;

/**
 * Created by vaycent on 2017/8/15.
 */

public abstract class MLogBaseBuilder {
    public abstract void buildError(boolean error);
    public abstract void buildWarning(boolean warning);
    public abstract void buildInformation(boolean information);
    public abstract void buildDebug(boolean debug);
    public abstract void buildVerbose(boolean verbose);
    public abstract void buildLogLevel(int logLevel);
    public abstract void buildLogFilePath(String logFilePath);
    public abstract void buildLogFilterPriority(String logFilterPriority);
    public abstract void buildLogFilterTag(String logFilterTag);

    public abstract MLogBaseBean create();
}
