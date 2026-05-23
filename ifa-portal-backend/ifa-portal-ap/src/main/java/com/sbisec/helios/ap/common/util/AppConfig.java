package com.sbisec.helios.ap.common.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sbisec.helios.ap.common.enums.LoadBalance;

public class AppConfig{
	@JsonCreator
	AppConfig(
		@JsonProperty("protocol") String in1
		, @JsonProperty("servers") List<String> in2
		, @JsonProperty("loadbalance") LoadBalance in3
		, @JsonProperty("retry") int in4
		, @JsonProperty("hash_key") String in5)
	{
		protocol = in1;
		servers = in2;
		loadbalance = in3;
		retry = in4;
		hashKey = in5;
	}

	@lombok.Getter
	String protocol;

	List<String> servers;

	@lombok.Getter
	LoadBalance loadbalance;

	@lombok.Getter
	int retry;

	@lombok.Getter
	String hashKey;

	private AtomicInteger counter = new AtomicInteger();

	public List<String> getServers()
	{
		List<String> out = new ArrayList<>();

		for(String s : servers){
			if(!ServerManage.isSuspend(s)){
				out.add(s);
			}
		}

		switch(loadbalance){
			case ROUND_ROBIN:
				if (out.size() != 0) {
					int cur = counter.getAndAccumulate(0, (prev, x) -> { if(prev == Integer.MAX_VALUE){ return 0;} return prev + 1;});
					cur = cur % out.size();
					for(int i = 0; i < cur; i++){
						out.add(out.remove(0));
					}
				}
				break;
			case RANDOM:
				Collections.shuffle(out);
				break;
			case BACKUP:
			default:
				break;
		}

		return out;
	}
}
