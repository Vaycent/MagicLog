package vaycent.magicloglib.MLogBuild;

/**
 * Created by vaycent on 2017/8/15.
 */

public abstract class MLogBaseBean {

    private boolean error;
    private boolean warning;
    private boolean information;
    private boolean debug;
    private boolean verbose;

    private int logLevel;
    private String logFilePath;
    private String logFilterPriority;
    private String logFilterTag;

    public void setError(boolean error) {
        this.error = error;
    }
    public void setWarning(boolean warning) {
        this.warning = warning;
    }
    public void setInformation(boolean information) {
        this.information = information;
    }
    public void setDebug(boolean debug) {
        this.debug = debug;
    }
    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    public void setLogLevel(int logLevel) {
        this.logLevel = logLevel;
    }
    public void setLogFilePath(String logFilePath) {
        this.logFilePath = logFilePath;
    }
    public void setLogFilterPriority(String logFilterPriority) {
        this.logFilterPriority = logFilterPriority;
    }
    public void setLogFilterTag(String logFilterTag) {
        this.logFilterTag = logFilterTag;
    }

    @Override
    public String toString() {
        return "MlogObj [error ="+error+",warning="+warning+",information="+information+
                ",debug="+debug+",verbose="+verbose+",logLevel="+logLevel+",logFilePath="+logFilePath
        +",logFilterPriority="+logFilterPriority+",logFilterTag="+logFilterTag+"]";
    }

}
