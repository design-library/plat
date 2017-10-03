package com.soramame.web.biz.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * transmission_history モデルクラス.
 * 
 * @author generated by ERMaster
 * @version $Id$
 */
public class TransmissionHistoryDto implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** history_id. */
	private String historyId;

	/** transmission_type. */
	private String transmissionType;

	/** senders_name. */
	private String sendersName;

	/** recipient_name. */
	private String recipientName;

	/** register_date. */
	private Date registerDate;

	/**
	 * コンストラクタ.
	 */
	public TransmissionHistoryDto() {
	}

	/**
	 * history_id を設定します.
	 * 
	 * @param historyId
	 *            history_id
	 */
	public void setHistoryId(String historyId) {
		this.historyId = historyId;
	}

	/**
	 * history_id を取得します.
	 * 
	 * @return history_id
	 */
	public String getHistoryId() {
		return this.historyId;
	}

	/**
	 * transmission_type を設定します.
	 * 
	 * @param transmissionType
	 *            transmission_type
	 */
	public void setTransmissionType(String transmissionType) {
		this.transmissionType = transmissionType;
	}

	/**
	 * transmission_type を取得します.
	 * 
	 * @return transmission_type
	 */
	public String getTransmissionType() {
		return this.transmissionType;
	}

	/**
	 * senders_name を設定します.
	 * 
	 * @param sendersName
	 *            senders_name
	 */
	public void setSendersName(String sendersName) {
		this.sendersName = sendersName;
	}

	/**
	 * senders_name を取得します.
	 * 
	 * @return senders_name
	 */
	public String getSendersName() {
		return this.sendersName;
	}

	/**
	 * recipient_name を設定します.
	 * 
	 * @param recipientName
	 *            recipient_name
	 */
	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	/**
	 * recipient_name を取得します.
	 * 
	 * @return recipient_name
	 */
	public String getRecipientName() {
		return this.recipientName;
	}

	/**
	 * register_date を設定します.
	 * 
	 * @param registerDate
	 *            register_date
	 */
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	/**
	 * register_date を取得します.
	 * 
	 * @return register_date
	 */
	public Date getRegisterDate() {
		return this.registerDate;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((historyId == null) ? 0 : historyId.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		TransmissionHistoryDto other = (TransmissionHistoryDto) obj;
		if (historyId == null) {
			if (other.historyId != null) {
				return false;
			}
		} else if (!historyId.equals(other.historyId)) {
			return false;
		}
		return true;
	}

}
