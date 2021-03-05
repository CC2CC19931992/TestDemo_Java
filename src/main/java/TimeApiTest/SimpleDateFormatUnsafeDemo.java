package TimeApiTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * SimpleDateFormat线程不安全测试
 * 通过查看底层代码可知
 * 这是在SimpleDateFormat类里的1531行这里 parsedDate = calb.establish(calendar).getTime()
 * 所有线程都是使用同一个calendar对象，如果没有线程同步措施的话，就可能会造成线程安全问题
 * @author tc
 * @date 2021/3/2
 */
public class SimpleDateFormatUnsafeDemo {

    final static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args){
        for (int i = 0;i<10;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Date date = SIMPLE_DATE_FORMAT.parse("2021-03-02 14:00:00");
                        System.out.println(date);
                    }catch (ParseException e){
                        e.printStackTrace();
                    }

                }
            }).start();
        }
    }
}
