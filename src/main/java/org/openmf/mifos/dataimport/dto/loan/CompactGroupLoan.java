package org.openmf.mifos.dataimport.dto.loan;

import org.openmf.mifos.dataimport.dto.Status;
import org.openmf.mifos.dataimport.dto.client.CompactGroup;

import java.util.Comparator;
import java.util.Locale;

public class CompactGroupLoan {

	private final String accountNo;

	private final CompactGroup group;

	private final String loanProductName;

	private final Double principal;

	private final LoanTimeline timeline;

	private final Status status;

	public CompactGroupLoan(String accountNo, CompactGroup group, String loanProductName, Double principal, LoanTimeline timeline, Status status) {
		this.accountNo = accountNo;
		this.group = group;
		this.loanProductName = loanProductName;
		this.principal = principal;
		this.timeline = timeline;
		this.status = status;
	}
	
	public String getAccountNo() {
        return this.accountNo;
    }

	public String getGroupId() {
		return this.group.getId().toString();
	}

	public String getGroupName() {
		return this.group.getName();
	}

	public String getLoanProductName() {
        return this.loanProductName;
    }
	
	public Double getPrincipal() {
		return this.principal;
	}
	
	public Boolean isActive() {
		return this.status.isActive();
	}
	
	public LoanTimeline getTimeline() {
		return timeline;
	}

	public static final Comparator<CompactGroupLoan> groupNameComparator = new Comparator<CompactGroupLoan>() {
		
	@Override
	public int compare(CompactGroupLoan loan1, CompactGroupLoan loan2) {
		String clientOfLoan1 = loan1.getGroupName().toUpperCase(Locale.ENGLISH);
		String clientOfLoan2 = loan2.getGroupName().toUpperCase(Locale.ENGLISH);
		return clientOfLoan1.compareTo(clientOfLoan2);
	 }
	};
}
