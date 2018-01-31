package order.nishi.utils;

import java.util.Calendar;

public class MyValidatorUtil {
    public static boolean validateDate(int year, int month, int day) {
        System.out.println(year);
        System.out.println(month);
        System.out.println(day);
        Calendar cal = Calendar.getInstance();
        // インスタンスには現在時刻が入っているので、クリアする
        cal.clear();
        cal.set(Calendar.YEAR, year);
        // 仕様上0月開始だから-1する
        cal.set(Calendar.MONTH, month-1);
        // 月末を取得する
        int actualDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        System.out.println("act " + actualDay);
        System.out.println("day " + day);
        // 実際の月末よりも大きければエラー
        if (actualDay < day) {
            return false;
        }
        return true;
    }
}
