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

import com.soramame.web.model.ProjectAccessCounterModel;

/**
 * ProjectAccessComparator
 * 
 * @author Plat.
 * @version 1.0
 */
@Component
public class ProjectAccessCounterComparator implements Comparator<ProjectAccessCounterModel> {

	@Override
	public int compare(ProjectAccessCounterModel arg0, ProjectAccessCounterModel arg1) {
		
		Integer projectAccessCount0 = arg0.getProjectAccessCount();
		Integer projectAccessCount1 = arg1.getProjectAccessCount();
		int value = projectAccessCount1.compareTo(projectAccessCount0);
		if (value == 0) {
			Date accessDate0 = arg0.getAccessDate();
			Date accessDate1 = arg1.getAccessDate();
			value = accessDate1.compareTo(accessDate0);
			
		}
		return value;
		
	}

	@Override
	public Comparator<ProjectAccessCounterModel> reversed() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<ProjectAccessCounterModel> thenComparing(
			Comparator<? super ProjectAccessCounterModel> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <U extends Comparable<? super U>> Comparator<ProjectAccessCounterModel> thenComparing(
			Function<? super ProjectAccessCounterModel, ? extends U> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <U> Comparator<ProjectAccessCounterModel> thenComparing(
			Function<? super ProjectAccessCounterModel, ? extends U> arg0,
			Comparator<? super U> arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<ProjectAccessCounterModel> thenComparingDouble(
			ToDoubleFunction<? super ProjectAccessCounterModel> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<ProjectAccessCounterModel> thenComparingInt(
			ToIntFunction<? super ProjectAccessCounterModel> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparator<ProjectAccessCounterModel> thenComparingLong(
			ToLongFunction<? super ProjectAccessCounterModel> arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
