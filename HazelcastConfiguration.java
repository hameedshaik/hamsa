package com.sgl.smartpra.master.app.common.aspects;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizeConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class HazelcastConfiguration {
	@Value("${hz.cache.expiry-time}")
	private Integer cacheExpiryTime;

	@Bean
	public Config hazelCastConfig() {
		Config config = new Config();

		config.setInstanceName("hazelcast-instance")
				.addMapConfig(new MapConfig().setName("smartrpa-master")
						.setMaxSizeConfig(new MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
						.setEvictionPolicy(EvictionPolicy.NONE).setTimeToLiveSeconds(cacheExpiryTime));
		return config;
	}
}
