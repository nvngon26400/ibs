
package com.sbisec.helios.ap.compliance.model;

import com.sbibits.earth.model.ModelBase;

@SuppressWarnings("serial")
public class updateCorComplianceConfirmationModel extends ModelBase {

	private String affectedRows;

	public String getAffectedRows() {
	    return affectedRows;
	}

	public void setAffectedRows(String affectedRows) {
	    this.affectedRows = affectedRows;
	}
}