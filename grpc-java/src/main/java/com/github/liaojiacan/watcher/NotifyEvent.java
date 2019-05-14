package com.github.liaojiacan.watcher;

/**
 * @author liaojiacan
 * @date 2019/4/10
 */
public class NotifyEvent {
	private String accountId;
	private String token;


	public NotifyEvent(String accountId, String token) {
		this.accountId = accountId;
		this.token = token;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
