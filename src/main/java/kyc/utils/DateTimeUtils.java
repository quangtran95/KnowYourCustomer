package kyc.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class DateTimeUtils {
   public static Date getNowDate() {
      TimeZone timeZone = TimeZone.getDefault();
      SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
      format.setTimeZone(timeZone);

      Calendar today = GregorianCalendar.getInstance(timeZone);
      String dateTime = format.format(today.getTime());

      try {
         format.setTimeZone(TimeZone.getDefault());
         Date date = format.parse(dateTime);

         return date;
      }
      catch (Exception ex) {
         return new Date();
      }

   }

   public static String formatDate(Date date, String formatString){
      SimpleDateFormat format = new SimpleDateFormat(formatString);
      return format.format(date);
   }

   public static String getCurrentDateTimeYYYYMMDDHHMMSS() {
      TimeZone timeZone = TimeZone.getDefault();
      SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
      format.setTimeZone(timeZone);

      Calendar today = GregorianCalendar.getInstance(timeZone);
      return format.format(today.getTime());
   }
}
