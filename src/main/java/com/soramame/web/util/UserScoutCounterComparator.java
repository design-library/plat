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

import java.util.Comparator;
import java.util.Date;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

import org.springframework.stereotype.Component;

import com.soramame.web.model.UserScoutCounterModel;

/**
 * UserScoutComparator
 * 
 * @author Plat.
 * @version 1.0
 */
@Component
public class UserScoutCounterComparator implements Comparator<UserScoutCounterModel> {

	@Override
	public int compare(UserScoutCounterModel arg0, UserScoutCounterModel arg1) {
		
		Integer userScoutCount0 = arg0.getUserScoutCount();
		Integer userScoutCount1 = arg1.getUserScoutCount();
		int value = userScoutCount1.compareTo(userScoutCount0);
		if (value == 0) {
			Date ScoutDate0 = arg0.getScoutDate();
			Date ScoutDate1 = arg1.getScoutDate();
			value = ScoutDate1.compareTo(ScoutDate0);
			
		}
		return value;
		
	}

	@Override
	public Comparator<UserScoutCounterModel> reversed() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<UserScoutCounterModel> thenComparing(
			Comparator<? super UserScoutCounterModel> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <U extends Comparable<? super U>> Comparator<UserScoutCounterModel> thenComparing(
			Function<? super UserScoutCounterModel, ? extends U> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <U> Comparator<UserScoutCounterModel> thenComparing(
			Function<? super UserScoutCounterModel, ? extends U> arg0,
			Comparator<? super U> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<UserScoutCounterModel> thenComparingDouble(
			ToDoubleFunction<? super UserScoutCounterModel> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<UserScoutCounterModel> thenComparingInt(
			ToIntFunction<? super UserScoutCounterModel> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<UserScoutCounterModel> thenComparingLong(
			ToLongFunction<? super UserScoutCounterModel> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
