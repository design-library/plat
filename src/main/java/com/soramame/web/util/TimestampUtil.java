/**
 * Copyright 2015 Plat.
 * 
 * This file is part of Pj Platform.
 *
 *  Pj Platform is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Pj Platform is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Pj Platform.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.soramame.web.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * Timestamp utility.
 * 
 * @version 1.0
 * @author Plat.
 *
 */
@Component
public class TimestampUtil {
	
	/**
	 * get string format for timestamp
	 * @param timestamp
	 * @param timeFormat
	 * @return timestamp
	 */
    public static String timestamp(Timestamp timestamp, String timeFormat) {
        return new SimpleDateFormat(timeFormat).format(timestamp);
        
    }

    /**
     * get timestamp
     * @return timestamp
     */
    public static Timestamp current() {
        return new Timestamp(System.currentTimeMillis());
    }
    
    /**
     * date format
     * @param format
     * @param date
     * @return format date
     */
    public static String format(String format, Date date) {
    	SimpleDateFormat sdf = new SimpleDateFormat(format);
    	return sdf.format(date);
    	
    }
}
