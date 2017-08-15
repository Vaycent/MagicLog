package vaycent.magicloglib.MLogBuild;

/**
 * Created by vaycent on 2017/8/15.
 */

public class MLogDirector {
    MLogBaseBuilder mBuilder =null;

    public MLogDirector(MLogBaseBuilder builder){
        this.mBuilder =builder;
    }

    public void construct(boolean error,boolean warning,boolean information,
                          boolean debug, boolean verbose,int logLevel,
                          String logFilePath,String logFilterPriority,String logFilterTag){
        mBuilder.buildError(error);
        mBuilder.buildError(warning);
        mBuilder.buildError(information);
        mBuilder.buildError(debug);
        mBuilder.buildError(verbose);

        mBuilder.buildLogLevel(logLevel);
        mBuilder.buildLogFilePath(logFilePath);
        mBuilder.buildLogFilterPriority(logFilterPriority);
        mBuilder.buildLogFilterTag(logFilterTag);

    }

    /*
    How to use:

        MLogBaseBuilder builder=new MLogBuilder();
        MLogDirector director=new MLogDirector(builder);
        director.construct(true,true,true,true,false,0,"","","");
        System.out.println("resutl :" +builder.create().toString());

     */
}
