package org.icec.common.base.tips;
/**
 * 返回带数据的tip
 * @author xxjin
 *
 */

public class DataTip extends SuccessTip{
	private Object data;
	public DataTip(){
		super();
	}

	public DataTip(Object data){
		this.data=data;
	}
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
