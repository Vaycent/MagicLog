package vaycent.magicloglib;

/**
 * Created by Vaycent on 2016/4/14.
 */
public class StackTraceMessages {
    private final static int STACK_DEEP_INDEX=5;

    public static String[] getTagInfo(){
        StackTraceElement[] stackElements = Thread.currentThread().getStackTrace();
//        testStackElements(stackElements);/*Just use to test here by Vaycent*/

        StackTraceElement stackElement = stackElements[STACK_DEEP_INDEX];

        String className=stackElement.getClassName();
        className=className.substring(className.lastIndexOf(".")+1);
        String tagName=className;
        className=className+".java";

        int lineNumber = stackElement.getLineNumber();

        String methodName=stackElement.getMethodName();

        String messageHelper=methodName+"("+className+":"+lineNumber+")";

        String[] stackTraceMessages=new String[]{tagName, messageHelper};

        return stackTraceMessages;
    }

    private static void testStackElements(StackTraceElement[] stackElements){
        System.out.println("stackElements.length is :"+stackElements.length);

        for(int i=0;i<stackElements.length;i++){
            StackTraceElement stackElement = stackElements[i];
            System.out.println("*************** stackElement["+i+"] ***************");
            System.out.println("stackElement["+i+"]className:"+stackElement.getClassName());
            System.out.println("stackElement["+i+"]className:"+stackElement.getLineNumber());
            System.out.println("stackElement["+i+"]className:"+stackElement.getMethodName());
        }
    }
}
