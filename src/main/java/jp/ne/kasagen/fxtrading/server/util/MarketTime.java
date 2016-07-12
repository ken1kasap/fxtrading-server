package jp.ne.kasagen.fxtrading.server.util;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * 市場の取引時間判定クラス.
 * 
 * Ref. http://www.gaitameonline.com/dealgaiyo.jsp
 * 
 * @author kasagen
 */
public class MarketTime {

    /**
     * MarketTime Open time.
     */
    public static final LocalTime MONDAY_LIMIT = LocalTime.of(7, 0);

    /**
     * MarketTime Close time.
     */
    public static final LocalTime SATUDAY_LIMIT = LocalTime.of(6, 55);

    /**
     * MarketTime Close time. (U.S. Summer time)
     */
    public static final LocalTime SATUDAY_LIMIT_SUMMERTIME = LocalTime.of(5, 55);

    /**
     * 引数で渡された日時において、取引市場が開いているかを返します。
     * Marketは月曜日 午前7：00～土曜日 午前6：55の間開いています。
     *
     * @param datetime
     * @return
     */
    public boolean isOpened(LocalDateTime datetime) {
        if (isMonday(datetime)) {
            return isMondayOpened(datetime);
        }

        if (isSaturday(datetime)) {
            return isSaturdayOpened(datetime);
        }
        
        // 曜日がMONDAYかSATURDAY, SUNDAY以外の場合は市場は開いている。
        return !isSunday(datetime);
    }

    /**
     * 曜日が月曜日かどうか。
     * @param datetime
     * @return
     */
    private boolean isMonday(LocalDateTime datetime) {
        return datetime.getDayOfWeek() == DayOfWeek.MONDAY;
    }

    /**
     * 曜日が土曜日かどうか。
     * @param datetime
     * @return
     */
    private boolean isSaturday(LocalDateTime datetime) {
        return datetime.getDayOfWeek() == DayOfWeek.SATURDAY;
    }
    
    /**
     * 曜日が日曜日かどうか。
     * @param datetime
     * @return
     */
    private boolean isSunday(LocalDateTime datetime) {
        return datetime.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    /**
     * 月曜日の市場が開いているか
     * @param datetime
     * @return
     */
    private boolean isMondayOpened(LocalDateTime datetime) {
        LocalTime time = datetime.toLocalTime();
        return MONDAY_LIMIT.compareTo(time) <= 0;
    }

    /**
     * 土曜日の市場がまだ開いているか
     * @param datetime
     * @return
     */
    private boolean isSaturdayOpened(LocalDateTime datetime) {
        LocalTime time = datetime.toLocalTime();
        return SATUDAY_LIMIT.compareTo(time) > 0;
    }
}
