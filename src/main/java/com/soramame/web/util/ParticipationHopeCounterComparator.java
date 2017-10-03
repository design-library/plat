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

import com.soramame.web.model.ParticipationHopeCounterModel;

/**
 * ParticipationHopeCounterComparator
 * 
 * @author Plat.
 * @version 1.0
 */
@Component
public class ParticipationHopeCounterComparator implements Comparator<ParticipationHopeCounterModel> {

	@Override
	public int compare(ParticipationHopeCounterModel arg0, ParticipationHopeCounterModel arg1) {
		
		Integer participationHopeCount0 = arg0.getParticipationHopeCount();
		Integer participationHopeCount1 = arg1.getParticipationHopeCount();
		int value = participationHopeCount1.compareTo(participationHopeCount0);
		if (value == 0) {
			Date accessDate0 = arg0.getParticipationHopeDate();
			Date accessDate1 = arg1.getParticipationHopeDate();
			value = accessDate1.compareTo(accessDate0);
			
		}
		return value;
		
	}

	@Override
	public Comparator<ParticipationHopeCounterModel> reversed() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<ParticipationHopeCounterModel> thenComparing(
			Comparator<? super ParticipationHopeCounterModel> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <U extends Comparable<? super U>> Comparator<ParticipationHopeCounterModel> thenComparing(
			Function<? super ParticipationHopeCounterModel, ? extends U> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <U> Comparator<ParticipationHopeCounterModel> thenComparing(
			Function<? super ParticipationHopeCounterModel, ? extends U> arg0,
			Comparator<? super U> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<ParticipationHopeCounterModel> thenComparingDouble(
			ToDoubleFunction<? super ParticipationHopeCounterModel> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<ParticipationHopeCounterModel> thenComparingInt(
			ToIntFunction<? super ParticipationHopeCounterModel> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<ParticipationHopeCounterModel> thenComparingLong(
			ToLongFunction<? super ParticipationHopeCounterModel> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
