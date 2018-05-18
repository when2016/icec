package org.icec.common.base.tips;

/**
 * 返回给前台的错误提示
 *
 */
public class ErrorTip extends Tip {

    public ErrorTip(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }
    public ErrorTip( String message) {
        super();
        this.code = 1;
        this.message = message;
    }
}
