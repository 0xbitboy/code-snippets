package com.github.liaojiacan.geo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Circle;
import org.springframework.data.geo.GeoResult;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RedisPeopleNearByServiceImpl implements PeopleNearByService {

	@Autowired
	private StringRedisTemplate redisTemplate;

	public final static String NEAR_BY_KEY = "nearby";

	@Override
	public void recordCoord(String username, Double x, Double y) {
		GeoOperations<String, String> ops = redisTemplate.opsForGeo();
		ops.geoAdd(NEAR_BY_KEY,new Point(x,y),username);
	}

	@Override
	public List<PeopleNearByDto> findNearBy(Double x, Double y, Double distance) {
		GeoOperations<String, String> ops = redisTemplate.opsForGeo();
		GeoResults<RedisGeoCommands.GeoLocation<String>> result = ops.geoRadius(NEAR_BY_KEY, new Circle(x, y, distance), RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().includeDistance());
		List<GeoResult<RedisGeoCommands.GeoLocation<String>>> list = result.getContent();

		List<PeopleNearByDto> rs = new ArrayList<>();
		list.forEach((obj)->{
			String name = obj.getContent().getName();
			String dist = obj.getDistance().getValue()+obj.getDistance().getUnit();
			rs.add(new PeopleNearByDto(name,dist));
		});

		return rs;
	}
}
