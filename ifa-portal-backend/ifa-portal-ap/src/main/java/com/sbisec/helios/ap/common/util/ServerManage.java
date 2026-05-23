package com.sbisec.helios.ap.common.util;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentHashMap;

public class ServerManage {

	private static final int MAX_COOL_TIME = 300;
	private static final int INITIAL_COOL_TIME = 60;
	private static final int INCREASE_COOL_TIME = 2;

	private static class CoolTimeStore {
		public LocalDateTime from;
		public int coolsec;

		CoolTimeStore(){
			from = LocalDateTime.now(ZoneId.of("Asia/Tokyo"));
			coolsec = INITIAL_COOL_TIME;
		}
	}

	private static ConcurrentMap<String, CoolTimeStore> suspends = new ConcurrentHashMap<String, CoolTimeStore>();

	public static void add(String host){
		CoolTimeStore val = suspends.get(host);
		if(val == null){
			val = new CoolTimeStore();
			suspends.put(host, val);
		}

		LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Tokyo"));
		Duration dur = Duration.between(val.from, now);
		if(dur.getSeconds() > val.coolsec){
			val.from = now;
			val.coolsec = val.coolsec * INCREASE_COOL_TIME;
			if(val.coolsec > MAX_COOL_TIME){
				val.coolsec = MAX_COOL_TIME;
			}
		}
	}

	public static boolean isSuspend(String org){

		String[] host = org.split(":", 0);
		if(host.length == 0){
			return false;
		}

		CoolTimeStore val = suspends.get(host[0]);
		if(val == null){
			return false;
		}

		LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Tokyo"));
		Duration dur = Duration.between(val.from, now);
		if(dur.getSeconds() > val.coolsec){
			return false;
		}

		return true;
	}

	public static void delete(String host){

		suspends.remove(host);
	}
}
