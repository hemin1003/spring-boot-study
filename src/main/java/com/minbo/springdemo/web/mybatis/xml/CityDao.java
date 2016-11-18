package com.minbo.springdemo.web.mybatis.xml;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 注意CityMapper.xml的ID名称不能重复和annotation目录下CityMapper方法名冲突，以免出现下面的错误：
 * java.lang.IllegalArgumentException: xxx is ambiguous in Mapped Statements collection (try using the full name including the namespace, or rename one of the entries)
 * 
 * @author Minbo.He
 *
 */
@Component
public class CityDao {

	@Autowired
	private SqlSession sqlSession;

	public int addCity(City city) {
		return this.sqlSession.insert("insertXmlCity", city);
	}
	
	public int removeCityById(long id) {
		return this.sqlSession.delete("deleteXmlCityById", id);
	}
	
	public City selectCityById(long id) {
		return this.sqlSession.selectOne("selectXmlCityById", id);
	}
	
	public int modifyCityById(City city) {
		return this.sqlSession.update("updateXmlCityById", city);
	}
}
