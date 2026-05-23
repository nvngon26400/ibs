package com.sbisec.helios.ap.common.util;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class HttpClientManager {
	private static HttpClientManager instance = null;

	// private Vector<OkHttpClient> clients = null;
	private static Vector<OkHttpClient> clients = new Vector<OkHttpClient>();
	private int max = 0;
    private int timeout = 0;

	public static void init(int initial, int max, int timeout){
		instance = new HttpClientManager(initial, max, timeout);
	}

	private HttpClientManager(int initial, int max, int timeout){

		this.max = max;
		this.timeout = timeout;
		clients = new Vector<OkHttpClient>();
		for(int i = 0; i < initial; i++){
			clients.add(new OkHttpClient.Builder().connectTimeout(timeout,TimeUnit.SECONDS).build());
		}
	}

	public static HttpClientManager get(){
		
		for(int i = 0; i < 1; i++){
			clients.add(new OkHttpClient.Builder().connectTimeout(0,TimeUnit.SECONDS).build());
		}

		return  new HttpClientManager(1, 0, 0);
	}

	public OkHttpClient pop(){
		if(clients.size() == 0){
			return new OkHttpClient.Builder().connectTimeout(timeout,TimeUnit.SECONDS).build();
		}

		return clients.remove(0);
	}

	public void push(OkHttpClient client){
		if(clients.size() >= max){
			return;
		}

		clients.add(client);
	}
}

