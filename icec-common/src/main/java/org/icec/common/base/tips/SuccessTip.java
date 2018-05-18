package org.icec.common.base.tips;

/**
 * 返回给前台的成功提示
 *
 * 
 */
public class SuccessTip extends Tip {
	private Object data;
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public SuccessTip(){
		super.code = 0;
		super.message = "操作成功";
	}
	public SuccessTip(String message){
		super.code = 0;
		super.message = message;
	}
}
