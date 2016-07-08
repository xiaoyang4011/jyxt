package crds.system.sysSite.web.form;

import crds.pub.util.AbstractForm;

public class IPSectionForm extends AbstractForm {
   public String getStart_one_segment() {
		return start_one_segment;
	}
	public void setStart_one_segment(String startOneSegment) {
		start_one_segment = startOneSegment;
	}
	public String getStart_two_segment() {
		return start_two_segment;
	}
	public void setStart_two_segment(String startTwoSegment) {
		start_two_segment = startTwoSegment;
	}
	public String getStart_three_segment() {
		return start_three_segment;
	}
	public void setStart_three_segment(String startThreeSegment) {
		start_three_segment = startThreeSegment;
	}
	public String getStart_four_segment() {
		return start_four_segment;
	}
	public void setStart_four_segment(String startFourSegment) {
		start_four_segment = startFourSegment;
	}
	public String getEnd_one_segment() {
		return end_one_segment;
	}
	public void setEnd_one_segment(String endOneSegment) {
		end_one_segment = endOneSegment;
	}
	public String getEnd_two_segment() {
		return end_two_segment;
	}
	public void setEnd_two_segment(String endTwoSegment) {
		end_two_segment = endTwoSegment;
	}
	public String getEnd_three_segment() {
		return end_three_segment;
	}
	public void setEnd_three_segment(String endThreeSegment) {
		end_three_segment = endThreeSegment;
	}
	public String getEnd_four_segment() {
		return end_four_segment;
	}
	public void setEnd_four_segment(String endFourSegment) {
		end_four_segment = endFourSegment;
	}
String start_one_segment;
   String start_two_segment;
   String start_three_segment;
   String start_four_segment;
   String end_one_segment;
   String end_two_segment;
   String end_three_segment;
   String end_four_segment;
   public String getFrom_IPAddress() {
	return from_IPAddress;
}
public void setFrom_IPAddress(String fromIPAddress) {
	from_IPAddress = fromIPAddress;
}
public String getTo_IPAddress() {
	return to_IPAddress;
}
public void setTo_IPAddress(String toIPAddress) {
	to_IPAddress = toIPAddress;
}
String from_IPAddress;
   String to_IPAddress;
   String ipsection_id;
public String getIpsection_id() {
	return ipsection_id;
}
public void setIpsection_id(String ipsectionId) {
	ipsection_id = ipsectionId;
}
}
