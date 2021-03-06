package com.soramame.web.model;

import java.io.Serializable;
import java.util.Date;

/**
 * project_access_counter モデルクラス.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
public class ProjectAccessCounterModel implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** project_id. */
	private String projectId;

	/** title. */
	private String title;

	/** project_access_count. */
	private Integer projectAccessCount;

	/** access_date. */
	private Date accessDate;

	/**
	 * コンストラクタ.
	 */
	public ProjectAccessCounterModel() {
		projectAccessCount = 0;
	}

	/**
	 * project_id を設定します.
	 * 
	 * @param projectId
	 *            project_id
	 */
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	/**
	 * project_id を取得します.
	 * 
	 * @return project_id
	 */
	public String getProjectId() {
		return this.projectId;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * project_access_count を設定します.
	 * 
	 * @param projectAccessCount
	 *            project_access_count
	 */
	public void setProjectAccessCount(Integer projectAccessCount) {
		this.projectAccessCount = projectAccessCount;
	}

	/**
	 * project_access_count を取得します.
	 * 
	 * @return project_access_count
	 */
	public Integer getProjectAccessCount() {
		return this.projectAccessCount;
	}

	/**
	 * access_date を設定します.
	 * 
	 * @param accessDate
	 *            access_date
	 */
	public void setAccessDate(Date accessDate) {
		this.accessDate = accessDate;
	}

	/**
	 * access_date を取得します.
	 * 
	 * @return access_date
	 */
	public Date getAccessDate() {
		return this.accessDate;
	}

}
