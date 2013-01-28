package org.welovy.jrpg;

public class D {
	private static final int DEBUG = 1;
	public static void d(String msg) {
		if (DEBUG == 1) {
			System.out.println(
			"[DEBUG:" + new Throwable().getStackTrace()[1].getFileName() + ":"
				+ new Throwable().getStackTrace()[1].getLineNumber() + "] "
				+ msg);
		}
		else {
			// do nothing
		}
	}
	
	public static void e(String msg) {
		System.out.println(
		"[ERROR:" + new Throwable().getStackTrace()[1].getFileName() + ":"
			+ new Throwable().getStackTrace()[1].getLineNumber() + "] "
			+ msg);
	}
}

