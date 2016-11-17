package com.minbo.springdemo.web.mysql;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * 数据库底层表名是：person(小写)，如果没有，则会自动创建表
 * @author Minbo.He
 */
public interface PersonRepository extends JpaRepository<Person, Long> {

	List<Person> findByName(String name);

	List<Person> findByAddress(String address);

	List<Person> findByNameAndAddress(String name, String address);

	//from跟的名称要和类名一致，区分大小写的，比如Person
	@Query("select p from Person p where p.name=:name and p.address=:address")
	List<Person> withNameAndAddressQuery(@Param("name") String Name, @Param("address") String address);

}
