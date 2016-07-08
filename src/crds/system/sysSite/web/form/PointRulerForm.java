package crds.system.sysSite.web.form;

import crds.pub.util.AbstractForm;

public class PointRulerForm extends AbstractForm {
public long getRuler_id() {
	return ruler_id;
}
public void setRuler_id(long rulerId) {
	ruler_id = rulerId;
}
public int getInit_point() {
	return init_point;
}
public void setInit_point(int initPoint) {
	init_point = initPoint;
}
public int getPoint_multiple() {
	return point_multiple;
}
public void setPoint_multiple(int pointMultiple) {
	point_multiple = pointMultiple;
}
public int getDefault_point() {
	return default_point;
}
public void setDefault_point(int defaultPoint) {
	default_point = defaultPoint;
}
public int getMax_point() {
	return max_point;
}
public void setMax_point(int maxPoint) {
	max_point = maxPoint;
}
public int getStart_point() {
	return start_point;
}
public void setStart_point(int startPoint) {
	start_point = startPoint;
}
public int getAdd_point() {
	return add_point;
}
public void setAdd_point(int addPoint) {
	add_point = addPoint;
}
long ruler_id;
int init_point;
int point_multiple;
int default_point;
int max_point;
int start_point;
int add_point;
int award_point;
public int getAward_point() {
	return award_point;
}
public void setAward_point(int awardPoint) {
	award_point = awardPoint;
}
}
